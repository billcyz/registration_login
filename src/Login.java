
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

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
		if(!userLogin(userName, password)) {	
			out.println("Error");
		} else {
			out.println("Found User");
		}
	}
	
	// Login function
	public boolean userLogin(String name, String pwd) {
		String tableName = "user_info";
		String sql = "select username from " + tableName + " where "
				+ "username = " + name + " and " + " password = " + password;
		
		try {
			CachedRowSetImpl crs = null;
			DbConnection con = new DbConnection("jdbc:mysql://127.0.0.1:3306", "br_test",
					"root", "Cyz37212302494");
			
			con.setConnection(sql);
			
			crs = con.getRowSet();
			while(crs.next()) {
				System.out.println("NAME: " + crs.getString("name") + 
						", " + "PASSWORD: " + crs.getString("password"));
			}
			crs.close();
			return true;
		} catch (SQLException se){
			return false;
		}
	}
}
