package GUI;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;
import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import connect_DB.Connect_DB;
import entity.NhanVien;
import entity.TaiKhoan;

public class GUI_QuanLiNhanVienQL extends JPanel implements ActionListener,MouseListener {

	private JLabel lblTieuDe, 
									lblMaNV, 
									lblTenNV, 
									lblSdt, 
									lblGioiTinh, 
									lblNgaySinh, 
									lblEmail, 
									lblNgayVaoLam,
									lblCMND, 
									lblChucVu, 
									lblDiaChi, 
									lblThanhPho, 
									lblQuan, 
									lblPhuong;
									
	private JTextField txtTenNV, 
											txtEmail, 
											txtCMND,
											txtDiaChi, 
											txtTim, 
											txtMaNV, 
											txtSdt;
	
	private JComboBox<String> cbChucVu, 
														cbPhuong, 
														cbQuan, 
														cbGioiTinh, 
														cbThanhPho;
	
	private JButton btnXoa, 
									btnSua, 
									btnThem, 
									btnLamMoi;

	private JDateChooser ngaySinh, 
												ngayVaoLam;

	private JTable tableNhanVien;
	private DefaultTableModel model;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int with = screenSize.width;
	int height = screenSize.height;
	
	private NhanVien_DAO nvdao;
	private NhanVien nvQL;
	
	public GUI_QuanLiNhanVienQL(NhanVien nv) {

		Connect_DB.getConnection();
		nvQL = nv;
		panelNhanVien();
		setBackground(new Color(147, 190, 221));

	}
	private void panelNhanVien() {
		setLayout(new BorderLayout());

		Box b = Box.createVerticalBox();
		Box bcenter = Box.createHorizontalBox();
		Box b0, b1,b2,b3;
		b.add(Box.createHorizontalStrut(40));
		b.add(Box.createVerticalStrut(20));
		// tiêu đề 
		b.add(b0=Box.createHorizontalBox());
		JLabel lblTieuDe=new JLabel("LTNT SHOP");
		b0.add(lblTieuDe);
		lblTieuDe.setFont(new Font("Arial",Font.BOLD,20) );
		b.add(Box.createVerticalStrut(20));

		// mã nhân viên
		b.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(20));
		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField();
		b1.add(lblMaNV);
		b1.add(txtMaNV);
		b1.add(Box.createHorizontalStrut(20));
		txtMaNV.setPreferredSize(new Dimension(400, 25));
		b.add(Box.createVerticalStrut(10));
		// tên nhân viên
		JLabel lblten = new JLabel("Tên nhân viên: ");
		txtTenNV = new JTextField();
		b1.add(lblten);
		b1.add(txtTenNV);
		txtTenNV.setPreferredSize(new Dimension(500, 25));
		b.add(Box.createVerticalStrut(10));

