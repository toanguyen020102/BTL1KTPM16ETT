package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.*;

import DAO.ChiTietSanPham_DAO;
import DAO.LoaiSanPham_DAO;
import DAO.SanPham_DAO;
import connect_DB.Connect_DB;
import entity.ChiTietSanPham;
import entity.LoaiSanPham;
import entity.SanPham;

public class GUI_TimSanPham_3 extends JPanel implements ActionListener{

	private JLabel lblTieuDeBoLoc, lblTenSanPhamTimKiemBoLoc, lblLoaiSanPhamBoLoc, lblTenThuongHieuBoLoc, lblGioiTinhBoLoc, lblKichCoBoLoc, lblMauSacBoLoc, lblGiaBanBoLoc, lblGiaMin, lblGiaMax;
	private JTextField txtTenSanPham, txtGiaBanMin, txtGiaBanMax;
	private JComboBox cbLoaiSanPhamBoLoc, cbTenThuongHieuBoLoc, cbGioiTinhBoLoc, cbKichCoBoLoc, cbMauSacBoLoc;
	private DefaultComboBoxModel cbModelLoaiSanPhamBoLoc, cbModelTenThuongHieuBoLoc, cbModelGioiTinhBoLoc, cbModelKichCoBoLoc, cbModelMauSacBoLoc;
	private JButton btnTimKiem, btnLamMoi;

	private JPanel pnlRight;
	private JPanel pnlContainer;
	private JPanel pnlForm;
	private JScrollPane scrPanelRight;
	private JPanel[] pnlArr;
	private JLabel[] lblTenSanPhamHienThi, lblMaSanPhamHidden;
	private JButton[] btnHienThiChiTiet;

	private JFrame frameForm = new JFrame();
	private JScrollPane scrPnlForm;
	private JLabel lblAnh, lblTenSanPham, lblLoaiSanPham, lblTenThuongHieu, lblNuocThuongHieu, lblChatLieu, lblKieuDang, lblMoTa;
	private JTextField txtTenSanPhamDisplay, txtLoaiSanPhamDisplay, txtTenThuongHieuDisplay, txtNuocThuongHieuDisplay, txtChatLieuDisplay, txtKieuDangDisplay;	
	private JTextArea txaMoTa;
	private Box bChiTietSanPham;

	private SanPham_DAO sanPhamDAO;
	private ChiTietSanPham_DAO chiTietSanPhamDAO;
	private LoaiSanPham_DAO loaiSanPhamDAO;

	private ArrayList<SanPham> sanPhamArray;
	private ArrayList<ChiTietSanPham> chiTietSanPhamArray;
	private ArrayList kichCoSanPhamArray, mauSacSanPhamArray;
	private ArrayList tenThuongHieuArray;
	private ArrayList<LoaiSanPham> loaiSanPhamArray;

	private int width , height;

	private int n = 0;
	private int nTimKiem = 0;


