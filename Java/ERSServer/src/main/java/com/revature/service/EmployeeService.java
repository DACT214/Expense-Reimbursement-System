package com.revature.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.DAO.EmployeeDAO;
import com.revature.DAO.EmployeeDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Employee;
import com.revature.utilities.ConnectionUtil;

public class EmployeeService {
	EmployeeDAO employeeDAO;
	
	public EmployeeService() {
		this.employeeDAO = new EmployeeDAOImpl();	
	}
	
	public Employee getLogin(String postion, String username, String password) throws UserNotFoundException, SQLException{
		try (Connection con = ConnectionUtil.getConnection()) {
			Employee user;

			user = employeeDAO.getLogin(postion, username, password, con);

			if (user == null) {
				System.out.println(new UserNotFoundException("username or password do not match or does not exist"));

			}

			return user;
		}
	}

	
	
	
}
