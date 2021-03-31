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
import com.revature.service.UserService;

/**
 * Servlet implementation class UserViewServlet
 */
public class UserViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService;


    public UserViewServlet() {
        super();
        this.userService = new UserService();
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
		String username = employee.getUsername();
		String status = "";
		if(request.getHeader("status") != null) {
			status = request.getHeader("status");
		}
		
		ArrayList<Request> reqs= null;
		try {
			reqs = userService.getAllReq(username, status);
		} catch (SQLException e) {e.printStackTrace();}
		
		out.print("<tr class=\"p-3 mb-2 bg-light text-dark\">"
				+ "            <th style='border:1px solid black'>Request #</th>"
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
