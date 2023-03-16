package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.sql.Date;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.*;

import com.itextpdf.text.DocumentException;

import DAO.*;
import entity.*;
import connect_DB.Connect_DB;

public class GUI_TaoHoaDon_2 extends JPanel implements ActionListener, KeyListener, MouseListener {
	// Attribute Panel Customer
	JLabel lblSDTKH, lblTenKH, lblNgaySinh, lblCapDoKH;
	JTextField txtSDTKH, txtTenKH, txtNgaySinhKH, txtCapDoKH;
	JButton btnTimKiemKH, btnTaoKH;

	// Attribute Panel Order Detail
	JTable tblCTHD;
	DefaultTableModel tblModelCTHD;
	JScrollPane scrTBLCTHD;
	JPanel panelCTHD;

	// Attribute Panel Purchase
	JPanel panelThanhToan;
	JButton btnThanhToan, btnHuyThanhToan, btnThemHangCho;
	JLabel lblTongSoLuongSP, lblTongCong, lblTienGiam, lblTienPhaiThanhToan, lblTienNhan, lblTienTra;
	JTextField txtTongSoLuongSP, txtTongCong, txtTienGiam, txtTienPhaiThanhToan, txtTienNhan, txtTienTra;

	// Attribute Panel Search Product
	JPanel panelTimKiemSP;
	JLabel lblTenSP, lblMauSac, lblKichCo;
	JTextField txtTenSP;
	JComboBox listMauSac, listKichCo;
	DefaultComboBoxModel<String> listModelMauSac, listModelKichCo;
	JScrollPane scrListMauSac, scrListKichCo, scrTBLTimKiemSP, scrTBLTimKiemCTSP;
	JButton btnTimKiemSP, btnLamMoi, btnThemVaoHoaDon;
	JTable tblTimKiemSP;
	JTable tblTimKiemCTSP;
	DefaultTableModel tblModelTimKiemSP;
	DefaultTableModel tblModelTimKiemCTSP;

	// DAO
	SanPham_DAO sanPham_DAO;
	ChiTietSanPham_DAO chiTietSanPham_DAO;
	HoaDon_DAO hoaDon_DAO;
	ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	KhachHang_DAO khachHang_DAO;

	// Entity
	NhanVien nvHD = null;
	KhachHang khachHang = null;

	// Array Entity
	ArrayList<ChiTietHoaDon> chiTietHoaDonArray = new ArrayList<ChiTietHoaDon>();

	// Popup Menu
	JPopupMenu pmnHoaDon;
	JMenuItem mniXoa,mniSuaSoLuong;

	// Attribute Panel Pending
	JScrollPane scrPanelHangCho;
	JTable[] tblHangCho = new JTable[100];
	DefaultTableModel[] tblModelHangCho = new DefaultTableModel[100];
	JButton[] btnThoatHangCho = new JButton[100];
	JButton[] btnXoaHoaDonTrongHangCho = new JButton[100];
	JPanel[] panelArr = new JPanel[100];
	ArrayList<ChiTietHoaDon> hangChoArray;
	int soHangCho = 0;
	JPanel panelHangCho;
	String[] header = {"Mã", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
	JLabel lblTieuDeHangCho;
	
	public GUI_TaoHoaDon_2(JFrame frame, NhanVien nvInfo) {
		super();
		
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		nvHD = nvInfo;
		createGUI(frame);
		loadAllDataSanPham();
	}

	void createGUI(JFrame frame) {
		
		int width = frame.getWidth(), 
				height = frame.getHeight();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JTabbedPane tabbed = new JTabbedPane();
		tabbed.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		add(tabbed);

		JComponent panel1 = taoHoaDonPanel(width, height);
		panel1.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		tabbed.addTab("Tạo Hóa Đơn", null, panel1,
				"Does nothing");
		tabbed.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = hangChoPanel(width, height);
		panel2.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		tabbed.addTab("Hàng Chờ", null, panel2, "Does nothing");
	}

	JComponent hangChoPanel(int width, int height) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(width, height));
		panel.setBackground(new Color(147, 190, 221));
		
		panelHangCho = new JPanel();
		panelHangCho.setLayout(new BoxLayout(panelHangCho, BoxLayout.Y_AXIS));
		panelHangCho.setBackground(new Color(147, 190, 221));
		scrPanelHangCho = new JScrollPane(panelHangCho, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrPanelHangCho.setPreferredSize(new Dimension(width, height));
		panel.add(scrPanelHangCho);

		return panel;
	}

	JComponent taoHoaDonPanel(int width, int height) {
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(width, height));

		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		panel.add(panelLeft);
		int wPL = width * 2 / 5, hPL = height;
		panelLeft.setPreferredSize(new Dimension(wPL, hPL));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		// Attribute Panel Customer
		JPanel panelThongTinKhachHang = new JPanel();
		panelThongTinKhachHang.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelLeft.add(panelThongTinKhachHang);
		panelThongTinKhachHang.setBorder(BorderFactory.createTitledBorder("Khách Hàng"));
		panelThongTinKhachHang.setLayout(new BoxLayout(panelThongTinKhachHang, BoxLayout.Y_AXIS));
		panelThongTinKhachHang.setPreferredSize(new Dimension(wPL,hPL / 5));
		panelThongTinKhachHang.setBackground(new Color(147, 190, 221));

		panelThongTinKhachHang.add(Box.createVerticalStrut(15));
		Box bRowKH1 = Box.createHorizontalBox();
		bRowKH1.add(lblSDTKH = new JLabel("Số Điện Thoại"));
		bRowKH1.add(Box.createHorizontalStrut(10));
		bRowKH1.add(txtSDTKH = new JTextField(30));
		bRowKH1.add(lblNgaySinh = new JLabel("Ngày Sinh"));
		bRowKH1.add(Box.createHorizontalStrut(10));
		bRowKH1.add(txtNgaySinhKH = new JTextField(30));
		txtNgaySinhKH.setEditable(false);
		panelThongTinKhachHang.add(bRowKH1);
		panelThongTinKhachHang.add(Box.createVerticalStrut(15));

