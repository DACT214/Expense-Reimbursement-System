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
import com.revature.model.Request;
import com.revature.model.User;
import com.revature.service.ManagerService;
import com.revature.service.RequestService;
import com.revature.service.UserService;

/**
 * Servlet implementation class servletOne
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService;
	RequestService requestService;
	ManagerService managerService;
   
    public loginServlet() {
    	this.userService = new UserService();
    	this.requestService = new RequestService();
    	this.managerService = new ManagerService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		response.setContentType("text/html");
		
		PrintWriter out= response.getWriter();
		
		
		User employee = null;
		
		ArrayList<Request> empReq = null;
		
		HttpSession session=request.getSession();
		
		
		
		String typ="";
		String un=""; 
		String pw=""; 
	
		
		try {
			if(session.getAttribute("name") != null) {
				employee= (User) session.getAttribute("name");
				typ = employee.getPosition();
				un = employee.getUsername();
			}else {
				typ = request.getParameter("empType").toString();
				
				 un = request.getParameter("username");
				
				 pw = request.getParameter("password");
				
				employee = userService.getLogin(typ, un, pw);
				
				session.setAttribute("name", employee);
		}
			
			try { 
				 empReq = requestService.getEmpReq(un);
				 
			
			}catch(SQLException | NullPointerException e) {
				e.fillInStackTrace();
				
			        out.print("Sorry Username or Password were not found");
			}
			
			
		} catch(SQLException | NullPointerException | UserNotFoundException e) {
			 
		        e.fillInStackTrace();
		       
		}
				
		
		if(employee != null) {
			out.print("<!DOCTYPE html><html>");
	     RequestDispatcher rd;
	        
	        		switch(typ) {
	 //==== manager page=======
	        		case "manager":
	        			rd=request.getRequestDispatcher("/ManagerPage.html");  
	        	        rd.include(request, response);
					        			out.print("<body>"
					        					+ "    <h3>"
					        					+ "        Welcome "+employee.getFirstName() +" "+ employee.getLastName()
					        					+ "    </h3>");
					        					String status = "";
					        					ArrayList<Request> reqs= null;
					        					try {
					        						reqs = managerService.getAllReq(status);
					        					} catch (SQLException e) {
					        						
					        						e.printStackTrace();
					        					}
					        					
					        					
					        					
					        					out.print("<tr class=\"p-3 mb-2 bg-light text-dark\">"
					        							+ "            <th style='border:1px solid black'>Request #</th>"
					        							+ "            <th style='border:1px solid black'>Username</th>"
					        							+ "            <th style='border:1px solid black'>Name</th>"
					        							+ "            <th style='border:1px solid black'>Requested Amount</th>"
					        							+ "            <th style='border:1px solid black'>Status</th>"
					        							+ "</tr>");
					        					
					        					
					        							for(Request req : reqs) {
					        								String style = "";
					        								
					        								switch(req.getStatus()) {
					        								case "pending":
					        									style = "p-3 mb-2 bg-danger text-white";
					        									break;
					        								case "accepted":
					        									style = "p-3 mb-2 bg-success text-white";
					        									break;
					        								default:
					        									break;
					        								}
					        								
					        					out.print("<tr class ='"+style+"'>"
					        							+ "<td style='border:1px solid black'>"+req.getId()+"</td>"
					        							+ "<td style='border:1px solid black'>"+req.getUsername()+"</td>"
					        							+ "<td style='border:1px solid black'> "+req.getFirstName()+" "+req.getLastName()+"</td>"
					        							+ "<td style='border:1px solid black'> $"+req.getAmount()+"</td>"
					        							+ "<td style='border:1px solid black'>"+req.getStatus()+"</td>"
					        							+ "<tr>");
					        							}
					        					out.print("</Table>"
					        							+ "<br> "
					        							+ "    <button type=\"button\" onclick=\"getData()\" name=status class=\"btn btn-outline-primary\">get All Requests</button>"
					        							+ "    <button type=\"button\" onclick=\"getPending()\" name=status value=\"pending\" class=\"btn btn-outline-primary\">get Pending Request</button>"
					        							+ "	   <button type=\"button\" onclick=\"getAccepted()\" name=status value=\"accepted\" class='btn btn-outline-primary'>get Accepted Request</button>"
					        							+ "<br><br>"
										        		+ "				<input type=text name=first id=\"first\" value='' class=\"form-control\" style=\"width: 200px; display:inline;\" placeholder='First Name'>"
										        		+ "				<input type=text name=last id=\"last\" value='' class='form-control' style='width: 200px; display:inline;' placeholder='Last Name'>"
										        		+ "				<button type=button onclick='getSerched()' class='btn btn-outline-success'>search</button>"
										        		+ "<br><br>"
										        		+ "				<label for=reqNum>Resolve a Request:</label>"
										        		+ "				<input type=number step=0 min=0 name=reqNum id='reqID' class='form-control' style='width: 200px; display:inline;' placeholder='Request ID Number'>"
										        		+ "				<button type=button onclick='accpetReq()' class='btn btn-outline-success'>Accept</button> <button type=button onclick='denyReq()' class='btn btn-outline-danger'>Deny</button>"
					        							+ "    <script src=\"test.js\"></script>"
					        							+ "</div>");		
	        			break;    	
	        			
	        			
	        			
	        			
	        			
	        			
	        			
	        			
	 //======================================================================================================================       			
	 //==== employee page====================================================================================================
	        		case "employee":
	        			rd=request.getRequestDispatcher("/userPage.html");  
	        			rd.include(request, response);
					        			out.print("<body>"
					        					+ "    <h3>"
					        					+ "        Welcome "+employee.getFirstName() +" "+ employee.getLastName()
					        					+ "    </h3>"
					        					+ "    <div>"
								       			+ "			<table id=\"data\" class=\"table\" style='width:50%'>"
								        		+ "				<tr class=\"p-3 mb-2 bg-light text-dark\"> "
								        		+ "					<th style='border:1px solid black';>Request #</th>"
								        		+ "					<th style='border:1px solid black';>Reimbursement amount</th>"
							    				+ "					<th style='border:1px solid black';>Status</th>"
								        		+ "				</tr>");
	        							for(int x= 0; x< empReq.size(); x++) {
	        								String style = "";
	        								
	        								switch(empReq.get(x).getStatus()) {
	        								case "pending":
	        									style = "p-3 mb-2 bg-danger text-white";
	        									break;
	        								case "accepted":
	        									style = "p-3 mb-2 bg-success text-white";
	        									break;
	        								default:
	        									break;
	        								}
	        								
	        					out.print("<tr class ='"+style+"'>"
						        		+ "					<td style='border:1px solid black';> #"+ empReq.get(x).getId() +"</td>"
						        		+ "					<td style='border:1px solid black';>$"+ empReq.get(x).getAmount() +"</td>"
					    				+ "					<td style='border:1px solid black';>"+ empReq.get(x).getStatus() +"</td>"
	        							+ "<tr>");
	        							}
								        out.print(""
								        		+ "			</table>"
								        		+ "			<form action=addData  method=post>"
								        		+ "				<input type=number step=0.01 min=0 name=ammount class=\"form-control\" style='display:inline; width: 200px;' placeholder='000.00'>"
								        		+ "<input type=submit value='New Request' class='btn btn-outline-primary' style='display:inline;'>"
								        		+ "			</form>"
								        		+ "<br>"
			        							+ "    <button type=\"button\" onclick=\"getData()\" name=status class='btn btn-outline-primary'>get All Requests</button>"
			        							+ "    <button type=\"button\" onclick=\"getPending()\" name=status value=\"pending\" class='btn btn-outline-primary'>get Pending Request</button>"
			        							+ "	   <button type=\"button\" onclick=\"getAccepted()\" name=status value=\"accepted\" class='btn btn-outline-primary'>get Accepted Request</button>"
			        							+ "    <script src=\"userRequest.js\"></script>");
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
