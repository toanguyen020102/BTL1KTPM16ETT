package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI_TroGiup extends JFrame implements MouseListener{
	private JLabel lblSanPham, lblKhachHang, lblNhanVien, lblHoaDon, lblThongKe;

	private JLabel lblQLSanPham, lblTimSanPham ,  
	lblQLKhachHang, 
	lblQLNhanVien, 
	lblTaoHoaDon, lblTimHoaDon, 
	lblThongKeDoanhThu, lblThongKeTongDoanhThu, lblThongKeSanPham;
	
	private JPanel pnlRight, pnlLeft;
	public GUI_TroGiup() {
		// TODO Auto-generated method stub
		setSize(700, 300);
		setResizable(false);

		createGUI();
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		int width = this.getWidth(), height = this.getHeight();

		JPanel pnl;
		add(pnl = new JPanel());
		pnl.setBackground(new Color(147, 190, 221));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));

		//	Panel Left
		pnl.add(pnlLeft = new JPanel());
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setPreferredSize(new Dimension(width * 1 / 4, height));
		pnlLeft.setBackground(new Color(147, 190, 221));
		pnlLeft.add(Box.createVerticalStrut(30));

		Box bSanPham = Box.createHorizontalBox();
		bSanPham.add(lblSanPham = new JLabel("Sản Phẩm"));
		lblSanPham.setFont(new Font("Arial", Font.BOLD, 18));
		pnlLeft.add(bSanPham);
		pnlLeft.add(Box.createVerticalStrut(10));

		Box bHoaDon = Box.createHorizontalBox();
		bHoaDon.add(lblHoaDon = new JLabel("Hóa Đơn"));
		lblHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
		pnlLeft.add(bHoaDon);
		pnlLeft.add(Box.createVerticalStrut(10));

		Box bNhanVien = Box.createHorizontalBox();
		bNhanVien.add(lblNhanVien = new JLabel("Nhân Viên"));
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
		pnlLeft.add(bNhanVien);
		pnlLeft.add(Box.createVerticalStrut(10));

		Box bKhachHang = Box.createHorizontalBox();
		bKhachHang.add(lblKhachHang = new JLabel("Khách Hàng"));
		lblKhachHang.setFont(new Font("Arial", Font.BOLD, 18));
		pnlLeft.add(bKhachHang);
		pnlLeft.add(Box.createVerticalStrut(10));

		Box bThongKe = Box.createHorizontalBox();
		bThongKe.add(lblThongKe = new JLabel("Thống Kê"));
		lblThongKe.setFont(new Font("Arial", Font.BOLD, 18));
		pnlLeft.add(bThongKe);
		pnlLeft.add(Box.createVerticalStrut(10));

		pnlLeft.add(Box.createVerticalStrut(30));

		//	Panel Right
		JScrollPane scrPnlRight = new JScrollPane(pnlRight = new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrPnlRight.setPreferredSize(new Dimension(width * 3 / 4, height));
		scrPnlRight.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		pnl.add(scrPnlRight);

		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
		pnlRight.setBackground(new Color(147, 190, 221));
		pnlRight.add(Box.createVerticalStrut(30));

		//		Product
		pnlRight.add(lblQLSanPham = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Quản Lí Sản Phẩm"));
		lblQLSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblQLSanPham.setVisible(false);

		pnlRight.add(lblTimSanPham = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Tìm Sản Phẩm"));
		lblTimSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblTimSanPham.setVisible(false);

		//		Customer
		pnlRight.add(lblQLKhachHang = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Quản Lí Khách Hàng"));
		lblQLKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
		lblQLKhachHang.setVisible(false);

		//		Staff
		pnlRight.add(lblQLNhanVien = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Quản Lí Nhân Viên"));
		lblQLNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		lblQLNhanVien.setVisible(false);

		//		Order
		pnlRight.add(lblTaoHoaDon = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Tạo Hóa Đơn"));
		lblTaoHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblTaoHoaDon.setVisible(false);

		pnlRight.add(lblTimHoaDon = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Tìm Hóa Đơn"));
		lblTimHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblTimHoaDon.setVisible(false);

		//		Statistic
		pnlRight.add(lblThongKeDoanhThu = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Thống Kê Doanh Thu"));
		lblThongKeDoanhThu.setFont(new Font("Arial", Font.BOLD, 16));
		lblThongKeDoanhThu.setVisible(false);

		pnlRight.add(lblThongKeTongDoanhThu = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Thống Kê Tổng Doanh Thu"));
		lblThongKeTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 16));
		lblThongKeTongDoanhThu.setVisible(false);

		pnlRight.add(lblThongKeSanPham = new JLabel("Hướng Dẫn Sử Dụng Giao Diện Thống Kê Sản Phẩm"));
		lblThongKeSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblThongKeSanPham.setVisible(false);

		//	Event
		lblSanPham.addMouseListener(this); 
		lblKhachHang.addMouseListener(this);  
		lblNhanVien.addMouseListener(this);  
		lblHoaDon.addMouseListener(this);  
		lblThongKe.addMouseListener(this); 
	}

	public static void main(String[] args) {
		new GUI_TroGiup().setVisible(true);
	}

	private void hideAll() {
		// TODO Auto-generated method stub
		lblQLSanPham.setVisible(false);
		lblTimSanPham.setVisible(false);
		
		lblQLKhachHang.setVisible(false);
		
		lblQLNhanVien.setVisible(false);
		
		lblTaoHoaDon.setVisible(false);	
		lblTimHoaDon.setVisible(false);
		
		lblThongKeSanPham.setVisible(false);
		lblThongKeDoanhThu.setVisible(false);
		lblThongKeTongDoanhThu.setVisible(false);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(lblSanPham)) {
			hideAll();
			
			lblQLSanPham.setVisible(true);
			lblTimSanPham.setVisible(true);
		} else if(o.equals(lblHoaDon)) {
			hideAll();
			
			lblTaoHoaDon.setVisible(true);	
			lblTimHoaDon.setVisible(true);
		} else if(o.equals(lblKhachHang)) {
			hideAll();
			
			lblQLKhachHang.setVisible(true);
		} else if(o.equals(lblNhanVien)) {
			hideAll();
			
			lblQLNhanVien.setVisible(true);
		} else if(o.equals(lblThongKe)) {
			hideAll();
			
			lblThongKeSanPham.setVisible(true);
			lblThongKeDoanhThu.setVisible(true);
			lblThongKeTongDoanhThu.setVisible(true);
		} else if(o.equals(lblQLSanPham)) {
			
		} else if(o.equals(lblTimSanPham)) {
			
		} else if(o.equals(lblTaoHoaDon)) {
			
		} else if(o.equals(lblTimHoaDon)) {
			
		} else if(o.equals(lblQLNhanVien)) {
			
		} else if(o.equals(lblQLKhachHang)) {
			
		} else if(o.equals(lblThongKeDoanhThu)) {
			
		} else if(o.equals(lblThongKeSanPham)) {
			
		} else if(o.equals(lblThongKeDoanhThu)) {
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
