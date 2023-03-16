package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.*;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import com.toedter.calendar.JDateChooser;

import DAO.ThongKeDoanhThu_DAO;
import entity.ThongKeDoanhThu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GUI_ThongKeDoanhThuQL extends JPanel implements ActionListener{
	private static JDateChooser Fromdate, 
															Todate;
	private JLabel lbtonghd;
	private JButton btnTimTKDT, 
									btnInTKDT;

	private JPanel pnEast, 
									pnSouth, 
									pnbtn;

	private JTextField txttongtien, 
											txttongHD;
	
	private JTable tableTKDT, 
									tableTOPNV;

	private DefaultTableModel modelTopNV, 
														modelTK;
									
	private JScrollPane scrollPane, 
											sctopNV;
									
	private JPanel pnCenterTxt;
	private static JComboBox cbbdoituong;
	static ThongKeDoanhThu_DAO tkDao = new ThongKeDoanhThu_DAO();
	SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public GUI_ThongKeDoanhThuQL() {
		panelTKcon();
	}
	private void panelTKcon() {
		setLayout(new BorderLayout());
		setBackground(new Color(147,190,222));

		// PANEL WEST
		JPanel pnWest = new JPanel();
		add(pnWest, BorderLayout.WEST);
		pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
		// pnWest.setBorder(new EmptyBorder(10, 10, 10, 10));
		// pnWest.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		JPanel pnwChucNang = new JPanel();
		pnWest.add(pnwChucNang);
		// pnwChucNang.setPreferredSize(new Dimension(250,200));
		pnwChucNang.setBackground(new Color(147,190,222));
		pnwChucNang.setBorder(BorderFactory.createTitledBorder("Chức Năng"));
		pnwChucNang.setLayout(new BoxLayout(pnwChucNang, BoxLayout.Y_AXIS));
		pnwChucNang.add(Box.createVerticalStrut(10));
		
		JPanel pnwFrom = new JPanel();
		pnwFrom.setBackground(new Color(147,190,222));
		pnwFrom.setLayout(new BoxLayout(pnwFrom, BoxLayout.X_AXIS));

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
		Todate.setDateFormatString("dd/MM/yyyy");
		Todate.setDate(datenowDate);

		pwnTo.add(lbto);
		pwnTo.add(Todate);

		pnwChucNang.add(pwnTo);
		pnwChucNang.add(Box.createVerticalStrut(10));

		JPanel pwnDoiTuong = new JPanel();
		pwnDoiTuong.setBackground(new Color(147,190,222));
		pwnDoiTuong.setLayout(new BoxLayout(pwnDoiTuong,BoxLayout.X_AXIS));

		JLabel lbDoiTuong = new JLabel("Đối Tượng: ");

		lbto.setPreferredSize(lbDoiTuong.getPreferredSize());
		lbfrom.setPreferredSize(lbDoiTuong.getPreferredSize());

		cbbdoituong = new JComboBox<>();
		cbbdoituong.addItem("Nhân Viên");
		cbbdoituong.addItem("Khách Hàng");

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
		lbtongtien.setBackground(new Color(147,190,222));
		
		txttongHD = new JTextField();
		txttongHD.setEnabled(false);
		txttongHD.setDisabledTextColor(Color.blue);
		
		txttongtien = new JTextField();
		txttongtien.setEnabled(false);
		txttongtien.setDisabledTextColor(Color.blue);

		JPanel pnwTongSoHoaDon = new JPanel();
		pnwTongSoHoaDon.setBackground(new Color(147,190,222));
		pnwTongSoHoaDon.setLayout(new BoxLayout(pnwTongSoHoaDon, BoxLayout.X_AXIS));
		pnwTongSoHoaDon.add(lbtonghd);
		pnwTongSoHoaDon.add(Box.createHorizontalStrut(10));
		pnwTongSoHoaDon.add(txttongHD);

		JPanel pnwTongTien = new JPanel();
		pnwTongTien.setBackground(new Color(147,190,222));
		pnwTongTien.setLayout(new BoxLayout(pnwTongTien, BoxLayout.X_AXIS));
		pnwTongTien.add(lbtongtien);
		pnwTongTien.add(Box.createHorizontalStrut(10));
		pnwTongTien.add(txttongtien);

		pnwChucNang.add(pnwTongSoHoaDon);
		pnwChucNang.add(Box.createVerticalStrut(10));
		pnwChucNang.add(pnwTongTien);
		pnwChucNang.add(Box.createVerticalStrut(10));

		// pnWest.setPreferredSize(new Dimension(250, 1000));
		// PANEL CENTER
		JPanel pnCenter = new JPanel();
		add(pnCenter, BorderLayout.CENTER);
		pnCenter.add(Box.createVerticalStrut(10));
		pnCenter.setBackground(new Color(147,190,222));
		pnCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Tổng Thống Kê"));
		
		modelTK = new DefaultTableModel();
		modelTK.addColumn("Tên");
		modelTK.addColumn("Số Lượng Hoá Đơn: ");
		modelTK.addColumn("Số Tiền Thu Được");

		tableTKDT = new JTable(modelTK);
		tableTKDT.setIntercellSpacing(new Dimension(10, 10));
		tableTKDT.setRowHeight(30);
		tableTKDT.setFillsViewportHeight(true);
		tableTKDT.setDefaultEditor(Object.class, null);

		scrollPane = new JScrollPane(tableTKDT);
		scrollPane.setPreferredSize(new Dimension(520, 700));

		pnCenter.add(scrollPane);		

		// PANEL EAST
		pnEast = new JPanel();
		add(pnEast, BorderLayout.EAST);
		pnEast.setBackground(new Color(147,190,222));
		pnEast.setLayout(new BoxLayout(pnEast, BoxLayout.Y_AXIS));
		pnEast.setBorder(BorderFactory.createTitledBorder("Bảng Thống Kê"));
		pnEast.add(Box.createVerticalStrut(10));

		modelTopNV = new DefaultTableModel();
		modelTopNV.addColumn("Họ Và Tên");
		modelTopNV.addColumn("Số Tiền Thu/Nhận Được");

		tableTOPNV = new JTable(modelTopNV);
		tableTOPNV.setIntercellSpacing(new Dimension(10, 10));
		tableTOPNV.setRowHeight(30);
		tableTOPNV.setDefaultEditor(Object.class, null);

		sctopNV = new JScrollPane(tableTOPNV);
		sctopNV.setBorder(BorderFactory.createTitledBorder("Top doanh thu/ tiêu phí nhiều nhất"));

		pnEast.add(sctopNV);

		// PANEL SOUTH
		pnSouth = new JPanel();
		add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBackground(new Color(147,190,222));
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		pnSouth.setPreferredSize(new Dimension(pnSouth.getPreferredSize().width, 450));
		pnSouth.add(Box.createVerticalStrut(10));

		// Event Listener
		btnTimTKDT.addActionListener(this);
		btnInTKDT.addActionListener(this);
	}
	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart(
			"BIỂU ĐỒ DOANH THU",
			"Tên", 
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
		DecimalFormat df = new DecimalFormat("####,####.000VND");
		String doiTuong = cbbdoituong.getSelectedItem().toString();
		try {
			if (doiTuong.equals("Nhân Viên")) {
				SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat.format(Fromdate.getDate());
				String DateDen = spDateFormat.format(Todate.getDate());
				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);

				for (ThongKeDoanhThu tKeDoanhThu : tkDao.thongKeDoanhThuNV(ngayMocDate, DateDenDate)) {
					dataset.addValue(tKeDoanhThu.getSoTien(), "Số tiền", tKeDoanhThu.getDoiTuong());
				}
			} else {
				SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat.format(Fromdate.getDate());
				String DateDen = spDateFormat.format(Todate.getDate());
				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);

				for (ThongKeDoanhThu tKeDoanhThu : tkDao.thongKeDoanhThuKH(ngayMocDate, DateDenDate)) {
					dataset.addValue(tKeDoanhThu.getSoTien(), "Số tiền", tKeDoanhThu.getDoiTuong());
				}
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
				ChartPanel chartPanel = new ChartPanel( createChart() );
				pnSouth.removeAll();
				pnSouth.revalidate();
				pnSouth.repaint();
				pnSouth.add(chartPanel);
				String doiTuong = cbbdoituong.getSelectedItem().toString();
				if (doiTuong.equals("Nhân Viên")) {
					lammoitable();
					SimpleDateFormat spDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String DateMoc = spDateFormat.format(Fromdate.getDate());
					String DateDen = spDateFormat.format(Todate.getDate());
					java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
					java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
					DecimalFormat df = new DecimalFormat("###,###.000VNĐ");

					for (ThongKeDoanhThu thongKeDoanhThu : tkDao.thongKeDoanhThuNV(ngayMocDate, DateDenDate)) {
						Object row[] = {
							thongKeDoanhThu.getDoiTuong(), 
							thongKeDoanhThu.getSoluongHoDon(), 
							thongKeDoanhThu.getSoTien()
						};
						modelTK.addRow(row);
					}
					for (ThongKeDoanhThu tkdtTopDoanhThu : tkDao.thongKeDoanhThuTOPNV(ngayMocDate, DateDenDate)) {
						Object row[] = {
							tkdtTopDoanhThu.getDoiTuong(),
							df.format(tkdtTopDoanhThu.getSoTien())
						};
						modelTopNV.addRow(row);
					}
					FillInText();
				} else {
					lammoitable();
					load();
					FillInText();
				}
			}
		}
		else if (o.equals(btnInTKDT)) {
			Date date = new Date();
			SimpleDateFormat spDateFormat = new SimpleDateFormat("hh:mm:ss");
			String dateString = spDateFormat.format(date);
			String listString[] = dateString.split(":");
			BufferedImage img = new BufferedImage(pnSouth.getWidth(), pnSouth.getHeight(), BufferedImage.TYPE_INT_RGB);

			pnSouth.paint(img.getGraphics());
			
			try {
				String doiTuong = cbbdoituong.getSelectedItem().toString();
				SimpleDateFormat spDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				String DateMoc = spDateFormat2.format(Fromdate.getDate());
				String DateDen = spDateFormat2.format(Todate.getDate());
				Date datenow = new Date();
				SimpleDateFormat nowDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
				java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
				java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
				int i = 0;
				
				if (doiTuong.equals("Nhân Viên")) {
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("DanhSachThongKe");
					Cell cell = null;
					XSSFRow row = null;
					row = sheet.createRow(4);
					
					cell = row.createCell(0,CellType.STRING);
					cell.setCellValue(doiTuong);
					
					cell = row.createCell(1,CellType.STRING);
					cell.setCellValue("DATE");
					
					cell = row.createCell(2,CellType.STRING);
					cell.setCellValue("SỐ HOÁ ĐƠN");
					
					cell = row.createCell(3,CellType.STRING);
					cell.setCellValue("TỔNG TIỀN");
					
					for (ThongKeDoanhThu tk : tkDao.thongKeDoanhThuNV(ngayMocDate, DateDenDate)) {
						i++;
						
						row = sheet.createRow(4+i);
						
						cell = row.createCell(1,CellType.STRING);
						cell.setCellValue(tk.getDoiTuong());
						
						cell = row.createCell(2,CellType.STRING);
						cell.setCellValue(tk.getSoluongHoDon());
						
						cell = row.createCell(3,CellType.STRING);
						cell.setCellValue(tk.getSoTien());
					}
					System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
					try {
						FileOutputStream fos = new FileOutputStream(
							new File(
								new File("./thongKe/thongKeDoanhThuNhanVien/excels/" + nowDateFormat.format(datenow) + ".xlsx").getAbsolutePath()
							)
						);
						workbook.write(fos);
						fos.close();
						
						JOptionPane.showMessageDialog(this, "In excel thong ke doanh thu nhan vien thanh cong");
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					try {
						ImageIO.write(
							img, 
							"png", 
							// new File("C:" + listString[0] + listString[1] + listString[2] + ".png")
							new File(new File("./thongKe/thongKeDoanhThuNhanVien/hinhAnh/" + listString[0] + listString[1] + listString[2] + ".png").getAbsolutePath())
						);
						JOptionPane.showMessageDialog(
							null, 
							"Xuất Thống Kê Doanh Thu " + cbbdoituong.getSelectedItem().toString() + " Thành Công"
						);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("DanhSachThongKe");
					Cell cell = null;
					XSSFRow row = null;
					row= sheet.createRow(4);
					
					cell = row.createCell(0,CellType.STRING);
					cell.setCellValue(doiTuong);
					
					cell = row.createCell(1,CellType.STRING);
					cell.setCellValue("Họ Và Tên");
					
					cell = row.createCell(2,CellType.STRING);
					cell.setCellValue("SỐ HOÁ ĐƠN");
					
					cell = row.createCell(3,CellType.STRING);
					cell.setCellValue("TỔNG TIỀN");
					
					for (ThongKeDoanhThu tk : tkDao.thongKeDoanhThuKH(ngayMocDate, DateDenDate)) {
						i++;
						
						row = sheet.createRow(4+i);
						
						cell = row.createCell(1,CellType.STRING);
						cell.setCellValue(tk.getDoiTuong());
						cell = row.createCell(2,CellType.STRING);
						cell.setCellValue(tk.getSoluongHoDon());
						
						cell = row.createCell(3,CellType.STRING);
						cell.setCellValue(tk.getSoTien());
					}
					System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
					try {
						FileOutputStream fos = new FileOutputStream(
							new File(
								new File("./thongKe/thongKeDoanhThuKhachHang/excels/" + nowDateFormat.format(datenow) + ".xlsx").getAbsolutePath()
							)
						);
						workbook.write(fos);
						fos.close();
						JOptionPane.showMessageDialog(this, "In excel thong ke doanh thu khach hang thanh cong");
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					try {
						ImageIO.write(
							img, 
							"png", 
							// new File("C:" + listString[0] + listString[1] + listString[2] + ".png")
							new File(new File("./thongKe/thongKeDoanhThuKhachHang/hinhAnh/" + listString[0] + listString[1] + listString[2] + ".png").getAbsolutePath())
						);
						JOptionPane.showMessageDialog(
							null, 
							"Xuất Thống Kê Doanh Thu " + cbbdoituong.getSelectedItem().toString() + " Thành Công"
						);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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
		while (modelTopNV.getRowCount()!=0) {
			modelTopNV.removeRow(0);
		}
	}
	public void FillInText() {
		int total = 0;
		double totaltien = 0;
		for (int i = 0; i < modelTK.getRowCount(); i++) {
			int sum =  (int) tableTKDT.getValueAt(i, 1);
			Double sumtien = (Double) tableTKDT.getValueAt(i, 2);
			total += sum;
			totaltien += sumtien;
		}
		DecimalFormat deci = new DecimalFormat("###,###.000VND");

		String stringhd= Integer.toString(total);

		txttongtien.setText("");
		txttongHD.setText("");
		txttongtien.setText(deci.format(totaltien));
		txttongHD.setText(stringhd);
	}
	public boolean CheckFormTKSP() {
		SimpleDateFormat spDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date nowDate = new Date();
		if (Fromdate.getDate() ==null || !(spDateFormat.format(Fromdate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))) {
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng dd/MM/20yy");
			return false;
		}
		else if (Todate.getDate() ==null || !(spDateFormat.format(Todate.getDate()).toString().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]20\\d{2}$"))){
			JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Không Được Rỗng Và Phải Đúng Định Dạng");
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
	public void load() {
		String DateMoc = spDateFormat.format(Fromdate.getDate());
		String DateDen = spDateFormat.format(Todate.getDate());
		java.sql.Date ngayMocDate = java.sql.Date.valueOf(DateMoc);
		java.sql.Date DateDenDate = java.sql.Date.valueOf(DateDen);
		DecimalFormat df = new DecimalFormat("###,###.000VND");

		for (ThongKeDoanhThu thongKeDoanhThu : tkDao.thongKeDoanhThuKH(ngayMocDate, DateDenDate)) {
			Object row[] = {
				thongKeDoanhThu.getDoiTuong(),
				thongKeDoanhThu.getSoluongHoDon(),
				thongKeDoanhThu.getSoTien()};
			modelTK.addRow(row);
		}
		for (ThongKeDoanhThu tkdtTopDoanhThu : tkDao.thongKeTOPDoanhThuKH(ngayMocDate, DateDenDate)) {
			Object row[] = {
				tkdtTopDoanhThu.getDoiTuong(), 
				df.format(tkdtTopDoanhThu.getSoTien())};
			modelTopNV.addRow(row);
		}
	}
}