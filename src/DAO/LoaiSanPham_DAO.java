package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.LoaiSanPham;
import entity.NhanVien;

public class LoaiSanPham_DAO {

	ArrayList<LoaiSanPham> danhSachLoai;
	public LoaiSanPham_DAO() {
		danhSachLoai = new ArrayList<LoaiSanPham>();
	}
	
	public ArrayList<LoaiSanPham> getAllLoaiSanPham(){
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "Select* from LoaiSanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maLoai = rs.getString(1);
				String tenLoai = rs.getString(2);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenLoai);
				danhSachLoai.add(loai);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return danhSachLoai;
	}
	
	public boolean themLoaiSanPham(LoaiSanPham loai) {
		
		Connection con = Connect_DB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoaiSanPham values (?,?)");
			stmt.setString(1, loai.getMaLoai());
			stmt.setString(2, loai.getTenLoai());
			n = stmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return n > 0;
	}

	public LoaiSanPham timLoai(String tenLoaiTim) {
		LoaiSanPham loai = null;
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "Select * from LoaiSanPham Where tenLoaiSanPham = " + tenLoaiTim;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maLoai = rs.getString(1);
				String tenLoai = rs.getString(2);
				loai = new LoaiSanPham(maLoai, tenLoai);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return loai;
	}
	
}
