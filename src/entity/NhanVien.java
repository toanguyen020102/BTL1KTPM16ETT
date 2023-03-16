package entity;

import java.sql.Date;

public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private String sDT;
	private boolean gioiTinh;
	private Date ngaySinh;
	private String email;
	private Date ngayVaoLam;
	private String chungMinhNhanDan;
	private String chucVu;
	private String thanhPho;
	private String quan;
	private String phuong;
	private String diaChi;
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	public NhanVien(
		String maNhanVien, 
		String tenNhanVien, 
		String sDT, 
		boolean gioiTinh, 
		Date ngaySinh, 
		String email,
		Date ngayVaoLam, 
		String chungMinhNhanDan, 
		String chucVu, 
		String thanhPho, 
		String quan, 
		String phuong,
		String diaChi
	) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.sDT = sDT;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.ngayVaoLam = ngayVaoLam;
		this.chungMinhNhanDan = chungMinhNhanDan;
		this.chucVu = chucVu;
		this.thanhPho = thanhPho;
		this.quan = quan;
		this.phuong = phuong;
		this.diaChi = diaChi;
	}
	public NhanVien() {
		super();
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getsDT() {
		return sDT;
	}
	public void setsDT(String sDT) {
		this.sDT = sDT;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
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
	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public String getChungMinhNhanDan() {
		return chungMinhNhanDan;
	}
	public void setChungMinhNhanDan(String chungMinhNhanDan) {
		this.chungMinhNhanDan = chungMinhNhanDan;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getThanhPho() {
		return thanhPho;
	}
	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}
	public String getQuan() {
		return quan;
	}
	public void setQuan(String quan) {
		this.quan = quan;
	}
	public String getPhuong() {
		return phuong;
	}
	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", sDT=" + sDT + ", gioiTinh="
				+ gioiTinh + ", ngaySinh=" + ngaySinh + ", email=" + email + ", ngayVaoLam=" + ngayVaoLam
				+ ", chungMinhNhanDan=" + chungMinhNhanDan + ", chucVu=" + chucVu + ", thanhPho=" + thanhPho + ", quan="
				+ quan + ", phuong=" + phuong + ", diaChi=" + diaChi + "]";
	}
}
