package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.model.User;

public interface UserDAO {

	User getLogin(String position, String username, String password, Connection con) throws SQLException;
	
	User getUser(String username, Connection con) throws SQLException;
}
