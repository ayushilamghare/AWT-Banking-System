package DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {
	
	static Connection con;
	
	public static Connection establishConnection() throws SQLException {
		
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306"
				+ "/banksystem?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true",
				"root", "A@yushi10");
		return con;
	}
	
	public static void closeConnection() throws SQLException {
		con.close();
	}

	
}
