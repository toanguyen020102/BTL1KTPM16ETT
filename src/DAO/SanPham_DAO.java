package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect_DB.Connect_DB;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;

public class SanPham_DAO {
	
	ArrayList<SanPham> danhSachSanPham;
	
	public SanPham_DAO() {
		danhSachSanPham = new ArrayList<SanPham>();	
	}
	
	public ArrayList<SanPham> getAllSanPham(){
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "select * from SanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String chatLieu = rs.getString(3);
				String kieuDang = rs.getString(4);
				String phuHopGioiTinh = rs.getString(5);
				String donVi = rs.getString(6);
				String moTa = rs.getString(7);
				String tenTH = rs.getString(8);
				String nuocTH = rs.getString(9);
				String tenNSX = rs.getString(10);
				String sDTNSX = rs.getString(11);
				String emailNSX = rs.getString(12);
				String duongDan = rs.getString(13);
				String maLoai = rs.getString(14);
				String maNV = rs.getString(15);
				SanPham sp = new SanPham(
					ma, 
					ten, 
					chatLieu, 
					kieuDang, 
					phuHopGioiTinh, 
					donVi, 
					moTa, 
					tenTH, 
					nuocTH, 
					tenNSX, 
					sDTNSX, 
					emailNSX, 
					duongDan, 
					new LoaiSanPham(maLoai), 
					new NhanVien(maNV)
				);
				
				danhSachSanPham.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachSanPham;
	}
	public ArrayList<SanPham> timSanPhamTheoNhieuTieuChi(
		String ten,
		String loai,
		String tenTH,
		String nuocTH,
		String tenNSX,
		String sdtNSX,
		String emailNSX,
		String kieu,
		String chatLieu,
		String donVi,
		String gioiTinh,
		String kichCo,
		String mauSac,
		String soLuong,
		String giaBan
	) {
		String tenQ, loaiQ, tenTHQ, nuocTHQ, tenNSXQ, sdtNSXQ, emailNSXQ, kieuQ, chatLieuQ, donViQ, gioiTinhQ, kichCoQ, mauSacQ, soLuongQ, giaBanQ;
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			
			if ( ten == null || ten.length() == 0 ) { tenQ = ""; } 
			else { tenQ = "tenSanPham LIKE '%" + ten + "%' AND "; }
			
			if ( loai == null || loai.length() == 0 ) { loaiQ = ""; } 
			else { loaiQ = "tenLoaiSanPham LIKE '%" + loai + "%' AND "; }
			
			if ( tenTH == null || tenTH.length() == 0 ) { tenTHQ = ""; } 
			else { tenTHQ = "thuongHieu LIKE '%" + tenTH + "%' AND "; }
			
			if ( nuocTH == null || nuocTH.length() == 0 ) { nuocTHQ = ""; } 
			else { nuocTHQ = "nuocThuongHieu LIKE '%" + nuocTH + "%' AND "; }
			
			if ( tenNSX == null || tenNSX.length() == 0 ) { tenNSXQ = ""; } 
			else { tenNSXQ = "tenNhaSanXuat LIKE '%" + tenNSX + "%' AND "; }
			
			if ( sdtNSX == null || sdtNSX.length() == 0 ) { sdtNSXQ = ""; } 
			else { sdtNSXQ = "soDienThoaiNhaSanXuat LIKE '%" + sdtNSX + "%' AND "; }
			
			if ( emailNSX == null || emailNSX.length() == 0 ) { emailNSXQ = ""; } 
			else { emailNSXQ = "emailNhaSanXuat LIKE '%" + emailNSX + "%' AND "; }
			
			if ( kieu == null || kieu.length() == 0 ) { kieuQ = ""; } 
			else { kieuQ = "kieuDang LIKE '%" + kieu + "%' AND "; }
			
			if ( chatLieu == null || chatLieu.length() == 0 ) { chatLieuQ = ""; } 
			else { chatLieuQ = "chatLieu LIKE '%" + chatLieu + "%' AND "; }
			
			if ( donVi == null || donVi.length() == 0 ) { donViQ = ""; } 
			else { donViQ = "donViSanPham LIKE '%" + donVi + "%' AND "; }
			
			gioiTinhQ = "phuHopGioiTinh = " + (gioiTinh == "Nam" ? 0 : 1) + " AND ";
			
			if ( kichCo == null || kichCo.length() == 0 ) { kichCoQ = ""; } 
			else { kichCoQ = "kichCo LIKE '%" + kichCo + "%' AND "; }
			
			if ( mauSac == null || mauSac.length() == 0 ) { mauSacQ = ""; } 
			else { mauSacQ = "mauSac LIKE '%" + mauSac + "%' AND "; }
			
			if ( soLuong == null || soLuong.length() == 0 ) { soLuongQ = ""; } 
			else { soLuongQ = "soLuong = " + soLuong + " AND "; }
			
			if ( giaBan == null || giaBan.length() == 0 ) { giaBanQ = ""; } 
			else { giaBanQ = "giaBan = " + giaBan + " AND "; }
			
			String sql = "SELECT * FROM SanPham sp JOIN LoaiSanPham lsp\r\n"
					+ "ON sp.maLoaiSanPham = lsp.maLoaiSanPham JOIN ChiTietSanPham ctsp\r\n"
					+ "ON ctsp.maSanPham = sp.maSanPham\r\n"
					+ "WHERE " 
					+ tenQ 
					+ loaiQ 
					+ tenTHQ 
					+ nuocTHQ 
					+ tenNSXQ 
					+ sdtNSXQ 
					+ emailNSXQ 
					+ kieuQ 
					+ chatLieuQ 
					+ donViQ
					+ gioiTinhQ
					+ kichCoQ
					+ mauSacQ
					+ soLuongQ
					+ giaBanQ;

			sql = sql.substring(0, sql.length() - 4);
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				
				String sMa = rs.getString(1);
				String sTen = rs.getString(2);
				String sChatLieu = rs.getString(3);
				String sKieuDang = rs.getString(4);
				String sPhuHopGioiTinh = rs.getString(5);
				String sDonVi = rs.getString(6);
				String sMoTa = rs.getString(7);
				String sTenTH = rs.getString(8);
				String sNuocTH = rs.getString(9);
				String sTenNSX = rs.getString(10);
				String sSDTNSX = rs.getString(11);
				String sEmailNSX = rs.getString(12);
				String sDuongDan = rs.getString(13);
				String sMaLoai = rs.getString(14);
				String sMaNV = rs.getString(15);
				SanPham sp = new SanPham(
					sMa, 
					sTen, 
					sChatLieu, 
					sKieuDang, 
					sPhuHopGioiTinh, 
					sDonVi, 
					sMoTa, 
					sTenTH, 
					sNuocTH, 
					sTenNSX, 
					sSDTNSX, 
					sEmailNSX, 
					sDuongDan, 
					new LoaiSanPham(sMaLoai), 
					new NhanVien(sMaNV)
				);
				dssp.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dssp;
	}
	public boolean themSanPham(SanPham sp) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into SanPham values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setString(3, sp.getChatLuong());
			stmt.setString(4, sp.getKieuDang());
			stmt.setBoolean(5, sp.getGioiTinh() == "Nam" ? true : false);
			stmt.setString(6, sp.getDonVi());
			stmt.setString(7, sp.getMoTa());
			stmt.setString(8, sp.getTenTH() );
			stmt.setString(9, sp.getNuocTH());
			stmt.setString(10, sp.getTenNSX());
			stmt.setString(11, sp.getsDTNSX());
			stmt.setString(12, sp.getEmailNSX());
			stmt.setString(13, sp.getPathImage());
			stmt.setString(14, sp.getLoaiSP().getMaLoai());
			stmt.setString(15, sp.getNhanVien().getMaNhanVien());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteSanPham(String maSp) {
		int n = 0;
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from SanPham where maSanPham = ?");
			stmt.setString(1, maSp);
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return n > 0;
	}
	
