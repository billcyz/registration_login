
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

//Login parameters: userName, password

public class Login extends HttpServlet {
	
	long currentTime;
	String userName;
	String password;
	
	public void init() throws ServletException {
		currentTime = new TimeStamp().getEpochTime();
	}
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("!!!!!!");
	}
	
	// Handle POST request
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		// DB interaction
		if(!userLogin("user_info", userName, password)) {	
			out.println("Error");
		} else {
			out.println("Found User");
		}
	}
	
	// Login function
	// Require table name, column name, user name, and user password
	public boolean userLogin(String tableName, String name, String pwd) {
		try {
			DbConnection con = new DbConnection("jdbc:mysql://127.0.0.1:3306", "br_test",
					"root", "Cyz37212302494");
			// prepared statement
			String preparedStmt = "SELECT `name` FROM ? WHERE `name` = ?"
					+ " and `password` = ?";
			Class.forName(DbConnection.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(con.DB_URL + "/" + con.DB, con.USER, con.PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(preparedStmt);
			pstmt.setString(1, tableName);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					System.out.println("NAME: " + rs.getString("name"));
				} while(rs.next());
				return true;
			} else {
				System.out.println("No data");
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
