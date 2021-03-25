package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletOne
 */
public class servletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servletOne() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
response.setContentType("text/html");
		
		PrintWriter out= response.getWriter();
		out.println("<html> <head> <title>  </title> </head> <body>");
		out.print("<h1>Reading Form Values</h1>");
		
		String fname=request.getParameter("first");
		
		Cookie fnameC = new Cookie("first",fname); //creates cookie object
		fnameC.setMaxAge(5*60); //5 min till deleted (age in seconds)
		response.addCookie(fnameC); // sends to browser (parameters = the name of your cookie)
		
		out.print("<h2> first name : "+fname+"</h2>");
		
	
		
		String lname=request.getParameter("last");
		
		Cookie lnameC = new Cookie("last",lname); //creates cookie object
		lnameC.setMaxAge(5*60); //5 min till deleted (age in seconds)
		response.addCookie(lnameC); // sends to browser (parameters = the name of your cookie)
		
		out.print("<h2> last name : "+lname+"</h2>");
		
		out.println("<form action=test2 method=post>");//created a form with an action to my second servlet, and the post method
		out.print("<input type=submit value=continue>");
		out.println("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
