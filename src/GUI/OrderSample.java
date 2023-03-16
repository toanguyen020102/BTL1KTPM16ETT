package GUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.NhanVien;

public class OrderSample {
	private PdfPTable tblOrderInfo = new PdfPTable(4);
	private PdfPTable tblOrderDetail = new PdfPTable(4);
	private PdfPTable tblPurchaseInfo = new PdfPTable(2);

	public OrderSample() {

	}
	
	public void printOrder(String orderID) {
		String FILE = "./orders/" + orderID + ".pdf";
		try {
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(FILE));
			doc.open();
			addHeader(doc);
			doc.add(tblOrderInfo);
			doc.add(addHeaderOrderDetail());
			doc.add(tblOrderDetail);
			doc.add(tblPurchaseInfo);
			addFooter(doc);
			doc.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	private void addHeader(Document doc) throws DocumentException {
		
		Paragraph pNameStore = new Paragraph("LNTN Shop");
		pNameStore.setAlignment(Element.ALIGN_CENTER);
		doc.add(pNameStore);

		addEmptyLine(doc, 1);

		Paragraph pAddress = new Paragraph("12 Le Van Bao, Phuong 5, Quan Go Vap, TP Ho Chi Minh");
		pAddress.setAlignment(Element.ALIGN_CENTER);
		doc.add(pAddress);
		
		Paragraph pTitle = new Paragraph("HOA DON BAN HANG");
		pTitle.setAlignment(Element.ALIGN_CENTER);
		doc.add(pTitle);
		
		addEmptyLine(doc, 1);
	}
	
	public PdfPTable addOrderInfo(String ngayTaoHoaDon, String maHoaDon, String maNhanVien) throws DocumentException {
		

		PdfPCell cellNgayBan = new PdfPCell();
		addCellBorderAlign(cellNgayBan, "Ngay Ban", "l");
		tblOrderInfo.addCell(cellNgayBan);

		PdfPCell cellNgayBanContent = new PdfPCell();
		addCellBorderAlign(cellNgayBanContent, ngayTaoHoaDon+" ", "r");
		tblOrderInfo.addCell(cellNgayBanContent);
		
		PdfPCell cellNhanVien = new PdfPCell();
		addCellBorderAlign(cellNhanVien, "Nhan Vien", "l");
		tblOrderInfo.addCell(cellNhanVien);

		PdfPCell cellNhanVienContent = new PdfPCell();
		addCellBorderAlign(cellNhanVienContent, maNhanVien + " ", "r");
		tblOrderInfo.addCell(cellNhanVienContent);
		
		PdfPCell cellHoaDon = new PdfPCell();
		addCellBorderAlign(cellHoaDon, "Hoa Don", "l");
		tblOrderInfo.addCell(cellHoaDon);

		PdfPCell cellHoaDonContent = new PdfPCell();
		cellHoaDonContent.setColspan(3);
		addCellBorderAlign(cellHoaDonContent, maHoaDon + " ", "l");
		tblOrderInfo.addCell(cellHoaDonContent);

//		addCellNull(tblOrderInfo);
//		addCellNull(tblOrderInfo);
		
		return tblOrderInfo;
	}
	
	private PdfPTable addHeaderOrderDetail() throws DocumentException {
		
		PdfPTable tbl = new PdfPTable(4);

		PdfPCell cellHoaDon = new PdfPCell();
		addCellBorderAlign(cellHoaDon, "San Pham", "l");
		tbl.addCell(cellHoaDon);

		PdfPCell cellHoaDonContent = new PdfPCell();
		addCellBorderAlign(cellHoaDonContent, "So Luong", "r");
		tbl.addCell(cellHoaDonContent);

		PdfPCell cellNhanVien = new PdfPCell();
		addCellBorderAlign(cellNhanVien, "Don Gia", "r");
		tbl.addCell(cellNhanVien);

		PdfPCell cellNhanVienContent = new PdfPCell();
		addCellBorderAlign(cellNhanVienContent, "Thanh Tien", "r");
		tbl.addCell(cellNhanVienContent);

		PdfPCell cellNull = new PdfPCell();
		cellNull.setColspan(4);
		addCellBorderAlign(cellNull, " ", "l");
		tbl.addCell(cellNull);

		return tbl;
	}
	

	public PdfPTable addCellOrderDetail(String ten, String ma, String soLuong, String donGia, String thanhTien) {
		
		PdfPCell cellTenSP = new PdfPCell();
		cellTenSP.setColspan(4);
		addCellBorderAlign(cellTenSP, ten + " ", "l");
		tblOrderDetail.addCell(cellTenSP);

		PdfPCell cellMaSP = new PdfPCell();
		addCellBorderAlign(cellMaSP, ma + " ", "l");
		tblOrderDetail.addCell(cellMaSP);

		PdfPCell cellSoLuongSP = new PdfPCell();
		addCellBorderAlign(cellSoLuongSP, soLuong + " ", "r");
		tblOrderDetail.addCell(cellSoLuongSP);

		PdfPCell cellDonGiaSP = new PdfPCell();
		addCellBorderAlign(cellDonGiaSP, donGia + " ", "r");
		tblOrderDetail.addCell(cellDonGiaSP);

		PdfPCell cellThanhTienSP = new PdfPCell();
		addCellBorderAlign(cellThanhTienSP, thanhTien + " ", "r");
		tblOrderDetail.addCell(cellThanhTienSP);

		PdfPCell cellNull = new PdfPCell();
		cellNull.setColspan(4);
		addCellBorderAlign(cellNull, " ", "l");
		tblOrderDetail.addCell(cellNull);
		
		return tblOrderDetail;
	}

	
	public PdfPTable addPurchaseInfo(String tongTien, String tienGiam, String tienPhaiThanhToan, String tienNhan, String tienTra) throws DocumentException {
		
		PdfPCell cellTongTien = new PdfPCell();
		
		// Row Total
		addCellBorderAlign(cellTongTien, "Tong Tien", "l");
		cellTongTien.setBorderWidthTop(1);
		tblPurchaseInfo.addCell(cellTongTien);

		PdfPCell cellTongTienContent = new PdfPCell();
		addCellBorderAlign(cellTongTienContent, tongTien + " ", "r");
		cellTongTienContent.setBorderWidthTop(1);
		tblPurchaseInfo.addCell(cellTongTienContent);

		// Row Discount
		PdfPCell cellTienGiam = new PdfPCell();
		addCellBorderAlign(cellTienGiam, "Tien Giam", "l");
		tblPurchaseInfo.addCell(cellTienGiam);
		
		PdfPCell cellTongGiamContent = new PdfPCell();
		addCellBorderAlign(cellTongGiamContent, tienGiam + " ", "r");
		cellTongGiamContent.setBorderWidthTop(0);
		tblPurchaseInfo.addCell(cellTongGiamContent);

		// Row Payment
		PdfPCell cellTienPhaiThanhToan = new PdfPCell();
		addCellBorderAlign(cellTienPhaiThanhToan, "Tien Phai Thanh Toan", "l");
		tblPurchaseInfo.addCell(cellTienPhaiThanhToan);
		
		PdfPCell cellTienPhaiThanhToanContent = new PdfPCell();
		addCellBorderAlign(cellTienPhaiThanhToanContent, tienPhaiThanhToan + " ", "r");
		cellTienPhaiThanhToanContent.setBorderWidthTop(0);
		tblPurchaseInfo.addCell(cellTienPhaiThanhToanContent);

		// Row Recieve
		PdfPCell cellTienNhan = new PdfPCell();
		addCellBorderAlign(cellTienNhan, "Tien Nhan", "l");
		tblPurchaseInfo.addCell(cellTienNhan);

		PdfPCell cellTienNhanContent = new PdfPCell();
		addCellBorderAlign(cellTienNhanContent, tienNhan + " ", "r");
		tblPurchaseInfo.addCell(cellTienNhanContent);

		// Row Change
		PdfPCell cellTienTra = new PdfPCell();
		addCellBorderAlign(cellTienTra, "Tien Tra", "l");
		tblPurchaseInfo.addCell(cellTienTra);

		PdfPCell cellTienTraContent = new PdfPCell();
		addCellBorderAlign(cellTienTraContent, tienTra + " ", "r");
		tblPurchaseInfo.addCell(cellTienTraContent);

		PdfPCell cellNotice = new PdfPCell();
		cellNotice.setColspan(2);
		addCellBorderAlign(cellNotice, "(Gia Da Bao Gom Thue GTGT)", "c");
		tblPurchaseInfo.addCell(cellNotice);
		
		return tblPurchaseInfo;
	}

	private void addFooter(Document doc) throws DocumentException {
		
		addEmptyLine(doc, 2);

		Paragraph pCamOn = new Paragraph("CAM ON QUY KHACH VA HEN GAP LAI");
		pCamOn.setAlignment(Element.ALIGN_CENTER);
		doc.add(pCamOn);

		Paragraph pLienHe = new Paragraph("Hotline: 0912345678");
		pLienHe.setAlignment(Element.ALIGN_CENTER);
		doc.add(pLienHe);
	}

	private void addEmptyLine(Document doc, int line) throws DocumentException {
		
		for(int i = 0; i < line; i++) {
			doc.add(new Paragraph(" "));
		}
	}

	private  void addCellNull(PdfPTable table) {
		
		PdfPCell cellNull = new PdfPCell();
		cellNull.setBorder(0);
		table.addCell(cellNull);
	}

	private  PdfPCell addCellBorderAlign(PdfPCell cell, String content, String align) {
		
		Paragraph p = new Paragraph(content + "");
		cell.setBorder(0);
		if (align.equalsIgnoreCase("r")) {
			p.setAlignment(Element.ALIGN_RIGHT);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else if (align.equalsIgnoreCase("l")) {
			p.setAlignment(Element.ALIGN_LEFT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align.equalsIgnoreCase("c")) {
			p.setAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		cell.addElement(p);
		return cell;
	}

}
