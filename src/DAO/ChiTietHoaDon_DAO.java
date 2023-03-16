package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHoaDon_DAO {
	
	ArrayList<ChiTietHoaDon> dsChiTietHoaDon;
	
	public ChiTietHoaDon_DAO() {
		dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
	}
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values (?,?,?,?,?,?,?)");
			
			stmt.setString(1, cthd.getHoaDon().getMaHoaDon());
			stmt.setString(2, cthd.getSanPham().getMaSP());
			stmt.setString(3, cthd.getSanPham().getTenSP());
			stmt.setString(4, cthd.getKichCo());
			stmt.setString(5, cthd.getMauSac());
			stmt.setInt(6, cthd.getSoLuong());
			stmt.setDouble(7, cthd.getGiaBan());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<ChiTietHoaDon> selectChiTietHoaDonTheoMa(String maHoaDon) {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDons = new ArrayList<>(dsChiTietHoaDon);

		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "select maHoaDon, maSanPham, kichCo, mauSac, soLuong, giaBan from ChiTietHoaDon WHERE maHoaDon = '" + maHoaDon + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(
					new HoaDon(rs.getString(1)), 
					new SanPham(rs.getString(2)), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getInt(5), 
					rs.getFloat(6)
				);
				
				dsChiTietHoaDons.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDons;
	}
}