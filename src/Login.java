
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

//Login parameters: userName, password

public class Login extends HttpServlet {
	
//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	long currentTime;
	String userName;
	String password;
	
	public void init() throws ServletException {
		currentTime = new TimeStamp().getEpochTime();
	}
	
//	public void doGet(HttpServletRequest request, 
//			HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		out.println("!!!!!!");
//	}
	
	// Handle POST request
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		userName = request.getHeader("username");
		password = request.getHeader("password");
		
		
		out.println("user name is: " + userName + ", " + "password is: " + password);
		
//		boolean a = userLogin("user_info", userName, password);
		
//		out.println("Result is: " + a);
		
		// DB interaction
		if(!userLogin("user_info", userName, password)) {
			// User not found
			
			out.println(userName + ", " + password);
			out.println("Result is: Error");
		} else {
			// Find user
			
			
			out.println("Found User");
		}
	}
	
	// Login function
	// Require table name, column name, user name, and user password
	public boolean userLogin(String tableName, String name, String pwd) {
		try {
			System.out.println("tablename is: " + tableName + ", " + "name is: " + name + ", " + "pwd is: " + pwd);
			DbConnection con = new DbConnection("jdbc:mysql://192.168.146.132:3306", "br_test",
					"root", "password");
			// prepared statement
			String preparedStmt = "SELECT `name` FROM " + tableName + " WHERE `name` = ?"
					+ " and `password` = ?";
			Class.forName(DbConnection.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(con.DB_URL + "/" + con.DB, con.USER, con.PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(preparedStmt);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				do {
////					System.out.println("NAME: " + rs.getString("name"));
//				} while(rs.next());
				return true;
			} else {
//				System.out.println("No data");
				return false;
			}
		} catch (SQLException se){
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
