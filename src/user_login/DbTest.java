package user_login;

import java.sql.*;

//Tips: change mysql bind address, and grant mysql user remote access
//privileges

public class DbTest {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
	
	//  Database credentials
	static final String DB = "br_test";
	static final String USER = "root";
	static final String PASS = "Cyz37212302494";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL + "/" + DB, USER, PASS);
		    
		    stmt = conn.createStatement();
		    
		    String sql;
		    sql = "select * from sample_data";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	String timeValue = rs.getString("timestamp");
		    	System.out.println("timestamp value is: " + timeValue);
		    }
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			//Handle errors for Class.forName
		    e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
				
			}
			
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		System.out.println("Goodbye!");
	}
}
