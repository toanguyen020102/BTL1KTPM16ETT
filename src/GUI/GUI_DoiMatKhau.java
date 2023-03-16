package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import DAO.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class GUI_DoiMatKhau extends JFrame implements ActionListener{

	private JLabel lblHeader, 
									lblMatKhauCu, 
									lblMatKhauMoi, 
									lblXacNhanMatKhauMoi;

	private JPasswordField txtMatKhauCu, 
													txtMatKhauMoi, 
													txtXacNhanMatKhauMoi;

	private JButton btnHienThiMatKhauCu, 
									btnHienThiMatKhauMoi, 
									btnHienThiXacNhanMatKhauMoi, 
									btnDoiMatKhau, 
									btnHuy;
	
	private String pathMKC = "C:\\Users\\Admin\\Desktop\\eyeOpenIcon.png";
	private String pathMKM = "C:\\Users\\Admin\\Desktop\\eyeOpenIcon.png";
	private String pathXNMK = "C:\\Users\\Admin\\Desktop\\eyeOpenIcon.png";
	
	private ImageIcon icon = new ImageIcon(
		scaleImage(
			new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath(), 20, 15
		)
	);
	
	private TaiKhoan_DAO taiKhoan_DAO;
	
	private NhanVien nvDMK;
	private TaiKhoan tkDMK;
	
	public GUI_DoiMatKhau(NhanVien nvInfo, TaiKhoan tkInfo) {
		setSize(400, 270);
		setLocationRelativeTo(null);
		setUndecorated(true);
		nvDMK = nvInfo;
		tkDMK = tkInfo;
		createGUI();
	}

	private void createGUI() {
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(147, 190, 221));
		
		panel.add(Box.createVerticalStrut(30));
		Box bHeader = Box.createHorizontalBox();
		bHeader.add(lblHeader = new JLabel("Đổi Mật Khẩu"));
		lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(bHeader);
		panel.add(Box.createVerticalStrut(20));
		
		Box bMKC = Box.createHorizontalBox();
		bMKC.add(Box.createHorizontalStrut(10));
		bMKC.add(lblMatKhauCu = new JLabel("Mật Khẩu Cũ"));
		bMKC.add(Box.createHorizontalStrut(10));
		bMKC.add(txtMatKhauCu = new JPasswordField());
		txtMatKhauCu.setEchoChar('*');
		bMKC.add(Box.createHorizontalStrut(10));
		bMKC.add(btnHienThiMatKhauCu = new JButton(icon));
		bMKC.add(Box.createHorizontalStrut(10));
		panel.add(bMKC);
		panel.add(Box.createVerticalStrut(20));
		
		Box bMKM = Box.createHorizontalBox();
		bMKM.add(Box.createHorizontalStrut(10));
		bMKM.add(lblMatKhauMoi= new JLabel("Mật Khẩu Mới"));
		bMKM.add(Box.createHorizontalStrut(10));
		bMKM.add(txtMatKhauMoi = new JPasswordField());
		bMKM.add(Box.createHorizontalStrut(10));
		txtMatKhauMoi.setEchoChar('*');
		bMKM.add(btnHienThiMatKhauMoi = new JButton(icon));
		bMKM.add(Box.createHorizontalStrut(10));
		panel.add(bMKM);
		panel.add(Box.createVerticalStrut(20));
		
		Box bXNMK = Box.createHorizontalBox();
		bXNMK.add(Box.createHorizontalStrut(10));
		bXNMK.add(lblXacNhanMatKhauMoi = new JLabel("Xác Nhận Mật Khẩu Mới"));
		bXNMK.add(Box.createHorizontalStrut(10));
		bXNMK.add(txtXacNhanMatKhauMoi = new JPasswordField());
		bXNMK.add(Box.createHorizontalStrut(10));
		txtXacNhanMatKhauMoi.setEchoChar('*');
		
		bXNMK.add(btnHienThiXacNhanMatKhauMoi = new JButton(icon));
		bXNMK.add(Box.createHorizontalStrut(10));
		panel.add(bXNMK);
		panel.add(Box.createVerticalStrut(20));
		
		Box bChucNang = Box.createHorizontalBox();
		bChucNang.add(btnDoiMatKhau = new JButton("Đổi Mật Khẩu"));
		bChucNang.add(Box.createHorizontalStrut(30));
		bChucNang.add(btnHuy = new JButton("Hủy"));
		panel.add(bChucNang);
		panel.add(Box.createVerticalStrut(30));
		
		lblMatKhauCu.setPreferredSize(lblXacNhanMatKhauMoi.getPreferredSize());
		lblMatKhauMoi.setPreferredSize(lblXacNhanMatKhauMoi.getPreferredSize());
		
		btnHienThiMatKhauCu.setBackground(Color.WHITE);
		btnHienThiMatKhauMoi.setBackground(Color.WHITE);
		btnHienThiXacNhanMatKhauMoi.setBackground(Color.WHITE);
		
		btnHienThiMatKhauCu.addActionListener(this);
		btnHienThiMatKhauMoi.addActionListener(this);
		btnHienThiXacNhanMatKhauMoi.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);
		btnHuy.addActionListener(this);
	}
	
	private Image scaleImage(String path, int w, int h) {
		ImageIcon img = new ImageIcon(path);
		Image image = img.getImage();
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if (o.equals(btnHienThiMatKhauCu)) {
			String MKC = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
			if (pathMKC.equalsIgnoreCase(MKC)) {
				pathMKC = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
				ImageIcon iconMKC = new ImageIcon(
					scaleImage(
						pathMKC, 20, 15
					)
				);
				btnHienThiMatKhauCu.setIcon(iconMKC);
				txtMatKhauCu.setEchoChar((char)0);
			} else {
				pathMKC = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
				ImageIcon iconMKC = new ImageIcon(
					scaleImage(
						pathMKC, 20, 15
					)
				);
				btnHienThiMatKhauCu.setIcon(iconMKC);
				txtMatKhauCu.setEchoChar(('*'));
			}
		} else if (o.equals(btnHienThiMatKhauMoi)) {
			String MKM = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
			if (pathMKM.equalsIgnoreCase(MKM)) {
				pathMKM = new File("./images/iCon/eyeCloseIcon.png").getAbsolutePath();
				ImageIcon iconMKM = new ImageIcon(scaleImage(pathMKM, 20, 15));
				btnHienThiMatKhauMoi.setIcon(iconMKM);
				txtMatKhauMoi.setEchoChar((char)0);
			} else {
				pathMKM = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
				ImageIcon iconMKM = new ImageIcon(scaleImage(pathMKM, 20, 15));
				btnHienThiMatKhauMoi.setIcon(iconMKM);
				txtMatKhauMoi.setEchoChar(('*'));
			}
		} else if (o.equals(btnHienThiXacNhanMatKhauMoi)) {
			String XNMK = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
			if (pathXNMK.equalsIgnoreCase(XNMK)) {
				pathXNMK = new File("./images/iCon/eyeCloseIcon.png").getAbsolutePath();
				ImageIcon iconXNMK = new ImageIcon(scaleImage(pathXNMK, 20, 15));
				btnHienThiXacNhanMatKhauMoi.setIcon(iconXNMK);
				txtXacNhanMatKhauMoi.setEchoChar((char)0);
			} else {
				pathXNMK = new File("./images/iCon/eyeOpenIcon.png").getAbsolutePath();
				ImageIcon iconXNMK = new ImageIcon(scaleImage(pathXNMK, 20, 15));
				btnHienThiXacNhanMatKhauMoi.setIcon(iconXNMK);
				txtXacNhanMatKhauMoi.setEchoChar(('*'));
			}
		} else if (o.equals(btnDoiMatKhau)) {
			String MKC = txtMatKhauCu.getText();
			String MKM = txtMatKhauMoi.getText();
			String XNMK = txtXacNhanMatKhauMoi.getText(); 
			
			taiKhoan_DAO = new TaiKhoan_DAO();
			
			if (MKC.equalsIgnoreCase(tkDMK.getMatKhau())) {
				if (XNMK.equalsIgnoreCase(MKM)) {
					if (taiKhoan_DAO.updateTaiKhoan(new TaiKhoan(nvDMK.getMaNhanVien(), MKM), new NhanVien(nvDMK.getMaNhanVien()))) {
						JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu Thành Công");
						this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Mật Khẩu Xác Nhận Không Giống Mật Khẩu Mới");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mật Khẩu Cũ Nhập Sai");
			}
			
			
		} else if (o.equals(btnHuy)) {
			
			this.dispose();
		}
	}
}
