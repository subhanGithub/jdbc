package servlet_jdbc_demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//to fetch the data from request
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String phone =req.getParameter("number");
		String gender=req.getParameter("gender");
		String dateofbirth=req.getParameter("dob");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//Store the data into data base
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement ps= con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, gender);
			ps.setString(5, dateofbirth);
			ps.setString(6, username);
			ps.setString(7, password);
			ps.executeUpdate(); 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		resp.sendRedirect("login.html");
	}

}
