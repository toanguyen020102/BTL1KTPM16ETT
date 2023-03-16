package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

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

import DAO.NhanVien_DAO;
import DAO.ThongKeNhanVien_DAO;
import entity.NhanVien;
import entity.NhanVienTK;
	
public class ThongKeDoanhThuNhanVienTheoDate extends JPanel implements ActionListener{
	private JPanel pnbtn, 
									pnSouth,
									pnEast;

	private JLabel lbtonghd;

	private static JDateChooser Fromdate, 
															Todate;

	private JButton btnTimTKDT, 
									btnInTKDT;

	private JTextField txttongtien, 
											txttongHD;

	private static JComboBox cbbdoituong;

	private JTable tableTKDT, 
									tableTOPNV;

	private DefaultTableModel modelTK, 
														modelTopNV;

	private JScrollPane sctopNV, 
											scrollPane;

	private ThongKeNhanVien_DAO thongKeNhanVien_DAO = new ThongKeNhanVien_DAO();
	private NhanVien_DAO nv_DAO = new NhanVien_DAO();

	public ThongKeDoanhThuNhanVienTheoDate() {
		panelTKcon();
	}
	private void panelTKcon() {
		setLayout(new BorderLayout());
		setBackground(new Color(147,190,222));

		// PANEL WEST
		JPanel pnWest = new JPanel();
		add(pnWest,BorderLayout.WEST);
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));

		JPanel pnwChucNang = new JPanel();
		pnWest.add(pnwChucNang);
		pnwChucNang.setBorder(BorderFactory.createTitledBorder("Chức Năng"));
		pnwChucNang.setLayout(new BoxLayout(pnwChucNang, BoxLayout.Y_AXIS));
		pnwChucNang.setBackground(new Color(147,190,222));
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

		Date datenowDate = new Date();
		Todate = new JDateChooser();
		Todate.setDate(datenowDate);
		Todate.setDateFormatString("dd/MM/yyyy");

		pwnTo.add(lbto);
		pwnTo.add(Todate);

		pnwChucNang.add(pwnTo);
		// pnwChucNang.setPreferredSize(new Dimension(250,200));
		pnwChucNang.add(Box.createVerticalStrut(10));
		
		JPanel pwnDoiTuong = new JPanel();
		pwnDoiTuong.setBackground(new Color(147,190,222));
		pwnDoiTuong.setLayout(new BoxLayout(pwnDoiTuong,BoxLayout.X_AXIS));

		JLabel lbDoiTuong = new JLabel("Nhân Viên: ");

		cbbdoituong = new JComboBox<>();
		for (NhanVien nv : nv_DAO.getAllNhanVien()) {
			cbbdoituong.addItem(nv.getTenNhanVien());
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

		lbto.setPreferredSize(lbDoiTuong.getPreferredSize());
		lbfrom.setPreferredSize(lbDoiTuong.getPreferredSize());
		
		JPanel pncenHD = new JPanel();
		pncenHD.setBackground(new Color(147,190,222));
		pncenHD.setLayout(new BoxLayout(pncenHD,BoxLayout.X_AXIS));

		JPanel pncenTien = new JPanel();
		pncenTien.setBackground(new Color(147,190,222));
		pncenTien.setLayout(new BoxLayout(pncenTien,BoxLayout.X_AXIS));

		lbtonghd = new JLabel("Tổng Số Hoá Đơn");
		JLabel lbtongtien = new JLabel("Tổng Số Tiền");
		lbtongtien.setBackground(new Color(147,190,222));

		txttongHD = new JTextField();
		txttongHD.setEnabled(false);

		txttongtien = new JTextField();
		txttongtien.setEnabled(false);

		pncenHD.add(lbtonghd);
		pncenHD.add(Box.createHorizontalStrut(10));
		pncenHD.add(txttongHD);
		pncenTien.add(lbtongtien);
		pncenTien.add(Box.createHorizontalStrut(10));
		pncenTien.add(txttongtien);

		pnwChucNang.add(pncenHD);
		pnwChucNang.add(Box.createVerticalStrut(10));
		pnwChucNang.add(pncenTien);
		pnwChucNang.add(Box.createVerticalStrut(10));
			
		// PANEL CENTER
		JPanel pnCenter = new JPanel();
		add(pnCenter,BorderLayout.CENTER);
		pnCenter.setBackground(new Color(147,190,222));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thống Kê Nhân Viên"));
		pnCenter.add(Box.createVerticalStrut(10));

		modelTK = new DefaultTableModel();
		modelTK.addColumn("Thời Gian");
		modelTK.addColumn("Số Lượng Hoá Đơn: ");
		modelTK.addColumn("Số Tiền Thu Được");

		tableTKDT = new JTable(modelTK) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				Color alternateColor = new Color(30, 144, 255);
				Color whiteColor = Color.RED;
				Double val = (Double) tableTKDT.getValueAt(row, 2);
				if (!comp.getBackground().equals(getSelectionBackground()) )  {
					Color c;
					if (val >= 3000000) c = alternateColor;
					else c = whiteColor;
					comp.setBackground(c);
					c = null;
				}
				return comp;
			}
		};
		tableTKDT.setIntercellSpacing(new Dimension(10, 10));
		tableTKDT.setRowHeight(30);
		tableTKDT.setDefaultEditor(Object.class, null);
		tableTKDT.setAutoCreateRowSorter(true);
		tableTKDT.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tableTKDT);
		scrollPane.setPreferredSize(new Dimension(520, 700));
		pnCenter.add(scrollPane);

		// PANEL EAST
		pnEast = new JPanel();
		add(pnEast,BorderLayout.EAST);
		pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
		pnEast.setBorder(BorderFactory.createTitledBorder("Bảng Thống Kê"));
		// pnEast.setPreferredSize(new Dimension(505, 791));
		pnEast.add(Box.createVerticalStrut(10));

		// PANEL SOUTH
		pnSouth = new JPanel();
		add(pnSouth,BorderLayout.SOUTH);
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		pnSouth.setPreferredSize(new Dimension(pnSouth.getPreferredSize().width, 450));
		
		lbtongtien.setPreferredSize(lbtonghd.getPreferredSize());
		btnTimTKDT.addActionListener(this);
		btnInTKDT.addActionListener(this);
		
		txttongHD.setDisabledTextColor(Color.blue);
		txttongtien.setDisabledTextColor(Color.blue);
	}
	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart(
			"Nhân Viên " + cbbdoituong.getSelectedItem().toString(),
			"Thời Gian", 
			"Số Tiền",
			createDataset(), 
			PlotOrientation.HORIZONTAL, 
			false, 
			false, 
			false
		);
		return barChart;
	}

	private static CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String a = cbbdoituong.getSelectedItem().toString();
		SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String DateMoc = spDateFormat.format(Fromdate.getDate());
		String DateDen = spDateFormat.format(Todate.getDate());
		java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
		java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
		try {
			for (NhanVienTK nhanVienTK : ThongKeNhanVien_DAO.thongKeDoanhThuTOPNV(a,ngayMocDate,DateDenDate)){
				dataset.addValue(nhanVienTK.getSotien(), "Số tiền", nhanVienTK.getNgayGiaoDich());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if ( o.equals(btnTimTKDT) ) {
			if ( !CheckFormTKSP() ) {
				return;						
			} else {
				ChartPanel chartPanel = new ChartPanel(createChart());
				pnSouth.removeAll();
				pnSouth.revalidate();
				pnSouth.repaint();
				pnSouth.add(chartPanel);
				String a = cbbdoituong.getSelectedItem().toString();
				
				lammoitable();
				DecimalFormat deci = new DecimalFormat("###,###.000VND");
				SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat.format(Fromdate.getDate());
				String DateDen = spDateFormat.format(Todate.getDate());
				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
				try {
					for (NhanVienTK nhanVienTK : ThongKeNhanVien_DAO.thongKeDoanhThuTOPNV(cbbdoituong.getSelectedItem().toString(),ngayMocDate,DateDenDate)){
						Object row[] = {
							spDateFormat.format(nhanVienTK.getNgayGiaoDich()),
							nhanVienTK.getSoluonghoadon(),
							nhanVienTK.getSotien()
						};
						modelTK.addRow(row);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			FillInText();
		}
		else if (o.equals(btnInTKDT)) {
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
				row= sheet.createRow(4);
				
				cell = row.createCell(0,CellType.STRING);
				cell.setCellValue(a);
				
				cell = row.createCell(1,CellType.STRING);
				cell.setCellValue("DATE");
				
				cell = row.createCell(2,CellType.STRING);
				cell.setCellValue("SỐ HOÁ ĐƠN");
				
				cell = row.createCell(3,CellType.STRING);
				cell.setCellValue("TỔNG TIỀN");
				
				for (NhanVienTK tkThongKeDoanhThu : ThongKeNhanVien_DAO.thongKeDoanhThuTOPNV(a,ngayMocDate,DateDenDate)) {
					i++;
					
					row = sheet.createRow(4+i);
					
					cell = row.createCell(1,CellType.STRING);
					cell.setCellValue(spDateFormat.format(tkThongKeDoanhThu.getNgayGiaoDich()));
					
					cell = row.createCell(2,CellType.STRING);
					cell.setCellValue(tkThongKeDoanhThu.getSoluonghoadon());
					

					cell = row.createCell(3,CellType.STRING);
					cell.setCellValue(tkThongKeDoanhThu.getSotien());
				}
				System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
				String file = new File("./thongKe/thongKeDoanhThuNhanVien" + nowDateFormat.format(datenow) + ".xlsx").getAbsolutePath();
				try {
					FileOutputStream fos = new FileOutputStream(new File(file));
					workbook.write(fos);
					fos.close();
					JOptionPane.showMessageDialog(this, "In thong ke doanh thu nhan vien thanh cong");
				} catch (Exception e2) {
					e2.printStackTrace();
				}					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void lammoitable() {
		while (modelTK.getRowCount()!=0) {
			modelTK.removeRow(0);	
		}
	}
	public void FillInText() {
		int total = 0;
		double totaltien = 0;
		for (int i = 0; i < modelTK.getRowCount(); i++) {
			int sum =  (int) tableTKDT.getValueAt(i, 1);
			Double sumtien =  (Double) tableTKDT.getValueAt(i, 2);
			total += sum;
			totaltien += sumtien;
		}
		DecimalFormat deci = new DecimalFormat("###,###.000VND");

		String stringhd = Integer.toString(total);
		
		txttongtien.setText("");
		txttongHD.setText("");
		txttongtien.setText( deci.format(totaltien) );
		txttongHD.setText(stringhd);
	}
	public boolean CheckFormTKSP() {
		SimpleDateFormat spDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date nowDate = new Date();
		if (Fromdate.getDate()==null || !(spDateFormat.format(Fromdate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))) {
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng dd/MM/20yy");
			return false;
		} else if (Todate.getDate()==null || !(spDateFormat.format(Todate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))){
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng");
			return false;
		} else if (Fromdate.getDate().after(Todate.getDate())){
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Bé Hơn Ngày Bắt Đầu");
			return false;
		} else if (Todate.getDate().after(nowDate)) {
			JOptionPane.showMessageDialog(this, "Không Vượt Quá Ngày "+ spDateFormat.format(nowDate));
			return false;
		}	
		return true;
	}
}
