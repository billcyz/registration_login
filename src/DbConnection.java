import java.sql.*;

import com.sun.rowset.CachedRowSetImpl;

// Database connection

public class DbConnection {
	
	// JDBC driver name
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String DB_URL;
	private String DB;
	private String USER;
	private String PASS;
	private CachedRowSetImpl crs = null;
	
	
	// Parameters for database connection
	public DbConnection(String dbUrl, String dbName,
			String user, String password) {
		DB_URL = dbUrl;
		DB = dbName;
		USER = user;
		PASS = password;
	}
	
	// Establish database connection, and do sql query
	public void setConnection(String sql) {
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL + "/" + DB, USER, PASS);
			Statement stmt = conn.createStatement();
			
			System.out.println(JDBC_DRIVER);
			System.out.println(DB_URL);
			System.out.println(DB);
			System.out.println(USER);
			System.out.println(PASS);
			System.out.println(sql);
			
			// Get result
			ResultSet rs = stmt.executeQuery(sql);
			crs.populate(rs);
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get sql query result
	public CachedRowSetImpl getRowSet() {
		return crs;
	}
	
}
