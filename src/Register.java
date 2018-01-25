
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
		
		
	}
	
	// Register function
	// require collection of variables. eg user name, password, email address
	/*public boolean userRegister(String tableName) {
		
	}*/
}
