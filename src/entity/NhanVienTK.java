package entity;

import java.sql.Date;

public class NhanVienTK {
	public Date ngayGiaoDich;
	public int soluonghoadon;
	public double sotien;
	public Date getNgayGiaoDich() {
		return ngayGiaoDich;
	}
	public void setNgayGiaoDich(Date ngayGiaoDich) {
		this.ngayGiaoDich = ngayGiaoDich;
	}
	public int getSoluonghoadon() {
		return soluonghoadon;
	}
	public void setSoluonghoadon(int soluonghoadon) {
		this.soluonghoadon = soluonghoadon;
	}
	public double getSotien() {
		return sotien;
	}
	public void setSotien(double sotien) {
		this.sotien = sotien;
	}
	public NhanVienTK(Date ngayGiaoDich, int soluonghoadon, double sotien) {
		super();
		this.ngayGiaoDich = ngayGiaoDich;
		this.soluonghoadon = soluonghoadon;
		this.sotien = sotien;
	}
	@Override
	public String toString() {
		return "NhanVienTK [ngayGiaoDich=" + ngayGiaoDich + ", soluonghoadon=" + soluonghoadon + ", sotien=" + sotien
				+ "]";
	}
	
	
}