	public GUI_TimSanPham_3(JFrame frame) {

		// TODO Auto-generated method stub
		try {
			Connect_DB.getInstance();
			Connection con = Connect_DB.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		createGUI(frame);
		loadAllDataBoLoc();
	}


	private void createGUI(JFrame frame) {
		// TODO Auto-generated method stub

		width = frame.getWidth();
		height = frame.getHeight();		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// Panel Product Filter
		JPanel pnlLeft;
		add(pnlLeft = new JPanel());
		pnlLeft.setBackground(new Color(147, 190, 221));
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setPreferredSize(new Dimension(width / 5, height));
		pnlLeft.add(Box.createVerticalStrut(45));

		//
		Box bRow1 = Box.createHorizontalBox();
		bRow1.add(lblTieuDeBoLoc = new JLabel("Bộ Tìm Kiếm Sản Phẩm"));
		lblTieuDeBoLoc.setFont(new Font("Arial", Font.BOLD, 20));
		pnlLeft.add(bRow1);
		pnlLeft.add(Box.createVerticalStrut(45));

		//
		Box bRow2 = Box.createVerticalBox();

		Box bRow2_1 = Box.createHorizontalBox();
		bRow2_1.add(lblTenSanPhamTimKiemBoLoc = new JLabel("Tên Sản Phẩm"));
		lblTenSanPhamTimKiemBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow2.add(bRow2_1);

		bRow2.add(Box.createVerticalStrut(5));
		Box bRow2_2 = Box.createHorizontalBox();
		bRow2_2.add(txtTenSanPham = new JTextField(30));
		bRow2.add(bRow2_2);

		pnlLeft.add(bRow2);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRow3 = Box.createVerticalBox();

		Box bRow3_1 = Box.createHorizontalBox();
		bRow3_1.add(lblLoaiSanPhamBoLoc = new JLabel("Loại Sản Phẩm"));
		lblLoaiSanPhamBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow3.add(bRow3_1);

		Box bRow3_2 = Box.createHorizontalBox();
		bRow3_2.add(cbLoaiSanPhamBoLoc = new JComboBox(cbModelLoaiSanPhamBoLoc = new DefaultComboBoxModel(new Object[] {""})));
		bRow3.add(bRow3_2);
		pnlLeft.add(bRow3);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRowTenThuongHieu = Box.createHorizontalBox();

		Box bRowTenThuongHieu_1 = Box.createHorizontalBox();
		bRowTenThuongHieu_1.add(lblTenThuongHieuBoLoc = new JLabel("Tên Thương Hiệu"));
		lblTenThuongHieuBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow3.add(bRowTenThuongHieu_1);

		Box bRowTenThuongHieu_2 = Box.createHorizontalBox();
		bRowTenThuongHieu_2.add(cbTenThuongHieuBoLoc = new JComboBox(cbModelTenThuongHieuBoLoc = new DefaultComboBoxModel(new Object[] {""})));
		bRowTenThuongHieu.add(bRowTenThuongHieu_2);

		pnlLeft.add(bRowTenThuongHieu);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRow4 = Box.createVerticalBox();
		bRow4.add(lblGioiTinhBoLoc = new JLabel("Giới Tính"));
		lblGioiTinhBoLoc.setFont(new Font("Arial", Font.BOLD, 16));

		bRow4.add(cbGioiTinhBoLoc = new JComboBox(cbModelGioiTinhBoLoc = new DefaultComboBoxModel(new Object[] {"Nam", "Nu", "Unisex"})));
		pnlLeft.add(bRow4);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRow5 = Box.createVerticalBox();

		Box bRow5_1 = Box.createHorizontalBox();
		bRow5_1.add(lblKichCoBoLoc = new JLabel("Kích Cỡ"));
		lblKichCoBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow5.add(bRow5_1);

		Box bRow5_2 = Box.createHorizontalBox();
		bRow5_2.add(cbKichCoBoLoc= new JComboBox(cbModelKichCoBoLoc = new DefaultComboBoxModel(new Object[] {""})));
		bRow5.add(bRow5_2);

		pnlLeft.add(bRow5);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRow6 = Box.createVerticalBox();

		Box bRow6_1 = Box.createHorizontalBox();
		bRow6_1.add(lblMauSacBoLoc = new JLabel("Màu Sắc"));
		lblMauSacBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow6.add(bRow6_1);

		Box bRow6_2 = Box.createHorizontalBox();
		bRow6_2.add(cbMauSacBoLoc = new JComboBox(cbModelMauSacBoLoc = new DefaultComboBoxModel(new Object[] {""})));
		bRow6.add(bRow6_2);

		pnlLeft.add(bRow6);
		pnlLeft.add(Box.createVerticalStrut(10));

		//
		Box bRow7 = Box.createVerticalBox();
		bRow7.add(lblGiaBanBoLoc = new JLabel("Giá Bán"));
		lblGiaBanBoLoc.setFont(new Font("Arial", Font.BOLD, 16));
		bRow7.add(Box.createVerticalStrut(5));

		Box bRow7_1 = Box.createHorizontalBox();
		bRow7_1.add(lblGiaMin = new JLabel("Từ"));
		lblGiaMin.setFont(new Font("Arial", Font.BOLD, 14));
		bRow7_1.add(Box.createHorizontalStrut(10));
		bRow7_1.add(txtGiaBanMin = new JTextField(30));
		bRow7.add(bRow7_1);
		bRow7.add(Box.createVerticalStrut(10));

		Box bRow7_2 = Box.createHorizontalBox();
		bRow7_2.add(lblGiaMax = new JLabel("Đến"));
		lblGiaMax.setFont(new Font("Arial", Font.BOLD, 14));
		bRow7_2.add(Box.createHorizontalStrut(10));
		bRow7_2.add(txtGiaBanMax = new JTextField(30));
		bRow7.add(bRow7_2);

		lblGiaMin.setPreferredSize(lblGiaMax.getPreferredSize());

		pnlLeft.add(bRow7);
		pnlLeft.add(Box.createVerticalStrut(30));

		//
		Box bRow8 = Box.createHorizontalBox();
		bRow8.add(btnTimKiem = new JButton("Tìm Kiếm"));
		bRow8.add(Box.createHorizontalStrut(10));
		bRow8.add(btnLamMoi = new JButton("Làm Mới"));
		pnlLeft.add(bRow8);
		pnlLeft.add(Box.createVerticalStrut(height * 3 / 10));


		// Panel Display Product
		pnlRight = new JPanel();
		pnlRight.setBackground(new Color(147, 190, 221));
		pnlRight.setLayout(new FlowLayout());
		int wPR = width * 4 / 5, hPR = height;

		scrPanelRight = new JScrollPane(pnlRight, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrPanelRight);

		// Event
		btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);

	}

