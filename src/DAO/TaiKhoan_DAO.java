package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import connect_DB.Connect_DB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	
	public TaiKhoan_DAO() {
		
	}
	
	public TaiKhoan timTaiKhoan(String tenTaiKhoanNhap, String matKhauNhap) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		TaiKhoan tkDN = null;
		try {

			stmt = con.prepareStatement("select * from TaiKhoan where tenTaiKhoan = ? and matKhau = ?");
			
			stmt.setString(1, tenTaiKhoanNhap);
			stmt.setString(2, matKhauNhap);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String taiKhoan = rs.getString(1);
				String matKhau = rs.getString(2);
				String maNhanVien = rs.getString(3);
				
				tkDN = new TaiKhoan(taiKhoan, matKhau, new NhanVien(maNhanVien));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//		JOptionPane.showMessageDialog(null, tkDN.getTaiKhoan() + ", "+ tkDN.getMatKhau()); 
		return tkDN;
	}
	
	public TaiKhoan timTaiKhoanTheoTenDangNhap(String tenTaiKhoanNhap) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		TaiKhoan tkDN = null;
		try {

			stmt = con.prepareStatement("select * from TaiKhoan where tenTaiKhoan = ?");
			
			stmt.setString(1, tenTaiKhoanNhap);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String taiKhoan = rs.getString(1);
				String matKhau = rs.getString(2);
				String maNhanVien = rs.getString(3);
				
				tkDN = new TaiKhoan(taiKhoan, taiKhoan, new NhanVien(maNhanVien));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//		JOptionPane.showMessageDialog(null, tkDN.getTaiKhoan() + ", "+ tkDN.getMatKhau()); 
		return tkDN;
	}
	
	public boolean taoTaiKhoan(TaiKhoan tk, NhanVien nv) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int  n = 0;
		try {
			stmt = con.prepareStatement("insert into TaiKhoan values (?,?,?)");
			
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, tk.getMatKhau());
			stmt.setString(3, nv.getMaNhanVien());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	public boolean updateTaiKhoan(TaiKhoan tk,NhanVien nv) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int  n = 0;
		try {
			stmt = con.prepareStatement("update TaiKhoan set matKhau = ? where maNhanVien = ?");
			
			stmt.setString(1, tk.getMatKhau());
			stmt.setString(2, nv.getMaNhanVien());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	public boolean deleteTaiKhoan(String maNhanVien) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete TaiKhoan where maNhanVien = ?");
			stmt.setString(1, maNhanVien);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
}