	// public boolean updateSanPham(
	// 	String ma,
	// 	String ten,
	// 	String loai,
	// 	String tenTH,
	// 	String nuocTH,
	// 	String tenNSX,
	// 	String sdtNSX,
	// 	String emailNSX,
	// 	String kieu,
	// 	String chatLieu,
	// 	String donVi,
	// 	String gioiTinh,
		
	// 	String kichCo,
	// 	String mauSac,
	// 	String soLuong,
	// 	String giaBan
	// ) {
	// 	Connect_DB.getInstance();
	// 	Connection con = Connect_DB.getConnection();
	// 	PreparedStatement ps = null;
	// 	int n = 0;
	// 	String tenQ, loaiQ, tenTHQ, nuocTHQ, tenNSXQ, sdtNSXQ, emailNSXQ, kieuQ, chatLieuQ, donViQ, gioiTinhQ, kichCoQ, mauSacQ, soLuongQ, giaBanQ;

	// 	try {		
	// 		if ( ten == null || ten.length() == 0 ) { tenQ = ""; } 
	// 		else { tenQ = "tenSanPham = '" + ten + "', "; }
			
	// 		if ( loai == null || loai.length() == 0 ) { loaiQ = ""; } 
	// 		else { loaiQ = "tenLoaiSanPham = '" + loai + "', "; }
			