		Box bRowKH2 = Box.createHorizontalBox();
		bRowKH2.add(lblTenKH = new JLabel("Họ Và Tên"));
		bRowKH2.add(Box.createHorizontalStrut(10));
		bRowKH2.add(txtTenKH = new JTextField(30));
		txtTenKH.setEditable(false);
		bRowKH2.add(lblCapDoKH = new JLabel("Cấp Độ"));
		bRowKH2.add(Box.createHorizontalStrut(10));
		bRowKH2.add(txtCapDoKH = new JTextField(30));
		txtCapDoKH.setEditable(false);
		panelThongTinKhachHang.add(bRowKH2);
		panelThongTinKhachHang.add(Box.createVerticalStrut(15));

		Box bRowKH3 = Box.createHorizontalBox();
		bRowKH3.add(btnTimKiemKH = new JButton("Tìm Kiếm"));
		bRowKH3.add(Box.createHorizontalStrut(50));
		bRowKH3.add(btnTaoKH = new JButton("Thêm Mới"));
		panelThongTinKhachHang.add(bRowKH3);
		panelThongTinKhachHang.add(Box.createVerticalStrut(15));

		lblTenKH.setPreferredSize(lblSDTKH.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblSDTKH.getPreferredSize());
		lblCapDoKH.setPreferredSize(lblSDTKH.getPreferredSize());

