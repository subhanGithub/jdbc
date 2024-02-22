package servlet_jdbc_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home")

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement ps= con.prepareStatement("select username,password from user where username=? and password=?");
					ps.setString(1, username);
					ps.setString(2, password);
					
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						resp.sendRedirect("home.html");
					}
					else
					{
						resp.setContentType("text/html");
						PrintWriter pw=resp.getWriter();
						pw.print("incorrect uername or passs word...............!");
						RequestDispatcher	rd=	req.getRequestDispatcher("login.html");
						rd.include(req, resp);
					}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

}
