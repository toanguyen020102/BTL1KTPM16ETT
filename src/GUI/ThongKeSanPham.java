package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import DAO.*;
import entity.*;

public class ThongKeSanPham extends JPanel implements ActionListener{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int with = screenSize.width;
	int height = screenSize.height;

	private static JDateChooser Fromdate;
	private static JDateChooser Todate;
	private JButton btnTimTKDT;
	private JPanel pnEast;
	private JButton btnInTKDT;
	private JPanel pnbtn;
	private JTextField txttongHD;
	private JTextField txttongtien;
	private static JComboBox cbbdoituong;
	static ThongKeSoLuongSanPham_DAO tkdao = new ThongKeSoLuongSanPham_DAO();
	private DefaultTableModel modelTK;
	private DefaultTableModel DmodelTopSp;
	private JPanel pnSouth;
	private JLabel lbtonghd;
	private JTable tableTKDT;
	private JScrollPane scrollPane;
	private DefaultTableModel modelTopSP;
	private JTable tabletopSP;
	private JScrollPane sctopNV;
	private JTextField txtTenNV;
	static ThongKeChiTietSP_DAO tkct = new ThongKeChiTietSP_DAO();
	static LoaiSanPham_DAO lsp_dao;

	// Image imgImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Downloads\\logo (2)\\logo\\logo.jpg");
	public ThongKeSanPham() {
		// setSize(with,height);
		panelTKcon();
	}
	private void panelTKcon() {
		setLayout(new BorderLayout());
		setBackground(new Color(147,190,222));
		
		// PANEL WEST
		JPanel pnWest = new JPanel();
		add(pnWest,BorderLayout.WEST);
		pnWest.setBackground(new Color(147,190,222));
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));

		JPanel pnwChucNang = new JPanel();
		pnWest.add(pnwChucNang);
		pnwChucNang.setBackground(new Color(147,190,222));
		pnwChucNang.setBorder(BorderFactory.createTitledBorder("Chức Năng"));
		pnwChucNang.setLayout(new BoxLayout(pnwChucNang, BoxLayout.Y_AXIS));
		pnwChucNang.add(Box.createVerticalStrut(10));

		JPanel pnwFrom = new JPanel();
		pnwFrom.setBackground(new Color(147,190,222));
		pnwFrom.setLayout(new BoxLayout(pnwFrom,BoxLayout.X_AXIS));

		java.sql.Date date = new java.sql.Date(2017-1900, 12-1, 30);
		Fromdate = new JDateChooser();
		Fromdate.setDateFormatString("dd/MM/yyyy");
		Fromdate.setDate(date);

		JLabel lbfrom = new JLabel("Từ: ");
		lbfrom.setBackground(new Color(147,190,222));

		pnwFrom.add(lbfrom);
		pnwFrom.add(Fromdate);

		pnwChucNang.add(pnwFrom);
		pnwChucNang.add(Box.createVerticalStrut(10));

		JPanel pwnTo = new JPanel();
		pwnTo.setBackground(new Color(147,190,222));
		pwnTo.setLayout(new BoxLayout(pwnTo,BoxLayout.X_AXIS));

		JLabel lbto = new JLabel("Đến: ");
		lbto.setBackground(new Color(147,190,222));

		java.sql.Date date1 = new java.sql.Date(2021-1900, 12-1, 30);
		Todate = new JDateChooser();
		Todate.setDateFormatString("dd/MM/yyyy");
		Todate.setDate(date1);
		
		pwnTo.add(lbto);
		pwnTo.add(Todate);

		pnwChucNang.add(pwnTo);
		pnwChucNang.add(Box.createVerticalStrut(10));
		
		JPanel pwnDoiTuong = new JPanel();
		pwnDoiTuong.setBackground(new Color(147,190,222));
		pwnDoiTuong.setLayout(new BoxLayout(pwnDoiTuong,BoxLayout.X_AXIS));

		JLabel lbDoiTuong = new JLabel("Loại Sản Phẩm: ");

		lbto.setPreferredSize(lbDoiTuong.getPreferredSize());
		lbfrom.setPreferredSize(lbDoiTuong.getPreferredSize());

		cbbdoituong = new JComboBox<>();

		lsp_dao = new LoaiSanPham_DAO();
		for (LoaiSanPham lsp : lsp_dao.getAllLoaiSanPham()) {
			cbbdoituong.addItem(lsp.getTenLoai() + "");
		}
		pwnDoiTuong.add(lbDoiTuong);
		pwnDoiTuong.add(cbbdoituong);
		pnwChucNang.add(pwnDoiTuong);
		pnwChucNang.add(Box.createVerticalStrut(10));

		btnTimTKDT = new JButton("Tìm Kiếm");
		btnInTKDT = new JButton("In Thống Kê");
		pnbtn = new JPanel();
		pnbtn.setBackground(new Color(147,190,222));
		pnbtn.setLayout(new BoxLayout(pnbtn,BoxLayout.X_AXIS));
		pnbtn.add(btnTimTKDT);
		pnbtn.add(Box.createHorizontalStrut(20));
		pnbtn.add(btnInTKDT);

		pnwChucNang.add(pnbtn);
		pnwChucNang.add(Box.createVerticalStrut(10));

		lbtonghd = new JLabel("Tổng Số Hoá Đơn");
		lbtonghd.setBackground(new Color(147,190,222));

		JLabel lbtongtien = new JLabel("Tổng Số Tiền");
		lbtongtien.setPreferredSize(lbtongtien.getPreferredSize());
		lbtongtien.setBackground(new Color(147,190,222));
		
		txttongHD = new JTextField();
		txttongHD.setEnabled(false);

		txttongtien = new JTextField();
		txttongtien.setSelectedTextColor(new Color(0,0,0));
		txttongtien.setEnabled(false);

		JPanel pnTongSoHoaDon = new JPanel();
		pnTongSoHoaDon.setBackground(new Color(147,190,222));
		pnTongSoHoaDon.setLayout(new BoxLayout(pnTongSoHoaDon, BoxLayout.X_AXIS));
		pnTongSoHoaDon.add(lbtonghd);
		pnTongSoHoaDon.add(Box.createHorizontalStrut(10));
		pnTongSoHoaDon.add(txttongHD);

		JPanel pnTongTien = new JPanel();
		pnTongTien.setBackground(new Color(147,190,222));
		pnTongTien.setLayout(new BoxLayout(pnTongTien, BoxLayout.X_AXIS));
		pnTongTien.add(lbtongtien);
		pnTongTien.add(Box.createHorizontalStrut(10));
		pnTongTien.add(txttongtien);

		pnwChucNang.add(pnTongSoHoaDon);
		pnwChucNang.add(Box.createVerticalStrut(10));
		pnwChucNang.add(pnTongTien);
		pnwChucNang.add(Box.createVerticalStrut(10));

		// PANEL CENTER
		JPanel pnCenter = new JPanel();
		add(pnCenter,BorderLayout.CENTER);
		pnCenter.setBackground(new Color(147,190,222));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Tổng Thống Kê"));
		pnCenter.add(Box.createVerticalStrut(10));
		modelTK = new DefaultTableModel();
		modelTK.addColumn("Tên Sản Phẩm");
		modelTK.addColumn("Kich Co");
		modelTK.addColumn("Mau Sac");
		modelTK.addColumn("Số Lượng Hoá Đơn: ");
		modelTK.addColumn("Số Tiền Thu Được");

		tableTKDT = new JTable(modelTK) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				Color alternateColor = new Color(30, 144, 255);
				Color whiteColor = Color.RED;
				Double val = (Double) tableTKDT.getValueAt(row, 4);
				if (!comp.getBackground().equals(getSelectionBackground())) {
					Color c;
					if (val >= 3000000)
						c = alternateColor;
					else
						c = whiteColor;
					comp.setBackground(c);
					c = null;
				}
				return comp;
			}
	 	};
		tableTKDT.setIntercellSpacing(new Dimension(10, 10));
		tableTKDT.setRowHeight(30);
	 	tableTKDT.setDefaultEditor(Object.class, null);
		tableTKDT.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tableTKDT);
		scrollPane.setPreferredSize(new Dimension(520, 700));
		pnCenter.add(scrollPane);

		// PANEL EAST
		pnEast = new JPanel();
		add(pnEast,BorderLayout.EAST);
		pnEast.setBackground(new Color(147,190,222));
		pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
		pnEast.setBorder(BorderFactory.createTitledBorder("Bảng Thống Kê Sản Phẩm"));
		pnEast.add(Box.createVerticalStrut(10));

		modelTopSP = new DefaultTableModel();
		modelTopSP.addColumn("Sản Phẩm");
		modelTopSP.addColumn("Số Tiền Bán Được");

		tabletopSP = new JTable(modelTopSP);
		tabletopSP.setIntercellSpacing(new Dimension(10, 10));
		tabletopSP.setRowHeight(30);
		tabletopSP.setDefaultEditor(Object.class, null);

		sctopNV = new JScrollPane(tabletopSP);
		sctopNV.setBorder(BorderFactory.createTitledBorder("Top Sản Phẩm Được Bán Nhiều Nhất"));

		pnEast.add(sctopNV);

		// PANEL SOUTH
		pnSouth = new JPanel();
		add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBackground(new Color(147,190,222));
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		pnSouth.setPreferredSize(new Dimension(pnSouth.getPreferredSize().width, 450));

		btnTimTKDT.addActionListener(this);
		btnInTKDT.addActionListener(this);

		txttongHD.setDisabledTextColor(Color.blue);
		txttongtien.setDisabledTextColor(Color.blue);
	}
	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart(
				"BIỂU ĐỒ SẢN PHẨM BÁN ĐƯỢC",
				"Tên Sản Phẩm", "Số Tiền",
				createDataset(), PlotOrientation.HORIZONTAL, false, false, false);
		return barChart;
	}

	private static CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String doituong  = cbbdoituong.getSelectedItem().toString();
		try {
			SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String DateMoc = spDateFormat.format(Fromdate.getDate());
			String DateDen = spDateFormat.format(Todate.getDate());
			java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
			java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
			for (ThongKeCTSP tKeSoLuongSanPham : tkct.ThongKeSoLuongSp(doituong, ngayMocDate, DateDenDate)) {
				dataset.addValue(tKeSoLuongSanPham.getSoLuong(), "Số Lượng Sản Phẩm", tKeSoLuongSanPham.getSp());
			}
		} catch (Exception e) {

		}
		return dataset;
	}
	public boolean CheckFormTKSP() {
		SimpleDateFormat spDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date nowDate = new Date();
		if (Fromdate.getDate()==null || !(spDateFormat.format(Fromdate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))) {
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng dd/MM/20yy");
			return false;
		}
		else if (Todate.getDate()==null || !(spDateFormat.format(Todate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))){
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng dd/MM/20yy");
			return false;
		}  			
		else if (Fromdate.getDate().after(Todate.getDate())){
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Bé Hơn Ngày Bắt Đầu");
			return false;
		}
		else if (Todate.getDate().after(nowDate)) {
			JOptionPane.showMessageDialog(this, "Không Vượt Quá Ngày "+ spDateFormat.format(nowDate));
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimTKDT)) {
			if (!CheckFormTKSP()) {
				return;
			}
			else {
				ChartPanel chartPanel = new ChartPanel(createChart());
				pnSouth.removeAll();
				pnSouth.revalidate();
				pnSouth.repaint();
				pnSouth.add(chartPanel);
				String doituong  = cbbdoituong.getSelectedItem().toString();
				while(modelTK.getRowCount()!=0) {
					modelTK.removeRow(0);
				}
				while (modelTopSP.getRowCount()!=0) {
					modelTopSP.removeRow(0);
				}

				SimpleDateFormat spDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat1.format(Fromdate.getDate());
				String DateDen = spDateFormat1.format(Todate.getDate());


				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);

				DecimalFormat deci = new DecimalFormat("###,###.000");
				for (ThongKeCTSP tKeSoLuongSanPham : tkct.ThongKeSoLuongSp(doituong, ngayMocDate, DateDenDate)) {
					Object opp[] = {tKeSoLuongSanPham.getSp(),tKeSoLuongSanPham.getSize(), tKeSoLuongSanPham.getMausac(), tKeSoLuongSanPham.getSoLuong(),tKeSoLuongSanPham.getDoanhthu()};
					modelTK.addRow(opp);
				}

				for (ThongKeSoLuongSanPham tksp : tkdao.ThongKeSoLuongSpTheoTop(ngayMocDate, DateDenDate)) {
					Object oppTop[] = {tksp.getTenSP(), deci.format(tksp.getDoanhthu()) };
					modelTopSP.addRow(oppTop);
				}
				
				int total = 0;
				double totaltien = 0;
				for (int i = 0; i < modelTK.getRowCount(); i++) {
					int sum = (int) tableTKDT.getValueAt(i, 3);
					Double sumtien = (Double) tableTKDT.getValueAt(i, 4);
					total += sum;
					totaltien += sumtien;
				}
				String stringhd = Integer.toString(total);
				String stringtien = Double.toString(totaltien);

				txttongtien.setText("");
				txttongHD.setText("");
				txttongtien.setText(deci.format(Double.parseDouble(stringtien)));
				txttongHD.setText(stringhd);
			}
		}

		if (o.equals(btnInTKDT)) {
			try {
				String a  = cbbdoituong.getSelectedItem().toString();
		 		SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat.format(Fromdate.getDate());
				String DateDen = spDateFormat.format(Todate.getDate());
				Date datenow = new Date();
				SimpleDateFormat nowDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
				int i = 0;
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("DanhSachThongKe");
				Cell cell = null;
				XSSFRow row = null;
				row = sheet.createRow(4);
				
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue(a);
				
				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue("Sản Phẩm");
				
				cell = row.createCell(2,CellType.STRING);
				cell.setCellValue("Size");
				
				cell = row.createCell(3,CellType.STRING);
				cell.setCellValue("Màu sắc");
				
				cell = row.createCell(4,CellType.STRING);
				cell.setCellValue("SỐ HOÁ ĐƠN");
					
				cell = row.createCell(5,CellType.STRING);
				cell.setCellValue("TỔNG TIỀN");
					
				for (ThongKeCTSP tk : tkct.ThongKeSoLuongSp(a,ngayMocDate, DateDenDate)) {
					i++;
					
					row = sheet.createRow(4+i);
					
					cell = row.createCell(1,CellType.STRING);
					cell.setCellValue(tk.getSp());
					
					cell = row.createCell(2,CellType.STRING);
					cell.setCellValue(tk.getSize());
					

					cell = row.createCell(3,CellType.STRING);
					cell.setCellValue(tk.getMausac());
					
					cell = row.createCell(4,CellType.STRING);
					cell.setCellValue(tk.getSoLuong());
					

					cell = row.createCell(5,CellType.STRING);
					cell.setCellValue(tk.getDoanhthu());
				}
				System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
				String file = new File("./thongKe/thongKeSanPham" + nowDateFormat.format(datenow) + ".xlsx").getAbsolutePath();
				try {
					
					FileOutputStream fos = new FileOutputStream(new File(file));
					workbook.write(fos);
					fos.close();
					
					JOptionPane.showMessageDialog(this, "Xuất Thành Công Danh Sách Loại Sản Phẩm");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}