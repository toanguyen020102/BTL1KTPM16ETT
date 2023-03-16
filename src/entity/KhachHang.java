package entity;

import java.sql.Date;

public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private boolean gioiTinh;
	private String soDienThoai;
	private Date ngaySinh;
	private String email;
	private int diemTichLuy;
	
	public KhachHang(String maKhachHang, int diemTichLuy) {
		super();
		this.maKhachHang = maKhachHang;
		this.diemTichLuy = diemTichLuy;
	}
	public KhachHang(String maKhachHang, String tenKhachHang, boolean gioiTinh, String soDienThoai, Date ngaySinh,
			String email, int diemTichLuy) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.diemTichLuy = diemTichLuy;
	}
	public KhachHang() {
		// TODO Auto-generated constructor stub
	}
	
	public KhachHang(String maKhachHang) {
		// TODO Auto-generated constructor stub
		this.maKhachHang = maKhachHang;
	}
	
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", gioiTinh=" + gioiTinh
				+ ", soDienThoai=" + soDienThoai + ", ngaySinh=" + ngaySinh + ", email=" + email + "]";
	}
	

}
