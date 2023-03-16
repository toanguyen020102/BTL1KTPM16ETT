package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;

import entity.NhanVien;
import entity.TaiKhoan;

public class GUI_TrangChu extends JFrame implements ActionListener{
	
	private JLabel lblRealTime;
	
	private JMenuBar menuBar;
	
	private JMenu menuSanPham, 
								menuHoaDon, 
								menuNhanVien, 
								menuKhachHang, 
								menuThongKe, 
								menuTroGiup, 
								menuTaiKhoan;
	
	private JMenuItem itemQuanLiSanPham, 
										itemTimSanPham, 
										itemTaoHoaDon,
										itemQuanLiHoaDon, 
										itemQuanLiNhanVien, 
										itemQuanLiKhachHang, 
										itemThongKeDoanhThuNV, 
										itemThongKeDoanhThuQL, 
										itemThongKeSanPhamQL, 
										itemThongKeDoanhThuNVDate,
										itemThongTinCaNhan, 
										itemDoiMatKhau, 
										itemDangXuat, 
										itemThoat;
	
	NhanVien nvInfo = null;
	TaiKhoan tkInfo = null;
	
	public GUI_TrangChu(NhanVien nv, TaiKhoan tk) {
		
		setExtendedState(this.MAXIMIZED_BOTH);
		setUndecorated(true);
		nvInfo = nv;
		tkInfo = tk;
		
		Panel panel = new Panel();
		add(panel);
		panel.setBackground(new Color(147, 190, 221));
		
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		setJMenuBar(menuBar);
		
		menuBar.add(menuSanPham = new JMenu("Sản Phẩm"));
		menuBar.add(menuHoaDon = new JMenu("Hóa Đơn"));
		menuBar.add(menuNhanVien = new JMenu("Nhân Viên"));
		menuBar.add(menuKhachHang = new JMenu("Khách Hàng"));
		menuBar.add(menuThongKe = new JMenu("Thống Kê"));
		menuBar.add(menuTroGiup = new JMenu("Trợ Giúp"));
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(lblRealTime = new JLabel());
		lblRealTime.setFont(new Font("Arial", Font.BOLD, 18));
		realTime();
		menuBar.add(Box.createHorizontalStrut(50));
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menuTaiKhoan = new JMenu("" + nv.getTenNhanVien()));
		menuBar.add(Box.createHorizontalStrut(50));
		
