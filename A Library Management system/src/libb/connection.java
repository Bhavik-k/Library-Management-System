package libb;
import java.sql.*;
import javax.sql.*;

public class connection {
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/library-management-sys", "root", "");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT Name FROM `books` ");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
	public Statement takeThis() {

		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library-management-sys", "root", "");
			st = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
		
	}
}