		// Panel Order Detail
		tblModelCTHD = new DefaultTableModel(new Object[] {"Mã", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0);
		tblCTHD = new JTable(tblModelCTHD);
		tblCTHD.setIntercellSpacing(new Dimension(10, 10));
		tblCTHD.setRowHeight(30);
		tblCTHD.setDefaultEditor(Object.class, null);
		scrTBLCTHD = new JScrollPane(tblCTHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTBLCTHD.setPreferredSize(new Dimension(wPL, hPL * 2 / 5) );
		panelLeft.add(scrTBLCTHD);
		
		TableColumnModel columnMod = tblCTHD.getColumnModel();
		TableColumn TC_ProductName = columnMod.getColumn(1);
		TC_ProductName.setPreferredWidth(300);
		
		// Panel Purchase
		panelLeft.add(panelThanhToan = new JPanel());
		panelThanhToan.setBorder(BorderFactory.createTitledBorder("Thanh Toán"));
		panelThanhToan.setLayout(new BoxLayout(panelThanhToan, BoxLayout.Y_AXIS));
		panelThanhToan.setPreferredSize(new Dimension(wPL,hPL / 5));
		panelThanhToan.setBackground(new Color(147, 190, 221));

		panelThanhToan.add(Box.createVerticalStrut(15));
		Box bRowTT1 = Box.createHorizontalBox();
		bRowTT1.add(lblTongCong = new JLabel("Tổng Cộng"));
		bRowTT1.add(txtTongCong = new JTextField(30));
		txtTongCong.setText("0");
		bRowTT1.add(lblTongSoLuongSP = new JLabel("Tổng Số Lượng Sản Phẩm"));
		bRowTT1.add(txtTongSoLuongSP = new JTextField(30));
		txtTongSoLuongSP.setText("0");
		panelThanhToan.add(bRowTT1);
		panelThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT2 = Box.createHorizontalBox();
		bRowTT2.add(lblTienPhaiThanhToan = new JLabel("Tiền Thanh Toán"));
		bRowTT2.add(txtTienPhaiThanhToan = new JTextField(30));
		txtTienPhaiThanhToan.setText("0");
		bRowTT2.add(lblTienGiam = new JLabel("Tiền Giảm"));
		bRowTT2.add(txtTienGiam = new JTextField(30));
		txtTienGiam.setText("0");
		panelThanhToan.add(bRowTT2);
		panelThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT3 = Box.createHorizontalBox();
		bRowTT3.add(lblTienNhan = new JLabel("Tiền Nhận"));
		bRowTT3.add(txtTienNhan = new JTextField(30));
		txtTienNhan.setText("0");
		bRowTT3.add(lblTienTra = new JLabel("Tiền Trả"));
		bRowTT3.add(txtTienTra = new JTextField(30));
		txtTienTra.setText("0");
		panelThanhToan.add(bRowTT3);
		panelThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT4 = Box.createHorizontalBox();
		bRowTT4.add(btnThanhToan = new JButton("Thanh Toán"));
		bRowTT4.add(Box.createHorizontalStrut(50));
		bRowTT4.add(btnHuyThanhToan = new JButton("Hủy Thanh Toán"));
		bRowTT4.add(Box.createHorizontalStrut(50));
		bRowTT4.add(btnThemHangCho = new JButton("Thêm Vào Hàng Chờ"));
		panelThanhToan.add(bRowTT4);
		panelThanhToan.add(Box.createVerticalStrut(15));

		lblTongCong.setPreferredSize(lblTongSoLuongSP.getPreferredSize());
		lblTienNhan.setPreferredSize(lblTongSoLuongSP.getPreferredSize());
		lblTienGiam.setPreferredSize(lblTongSoLuongSP.getPreferredSize());
		lblTienPhaiThanhToan.setPreferredSize(lblTongSoLuongSP.getPreferredSize());
		lblTienTra.setPreferredSize(lblTongSoLuongSP.getPreferredSize());

		txtTongCong.setEditable(false); 
		txtTienTra.setEditable(false);
		txtTongSoLuongSP.setEditable(false);
		txtTienGiam.setEditable(false);
		txtTienPhaiThanhToan.setEditable(false);
		txtTienPhaiThanhToan.setBackground(Color.green);
		txtTienTra.setBackground(Color.yellow);

		JPanel panelRight;
		panel.add(panelRight = new JPanel());
		int wPR = width * 2 / 5, hPR = height;
		panelRight.setPreferredSize(new Dimension(wPR, hPR));
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelRight.setBackground(new Color(147, 190, 221));

		// Panel Search Product
		panelRight.add(panelTimKiemSP = new JPanel());
		panelTimKiemSP.setLayout(new BoxLayout(panelTimKiemSP, BoxLayout.Y_AXIS));
		panelTimKiemSP.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm Sản Phẩm"));
		panelTimKiemSP.setPreferredSize(new Dimension(wPR, hPR / 6));
		panelTimKiemSP.setBackground(new Color(147, 190, 221));

		panelTimKiemSP.add(Box.createVerticalStrut(15));
		Box bRowTKSP1 = Box.createHorizontalBox();
		bRowTKSP1.add(lblTenSP = new JLabel("Tên Sản Phẩm"));
		bRowTKSP1.add(Box.createHorizontalStrut(10));
		bRowTKSP1.add(txtTenSP = new JTextField(30));
		panelTimKiemSP.add(bRowTKSP1);
		panelTimKiemSP.add(Box.createVerticalStrut(15));

		Box bRowTKSP2 = Box.createHorizontalBox();

		listModelMauSac = new DefaultComboBoxModel();
		listMauSac = new JComboBox<>(listModelMauSac);
		scrListMauSac = new JScrollPane(listMauSac, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		listModelKichCo = new DefaultComboBoxModel();
		listKichCo = new JComboBox<>(listModelKichCo);
		scrListKichCo = new JScrollPane(listKichCo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		bRowTKSP2.add(lblMauSac = new JLabel("Màu Sắc"));
		bRowTKSP2.add(Box.createHorizontalStrut(25));
		bRowTKSP2.add(listMauSac);
		bRowTKSP2.add(lblKichCo = new JLabel("Kích Cỡ"));
		bRowTKSP2.add(Box.createHorizontalStrut(10));
		bRowTKSP2.add(listKichCo);
		bRowTKSP2.setPreferredSize(
			new Dimension(
				bRowTKSP2.getPreferredSize().width,
				20
			)
		);
		panelTimKiemSP.add(bRowTKSP2);
		panelTimKiemSP.add(Box.createVerticalStrut(10));

		// lblMauSac.setPreferredSize(lblTenSP.getPreferredSize());
		// lblKichCo.setPreferredSize(lblTenSP.getPreferredSize());

		Box bRowTKSP3 = Box.createHorizontalBox();
		bRowTKSP3.add(btnTimKiemSP = new JButton("Tìm Kiếm"));
		bRowTKSP3.add(Box.createHorizontalStrut(50));
		bRowTKSP3.add(btnLamMoi = new JButton("Làm Mới"));
		panelTimKiemSP.add(bRowTKSP3);
		panelTimKiemSP.add(Box.createVerticalStrut(15));

		tblModelTimKiemSP = new DefaultTableModel(new Object[] {"Mã", "Tên"}, 0);	
		tblTimKiemSP = new JTable(tblModelTimKiemSP);
		tblTimKiemSP.setIntercellSpacing(new Dimension(20, 1));
		tblTimKiemSP.setDefaultEditor(Object.class, null);
		scrTBLTimKiemSP = new JScrollPane(tblTimKiemSP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTBLTimKiemSP.setPreferredSize(new Dimension(wPR, hPR * 4 / 6));

		panelRight.add(scrTBLTimKiemSP);

		tblModelTimKiemCTSP = new DefaultTableModel(new Object[] {"Kích Cỡ", "Màu Sắc", "Số Lượng", "Giá Bán"}, 0);	
		tblTimKiemCTSP = new JTable(tblModelTimKiemCTSP);
		tblTimKiemCTSP.setIntercellSpacing(new Dimension(20, 1));
		tblTimKiemCTSP.setDefaultEditor(Object.class, null);
		scrTBLTimKiemCTSP = new JScrollPane(tblTimKiemCTSP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTBLTimKiemCTSP.setPreferredSize(new Dimension(wPR, hPR * 4 / 6));

		panelRight.add(scrTBLTimKiemCTSP);

		panelRight.add(btnThemVaoHoaDon = new JButton("Thêm Vào Hóa Đơn"));

		TableColumnModel columnModSearchProductTable = tblTimKiemSP.getColumnModel();
		TableColumn TC_ProductIDSearchProductTable = columnModSearchProductTable.getColumn(0);
		TC_ProductIDSearchProductTable.setPreferredWidth(105);
		
		TableColumn TC_ProductNameSearchProductTable = columnModSearchProductTable.getColumn(1);
		TC_ProductNameSearchProductTable.setPreferredWidth(250);
		
		// TableColumn TC_ProductSizeSearchProductTable = columnModSearchProductTable.getColumn(2);
		// TC_ProductSizeSearchProductTable.setPreferredWidth(55);
		
		// TableColumn TC_ProductQuantitySearchProductTable = columnModSearchProductTable.getColumn(5);
		// TC_ProductQuantitySearchProductTable.setPreferredWidth(60);
		
		// Event
		// Action Listener
		btnTimKiemSP.addActionListener(this);
		btnTimKiemKH.addActionListener(this);
		btnTaoKH.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnHuyThanhToan.addActionListener(this);
		btnThemHangCho.addActionListener(this);
		btnTimKiemSP.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemVaoHoaDon.addActionListener(this);
		// Key Listener
		tblCTHD.addKeyListener(this);
		txtTienNhan.addKeyListener(this);
		// Mouse Listener
		tblCTHD.addMouseListener(this);
		tblTimKiemSP.addMouseListener(this);

		return panel;
	}

	void updateOrder() {
		
		int soLuongSP = 0;
		double tongTien = 0;
		for(int i = 0; i < tblCTHD.getRowCount(); i++) {
			soLuongSP += Integer.parseInt(tblCTHD.getValueAt(i, 2).toString());
			tongTien += Double.parseDouble(tblCTHD.getValueAt(i, 4).toString());
		}
		txtTongSoLuongSP.setText("" + String.valueOf(soLuongSP));
		txtTongCong.setText("" + String.valueOf(tongTien));

		if (txtCapDoKH.getText().equalsIgnoreCase("")){
			txtTienGiam.setText("0");
		} else if (txtCapDoKH.getText().equalsIgnoreCase("Thành Viên")) {
			double tienGiam = (tongTien * 0.05);
			txtTienGiam.setText("" + tienGiam);
		} else if (txtCapDoKH.getText().equalsIgnoreCase("Thành Viên Bạc")) {
			double tienGiam = (tongTien * 0.1);
			txtTienGiam.setText("" + tienGiam);
		} else if (txtCapDoKH.getText().equalsIgnoreCase("Thành Viên Vàng")) {
			double tienGiam = (tongTien * 0.15);
			txtTienGiam.setText("" + tienGiam);
		}

		double tienPhaiThanhToan = Double.parseDouble(txtTongCong.getText()) - Double.parseDouble(txtTienGiam.getText());

		txtTienPhaiThanhToan.setText("" + tienPhaiThanhToan);

	}

	void removeAllRow(DefaultTableModel tableModel) {
		
		while(tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	public static boolean isNumeric(String strNum) {
    if (strNum == null) return false;
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
	}

	boolean checkInputData() {
		String sDT = txtSDTKH.getText();
		String tenSP = txtTenSP.getText();
		String tienNhan = txtTienNhan.getText();
		if (!(sDT.length() > 0) && sDT.matches("^[0]{1}\\d{9,10}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải bao gồm 10 số và bắt đầu bằng số 0");
			return false;
		} else if (!(tenSP.length() > 0) && tenSP.matches("^[A-Za-z]{2,6}(\\s[A-Za-z0-9]*)*")){
			JOptionPane.showMessageDialog(null, "Tên Sản Phẩm Bắt Đầu Bằng Chữ Và Không Có Kí Tự Đặt Biệt");
			return false;
		} else if (!(tienNhan.length() > 0) && tienNhan.matches("^[1-9][0-9]*")) {
			JOptionPane.showMessageDialog(null, "Tiền Nhận Không Được Rỗng Và Không Được Chứa Chữ Và Phải Lớn Hơn 1000");
			return false;
		}
		return true;
	}

	void loadAllDataSanPham() {
		sanPham_DAO = new SanPham_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();

		tblModelTimKiemSP.setRowCount(0);
		tblModelTimKiemCTSP.setRowCount(0);

		for(SanPham sp : sanPham_DAO.getTopSanPham()) {
			tblModelTimKiemSP.addRow( new Object[] {
				sp.getMaSP(), 
				sp.getTenSP()
			});
		}

		ChiTietSanPham_DAO ctsp_dao = new ChiTietSanPham_DAO();
		listModelMauSac.addElement("Khong Chon");
		listModelKichCo.addElement("Khong Chon");
		for (String mauSac : ctsp_dao.getAllMauSac()) {
			listModelMauSac.addElement(mauSac);
		}
		for (String kichCo : ctsp_dao.getAllKichCo()) {
			listModelKichCo.addElement(kichCo);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if (o.equals(btnTimKiemKH)) {
			if (!txtSDTKH.getText().isBlank()) {
				timKhachHangAction();
			} else {
				JOptionPane.showMessageDialog(this, "Phải nhập số điện thoại khách hàng");
			}
		} else if (o.equals(btnTaoKH)) {
			new Form_TaoKhachHang(txtSDTKH.getText()).setVisible(true);
		} else if (o.equals(btnTimKiemSP)) {
			timSanPhamAction();
		} else if (o.equals(btnLamMoi)) {
			lamMoiTimKiemSanPhamAction();
		} else if (o.equals(btnThemVaoHoaDon)) {
			themVaoHoaDonAction();
		} else if (o.equals(btnThanhToan)) {
			try {
				thanhToanAction();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnHuyThanhToan)) {
			huyThanhToanAction();
		} else if (o.equals(mniXoa)) {
			int row = tblCTHD.getSelectedRow();
			tblModelCTHD.removeRow(row);
			updateOrder();
		} else if (o.equals(mniSuaSoLuong)) {
			int row2 = tblCTHD.getSelectedRow();
			String soLuong1 = JOptionPane.showInputDialog(null, "Nhập số lượng: ");
			tblCTHD.setValueAt(soLuong1, row2, 2);
			String a = tblCTHD.getValueAt(row2, 3).toString();
			tblCTHD.setValueAt(String.valueOf(Double.parseDouble(soLuong1) * Double.parseDouble(a)), row2, 4);
			updateOrder();
		} else if (o.equals(btnThemHangCho)) {
			themVaoHangChoAction();
		}
		
		for(int i = 0; i < btnThoatHangCho.length; i++){
			if (o.equals(btnThoatHangCho[i])) {
				for(int j = 0; j < tblModelHangCho[i].getRowCount(); j++) {
					String maSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 0).toString();
					String tenSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 1).toString();
					String soLuongSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 2).toString();
					String donGiaSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 3).toString();
					String thanhTienSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 4).toString();
					
					tblModelCTHD.addRow(new Object[] {maSanPhamTrongHangCho, tenSanPhamTrongHangCho, soLuongSanPhamTrongHangCho, donGiaSanPhamTrongHangCho, thanhTienSanPhamTrongHangCho});
				}
				panelHangCho.remove(panelArr[i]);
			}
		}
		
		for(int i = 0; i < btnXoaHoaDonTrongHangCho.length; i++){
			if (o.equals(btnXoaHoaDonTrongHangCho[i])) {
				panelHangCho.remove(panelArr[i]);
			}
		}
	}

	void themVaoHangChoAction() {
		
		hangChoArray = new ArrayList<ChiTietHoaDon>();
		for(int i = 0; i < tblModelCTHD.getRowCount(); i++) {
			String ma = tblCTHD.getValueAt(i, 0).toString();
			String ten = tblCTHD.getValueAt(i, 1).toString();
			String soLuong = tblCTHD.getValueAt(i, 2).toString();
			String donGia = tblCTHD.getValueAt(i, 3).toString();
			String thanhTien = tblCTHD.getValueAt(i, 4).toString();

			ChiTietHoaDon cthd = new ChiTietHoaDon(new SanPham(ma, ten), 
					Integer.parseInt(soLuong),
					Double.parseDouble(donGia));

			hangChoArray.add(cthd);
		}
		taoHangCho(hangChoArray);
	}
	
	void taoHangCho(ArrayList<ChiTietHoaDon> arrayList) {
		
		int wHangCho = scrPanelHangCho.getWidth() - 20, hHangCho = 150;
		
		panelHangCho.add(panelArr[soHangCho] = new JPanel());

		panelArr[soHangCho].setPreferredSize(new Dimension(wHangCho, hHangCho));

		panelArr[soHangCho].setLayout(new BoxLayout(panelArr[soHangCho], BoxLayout.X_AXIS));

		panelHangCho.add(Box.createVerticalStrut(30));

		JPanel pnlLeftHangCho = new JPanel();
		panelArr[soHangCho].add(pnlLeftHangCho);
		pnlLeftHangCho.setPreferredSize(new Dimension(wHangCho * 4 / 5, 140));
		pnlLeftHangCho.setLayout(new BoxLayout(pnlLeftHangCho, BoxLayout.Y_AXIS));
		
		Date thoiGianThemVaoHangCho = new Date(Calendar.getInstance().getTime().getTime());
		SimpleDateFormat sdfHangCho = new SimpleDateFormat("HH : mm : ss");
		String s = "";
		s += s.format("Hóa đơn số %s %12s %12s", String.valueOf(soHangCho + 1), " ", sdfHangCho.format(thoiGianThemVaoHangCho));
		pnlLeftHangCho.add(lblTieuDeHangCho = new JLabel("" + s));
		
		tblHangCho[soHangCho] = new JTable(tblModelHangCho[soHangCho] = new DefaultTableModel(header, 0));
		for(ChiTietHoaDon cthd : arrayList) {	
			tblModelHangCho[soHangCho].addRow(new Object[] {cthd.getSanPham().getMaSP().toString(), 
			cthd.getSanPham().getTenSP().toString(),
			String.valueOf(cthd.getSoLuong()),
			String.valueOf(cthd.getGiaBan()),
			Double.parseDouble(String.valueOf(cthd.getSoLuong())) * Double.parseDouble(String.valueOf(cthd.getGiaBan()))});
		}
		pnlLeftHangCho.add(new JScrollPane(tblHangCho[soHangCho]));

		JPanel pnlRightHangCho = new JPanel();
		panelArr[soHangCho].add(pnlRightHangCho);
		pnlRightHangCho.setLayout(new BoxLayout(pnlRightHangCho, BoxLayout.X_AXIS));
		pnlRightHangCho.setPreferredSize(new Dimension(wHangCho / 5, 140));
		pnlRightHangCho.add(Box.createVerticalStrut(5));
		pnlRightHangCho.add(btnThoatHangCho[soHangCho] = new JButton("Thoát Hàng Chờ " + String.valueOf(soHangCho + 1)));
		pnlRightHangCho.add(Box.createVerticalStrut(5));
		pnlRightHangCho.add(btnXoaHoaDonTrongHangCho[soHangCho] = new JButton("Xóa Hóa Đơn " + String.valueOf(soHangCho + 1)));
		
		btnThoatHangCho[soHangCho].addActionListener(this);
		btnXoaHoaDonTrongHangCho[soHangCho].addActionListener(this);
		
		soHangCho++;
	}

	void lamMoiTimKiemSanPhamAction() {
		txtTenSP.setText("");
		listKichCo.setSelectedIndex(0);
		listMauSac.setSelectedIndex(0);
		
		loadAllDataSanPham();
	}

	void timSanPhamAction() {
		
		ChiTietSanPham_DAO ctsp_dao = new ChiTietSanPham_DAO();
		String tenSP = txtTenSP.getText();

		String sMauSac = "";
		String[] sMauSacArr = null;
		String rsMauSac = "";
		if (listMauSac.getSelectedIndex() != 0) {
			if ( listMauSac.getSelectedItem().toString().length() != 0 ) {
				sMauSac += "mauSac = '" + listMauSac.getSelectedItem().toString() + "', ";
				sMauSacArr = sMauSac.split("\\,");

				rsMauSac = rsMauSac.join(" or ", sMauSacArr);
				rsMauSac = rsMauSac.substring(0, rsMauSac.length() - 5);
			} else {
				rsMauSac += "mauSac is not null";
			}
		} else {
			rsMauSac += "mauSac is not null";
		}

		String sKichCo = "";
		String[] sKichCoArr = null;
		String rsKichCo = "";
		if (listKichCo.getSelectedIndex() != 0) {
			if ( listKichCo.getSelectedItem().toString().length() != 0 ) {
				sKichCo += "kichCo = '" + listKichCo.getSelectedItem().toString() + "', ";	
				sKichCoArr = sKichCo.split("\\,");

				rsKichCo = rsKichCo.join(" or ", sKichCoArr);
				rsKichCo = rsKichCo.substring(0, rsKichCo.length() - 5);
			} else {
				rsKichCo += "kichCo is not null";
			}
		} else {
			rsKichCo += "kichCo is not null";
		}

		ArrayList<SanPham> spList = new ArrayList<SanPham>();
		spList = ctsp_dao.getAllSanPhamTheoMauSacVaKichCo(rsMauSac, rsKichCo);

		tblModelTimKiemSP.setRowCount(0);
		for (SanPham ctsp : spList) {
			tblModelTimKiemSP.addRow(new Object[] {
				ctsp.getMaSP(),
				ctsp.getTenSP(),
			});
		}
		tblModelTimKiemCTSP.setRowCount(0);
	}

	void timKhachHangAction() {
		
		String sDT = txtSDTKH.getText();

		if ((sDT.length() > 0) && sDT.matches("^[0]{1}\\d{9}$")) {
			khachHang_DAO = new KhachHang_DAO();
			khachHang = khachHang_DAO.timKhachHangTheoSDT(sDT);

			if (khachHang != null) {
				txtNgaySinhKH.setText("" + khachHang.getNgaySinh());
				txtTenKH.setText("" + khachHang.getTenKhachHang());
		
				if (0 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 1000000) {
					txtCapDoKH.setText("Thành Viên");
				} else if (1000000 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 5000000) {
					txtCapDoKH.setText("Thành Viên Bạc");
				} else if (5000000 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 10000000) {
					txtCapDoKH.setText("Thành Viên Vàng");
				}
				updateOrder();
			} else {
				int option = JOptionPane.showConfirmDialog(null, "Không có khách hàng có số điện thoại là " + txtSDTKH.getText() + " trong cửa hàng. Bạn có muốn thêm khách hàng mới không?");

				if (option == JOptionPane.YES_OPTION) {
					new Form_TaoKhachHang(txtSDTKH.getText()).setVisible(true); 
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải bao gồm 10 số và bắt đầu bằng số 0");
			txtSDTKH.setText("");
		}
	}

	void themVaoHoaDonAction() {
		
		int row = tblTimKiemSP.getSelectedRow();
		int rowCTSP = tblTimKiemCTSP.getSelectedRow();
		
		if (row >= 0) {
			String maSP = tblTimKiemSP.getValueAt(row, 0).toString();
			String tenSP = tblTimKiemSP.getValueAt(row, 1).toString();
			if (rowCTSP >= 0) {
				String soLuongSanPhamTonKho = tblTimKiemCTSP.getValueAt(rowCTSP, 2).toString();
				String soLuong = JOptionPane.showInputDialog(null, "Nhập Số Lượng: ");
				if (soLuong != null) {
					if (isNumeric(soLuong)) {
						if (Integer.parseInt(soLuong) > 0) {
							if (Integer.parseInt(soLuong) <= Integer.parseInt(soLuongSanPhamTonKho)) {
								String kichCo = tblTimKiemCTSP.getValueAt(rowCTSP, 0).toString();
								String mauSac = tblTimKiemCTSP.getValueAt(rowCTSP, 1).toString();
								String donGia = tblTimKiemCTSP.getValueAt(rowCTSP, 3).toString();
				
								// The product's name will be displayed in Order Detail Table
								String tenSanPhamTrongHoaDon = tenSP + " - " + kichCo + " - " + mauSac;
								
								// Check Product Exist In Order Detail Table
								// If Product exits, Order Detail Table Will Update Quantity Of This Quantity
								int flag = 0;
								for(int i = 0; i < tblModelCTHD.getRowCount(); i++) {
									
									String tenSanPhamCanKiemTra = tblCTHD.getValueAt(i, 1).toString();
									if (tenSanPhamTrongHoaDon.equals(tenSanPhamCanKiemTra)) {
										// Update Quantity And Total On Order Detail Table
										flag = 1;
										String soLuongSanPhamTrongHoaDon = tblCTHD.getValueAt(i, 2).toString();
										tblCTHD.setValueAt(String.valueOf(Integer.parseInt(soLuongSanPhamTrongHoaDon) + Integer.parseInt(soLuong)) , i, 2);
										
										tblCTHD.setValueAt(String.valueOf(Double.parseDouble(tblCTHD.getValueAt(i, 2).toString()) * Double.parseDouble(tblCTHD.getValueAt(i, 3).toString())) , i, 4);
									
										// Update Quantity In Order Detail Array 
										for(ChiTietHoaDon chiTietHoaDon : chiTietHoaDonArray) {
											if (chiTietHoaDon.getSanPham().getTenSP().equals(tenSP) && chiTietHoaDon.getKichCo().equals(kichCo) && chiTietHoaDon.getMauSac().equals(mauSac)) {
												chiTietHoaDon.setSoLuong(Integer.parseInt(tblCTHD.getValueAt(i, 2).toString()));
											}
										}
									}
								}
								
								// If Order Detail Table doesn't have product which the customer wants to buy, Order Detail Table will insert that product
								if (flag == 0) {
									Object[] rowHD = {maSP, tenSanPhamTrongHoaDon, soLuong, donGia, String.valueOf(Double.parseDouble(soLuong) * Double.parseDouble(donGia) )};
									tblModelCTHD.addRow(rowHD);
									ChiTietHoaDon cthd = new ChiTietHoaDon(new SanPham(maSP, tenSP), kichCo, mauSac, Integer.parseInt(soLuong), Double.parseDouble(donGia)); 
									chiTietHoaDonArray.add(cthd);
								}
								
								updateOrder();
							} else {
								JOptionPane.showMessageDialog(null, "Số Lượng Mua Không được Vượt Quá Số Lượng Tồn Kho");
								soLuong = "";
							} 
						} else {
							JOptionPane.showMessageDialog(null, "Số lượng chi tiết sản phẩm phải lớn hơn 0");
							soLuong = "";
						}
					} else {
						JOptionPane.showMessageDialog(null, "Phải nhập kiểu dữ liệu số");
						soLuong = "";
					}
				} else {
					JOptionPane.showMessageDialog(null, "Phải nhập số lượng mua");
					soLuong = "";
				}
			} else {
				JOptionPane.showMessageDialog(null, "Phai chọn chi tiết sản phẩm");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phải chọn sản phẩm cần mua");
		}
	}

	void huyThanhToanAction() {
		
		txtSDTKH.setText("");
		txtNgaySinhKH.setText("");
		txtTenKH.setText("");
		txtCapDoKH.setText("");

		txtTenSP.setText("");
		listKichCo.setSelectedIndex(0);
		listMauSac.setSelectedIndex(0);
		removeAllRow(tblModelCTHD);
		txtTienNhan.setText("0");
		txtTienTra.setText("0");
	}

	void thanhToanAction() throws DocumentException {
		
		int caLamViec;
		khachHang_DAO = new KhachHang_DAO();	
		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();
		hoaDon_DAO = new HoaDon_DAO();

		double tienNhan = Double.parseDouble(txtTienNhan.getText());
		double tongCong = Double.parseDouble(txtTongCong.getText());
		double tienTra = Double.parseDouble(txtTienTra.getText());
		double tienGiam = Double.parseDouble(txtTienGiam.getText());
		double tienThanhToan = Double.parseDouble(txtTienPhaiThanhToan.getText());

		String tenKH = txtTenKH.getText();
		
		if (!tenKH.isBlank()) {
			if (tblCTHD.getRowCount() > 0) {
				if (tienTra >=0 && tienNhan >= tienThanhToan) {
					String maHoaDon = "HD";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH mm ss");
					SimpleDateFormat sdfID = new SimpleDateFormat("ssmmhhddMMyyyy");
					String time = sdf.format(new Date(Calendar.getInstance().getTime().getTime()));
					maHoaDon += sdfID.format(new Date(Calendar.getInstance().getTime().getTime()));
		
					Date currentTime = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					String now = sdfTime.format(currentTime);
		
					String chinGio = sdfTime.format(new java.util.Date(Calendar.getInstance().getTime().getDate(), 
							Calendar.getInstance().getTime().getMonth(), 
							Calendar.getInstance().getTime().getYear(), 9, 00, 00));
		
					String muoiSauGio = sdfTime.format(new java.util.Date(Calendar.getInstance().getTime().getDate(), 
							Calendar.getInstance().getTime().getMonth(), 
							Calendar.getInstance().getTime().getYear(), 16, 00, 00));
		
					String haiMuoiHaiGio = sdfTime.format(new java.util.Date(Calendar.getInstance().getTime().getDate(), 
							Calendar.getInstance().getTime().getMonth(), 
							Calendar.getInstance().getTime().getYear(), 22, 00, 00));
		
					if (now.compareTo(muoiSauGio) <= 0 && now.compareTo(chinGio) > 0) {
						caLamViec = 1;
					} else {
						caLamViec = 2;
					}
		
					HoaDon hd = new HoaDon(
						maHoaDon, 
						currentTime,
						new NhanVien(nvHD.getMaNhanVien()),
						new KhachHang(khachHang.getMaKhachHang()),
						caLamViec, 
						tongCong, 
						tienGiam, 
						tienThanhToan, 
						tienNhan, 
						tienTra
					);
		
					hoaDon_DAO.themHoaDon(hd);

					JOptionPane.showMessageDialog(null, "Thanh toán thành công");
		
					int diemTichLuy = (int)tienThanhToan/1000;
		
					KhachHang khachHangTichLuy = new KhachHang(khachHang.getMaKhachHang(), Math.round(diemTichLuy));
					khachHang_DAO.congDiemTichLuy(khachHangTichLuy);
		
					// Save
					for(ChiTietHoaDon cthd : chiTietHoaDonArray) {
						ChiTietHoaDon cTHD = new ChiTietHoaDon(
							new HoaDon(
								hd.getMaHoaDon()
							), 
							new SanPham(
								cthd.getSanPham().getMaSP(), 
								cthd.getSanPham().getTenSP()
							),
							cthd.getKichCo(),
							cthd.getMauSac(), 
							cthd.getSoLuong(), 
							cthd.getGiaBan()
						);
		
						if (chiTietHoaDon_DAO.themChiTietHoaDon(cTHD)) {

						} else {
							
						}
						if (
							chiTietSanPham_DAO.updataChiTietSanPhamTheoMa(
								new ChiTietSanPham(
									new SanPham(
										cthd.getSanPham().getMaSP()
									), 
									cthd.getKichCo(), 
									cthd.getMauSac(), 
									cthd.getSoLuong()
								)
							)
						) {

						} else {

						}
					}
		
					// Print Order Action
					int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn");
		
					if (option == JOptionPane.YES_OPTION) {
						OrderSample os = new OrderSample();
		
						// Order Info include order ID, Sale ID, create Date
						os.addOrderInfo(time + " ", maHoaDon + " ", nvHD.getMaNhanVien());
		
						for(int i = 0; i < tblCTHD.getRowCount(); i++) {
							String maSP = tblCTHD.getValueAt(i, 0).toString();
							String tenSP = tblCTHD.getValueAt(i, 1).toString();
							String soLuong = tblCTHD.getValueAt(i, 2).toString();
							String donGia = tblCTHD.getValueAt(i, 3).toString();
							String thanhTien = tblCTHD.getValueAt(i, 4).toString();
		
							// Insert Order Detail
							os.addCellOrderDetail(tenSP, maSP, soLuong, donGia, thanhTien);
						}
						// Insert Order Purchase Info
						os.addPurchaseInfo(txtTongCong.getText()+ " ", 
								txtTienGiam.getText() + " ",
								txtTienPhaiThanhToan.getText() + " ",
								txtTienNhan.getText() + " ", 
								txtTienTra.getText() + " ");
						// Create Order
						os.printOrder(maHoaDon + "");
	
						JOptionPane.showMessageDialog(this, "In hoá đơn thành công");
		
						// Display Order File
						try {
							Thread.sleep(500);
							Runtime.getRuntime().exec(
								"rundll32 url.dll, FileProtocolHandler " + 
								new File("./orders/" + maHoaDon + ".pdf").getAbsolutePath()
							);
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					removeAllRow(tblModelTimKiemSP);
					loadAllDataSanPham();
					removeAllRow(tblModelCTHD);
					txtNgaySinhKH.setText("");
					txtSDTKH.setText("");
					txtCapDoKH.setText("");
					txtSDTKH.setText("");
		
					txtTienNhan.setText("");
		
					txtTongCong.setText("0");
					txtTienGiam.setText("0");
					txtTienPhaiThanhToan.setText("0");
					txtTienTra.setText("0");
					txtTongSoLuongSP.setText("0");
				} else {
					JOptionPane.showMessageDialog(null, "Tiền Nhận Không Được Bé Hơn Tổng Cộng");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Phải có thông tin sản phẩm để thanh toán");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phải có thông tin khách hàng");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Object o = e.getKeyCode();
		if (o.equals(KeyEvent.VK_DELETE)) {
			int row = tblCTHD.getSelectedRow();
			tblModelCTHD.removeRow(row);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		Object o = e.getSource();
		if (o.equals(txtTienNhan)) {
			String tienNhan = txtTienNhan.getText();
			if (isNumeric(tienNhan)) {
				if (Integer.parseInt(tienNhan) > 0) {
					String tienThanhToan = txtTienPhaiThanhToan.getText();
					double tienTra = Double.parseDouble(tienNhan) - Double.parseDouble(tienThanhToan); // Error empty string
					txtTienTra.setText("" + tienTra);
				} else {
					JOptionPane.showMessageDialog(null, "Tiền nhận phải lớn hơn 0");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Tiền nhận phải là kiểu số");
			}
		} 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		Object o = e.getSource();
		int row = tblCTHD.getSelectedRow();
		if (o.equals(tblCTHD)) {
			if (e.isPopupTrigger()) {
				if (row > -1) {
					pmnHoaDon = new JPopupMenu();
					// add menu items to pmnHoaDon
					pmnHoaDon.add(mniXoa= new JMenuItem("Xoá"));
					pmnHoaDon.add(mniSuaSoLuong= new JMenuItem("Sửa"));

					pmnHoaDon.show(e.getComponent(), e.getX(), e.getY());
					mniXoa.addActionListener(this);
					mniSuaSoLuong.addActionListener(this);
				}
			}
		} else if (o.equals(tblTimKiemSP)) {
			tblModelTimKiemCTSP.setRowCount(0);

			int rowCTSP = tblTimKiemSP.getSelectedRow();

			ChiTietSanPham_DAO ctsp_DAO = new ChiTietSanPham_DAO();

			for (ChiTietSanPham ctsp : ctsp_DAO.getAllChiTietSanPhamTheoMaSanPham(tblTimKiemSP.getValueAt(rowCTSP, 0) + "")) {
				// System.out.println("1 " + ctsp);
				if (listMauSac.getSelectedIndex() != 0 && listKichCo.getSelectedIndex() != 0) {
					if (ctsp.getMauSac().equals(listMauSac.getSelectedItem()) && ctsp.getKichCo().equals(listKichCo.getSelectedItem())) {
						Object ctspRow[] = {
							ctsp.getKichCo(),
							ctsp.getMauSac(),
							ctsp.getSoLuong() + "",
							ctsp.getGiaBan() + ""
						};
						tblModelTimKiemCTSP.addRow(ctspRow);
					}
				} else if (listMauSac.getSelectedIndex() == 0 && listKichCo.getSelectedIndex() == 0) {
					System.out.println("! !");
					Object ctspRow[] = {
						ctsp.getKichCo(),
						ctsp.getMauSac(),
						ctsp.getSoLuong() + "",
						ctsp.getGiaBan() + ""
					};
					tblModelTimKiemCTSP.addRow(ctspRow);
				} else if (listMauSac.getSelectedIndex() == 0 && listKichCo.getSelectedIndex() != 0) {
					if (ctsp.getKichCo().equals(listKichCo.getSelectedItem())) {
						Object ctspRow[] = {
							ctsp.getKichCo(),
							ctsp.getMauSac(),
							ctsp.getSoLuong() + "",
							ctsp.getGiaBan() + ""
						};
						tblModelTimKiemCTSP.addRow(ctspRow);
					}
				} else if (listMauSac.getSelectedIndex() != 0 && listKichCo.getSelectedIndex() == 0) {
					if (ctsp.getMauSac().equals(listMauSac.getSelectedItem())) {
						Object ctspRow[] = {
							ctsp.getKichCo(),
							ctsp.getMauSac(),
							ctsp.getSoLuong() + "",
							ctsp.getGiaBan() + ""
						};
						tblModelTimKiemCTSP.addRow(ctspRow);
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		Object o =e.getSource();
		if (o.equals(tblCTHD)) {
			int row = tblCTHD.getSelectedRow();
			if (row > -1) {
				if (e.isPopupTrigger()) {
					pmnHoaDon = new JPopupMenu();
					// add menu items to pmnHoaDon
					pmnHoaDon.add(mniXoa= new JMenuItem("Xóa Sản Phẩm Ra Khỏi Hóa Đơn"));
					pmnHoaDon.add(mniSuaSoLuong= new JMenuItem("Sửa Số Lượng"));
					pmnHoaDon.show(e.getComponent(), e.getX(), e.getY());

					mniXoa.addActionListener(this);
					mniSuaSoLuong.addActionListener(this);
				}	
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		

	}
}