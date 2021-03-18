package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.model.Employee;

public interface EmployeeDAO {
	
	Employee getLogin(String position, String username, String password, Connection con) throws SQLException;
	

}
