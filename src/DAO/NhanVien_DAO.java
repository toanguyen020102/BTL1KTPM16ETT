package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect_DB.Connect_DB;
import entity.NhanVien;

public class NhanVien_DAO {
	
	ArrayList<NhanVien> dsnv ;
	NhanVien nv ;
	
	public NhanVien_DAO() {
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}

	public ArrayList<NhanVien> getAllNhanVien(){
		try {
			Connect_DB.getInstance();
			Connection con=Connect_DB.getConnection();
			String sql="Select* from NhanVien";
			Statement statement= con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				String soDienThoai = rs.getString(3);
				Boolean gioiTinh=rs.getBoolean(4);
				Date ngaySinh =  rs.getDate(5);
				String email = rs.getString(6);
				Date ngayVaoLam = rs.getDate(7);
				String chungMinhNhanDan = rs.getString(8);
				String chucVu =  rs.getString(9);
				String thanhPho = rs.getString(10);
				String quan = rs.getString(11);
				String phuong = rs.getString(12);
				String diaChi= rs.getString(13);
				NhanVien nv = new NhanVien(
					maNhanVien, 
					tenNhanVien, 
					soDienThoai, 
					gioiTinh, 
					ngaySinh, 
					email, 
					ngayVaoLam, 
					chungMinhNhanDan, 
					chucVu,
					thanhPho, 
					quan, 
					phuong,
					diaChi
				);
				dsnv.add(nv);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public ArrayList<NhanVien> getAllNhanVienCoTaiKhoan(){
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "Select * from NhanVien nv join TaiKhoan tk on nv.maNhanVien = tk.maNhanVien";
			Statement statement= con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);
				String soDienThoai = rs.getString(3);
				Boolean gioiTinh=rs.getBoolean(4);
				Date ngaySinh =  rs.getDate(5);
				String email = rs.getString(6);
				Date ngayVaoLam = rs.getDate(7);
				String chungMinhNhanDan = rs.getString(8);
				String chucVu =  rs.getString(9);
				String thanhPho = rs.getString(10);
				String quan = rs.getString(11);
				String phuong = rs.getString(12);
				String diaChi= rs.getString(13);
				NhanVien nv = new NhanVien(
					maNhanVien, 
					tenNhanVien, 
					soDienThoai, 
					gioiTinh, 
					ngaySinh, 
					email, 
					ngayVaoLam, 
					chungMinhNhanDan, 
					chucVu,
					thanhPho, 
					quan, 
					phuong,
					diaChi
				);
				dsnv.add(nv);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public boolean checkNhanVienTheoMa(String maNhanVien) {
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
			String sql = "Select * from NhanVien where maNhanVien = '" + maNhanVien + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if ( rs.isBeforeFirst() ) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//----add nhân viên 
	public boolean createNhanVien (NhanVien nv)
	{	
		Connection con = Connect_DB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into "+" NhanVien values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1,nv.getMaNhanVien());
			stmt.setString(2, nv.getTenNhanVien());
			stmt.setString(3, nv.getsDT());
			stmt.setBoolean(4, nv.isGioiTinh()? true : false);
			stmt.setDate(5, nv.getNgaySinh());
			stmt.setString(6, nv.getEmail());
			stmt.setDate(7,nv.getNgayVaoLam());
			stmt.setString(8, nv.getChungMinhNhanDan());
			stmt.setString(9, nv.getChucVu());
			stmt.setString(10, nv.getThanhPho());
			stmt.setString(11, nv.getQuan());
			stmt.setString(12,nv.getPhuong());
			stmt.setString(13,nv.getDiaChi());

			n = stmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return n > 0 ;
	}

	public boolean deleteNhanVien(String maNhanVien) {
		Connection con = Connect_DB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt=con.prepareStatement("delete from NhanVien where maNhanVien =?");
			stmt.setString(1, maNhanVien);
			n=stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;

	}

	public NhanVien timNhanVienTheoMa(NhanVien nv) {
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		NhanVien nv1 = null;
		try {
			
			stmt = con.prepareStatement("select * from NhanVien  where maNhanVien =?");

			stmt.setString(1, nv.getMaNhanVien());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String soDienThoai = rs.getString(3);
				Boolean gt = rs.getBoolean(4);
				Date ngaySinh = rs.getDate(5);
				String Email = rs.getString(6);
				Date ngayVaoLam = rs.getDate(7);
				String cmnd = rs.getString(8);
				String chucVu = rs.getString(9);
				String thanhPho = rs.getString(10);
				String quan = rs.getString(11);
				String phuong = rs.getString(12);
				String diaChi = rs.getString(13); 
				
				nv1 = new NhanVien(
					maNV, 
					tenNV, 
					soDienThoai, 
					gt, 
					ngaySinh, 
					Email, 
					ngayVaoLam, 
					cmnd, 
					chucVu,
					thanhPho, 
					quan, 
					phuong,
					diaChi
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nv1;
	}

	//---update----//
	public boolean upDateNhanVien(NhanVien nv)
	{
		Connect_DB.getInstance();
		Connection con = Connect_DB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set tenNhanVien=?,sodienthoai=?,gioiTinh=?,ngaySinh=?,email=?,ngayVaoLam=?,chungMinhNhanDan=?,chucVu=?,thanhPho=?,quan=?,phuong=?,diaChi=? where maNhanVien=? ");

			stmt.setString(1, nv.getTenNhanVien());
			stmt.setString(2, nv.getsDT());
			stmt.setBoolean(3, nv.isGioiTinh()? true : false);
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setString(5, nv.getEmail());
			stmt.setDate(6,nv.getNgayVaoLam());
			stmt.setString(7, nv.getChungMinhNhanDan());
			stmt.setString(8, nv.getChucVu());
			stmt.setString(9, nv.getThanhPho());
			stmt.setString(10, nv.getQuan());
			stmt.setString(11,nv.getPhuong());
			stmt.setString(12,nv.getDiaChi());
			stmt.setString(13,nv.getMaNhanVien());

			n = stmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return n > 0 ;  
	}

	//find
	public ArrayList<NhanVien> findNhanVienByName(String name) {
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList<NhanVien>list=new ArrayList<NhanVien>(); 
		try {
			String sql = "select * from NhanVien  where tenNhanVien like N'%"+name+"%'";
			Connection con = Connect_DB.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String sdt = rs.getString(3);
				Boolean gt = rs.getBoolean(4);
				Date ngaySinh = rs.getDate(5);
				String Email = rs.getString(6);
				Date ngayVaoLam = rs.getDate(7);
				String cmnd = rs.getString(8);
				String chucVu = rs.getString(9);
				String thanhPho = rs.getString(10);
				String quan = rs.getString(11);
				String phuong = rs.getString(12);
				String diaChi = rs.getString(13);

				NhanVien nv = new NhanVien(
					maNV, 
					tenNV, 
					sdt, 
					gt, 
					ngaySinh, 
					Email, 
					ngayVaoLam, 
					cmnd, 
					chucVu,
					thanhPho, 
					quan, 
					phuong,
					diaChi
				);
				list.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
}
