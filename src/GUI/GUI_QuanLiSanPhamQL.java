package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.compress.harmony.pack200.FileBands;

import DAO.ChiTietSanPham_DAO;
import DAO.LoaiSanPham_DAO;
import DAO.SanPham_DAO;
import connect_DB.Connect_DB;
import entity.ChiTietSanPham;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;


public class GUI_QuanLiSanPhamQL extends JPanel implements ActionListener, MouseListener, FocusListener, KeyListener {
	// General
	private String pathLbl = "";
	private String pathNormalIcon = "..\\heThongBanThoiTrang\\img\\iCon\\null.jpg";
	private String pathCorrectIcon = "..\\heThongBanThoiTrang\\img\\iCon\\correctIcon.png";
	private String pathWrongIcon = "..\\heThongBanThoiTrang\\img\\iCon\\wrongIcon.png";

	// Attribute Panel Product Image
	private JPanel pnlTopLeft;
	private JLabel lblImg;
	private JButton btnThemAnh;
	private String path;

	// Attribute Panel Product Info
	private JPanel pnlTopRight;
	private JLabel lblMaSanPham, lblTenSanPham, lblLoaiSanPham, lblTenTH, lblNuocTH, lblTenNhaSanXuat, lblSDTNhaSanXuat, lblEmailNhaSanXuat, lblKieuDang, lblChatLieu, lblDonVi, lblGioiTinh, lblKichCo, lblMauSac, lblSoLuong, lblGiaBan, lblMoTa;
	private JLabel lblCheckTenSanPham, lblCheckTenNhaSanXuat, lblCheckSoDienThoaiNhaSanXuat, lblCheckEmailNhaSanXuat, lblCheckSoLuong, lblCheckGiaBan;
	private JTextField txtMaSanPham, txtTenSanPham, txtTenNhaSanXuat, txtSDTNhaSanXuat, txtEmailNhaSanXuat, txtSoLuongSanPham, txtGiaBan;
	private JTextArea txaMoTa;
	private JComboBox cbLoaiSanPham, cbDonVi, cbGioiTinh, cbTenThuongHieu, cbNuocThuongHieu, cbKichCo,cbKieuDang, cbChatLieu, cbMauSac;
	private DefaultComboBoxModel cbModelLoaiSanPham, cbModelDonVi, cbModelGioiTinh, cbModelTenTH, cbModelNuocTH, cbModelKichCo, cbModelKieuDang, cbModelChatLieu, cbModelMauSac;
	private JTable tblCTSanPham;
	private DefaultTableModel tblModelChiTietSanPham;
	private JScrollPane scrTblChiTietSanPham;
	private JButton btnThemChiTietSanPham, btnSuaChiTietSanPham, btnXoaChiTietSanPham, btnThemSanPham, btnSuaSanPham, btnXoaSanPham, btnXoaTrang, btnTimSanPham;

	// Attribute Panel Product Table
	private JTable tblSanPham;
	private DefaultTableModel tblModelSanPham;
	private JScrollPane scrTblSanPham;

	// Attribute Form Input Data
	private JFrame frameInputData = new JFrame();
	private JPanel pnlInputData;
	private JLabel lblTitleInputData ,lblNameInputData, lblCheckNameInputData;
	private JTextField txtInputData;
	private JButton btnAddNewInputData, btnClearInputData, btnCancelInputData;

	// DAO
	private LoaiSanPham_DAO loai_DAO;
	private SanPham_DAO sanPham_DAO;
	private ChiTietSanPham_DAO chiTietSanPham_DAO;

	// Entity
	NhanVien nvSanPham = null;

	// Array
	ArrayList tenThuonHieuArray, nuocThuonHieuArray, kieuDangArray, chatLieuArray, kichCoArray, mauSacArray;

	public GUI_QuanLiSanPhamQL(JFrame frame, NhanVien nvInfo) {
		// TODO Auto-generated constructor stub
		try {
			Connect_DB.getInstance().connect();

			//			Connect_DB.getInstance();
			//			Connection con = Connect_DB.getConnection();
		} catch (Exception e) {
			// TODO: handle exception

		} 
		nvSanPham = nvInfo;
		createGUI(frame);
		loadDataLoaiSanPham();
		loadDataThongTinSanPham();
		loadDataSanPham();
		khongChoNhap();
	}