	private void formDisplayProduct(SanPham sp) {
		// TODO Auto-generated method stub
		frameForm = new JFrame();
		frameForm.setLayout(new BorderLayout());
		frameForm.setTitle("" + sp.getTenSP());
		frameForm.setSize(900, 800);
		frameForm.setAlwaysOnTop(true);
		frameForm.setResizable(false);
		frameForm.setLocationRelativeTo(null);

		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(147, 190, 221));

		pnlForm = new JPanel();
		pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
		pnlForm.setBackground(new Color(147, 190, 221));
		
		pnlContainer.add(pnlForm);

		scrPnlForm = new JScrollPane(
			pnlContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
		);

		frameForm.add(scrPnlForm, BorderLayout.CENTER);

		pnlForm.add(Box.createVerticalStrut(40));

		Box bImg = Box.createHorizontalBox();
		lblAnh = new JLabel(new ImageIcon(scaleImage(sp.getPathImage() + sp.getMaSP() + ".jpg", 400, 400)));
		bImg.add(lblAnh);
		pnlForm.add(bImg);
		pnlForm.add(Box.createVerticalStrut(20));

		Box bTenSanPham = Box.createHorizontalBox();
		bTenSanPham.add(Box.createHorizontalStrut(10));
		bTenSanPham.add(lblTenSanPham = new JLabel("Tên Sản Phẩm"));
		bTenSanPham.add(Box.createHorizontalStrut(10));;
		bTenSanPham.add(txtTenSanPhamDisplay = new JTextField(30));
		txtTenSanPhamDisplay.setText(sp.getTenSP());
		txtTenSanPhamDisplay.setEditable(false);
		txtTenSanPhamDisplay.setBackground(new Color(147, 190, 221));
		txtTenSanPhamDisplay.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		bTenSanPham.add(Box.createHorizontalStrut(10));
		pnlForm.add(bTenSanPham);

