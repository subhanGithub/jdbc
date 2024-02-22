package servlet_jdbc_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	PreparedStatement ps= con.prepareStatement("select * from user");
	
	ResultSet rs=ps.executeQuery();
	
		
		
		//create table
	PrintWriter pw=	resp.getWriter();
	resp.setContentType("text/html");
	
	pw.print("<html><body>");
	pw.print("<h3> USER DETAILS </h3>");
	pw.print("<table border=1> <tr>"+"<td> <b>NAME</b></td>"+
			"<td><b>EMAIL</b></td>"+
			"<td><b>phonenumber</b></td>"+
			"<td><b>gender</b></td>"+
			"<td><b>dob</b></td>"+
			"<td><b>Username</b></td></tr>");
		
			
	
	
	while(rs.next())
	{
		String name=rs.getString(1);
		String email=rs.getString(2);
		String phone=rs.getString(3);
		String gender=rs.getString(4);
		String dob=rs.getString(5);
		String Username=rs.getString(6);
		pw.print("<tr>"+"<td>"+name+"</td>"
				+"<td>"+email+"</td>"
				+"<td>"+phone+"</td>"
				+"<td>"+gender+"</td>"
				+"<td>"+dob+"</td>"
				+"<td>"+Username+"</td>");
	}
	pw.print("</table></body></html>");
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	}
 
}