	private void createGUI(JFrame frame) {
		// TODO Auto-generated method stub
		int width = frame.getWidth(), height = frame.getHeight();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pnlTop;
		int wPT = width, hPT = height * 2 / 3;
		add(pnlTop = new JPanel());
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.X_AXIS));
		pnlTop.setPreferredSize(new Dimension(wPT, hPT));

		// Panel Product Image
		pnlTop.add(pnlTopLeft = new JPanel());
		int wPTL = wPT / 3, hPTL = hPT;
		pnlTopLeft.setPreferredSize(new Dimension(wPTL, hPTL));
		pnlTopLeft.setLayout(new BoxLayout(pnlTopLeft, BoxLayout.Y_AXIS));
		pnlTopLeft.add(Box.createHorizontalStrut(wPTL / 5));
		pnlTopLeft.setBackground(new Color(147, 190, 221));

		Box b = Box.createVerticalBox();

		Box bImage = Box.createHorizontalBox();
		path = "..\\heThongBanThoiTrang\\img\\iCon\\imageIcon.jfif";
		ImageIcon icon = new ImageIcon(scaleImage(path, wPTL / 2, wPTL / 2 ));
		bImage.add(lblImg = new JLabel(icon));
		b.add(bImage);
		b.add(Box.createVerticalStrut(10));

		Box bChucNang = Box.createHorizontalBox();
		bChucNang.add(btnThemAnh = new JButton("Thêm Ảnh"));
		b.add(bChucNang);

		pnlTopLeft.add(b);
		pnlTopLeft.add(Box.createVerticalStrut(hPTL / 4));

		// Panel Product Info
		pnlTop.add(pnlTopRight = new JPanel());
		pnlTopRight.setLayout(new BoxLayout(pnlTopRight, BoxLayout.Y_AXIS));
		pnlTopRight.setPreferredSize(new Dimension(wPT * 2 /3, hPT));
		pnlTopRight.setBackground(new Color(147, 190, 221));
		pnlTopRight.add(Box.createVerticalStrut(15));

		//		Row 1 contain Product ID, Product Name, Category Name 
		Box bRowProduct1 = Box.createHorizontalBox();
		bRowProduct1.add(lblMaSanPham = new JLabel("Mã Sản Phẩm"));
		bRowProduct1.add(Box.createHorizontalStrut(15));
		bRowProduct1.add(txtMaSanPham = new JTextField(30));
		txtMaSanPham.setEditable(false);
		bRowProduct1.add(Box.createHorizontalStrut(20));
		bRowProduct1.add(lblTenNhaSanXuat = new JLabel("Tên Sản Phẩm"));
		bRowProduct1.add(Box.createHorizontalStrut(10));
		bRowProduct1.add(txtTenSanPham = new JTextField(30));
		bRowProduct1.add(Box.createHorizontalStrut(10));
		bRowProduct1.add(lblCheckTenSanPham = new JLabel(new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\null.jpg", 20, 20))));
		bRowProduct1.add(Box.createHorizontalStrut(20));
		bRowProduct1.add(lblLoaiSanPham = new JLabel("Loại Sản Phẩm"));
		bRowProduct1.add(Box.createHorizontalStrut(10));
		bRowProduct1.add(cbLoaiSanPham = new JComboBox<>(cbModelLoaiSanPham = new DefaultComboBoxModel()));
		pnlTopRight.add(bRowProduct1);
		pnlTopRight.add(Box.createVerticalStrut(15));

		//		Row 2 contain Brand Name, Brand Country 
		Box bRowProduct2 = Box.createHorizontalBox();
		bRowProduct2.add(lblTenTH = new JLabel("Tên Thương Hiệu"));
		bRowProduct2.add(Box.createHorizontalStrut(10));
		bRowProduct2.add(cbTenThuongHieu = new JComboBox<>(cbModelTenTH = new DefaultComboBoxModel()));
		bRowProduct2.add(Box.createHorizontalStrut(20));
		bRowProduct2.add(lblNuocTH = new JLabel("Nước Thương Hiệu"));
		bRowProduct2.add(Box.createHorizontalStrut(10));
		bRowProduct2.add(cbNuocThuongHieu = new JComboBox<>(cbModelNuocTH = new DefaultComboBoxModel<>()));
		pnlTopRight.add(bRowProduct2);
		pnlTopRight.add(Box.createVerticalStrut(15));

		//		Row 3 contain Manufacture Name, Manufacture Telephone, Manufacture Email
		Box bRowProduct3 = Box.createHorizontalBox();
		bRowProduct3.add(lblTenNhaSanXuat = new JLabel("Tên NSX"));
		bRowProduct3.add(Box.createHorizontalStrut(23));
		bRowProduct3.add(txtTenNhaSanXuat = new JTextField(30));
		bRowProduct3.add(Box.createHorizontalStrut(10));
		bRowProduct3.add(lblCheckTenNhaSanXuat = new JLabel(new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\\\iCon\\null.jpg", 20, 20))));
		bRowProduct3.add(Box.createHorizontalStrut(20));
		bRowProduct3.add(lblSDTNhaSanXuat = new JLabel("Số Điện Thoại"));
		bRowProduct3.add(Box.createHorizontalStrut(10));
		bRowProduct3.add(txtSDTNhaSanXuat = new JTextField(30));
		bRowProduct3.add(Box.createHorizontalStrut(10));
		bRowProduct3.add(lblCheckSoDienThoaiNhaSanXuat = new JLabel(new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\null.jpg", 20, 20))));
		bRowProduct3.add(Box.createHorizontalStrut(20));
		bRowProduct3.add(lblEmailNhaSanXuat = new JLabel("Email"));
		bRowProduct3.add(Box.createHorizontalStrut(10));
		bRowProduct3.add(txtEmailNhaSanXuat = new JTextField(30));
		bRowProduct3.add(Box.createHorizontalStrut(10));
		bRowProduct3.add(lblCheckEmailNhaSanXuat = new JLabel(new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\null.jpg", 20, 20))));
		pnlTopRight.add(bRowProduct3);
		pnlTopRight.add(Box.createVerticalStrut(15));

		//		Row 4 contain Style, Manufacture Telephone, Unit, Gender
		Box bRowProduct4 = Box.createHorizontalBox();
		bRowProduct4.add(lblKieuDang = new JLabel("Kiểu Dáng"));
		bRowProduct4.add(Box.createHorizontalStrut(17));
		bRowProduct4.add(cbKieuDang = new JComboBox<>(cbModelKieuDang = new DefaultComboBoxModel<>()));
		bRowProduct4.add(Box.createHorizontalStrut(20));
		bRowProduct4.add(lblChatLieu = new JLabel("Chất Liệu"));
		bRowProduct4.add(Box.createHorizontalStrut(10));
		bRowProduct4.add(cbChatLieu = new JComboBox<>(cbModelChatLieu = new DefaultComboBoxModel<>()));
		bRowProduct4.add(Box.createHorizontalStrut(20));
		bRowProduct4.add(lblDonVi = new JLabel("Đơn Vị"));
		bRowProduct4.add(Box.createHorizontalStrut(10));
		bRowProduct4.add(cbDonVi = new JComboBox<>(cbModelDonVi = new DefaultComboBoxModel<>(new Object[] {"Cai", "Doi", "Bo"})));
		bRowProduct4.add(Box.createHorizontalStrut(20));
		bRowProduct4.add(lblGioiTinh = new JLabel("Giói Tinh"));
		bRowProduct4.add(Box.createHorizontalStrut(10));
		bRowProduct4.add(cbGioiTinh = new JComboBox<>(cbModelGioiTinh = new DefaultComboBoxModel<>(new Object[] {"Nam", "Nu", "Unisex"})));
		pnlTopRight.add(bRowProduct4);
		pnlTopRight.add(Box.createVerticalStrut(15));

		//		Row 5 contain Size, Color, Quantity, Price
		Box bRowProduct5 = Box.createHorizontalBox();
		bRowProduct5.add(lblKichCo = new JLabel("Kích Cỡ"));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(cbKichCo = new JComboBox<>(cbModelKichCo = new DefaultComboBoxModel<>()));
		bRowProduct5.add(Box.createHorizontalStrut(20));

		bRowProduct5.add(lblMauSac = new JLabel("Màu Sắc"));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(cbMauSac = new JComboBox(cbModelMauSac = new DefaultComboBoxModel()));
		bRowProduct5.add(Box.createHorizontalStrut(20));

		bRowProduct5.add(lblSoLuong = new JLabel("Số Lượng"));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(txtSoLuongSanPham = new JTextField(30));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(lblCheckSoLuong = new JLabel(new ImageIcon(scaleImage(pathNormalIcon, 20, 20))));
		bRowProduct5.add(Box.createHorizontalStrut(20));

		bRowProduct5.add(lblGiaBan = new JLabel("Giá Bán"));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(txtGiaBan = new JTextField(30));
		bRowProduct5.add(Box.createHorizontalStrut(10));
		bRowProduct5.add(lblCheckGiaBan = new JLabel(new ImageIcon(scaleImage(pathNormalIcon, 20, 20))));
		pnlTopRight.add(bRowProduct5);
		pnlTopRight.add(Box.createVerticalStrut(15));

		// 		Row 6 contain button
		Box bRowProduct6 = Box.createHorizontalBox();
		bRowProduct6.add(btnThemChiTietSanPham = new JButton("Thêm"));
		bRowProduct6.add(Box.createHorizontalStrut(50));
		bRowProduct6.setPreferredSize(new Dimension(150, 20));
		bRowProduct6.add(btnSuaChiTietSanPham = new JButton("Sửa"));
		bRowProduct6.add(Box.createHorizontalStrut(50));
		bRowProduct6.add(btnXoaChiTietSanPham = new JButton("Xóa"));
		pnlTopRight.add(bRowProduct6);
		pnlTopRight.add(Box.createVerticalStrut(15));

		// 		Row 7 contain button
		Box bRowProduct7 = Box.createHorizontalBox();
		scrTblChiTietSanPham = new JScrollPane(tblCTSanPham = new JTable(tblModelChiTietSanPham = new DefaultTableModel(new Object[] {"Kích Cỡ", "Màu Sắc", "Số Lượng", "Giá Bán"},  0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tblCTSanPham.setAutoCreateRowSorter(true);
		bRowProduct7.add(scrTblChiTietSanPham);
		pnlTopRight.add(bRowProduct7);
		pnlTopRight.add(Box.createVerticalStrut(15));

		// 		Row 8 contain Description
		Box bRowProduct8 = Box.createHorizontalBox();
		JScrollPane scrTXTMoTa;
		bRowProduct8.add(lblMoTa = new JLabel("Mô Tả"));
		bRowProduct8.add(scrTXTMoTa = new JScrollPane(txaMoTa = new JTextArea(10, 5), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		txaMoTa.setLineWrap(true);
		txaMoTa.setWrapStyleWord(true);
		pnlTopRight.add(bRowProduct8);
		pnlTopRight.add(Box.createVerticalStrut(15));

		// 		Row 9 contain button
		Box bRowProduct9 = Box.createHorizontalBox();
		bRowProduct9.add(btnThemSanPham = new JButton("Thêm Sản Phẩm", new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\addProductIcon.png", 25, 25))));
		bRowProduct9.add(Box.createHorizontalStrut(50));
		bRowProduct9.add(btnSuaSanPham = new JButton("Sửa Sản Phẩm",new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\updateIcon.png", 25, 25))));
		bRowProduct9.add(Box.createHorizontalStrut(50));
		bRowProduct9.add(btnXoaSanPham = new JButton("Xóa Sản Phẩm", new ImageIcon(scaleImage("..\\heThongBanThoiTrang\\img\\iCon\\deleteProductIcon.png", 25, 25))));
		pnlTopRight.add(bRowProduct9);
		pnlTopRight.add(Box.createVerticalStrut(15));

		lblMaSanPham.setPreferredSize(lblTenTH.getPreferredSize());
		lblTenNhaSanXuat.setPreferredSize(lblTenTH.getPreferredSize());
		lblKieuDang.setPreferredSize(lblTenTH.getPreferredSize());
		lblMoTa.setPreferredSize(lblTenTH.getPreferredSize());

		JPanel pnlBottom;
		int wPB = width, hPB = height / 3;
		add(pnlBottom = new JPanel());
		pnlBottom.setLayout(new BoxLayout(pnlBottom, BoxLayout.Y_AXIS));
		pnlBottom.setPreferredSize(new Dimension(wPB, hPB));

		// Panel Product Table
		tblModelSanPham = new DefaultTableModel(new Object[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Tên Thương Hiệu", "Tên Nhà Sản Xuất", "Kiểu Dáng", "Chất Liệu", "Giới Tính"}, 0);
		tblSanPham = new JTable(tblModelSanPham);
		tblSanPham.setIntercellSpacing(new Dimension(10, 10));
		tblSanPham.setRowHeight(30);
		scrTblSanPham = new JScrollPane(tblSanPham, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrTblSanPham.setPreferredSize(new Dimension(wPB, hPB *4/5));
		pnlBottom.add(scrTblSanPham);

		TableColumnModel columnModSearchProductTable = tblSanPham.getColumnModel();

		TableColumn TC_ProductIDProductTable = columnModSearchProductTable.getColumn(0);
		TC_ProductIDProductTable.setPreferredWidth(30);

		TableColumn TC_ProductNameProductTable = columnModSearchProductTable.getColumn(1);
		TC_ProductNameProductTable.setPreferredWidth(200);

		// Event
		// 		Action Listener
		cbLoaiSanPham.addActionListener(this);
		cbTenThuongHieu.addActionListener(this);
		cbNuocThuongHieu.addActionListener(this);
		cbKieuDang.addActionListener(this);
		cbChatLieu.addActionListener(this);
		cbKichCo.addActionListener(this);
		cbMauSac.addActionListener(this);
		btnThemAnh.addActionListener(this);
		btnThemChiTietSanPham.addActionListener(this); 
		btnSuaChiTietSanPham.addActionListener(this); 
		btnXoaChiTietSanPham.addActionListener(this); 
		btnThemSanPham.addActionListener(this);
		btnSuaSanPham.addActionListener(this);
		btnXoaSanPham.addActionListener(this);
		// 		Mouse Listener
		tblCTSanPham.addMouseListener(this);
		tblSanPham.addMouseListener(this);
		// 		Focus Listener
		txtTenSanPham.addFocusListener(this);
		txtTenNhaSanXuat.addFocusListener(this);
		txtSDTNhaSanXuat.addFocusListener(this);
		txtEmailNhaSanXuat.addFocusListener(this);
		//		Key Listener
		txtSoLuongSanPham.addKeyListener(this);
		txtGiaBan.addKeyListener(this);
	}

	private void formInputData(String tieuDeForm, String tieuDeNhap) {
		// TODO Auto-generated method stub

		frameInputData = new JFrame();
		frameInputData.setSize(400, 160);
		frameInputData.setTitle("Form Thêm " + tieuDeNhap);
		frameInputData.setLocationRelativeTo(null);

		pnlInputData = new JPanel();
		pnlInputData.setBackground(new Color(147, 190, 221));
		pnlInputData.setLayout(new BoxLayout(pnlInputData, BoxLayout.Y_AXIS));
		frameInputData.add(pnlInputData);

		// Header Area
		pnlInputData.add(Box.createVerticalStrut(5));
		Box bHeader = Box.createHorizontalBox();
		bHeader.add(lblTitleInputData = new JLabel("" + tieuDeForm));
		lblTitleInputData.setHorizontalAlignment(JLabel.CENTER);
		lblTitleInputData.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlInputData.add(bHeader);
		pnlInputData.add(Box.createVerticalStrut(20));

		// Input And Check Input Data Area
		Box bInputData = Box.createHorizontalBox();

		bInputData.add(Box.createHorizontalStrut(10));
		bInputData.add(lblNameInputData = new JLabel("" + tieuDeNhap));
		bInputData.add(Box.createHorizontalStrut(10));
		bInputData.add(txtInputData = new JTextField(30));
		bInputData.add(Box.createHorizontalStrut(10));
		bInputData.add(lblCheckNameInputData = new JLabel(new ImageIcon(scaleImage(pathNormalIcon, 20, 20))));
		bInputData.add(Box.createHorizontalStrut(10));

		pnlInputData.add(bInputData);
		pnlInputData.add(Box.createVerticalStrut(20));

		// Button Area
		Box bButtonInputData = Box.createHorizontalBox();

		bButtonInputData.add(btnAddNewInputData = new JButton("Thêm"));
		bButtonInputData.add(Box.createHorizontalStrut(10));
		bButtonInputData.add(btnClearInputData = new JButton("Xóa Trắng"));
		bButtonInputData.add(Box.createHorizontalStrut(10));
		bButtonInputData.add(btnCancelInputData = new JButton("Hủy"));

		pnlInputData.add(bButtonInputData);
		pnlInputData.add(Box.createVerticalStrut(20));

		// Action
		// 		Action Listener
		btnAddNewInputData.addActionListener(this);
		btnClearInputData.addActionListener(this);
		btnCancelInputData.addActionListener(this);

		// 		KeyListener
		txtInputData.addKeyListener(this);

		frameInputData.setVisible(true);
		frameInputData.setAlwaysOnTop(true);
	}

	private void loadDataLoaiSanPham() {
		loai_DAO = new LoaiSanPham_DAO();
		ArrayList<LoaiSanPham> loaiSanPhamArr = loai_DAO.getAllLoaiSanPham();
		for(LoaiSanPham loai : loaiSanPhamArr) {
			cbModelLoaiSanPham.addElement(loai.getTenLoai());
		}
		cbModelLoaiSanPham.addElement("Khác");
	}

	private void loadDataChiTietSanPhamTheoMa(String maSanPham) {
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();
		ArrayList<ChiTietSanPham> CTSanPhamArr = chiTietSanPham_DAO.getAllChiTietSanPhamTheoMaSanPham(maSanPham);
		for(ChiTietSanPham loai : CTSanPhamArr) {
			tblModelChiTietSanPham.addRow(new Object[] {loai.getKichCo(), loai.getMauSac(), loai.getSoLuong(), loai.getGiaBan()} );
		}
	}

	private void loadDataSanPham() {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();

		ArrayList<SanPham> sanPhamArray = sanPham_DAO.getAllSanPham();
		ArrayList<ChiTietSanPham> chiTietSanPhamArray = chiTietSanPham_DAO.getAllChiTietSanPham();

		for(SanPham sp : sanPhamArray) {
			tblModelSanPham.addRow(
					new Object[] {
							sp.getMaSP(), 
							sp.getTenSP(),
							sp.getTenTH(),
							sp.getTenNSX(),
							sp.getKieuDang(),
							sp.getChatLuong(),
							sp.getGioiTinh()
					}
					);
		}		
	}

	private void loadDataThongTinSanPham() {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();

		kieuDangArray = new ArrayList();
		chatLieuArray = new ArrayList();
		tenThuonHieuArray = new ArrayList();
		nuocThuonHieuArray = new ArrayList();
		mauSacArray = new ArrayList();
		kichCoArray = new ArrayList();

		ArrayList<SanPham> sanPhamArray = sanPham_DAO.getAllSanPham();
		ArrayList<ChiTietSanPham> chiTietSanPhamArray = chiTietSanPham_DAO.getAllChiTietSanPham();

		for(SanPham sp : sanPhamArray) {
			if(!nuocThuonHieuArray.contains(sp.getNuocTH())) {
				nuocThuonHieuArray.add(sp.getNuocTH());
				cbModelNuocTH.addElement(sp.getNuocTH());
			}
			if(!tenThuonHieuArray.contains(sp.getTenTH())) {
				tenThuonHieuArray.add(sp.getTenTH());
				cbModelTenTH.addElement(sp.getTenTH());
			}
			if(!(kieuDangArray.contains(sp.getKieuDang()))){
				kieuDangArray.add(sp.getKieuDang());
				cbModelKieuDang.addElement(sp.getKieuDang());
			}
			if(!(chatLieuArray.contains(sp.getChatLuong()))){
				chatLieuArray.add(sp.getChatLuong());
				cbModelChatLieu.addElement(sp.getChatLuong());
			}
		}
		cbModelTenTH.addElement("Khác");
		cbModelNuocTH.addElement("Khác");
		cbModelChatLieu.addElement("Khác");
		cbModelKieuDang.addElement("Khác");

		for(ChiTietSanPham ctsp: chiTietSanPhamArray) {
			if(!mauSacArray.contains(ctsp.getMauSac())) {
				mauSacArray.add(ctsp.getMauSac());
				cbModelMauSac.addElement(ctsp.getMauSac());
			}

			if(!kichCoArray.contains(ctsp.getKichCo())) {
				kichCoArray.add(ctsp.getKichCo());
				cbModelKichCo.addElement(ctsp.getKichCo());
			}
		}
		cbModelMauSac.addElement("Khác");
		cbModelKichCo.addElement("Khác");
	}

	private SanPham loadDataSanPhamTheoMa(String ma) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		SanPham sp = sanPham_DAO.timSanPhamTheoMa(ma);
		return sp;
	}

	private void removeAllRow(DefaultTableModel tableModel) {
		// TODO Auto-generated method stub
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	private Image scaleImage(String path, int w, int h) {
		// TODO Auto-generated method stub
		ImageIcon img = new ImageIcon(path);
		Image image = img.getImage();
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);
		return scaled;
	}

	private void clearTextFieldChiTietSanPham() {
		// TODO Auto-generated method stub		

		cbKichCo.setSelectedIndex(0);
		cbMauSac.setSelectedIndex(0);
		txtSoLuongSanPham.setText("");
		txtGiaBan.setText("");
	}

	private void clearTextFieldSanPham() {
		// TODO Auto-generated method stub

		txtTenSanPham.setText("");
		cbLoaiSanPham.setSelectedIndex(0);

		cbTenThuongHieu.setSelectedIndex(0);
		cbNuocThuongHieu.setSelectedIndex(0);

		txtTenNhaSanXuat.setText("");
		txtSDTNhaSanXuat.setText("");
		txtEmailNhaSanXuat.setText("");

		cbKieuDang.setSelectedIndex(0);
		cbChatLieu.setSelectedIndex(0);

		cbGioiTinh.setSelectedIndex(0);
		cbDonVi.setSelectedIndex(0);
	}

	private void khongChoNhap() {
		// TODO Auto-generated method stub

		// Row 1
		txtTenSanPham.setEnabled(false);
		cbLoaiSanPham.setEnabled(false);

		// Row 2
		cbTenThuongHieu.setEnabled(false);
		cbNuocThuongHieu.setEnabled(false);

		// Row 3
		txtTenNhaSanXuat.setEnabled(false);
		txtSDTNhaSanXuat.setEnabled(false);
		txtEmailNhaSanXuat.setEnabled(false);

		// Row 4
		cbKieuDang.setEnabled(false);
		cbChatLieu.setEnabled(false);
		cbDonVi.setEnabled(false);
		cbGioiTinh.setEnabled(false);

		// Row 5
		cbKichCo.setEnabled(false);
		cbMauSac.setEnabled(false);
		txtSoLuongSanPham.setEnabled(false);
		txtGiaBan.setEnabled(false);

		// Row 8
		txaMoTa.setEnabled(false);

		btnThemAnh.setEnabled(false);
	}

	private void choNhapDuLieu(){
		// TODO Auto-generated method stub

		// Row 1
		txtTenSanPham.setEnabled(true);
		cbLoaiSanPham.setEnabled(true);

		// Row 2
		cbTenThuongHieu.setEnabled(true);
		cbNuocThuongHieu.setEnabled(true);

		// Row 3
		txtTenNhaSanXuat.setEnabled(true);
		txtSDTNhaSanXuat.setEnabled(true);
		txtEmailNhaSanXuat.setEnabled(true);

		// Row 4
		cbKieuDang.setEnabled(true);
		cbChatLieu.setEnabled(true);
		cbDonVi.setEnabled(true);
		cbGioiTinh.setEnabled(true);

		// Row 5
		cbKichCo.setEnabled(true);
		cbMauSac.setEnabled(true);
		txtSoLuongSanPham.setEnabled(true);
		txtGiaBan.setEnabled(true);

		// Row 8
		txaMoTa.setEnabled(true);

		btnThemAnh.setEnabled(true);
	}

	private boolean kiemTraDuLieuChiTietSanPham() {
		// TODO Auto-generated method stub

		String soLuong = txtSoLuongSanPham.getText();
		if(soLuong.isBlank()) {
			return false;
		} else if(!(soLuong).matches("^([0-9]*)$")) {

			return false;
		} else if(!(Integer.parseInt(soLuong) > 0)) {

			return false;
		}

		String giaBan = txtGiaBan.getText();
		if(giaBan.isBlank()) {
		} else if(!(Double.parseDouble(giaBan) >= 1000)) {

			return false;
		} else if(!(giaBan).matches("^[0-9]*$")) {

			return false;
		}
		return true;
	}

	private boolean kiemTraDuLieuSanPham(){
		String tenSanPham = txtTenSanPham.getText();
		if(tenSanPham.isBlank()) {
			System.out.println("1");
			return false;
		} else if(!(tenSanPham).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {

			System.out.println("2");
			return false;
		}

		String tenNhaSanXuat = txtTenNhaSanXuat.getText();
		if(tenNhaSanXuat.isBlank()) {
			System.out.println("3");
			return false;
		} else if(!(tenNhaSanXuat).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
			System.out.println("4");
			return false;
		}

		String sDT = txtSDTNhaSanXuat.getText();
		if(sDT.isBlank()) {
			System.out.println("5");
			return false;
		} else if(!(sDT).matches("^(0){1}[0-9]{9,10}$")) {
			System.out.println("6");
			return false;
			
		}

		String email = txtEmailNhaSanXuat.getText();
		if(email.isBlank()) {
			System.out.println("7");
			return false;
		} else if(!(email).matches("^[A-Za-z]([A-Za-z0-9]*)*([A-Za-z0-9]*)*(@gmail|@yahoo|@outlook)(.com)$")) {
			System.out.println("8");
			return false;
		}
		
		if(tblModelChiTietSanPham.getRowCount() == 0) {
			System.out.println("9");
			return false;
		}
		
		return true;
	}

	private void kiemTraChiTietSanPhamHoanThanh() {
		// TODO Auto-generated method stub

		lblCheckSoLuong.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
		lblCheckGiaBan.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
	}

	private void kiemTraSanPhamHoanThanh() {
		// TODO Auto-generated method stub

		lblCheckTenSanPham.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
		lblCheckTenNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
		lblCheckSoDienThoaiNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
		lblCheckEmailNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathNormalIcon, 20, 20)));
	}

	private void addNewValueToComboBox(DefaultComboBoxModel cbModel, String lblComboBox) {
		String newInputData = txtInputData.getText();
		if(pathLbl.equalsIgnoreCase(pathCorrectIcon)) {
			cbModel.removeElementAt(cbModel.getSize()-1);
			cbModel.addElement(newInputData);
			cbModel.addElement("Khác");
			cbModel.setSelectedItem(newInputData);
			frameInputData.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("10");
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if(o.equals(btnAddNewInputData)) {

			if(lblNameInputData.getText().equalsIgnoreCase(lblTenTH.getText())) {
				addNewValueToComboBox(cbModelTenTH, lblTenTH.getText());
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblNuocTH.getText())){
				addNewValueToComboBox(cbModelNuocTH, lblNuocTH.getText());
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblKieuDang.getText())){
				addNewValueToComboBox(cbModelKieuDang, lblKieuDang.getText());
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblChatLieu.getText())){
				addNewValueToComboBox(cbModelChatLieu, lblChatLieu.getText());
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblKichCo.getText())){
				addNewValueToComboBox(cbModelKichCo, lblKichCo.getText());
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblMauSac.getText())){
				addNewValueToComboBox(cbModelMauSac, lblMauSac.getText());
			}

		} else if(o.equals(btnCancelInputData)){

			frameInputData.dispose();

		} else if(o.equals(btnClearInputData)) {

			txtInputData.setText("");

		}
		if(o.equals(cbLoaiSanPham)) {

			themLoaiSanPhamMoiAction();

		} else if(o.equals(cbTenThuongHieu)) {

			String optTenThuongHieu = cbTenThuongHieu.getSelectedItem().toString();
			if(optTenThuongHieu.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("3");
				formInputData("Thêm Tên Thương Hiệu", "Tên Thương Hiệu");
			}

		} else if(o.equals(cbNuocThuongHieu)){

			String optNuocThuongHieu = cbNuocThuongHieu.getSelectedItem().toString();
			if(optNuocThuongHieu.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("4");
				formInputData("Thêm Nước Thương Hiệu", "Nước Thương Hiệu");
			}

		} else if(o.equals(cbKieuDang)) {

			String optKieuDang = cbKieuDang.getSelectedItem().toString();
			if(optKieuDang.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("6");
				formInputData("Thêm Kiểu Dáng", "Kiểu Dáng");
			}

		} else if(o.equals(cbChatLieu)) {

			String optChatLieu = cbChatLieu.getSelectedItem().toString();
			if(optChatLieu.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("7");
				formInputData("Thêm Chất Liệu", "Chất Liệu");
			}

		} else if(o.equals(cbKichCo)) {

			String optKichCo = cbKichCo.getSelectedItem().toString();
			if(optKichCo.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("8");
				formInputData("Thêm Kích Cỡ", "Kích Cỡ");
			}

		}else if(o.equals(cbMauSac)) {

			String optMauSac = cbMauSac.getSelectedItem().toString();
			if(optMauSac.equalsIgnoreCase("Khác")) {
				if(frameInputData.isVisible()) {
					frameInputData.dispose();
				}
				System.out.println("9");
				formInputData("Thêm Màu Sắc", lblMauSac.getText());
			}

		} else if(o.equals(btnThemAnh)) {

			themAnhAction();

		} else if(o.equals(btnThemChiTietSanPham)) {

			themChiTietSanPhamAction();

		} else if(o.equals(btnSuaChiTietSanPham)) {

			suaChiTietSanPhamAction();

		} else if(o.equals(btnXoaChiTietSanPham)) {

			xoaChiTietSanPhamAction();

		} else if(o.equals(btnThemSanPham)) {

			if(btnThemSanPham.getText().equalsIgnoreCase("Thêm Sản Phẩm")) {
				btnThemSanPham.setText("Lưu Thay Đổi");
				btnXoaSanPham.setText("Hủy");
				choNhapDuLieu();
				btnSuaSanPham.setEnabled(false);
			} else if(btnThemSanPham.getText().equalsIgnoreCase("Lưu Thay Đổi")) {
				btnThemSanPham.setText("Thêm Sản Phẩm");
				btnXoaSanPham.setText("Xóa Sản Phẩm");
				btnSuaSanPham.setEnabled(true);
				khongChoNhap();
				themSanPhamAction();
			}

		} else if(o.equals(btnSuaSanPham)) {

			if(btnSuaSanPham.getText().equalsIgnoreCase("Sửa Sản Phẩm")) {
				btnSuaSanPham.setText("Lưu Thay Đổi");
				btnXoaSanPham.setText("Hủy");
				choNhapDuLieu();
				btnThemSanPham.setEnabled(false);
			} else if(btnSuaSanPham.getText().equalsIgnoreCase("Lưu Thay Đổi")) {
				btnSuaSanPham.setText("Sửa Sản Phẩm");
				btnXoaSanPham.setText("Xóa Sản Phẩm");
				btnThemSanPham.setEnabled(true);
				khongChoNhap();
				capNhatSanPhamAction();
			}

		} else if(o.equals(btnXoaSanPham)) {

			if(btnXoaSanPham.getText().equalsIgnoreCase("Hủy")) {
				btnXoaSanPham.setText("Xóa Sản Phẩm");
				btnThemSanPham.setText("Thêm Sản Phẩm");
				btnSuaSanPham.setText("Sửa Sản Phẩm");
				btnThemSanPham.setEnabled(true);
				btnSuaSanPham.setEnabled(true);
				khongChoNhap();
			} else if(btnXoaSanPham.getText().equalsIgnoreCase("Xóa Sản Phẩm")) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Xóa Sản Phẩm", null, JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					xoaSanPhamAction();
				}
			}
		}
	}

	private void themLoaiSanPhamMoiAction() {
		// TODO Auto-generated method stub
		String opt = cbLoaiSanPham.getSelectedItem().toString();
		if(opt.equalsIgnoreCase("Khác")) {
			String tenLoaiSanPhamMoi = "";
			do {
				tenLoaiSanPhamMoi = JOptionPane.showInputDialog(null, "Nhập Loại Sản Phẩm Thêm Mới: ");
				if(tenLoaiSanPhamMoi.isBlank()) {
					JOptionPane.showMessageDialog(null, "Tên Loại Không Được Rỗng");
				}
				if(!(tenLoaiSanPhamMoi.matches("[A-Za-z]*(\\s*[A-Za-z]*)*"))){
					JOptionPane.showMessageDialog(null, "Tên Loại Không Được Chứa Số Hay Kí Tự Đặc Biệt");
				}
			} while (!(tenLoaiSanPhamMoi.length() > 0) && !(tenLoaiSanPhamMoi.matches("[A-Za-z]*(\\s*[A-Za-z]*)*")));

			if(!tenLoaiSanPhamMoi.isBlank()) {
				String[] sArr = tenLoaiSanPhamMoi.split("\\s(\\s)*");

				String maLoai = "";
				String tenLoai = "";
				for(String b : sArr) {
					tenLoai += b.substring(0, 1).toUpperCase() + b.substring(1).toLowerCase() + " ";
				}

				for(String a : sArr) {
					maLoai += a.substring(0, 1).toUpperCase();
				}

				// Save Data Base
				LoaiSanPham loaiSanPhamMoi = new LoaiSanPham(maLoai, tenLoai);
				loai_DAO = new LoaiSanPham_DAO();
				loai_DAO.themLoaiSanPham(loaiSanPhamMoi);
				cbModelLoaiSanPham.removeAllElements();
				loadDataLoaiSanPham();
				cbModelLoaiSanPham.setSelectedItem(tenLoai);
			}
		}
	}
	
	private void themAnhAction() {
		// TODO Auto-generated method stub
		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		//filtering files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
		file.addChoosableFileFilter(filter);
		int res = file.showSaveDialog(null);
		//if the user clicks on save in File Chooser
		if(res == JFileChooser.APPROVE_OPTION){
			File selFile = file.getSelectedFile();
			path = selFile.getAbsolutePath();
			ImageIcon icon = new ImageIcon(scaleImage(path, lblImg.getWidth(), lblImg.getHeight()));
			lblImg.setIcon(icon);
		}
	}

	private void themChiTietSanPhamAction() {
		// TODO Auto-generated method stub

		int flag = 0;

		if(kiemTraDuLieuChiTietSanPham()) {
			// Get Data From Text Field
			String kichCo = cbKichCo.getSelectedItem().toString();
			String mauSac = cbMauSac.getSelectedItem().toString();
			String soLuong = txtSoLuongSanPham.getText();
			String giaBan = txtGiaBan.getText();

			// Check If Product Details Exist
			for(int i = 0; i <  tblModelChiTietSanPham.getRowCount(); i++) {
				String kichCoTrongBang = tblCTSanPham.getValueAt(i, 0).toString();
				String mauSacTrongBang = tblCTSanPham.getValueAt(i, 1).toString();
				String soLuongTrongBang = tblCTSanPham.getValueAt(i, 2).toString();

				if(kichCo.equalsIgnoreCase(kichCoTrongBang) && mauSac.equalsIgnoreCase(mauSacTrongBang)) {
					flag = 1;
				}
			}

			if(flag == 0){
				// If Product Detail Doesn't exist, The System Will Add The New Product Detail
				Object[] rowCTSanPham = {kichCo, mauSac, soLuong, giaBan};
				tblModelChiTietSanPham.addRow(rowCTSanPham);
			} else {
				for(int i = 0; i <  tblModelChiTietSanPham.getRowCount(); i++) {
					String kichCoTrongBang = tblCTSanPham.getValueAt(i, 0).toString();
					String mauSacTrongBang = tblCTSanPham.getValueAt(i, 1).toString();
					String soLuongTrongBang = tblCTSanPham.getValueAt(i, 2).toString();
					if(kichCo.equalsIgnoreCase(kichCoTrongBang) && mauSac.equalsIgnoreCase(mauSacTrongBang)) {
						// If Product Detail exist, System will increase The Product Details Quantity to that Product Detail
						tblCTSanPham.setValueAt(String.valueOf(Integer.parseInt(soLuong) + Integer.parseInt(soLuongTrongBang)), i, 2);
					}
				}
			}
			clearTextFieldChiTietSanPham();
			kiemTraChiTietSanPhamHoanThanh();
		} else {

		}

	}

	private void suaChiTietSanPhamAction() {
		// TODO Auto-generated method stub

		int row = tblCTSanPham.getSelectedRow();

		if(kiemTraDuLieuChiTietSanPham()) {
			String kichCo = cbKichCo.getSelectedItem().toString();
			String mauSac = cbMauSac.getSelectedItem().toString();
			String soLuong = txtSoLuongSanPham.getText();
			String giaBan = txtGiaBan.getText();

			Object[] rowCTSanPham = {kichCo, mauSac, soLuong, giaBan};

			tblModelChiTietSanPham.removeRow(row);
			tblModelChiTietSanPham.addRow(rowCTSanPham);

			clearTextFieldChiTietSanPham();
			clearTextFieldChiTietSanPham();
		}
	}

	private void xoaChiTietSanPhamAction() {
		// TODO Auto-generated method stub
		int row = tblCTSanPham.getSelectedRow();
		tblModelChiTietSanPham.removeRow(row);

		if (row > -1) {
			tblModelChiTietSanPham.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Chi Tiết Sản Phẩm Cần Xóa");
		}
	}

	private void themSanPhamAction() {
		// TODO Auto-generated method stub

		sanPham_DAO = new SanPham_DAO();

		if(kiemTraDuLieuSanPham()) {
			// Get Data From Text Field
			String tenSanPham = txtTenSanPham.getText();
			String loaiSanPham = cbLoaiSanPham.getSelectedItem().toString();

			String tenThuongHieu = cbTenThuongHieu.getSelectedItem().toString();
			String nuocThuongHieu = cbNuocThuongHieu.getSelectedItem().toString();

			String tenNhaSanXuat = txtTenNhaSanXuat.getText();
			String sDTNhaSanXuat = txtSDTNhaSanXuat.getText();
			String emailNhaSanXuat = txtEmailNhaSanXuat.getText();

			String kieuDang = cbKieuDang.getSelectedItem().toString();
			String chatLieu = cbChatLieu.getSelectedItem().toString();
			String gioiTinh = cbGioiTinh.getSelectedItem().toString();
			String donVi = cbDonVi.getSelectedItem().toString();

			String moTa = txaMoTa.getText();

			String linkAnh = path;

			// Create The Product's ID
			String[] s = loaiSanPham.split("\\s(\\s)*");
			String maSanPham = "";
			for (String w : s) {
				maSanPham += w.substring(0, 1).toUpperCase();  
			}
			java.util.Date dateNOW = new java.util.Date();
			SimpleDateFormat spDateFormat = new SimpleDateFormat("ssmmHHddMMyy");
			String dateString = spDateFormat.format(dateNOW);

			maSanPham += dateString;

			// Get Category ID
			String[] sArr = loaiSanPham.split("\\s(\\s)*");
			String maLoai = "";
			String tenLoai = loaiSanPham.join(" ", sArr);

			tenLoai = tenLoai.toUpperCase();
			for(String a : sArr) {
				maLoai += a.substring(0, 1).toUpperCase();
			}	

			// Add New Product TO Data Base
			SanPham sanPhamMoi = new SanPham(
					maSanPham, 
					tenSanPham,
					chatLieu,
					kieuDang, 
					gioiTinh, 
					donVi,
					moTa, 
					tenThuongHieu, 
					nuocThuongHieu, 
					tenNhaSanXuat, 
					sDTNhaSanXuat, 
					emailNhaSanXuat, 
					linkAnh, 
					new LoaiSanPham(maLoai, loaiSanPham), 
					new NhanVien(nvSanPham.getMaNhanVien())
					);

			if (sanPham_DAO.themSanPham(sanPhamMoi)) {
				JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");

				// Add Detail Product To Data Base
				for (int i = 0; i< tblCTSanPham.getRowCount(); i++) {
					String kichCo = tblCTSanPham.getValueAt(i, 0).toString();
					String mauSac = tblCTSanPham.getValueAt(i, 1).toString();
					String soLuong = tblCTSanPham.getValueAt(i, 2).toString();
					String giaBan = tblCTSanPham.getValueAt(i, 3).toString();

					ChiTietSanPham chiTietSanPhamMoi = new ChiTietSanPham(new SanPham(maSanPham), kichCo, mauSac, Integer.parseInt(soLuong), Double.parseDouble(giaBan));
					chiTietSanPham_DAO = new ChiTietSanPham_DAO();
					chiTietSanPham_DAO.themChiTietSanPham(chiTietSanPhamMoi);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Thêm sản phẩm không thành công");
			}

			// Update Data After Changing
			removeAllRow(tblModelSanPham);
			loadDataSanPham();

			// Set All Text Field And Combo Box And Label Check Initialization status
			kiemTraSanPhamHoanThanh();
			clearTextFieldSanPham();
			clearTextFieldChiTietSanPham();
		}

	}

	private void capNhatSanPhamAction() {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		chiTietSanPham_DAO = new ChiTietSanPham_DAO();

		if(kiemTraDuLieuSanPham()) {
			// Get Data From Text Field And Combo Box
			String maSanPham = txtMaSanPham.getText();
			String tenSanPham = txtTenSanPham.getText();
			String tenLoaiSanPham = cbLoaiSanPham.getSelectedItem().toString();

			String tenTH = cbTenThuongHieu.getSelectedItem().toString();
			String nuocTH = cbNuocThuongHieu.getSelectedItem().toString();

			String tenNhaSanXuat = txtTenNhaSanXuat.getText();
			String sdtNhaSanXuat = txtSDTNhaSanXuat.getText();
			String emailNhaSanXuat = txtEmailNhaSanXuat.getText();

			String kieuDang = cbKieuDang.getSelectedItem().toString();
			String chatLieu = cbChatLieu.getSelectedItem().toString();
			String donVi = cbDonVi.getSelectedItem().toString();
			String gioitinh = cbGioiTinh.getSelectedItem().toString();

			String moTaSanPham = txaMoTa.getText();

			// Get Product Category's ID
			String[] sArr = tenLoaiSanPham.split("\\s(\\s)*");

			String maLoai = "";

			for(String a : sArr) {
				maLoai += a.substring(0, 1).toUpperCase();
			}

			SanPham sanPhamUpdate = new SanPham(maSanPham, tenSanPham, 
					chatLieu, kieuDang, gioitinh, donVi, 
					moTaSanPham, 
					tenTH, nuocTH, 
					tenNhaSanXuat, sdtNhaSanXuat, emailNhaSanXuat, 
					path, 
					new LoaiSanPham(maLoai), new NhanVien(nvSanPham.getMaNhanVien()));

			// Update Product By Product ID
			if (sanPham_DAO.updateSanPham_2(sanPhamUpdate)) {
				System.out.println("kưebkrwbkh");

				// Update Detail Product

				//		Step 1: Delete All Product Details By Product ID
				chiTietSanPham_DAO.deleteAllChiTietSanPhamTheoMaSanPham(new SanPham(maSanPham));

				//  	Step 2: Update Product Detail By Product ID
				for(int i = 0; i < tblModelChiTietSanPham.getRowCount(); i++) {
					// Get Data From Detail Product Table
					String kichCo = tblCTSanPham.getValueAt(i, 0).toString();
					String mauSac = tblCTSanPham.getValueAt(i, 1).toString();
					String soLuong = tblCTSanPham.getValueAt(i, 2).toString();
					String giaBan = tblCTSanPham.getValueAt(i, 3).toString();

					// Update New Product Details By Product ID
					chiTietSanPham_DAO.themChiTietSanPham(new ChiTietSanPham(sanPhamUpdate, kichCo, mauSac, Integer.parseInt(soLuong), Double.parseDouble(giaBan)));

				}

				// Update Data After Adding
				removeAllRow(tblModelSanPham);
				loadDataSanPham();
				
				// Set All Text Field And Combo Box And Label Check Initialization status
				kiemTraSanPhamHoanThanh();
				clearTextFieldSanPham();
				clearTextFieldChiTietSanPham();
				
				JOptionPane.showMessageDialog(null, "Cập Nhật Sản Phẩm Thành Công ");
			} else {
				JOptionPane.showMessageDialog(null, "Cập Nhật Sản Phẩm Thất Bại");
			}
		}


	}

	private void xoaSanPhamAction() {
		// TODO Auto-generated method stub
		int row = tblSanPham.getSelectedRow();
		if (row > -1) {
			String maSanPham = tblSanPham.getValueAt(row, 0).toString();	
			// DAO
			sanPham_DAO = new SanPham_DAO();
			if (sanPham_DAO.deleteSanPham(maSanPham)) {

				// Update Data After Deleta
				removeAllRow(tblModelSanPham);
				loadDataSanPham();
				
				JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm Thành Công");
			} else {
				JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm Thất Bại");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Sản Phẩm Cần Xóa");
		}

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if(o.equals(tblCTSanPham)) {
			int rowCTSanPham = tblCTSanPham.getSelectedRow();

			String kichCo = tblCTSanPham.getValueAt(rowCTSanPham, 0).toString();
			String mauSac = tblCTSanPham.getValueAt(rowCTSanPham, 1).toString();
			String soLuong = tblCTSanPham.getValueAt(rowCTSanPham, 2).toString();
			String giaBan = tblCTSanPham.getValueAt(rowCTSanPham, 3).toString();

			cbKichCo.setSelectedItem(kichCo);
			cbMauSac.setSelectedItem(mauSac);
			txtSoLuongSanPham.setText(soLuong + "");
			txtGiaBan.setText(giaBan + "");
		} else if(o.equals(tblSanPham)) {
			int rowSanPham = tblSanPham.getSelectedRow();

			String maSanPham = tblSanPham.getValueAt(rowSanPham, 0).toString();

			removeAllRow(tblModelChiTietSanPham);

			SanPham sanPhamClick = loadDataSanPhamTheoMa(maSanPham);

			txtMaSanPham.setText(sanPhamClick.getMaSP());
			txtTenSanPham.setText(sanPhamClick.getTenSP());

			for(int i = 0; i < cbModelLoaiSanPham.getSize(); i++) {
				String tenLoai = cbModelLoaiSanPham.getElementAt(i).toString();

				String[] sArr = tenLoai.split("\\s(\\s)*");

				String maLoai = "";
				for(String a : sArr) {
					maLoai += a.substring(0, 1).toUpperCase();
				}

				String maLoaiDataBase = sanPhamClick.getLoaiSP().getMaLoai();

				if(maLoai.equals(maLoaiDataBase)) {
					cbLoaiSanPham.setSelectedIndex(i);
				}
			}

			cbChatLieu.setSelectedItem(sanPhamClick.getChatLuong());
			cbKieuDang.setSelectedItem(sanPhamClick.getKieuDang());
			cbGioiTinh.setSelectedItem(sanPhamClick.getGioiTinh());
			cbDonVi.setSelectedItem(sanPhamClick.getDonVi());

			txaMoTa.setText(sanPhamClick.getMoTa());

			cbTenThuongHieu.setSelectedItem(sanPhamClick.getTenTH());
			cbNuocThuongHieu.setSelectedItem(sanPhamClick.getNuocTH());

			txtTenNhaSanXuat.setText(sanPhamClick.getTenNSX());
			txtSDTNhaSanXuat.setText(sanPhamClick.getsDTNSX());
			txtEmailNhaSanXuat.setText(sanPhamClick.getEmailNSX());
			
			SanPham_DAO sp_DAO = new SanPham_DAO();
			
			SanPham sp = sp_DAO.timSanPhamTheoMa(tblSanPham.getValueAt(rowSanPham, 0).toString());

			String file = new File(
				sp.getPathImage() + sp.getMaSP() + ".jpg"
			).getAbsolutePath();

			// ImageIcon icon = new ImageIcon(scaleImage(file, lblImg.getWidth() / 10, lblImg.getHeight() / 10));
			ImageIcon icon = new ImageIcon(scaleImage(file, 300, 400));
			lblImg.setIcon(icon);

			loadDataChiTietSanPhamTheoMa(maSanPham);
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

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(txtTenSanPham)) {
			String tenSanPham = txtTenSanPham.getText();
			if(tenSanPham.isBlank()) {
				lblCheckTenSanPham.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(tenSanPham).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
				lblCheckTenSanPham.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else {
				lblCheckTenSanPham.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
			}
		} else if(o.equals(txtTenNhaSanXuat)) {
			String tenNhaSanXuat = txtTenNhaSanXuat.getText();
			if(tenNhaSanXuat.isBlank()) {
				lblCheckTenNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(tenNhaSanXuat).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
				lblCheckTenNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			}else {
				lblCheckTenNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
			}
		} else if(o.equals(txtSDTNhaSanXuat)) {
			String sDT = txtSDTNhaSanXuat.getText();
			if(sDT.isBlank()) {
				lblCheckSoDienThoaiNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(sDT).matches("^(0){1}[0-9]{9,10}$")) {
				lblCheckSoDienThoaiNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			}else {
				lblCheckSoDienThoaiNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
			}
		} else if(o.equals(txtEmailNhaSanXuat)) {
			String email = txtEmailNhaSanXuat.getText();
			if(email.isBlank()) {
				lblCheckEmailNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(email).matches("^[A-Za-z]([A-Za-z0-9]*)*([A-Za-z0-9]*)*(@gmail|@yahoo|@outlook)(.com)$")) {
				lblCheckEmailNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			}else {
				lblCheckEmailNhaSanXuat.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(txtInputData)) {
			System.out.println(o);
			if(lblNameInputData.getText().equalsIgnoreCase(lblTenTH.getText())) {
				String tenTH = txtInputData.getText();
				if(tenTH.isBlank()) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else if(!(tenTH).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else {
					pathLbl = pathCorrectIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				}

			} else if(lblNameInputData.getText().equalsIgnoreCase(lblNuocTH.getText())) {

				String nuocThuongHieu = txtInputData.getText();
				if(nuocThuongHieu.isBlank()) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else if(!(nuocThuongHieu).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else {
					pathLbl = pathCorrectIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				}

			} else if(lblNameInputData.getText().equalsIgnoreCase(lblChatLieu.getText())) {

				String chatLieu = txtInputData.getText();
				if(chatLieu.isBlank()) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else if(!(chatLieu).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else {
					pathLbl = pathCorrectIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				}

			} else if(lblNameInputData.getText().equalsIgnoreCase(lblKieuDang.getText())) {

				String kieuDang = txtInputData.getText();
				if(kieuDang.isBlank()) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else if(!(kieuDang).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else {
					pathLbl = pathCorrectIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				}
			} else if(lblNameInputData.getText().equalsIgnoreCase(lblMauSac.getText())) {
				String mauSac = txtInputData.getText();
				if(mauSac.isBlank()) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else if(!(mauSac).matches("[A-Za-z\\s]+([A-Za-z\\w\\s]*)*")) {
					pathLbl = pathWrongIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				} else {
					pathLbl = pathCorrectIcon;
					lblCheckNameInputData.setIcon(new ImageIcon(scaleImage(pathLbl, 20, 20)));
				}
			}
		} else if(o.equals(txtSoLuongSanPham)) {
			String soLuong = txtSoLuongSanPham.getText();
			if(soLuong.isBlank()) {
				lblCheckSoLuong.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(soLuong).matches("^([0-9]*)$")) {
				lblCheckSoLuong.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(Integer.parseInt(soLuong) > 0)) {
				lblCheckSoLuong.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else {
				lblCheckSoLuong.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
			}
		} else if(o.equals(txtGiaBan)) {
			String giaBan = txtGiaBan.getText();
			if(giaBan.isBlank()) {
				lblCheckGiaBan.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(Double.parseDouble(giaBan) >= 1000)) {
				lblCheckGiaBan.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else if(!(giaBan).matches("^[0-9]*$")) {
				lblCheckGiaBan.setIcon(new ImageIcon(scaleImage(pathWrongIcon, 20, 20)));
			} else {
				lblCheckGiaBan.setIcon(new ImageIcon(scaleImage(pathCorrectIcon, 20, 20)));
			}
		} 
	}

}
