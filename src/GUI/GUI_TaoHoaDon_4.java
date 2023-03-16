package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.DocumentException;

import DAO.ChiTietHoaDon_DAO;
import DAO.ChiTietSanPham_DAO;
import DAO.HoaDon_DAO;
import DAO.KhachHang_DAO;
import DAO.SanPham_DAO;
import connect_DB.Connect_DB;
import entity.ChiTietHoaDon;
import entity.ChiTietSanPham;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class GUI_TaoHoaDon_Demo_4 extends JPanel implements ActionListener, KeyListener, MouseListener {
	// Attribute Panel Customer
	private JLabel lblSDTKH, lblTenKhachHang, lblNgaySinhKhachHang, lblCapDoKhachHang;
	private JTextField txtSDTKhachHang, txtTenKhachHang, txtNgaySinhKhachHang, txtCapDoKhachHang;
	private JButton btnTimKiemKhachHang, btnTaoKhachHang;

	// Attribute Panel Order Detail
	private JTable tblChiTietHoaDon;
	private DefaultTableModel tblModelChiTietHoaDon;
	private JScrollPane scrTblChiTietHoaDon;

	// Attribute Panel Purchase
	private JPanel pnlThanhToan;
	private JButton btnThanhToan, btnHuyThanhToan, btnThemHangCho;
	private JLabel lblTongSoLuongSanPham, lblTongCong, lblTienGiam, lblTienPhaiThanhToan, lblTienNhan, lblTienTra;
	private JTextField txtTongSoLuongSanPham, txtTongCong, txtTienGiam, txtTienPhaiThanhToan, txtTienNhan, txtTienTra;

	// Attribute Panel Search Product
	private JPanel pnlTimKiemSanPham;
	private JLabel lblMaSanPhamTimKiem, lblMauSac, lblKichCo;
	private JTextField txtMaSanPhamTimKiem;
	private JComboBox cbKichCo, cbMauSac;
	private DefaultComboBoxModel cbModelKichCo, cbModelMauSac;
	private JScrollPane scrListMauSac, scrListKichCo, scrTBLTimKiemSP;
	private JButton btnTimKiemSanPham, btnLamMoiTimKiemSanPham, btnThemVaoHoaDon;
	private JTable tblTimKiemSanPham;
	private DefaultTableModel tblModelTimKiemSanPham;

	// Attribute Panel Pending
	private JScrollPane scrPnlHangCho;
	private JPanel panelHangCho;
	private JPanel[] pnlArr = new JPanel[100];
	private JTable[] tblHangCho = new JTable[100];
	private DefaultTableModel[] tblModelHangCho = new DefaultTableModel[100];
	private JButton[] btnThoatHangCho = new JButton[100];
	private JButton[] btnXoaHoaDonTrongHangCho = new JButton[100];
	private ArrayList<ChiTietHoaDon> hangChoArray;
	private int soHangCho = 0;


	// DAO
	private SanPham_DAO sanPham_DAO;
	private ChiTietSanPham_DAO chiTietSanPham_DAO;
	private HoaDon_DAO hoaDon_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private KhachHang_DAO khachHang_DAO;

	// Entity
	private NhanVien nvHD = null;
	private KhachHang khachHang = null;

	// Array Entity
	private ArrayList<ChiTietHoaDon> chiTietHoaDonArray = new ArrayList<ChiTietHoaDon>();
	private ArrayList<SanPham> sanPhamArray;
	private ArrayList mauSacArray, kichCoArray;
	private ArrayList<ChiTietHoaDon>  topSanPhamBanChayArray;

	// Pop Up Menu
	private JPopupMenu pmnHoaDon;
	private JMenuItem mniXoa,mniSuaSoLuong;

	public GUI_TaoHoaDon_Demo(JFrame frame, NhanVien nvInfo) {
		// TODO Auto-generated constructor stub
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		nvHD = nvInfo;
		createGUI(frame);
		loadAllDataSanPham();
		loadDataTopSanPham();
	}

	private void createGUI(JFrame frame) {
		// TODO Auto-generated method stub
		int width = frame.getWidth(), height = frame.getHeight();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JTabbedPane tabbed = new JTabbedPane();
		add(tabbed);

		JComponent panelTaoHoaDon = taoHoaDonPanel(width, height);
		tabbed.addTab("Tạo Hóa Đơn", null, panelTaoHoaDon, "Create Order");

		JComponent panelHangCho = hangChoPanel(width, height);
		tabbed.addTab("Hàng Chờ", null, panelHangCho, "Waiting Line");
	}

	private JComponent hangChoPanel(int width, int height) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(width, height));
		panel.setBackground(new Color(147, 190, 221));

		panelHangCho = new JPanel();
		panelHangCho.setLayout(new BoxLayout(panelHangCho, BoxLayout.Y_AXIS));
		panelHangCho.setBackground(new Color(147, 190, 221));
		scrPnlHangCho = new JScrollPane(panelHangCho, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrPnlHangCho.setPreferredSize(new Dimension(width, height));
		panel.add(scrPnlHangCho);

		return panel;
	}

	private JComponent taoHoaDonPanel(int width, int height) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(width, height));

		JPanel panelLeft;
		panel.add(panelLeft = new JPanel());
		int wPL = width * 3 / 5, hPL = height;
		panelLeft.setPreferredSize(new Dimension(wPL, hPL));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		// Attribute Panel Customer
		JPanel pnlThongTinKhachHang;
		panelLeft.add(pnlThongTinKhachHang = new JPanel());
		pnlThongTinKhachHang.setBorder(BorderFactory.createTitledBorder("Khách Hàng"));
		pnlThongTinKhachHang.setLayout(new BoxLayout(pnlThongTinKhachHang, BoxLayout.Y_AXIS));
		pnlThongTinKhachHang.setPreferredSize(new Dimension(wPL,hPL / 5));
		pnlThongTinKhachHang.setBackground(new Color(147, 190, 221));

		pnlThongTinKhachHang.add(Box.createVerticalStrut(15));
		Box bRowKH1 = Box.createHorizontalBox();
		bRowKH1.add(lblSDTKH = new JLabel("Số Điện Thoại"));
		bRowKH1.add(Box.createHorizontalStrut(10));
		bRowKH1.add(txtSDTKhachHang = new JTextField(30));
		bRowKH1.add(lblNgaySinhKhachHang = new JLabel("Ngày Sinh"));
		bRowKH1.add(Box.createHorizontalStrut(10));
		bRowKH1.add(txtNgaySinhKhachHang = new JTextField(30));
		txtNgaySinhKhachHang.setEditable(false);
		pnlThongTinKhachHang.add(bRowKH1);
		pnlThongTinKhachHang.add(Box.createVerticalStrut(15));

		Box bRowKH2 = Box.createHorizontalBox();
		bRowKH2.add(lblTenKhachHang = new JLabel("Họ Và Tên"));
		bRowKH2.add(Box.createHorizontalStrut(10));
		bRowKH2.add(txtTenKhachHang = new JTextField(30));
		txtTenKhachHang.setEditable(false);
		bRowKH2.add(lblCapDoKhachHang = new JLabel("Cấp Độ"));
		bRowKH2.add(Box.createHorizontalStrut(10));
		bRowKH2.add(txtCapDoKhachHang = new JTextField(30));
		txtCapDoKhachHang.setEditable(false);
		pnlThongTinKhachHang.add(bRowKH2);
		pnlThongTinKhachHang.add(Box.createVerticalStrut(15));

		Box bRowKH3 = Box.createHorizontalBox();
		bRowKH3.add(btnTimKiemKhachHang = new JButton("Tìm Kiếm"));
		bRowKH3.add(Box.createHorizontalStrut(50));
		bRowKH3.add(btnTaoKhachHang = new JButton("Thêm Khách Hàng Mới"));
		pnlThongTinKhachHang.add(bRowKH3);
		pnlThongTinKhachHang.add(Box.createVerticalStrut(15));

		lblTenKhachHang.setPreferredSize(lblSDTKH.getPreferredSize());
		lblNgaySinhKhachHang.setPreferredSize(lblSDTKH.getPreferredSize());
		lblCapDoKhachHang.setPreferredSize(lblSDTKH.getPreferredSize());

		// Panel Order Detail
		tblModelChiTietHoaDon = new DefaultTableModel(new Object[] {"Mã", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0);
		tblChiTietHoaDon = new JTable(tblModelChiTietHoaDon);
		scrTblChiTietHoaDon = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTblChiTietHoaDon.setPreferredSize(new Dimension(wPL,hPL * 3 / 5) );
		panelLeft.add(scrTblChiTietHoaDon);

		TableColumnModel columnMod = tblChiTietHoaDon.getColumnModel();
		TableColumn TC_ProductName = columnMod.getColumn(1);
		TC_ProductName.setPreferredWidth(300);

		// Panel Purchase
		panelLeft.add(pnlThanhToan = new JPanel());
		pnlThanhToan.setBorder(BorderFactory.createTitledBorder("Thanh Toán"));
		pnlThanhToan.setLayout(new BoxLayout(pnlThanhToan, BoxLayout.Y_AXIS));
		pnlThanhToan.setPreferredSize(new Dimension(wPL,hPL / 5));
		pnlThanhToan.setBackground(new Color(147, 190, 221));

		pnlThanhToan.add(Box.createVerticalStrut(15));
		Box bRowTT1 = Box.createHorizontalBox();
		bRowTT1.add(lblTongCong = new JLabel("Tổng Cộng"));
		bRowTT1.add(txtTongCong = new JTextField(30));
		txtTongCong.setText("0");
		bRowTT1.add(lblTongSoLuongSanPham = new JLabel("Tổng Số Lượng Sản Phẩm"));
		bRowTT1.add(txtTongSoLuongSanPham = new JTextField(30));
		txtTongSoLuongSanPham.setText("0");
		pnlThanhToan.add(bRowTT1);
		pnlThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT2 = Box.createHorizontalBox();
		bRowTT2.add(lblTienPhaiThanhToan = new JLabel("Tiền Thanh Toán"));
		bRowTT2.add(txtTienPhaiThanhToan = new JTextField(30));
		txtTienPhaiThanhToan.setText("0");
		bRowTT2.add(lblTienGiam = new JLabel("Tiền Giảm"));
		bRowTT2.add(txtTienGiam = new JTextField(30));
		txtTienGiam.setText("0");
		pnlThanhToan.add(bRowTT2);
		pnlThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT3 = Box.createHorizontalBox();
		bRowTT3.add(lblTienNhan = new JLabel("Tiền Nhận"));
		bRowTT3.add(txtTienNhan = new JTextField(30));
		txtTienNhan.setText("0");
		bRowTT3.add(lblTienTra = new JLabel("Tiền Trả"));
		bRowTT3.add(txtTienTra = new JTextField(30));
		txtTienTra.setText("0");
		pnlThanhToan.add(bRowTT3);
		pnlThanhToan.add(Box.createVerticalStrut(15));

		Box bRowTT4 = Box.createHorizontalBox();
		bRowTT4.add(btnThanhToan = new JButton("Thanh Toán"));
		bRowTT4.add(Box.createHorizontalStrut(50));
		bRowTT4.add(btnHuyThanhToan = new JButton("Hủy Thanh Toán"));
		bRowTT4.add(Box.createHorizontalStrut(50));
		bRowTT4.add(btnThemHangCho = new JButton("Thêm Vào Hàng Chờ"));
		pnlThanhToan.add(bRowTT4);
		pnlThanhToan.add(Box.createVerticalStrut(15));

		lblTongCong.setPreferredSize(lblTongSoLuongSanPham.getPreferredSize());
		lblTienNhan.setPreferredSize(lblTongSoLuongSanPham.getPreferredSize());
		lblTienGiam.setPreferredSize(lblTongSoLuongSanPham.getPreferredSize());
		lblTienPhaiThanhToan.setPreferredSize(lblTongSoLuongSanPham.getPreferredSize());
		lblTienTra.setPreferredSize(lblTongSoLuongSanPham.getPreferredSize());

		txtTongCong.setEditable(false); 
		txtTienTra.setEditable(false);
		txtTongSoLuongSanPham.setEditable(false);
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
		panelRight.add(pnlTimKiemSanPham = new JPanel());
		pnlTimKiemSanPham.setLayout(new BoxLayout(pnlTimKiemSanPham, BoxLayout.Y_AXIS));
		pnlTimKiemSanPham.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm Sản Phẩm"));
		pnlTimKiemSanPham.setPreferredSize(new Dimension(wPR, hPR / 10));
		pnlTimKiemSanPham.setBackground(new Color(147, 190, 221));
		pnlTimKiemSanPham.add(Box.createVerticalStrut(15));

		// 
		Box bRowTKSP1 = Box.createHorizontalBox();
		bRowTKSP1.add(lblMaSanPhamTimKiem = new JLabel("Mã Sản Phẩm"));
		bRowTKSP1.add(Box.createHorizontalStrut(10));
		bRowTKSP1.add(txtMaSanPhamTimKiem = new JTextField(30));
		pnlTimKiemSanPham.add(bRowTKSP1);
		pnlTimKiemSanPham.add(Box.createVerticalStrut(15));

		//
		Box bRowTKSP2 = Box.createHorizontalBox();
		bRowTKSP2.add(lblMauSac = new JLabel("Màu Sắc"));
		bRowTKSP2.add(Box.createHorizontalStrut(25));
		bRowTKSP2.add(cbMauSac = new JComboBox(cbModelMauSac = new DefaultComboBoxModel()));
		bRowTKSP2.add(lblKichCo = new JLabel("Kích Cỡ"));
		bRowTKSP2.add(Box.createHorizontalStrut(10));
		bRowTKSP2.add(cbKichCo = new JComboBox(cbModelKichCo = new DefaultComboBoxModel()));
		pnlTimKiemSanPham.add(bRowTKSP2);
		pnlTimKiemSanPham.add(Box.createVerticalStrut(15));

		lblMauSac.setPreferredSize(lblMaSanPhamTimKiem.getPreferredSize());
		lblKichCo.setPreferredSize(lblMaSanPhamTimKiem.getPreferredSize());

		//
		Box bRowTKSP3 = Box.createHorizontalBox();
		bRowTKSP3.add(btnTimKiemSanPham = new JButton("Tìm Kiếm"));
		bRowTKSP3.add(Box.createHorizontalStrut(50));
		bRowTKSP3.add(btnLamMoiTimKiemSanPham = new JButton("Làm Mới"));
		pnlTimKiemSanPham.add(bRowTKSP3);
		pnlTimKiemSanPham.add(Box.createVerticalStrut(15));

		Box bRowTKSP4 = Box.createHorizontalBox();
		tblModelTimKiemSanPham = new DefaultTableModel(new Object[] {"Mã", "Tên", "Kích Cỡ", "Màu Sắc", "Giá Bán", "Số Lượng"}, 0);	
		tblTimKiemSanPham = new JTable(tblModelTimKiemSanPham);
		scrTBLTimKiemSP = new JScrollPane(tblTimKiemSanPham, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		bRowTKSP4.setPreferredSize(new Dimension(wPR, hPR * 7 / 10));
		bRowTKSP4.add(scrTBLTimKiemSP);
		panelRight.add(bRowTKSP4);
		panelRight.add(Box.createVerticalStrut(15));

		panelRight.add(btnThemVaoHoaDon = new JButton("Thêm Vào Hóa Đơn"));
		panelRight.add(Box.createVerticalStrut(15));

		TableColumnModel columnModSearchProductTable = tblTimKiemSanPham.getColumnModel();
		TableColumn TC_ProductIDSearchProductTable = columnModSearchProductTable.getColumn(0);
		TC_ProductIDSearchProductTable.setPreferredWidth(105);

		TableColumn TC_ProductNameSearchProductTable = columnModSearchProductTable.getColumn(1);
		TC_ProductNameSearchProductTable.setPreferredWidth(250);

		TableColumn TC_ProductSizeSearchProductTable = columnModSearchProductTable.getColumn(2);
		TC_ProductSizeSearchProductTable.setPreferredWidth(55);

		TableColumn TC_ProductQuantitySearchProductTable = columnModSearchProductTable.getColumn(5);
		TC_ProductQuantitySearchProductTable.setPreferredWidth(60);

		// Event
		// Action Listener
		btnTimKiemSanPham.addActionListener(this);
		btnTimKiemKhachHang.addActionListener(this);
		btnTaoKhachHang.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnHuyThanhToan.addActionListener(this);
		btnThemHangCho.addActionListener(this);
		btnTimKiemSanPham.addActionListener(this);
		btnLamMoiTimKiemSanPham.addActionListener(this);
		btnThemVaoHoaDon.addActionListener(this);
		// Key Listener
		tblChiTietHoaDon.addKeyListener(this);
		txtTienNhan.addKeyListener(this);
		txtSDTKhachHang.addKeyListener(this);
		txtMaSanPhamTimKiem.addKeyListener(this);
		// Mouse Listener
		tblChiTietHoaDon.addMouseListener(this);

		return panel;
	}

	private void updateOrder() {
		// TODO Auto-generated method stub
		int soLuongSP = 0;
		double tongTien = 0;
		for(int i = 0; i < tblChiTietHoaDon.getRowCount(); i++) {
			soLuongSP += Integer.parseInt(tblChiTietHoaDon.getValueAt(i, 2).toString());
			tongTien += Double.parseDouble(tblChiTietHoaDon.getValueAt(i, 4).toString());
		}
		txtTongSoLuongSanPham.setText("" + String.valueOf(soLuongSP));
		txtTongCong.setText("" + String.valueOf(tongTien));

		if(txtCapDoKhachHang.getText().equalsIgnoreCase("")){
			txtTienGiam.setText("0");
		} else if(txtCapDoKhachHang.getText().equalsIgnoreCase("Thành Viên")) {
			double tienGiam = (tongTien * 0.05);
			txtTienGiam.setText("" + tienGiam);
		} else if(txtCapDoKhachHang.getText().equalsIgnoreCase("Thành Viên Bạc")) {
			double tienGiam = (tongTien * 0.1);
			txtTienGiam.setText("" + tienGiam);
		} else if(txtCapDoKhachHang.getText().equalsIgnoreCase("Thành Viên Vàng")) {
			double tienGiam = (tongTien * 0.15);
			txtTienGiam.setText("" + tienGiam);
		}

		double tienPhaiThanhToan = Double.parseDouble(txtTongCong.getText()) - Double.parseDouble(txtTienGiam.getText());

		txtTienPhaiThanhToan.setText("" + tienPhaiThanhToan);

	}

	private void removeAllRow(DefaultTableModel tableModel) {
		// TODO Auto-generated method stub
		while(tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	private void loadAllDataSanPham() {
		// TODO Auto-generated method stub
		kichCoArray = new ArrayList();
		mauSacArray = new ArrayList();

		sanPham_DAO = new SanPham_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();

		sanPhamArray = sanPham_DAO.getAllSanPham();

		for(SanPham sp : sanPhamArray) {
			ArrayList<ChiTietSanPham> chiTietSanPhamArray = chiTietSanPham_DAO.getAllChiTietSanPhamTheoMaSanPham(sp.getMaSP());
			for(ChiTietSanPham ctsp : chiTietSanPhamArray) {
				// Checking The Product's Quantity. If this equals 0, this won't be displayed on the screen
				//				if(ctsp.getSoLuong() == 0) {
				//
				//				} else {
				//					tblModelTimKiemSanPham.addRow(new Object[] {sp.getMaSP(), 
				//							sp.getTenSP(), 
				//							ctsp.getKichCo(), 
				//							ctsp.getMauSac(), 
				//							ctsp.getGiaBan(),
				//							ctsp.getSoLuong()});
				//				}
				
				// Add the product's size into combo box 
				if(!kichCoArray.contains(ctsp.getKichCo())) {
					kichCoArray.add(ctsp.getKichCo());
					cbModelKichCo.addElement(ctsp.getKichCo());
				}
				// Add the product's color into combo box 
				if(!mauSacArray.contains(ctsp.getMauSac())) {
					mauSacArray.add(ctsp.getMauSac());
					cbModelMauSac.addElement(ctsp.getMauSac());
				}
			}
		}
	}

	private void loadDataTopSanPham() {
		// TODO Auto-generated method stub

		sanPham_DAO = new SanPham_DAO();

		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();

		topSanPhamBanChayArray = new ArrayList<ChiTietHoaDon>();
		topSanPhamBanChayArray = chiTietHoaDon_DAO.topSanPhamBanChay();

		ArrayList<SanPham> sanPhamArray = sanPham_DAO.getAllSanPham();


		for(ChiTietHoaDon cthd : topSanPhamBanChayArray) {

			for(SanPham sp : sanPhamArray) {

				if(cthd.getSanPham().getMaSP().equalsIgnoreCase(sp.getMaSP())) {
					chiTietSanPham_DAO = new ChiTietSanPham_DAO();
					ArrayList<ChiTietSanPham> chiTietSanPhamArray = chiTietSanPham_DAO.getAllChiTietSanPhamTheoMaSanPham(sp.getMaSP());
					for(ChiTietSanPham ctsp : chiTietSanPhamArray) {
						tblModelTimKiemSanPham.addRow(new Object[] {sp.getMaSP(), 
								sp.getTenSP(), 
								ctsp.getKichCo(), 
								ctsp.getMauSac(), 
								ctsp.getGiaBan(),
								ctsp.getSoLuong()});
					}

				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTimKiemKhachHang)) {
			timKhachHangAction();
		} else if(o.equals(btnTaoKhachHang)) {
			new Form_TaoKhachHang().setVisible(true);
		} else if(o.equals(btnTimKiemSanPham)) {
			timSanPhamAction();
		} else if(o.equals(btnLamMoiTimKiemSanPham)) {
			lamMoiTimKiemSanPhamAction();
		} else if(o.equals(btnThemVaoHoaDon)) {
			themVaoHoaDonAction();
		} else if(o.equals(btnThanhToan)) {
			try {
				thanhToanAction();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(o.equals(btnHuyThanhToan)) {
			huyThanhToanAction();
		} else if(o.equals(mniXoa)) {
			int row = tblChiTietHoaDon.getSelectedRow();
			tblModelChiTietHoaDon.removeRow(row);
			updateOrder();
		} else if(o.equals(mniSuaSoLuong)) {
			int row2 = tblChiTietHoaDon.getSelectedRow();
			String soLuong1 = JOptionPane.showInputDialog(null, "Nhập số lượng: ");
			tblChiTietHoaDon.setValueAt(soLuong1, row2, 2);
			String a = tblChiTietHoaDon.getValueAt(row2, 3).toString();
			tblChiTietHoaDon.setValueAt(String.valueOf(Double.parseDouble(soLuong1) * Double.parseDouble(a)), row2, 4);
			updateOrder();
		} else if(o.equals(btnThemHangCho)) {
			themVaoHangChoAction();
		}

		for(int i = 0; i < btnThoatHangCho.length; i++){
			if(o.equals(btnThoatHangCho[i])) {
				// Step 1: Remove all row table Order Detail
				removeAllRow(tblModelChiTietHoaDon);

				// Step 2: Get all order detail in the waiting line and add this to table Order Detail
				for(int j = 0; j < tblModelHangCho[i].getRowCount(); j++) {
					String maSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 0).toString();
					String tenSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 1).toString();
					String soLuongSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 2).toString();
					String donGiaSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 3).toString();
					String thanhTienSanPhamTrongHangCho = tblHangCho[i].getValueAt(j, 4).toString();

					tblModelChiTietHoaDon.addRow(new Object[] {maSanPhamTrongHangCho, tenSanPhamTrongHangCho, soLuongSanPhamTrongHangCho, donGiaSanPhamTrongHangCho, thanhTienSanPhamTrongHangCho});
				}

				// Remove the waiting line Panel which choosing
				panelHangCho.remove(pnlArr[i]);
				soHangCho--;
			}
		}

		for(int i = 0; i < btnXoaHoaDonTrongHangCho.length; i++){
			if(o.equals(btnXoaHoaDonTrongHangCho[i])) {
				panelHangCho.remove(pnlArr[i]);
				soHangCho--;
			}
		}
	}

	private void lamMoiTimKiemSanPhamAction() {
		// TODO Auto-generated method stub

		// Clear Text Field And Set Combo Box Initialization Status
		txtMaSanPhamTimKiem.setText("");
		txtMaSanPhamTimKiem.setText("");
		cbKichCo.setSelectedIndex(0);
		cbMauSac.setSelectedIndex(0);

		removeAllRow(tblModelTimKiemSanPham);

		loadAllDataSanPham();
	}

	private void timSanPhamAction() {
		// TODO Auto-generated method stub

		String tuTimKiem = txtMaSanPhamTimKiem.getText().toUpperCase();
		String kichCoSanPhamTimKiem = cbKichCo.getSelectedItem().toString();
		String mauSacSanPhamTimKiem = cbMauSac.getSelectedItem().toString();

		removeAllRow(tblModelTimKiemSanPham);

		for(SanPham sp : sanPhamArray) {
			if(sp.getMaSP().contains(tuTimKiem)) {
				chiTietSanPham_DAO = new ChiTietSanPham_DAO();
				ArrayList<ChiTietSanPham> chiTietSanPhamArray = chiTietSanPham_DAO.getAllChiTietSanPhamTheoMaSanPham(sp.getMaSP());
				for(ChiTietSanPham ctsp : chiTietSanPhamArray) {

					if(sp.getMaSP().equals(ctsp.getSanPham().getMaSP()) && ctsp.getKichCo().equals(kichCoSanPhamTimKiem) && ctsp.getMauSac().equals(mauSacSanPhamTimKiem)) {
						// Checking The Product's Quantity. If this equals 0, this won't be displayed on the screen
						if(ctsp.getSoLuong() == 0) {

						} else {
							tblModelTimKiemSanPham.addRow(new Object[] {sp.getMaSP(), 
									sp.getTenSP(), 
									ctsp.getKichCo(), 
									ctsp.getMauSac(), 
									ctsp.getGiaBan(),
									ctsp.getSoLuong()});
						}
					}
				}
			}
		}
	}

	private void timKhachHangAction() {
		// TODO Auto-generated method stub
		String sDT = txtSDTKhachHang.getText();

		khachHang_DAO = new KhachHang_DAO();
		khachHang = khachHang_DAO.timKhachHangTheoSDT(sDT);

		txtNgaySinhKhachHang.setText("" + khachHang.getNgaySinh());
		txtTenKhachHang.setText("" + khachHang.getTenKhachHang());

		if(0 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 1000000) {
			txtCapDoKhachHang.setText("Thành Viên");
		} else if(1000000 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 5000000) {
			txtCapDoKhachHang.setText("Thành Viên Bạc");
		} else if(5000000 <= khachHang.getDiemTichLuy() && khachHang.getDiemTichLuy() < 10000000) {
			txtCapDoKhachHang.setText("Thành Viên Vàng");
		}
		updateOrder();
	}

	private void themVaoHoaDonAction() {
		// TODO Auto-generated method stub
		int row = tblTimKiemSanPham.getSelectedRow();

		String maSP = tblTimKiemSanPham.getValueAt(row, 0).toString();
		String tenSP = tblTimKiemSanPham.getValueAt(row, 1).toString();
		String soLuong = JOptionPane.showInputDialog(null, "Nhập Số Lượng: ");
		String soLuongSanPhamTonKho = tblTimKiemSanPham.getValueAt(row, 5).toString();

		if(Integer.parseInt(soLuong) > Integer.parseInt(soLuongSanPhamTonKho)) {
			JOptionPane.showMessageDialog(null, "Số Lượng Mua Vượt Quá Số Lượng Tồn Kho");
			soLuong = "";
		} else {
			String kichCo = tblTimKiemSanPham.getValueAt(row, 2).toString();
			String mauSac = tblTimKiemSanPham.getValueAt(row, 3).toString();
			String donGia = tblTimKiemSanPham.getValueAt(row, 4).toString();

			// The product's name will be displayed in Order Detail Table
			String tenSanPhamTrongHoaDon = tenSP + " - " + kichCo + " - " + mauSac;

			// Check Product Exist In Order Detail Table
			// If Product exits, Order Detail Table Will Update Quantity Of This Quantity
			int flag = 0;
			for(int i = 0; i < tblModelChiTietHoaDon.getRowCount(); i++) {
				String tenSanPhamCanKiemTra = tblChiTietHoaDon.getValueAt(i, 1).toString();
				if(tenSanPhamTrongHoaDon.equals(tenSanPhamCanKiemTra)) {
					// Update Quantity And Total On Order Detail Table
					flag = 1;

					String soLuongSanPhamTrongHoaDon = tblChiTietHoaDon.getValueAt(i, 2).toString();

					if(Integer.parseInt(soLuongSanPhamTrongHoaDon) + Integer.parseInt(soLuong) > Integer.parseInt(soLuongSanPhamTonKho)) {
						JOptionPane.showMessageDialog(null, "Số Lượng Sản Phẩm Mua Không Được Lớn Số Lượng Sản Phẩm Có Trong Kho");
					} else {
						tblChiTietHoaDon.setValueAt(String.valueOf(Integer.parseInt(soLuongSanPhamTrongHoaDon) + Integer.parseInt(soLuong)) , i, 2);

						tblChiTietHoaDon.setValueAt(String.valueOf(Double.parseDouble(tblChiTietHoaDon.getValueAt(i, 2).toString()) * Double.parseDouble(tblChiTietHoaDon.getValueAt(i, 3).toString())) , i, 4);

						// Update Quantity In Order Detail Array 
						for(ChiTietHoaDon chiTietHoaDon : chiTietHoaDonArray) {
							if(chiTietHoaDon.getSanPham().getTenSP().equals(tenSP) && chiTietHoaDon.getKichCo().equals(kichCo) && chiTietHoaDon.getMauSac().equals(mauSac)) {
								chiTietHoaDon.setSoLuong(Integer.parseInt(tblChiTietHoaDon.getValueAt(i, 2).toString()));
							}
						}
					}
				} 
			}

			// If Order Detail Table doesn't have product which the customer wants to buy, Order Detail Table will insert that product
			if(flag == 0) {
				Object[] rowHD = {maSP, tenSanPhamTrongHoaDon, soLuong, donGia, String.valueOf(Double.parseDouble(soLuong) * Double.parseDouble(donGia) )};
				tblModelChiTietHoaDon.addRow(rowHD);
				ChiTietHoaDon cthd = new ChiTietHoaDon(new SanPham(maSP, tenSP), kichCo, mauSac, Integer.parseInt(soLuong), Double.parseDouble(donGia)); 
				chiTietHoaDonArray.add(cthd);
			}

			updateOrder();
		}
	}

	private boolean kiemTraDieuKienThanhToan() {
		// TODO Auto-generated method stub

		// Checking Order Detail
		if(tblModelChiTietHoaDon.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Hóa Đơn Chưa Có Sản Phẩm");
			return false;
		}

		// Checking Customer Exist
		if(txtSDTKhachHang.getText().isBlank() || txtTenKhachHang.getText().isBlank()) {
			if (txtSDTKhachHang.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Thông Tin Khách Hàng");
				txtSDTKhachHang.requestFocus();
				return false;
			} else if (txtTenKhachHang.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Khách Hàng Không Có Trên Hệ Thống");
				txtSDTKhachHang.requestFocus();
				return false;
			}
		} else {
			String sDT = txtSDTKhachHang.getText();

			if(!sDT.matches("^[0][0-9]{9,10}")) {
				JOptionPane.showMessageDialog(null, "Số Điện Bao Gồm 10 Hoặc 11 Số Và Bắt Đầu Bằng Số 0");
				return false;
			}
		}

		String tienNhan = txtTienNhan.getText();
		// Checking Receipt
		if(tienNhan.isBlank() || !(tienNhan.matches("([0-9])*"))) {
			if(tienNhan.isBlank()) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Tiền Nhận");
				txtTienNhan.requestFocus();
				return false;
			} else if(!tienNhan.matches("([0-9])*")) {
				JOptionPane.showMessageDialog(null, "Tiền Nhập Chỉ Được Nhập Số");
				txtTienNhan.selectAll();
				txtTienNhan.requestFocus();
				return false;
			}
		} 
		return true;
	}

	private void thanhToanAction() throws DocumentException {
		// TODO Auto-generated method stub
		if(kiemTraDieuKienThanhToan()) {

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

			if(tienTra >=0 && tienNhan >= tienThanhToan) {
				String maHoaDon = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH mm ss");
				SimpleDateFormat sdfID = new SimpleDateFormat("ddMMyyyyHHmm");
				String time = sdf.format(new Date(Calendar.getInstance().getTime().getTime()));
				int rand = (int)(Math.floor(Math.random() * 100000) + 899999);
				maHoaDon += sdfID.format(new Date(Calendar.getInstance().getTime().getTime()))  + String.valueOf(rand);

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

				if(now.compareTo(muoiSauGio) <= 0 && now.compareTo(chinGio) > 0) {
					caLamViec = 1;
				} else {
					caLamViec = 2;
				}

				HoaDon hd = new HoaDon(maHoaDon, 
						currentTime,
						new NhanVien(nvHD.getMaNhanVien()),
						new KhachHang(khachHang.getMaKhachHang()),
						caLamViec, 
						tongCong, 
						tienGiam, 
						tienThanhToan, 
						tienNhan, 
						tienTra);

				if(hoaDon_DAO.themHoaDon(hd)) {
					int diemTichLuy = (int)tienThanhToan/1000;

					KhachHang khachHangTichLuy = new KhachHang(khachHang.getMaKhachHang(), Math.round(diemTichLuy));
					khachHang_DAO.congDiemTichLuy(khachHangTichLuy);

					// Save
					for(ChiTietHoaDon cthd : chiTietHoaDonArray) {
						ChiTietHoaDon cTHD = new ChiTietHoaDon(new HoaDon(hd.getMaHoaDon()), 
								new SanPham(cthd.getSanPham().getMaSP(), cthd.getSanPham().getTenSP()),
								cthd.getKichCo(),
								cthd.getMauSac(), 
								cthd.getSoLuong(), 
								cthd.getGiaBan());

						if(chiTietHoaDon_DAO.themChiTietHoaDon(cTHD)) {

						} else {

						}
						if(
								//This line means the database will update the product's quantity which is contained in order detail when the staff presses the purchase button
								chiTietSanPham_DAO.updataChiTietSanPhamTheoMa(new ChiTietSanPham(new SanPham(cthd.getSanPham().getMaSP()), 
										cthd.getKichCo(), 
										cthd.getMauSac(), 
										cthd.getSoLuong()))
								) {
						} else {

						}
					}

					JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");

					// Print Order Action
					int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn ?", "In Hóa Đơn", JOptionPane.YES_NO_OPTION);

					if(option == JOptionPane.YES_OPTION) {
						OrderSample os = new OrderSample();

						// Order Info include order ID, Sale ID, create Date
						os.addOrderInfo(time + " ", maHoaDon + " ", nvHD.getMaNhanVien());

						for(int i = 0; i < tblChiTietHoaDon.getRowCount(); i++) {
							String maSP = tblChiTietHoaDon.getValueAt(i, 0).toString();
							String tenSP = tblChiTietHoaDon.getValueAt(i, 1).toString();
							String soLuong = tblChiTietHoaDon.getValueAt(i, 2).toString();
							String donGia = tblChiTietHoaDon.getValueAt(i, 3).toString();
							String thanhTien = tblChiTietHoaDon.getValueAt(i, 4).toString();

							// Insert Order Detail
							os.addCellOrderDetail(tenSP, maSP, soLuong, donGia, thanhTien);
						}
						// Insert Order Purchase Info
						os.addPurchaseInfo(txtTongCong.getText()+ "", 
								txtTienGiam.getText() + "",
								txtTienPhaiThanhToan.getText() + "",
								txtTienNhan.getText() + "", 
								txtTienTra.getText() + "");
						// Create Order
						os.printOrder(maHoaDon + "");

						// Display Order File
						try {
							Thread.sleep(500);
							Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + "..\\heThongBanThoiTrang\\pdf\\" + maHoaDon + ".pdf");
						} catch (Exception e) {
							// TODO: handle exception

						} 
					}
				} else {
					JOptionPane.showMessageDialog(null, "Thanh Toán Thất Bại");
				}

				removeAllRow(tblModelTimKiemSanPham);
				loadDataTopSanPham();
				removeAllRow(tblModelChiTietHoaDon);
				txtNgaySinhKhachHang.setText("");
				txtSDTKhachHang.setText("");
				txtCapDoKhachHang.setText("");
				txtTenKhachHang.setText("");

				txtTienNhan.setText("");

				txtTongCong.setText("0");
				txtTienGiam.setText("0");
				txtTienPhaiThanhToan.setText("0");
				txtTienTra.setText("0");
				txtTongSoLuongSanPham.setText("0");
			} else {
				JOptionPane.showMessageDialog(null, "Tiền Nhận Không Được Bé Hơn Tổng Cộng");
			}
		}

	}

	private void huyThanhToanAction() {
		// TODO Auto-generated method stub

		// Customer
		txtSDTKhachHang.setText("");
		txtNgaySinhKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtCapDoKhachHang.setText("");

		// Product
		txtMaSanPhamTimKiem.setText("");
		cbKichCo.setSelectedIndex(0);
		cbMauSac.setSelectedIndex(0);
		removeAllRow(tblModelTimKiemSanPham);
		loadDataTopSanPham();

		// Order Detail
		removeAllRow(tblModelChiTietHoaDon);

		// Purchase
		txtTienNhan.setText("0");
		txtTienTra.setText("0");
	}

	private void taoHangCho(ArrayList<ChiTietHoaDon> arrayList) {
		// TODO Auto-generated method stub
		int wHangCho = scrPnlHangCho.getWidth() - 20, hHangCho = 150;

		panelHangCho.add(pnlArr[soHangCho] = new JPanel());

		pnlArr[soHangCho].setPreferredSize(new Dimension(wHangCho, hHangCho));

		pnlArr[soHangCho].setLayout(new BoxLayout(pnlArr[soHangCho], BoxLayout.X_AXIS));

		panelHangCho.add(Box.createVerticalStrut(30));

		JPanel pnlLeftHangCho = new JPanel();
		pnlArr[soHangCho].add(pnlLeftHangCho);
		pnlLeftHangCho.setPreferredSize(new Dimension(wHangCho * 4 / 5, 140));
		pnlLeftHangCho.setLayout(new BoxLayout(pnlLeftHangCho, BoxLayout.Y_AXIS));

		Date thoiGianThemVaoHangCho = new Date(Calendar.getInstance().getTime().getTime());
		SimpleDateFormat sdfHangCho = new SimpleDateFormat("HH : mm : ss");
		String s = "";
		s += s.format("Hóa đơn số %s %12s %12s", String.valueOf(soHangCho + 1), " ", sdfHangCho.format(thoiGianThemVaoHangCho));
		pnlLeftHangCho.add(new JLabel("" + s));

		tblHangCho[soHangCho] = new JTable(tblModelHangCho[soHangCho] = new DefaultTableModel(new Object[] {"Mã", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0));
		for(ChiTietHoaDon cthd : arrayList) {	
			tblModelHangCho[soHangCho].addRow(new Object[] {cthd.getSanPham().getMaSP().toString(), 
					cthd.getSanPham().getTenSP().toString(),
					String.valueOf(cthd.getSoLuong()),
					String.valueOf(cthd.getGiaBan()),
					Double.parseDouble(String.valueOf(cthd.getSoLuong())) * Double.parseDouble(String.valueOf(cthd.getGiaBan()))});
		}
		pnlLeftHangCho.add(new JScrollPane(tblHangCho[soHangCho]));

		JPanel pnlRightHangCho = new JPanel();
		pnlArr[soHangCho].add(pnlRightHangCho);
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

	private void themVaoHangChoAction() {
		// TODO Auto-generated method stub
		if(soHangCho == 99) {
			JOptionPane.showMessageDialog(null, "Số Hóa Đơn Trong Hàng Chờ Đã Đạt Đến Giới Hạn \n Vui Lòng Thanh Toán Các Hóa Đã Có Trong Hàng Chờ");
		} else {
			if(tblModelChiTietHoaDon.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa Thêm Sản Phẩm Nào Vào Hóa Đơn");

			} else {
				hangChoArray = new ArrayList<ChiTietHoaDon>();
				for(int i = 0; i < tblModelChiTietHoaDon.getRowCount(); i++) {
					String ma = tblChiTietHoaDon.getValueAt(i, 0).toString();
					String ten = tblChiTietHoaDon.getValueAt(i, 1).toString();
					String soLuong = tblChiTietHoaDon.getValueAt(i, 2).toString();
					String donGia = tblChiTietHoaDon.getValueAt(i, 3).toString();
					String thanhTien = tblChiTietHoaDon.getValueAt(i, 4).toString();

					ChiTietHoaDon cthd = new ChiTietHoaDon(new SanPham(ma, ten), 
							Integer.parseInt(soLuong),
							Double.parseDouble(donGia));

					hangChoArray.add(cthd);
				}
				taoHangCho(hangChoArray);

				removeAllRow(tblModelChiTietHoaDon);
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getKeyCode();
		if(o.equals(KeyEvent.VK_DELETE)) {
			int row = tblChiTietHoaDon.getSelectedRow();
			tblModelChiTietHoaDon.removeRow(row);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		Object o1 = e.getKeyCode();
		if(o.equals(txtTienNhan)) {
			String tienThanhToan = txtTienPhaiThanhToan.getText();
			String tienNhan = txtTienNhan.getText();
			double tienTra = Double.parseDouble(tienNhan) - Double.parseDouble(tienThanhToan);
			txtTienTra.setText("" + tienTra);
		}
		if(o.equals(txtSDTKhachHang)) {
			if(o1.equals(KeyEvent.VK_ENTER)) {
				timKhachHangAction();
			}
		}
		if(o.equals(txtMaSanPhamTimKiem)) {
			if(o1.equals(KeyEvent.VK_ENTER)) {
				timSanPhamAction();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o =e.getSource();
		int row = tblChiTietHoaDon.getSelectedRow();
		if(o.equals(tblChiTietHoaDon)) {
			if(e.isPopupTrigger()) {
				if(row > -1) {
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o =e.getSource();
		if(o.equals(tblChiTietHoaDon)) {
			int row = tblChiTietHoaDon.getSelectedRow();
			if(row > -1) {
				if(e.isPopupTrigger()) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
