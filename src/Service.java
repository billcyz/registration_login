
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
		msg = "Initial Message";
	}
	
	// Handle POST request
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		EpochTimeStamp currentTime = new EpochTimeStamp();
		
		PrintWriter out = response.getWriter();
		out.println(currentTime.getEpochTime());
//		currentTime.getEpochTime();
	}
	
	public void destory() {
		
	}
}

// Timestamp for each incoming request
// Time is in epoch time format, and with milliseconds
class EpochTimeStamp {
	
	// return current epoch timestamp in milliseconds
	long getEpochTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}
	
}

