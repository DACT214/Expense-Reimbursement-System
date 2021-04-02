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
		RequestDispatcher rd;
		String username = null;
		double ammount = (Double) 0.00;
		try { username = employee.getUsername();
		
		
		
		 ammount = Double.parseDouble(request.getParameter("ammount"));}
		catch (NumberFormatException | NullPointerException e) {
			e.fillInStackTrace();
			rd = request.getRequestDispatcher("/login");
	        rd.include(request, response);
			rd.forward(request, response);
		}
		
		
		try {
			requestService.newEmpReq(username, ammount);
	        
	        ArrayList<Request> empReq;
			try { 
				 empReq = requestService.getEmpReq(username);
			}catch(SQLException | NullPointerException e) {
				e.fillInStackTrace();
			}
			
	        rd = request.getRequestDispatcher("/login");
	        rd.include(request, response);
		
	        
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
