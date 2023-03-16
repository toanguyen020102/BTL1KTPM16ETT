package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.NhanVienTK;
import entity.ThongKeDoanhThu;

public class ThongKeNhanVien_DAO {
	
	public static ArrayList<NhanVienTK> thongKeDoanhThuTOPNV(String TenNhanVien,Object NgayMoc,Object ngayDen){
	ArrayList<NhanVienTK> tknv = new ArrayList<>();
	Connection con = new Connect_DB().getConnection();
	String sql = "select NhanVien.tenNhanVien, FORMAT(DATEADD(MONTH, DATEDIFF(MONTH, 0, HoaDon.thoiGianTaoHoaDon), 0),'yyyy-MM-dd')\r\n"
			+ "as timehd ,sum(HoaDon.tongTien) as sotien,COUNT(HoaDon.maHoaDon)\r\n"
			+ "from HoaDon inner join NhanVien on NhanVien.maNhanVien = HoaDon.maNhanVien\r\n"
			+ "where thoiGianTaoHoaDon >= '"+NgayMoc+"' \r\n"
			+ "and thoiGianTaoHoaDon <= '"+ngayDen+"'\r\n"
			+ "and NhanVien.tenNhanVien = N'"+TenNhanVien+"' \r\n"
			+ "group by NhanVien.tenNhanVien, FORMAT(DATEADD(MONTH, DATEDIFF(MONTH, 0, HoaDon.thoiGianTaoHoaDon), 0),'yyyy-MM-dd') order by sotien DESC";
	try {
		Statement statement = con.createStatement();
		ResultSet rs =  statement.executeQuery(sql);
		while (rs.next()) {
		Date dategiaodich = rs.getDate(2);
		double sotien = rs.getDouble(3);
		int SoHD = rs.getInt(4);
		NhanVienTK tk = new NhanVienTK(dategiaodich, SoHD, sotien);
		tknv.add(tk);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return tknv;
	
	}
}