	// 		if ( tenTH == null || tenTH.length() == 0 ) { tenTHQ = ""; } 
	// 		else { tenTHQ = "thuongHieu = '" + tenTH + "', "; }
			
	// 		if ( nuocTH == null || nuocTH.length() == 0 ) { nuocTHQ = ""; } 
	// 		else { nuocTHQ = "nuocThuongHieu = '" + nuocTH + "', "; }
			
	// 		if ( tenNSX == null || tenNSX.length() == 0 ) { tenNSXQ = ""; } 
	// 		else { tenNSXQ = "tenNhaSanXuat = '" + tenNSX + "', "; }
			
	// 		if ( sdtNSX == null || sdtNSX.length() == 0 ) { sdtNSXQ = ""; } 
	// 		else { sdtNSXQ = "soDienThoaiNhaSanXuat = '" + sdtNSX + "', "; }
			
	// 		if ( emailNSX == null || emailNSX.length() == 0 ) { emailNSXQ = ""; } 
	// 		else { emailNSXQ = "emailNhaSanXuat = '" + emailNSX + "', "; }
			
	// 		if ( kieu == null || kieu.length() == 0 ) { kieuQ = ""; } 
	// 		else { kieuQ = "kieuDang = '" + kieu + "', "; }
			
	// 		if ( chatLieu == null || chatLieu.length() == 0 ) { chatLieuQ = ""; } 
	// 		else { chatLieuQ = "chatLieu = '" + chatLieu + "', "; }
			
	// 		if ( donVi == null || donVi.length() == 0 ) { donViQ = ""; } 
	// 		else { donViQ = "donViSanPham = '" + donVi + "', "; }
			
	// 		gioiTinhQ = "phuHopGioiTinh = " + (gioiTinh == "Nam" ? 0 : 1) + ", ";
			
	// 		if ( kichCo == null || kichCo.length() == 0 ) { kichCoQ = ""; } 
	// 		else { kichCoQ = "kichCo = '" + kichCo + "', "; }
			
	// 		if ( mauSac == null || mauSac.length() == 0 ) { mauSacQ = ""; } 
	// 		else { mauSacQ = "mauSac = '" + mauSac + "', "; }
			
	// 		if ( soLuong == null || soLuong.length() == 0 ) { soLuongQ = ""; } 
	// 		else { soLuongQ = "soLuong = " + soLuong + "', "; }
			
	// 		if ( giaBan == null || giaBan.length() == 0 ) { giaBanQ = ""; } 
	// 		else { giaBanQ = "giaBan = " + giaBan + "', "; }
			
