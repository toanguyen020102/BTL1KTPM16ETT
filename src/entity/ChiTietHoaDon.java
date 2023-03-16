package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private SanPham sanPham;
	private String kichCo;
	private String mauSac;
	private int soLuong;
	private double giaBan;
	
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, String kichCo, String mauSac, int soLuong, double giaBan) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.kichCo = kichCo;
		this.mauSac = mauSac;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}

	public ChiTietHoaDon(SanPham sanPham, int soLuong, double giaBan) {
		super();
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}

	public ChiTietHoaDon(SanPham sanPham, String kichCo, String mauSac, int soLuong, double giaBan) {
		super();
		this.sanPham = sanPham;
		this.kichCo = kichCo;
		this.mauSac = mauSac;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public String getKichCo() {
		return kichCo;
	}

	public void setKichCo(String kichCo) {
		this.kichCo = kichCo;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", sanPham=" + sanPham + ", kichCo=" + kichCo + ", mauSac=" + mauSac
				+ ", soLuong=" + soLuong + ", giaBan=" + giaBan + "]";
	}

	
}
