package entity;

public class ThongKeSoLuongSanPham {
	protected String Sp;
	protected String TenSP;
	protected String kichCo;
	protected String MauSac;
	protected int soLuongSP;
	protected int soLuongHD;
	protected double doanhthu;
	public String getSp() {
		return Sp;
	}
	public void setSp(String sp) {
		Sp = sp;
	}
	public String getTenSP() {
		return TenSP;
	}
	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}
	public String getKichCo() {
		return kichCo;
	}
	public void setKichCo(String kichCo) {
		this.kichCo = kichCo;
	}
	public String getMauSac() {
		return MauSac;
	}
	public void setMauSac(String mauSac) {
		MauSac = mauSac;
	}
	public int getSoLuongSP() {
		return soLuongSP;
	}
	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}
	public int getSoLuongHD() {
		return soLuongHD;
	}
	public void setSoLuongHD(int soLuongHD) {
		this.soLuongHD = soLuongHD;
	}
	public double getDoanhthu() {
		return doanhthu;
	}
	public void setDoanhthu(double doanhthu) {
		this.doanhthu = doanhthu;
	}
	public ThongKeSoLuongSanPham(String sp, String tenSP, String kichCo, String mauSac, int soLuongSP, int soLuongHD,
		double doanhthu) {
		super();
		Sp = sp;
		TenSP = tenSP;
		this.kichCo = kichCo;
		this.MauSac = mauSac;
		this.soLuongSP = soLuongSP;
		this.soLuongHD = soLuongHD;
		this.doanhthu = doanhthu;
	}
	public ThongKeSoLuongSanPham(String tenSP, int soLuongSP, double doanhthu) {
		super();
		TenSP = tenSP;
		this.soLuongSP = soLuongSP;
		this.doanhthu = doanhthu;
	}
}
