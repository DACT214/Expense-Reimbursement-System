package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Request;
import com.revature.model.User;
import com.revature.service.ManagerService;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ManagerService managerService;

    public SearchServlet() {
        super();
        this.managerService = new ManagerService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		
		User employee= (User) session.getAttribute("name");

		
		String first = request.getHeader("fName").trim();
		String last =request.getHeader("lName").trim();
		
							
		ArrayList<Request> reqs= null;
		try {
			reqs = managerService.getSearchedReq(first, last);
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
		
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
