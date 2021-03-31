package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;

/**
 * Servlet implementation class Profile
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ProfileServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		HttpSession session=request.getSession();
		
		User employee= (User) session.getAttribute("name");
		 int id = employee.getId();
		 System.out.println(id);
		 String position= employee.getPosition();
		 String username= employee.getUsername();
		String password= employee.getPassword();
		 String firstName= employee.getFirstName();
		 String lastName= employee.getLastName();
		
		
		out.print("<html>"
				+ "<head>"
				+ "		<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"> "
				+ "</head>"
				+ "<body>"
				+ "<h3>Profile Page</h3>"
				
				+ "			<h4>Employee ID: "+id+"</h4>"
				+ "			<h4>Employee Position: "+position+"</h4>"
				+ "			<label for='fname'>First name:</label>"
				+ "<br>"
				+ "  		<input type='text' id='fname' name='fname' value='"+firstName+"'>"
				+ "<br>"
				+ "  		<label for='lname'>Last name:</label>"
				+ "<br>"
				+ "  		<input type='text' id='lname' name='lname' value='"+lastName+"'>"
				+ "<br>"
				+ "			<label for='ursName'>Username:</label>"
				+ "<br>"
				+ "  		<input type='text' id='usrName' name='ursName' value='"+username+"'>"
				+ "<br>"
				+ "<br>"
				+ "  		<button type='button' onclick='update()'>update</button>"
				
				+ "<button onclick='goBack()'>back</button>"
		
				+ " <script src=\"userProfile.js\"></script>"
				+ "</body>"
				+ "</html>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
