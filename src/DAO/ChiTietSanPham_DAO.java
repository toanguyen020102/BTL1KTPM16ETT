package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.ChiTietSanPham;
import entity.SanPham;

public class ChiTietSanPham_DAO {
	ArrayList<ChiTietSanPham> danhSachChiTietSanPham;
	
	public ChiTietSanPham_DAO() {
		danhSachChiTietSanPham = new ArrayList<ChiTietSanPham>();
	}
	
	public ArrayList<ChiTietSanPham> getAllChiTietSanPhamTheoMaSanPham(String maSanPham){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Select * from ChiTietSanPham where maSanPham = ?");
			stmt.setString(1, maSanPham);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maSP = rs.getString(1);
				String kichCo = rs.getString(2);
				String mauSac = rs.getString(3);
				int soLuong = rs.getInt(4);
				double giaBan = rs.getDouble(5);
				
				ChiTietSanPham ctsp = new ChiTietSanPham(new SanPham(maSP), kichCo, mauSac, soLuong, giaBan);
				danhSachChiTietSanPham.add(ctsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachChiTietSanPham;
	}
	public ArrayList<ChiTietSanPham> getAllChiTietSanPham(){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Select * from ChiTietSanPham");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maSP = rs.getString(1);
				String kichCo = rs.getString(2);
				String mauSac = rs.getString(3);
				int soLuong = rs.getInt(4);
				double giaBan = rs.getDouble(5);
				
				ChiTietSanPham ctsp = new ChiTietSanPham(new SanPham(maSP), kichCo, mauSac, soLuong, giaBan);
				danhSachChiTietSanPham.add(ctsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachChiTietSanPham;
	}
	public ArrayList<String> getAllMauSac(){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		ArrayList<String> danhSachMauSac = new ArrayList<String>();
		try {
			stmt = con.prepareStatement("SELECT DISTINCT mauSac FROM ChiTietSanPham");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				danhSachMauSac.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachMauSac;
	}
	public ArrayList<String> getAllKichCo(){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		ArrayList<String> danhSachKichCo = new ArrayList<String>();
		try {
			stmt = con.prepareStatement("SELECT DISTINCT kichCo FROM ChiTietSanPham");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				danhSachKichCo.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachKichCo;
	}
	public ArrayList<SanPham> getAllSanPhamTheoMauSacVaKichCo(String mauSacQuery, String kichCoQuery){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		ArrayList<SanPham> danhSachSanPham = new ArrayList<SanPham>();
		try {
			stmt = con.prepareStatement("SELECT DISTINCT \r\n"
					+ "	sp.maSanPham, \r\n"
					+ "	sp.tenSanPham \r\n"
					+ "FROM ChiTietSanPham ctsp JOIN SanPham sp \r\n"
					+ "ON ctsp.maSanPham = sp.maSanPham\r\n"
					+ "WHERE (" + mauSacQuery + ") \r\n"
					+ "AND (" + kichCoQuery + ")");
			// stmt = con.prepareStatement("SELECT TOP 10 sp.maSanPham, cthd.tenSanPham, COUNT(cthd.tenSanPham) as soLuongBanRa, SUM(hd.tongPhaiTra) as lmao FROM ChiTietHoaDon cthd INNER JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon INNER JOIN SanPham sp ON sp.maSanPham = cthd.maSanPham INNER JOIN ChiTietSanPham ctsp ON ctsp.maSanPham = sp.maSanPham GROUP BY sp.maSanPham, cthd.tenSanPham, cthd.tenSanPham ORDER BY lmao DESC" + "WHERE (" + mauSacQuery + ") " + "AND (" + kichCoQuery + ")");					
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				SanPham sp = new SanPham(rs.getString(1), rs.getString(2));
				// String kichCo = rs.getString(3);
				// String mauSac = rs.getString(4);
				// Double giaNhap = rs.getDouble(5);
				// Integer soLuong = rs.getInt(6);
				// ChiTietSanPham ctsp = new ChiTietSanPham(
				// 	sp,
				// 	kichCo,
				// 	mauSac,
				// 	soLuong,
				// 	giaNhap
				// );
				danhSachSanPham.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachSanPham;
	}
	public boolean themChiTietSanPham(ChiTietSanPham ctsp) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietSanPham values (?,?,?,?,?)");
			
			stmt.setString(1, ctsp.getSanPham().getMaSP());
			stmt.setString(2, ctsp.getKichCo());
			stmt.setString(3, ctsp.getMauSac());
			stmt.setInt(4, ctsp.getSoLuong());
			stmt.setDouble(5, ctsp.getGiaBan());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean deleteChiTietSanPham(String maSp, String kichCo, String mauSac, String soLuong, String giaNhap) {
		int n = 0;
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from ChiTietSanPham where maSanPham = ? and kichCo = ? and mauSac = ? and soLuong = ? and giaNhap = ?");
			stmt.setString(1, maSp);
			stmt.setString(2, kichCo);
			stmt.setString(3, mauSac);
			stmt.setString(4, soLuong);
			stmt.setString(5, giaNhap);
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return n > 0;
	}
	public boolean capNhatChiTietSanPhamTheoMa(SanPham sp, ChiTietSanPham ctsp) {
		int n = 0;
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		
		PreparedStatement stmt = null;	
		try {
			stmt = con.prepareStatement("update ChiTietSanPham set soLuong = ? where maSanPham = ? and kichCo = ? and mauSac = ?");
			
			stmt.setInt(1, ctsp.getSoLuong());
			stmt.setString(2, sp.getMaSP());
			stmt.setString(3, ctsp.getKichCo());
			stmt.setString(4, ctsp.getMauSac());
					
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean deleteAllChiTietSanPhamTheoMaSanPham(SanPham sp) {
		int n = 0;
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from ChiTietSanPham where maSanPham = ?");
			stmt.setString(1, sp.getMaSP());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return n > 0;
	}
	public ArrayList<ChiTietSanPham> deleteAllChiTietSanPham(String maSanPham){
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Delete ChiTietSanPham");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maSP = rs.getString(1);
				String kichCo = rs.getString(2);
				String mauSac = rs.getString(3);
				int soLuong = rs.getInt(4);
				double giaBan = rs.getDouble(5);
				
				ChiTietSanPham ctsp = new ChiTietSanPham(new SanPham(maSP), kichCo, mauSac, soLuong, giaBan);
				danhSachChiTietSanPham.add(ctsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return danhSachChiTietSanPham;
	}
	public boolean updataChiTietSanPhamTheoMa(ChiTietSanPham ctsp) {
		PreparedStatement stmt = null;
		int n = 0;
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			
			stmt = con.prepareStatement("update ChiTietSanPham set soLuong -= ? where maSanPham = ? and kichCo = ? and mauSac = ?");
			
			stmt.setInt(1, ctsp.getSoLuong());
			stmt.setString(2, ctsp.getSanPham().getMaSP());
			stmt.setString(3, ctsp.getKichCo());
			stmt.setString(4, ctsp.getMauSac());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
