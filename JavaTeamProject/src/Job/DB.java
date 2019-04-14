package Job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DB {

	static String id_DB="";
	static String pw_DB="";


	public static void db_StartF() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					"Job",
					"Job");

			Statement st1 = conn.createStatement();

			String sql1 = "select * from USER_INFO";

			ResultSet rs1 = st1.executeQuery(sql1);

			while(rs1.next()) {
				id_DB = rs1.getString("ID");
				pw_DB = rs1.getString("PASSWORD");
			}

			rs1.close();
			st1.close();
			conn.close();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void db_SignUp(String sID, String sPW, String sNA, String sUI) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					"Job",
					"Job");
			Statement st = conn.createStatement();
			
			String sql = "INSERT INTO user_info VALUES('"+sID+"','"+sPW+"','"+sNA+"','"+sUI+"')";
			ResultSet rs = st.executeQuery(sql);

			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	
	}
	public static void db_Alterin(String aID, String aPW, String aNA, String aUI) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					"Job",
					"Job");
			Statement st = conn.createStatement();
		
			String sql = "UPDATE USER_INFO SET ID='"+aID+"', PASSWORD='"+aPW+"', NAME='"+aNA+"', UNIVERSITY='"+aUI+"' WHERE ID='"+id_DB+"'";
			ResultSet rs = st.executeQuery(sql);
			
			
			rs.close();
			st.close();
			conn.close();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
