
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class TestDbClass {
	
	public static void main(String[] args) {
		
		try {
			CachedRowSetImpl crs = null;
			
			DbConnection con = new DbConnection("jdbc:mysql://127.0.0.1:3306", "br_test",
					"root", "Cyz37212302494");
			con.setConnection("select * from user_info");
			crs = con.getRowSet();
			
			while(crs.next()) {
				System.out.println("NAME: " + crs.getString("name") + 
						", " + "PASSWORD: " + crs.getString("password"));
			}
			crs.close();
		} catch (SQLException se) {
			
		}
	}
}
