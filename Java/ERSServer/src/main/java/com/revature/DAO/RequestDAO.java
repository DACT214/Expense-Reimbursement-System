package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;
import com.revature.model.User;

public interface RequestDAO {
	
	ArrayList<Request> empReq(String username, Connection con) throws SQLException;

	void newEmpReq(String username, double ammount, Connection con) throws SQLException;
	
	User updatePro(int ID, String username, String firstName, String lastName, Connection con) throws SQLException;
}
