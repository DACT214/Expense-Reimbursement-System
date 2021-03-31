package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.ManagerService;

/**
 * Servlet implementation class ResolveReqServlet
 */
public class ResolveReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ManagerService managerService;
	

    public ResolveReqServlet() {
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
		
		String status = request.getHeader("status").trim();
		int reqID = Integer.parseInt(request.getHeader("reqNum").trim());
		
		try {
			managerService.acceptOrDeny(status, reqID);
		} catch (SQLException e) {out.print("that record does not exist");}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
