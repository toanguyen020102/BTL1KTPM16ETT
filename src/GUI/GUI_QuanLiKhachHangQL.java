package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import DAO.KhachHang_DAO;
import connect_DB.Connect_DB;
import entity.KhachHang;

public class GUI_QuanLiKhachHangQL extends JPanel implements ActionListener, MouseListener {
	private JLabel lblMaKhachHang, 
									lblTenKhachHang, 
									lblSdt, 
									lblGioiTinh, 
									lblNgaySinh, 
									lblEmail, 
									lblDiemTichLuy;

	private JTextField txtMaKhachHang, 
											txtTenKhachHang, 
											txtEmail, 
											txtSdt, 
											txtTim, 
											txtDiemTichLuy;

	private JButton btnXoa, 
									btnSua, 
									btnThem, 
									btnLamMoi;

	private DefaultTableModel modelKhachHang;
	private JTable tableKhachHang;
	private JComboBox<String>cbGioiTinh;
	private JDateChooser ngaySinh;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int with = screenSize.width;
	int height = screenSize.height;

	KhachHang_DAO khachhangDAO = new KhachHang_DAO();
	KhachHang_DAO kh_dao = new KhachHang_DAO();
	
	public GUI_QuanLiKhachHangQL() {
		panelKhachHang();
		setBackground(new Color(147, 190, 221));
		Connect_DB.getConnection();
	}
	private void panelKhachHang() {
		setLayout(new BorderLayout());
		Box b = Box.createVerticalBox();
		Box bcenter = Box.createHorizontalBox();
		Box b0, b1,b2;
		b.add(Box.createVerticalStrut(20));
		// tiêu đề
		b.add(b0=Box.createHorizontalBox());
		JLabel lblTieuDe=new JLabel("LTNT SHOP");
		b0.add(lblTieuDe);
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,20) );
		b.add(Box.createVerticalStrut(20));

		// mã
		b.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(20));
		lblMaKhachHang = new JLabel("Mã khách hàng: ");
		txtMaKhachHang = new JTextField();		
		b1.add(lblMaKhachHang);
		b1.add(txtMaKhachHang);
		txtMaKhachHang.setPreferredSize(new Dimension(250, 25));
		txtMaKhachHang.setEditable(false);
		b.add(Box.createVerticalStrut(10));
		// tên
		lblTenKhachHang = new JLabel("Tên khách hàng: ");
		txtTenKhachHang = new JTextField();
		b1.add(lblTenKhachHang);
		b1.add(txtTenKhachHang);
		txtTenKhachHang.setPreferredSize(new Dimension(500, 25));
		b.add(Box.createVerticalStrut(10));
		// giới tính
		lblGioiTinh =new JLabel("Giới tính");
		b1.add(Box.createHorizontalStrut(5));
		b1.add(cbGioiTinh=new JComboBox<>());
		cbGioiTinh.addItem("Nu");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.setPreferredSize(new Dimension(100,25));
		b1.add(lblGioiTinh);
		b1.add(cbGioiTinh);
		// sdt
		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(20));
		lblSdt = new JLabel("Số Điện Thoại: ");
		txtSdt = new JTextField();
		b2.add(lblSdt);
		b2.add(txtSdt);
		txtSdt.setPreferredSize(new Dimension(150, 25));
		// ngày sinh
		b2.add(Box.createHorizontalStrut(5));
		lblNgaySinh = new JLabel("Ngày Sinh:         ");
		b2.add(lblNgaySinh);
		b2.add(ngaySinh = new JDateChooser());
		ngaySinh.setDateFormatString("yyyy-MM-dd");
		ngaySinh.setPreferredSize(new Dimension(100,25));
		b2.add(Box.createHorizontalStrut(20));
		// email
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField();
		b2.add(lblEmail);
		b2.add(txtEmail);
		txtEmail.setPreferredSize(new Dimension(100, 25));
		b.add(Box.createVerticalStrut(10));
		// điểm tích lũy
		lblDiemTichLuy=new JLabel("Điểm tích lũy");
		txtDiemTichLuy=new JTextField();
		b2.add(lblDiemTichLuy);
		b2.add(txtDiemTichLuy);
		txtDiemTichLuy.setPreferredSize(new Dimension(100, 25));
		add(b, BorderLayout.CENTER);

		// table
		b.add(Box.createVerticalStrut(5));
		bcenter.add(bcenter = Box.createVerticalBox());
		bcenter.setBorder(BorderFactory.createTitledBorder("BẢNG KHÁCH HÀNG "));
		String header[] = {" Mã khách hàng","Tên khách hàng","Giới Tính","Số Điện Thoại","Ngày Sinh","Email","Điểm tích lũy"};
		modelKhachHang = new DefaultTableModel(header,0);
		JScrollPane scroll = new JScrollPane();
		tableKhachHang = new JTable(modelKhachHang);
		tableKhachHang.setDefaultEditor(Object.class, null);
		scroll.setViewportView(tableKhachHang);
		scroll.setPreferredSize(new Dimension(with, 620));
		tableKhachHang.setRowHeight(30);
		tableKhachHang.setAutoCreateRowSorter(true);
		bcenter.add(scroll);
		b.add(bcenter, BorderLayout.CENTER);
		// button 
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setPreferredSize(new Dimension(with, 70));
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		split.add(pnlLeft = new JPanel());
		split.add(pnlRight = new JPanel());

		pnlLeft.add(new JLabel("Nhập thông tin cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(50));
		txtTim.setPreferredSize(new Dimension(100, 35));

		// xử lí nút tìm
		txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TimKiemKeyRelease(evt);
			}
			private void TimKiemKeyRelease(KeyEvent evt) {
				DefaultTableModel tableModel = (DefaultTableModel)tableKhachHang.getModel();
				String search = txtTim.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
				tableKhachHang.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
			}
		});
		//
		pnlRight.add(btnThem = new JButton("Thêm"));
		btnThem.setPreferredSize(new Dimension(120, 40));

		pnlRight.add(btnSua = new JButton("Sửa"));
		btnSua.setPreferredSize(new Dimension(120, 40));

		pnlRight.add(btnXoa = new JButton("Xóa"));
		btnXoa.setPreferredSize(new Dimension(120, 40));

		pnlRight.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.setPreferredSize(new Dimension(120, 40));

		lblSdt.setPreferredSize(lblMaKhachHang.getPreferredSize());

		// xử lí
		tableKhachHang.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);

		try {
			loadDuLieu();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// TODO: handle exception
	}

	public void loadDuLieu() throws SQLException, ClassNotFoundException{
		while (modelKhachHang.getRowCount()!=0) {
			modelKhachHang.removeRow(0);
		}
		for (KhachHang khachHang : kh_dao.getAllKhachHang()) {
			modelKhachHang.addRow(new Object[] {
					khachHang.getMaKhachHang(),
					khachHang.getTenKhachHang(),
					khachHang.isGioiTinh()==false? "Nu" :"Nam",
							khachHang.getSoDienThoai(),
							khachHang.getNgaySinh(),
							khachHang.getEmail(),
							khachHang.getDiemTichLuy()});
		}
		tableKhachHang.setModel(modelKhachHang);
	}


	public void mouseClicked(MouseEvent e) {
		int row =tableKhachHang.getSelectedRow();

		if (row >= 0)
		{
			txtMaKhachHang.setText(tableKhachHang.getValueAt(row, 0).toString());
			txtTenKhachHang.setText(tableKhachHang.getValueAt(row, 1).toString());
			cbGioiTinh.setSelectedItem(tableKhachHang.getValueAt(row, 2).toString());
			txtSdt.setText(tableKhachHang.getValueAt(row, 3).toString());

			String NgaySinhString[] = tableKhachHang.getValueAt(row, 4).toString().split("-");
			int year = Integer.parseInt(NgaySinhString[0]);
			int month = Integer.parseInt(NgaySinhString[1]);
			int day = Integer.parseInt(NgaySinhString[2]);

			Date datengayssinh = new Date(year-1900, month-1, day);
			ngaySinh.setDate(datengayssinh);

			txtEmail.setText(tableKhachHang.getValueAt(row, 5).toString());
			txtDiemTichLuy.setText(tableKhachHang.getValueAt(row, 6).toString());

		}

	}


	public void mousePressed(MouseEvent e) {
		

	}


	public void mouseReleased(MouseEvent e) {
		

	}


	public void mouseEntered(MouseEvent e) {
		

	}


	public void mouseExited(MouseEvent e) {
		

	}


	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)){
			if (validData()) ThemKhachHang();
		}
		else if (o.equals(btnSua)) {
			if (validData()) SuaKhachHang();
		}
		else if (o.equals(btnXoa)) {
			XoaKhachHang();
		}
		else if (o.equals(btnLamMoi)) {
			LamMoiKhachHang();
		}

	}
	// xóa rỗng
	private void LamMoiKhachHang() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		cbGioiTinh.setSelectedItem("");
		txtSdt.setText("");
		ngaySinh.setDate(null);
		txtEmail.setText("");
		txtDiemTichLuy.setText("");
		txtMaKhachHang.requestFocus();
	}

	// thêm 
	private void ThemKhachHang() {
		try {
			java.util.Date dateNOW = new java.util.Date();
			SimpleDateFormat spDateFormat = new SimpleDateFormat("ssmmhhddMMyyyy");
			String dateString = spDateFormat.format(dateNOW);

			String maKhachHang = "KH" + dateString;
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
			int diemTichLuy = Integer.parseInt(txtDiemTichLuy.getText());

			KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, gt, soDienThoai, ngaysinh, email, 0);
			if (khachhangDAO.createKhachHang(kh)) {
				modelKhachHang.addRow(new Object[] {kh.getMaKhachHang(),kh.getTenKhachHang(),kh.isGioiTinh()?"Nam":"Nu",kh.getSoDienThoai(),sdf.format(kh.getNgaySinh()),kh.getEmail(),kh.getDiemTichLuy()});
				JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
				loadDuLieu();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm khách hàng không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// xóa
	private void XoaKhachHang() {
		int row =tableKhachHang.getSelectedRow();
		if (row >=0) {
			String maKhachHang =(String) tableKhachHang.getValueAt(row, 0);
			int mess=JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa dòng này không???", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (mess == JOptionPane.YES_OPTION) {
				if (kh_dao.deleteKhachHang(maKhachHang)) {
					modelKhachHang.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Xóa khách hàng không thành công");
				}
			}
		}
	}
	
	// sửa
	private void SuaKhachHang() {
		try {
			int row = tableKhachHang.getSelectedRow();
			String maKhachHang = txtMaKhachHang.getText();
			String tenKhachHang = txtTenKhachHang.getText();
			String GioiTinh = cbGioiTinh.getSelectedItem().toString();
			Boolean gt = null;
			if (GioiTinh.equals("Nu")) {
				gt = true;
			} else {
				gt = false;
			}
			String sdt = txtSdt.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateSinh = sdf.format(ngaySinh.getDate());
			java.sql.Date nsinh =java.sql.Date.valueOf(dateSinh);
			String email = txtEmail.getText();
			int diemTichLuy= Integer.parseInt(txtDiemTichLuy.getText());
			KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, gt, sdt, nsinh, email, diemTichLuy);
			
			if (khachhangDAO.upDateKhachHang(kh)) {
				JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
				tableKhachHang.setValueAt(txtMaKhachHang.getText(),row,0);
				tableKhachHang.setValueAt(txtTenKhachHang.getText(),row,1);
				tableKhachHang.setValueAt(cbGioiTinh.getSelectedItem().toString(),row,2);
				tableKhachHang.setValueAt(txtSdt.getText(),row,3);
				tableKhachHang.setValueAt(ngaySinh.getDate(),row,4);
				tableKhachHang.setValueAt(txtEmail.getText(),row,5);
				tableKhachHang.setValueAt(txtDiemTichLuy.getText(),row,6);
							
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật khách hàng không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean validData() {	
		SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if ( !(txtTenKhachHang.getText().length() > 0 ) )  {		
			JOptionPane.showMessageDialog(this,"Tên Không Được Rỗng");
			return false;
		} 
		if ( txtEmail.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog(this,"Email không được rỗng");
			return false;
		}	
		if ( txtEmail.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog(this,"Email không được rỗng");
			return false;
		}
		if ( txtSdt.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng");
			txtSdt.requestFocus();
			return false;
		}
		if ( !( txtSdt.getText().matches("[0][0-9]{9}") ) ) {
			JOptionPane.showMessageDialog(this, " Số điện thoại số đầu tiên là số 0 tối đa nhập 10 số");			
			return false;
		}

		long millis = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis));
		cal.add(Calendar.YEAR, -13);
		String ngaySinhKH = spDateFormat.format(cal.getTime());

		if ( ngaySinh.getDate() == null || !(spDateFormat.format(ngaySinh.getDate()).toString().matches("^20\\d{2}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$"))){
			JOptionPane.showMessageDialog(this,"Ngày sinh phải be hơn ngày hiện tại và có định dạng yyyy-MM-dd");
			return false;
		} else if ( !ngaySinh.getDate().before(Date.valueOf(ngaySinhKH) ) ) {
			JOptionPane.showMessageDialog(this, "Phai tren 13 tuoi");
			return false;
		}
		return true;
	}
}