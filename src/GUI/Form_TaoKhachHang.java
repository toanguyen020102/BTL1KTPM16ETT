package GUI;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import DAO.KhachHang_DAO;
import connect_DB.Connect_DB;
import entity.KhachHang;

public class Form_TaoKhachHang extends JFrame implements ActionListener{
	private JLabel lblTieuDe, 
									lblMaKhachHang, 
									lblTenKhachHang, 
									lblGioiTinh, 
									lblSdt, 
									lblNgaySinh, 
									lblEmail;

	private JTextField txtMaKhachHang, 
											txtTenKhachHang, 
											txtEmail, 
											txtSdt, 
											txtNgaySinh;

	private JButton btnHuy, 
									btnThem, 
									btnLamMoi;
									
	private JComboBox<String> cbGioiTinh;
	private JDateChooser ngaySinh;
	String sdtKHForm;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int with = screenSize.width;
	int height = screenSize.height;

	private KhachHang_DAO kh_DAO = new KhachHang_DAO();
	public Form_TaoKhachHang(String sdtKH) {
		setSize(800	, 180);
		setUndecorated(true);
		setLocationRelativeTo(null);
		panelKhachHang(sdtKH);
		Connect_DB.getConnection();
	}

	private void panelKhachHang(String sdtKH) {
		JPanel panel = new JPanel();
		add(panel);

		panel.setBackground(new Color(147, 190, 221));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Panel Input Info
		JPanel panelTop;
		panel.add(panelTop = new JPanel());
		panelTop.setBackground(new Color(147, 190, 221));
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

		// Header
		panelTop.add(Box.createVerticalStrut(20));
		Box bTitle = Box.createHorizontalBox();
		bTitle.add(lblTieuDe = new JLabel("LTNT SHOP"));
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,20));
		panelTop.add(bTitle);
		panelTop.add(Box.createVerticalStrut(20));

		// ID Customer
		Box bRowKH1 = Box.createHorizontalBox();
		bRowKH1.add(Box.createHorizontalStrut(20));
		bRowKH1.add(lblMaKhachHang = new JLabel("Mã khách hàng: "));
		bRowKH1.add(txtMaKhachHang = new JTextField());
		txtMaKhachHang.setEditable(false);

		// Name Customer
		bRowKH1.add(lblTenKhachHang = new JLabel("Tên khách hàng: "));
		bRowKH1.add(txtTenKhachHang = new JTextField());

		// Gender
		bRowKH1.add(lblGioiTinh =new JLabel("Giới tính"));
		bRowKH1.add(cbGioiTinh=new JComboBox<>());
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.addItem("Nam");
		bRowKH1.add(cbGioiTinh);
		bRowKH1.add(Box.createHorizontalStrut(20));
		panelTop.add(bRowKH1);
		panelTop.add(Box.createVerticalStrut(20));

		// Telephone
		Box bRowKH2 = Box.createHorizontalBox();
		bRowKH2.add(Box.createHorizontalStrut(20));
		bRowKH2.add(lblSdt = new JLabel("Số Điện Thoại: "));
		bRowKH2.add(txtSdt = new JTextField(sdtKH));

		// Birthday
		bRowKH2.add(Box.createHorizontalStrut(5));
		lblNgaySinh = new JLabel("Ngày Sinh: ");
		bRowKH2.add(lblNgaySinh);
		ngaySinh = new JDateChooser();
		ngaySinh.setDateFormatString("dd/MM/yyyy");
		try {
			ngaySinh.setDate(
				new SimpleDateFormat("dd/MM/yyyy").parse("1/1/1970")
			);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bRowKH2.add(ngaySinh);
		ngaySinh.setDateFormatString("yyyy-MM-dd");
		bRowKH2.add(Box.createHorizontalStrut(20));

		// Email
		bRowKH2.add(lblEmail = new JLabel("Email: "));
		bRowKH2.add(txtEmail = new JTextField());
		bRowKH2.add(Box.createHorizontalStrut(20));
		panelTop.add(bRowKH2);
		panelTop.add(Box.createVerticalStrut(20));

		// Panel Button 
		JPanel panelChucNang;
		panel.add(panelChucNang = new JPanel());
		panelChucNang.setBackground(new Color(147, 190, 221));
		panelChucNang.setLayout(new BoxLayout(panelChucNang, BoxLayout.Y_AXIS));

		Box bChucNang = Box.createHorizontalBox();
		bChucNang.add(btnThem = new JButton("Thêm"));
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnLamMoi = new JButton("Làm mới"));
		bChucNang.add(Box.createHorizontalStrut(50));
		bChucNang.add(btnHuy = new JButton("Hủy"));
		panelChucNang.add(bChucNang);
		panelChucNang.add(Box.createVerticalStrut(20));

		// Event
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (validData()) {
				ThemKhachHang();
			}
		} else if (o.equals(btnLamMoi)) {
			LamMoiKhachHang();
		} else if (o.equals(btnHuy)) {
			this.dispose();
		}
	}
	private void LamMoiKhachHang() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		cbGioiTinh.setSelectedItem("");
		txtSdt.setText("");
		ngaySinh.setDate(null);
		txtEmail.setText("");
		txtMaKhachHang.requestFocus();
	}
	private void ThemKhachHang() {
		try {
			java.util.Date dateNOW = new java.util.Date();
			SimpleDateFormat spDateFormat = new SimpleDateFormat("ssmmHHddMMyyyy");
			String dateString = spDateFormat.format(dateNOW);

			String maKhachHang = "KH"+dateString;
			String tenKhachHang = txtTenKhachHang.getText();
			String gioiTinh = cbGioiTinh.getSelectedItem().toString();
			Boolean gt = null;
			if (gioiTinh.equals("Nam")) {
				gt = true;
			} else {
				gt = false;
			}

			String soDienThoai = txtSdt.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sn = sdf.format(ngaySinh.getDate());
			java.sql.Date ngaysinh = java.sql.Date.valueOf(sn);
			String email = txtEmail.getText();

			KhachHang kh1 = new KhachHang(maKhachHang, tenKhachHang, gt, soDienThoai, ngaysinh, email, 0);
			if (kh_DAO.createKhachHang(kh1)) {
				JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
				StringSelection stringSelection = new StringSelection(txtSdt.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm khách hàng không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validData() {	
		String ten = txtTenKhachHang.getText();
		SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if ( !(ten.length() > 0) ) {		
			JOptionPane.showMessageDialog(this,"Phải có tên khách hàng");
			return false;
		} else if (!ten.matches("[A-Za-z\\s]+")) {
			JOptionPane.showMessageDialog(this,"Tên khách hàng không được chứa số và kí tự đặc biệt");
			return false;
		}
		if (txtSdt.getText().length()  <= 0 ){
			JOptionPane.showMessageDialog(this, "Phải có số điện thoại");
			txtSdt.requestFocus();
			return false;
		}
		long millis = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis));
		cal.add(Calendar.YEAR, -18);
		String ngaySinhKH = spDateFormat.format(cal.getTime());

		if ( !ngaySinh.getDate().before(Date.valueOf(ngaySinhKH)) ) {
			JOptionPane.showMessageDialog(this, "Phải trên 18 tuổi");
			return false;
		}
		if (txtEmail.getText().length() <= 0) {
			JOptionPane.showMessageDialog(this,"Phải có email");
			return false;
		}	
		if (!(txtEmail.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))) {
			JOptionPane.showMessageDialog(this,"Email phải có định dạng example@xyz.abc hoặc eXAmple123@xyz.abc");
			return false;
		}
		if (txtEmail.getText().length() <= 0){
			JOptionPane.showMessageDialog(this, "Phải có số điện thoại");			
			return false;
		}
		if (!(txtSdt.getText().matches("[0][0-9]{9}"))){
			JOptionPane.showMessageDialog(this, "Số điện thoại số đầu tiên là số 0 tối đa nhập 10 số");			
			return false;
		}
		return true;
	}
}