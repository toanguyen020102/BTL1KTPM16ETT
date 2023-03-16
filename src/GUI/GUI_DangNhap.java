package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import connect_DB.Connect_DB;
import entity.NhanVien;
import entity.TaiKhoan;

public class GUI_DangNhap extends JFrame implements ActionListener, KeyListener{
	private JLabel lblTieuDe, 
									lblAccount, 
									lblPassword, 
									lblSignInStatus;

	private JButton btnSignIn, 
									btnExit;

	private JTextField txtAccount;
	private JPasswordField txtPassword;
	private JCheckBox cbShowPass;

	// DAO
	private TaiKhoan_DAO taiKhoan_DAO;
	private NhanVien_DAO nhanVien_DAO;

	public GUI_DangNhap() {
		try {
			Connect_DB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
		} 
		setTitle("Đăng Nhập");
		setSize(500, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		createGUI();
	}

	private void createGUI() {
		
		JPanel panel;
		add(panel = new JPanel());
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(new Color(147, 190, 221));

		JPanel panelLeft, panelRight;

		panel.add(panelLeft = new JPanel());
		panelLeft.setBackground(new Color(147, 190, 221));
		panelLeft.setPreferredSize(new Dimension(200, 300));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		ImageIcon icon = new ImageIcon(
			new File("./images/logoBrand.jpg").getAbsolutePath()
		);
		Image scaledLogo = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(scaledLogo);
		JLabel lblLogo = new JLabel(" ",logo, (int)JLabel.CENTER_ALIGNMENT);
		panelLeft.add(lblLogo);

		panel.add(panelRight = new JPanel());
		panelRight.setBackground(new Color(147, 190, 221));
		panelRight.setPreferredSize(new Dimension(400, 300));
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

		panelRight.add(Box.createVerticalStrut(30));

		panelRight.add(lblTieuDe = new JLabel("Đăng Nhập"));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		panelRight.add(Box.createVerticalStrut(30));

		Box bAccount, bPassword, bSignIn, bChucNang, bChucNang1;

		bAccount = Box.createHorizontalBox();
		bPassword = Box.createHorizontalBox();
		bChucNang = Box.createHorizontalBox();
		bChucNang1 = Box.createHorizontalBox(); 

		bAccount.add(Box.createHorizontalStrut(20));
		bAccount.add(lblAccount = new JLabel("Tài Khoản"));
		bAccount.add(Box.createHorizontalStrut(5));
		bAccount.add(txtAccount = new JTextField());
		txtAccount.setSize(new Dimension(50, 10));
		bAccount.add(Box.createHorizontalStrut(50));

		bPassword.add(Box.createHorizontalStrut(20));
		bPassword.add(lblPassword = new JLabel("Mật Khẩu"));
		bPassword.add(Box.createHorizontalStrut(9));
		bPassword.add(txtPassword = new JPasswordField());
		txtPassword.setEchoChar('*');
		bPassword.add(Box.createHorizontalStrut(50));

		bChucNang.add(cbShowPass = new JCheckBox("Hiển Thị Mật Khẩu", null));
		cbShowPass.setBackground(new Color(147, 190, 221));

		bChucNang1.add(Box.createHorizontalStrut(30));
		bChucNang1.add(btnSignIn = new JButton("Đăng Nhập", null));
		bChucNang1.add(Box.createHorizontalStrut(30));
		bChucNang1.add(btnExit = new JButton("Thoát"));

		panelRight.add(bAccount);
		panelRight.add(Box.createVerticalStrut(10));
		panelRight.add(bPassword);
		panelRight.add(Box.createVerticalStrut(10));
		panelRight.add(bChucNang);
		panelRight.add(Box.createVerticalStrut(10));
		panelRight.add(bChucNang1);
		panelRight.add(Box.createVerticalStrut(50));

		txtAccount.setText("admin");
		txtPassword.setText("admin");
		// Event 
		cbShowPass.addActionListener(this);
		btnSignIn.addActionListener(this);
		btnExit.addActionListener(this);

		txtPassword.addKeyListener(this);
		txtAccount.addKeyListener(this);
	}

	private void dangNhap() {
		//
		String taiKhoan = "";
		String matKhau = "";
		taiKhoan_DAO = new TaiKhoan_DAO();
		
		if (!txtAccount.getText().equals("")) {
			taiKhoan = txtAccount.getText();	
		}

		if (!txtPassword.getText().equals("")) {
			matKhau = txtPassword.getText();	
		}

		TaiKhoan tk = taiKhoan_DAO.timTaiKhoan(taiKhoan, matKhau);

		if (tk != null) {
			this.dispose();
			nhanVien_DAO = new NhanVien_DAO();
			NhanVien nv = nhanVien_DAO.timNhanVienTheoMa(new NhanVien(tk.getNhanVien().getMaNhanVien()));

			new GUI_TrangChu(nv, tk).setVisible(true);	
		} else {
			JOptionPane.showMessageDialog(null, "Tài Khoản Hoặc Mật Khẩu Bị Sai");
		}
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();

		if (o.equals(cbShowPass)) {
			if (cbShowPass.isSelected()) {
				txtPassword.setEchoChar((char)0);
			} else {
				txtPassword.setEchoChar('*');
			}			
		} else if (o.equals(btnSignIn)) {
			dangNhap();
		} else if (o.equals(btnExit)) {
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Object o = e.getKeyCode();
		if (o.equals(KeyEvent.VK_ENTER)) {
			dangNhap();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}	
}
