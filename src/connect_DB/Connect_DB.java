package connect_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_DB {
	public static Connection con = null;
	public static Connect_DB instance = new Connect_DB();	
	
	public static Connect_DB getInstance() {
		return instance;
	}
	public void connect() throws SQLException {
		try {
			// JOptionPane.showMessageDialog(null, "1");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyCuaHangBanQuanAo", "sa", "123");
			System.out.println("Connected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	public void disconnect() {
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {	
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) {
		
	}
}
