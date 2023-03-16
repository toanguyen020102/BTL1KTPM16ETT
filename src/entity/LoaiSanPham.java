package entity;

public class LoaiSanPham {
	private String maLoai, tenLoai;
	
	public LoaiSanPham() {
		super();
	}

	public LoaiSanPham(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public LoaiSanPham(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "LoaiSanPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
}
