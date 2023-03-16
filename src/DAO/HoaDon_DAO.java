package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	ArrayList<HoaDon> danhSachHoaDon;
	public HoaDon_DAO() {
		danhSachHoaDon = new ArrayList<HoaDon>();
	}
	
	public ArrayList<HoaDon> getAllHoaDon(){
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "Select * from HoaDon";
			Statement statement= con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				Date thoiGianTaoHoaDon = rs.getDate(2);
				String maNhanVien = rs.getString(3);
				String maKhachHang = rs.getString(4);
				int caLamViec = rs.getInt(5);
				double tongTien = rs.getDouble(6);
				double tongGiam = rs.getDouble(7);
				double tongPhaiTra = rs.getDouble(8);
				double tienNhan = rs.getDouble(9);
				double tienTra = rs.getDouble(10);				
				HoaDon hd = new HoaDon(maHoaDon, thoiGianTaoHoaDon, new NhanVien(maNhanVien), new KhachHang(maKhachHang), caLamViec, tongTien, tongGiam, tongPhaiTra, tienNhan, tienTra);
				danhSachHoaDon.add(hd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return danhSachHoaDon;
	}
	
	public boolean themHoaDon(HoaDon hd) {
		Connection con = Connect_DB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into HoaDon values (?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setDate(2, hd.getNgayTaoHoaDon());
			stmt.setString(3, hd.getKhachHang().getMaKhachHang());
			stmt.setString(4, hd.getNhanVien().getMaNhanVien());
			stmt.setInt(5, hd.getCaLamViec());
			stmt.setDouble(6, hd.getTongCong());
			stmt.setDouble(7, hd.getTienGiam());
			stmt.setDouble(8, hd.getTienThanhToan());
			stmt.setDouble(9, hd.getTienNhan());
			stmt.setDouble(10, hd.getTienTra());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0 ;
	}	
}