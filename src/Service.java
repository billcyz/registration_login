
import java.util.Date;
import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.GenericServlet;

public class Service extends HttpServlet {
	
	private String msg;
//	private long currentTime;
	
	public void init() throws ServletException {
		msg = "";
	}
	
	// Handle POST request
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		TimeStamp currentTime = new TimeStamp();
		
		PrintWriter out = response.getWriter();
		out.println(currentTime.getEpochTime());
		
		doGet(request, response);
		
	}
	
	// Handle GET request
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void destory() {
		
	}
}

