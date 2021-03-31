package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;
import com.revature.model.User;

public interface UserDAO {

	User getLogin(String position, String username, String password, Connection con) throws SQLException;
	
	User getUser(String username, Connection con) throws SQLException;
	
	ArrayList<Request> getAllReq(String username, String statusIn, Connection con) throws SQLException;
}
