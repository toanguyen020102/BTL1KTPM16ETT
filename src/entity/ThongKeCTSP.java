package entity;

public class ThongKeCTSP {

		protected String Sp;
		protected String size;
		protected String mausac;
		protected int soLuong;
		protected double doanhthu;
		public String getSp() {
			return Sp;
		}
		public void setSp(String sp) {
			Sp = sp;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getMausac() {
			return mausac;
		}
		public void setMausac(String mausac) {
			this.mausac = mausac;
		}
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		public double getDoanhthu() {
			return doanhthu;
		}
		public void setDoanhthu(double doanhthu) {
			this.doanhthu = doanhthu;
		}
		public ThongKeCTSP(String sp, String size, String mausac, int soLuong, double doanhthu) {
			super();
			this.Sp = sp;
			this.size = size;
			this.mausac = mausac;
			this.soLuong = soLuong;
			this.doanhthu = doanhthu;
		}
		@Override
		public String toString() {
			return "ThongKeCTSP [Sp=" + Sp + ", size=" + size + ", mausac=" + mausac + ", soLuong=" + soLuong
					+ ", doanhthu=" + doanhthu + "]";
		}
		
	
}
