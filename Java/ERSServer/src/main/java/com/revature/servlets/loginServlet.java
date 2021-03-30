package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.model.Request;
import com.revature.service.UserService;
import com.revature.service.RequestService;

/**
 * Servlet implementation class servletOne
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService;
	RequestService requestService;
	
   
    public loginServlet() {
    	this.userService = new UserService();
    	this.requestService = new RequestService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		response.setContentType("text/html");
		
		PrintWriter out= response.getWriter();
		
		
		User employee = null;
		
		ArrayList<Request> empReq = null;
		
		HttpSession session=request.getSession();
		
		
		
		String typ = request.getParameter("empType").toString();
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
//		switch(typ) {
//		case "employee":
//				System.out.println("employee");
//				break;
//		case "manager":
//				System.out.println("manager");
//				break;
//		default:
//				System.out.println("end");
//				break;
//		}
		
		try {
				employee = userService.getLogin(typ, un, pw);
				
				session.setAttribute("name", employee);
			
			try { 
				 empReq = requestService.getEmpReq(un);
				 
			
			}catch(SQLException | NullPointerException e) {
				e.fillInStackTrace();
			}
			
			
		} catch(SQLException | NullPointerException | UserNotFoundException e) {
			 
		        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
		        rd.include(request, response);  
			
		}
				
		
		if(employee != null) {
			out.print("<!DOCTYPE html><html>");
	        RequestDispatcher rd=request.getRequestDispatcher("/userPage.html");  
	        rd.include(request, response);
	     
	        
	        out.print("<body>"
	        		+ "    <h3>"
	        		+ "        Welcome "+employee.getFirstName() +" "+ employee.getLastName()
	        		+ "    </h3>"
	        		+ "    <div>");
	        		switch(typ) {
	        		case "employee":
								       out.print( "			<table>"
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
								        		+ "			</form>");
	        		break;
	        		case "manager":
				       out.print(""
				       			+ "			hi from manager"
				    		   
				    		   +""); 			
				    break;    		
	        		}
				       out.print( "    </div>"
				        		+ "</body>"
				        		+ "</html>");
	        
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
	        rd.include(request, response); 
	        out.print("Sorry Username or Password were not found");
				System.out.println("can't print");
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