		//số điện thoại
		JLabel lblSdt = new JLabel("Số Điện Thoại: ");
		txtSdt = new JTextField();
		b1.add(lblSdt);
		b1.add(txtSdt);
		txtSdt.setPreferredSize(new Dimension(240, 25));
		// giới tính
		JLabel lblGioiTinh =new JLabel("Giới tính");
		b1.add(Box.createHorizontalStrut(5));
		b1.add(cbGioiTinh=new JComboBox<>());
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.setPreferredSize(new Dimension(100,25));
		b1.add(lblGioiTinh);
		b1.add(cbGioiTinh);
		// ngày sinh
		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(20));
		JLabel lblNgaySinh = new JLabel("Ngày Sinh: ");
		b2.add(lblNgaySinh);
		ngaySinh = new JDateChooser();
		b2.add(ngaySinh);
		ngaySinh.setDateFormatString("yyyy-MM-dd");
		ngaySinh.setPreferredSize(new Dimension(230,25));
		//email
		b2.add(Box.createHorizontalStrut(5));
		JLabel lblEmail = new JLabel("Email:              ");
		txtEmail = new JTextField();
		b2.add(lblEmail);
		b2.add(txtEmail);
		txtEmail.setPreferredSize(new Dimension(100, 25));
		// ngày vào làm
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
		b2.add(lblNgayVaoLam);
		ngayVaoLam = new JDateChooser();
		b2.add(ngayVaoLam);
		ngayVaoLam.setDateFormatString("yyyy-MM-dd");
		ngayVaoLam.setPreferredSize(new Dimension(100,25)); 
		b2.add(Box.createHorizontalStrut(20));
		// chứng minh nhân dân		
		JLabel lblCMDN= new JLabel("CMND:");
		b2.add(lblCMDN);
		txtCMND = new JTextField();
		b2.add(txtCMND);
		txtCMND.setPreferredSize(new Dimension(100, 25));
		b.add(Box.createVerticalStrut(20));

		// chức vụ
		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createHorizontalStrut(20));
		JLabel lblChucVu=new JLabel("Chức vụ");
		cbChucVu =new JComboBox();
		cbChucVu.addItem("Nhan Vien Ban Hang");
		cbChucVu.addItem("Quan Li");
		cbChucVu.setPreferredSize(new Dimension(150,25));
		b3.add(lblChucVu);
		b3.add(cbChucVu);
		b.add(Box.createVerticalStrut(10));

		//thành phố
		JLabel lblThanhPho = new JLabel("Thành Phố: ");
		cbThanhPho = new JComboBox();
		cbThanhPho.addItem("Hồ Chí Minh");
		cbThanhPho.addItem("Bình Định");
		cbThanhPho.addItem("Quảng Nam");
		cbThanhPho.addItem("Quảng Ngãi");
		cbThanhPho.addItem("Đà Lạt");
		cbThanhPho.addItem("Phú Yên");
		cbThanhPho.addItem("Đà Nẵng");

		cbThanhPho.setPreferredSize(new Dimension(170,25));
		b3.add(lblThanhPho);
		b3.add(cbThanhPho);
		// quận/huyện
		JLabel lblQuan = new JLabel("Quận/Huyện: ");
		cbQuan = new JComboBox();

		cbQuan.addItem("Gò Vấp");
		cbQuan.addItem("An Nhơn");
		cbQuan.addItem("Phù Mỹ");
		cbQuan.addItem("Tam Kỳ");
		cbQuan.setPreferredSize(new Dimension(170,25));

		b3.add(lblQuan);
		b3.add(cbQuan);
		// phường/xã
		JLabel lblPhuong = new JLabel("Phường/Xã: ");
		cbPhuong = new JComboBox();

		cbPhuong.addItem("Nhơn Hòa");
		cbPhuong.addItem("Nhơn Tân");
		cbPhuong.addItem("Nhơn Thọ");
		cbPhuong.addItem("Nhơn Lộc");
		cbPhuong.addItem("Nhơn Khánh");
		cbPhuong.addItem("Nhơn Mỹ");
		cbPhuong.addItem("Núi Thành ");



		cbPhuong.setPreferredSize(new Dimension(170,25));

		b3.add(lblPhuong);
		b3.add(cbPhuong);
		// địa chỉ
		JLabel lblDiachi = new JLabel("Địa Chỉ: ");
		txtDiaChi = new JTextField();
		b3.add(lblDiachi);
		b3.add(txtDiaChi);
		txtDiaChi.setPreferredSize(new Dimension(70, 25));
		b.add(Box.createVerticalStrut(20));
		add(b,BorderLayout.CENTER);
		b.add(Box.createVerticalStrut(10));
		// table
		bcenter.add(bcenter = Box.createVerticalBox());
		String header[] = {"Mã nhân viên","Tên nhân viên","Số Điện Thoại","Giới Tính","Ngày Sinh","Email","Ngày vào làm","CMND","Chức vụ","Thành Phố","Quận/Huyện","Phường/Xã","Địa chỉ"};
		model = new DefaultTableModel(header,0);
		tableNhanVien = new JTable(model);
		tableNhanVien.setDefaultEditor(Object.class, null);
		JScrollPane scroll = new JScrollPane(tableNhanVien);
		scroll.setPreferredSize(new Dimension(with, 520));

		tableNhanVien.setRowHeight(30);
		tableNhanVien.setAutoCreateRowSorter(true);
		bcenter.setBorder(BorderFactory.createTitledBorder("BẢNG NHÂN VIÊN"));
		bcenter.add(scroll);
		//
		b.add(Box.createVerticalStrut(20));
		b.add(bcenter,BorderLayout.CENTER);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setPreferredSize(new Dimension(with, 60));
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
				
				DefaultTableModel tableModel = (DefaultTableModel)tableNhanVien.getModel();
				String search = txtTim.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
				tableNhanVien.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
        });

		pnlRight.add(btnThem = new JButton("Thêm"));
		btnThem.setPreferredSize(new Dimension(120, 40));

		pnlRight.add(btnSua = new JButton("Sửa"));
		btnSua.setPreferredSize(new Dimension(120, 40));


		pnlRight.add(btnXoa = new JButton("Xóa"));
		btnXoa.setPreferredSize(new Dimension(120, 40));

		pnlRight.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.setPreferredSize(new Dimension(120, 40));

		lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblChucVu.setPreferredSize(lblMaNV.getPreferredSize());
		txtMaNV.setEditable(false);
		// sự kiện 
		tableNhanVien.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		try {
			loadDuLieu();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row =tableNhanVien.getSelectedRow();
		if (row>=0) {
			txtMaNV.setText(tableNhanVien.getValueAt(row, 0).toString());
			txtTenNV.setText(tableNhanVien.getValueAt(row, 1).toString());
			txtSdt.setText(tableNhanVien.getValueAt(row, 2).toString());
			cbGioiTinh.setSelectedItem(tableNhanVien.getValueAt(row, 3).toString());

			String NgaySinhString[] = tableNhanVien.getValueAt(row, 4).toString().split("-");
			int year = Integer.parseInt(NgaySinhString[0]);
			int month = Integer.parseInt(NgaySinhString[1]);
			int day = Integer.parseInt(NgaySinhString[2]);

			Date datengayssinh = new Date(year-1900, month-1, day);
			ngaySinh.setDate(datengayssinh);

			txtEmail.setText(tableNhanVien.getValueAt(row, 5).toString());

			//				ngayVaoLam.setDate(new java.util.Date(tableNhanVien.getValueAt(row, 6).toString()));
			String NgayVaoLamString[] = tableNhanVien.getValueAt(row, 6).toString().split("-");
			int year1 = Integer.parseInt(NgayVaoLamString[0]);
			int month1 = Integer.parseInt(NgayVaoLamString[1]);
			int day1 = Integer.parseInt(NgayVaoLamString[2]);

			Date datevaolam = new Date(year1-1900, month1-1, day1);
			ngayVaoLam.setDate(datevaolam);

			txtCMND.setText(tableNhanVien.getValueAt(row, 7).toString());

			cbChucVu.setSelectedItem(tableNhanVien.getValueAt(row, 8).toString());
			String Thanhpho = tableNhanVien.getValueAt(row, 9).toString();	
			cbThanhPho.setSelectedItem(Thanhpho);
			String quan = tableNhanVien.getValueAt(row, 10).toString();	
			cbQuan.setSelectedItem(quan);
			cbPhuong.setSelectedItem(tableNhanVien.getValueAt(row, 11).toString());
			txtDiaChi.setText(tableNhanVien.getValueAt(row, 12).toString());
			txtMaNV.requestFocus(getFocusTraversalKeysEnabled());

		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)){
			if (validData()) {
				ThemNhanVien();
			}
		}
		else if (o.equals(btnSua)) {
			if (validData()) {
				SuaNhanVien();
			}
		}
		else if (o.equals(btnXoa)) {
			XoaNhanVien();
		}

		else if (o.equals(btnLamMoi)) {
			LamMoiNhanVien();

		}
	}
	// xóa rỗng
	private void LamMoiNhanVien() {
		txtMaNV.setText("");
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtSdt.setText("");
		cbGioiTinh.setSelectedItem("");
		ngaySinh.setDate(null);
		txtEmail.setText("");
		ngayVaoLam.setDate(null);
		txtCMND.setText("");
		cbChucVu.setSelectedItem("");
		cbThanhPho.setSelectedItem("");
		cbQuan.setSelectedItem("");
		cbPhuong.setSelectedItem("");
		txtDiaChi.setText("");
		txtMaNV.requestFocus();
	}

	public void loadDuLieu() throws SQLException, ClassNotFoundException{
		while(model.getRowCount() > 0){
			model.removeRow(0);
		}
		nvdao = new NhanVien_DAO();
		
		model.setRowCount(0);
		for (NhanVien nhanVien : nvdao.getAllNhanVienCoTaiKhoan()) {
			model.addRow(
				new Object[] {
					nhanVien.getMaNhanVien(),
					nhanVien.getTenNhanVien(),
					nhanVien.getsDT(),
					nhanVien.isGioiTinh()==false ? "Nu" : "Nam",
					nhanVien.getNgaySinh().toString(),
					nhanVien.getEmail(),
					nhanVien.getNgayVaoLam().toString(),
					nhanVien.getChungMinhNhanDan(),
					nhanVien.getChucVu(),
					nhanVien.getThanhPho(),
					nhanVien.getQuan(),
					nhanVien.getPhuong(),
					nhanVien.getDiaChi()
				}
			);
		}
	}

	// thêm 
	private void ThemNhanVien() {
		try {
			java.util.Date dateNOW = new java.util.Date();
			SimpleDateFormat spDateFormat = new SimpleDateFormat("ssmmhhddMMyyyy");
			String dateString = spDateFormat.format(dateNOW);

			String Manv = "NV" + dateString;
			String TenNv = txtTenNV.getText();
			String sdt = txtSdt.getText();
			String GioiTinh = cbGioiTinh.getSelectedItem().toString();
			Boolean gt = null;
			if (GioiTinh.equals("Nam")) {
				gt = true;
			} else {
				gt = false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateSinh = sdf.format(ngaySinh.getDate());
			java.sql.Date nsinh =java.sql.Date.valueOf(dateSinh);
			String email = txtEmail.getText();
			String dateVaoLam = sdf.format(ngayVaoLam.getDate());
			java.sql.Date nvl =java.sql.Date.valueOf(dateVaoLam);
			String cmnd = txtCMND.getText();
			String chucvu = cbChucVu.getSelectedItem().toString();
			String thanhpho = cbThanhPho.getSelectedItem().toString();			
			String quan = cbQuan.getSelectedItem().toString();
			String Phuong = cbPhuong.getSelectedItem().toString();
			String diachi = txtDiaChi.getText();

			NhanVien nv = new NhanVien(Manv, TenNv, sdt, gt, nsinh, email, nvl, cmnd, chucvu, thanhpho, quan, Phuong, diachi);
			if (nvdao.createNhanVien(nv)) {
				TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
				TaiKhoan tk = new TaiKhoan(Manv, "123456");
				tk_dao.taoTaiKhoan(tk, nv);
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công, ten tai khoan la " + tk.getTaiKhoan() + " va mat khau la " + tk.getMatKhau());
				model.setRowCount(0);
				loadDuLieu();
			}else {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//xóa
	private void XoaNhanVien() {
		int row = tableNhanVien.getSelectedRow();
		if (row >= 0) {
			String maNhanVien = (String) tableNhanVien.getValueAt(row, 0);
			int mess = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa dòng này không???", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (mess == JOptionPane.YES_OPTION) {
				if (!maNhanVien.equals(nvQL.getMaNhanVien())) {
					if ( new TaiKhoan_DAO().deleteTaiKhoan(maNhanVien) ) {
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
						try {
							loadDuLieu();
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Xóa nhân viên không thành công");
					}
				} else JOptionPane.showMessageDialog(this, "Ban khong the xoa tai khoan chinh minh!");	
			}
		}
	}
	// sửa
	private void SuaNhanVien() {
		if ( validData() ) {
			if ( 
				tableNhanVien
					.getValueAt( tableNhanVien.getSelectedRow(), 0)
						.toString()
							.equals( nvQL.getMaNhanVien() ) 
			) {
				if ( 
					nvQL.getChucVu().equals("Quan Li") && 
					cbChucVu.getSelectedItem().toString().equals("Nhan Vien Ban Hang")
				) {
					JOptionPane.showMessageDialog(this, "Ban khong duoc ha chuc vu cua minh xuong");
				} else {
					try {
						int row = tableNhanVien.getSelectedRow();
						if (row == 0) {
							String Manv = txtMaNV.getText();
							String TenNv = txtTenNV.getText();
							String sdt = txtSdt.getText();
							String gioiTinh = cbGioiTinh.getSelectedItem().toString();
							Boolean gt = null;
							if (gioiTinh.equals("Nu")) {
								gt = true;
							} else {
								gt = false;
							}
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String dateSinh = sdf.format(ngaySinh.getDate());
							java.sql.Date nsinh =java.sql.Date.valueOf(dateSinh);
							String email = txtEmail.getText();
							String dateVaoLam = sdf.format(ngayVaoLam.getDate());
							java.sql.Date nvl =java.sql.Date.valueOf(dateVaoLam);
							String cmnd = txtCMND.getText();
							String chucvu = cbChucVu.getSelectedItem().toString();
							String thanhpho = cbThanhPho.getSelectedItem().toString();			
							String quan = cbQuan.getSelectedItem().toString();
							String Phuong = cbPhuong.getSelectedItem().toString();
							String diachi = txtDiaChi.getText();
			
							NhanVien nv = new NhanVien(Manv, TenNv, sdt, gt, nsinh, email, nvl, cmnd, chucvu, thanhpho, quan, Phuong, diachi);
							if ( nvdao.upDateNhanVien(nv) ) {	
								JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
								loadDuLieu();
							} else {
								JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công");
							}
						} else {
							JOptionPane.showMessageDialog(this, "Phải chọn dòng cần sửa");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private boolean validData() {
		SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if ( !( txtTenNV.getText().length() > 0 ) ) {		
			JOptionPane.showMessageDialog(this,"Tên Không Được Rỗng");
			txtTenNV.requestFocus();
			return false;
		} else if ( !( txtTenNV.getText().matches("^([A-Z][a-z]*((\s)))+[A-Z][a-z]*$") ) ) {
			JOptionPane.showMessageDialog(this,"Tên không được chứa số và ký tự đặc biệt");
			txtTenNV.requestFocus();
			return false;
		}
		//SĐT
		if ( txtSdt.getText().length() <= 0 ){
			JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng");
			txtSdt.requestFocus();
			return false;
		}
		if ( !( txtSdt.getText().matches("[0][0-9]{9}") ) ){
			JOptionPane.showMessageDialog(this, "Số điện thoại phải chứa số đầu tiên là số 0 tối đa nhập 10 số");
			txtSdt.requestFocus();
			return false;
		}
		
		long millis = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis)); 
		cal.add(Calendar.YEAR, -18);
		String ngaySinhNV = spDateFormat.format(cal.getTime());

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date(millis));
		String ngayHienTai = spDateFormat.format(cal2.getTime());

		if ( ngaySinh.getDate() == null || !(spDateFormat.format(ngaySinh.getDate()).toString().matches("^(19|20)\\d{2}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$"))) {
			JOptionPane.showMessageDialog(this, "Ngày sinh phải Đúng Định Dạng yyyy-MM-dd và khong duoc rong");
			return false;
		} else if ( !ngaySinh.getDate().before(Date.valueOf( ngaySinhNV ) ) ) {
			JOptionPane.showMessageDialog(this, "Phai tren 18 tuoi");
			return false;
		}

		// email
		if ( txtEmail.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog( this,"Email không được rỗng" );
			return false;
		}	

		if ( ngayVaoLam.getDate() == null || !( spDateFormat.format(ngayVaoLam.getDate()).toString().matches("^20\\d{2}[-](0[1-9]|1[0-2])[-](0[1-9]|[1-2][0-9]|3[0-1])$") ) ) {
			JOptionPane.showMessageDialog(this, "Ngày vào làm phải Đúng Định Dạng yyyy-MM-dd vvà khong duoc rong");
			return false;
		} else if ( !ngayVaoLam.getDate().before(Date.valueOf(ngayHienTai) ) ) {
			JOptionPane.showMessageDialog(this, "Ngay vao lam phai be hon hoac bang ngay hien tai");
			return false;
		} else if ( (ngayVaoLam.getDate().getYear() < ngaySinh.getDate().getYear() + 18) ) {
			JOptionPane.showMessageDialog(this, "Ngay vao lam phai hon ngay sinh 18 nam");
			return false;
		} else if ( (ngayVaoLam.getDate().getMonth() < ngaySinh.getDate().getMonth() ) ) {
			JOptionPane.showMessageDialog(this, "Ngay vao lam phai hon ngay sinh 18 nam");
			return false;
		} else if ( (ngayVaoLam.getDate().getDay() < ngaySinh.getDate().getDay() ) ) {
			JOptionPane.showMessageDialog(this, "Ngay vao lam phai hon ngay sinh 18 nam");
			return false;
		}
		// CMND
		if ( txtCMND.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog(this, "CMND không được nhập chữ ");
			return false;
		}
		if ( !( txtCMND.getText().matches("\\d{12}") ) ) {
			JOptionPane.showMessageDialog(this, "CMND không được nhập chữ và nhập 12 số");
			return false;
		}
		if ( txtDiaChi.getText().length() <= 0 ) {
			JOptionPane.showMessageDialog(this, "Dia chi khong duoc rong");
			return false;
		}
		return true;
	}
}