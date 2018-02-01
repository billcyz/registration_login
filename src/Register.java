
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

// Require user name, password, email address, etc

public class Register extends HttpServlet {
	long currentTime;
	String userName;
	String password;
	
	public void init() throws ServletException {
		currentTime = new TimeStamp().getEpochTime();
	}
	
	// Handle POST request
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int resultId;
		String resultMsg;
		
		System.out.println("Register time is: " + currentTime);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		userName = request.getHeader("username");
		password = request.getHeader("password");
		
		if(!userRegister("user_info", userName, password)) {
			// User not found
			
			resultId = 0;
			resultMsg = "{" + "\"id\"" + ":" + " " + resultId + "}";
			out.println(resultMsg);
			
			
			out.println(userName + ", " + password);
			out.println("Result is: Error");
		} else {
			// Find user
			resultId = 1;
			resultMsg = "{" + "\"id\"" + ":" + " " + resultId + "}";
			
			out.println("Found User");
		}
	}
	
	// Register function
	// require collection of variables. eg user name, password, email address
	public boolean userRegister(String tableName, String name, String pwd) {
		try {
			System.out.println("tablename is: " + tableName + ", " + "name is: " + name + ", " + "pwd is: " + pwd);
			DbConnection con = new DbConnection("jdbc:mysql://192.168.146.132:3306", "br_test",
					"root", "password");
			// prepared statement
			String preparedStmt = "SELECT `name` FROM " + tableName + " WHERE `name` = ?";
			Class.forName(DbConnection.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(con.DB_URL + "/" + con.DB, con.USER, con.PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(preparedStmt);
			pstmt.setString(1, name);
//			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// User is found
				rs.close();
				pstmt.close();
				conn.close();
				return true;
			} else {
				// User not found, add user info into database (Create user)
				String registerStmt = "INSERT INTO " + tableName + " (name, password) VALUES (?, ?)";
				PreparedStatement prepRegisterStmt = conn.prepareStatement(registerStmt);
				prepRegisterStmt.setString(1, name);
				prepRegisterStmt.setString(1, pwd);
				
				prepRegisterStmt.executeQuery();
				
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
