
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
	
	// Handle POST request
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		
		// DB interaction
		
	}
}