		JSeparator sprts[] = { null, null, null, null, null, null, null };
		for (int i = 0; i < 7; i++) {
			sprts[i] = new JSeparator();
			sprts[i].setBackground(Color.black);
		}

		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[0]);
		pnlForm.add(Box.createVerticalStrut(10));

		Box bTenThuongHieu = Box.createHorizontalBox();
		bTenThuongHieu.add(Box.createHorizontalStrut(10));
		bTenThuongHieu.add(lblTenThuongHieu = new JLabel("Tên Thương Hiệu"));
		bTenThuongHieu.add(Box.createHorizontalStrut(10));
		bTenThuongHieu.add(txtTenThuongHieuDisplay = new JTextField(30));
		bTenThuongHieu.add(Box.createHorizontalStrut(10));
		txtTenThuongHieuDisplay.setText(sp.getTenTH());
		txtTenThuongHieuDisplay.setEditable(false);
		txtTenThuongHieuDisplay.setBackground(new Color(147, 190, 221));
		txtTenThuongHieuDisplay.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		pnlForm.add(bTenThuongHieu);
		
		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[1]);
		pnlForm.add(Box.createVerticalStrut(10));

		Box NuocThuongHieu = Box.createHorizontalBox();
		NuocThuongHieu.add(Box.createHorizontalStrut(10));
		NuocThuongHieu.add(lblNuocThuongHieu = new JLabel("Nước Thương Hiệu"));
		NuocThuongHieu.add(Box.createHorizontalStrut(10));
		NuocThuongHieu.add(txtNuocThuongHieuDisplay = new JTextField(30));
		NuocThuongHieu.add(Box.createHorizontalStrut(10));
		txtNuocThuongHieuDisplay.setText(sp.getNuocTH());
		txtNuocThuongHieuDisplay.setEditable(false);
		txtNuocThuongHieuDisplay.setBackground(new Color(147, 190, 221));
		txtNuocThuongHieuDisplay.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		pnlForm.add(NuocThuongHieu);

		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[2]);
		pnlForm.add(Box.createVerticalStrut(10));

		Box bChatLieu = Box.createHorizontalBox();
		bChatLieu.add(Box.createHorizontalStrut(10));
		bChatLieu.add(lblChatLieu = new JLabel("Chất Liệu"));
		bChatLieu.add(Box.createHorizontalStrut(10));
		bChatLieu.add(txtChatLieuDisplay = new JTextField(30));
		bChatLieu.add(Box.createHorizontalStrut(10));
		txtChatLieuDisplay.setText(sp.getChatLuong());
		txtChatLieuDisplay.setEditable(false);
		txtChatLieuDisplay.setBackground(new Color(147, 190, 221));
		txtChatLieuDisplay.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		pnlForm.add(bChatLieu);
		
		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[3]);
		pnlForm.add(Box.createVerticalStrut(10));

		Box bKieuDang = Box.createHorizontalBox();
		bKieuDang.add(Box.createHorizontalStrut(10));
		bKieuDang.add(lblKieuDang = new JLabel("Kiểu Dáng"));
		bKieuDang.add(Box.createHorizontalStrut(10));
		bKieuDang.add(txtKieuDangDisplay = new JTextField(30));
		bKieuDang.add(Box.createHorizontalStrut(10));
		txtKieuDangDisplay.setText(sp.getKieuDang());
		txtKieuDangDisplay.setEditable(false);
		txtKieuDangDisplay.setBackground(new Color(147, 190, 221));
		txtKieuDangDisplay.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		pnlForm.add(bKieuDang);
		
		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[4]);
		pnlForm.add(Box.createVerticalStrut(10));

		Box bMoTa = Box.createHorizontalBox();
		bMoTa.add(Box.createHorizontalStrut(10));
		bMoTa.add(lblMoTa = new JLabel("Mô Tả"));
		bMoTa.add(Box.createHorizontalStrut(10));
		bMoTa.add(txaMoTa = new JTextArea(10,10));
		bMoTa.add(Box.createHorizontalStrut(10));
		txaMoTa.setText(sp.getMoTa());
		txaMoTa.setEditable(false);
		txaMoTa.setBackground(new Color(147, 190, 221));
		txaMoTa.setBorder(BorderFactory.createLineBorder(new Color(147, 190, 221)));
		txaMoTa.setLineWrap(true);
		txaMoTa.setWrapStyleWord(true);
		txaMoTa.setForeground(Color.BLACK);
		txaMoTa.setPreferredSize(
			new Dimension(
				txaMoTa.getPreferredSize().width,
				txaMoTa.getPreferredSize().height
			)
		);
		pnlForm.add(bMoTa);
		
		pnlForm.add(Box.createVerticalStrut(10));
		pnlForm.add(sprts[5]);

		JPanel pnlChiTietSanPham = new JPanel();
		pnlChiTietSanPham.setBackground(new Color(147, 190, 221));
		pnlChiTietSanPham.setLayout(new BoxLayout(pnlChiTietSanPham, BoxLayout.Y_AXIS));

		JPanel pnlChiTietSanPhamHeader = new JPanel();

		JPanel pnlSoThuTu = new JPanel();
		pnlSoThuTu.setPreferredSize(new Dimension(60, 20));
		pnlSoThuTu.add(new JLabel("So thu tu"));

		JPanel pnlKichCo = new JPanel();
		pnlKichCo.setPreferredSize(new Dimension(60, 20));
		pnlKichCo.add(new JLabel("Kich co"));

		JPanel pnlMauSac = new JPanel();
		pnlMauSac.setPreferredSize(new Dimension(80, 20));
		pnlMauSac.add(new JLabel("Mau sac"));

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setPreferredSize(new Dimension(60, 20));
		pnlSoLuong.add(new JLabel("So luong"));

		JPanel pnlGiaBan = new JPanel();
		pnlGiaBan.setPreferredSize(new Dimension(60, 20));
		pnlGiaBan.add(new JLabel("Gia ban"));

		pnlChiTietSanPhamHeader.add(pnlSoThuTu);
		pnlChiTietSanPhamHeader.add(Box.createHorizontalStrut(10));
		pnlChiTietSanPhamHeader.add(pnlKichCo);
		pnlChiTietSanPhamHeader.add(Box.createHorizontalStrut(10));
		pnlChiTietSanPhamHeader.add(pnlMauSac);
		pnlChiTietSanPhamHeader.add(Box.createHorizontalStrut(10));
		pnlChiTietSanPhamHeader.add(pnlSoLuong);
		pnlChiTietSanPhamHeader.add(Box.createHorizontalStrut(10));
		pnlChiTietSanPhamHeader.add(pnlGiaBan);

		pnlChiTietSanPham.add(pnlChiTietSanPhamHeader);
		
		int i = 0;
		ChiTietSanPham_DAO ctsp_DAO = new ChiTietSanPham_DAO();
		for (ChiTietSanPham ctsp : ctsp_DAO.getAllChiTietSanPhamTheoMaSanPham(sp.getMaSP())) {
			i += 1;
			JSeparator sprtsRow = new JSeparator();
			sprtsRow.setBackground(Color.BLACK);

			JPanel pnlSoThuTuRow = new JPanel();
			pnlSoThuTuRow.setPreferredSize(new Dimension(60, 30));
			pnlSoThuTuRow.add(new JLabel(i + "."));

			JPanel pnlKichCoRow = new JPanel();
			pnlKichCoRow.setPreferredSize(new Dimension(60, 30));
			pnlKichCoRow.add(new JLabel(ctsp.getKichCo()));

			JPanel pnlMauSacRow = new JPanel();
			pnlMauSacRow.setPreferredSize(new Dimension(80, 30));
			pnlMauSacRow.add(new JLabel(ctsp.getMauSac()));

			JPanel pnlSoLuongRow = new JPanel();
			pnlSoLuongRow.setPreferredSize(new Dimension(60, 30));
			pnlSoLuongRow.add(new JLabel(ctsp.getSoLuong() + ""));

			JPanel pnlGiaBanRow = new JPanel();
			pnlGiaBanRow.setPreferredSize(new Dimension(60, 30));
			pnlGiaBanRow.add(new JLabel(ctsp.getGiaBan() + ""));
			
			JPanel pnlChiTietSanPhamRow = new JPanel();
			pnlChiTietSanPhamRow.add(pnlSoThuTuRow);
			pnlChiTietSanPhamRow.add(Box.createHorizontalStrut(10));
			pnlChiTietSanPhamRow.add(pnlKichCoRow);
			pnlChiTietSanPhamRow.add(Box.createHorizontalStrut(10));
			pnlChiTietSanPhamRow.add(pnlMauSacRow);
			pnlChiTietSanPhamRow.add(Box.createHorizontalStrut(10));
			pnlChiTietSanPhamRow.add(pnlSoLuongRow);
			pnlChiTietSanPhamRow.add(Box.createHorizontalStrut(10));
			pnlChiTietSanPhamRow.add(pnlGiaBanRow);

			pnlChiTietSanPham.add(sprtsRow);
			pnlChiTietSanPham.add(pnlChiTietSanPhamRow);
		}
		
		JScrollPane spCTSP = new JScrollPane(pnlChiTietSanPham);
		spCTSP.setPreferredSize(new Dimension(450, spCTSP.getPreferredSize().height));
		frameForm.add(spCTSP, BorderLayout.EAST);
		frameForm.setVisible(true);

		lblTenSanPham.setPreferredSize(lblNuocThuongHieu.getPreferredSize());  
		lblTenThuongHieu.setPreferredSize(lblNuocThuongHieu.getPreferredSize());  
		lblChatLieu.setPreferredSize(lblNuocThuongHieu.getPreferredSize());  
		lblKieuDang.setPreferredSize(lblNuocThuongHieu.getPreferredSize());  
		lblMoTa.setPreferredSize(lblNuocThuongHieu.getPreferredSize()); 
	}

	private Image scaleImage(String path, int w, int h) {
		// TODO Auto-generated method stub
		ImageIcon img = new ImageIcon(path);
		Image image = img.getImage();
		Image scaled = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);
		return scaled;
	}

	private void loadAllDataBoLoc() {
		sanPhamDAO = new SanPham_DAO();
		sanPhamArray = new ArrayList<SanPham>();
		kichCoSanPhamArray = new ArrayList();
		mauSacSanPhamArray = new ArrayList();
		loaiSanPhamArray = new ArrayList<LoaiSanPham>();;
		tenThuongHieuArray = new ArrayList();
		
		sanPhamArray = sanPhamDAO.getAllSanPham();

		for(SanPham sanPham : sanPhamArray) {

			if(!tenThuongHieuArray.contains(sanPham.getTenTH())) {
				tenThuongHieuArray.add(sanPham.getTenTH());
				cbModelTenThuongHieuBoLoc.addElement(sanPham.getTenTH());
			}

			chiTietSanPhamDAO = new ChiTietSanPham_DAO();
			chiTietSanPhamArray = chiTietSanPhamDAO.getAllChiTietSanPhamTheoMaSanPham(sanPham.getMaSP());
			for(ChiTietSanPham chiTietSanPham : chiTietSanPhamArray) {

				if(!kichCoSanPhamArray.contains(chiTietSanPham.getKichCo())) {
					kichCoSanPhamArray.add(chiTietSanPham.getKichCo());
					cbModelKichCoBoLoc.addElement(chiTietSanPham.getKichCo());
				}

				if(!mauSacSanPhamArray.contains(chiTietSanPham.getMauSac())) {
					mauSacSanPhamArray.add(chiTietSanPham.getMauSac());
					cbModelMauSacBoLoc.addElement(chiTietSanPham.getMauSac());
				}
			}
		}

		loaiSanPhamDAO = new LoaiSanPham_DAO();
		loaiSanPhamArray = loaiSanPhamDAO.getAllLoaiSanPham();
		for(LoaiSanPham loaiSanPham : loaiSanPhamArray) {
			cbModelLoaiSanPhamBoLoc.addElement(loaiSanPham.getTenLoai());

		}
		
		display(sanPhamArray);
	}

	private void display(ArrayList<SanPham> sanPhamDisplayArray) {
		// TODO Auto-generated method stub
		
		sanPhamDAO = new SanPham_DAO();
		sanPhamArray = new ArrayList<SanPham>();
		sanPhamArray = sanPhamDAO.getAllSanPham();
		
		int soLuongPhanTu = sanPhamDisplayArray.toArray().length;
		
		pnlRight.removeAll();
		
		while (soLuongPhanTu % 4 != 0) {
			if(soLuongPhanTu % 4 != 0) {
				soLuongPhanTu++;
			} else {
				break;
			}
		}
		
		pnlRight.setPreferredSize(new Dimension(width * 4 / 5, 310 * soLuongPhanTu / 4  - (4 * soLuongPhanTu / 4)));

		pnlArr = new JPanel[soLuongPhanTu];
		lblTenSanPhamHienThi = new JLabel[soLuongPhanTu];
		lblMaSanPhamHidden = new JLabel[soLuongPhanTu];
		btnHienThiChiTiet = new JButton[soLuongPhanTu];


		for(SanPham sanPham : sanPhamDisplayArray) {
			pnlRight.add(pnlArr[n] = new JPanel());
			pnlArr[n].setPreferredSize(new Dimension(width * 4 / 5 * 24 / 100, 300));
			pnlArr[n].setLayout(new BoxLayout(pnlArr[n], BoxLayout.Y_AXIS));
			pnlArr[n].setBackground(Color.WHITE);
			pnlArr[n].add(Box.createVerticalStrut(20));

			lblMaSanPhamHidden[n] = new JLabel("" + sanPham.getMaSP());
			
			Box bImg = Box.createHorizontalBox();
			bImg.add(
				new JLabel(
					new ImageIcon(
						scaleImage(
							new File(
								sanPham.getPathImage() + sanPham.getMaSP() + ".jpg"
							).getAbsolutePath(), 
						200, 
						200
						)
					)
				)
			);
			pnlArr[n].add(bImg);
			pnlArr[n].add(Box.createVerticalStrut(15));

			Box bTenSanPham = Box.createHorizontalBox();
			bTenSanPham.add(lblTenSanPhamHienThi[n] = new JLabel("" + sanPham.getTenSP()));
			lblTenSanPhamHienThi[n].setToolTipText("" + sanPham.getTenSP());
			pnlArr[n].add(bTenSanPham);
			pnlArr[n].add(Box.createVerticalStrut(15));

			Box bBTN = Box.createHorizontalBox();
			bBTN.add(btnHienThiChiTiet[n] = new JButton("Xem Thông Tin"));
			pnlArr[n].add(bBTN);
			pnlArr[n].add(Box.createVerticalStrut(20));

			btnHienThiChiTiet[n].addActionListener(this);

			n++;
		}

		if(n < soLuongPhanTu) {
			for(int j = n; j < soLuongPhanTu; j++) {
				pnlRight.add(pnlArr[n] = new JPanel());
				pnlArr[j].setPreferredSize(new Dimension(width * 4 / 5 * 24 / 100, 300));
				pnlArr[j].setBackground(new Color(147, 190, 221));

				n++;
			}
		}
		n = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTimKiem)) {
			timSanPhamAction();
			nTimKiem = 0;
		} else if(o.equals(btnLamMoi)) {
			lamMoiAction();
		} 
		for(int i = 0; i < btnHienThiChiTiet.length; i++) {
			if(o.equals(btnHienThiChiTiet[i])) {
				if(frameForm.isVisible()) {
					frameForm.dispose();
					activeFormDisplayProduct(lblMaSanPhamHidden[i].getText());
				} else {
					activeFormDisplayProduct(lblMaSanPhamHidden[i].getText());
				}
			}
		}
	}

	private void lamMoiAction() {
		// TODO Auto-generated method stub

		txtTenSanPham.setText("");
		cbLoaiSanPhamBoLoc.setSelectedIndex(0);
		cbGioiTinhBoLoc.setSelectedIndex(0);
		cbKichCoBoLoc.setSelectedIndex(0);
		cbMauSacBoLoc.setSelectedIndex(0);
		txtGiaBanMin.setText("");
		txtGiaBanMax.setText("");
	}

	private void activeFormDisplayProduct(String maSanPham) {
		// TODO Auto-generated method stub

		sanPhamDAO = new SanPham_DAO();
		sanPhamArray = new ArrayList<SanPham>();

		sanPhamArray = sanPhamDAO.getAllSanPham();

		for(SanPham sanPham : sanPhamArray) {
			if(sanPham.getMaSP().equalsIgnoreCase(maSanPham)) {
				formDisplayProduct(sanPham);
			}
		}
	}

	private void timSanPhamAction() {
		// TODO Auto-generated method stub

		sanPhamDAO = new SanPham_DAO();
		sanPhamArray = new ArrayList<SanPham>();

		sanPhamArray = sanPhamDAO.getAllSanPham();
		
		ArrayList<SanPham> sanPhamTimKiemArray = new ArrayList<SanPham>();

//		int soLuongPhanTu = sanPhamArray.toArray().length;
//
//		while (soLuongPhanTu % 4 != 0) {
//			if(soLuongPhanTu % 4 != 0) {
//				soLuongPhanTu++;
//			} else {
//				break;
//			}
//		}

//		pnlRight.setPreferredSize(new Dimension(width * 4 / 5, 310 * soLuongPhanTu / 3  - (5 * soLuongPhanTu / 3)));

//		pnlArr = new JPanel[soLuongPhanTu + 1];
//		lblTenSanPhamHienThi = new JLabel[soLuongPhanTu + 1];
//		lblMaSanPhamHidden = new JLabel[soLuongPhanTu + 1];
//		btnHienThiChiTiet = new JButton[soLuongPhanTu + 1];

		String tenSanPhamTimKiem = txtTenSanPham.getText();
		String loaiSanPhamTimKiem = cbLoaiSanPhamBoLoc.getSelectedItem().toString();
		String tenThuongHieuTimKiem = cbTenThuongHieuBoLoc.getSelectedItem().toString();
		String mauSacTimKiem = cbMauSacBoLoc.getSelectedItem().toString();
		String kichCoTimKiem = cbKichCoBoLoc.getSelectedItem().toString();

		String giaMin = txtGiaBanMin.getText();
		if(giaMin.isBlank() || Double.parseDouble(giaMin) < 0) {
			giaMin = "0";
		}
		String giaMax = txtGiaBanMax.getText();
		if(giaMax.isBlank() || Double.parseDouble(giaMax) < 0) {
			giaMax = "999999999999999999999";
		}

		ArrayList flagMaSanPhamArray = new ArrayList();
		
		
		for(SanPham sanPham : sanPhamArray) {

			// Filter Product Name
			if(sanPham.getTenSP().matches(".*(" + tenSanPhamTimKiem + ").*") || tenSanPhamTimKiem.isBlank()) {

				// Filter Category
				for(LoaiSanPham loai : loaiSanPhamArray) {
					if(loai.getTenLoai().equals(loaiSanPhamTimKiem) && loai.getMaLoai().equals(sanPham.getLoaiSP().getMaLoai()) || loaiSanPhamTimKiem.isBlank()) {
						
						// Filter Brand Name
						if(sanPham.getTenTH().equals(tenThuongHieuTimKiem) || tenThuongHieuTimKiem.isBlank()) {
							chiTietSanPhamDAO = new ChiTietSanPham_DAO();
							chiTietSanPhamArray = chiTietSanPhamDAO.getAllChiTietSanPhamTheoMaSanPham(sanPham.getMaSP());
							for(ChiTietSanPham chiTietSanPham : chiTietSanPhamArray) {

								// Filter Size
								if(chiTietSanPham.getKichCo().equals(kichCoTimKiem) || kichCoTimKiem.isBlank()) {
									
									// Filter Color
									if(chiTietSanPham.getMauSac().equals(mauSacTimKiem) || mauSacTimKiem.isBlank()){
										
										// Filter Price
										if(Double.parseDouble(giaMin) <= chiTietSanPham.getGiaBan() && chiTietSanPham.getGiaBan() <= Double.parseDouble(giaMax)) {
											
											if(!flagMaSanPhamArray.contains(sanPham.getMaSP())) {
												flagMaSanPhamArray.add(sanPham.getMaSP());
												sanPhamTimKiemArray.add(sanPham);
												
											} // Checking Exist
										} // Price
									} // Color

								} // Size
							}
						} // Brand Name
					}
				} // Category Name
			} // Product Name 
		}
		
		display(sanPhamTimKiemArray);

	}


}
