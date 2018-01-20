
import java.sql.*;
//import com.sun.rowset.CachedRowSetImpl;

public class TestDbClass {
	
	public static void main(String[] args) {
		
		String sql;
		String userName = "user01";
		String userPassword = "password11";
		
		String conSql;
		
		try {
//			CachedRowSetImpl crs = null;
			
			DbConnection con = new DbConnection("jdbc:mysql://127.0.0.1:3306", "br_test",
					"root", "Cyz37212302494");
//			con.setConnection("select * from user_info");
			
			sql = "select name from user_info where name = ";
			conSql = sql + "\'" + userName + "\'";
			
			
			String preparedStmt = "SELECT ? FROM ? WHERE ? = ?"
					+ " and ? = ?";
			
			
			System.out.println(DbConnection.JDBC_DRIVER);
			System.out.println(con.DB_URL);
			System.out.println(con.DB);
			System.out.println(con.USER);
			System.out.println(con.PASS);
			System.out.println(conSql);
			
			
			Class.forName(DbConnection.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(con.DB_URL + "/" + con.DB, con.USER, con.PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(preparedStmt);
			pstmt.setString(1, userName);
			pstmt.setString(2, userPassword);
			
			System.out.println(pstmt);
			
//			Statement stmt = conn.createStatement();
			
//			ResultSet rs = stmt.executeQuery(conSql);
			
			ResultSet rs = pstmt.executeQuery();
			
//			while(rs.next()) {
//				System.out.println("NAME: " + rs.getString("name") + 
//						", " + "PASSWORD: " + rs.getString("password"));
//			}
			
			
			if(rs.next()) {
				do {
					System.out.println("NAME: " + rs.getString("name"));
				} while(rs.next());
			} else
				System.out.println("No data");
			
//			while(rs.next()) {
//				System.out.println("NAME: " + rs.getString("name"));
//			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
//			crs = con.getRowSet();
			
//			while(crs.next()) {
//				System.out.println("NAME: " + crs.getString("name") + 
//						", " + "PASSWORD: " + crs.getString("password"));
//			}
//			crs.close();
		} catch (SQLException se) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
