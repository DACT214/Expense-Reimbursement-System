package com.revature.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;

public interface RequestDAO {
	
	ArrayList<Request> empReq(String username, Connection con) throws SQLException;

	void newEmpReq(String username, double ammount, Connection con) throws SQLException;
	
}