	// 		String sql = "UPDATE SanPham SET "
	// 				+ tenQ 
	// 				+ tenTHQ 
	// 				+ nuocTHQ 
	// 				+ tenNSXQ 
	// 				+ sdtNSXQ 
	// 				+ emailNSXQ 
	// 				+ kieuQ 
	// 				+ chatLieuQ 
	// 				+ donViQ
	// 				+ gioiTinhQ;

	// 		sql = sql.substring(0, sql.length() - 2);
	// 		sql += " WHERE maSanPham = '" + ma + "'";
	// 		ps = con.prepareStatement(sql);
	// 		n = ps.executeUpdate();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// 	return n > 0;
	// }

	public boolean updateSanPham_2(SanPham sp) {
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement ps = null;
		int n = 0;
		try {
			ps = con.prepareStatement("UPDATE SanPham SET " +
											"tenSanPham = ?, " +
											"chatLieu = ?, " +
											"kieuDang = ?, " +
											"phuHopGioiTinh = ?,  " +
											"donViSanPham = ?,  " +
											"moTaSanPham = ?,  " +
											"thuongHieu = ?,  " +
											"nuocThuongHieu = ?,  " +
											"tenNhaSanXuat = ?,  " +
											"soDienThoaiNhaSanXuat = ?,  " +
											"emailNhaSanXuat = ?,  " +
											"duongDan = ? " +
											"WHERE maSanPham = ?");

			ps.setString(1, sp.getTenSP());
			ps.setString(2, sp.getChatLuong());
			ps.setString(3, sp.getKieuDang());
			ps.setString(4, sp.getGioiTinh());
			ps.setString(5, sp.getDonVi());
			ps.setString(6, sp.getMoTa());
			ps.setString(7, sp.getTenTH());
			ps.setString(8, sp.getNuocTH());
			ps.setString(9, sp.getTenNSX());
			ps.setString(10, sp.getsDTNSX());
			ps.setString(11, sp.getEmailNSX());
			ps.setString(12, sp.getPathImage());
			ps.setString(13, sp.getMaSP());

			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public SanPham timSanPhamTheoMa(String ma){
		PreparedStatement stmt = null;
		SanPham spTM = null;
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			stmt = con.prepareStatement("select * from SanPham where maSanPham = ?");
			
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				String maSP = rs.getString(1);
				String ten = rs.getString(2);
				String chatLieu = rs.getString(3);
				String kieuDang = rs.getString(4);
				
				// Lỗi
				String phuHopGioiTinh = rs.getString(5);
				
				String donVi = rs.getString(6);
				String moTa = rs.getString(7);
				String tenTH = rs.getString(8);
				String nuocTH = rs.getString(9);
				String tenNSX = rs.getString(10);
				String sDTNSX = rs.getString(11);
				String emailNSX = rs.getString(12);
				String duongDan = rs.getString(13);
				String maLoai = rs.getString(14);
				String maNV = rs.getString(15);
				spTM = new SanPham(
					maSP, 
					ten, 
					chatLieu, 
					kieuDang, 
					phuHopGioiTinh, 
					donVi, 
					moTa, 
					tenTH, 
					nuocTH, 
					tenNSX, 
					sDTNSX, 
					emailNSX, 
					duongDan, 
					new LoaiSanPham(maLoai), 
					new NhanVien(maNV)
				);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return spTM;
	}
	public ArrayList<SanPham> getTopSanPham() {
		PreparedStatement stmt = null;
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			stmt = con.prepareStatement("SELECT TOP 10 sp.maSanPham, cthd.tenSanPham, COUNT(cthd.tenSanPham) as soLuongBanRa, SUM(hd.tongPhaiTra) as lmao FROM ChiTietHoaDon cthd INNER JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon INNER JOIN SanPham sp ON sp.maSanPham = cthd.maSanPham GROUP BY sp.maSanPham, cthd.tenSanPham, cthd.tenSanPham ORDER BY lmao DESC");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				String maSP = rs.getString(1);
				String ten = rs.getString(2);

				SanPham spTM = new SanPham(
					maSP, 
					ten
				);
				dssp.add(spTM);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dssp;
	}
}