		ImageIcon iconSP = new ImageIcon(
			scaleImage(
				new File("./images/iCon/clothesIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuSanPham.add(itemQuanLiSanPham = new JMenuItem("Quản Lí Sản Phẩm", iconSP));

		ImageIcon iconTimSP = new ImageIcon(
			scaleImage(
				new File("./images/iCon/clothesIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuSanPham.add(itemTimSanPham = new JMenuItem("Tim Sản Phẩm", iconTimSP));
		
		ImageIcon iconHD = new ImageIcon(
			scaleImage(
				new File("./images/iCon/orderIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuHoaDon.add(itemTaoHoaDon = new JMenuItem("Tạo Hóa Đơn Demo", iconHD));

		ImageIcon iconQuanLiHD = new ImageIcon(
			scaleImage(
				new File("./images/iCon/orderIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuHoaDon.add(itemQuanLiHoaDon = new JMenuItem("Quan Li Hoa Don", iconQuanLiHD));
		
		ImageIcon iconNV = new ImageIcon(
			scaleImage(
				new File("./images/iCon/staffIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuNhanVien.add(itemQuanLiNhanVien = new JMenuItem("Quản Lí Nhân Viên", iconNV));
		
		ImageIcon iconKH = new ImageIcon(
			scaleImage(
				new File("./images/iCon/customersIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuKhachHang.add(itemQuanLiKhachHang = new JMenuItem("Quản Lí Khách Hàng", iconKH));
		
		ImageIcon iconTKDTNV = new ImageIcon(
			scaleImage(
				new File("./images/iCon/tKDTNVIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuThongKe.add(itemThongKeDoanhThuNV = new JMenuItem("Thống Kê Doanh Thu", iconTKDTNV));
		
		ImageIcon iconTKTDN = new ImageIcon(
			scaleImage(
				new File("./images/iCon/tKTDTIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuThongKe.add(itemThongKeDoanhThuQL = new JMenuItem("Thống Kê Tổng Doanh Thu", iconTKTDN));
		
		ImageIcon iconTKSP = new ImageIcon(
			scaleImage(
				new File("./images/iCon/tKSPIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuThongKe.add(itemThongKeSanPhamQL = new JMenuItem("Thống Kê Sản Phẩm", iconTKSP));

		ImageIcon iconTKDTNVDate = new ImageIcon(
			scaleImage(
				new File("./images/iCon/tKSPIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuThongKe.add(itemThongKeDoanhThuNVDate = new JMenuItem("Thống Kê Doanh Thu Nhân Viên theo ngày", iconTKDTNVDate));
		
		ImageIcon iconTTCN = new ImageIcon(
			scaleImage(
				new File("./images/iCon/userInfoIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuTaiKhoan.add(itemThongTinCaNhan = new JMenuItem("Thông Tin Cá Nhân", iconTTCN));
		
		ImageIcon iconDoiMatKhau = new ImageIcon(
			scaleImage(
				new File("./images/iCon/changePassIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuTaiKhoan.add(itemDoiMatKhau = new JMenuItem("Đổi Mật Khẩu", iconDoiMatKhau));
		
		ImageIcon iconSignOut = new ImageIcon(
			scaleImage(
				new File("./images/iCon/signOutIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuTaiKhoan.add(itemDangXuat = new JMenuItem("Đăng Xuất", iconSignOut));
		
		ImageIcon iconExit = new ImageIcon(
			scaleImage(
				new File("./images/iCon/exitIcon.png").getAbsolutePath(), 20, 20
			)
		);
		menuTaiKhoan.add(itemThoat = new JMenuItem("Thoát", iconExit));
		
		// Check 
		if (nv.getChucVu().equalsIgnoreCase("Quan Li")) {
			itemThongKeDoanhThuNV.setVisible(false);
		} else {
			menuNhanVien.setVisible(false);
			menuKhachHang.setVisible(false);
			menuNhanVien.setVisible(false);
			itemThongKeDoanhThuQL.setVisible(false);
			itemThongKeSanPhamQL.setVisible(false);
			menuSanPham.setVisible(false);
		}
		
		// Event
		itemQuanLiSanPham.addActionListener(this);
		itemTimSanPham.addActionListener(this);
		itemTaoHoaDon.addActionListener(this);
		itemQuanLiHoaDon.addActionListener(this);
		itemQuanLiNhanVien.addActionListener(this);
		itemQuanLiKhachHang.addActionListener(this);
		itemThongKeDoanhThuNV.addActionListener(this);
		itemThongKeDoanhThuQL.addActionListener(this);
		itemThongKeSanPhamQL.addActionListener(this);
		itemThongKeDoanhThuNVDate.addActionListener(this);
		itemThongTinCaNhan.addActionListener(this);
		itemDoiMatKhau.addActionListener(this);
		itemDangXuat.addActionListener(this);
		itemThoat.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if (o.equals(itemQuanLiSanPham)) switchPanel(new GUI_QuanLiSanPhamQL(this, nvInfo));
		else if (o.equals(itemTimSanPham)) switchPanel(new GUI_TimSanPham_3(this));
		else if (o.equals(itemTaoHoaDon)) switchPanel(new GUI_TaoHoaDon_2(this, nvInfo));
		else if (o.equals(itemQuanLiHoaDon)) switchPanel(new GUI_QuanLiHoaDon(nvInfo));
		else if (o.equals(itemQuanLiNhanVien)) switchPanel(new GUI_QuanLiNhanVienQL(nvInfo));
		else if (o.equals(itemQuanLiKhachHang)) switchPanel(new GUI_QuanLiKhachHangQL());
		else if (o.equals(itemThongKeDoanhThuNV)) switchPanel(new GUI_ThongKeDoanhThuNV(this));
		else if (o.equals(itemThongKeSanPhamQL)) switchPanel(new ThongKeSanPham());
		else if (o.equals(itemThongKeDoanhThuQL)) switchPanel(new GUI_ThongKeDoanhThuQL());
		else if (o.equals(itemThongKeDoanhThuNVDate)) switchPanel(new ThongKeDoanhThuNhanVienTheoDate());
		else if (o.equals(itemThongTinCaNhan)) switchPanel(new GUI_ThongTinCaNhan(nvInfo));
		else if (o.equals(itemDoiMatKhau)) new GUI_DoiMatKhau(nvInfo, tkInfo).setVisible(true);
		else if (o.equals(itemDangXuat)) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn Muốn Đăng Xuất", "Đăng Xuất", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				this.dispose();
				new GUI_DangNhap().setVisible(true);
			}
		} else if (o.equals(itemThoat)) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn Muốn Thoát Ứng Dụng", "Thoát Ứng Dụng", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) System.exit(0);
		}
	}
	
	private Image scaleImage(String path, int w, int h) {
		ImageIcon img = new ImageIcon(path);
		Image image = img.getImage();
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return scaled;
	}
	
	private void realTime() {
		Timer t = new Timer(500, new ActionListener() {
            
			public void actionPerformed(ActionEvent e) {
				lblRealTime.setText(
					DateFormat.getDateTimeInstance().format(new Date())
				);
			}
		});
		t.setRepeats(true);
		t.setCoalesce(true);
		t.setInitialDelay(0);
		t.start();
	}
	
	private void switchPanel(JPanel panel) {
		setContentPane(panel);
		validate();
		invalidate();
	}
}
