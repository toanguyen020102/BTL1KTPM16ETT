package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.ThongKeSoLuongSanPham;

public class ThongKeSoLuongSanPham_DAO {
	
	public ArrayList<ThongKeSoLuongSanPham> ThongKeSoLuongSp(Object ngayMoc, Object NgayHan){
		ArrayList<ThongKeSoLuongSanPham> lSanPhams = new ArrayList<>();
		Connection con = new Connect_DB().getConnection();
		String sqlString = "select SanPham.tenSanPham, ChiTietHoaDon.kichCo, ChiTietHoaDon.mauSac,SUM(ChiTietHoaDon.soLuong),SUM(HoaDon.tienNhan) from "
		+ "ChiTietHoaDon inner join SanPham on ChiTietHoaDon.maSanPham = SanPham.maSanPham  inner "
		+ "join LoaiSanPham on LoaiSanPham.maLoaiSanPham = SanPham.maLoaiSanPham inner join HoaDon on "
		+ "HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon where LoaiSanPham.tenLoaiSanPham = 'Ao So Mi' "
		+ "and HoaDon.thoiGianTaoHoaDon >=  '2017-12-30' and  HoaDon.thoiGianTaoHoaDon <=  '2022-12-30' "
		+ "group by SanPham.tenSanPham,ChiTietHoaDon.kichCo,ChiTietHoaDon.mauSac";
		try {
			Statement statement = con.createStatement();
			ResultSet rs =  statement.executeQuery(sqlString);
			while (rs.next()) {
				String tenSp = rs.getString(1);
				int soluong = rs.getInt(2);
				double tongtien = rs.getDouble(3);
				ThongKeSoLuongSanPham tKeSl = new ThongKeSoLuongSanPham(tenSp, soluong, tongtien);
				lSanPhams.add(tKeSl);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lSanPhams;
		
	}
	public ArrayList<ThongKeSoLuongSanPham> ThongKeSoLuongSpTheoTop(Object ngayMoc, Object NgayHan){
		ArrayList<ThongKeSoLuongSanPham> lSanPhams = new ArrayList<>();
		Connection con = new Connect_DB().getConnection();
		String sqlTOPString = "\r\n"
				+ "select SanPham.tenSanPham, SUM(ChiTietHoaDon.soLuong),SUM(HoaDon.tienNhan)  tienban\r\n"
				+ "from ChiTietHoaDon inner join SanPham \r\n"
				+ "on ChiTietHoaDon.maSanPham = SanPham.maSanPham inner join LoaiSanPham\r\n"
				+ "on LoaiSanPham.maLoaiSanPham = SanPham.maLoaiSanPham inner join HoaDon\r\n"
				+ "on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
				+ "where HoaDon.thoiGianTaoHoaDon >=  '" + ngayMoc+"'\r\n"
				+ "and  HoaDon.thoiGianTaoHoaDon <=  '" + NgayHan+"'  \r\n"
				+ "group by SanPham.tenSanPham order By tienban DESC";		
		try {
			Statement statement = con.createStatement();
			ResultSet rs =  statement.executeQuery(sqlTOPString);
			while (rs.next()) {
				String tenSp = rs.getString(1);
				int soluong = rs.getInt(2);
				double tongtien = rs.getDouble(3);
				ThongKeSoLuongSanPham tKeSl = new ThongKeSoLuongSanPham(tenSp, soluong, tongtien);
				lSanPhams.add(tKeSl);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lSanPhams;
		
	}
}