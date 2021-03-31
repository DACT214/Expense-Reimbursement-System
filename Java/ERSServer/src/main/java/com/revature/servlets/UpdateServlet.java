package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.service.RequestService;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    RequestService requestService;
    
    public UpdateServlet() {
        super();
        this.requestService= new RequestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		HttpSession session=request.getSession();
		
		User employee = (User) session.getAttribute("name");
		
		int id = employee.getId();
		String username = request.getHeader("username");
		String firstName= request.getHeader("fName");
		String lastName= request.getHeader("lName");
		
		
		
		
		try {
			employee = requestService.updatePro(id, username, firstName, lastName);
		} catch (SQLException e) {e.printStackTrace();}
		
		session.removeAttribute("name");
		session.invalidate();
		
		 session=request.getSession();
		session.setAttribute("name", employee);
		out.print("Profile Updated");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
