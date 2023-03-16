package entity;

public class SanPham {
	private String maSP, tenSP;
	private LoaiSanPham loaiSP;
	private String tenTH, nuocTH;
	private String tenNSX, sDTNSX, emailNSX;
	private String kieuDang, chatLuong, gioiTinh, donVi, moTa, pathImage;
	private NhanVien nhanVien;
	
	
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public SanPham(String maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}
	
	public SanPham(
		String maSP, 
		String tenSP,
		String chatLuong, 
		String kieuDang, 
		String gioiTinh, 
		String donVi,	
		String moTa, 
		String tenTH, 
		String nuocTH, 
		String tenNSX,
		String sDTNSX, 
		String emailNSX, 
		String pathImage, 
		LoaiSanPham loaiSP, 
		NhanVien nhanVien
	) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.tenTH = tenTH;
		this.nuocTH = nuocTH;
		this.tenNSX = tenNSX;
		this.sDTNSX = sDTNSX;
		this.emailNSX = emailNSX;
		this.kieuDang = kieuDang;
		this.chatLuong = chatLuong;
		this.gioiTinh = gioiTinh;
		this.donVi = donVi;
		this.moTa = moTa;
		this.pathImage = pathImage;
		this.nhanVien = nhanVien;
	}

	public SanPham() {
		// TODO Auto-generated constructor stub
	}

	public String getMaSP() {
		return maSP;
	}
	
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	
	public String getTenSP() {
		return tenSP;
	}
	
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	
	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}
	
	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}
	
	public String getTenTH() {
		return tenTH;
	}
	
	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}
	
	public String getNuocTH() {
		return nuocTH;
	}
	
	public void setNuocTH(String nuocTH) {
		this.nuocTH = nuocTH;
	}
	
	public String getTenNSX() {
		return tenNSX;
	}
	
	public void setTenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}
	
	public String getsDTNSX() {
		return sDTNSX;
	}
	
	public void setsDTNSX(String sDTNSX) {
		this.sDTNSX = sDTNSX;
	}
	
	public String getEmailNSX() {
		return emailNSX;
	}
	
	public void setEmailNSX(String emailNSX) {
		this.emailNSX = emailNSX;
	}
	
	public String getKieuDang() {
		return kieuDang;
	}
	
	public void setKieuDang(String kieuDang) {
		this.kieuDang = kieuDang;
	}
	
	public String getChatLuong() {
		return chatLuong;
	}
	
	public void setChatLuong(String chatLuong) {
		this.chatLuong = chatLuong;
	}
	
	public String getGioiTinh() {
		return gioiTinh;
	}
	
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public String getDonVi() {
		return donVi;
	}
	
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;

	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "SanPham (maSP" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", tenTH=" + tenTH + ", nuocTH="
				+ nuocTH + ", tenNSX=" + tenNSX + ", sDTNSX=" + sDTNSX + ", emailNSX=" + emailNSX + ", kieuDang="
				+ kieuDang + ", chatLuong=" + chatLuong + ", gioiTinh=" + gioiTinh + ", donVi=" + donVi + ", moTa="
				+ moTa + ", pathImage=" + pathImage + ", nhanVien=" + nhanVien + ")";
	}
	
}
