package com.diworksdev.ecsite.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static String driverName = "org.mariadb.jdbc.Driver";
	private static String url="jdbc:mysql://localhost/ecsite";	
	private static String user = "root";
	private static String password = "";
	
	public Connection getConnetion() {
		Connection con = null;
		
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
