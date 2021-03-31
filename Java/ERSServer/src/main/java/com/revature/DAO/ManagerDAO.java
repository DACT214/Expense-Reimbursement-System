package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;

public interface ManagerDAO {
	
	ArrayList<Request> getAllReq(String statusIn, Connection con) throws SQLException;
	
	ArrayList<Request> getSearchedReq(String firstName, String lastName, Connection con) throws SQLException;
	
	void acceptOrDeny(String status, int reqID, Connection con)throws SQLException;
}
