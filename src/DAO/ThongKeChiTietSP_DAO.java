package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.ThongKeCTSP;

public class ThongKeChiTietSP_DAO {
	public ArrayList<ThongKeCTSP> ThongKeSoLuongSp(String loai,Object ngayMoc, Object NgayHan){
		ArrayList<ThongKeCTSP> lSanPhams = new ArrayList<>();
		Connection con = new Connect_DB().getConnection();
		String sQLString2 = "select SanPham.tenSanPham, ChiTietHoaDon.kichCo, ChiTietHoaDon.mauSac,SUM(ChiTietHoaDon.soLuong),SUM(HoaDon.tienNhan) from \r\n"
				+ "ChiTietHoaDon inner join SanPham on ChiTietHoaDon.maSanPham = SanPham.maSanPham  inner \r\n"
				+ "join LoaiSanPham on LoaiSanPham.maLoaiSanPham = SanPham.maLoaiSanPham inner join HoaDon on \r\n"
				+ "HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon where LoaiSanPham.tenLoaiSanPham = '"+loai+"'\r\n"
				+ "and HoaDon.thoiGianTaoHoaDon >=  '"+ngayMoc+"' "
				+ "and  HoaDon.thoiGianTaoHoaDon <=  '"+NgayHan+"' \r\n"
				+ "group by SanPham.tenSanPham,ChiTietHoaDon.kichCo,ChiTietHoaDon.mauSac";
		try {
			Statement statement = con.createStatement();
			ResultSet rs =  statement.executeQuery(sQLString2);
			while (rs.next()) {
				String tenSp = rs.getString(1);
				String Size = rs.getString(2);
				String mau = rs.getString(3);
				int soluong = rs.getInt(4);
				double tongtien = rs.getDouble(5);
				ThongKeCTSP tKeSl = new ThongKeCTSP(tenSp, Size, mau, soluong, tongtien);
				lSanPhams.add(tKeSl);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lSanPhams;
		
	}
}
