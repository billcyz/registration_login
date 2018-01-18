package user_login;

import java.sql.*;

public class DbTest {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://100.100.100.100:3306/br_test";
	
	//  Database credentials
	static final String DB = "br_test";
	static final String USER = "root";
	static final String PASS = "Password";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    
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
