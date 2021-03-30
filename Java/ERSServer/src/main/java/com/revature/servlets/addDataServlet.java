package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Request;
import com.revature.model.User;
import com.revature.service.RequestService;
import com.revature.service.UserService;

/**
 * Servlet implementation class Welcome
 */
public class addDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	RequestService requestService;
	UserService	userService;
   
    public addDataServlet() {
        super();
        this.requestService = new RequestService();
        this.userService = new UserService();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		
		User employee= (User) session.getAttribute("name");
		String username = employee.getUsername();
		
		
		
		double ammount = Double.parseDouble(request.getParameter("ammount"));
		
		
		try {
			requestService.newEmpReq(username, ammount);
			RequestDispatcher rd=request.getRequestDispatcher("/userPage.html");  
	        rd.include(request, response);
	        
	        ArrayList<Request> empReq=null;
			try { 
				 empReq = requestService.getEmpReq(username);
			}catch(SQLException | NullPointerException e) {
				e.fillInStackTrace();
			}
			
	        out.print("<body>"
	        		+ "    <h3>"
	        		+ "        Welcome "+employee.getFirstName() +" "+ employee.getLastName()
	        		+ "    </h3>"
	        		+ "    <div>"
	        		+ "			<table>"
	        		+ "				<tr> "
	        		+ "					<th style='border:1px solid black';>Request #</th>"
	        		+ "					<th style='border:1px solid black';>Reimbursement amount</th>"
    				+ "					<th style='border:1px solid black';>Status</th>"
	        		+ "				</tr>");
	       
	        for (int x= 0; x< empReq.size(); x++) {
	        	out.print(""
        			+ "				<tr> "
	        		+ "					<td style='border:1px solid black';> #"+ empReq.get(x).getId() +"</td>"
	        		+ "					<td style='border:1px solid black';>$"+ empReq.get(x).getAmount() +"</td>"
    				+ "					<td style='border:1px solid black';>"+ empReq.get(x).getStatus() +"</td>"
    				+ "				</tr>");
	        }
    				out.print(""
	        		+ "			</table>"
	        		+ "			<form action=addData  method=post>"
	        		+ "				<input type=number step=0.01 min=0 name=ammount>"
	        		+ "<br>"
	        		+ "				<input type=submit value='New Request'>"
	        		+ "			</form>"
	        		+ "    </div>"
	        		+ "</body>"
	        		+ "</html>");
	        
		
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
		
		
		
	}

}
