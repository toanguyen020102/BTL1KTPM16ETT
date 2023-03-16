package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import DAO.ChiTietHoaDon_DAO;
import DAO.HoaDon_DAO;
import connect_DB.Connect_DB;
import entity.*;

public class GUI_QuanLiHoaDon extends JPanel implements ActionListener, MouseListener  {
  private JPanel pnlEast,
                  pnlNorth, 
                  pnlCenter, 
                  pnlTimKiem, 
                  pnlChucNang, 
                  pnlChiTietHoaDon, 
                  pnlThongTinHoaDon,

                  pnlThongTinMaHoaDon, 
                  pnlThongTinMaNhanVien, 
                  pnlThongTinMaKhachHang, 
                  pnlThongTinThoiGianTaoHoaDon, 
                  pnlThongTinCaLamViec, 
                  pnlThongTinTongTien, 
                  pnlThongTinTongGiam, 
                  pnlThongTinTongPhaiTra, 
                  pnlThongTinTienNhan, 
                  pnlThongTinTienTra;

  private JLabel lblMaHoaDon, 
                  lblTieuDeMaHoaDon, 

                  lblThongTinHoaDon,
                  lblThongTinMaHoaDon,
                  lblThongTinMaNhanVien,
                  lblThongTinMaKhachHang,
                  lblThongTinThoiGianTaoHoaDon,
                  lblThongTinCaLamViec,
                  lblThongTinTongTien,
                  lblThongTinTongGiam,
                  lblThongTinTongPhaiTra, 
                  lblThongTinTienNhan, 
                  lblThongTinTienTra, 

                  lblValueHoaDon,
                  lblValueMaHoaDon,
                  lblValueMaNhanVien,
                  lblValueMaKhachHang,
                  lblValueThoiGianTaoHoaDon,
                  lblValueCaLamViec,
                  lblValueTongTien,
                  lblValueTongGiam,
                  lblValueTongPhaiTra, 
                  lblValueTienNhan, 
                  lblValueTienTra;

  private JTextField txtTimKiem;

  private JTable tableHoaDon,
                  tableChiTietHoaDon;

  private DefaultTableModel dtmHoaDon, 
                            dtmChiTietHoaDon;

  private HoaDon_DAO hd_DAO = new HoaDon_DAO();

  public GUI_QuanLiHoaDon(NhanVien nv) {
    panel_QuanLiHoaDon();
    setBackground(new Color(147, 190, 221));
		Connect_DB.getConnection();
  } 
  public void panel_QuanLiHoaDon() {
    setLayout(new BorderLayout());

    // PANEL NORTH
    pnlNorth = new JPanel();
    pnlNorth.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    pnlNorth.setLayout(new BorderLayout());

    lblMaHoaDon = new JLabel("Tu khoa tim kiem");

    txtTimKiem = new JTextField();

    txtTimKiem.setPreferredSize(
      new Dimension(
        txtTimKiem.getPreferredSize().width,
        lblMaHoaDon.getPreferredSize().height
      )
    );

    txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TimKiemKeyRelease(evt);
			}
			private void TimKiemKeyRelease(KeyEvent evt) {
				DefaultTableModel tableModel = (DefaultTableModel) tableHoaDon.getModel();
				String search = txtTimKiem.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
				tableHoaDon.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
			}
		});

    pnlTimKiem = new JPanel();
    pnlTimKiem.setLayout(new BoxLayout(pnlTimKiem, BoxLayout.X_AXIS));
    pnlTimKiem.add(lblMaHoaDon);
    pnlTimKiem.add(Box.createHorizontalStrut(10));
    pnlTimKiem.add(txtTimKiem);

    pnlChucNang = new JPanel();
    pnlChucNang.setLayout(new BoxLayout(pnlChucNang, BoxLayout.Y_AXIS));

    pnlChucNang.add(pnlTimKiem);
    pnlChucNang.add(Box.createVerticalStrut(10));

    pnlNorth.add(pnlChucNang, BorderLayout.NORTH);

    pnlChiTietHoaDon = new JPanel();
    pnlChiTietHoaDon.setLayout(new BoxLayout(pnlChiTietHoaDon, BoxLayout.Y_AXIS));
    pnlChiTietHoaDon.setPreferredSize(
      new Dimension(
        300, 
        30
      )
    );

    // lblTieuDeChiTietHoaDon = new JLabel("Chi Tiet Hoa Don");

    // pnlChiTietHoaDon.add(lblTieuDeChiTietHoaDon);

    String rowCTHD[] = {
      "So luong",
      "Ma San pham",
      "Ma Hoa don",
      "Kich co", 
      "Mau sac", 
      "Gia ban" 
    };
    dtmChiTietHoaDon = new DefaultTableModel(rowCTHD, 0);
    tableChiTietHoaDon = new JTable(dtmChiTietHoaDon);
    
    tableChiTietHoaDon.setIntercellSpacing(new Dimension(10, 10));
    tableChiTietHoaDon.setRowHeight(30);
    tableChiTietHoaDon.setDefaultEditor(Object.class, null);
    JScrollPane scrollCTHD = new JScrollPane();
    scrollCTHD.setViewportView(tableChiTietHoaDon);
    tableChiTietHoaDon.setAutoCreateRowSorter(true);

    pnlChiTietHoaDon.add(scrollCTHD);

    pnlNorth.add(pnlChiTietHoaDon, BorderLayout.CENTER);

    pnlThongTinHoaDon = new JPanel();
    pnlThongTinHoaDon.setLayout(new BoxLayout(pnlThongTinHoaDon, BoxLayout.Y_AXIS));

    lblThongTinHoaDon = new JLabel("Thong tin hoa don");
    lblThongTinMaHoaDon = new JLabel("Ma hoa don");
    lblThongTinMaNhanVien = new JLabel("Ma nhan vien");
    lblThongTinMaKhachHang  = new JLabel("Ma khach hang");
    lblThongTinThoiGianTaoHoaDon = new JLabel("Thoi gian tao hoa don");
    lblThongTinCaLamViec = new JLabel("Ca lam viec");
    lblThongTinTongTien = new JLabel("Tong tien");
    lblThongTinTongGiam = new JLabel("Tong giam");
    lblThongTinTongPhaiTra = new JLabel("Tong phai tra");
    lblThongTinTienNhan = new JLabel("Tien nhan");
    lblThongTinTienTra = new JLabel("Tien tra");

    lblValueHoaDon = new JLabel("?");
    lblValueMaHoaDon = new JLabel("?");
    lblValueMaNhanVien = new JLabel("?");
    lblValueMaKhachHang  = new JLabel("?");
    lblValueThoiGianTaoHoaDon = new JLabel("?");
    lblValueCaLamViec = new JLabel("?");
    lblValueTongTien = new JLabel("?");
    lblValueTongGiam = new JLabel("?");
    lblValueTongPhaiTra = new JLabel("?");
    lblValueTienNhan = new JLabel("?");
    lblValueTienTra = new JLabel("?");

    pnlThongTinMaHoaDon = new JPanel();
    pnlThongTinMaNhanVien = new JPanel();
    pnlThongTinMaKhachHang = new JPanel();
    pnlThongTinThoiGianTaoHoaDon = new JPanel();
    pnlThongTinCaLamViec = new JPanel();
    pnlThongTinTongTien = new JPanel();
    pnlThongTinTongGiam = new JPanel();
    pnlThongTinTongPhaiTra = new JPanel();
    pnlThongTinTienNhan = new JPanel();
    pnlThongTinTienTra = new JPanel();

    pnlThongTinMaHoaDon.setLayout(new BoxLayout(pnlThongTinMaHoaDon, BoxLayout.X_AXIS));
    pnlThongTinMaNhanVien.setLayout(new BoxLayout(pnlThongTinMaNhanVien, BoxLayout.X_AXIS));
    pnlThongTinMaKhachHang.setLayout(new BoxLayout(pnlThongTinMaKhachHang, BoxLayout.X_AXIS));
    pnlThongTinThoiGianTaoHoaDon.setLayout(new BoxLayout(pnlThongTinThoiGianTaoHoaDon, BoxLayout.X_AXIS));
    pnlThongTinCaLamViec.setLayout(new BoxLayout(pnlThongTinCaLamViec, BoxLayout.X_AXIS));
    pnlThongTinTongTien.setLayout(new BoxLayout(pnlThongTinTongTien, BoxLayout.X_AXIS));
    pnlThongTinTongGiam.setLayout(new BoxLayout(pnlThongTinTongGiam, BoxLayout.X_AXIS));
    pnlThongTinTongPhaiTra.setLayout(new BoxLayout(pnlThongTinTongPhaiTra, BoxLayout.X_AXIS));
    pnlThongTinTienNhan.setLayout(new BoxLayout(pnlThongTinTienNhan, BoxLayout.X_AXIS));
    pnlThongTinTienTra.setLayout(new BoxLayout(pnlThongTinTienTra, BoxLayout.X_AXIS));

    pnlThongTinMaHoaDon.add(lblThongTinMaHoaDon);
    pnlThongTinMaHoaDon.add(lblValueMaHoaDon);

    pnlThongTinMaNhanVien.add(lblThongTinMaNhanVien);
    pnlThongTinMaNhanVien.add(lblValueMaNhanVien);

    pnlThongTinMaKhachHang.add(lblThongTinMaHoaDon);
    pnlThongTinMaKhachHang.add(lblValueMaHoaDon);

    pnlThongTinThoiGianTaoHoaDon.add(lblThongTinMaHoaDon);
    pnlThongTinThoiGianTaoHoaDon.add(lblValueMaHoaDon);

    pnlThongTinCaLamViec.add(lblThongTinMaHoaDon);
    pnlThongTinCaLamViec.add(lblValueMaHoaDon);

    pnlThongTinTongTien.add(lblThongTinMaHoaDon);
    pnlThongTinTongTien.add(lblValueMaHoaDon);

    pnlThongTinTongGiam.add(lblThongTinMaHoaDon);
    pnlThongTinTongGiam.add(lblValueMaHoaDon);

    pnlThongTinTongPhaiTra.add(lblThongTinMaHoaDon);
    pnlThongTinTongPhaiTra.add(lblValueMaHoaDon);

    pnlThongTinTienNhan.add(lblThongTinMaHoaDon);
    pnlThongTinTienNhan.add(lblValueMaHoaDon);

    pnlThongTinTienTra.add(lblThongTinMaHoaDon);
    pnlThongTinTienTra.add(lblValueMaHoaDon);

    int gap = 10;

    pnlThongTinHoaDon.add(pnlThongTinMaHoaDon);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinMaNhanVien);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinMaKhachHang);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinThoiGianTaoHoaDon);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinCaLamViec);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinTongTien);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinTongGiam);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinTongPhaiTra);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinTienNhan);
    pnlThongTinHoaDon.add(Box.createVerticalStrut(gap));
    pnlThongTinHoaDon.add(pnlThongTinTienTra);

    pnlNorth.add(pnlThongTinHoaDon, BorderLayout.SOUTH);

    // PANEL CENTER
    pnlCenter = new JPanel();
    pnlCenter.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));

    lblTieuDeMaHoaDon = new JLabel("Ma Hoa Don");

    pnlCenter.add(lblTieuDeMaHoaDon);
    
    String row[] = {
      "Ma hoa don", 
      "Ma nhan vien", 
      "Ma khach hang",
      "Thoi gian tao hoa don", 
      "Ca lam viec", 
      "Tong tien", 
      "Tong giam", 
      "Tong phai tra", 
      "Tien nhan", 
      "Tien tra"
    };
    dtmHoaDon = new DefaultTableModel(row, 0);
    tableHoaDon = new JTable(dtmHoaDon);
    tableHoaDon.setIntercellSpacing(new Dimension(10, 10));
    tableHoaDon.setRowHeight(30);
    tableHoaDon.setDefaultEditor(Object.class, null);
    JScrollPane scroll = new JScrollPane();
    scroll.setViewportView(tableHoaDon);
    tableHoaDon.setAutoCreateRowSorter(true);

    pnlCenter.add(scroll);

    // PANEL EAST
    pnlEast = new JPanel();
    pnlEast.setLayout(new BoxLayout(pnlEast, BoxLayout.Y_AXIS));

    // lblTieuDeChiTietHoaDon = new JLabel("Chi Tiet Hoa Don");

    // pnlEast.add(lblTieuDeChiTietHoaDon);

    // String rowCTHD[] = {
    //   "So luong",
    //   "Ma San pham",
    //   "Ten San pham", 
    //   "Ma Hoa don",
    //   "Kich co", 
    //   "Mau sac", 
    //   "Gia ban" 
    // };
    // dtmChiTietHoaDon = new DefaultTableModel(rowCTHD, 0);
    // tableChiTietHoaDon = new JTable(dtmChiTietHoaDon);
    // tableChiTietHoaDon.setIntercellSpacing(new Dimension(10, 10));
    // tableChiTietHoaDon.setRowHeight(30);
    // tableChiTietHoaDon.setDefaultEditor(Object.class, null);
    // JScrollPane scrollCTHD = new JScrollPane();
    // scrollCTHD.setViewportView(tableChiTietHoaDon);
    // tableChiTietHoaDon.setAutoCreateRowSorter(true);

    // pnlEast.add(scrollCTHD);

    // 

    // add(pnlEast, BorderLayout.EAST);
    add(pnlNorth, BorderLayout.WEST);
    add(pnlCenter, BorderLayout.CENTER);

    loadHoaDon();

    tableHoaDon.addMouseListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
  }

  public void loadHoaDon() {
    for (HoaDon hd : hd_DAO.getAllHoaDon()) {
      String row[] = {
        hd.getMaHoaDon(),
        hd.getKhachHang().getMaKhachHang(),
        hd.getNhanVien().getMaNhanVien(),
        hd.getNgayTaoHoaDon() + "",
        hd.getCaLamViec() + "",
        hd.getTongCong() + "",
        hd.getTienGiam() + "",
        hd.getTongCong() + "",
        hd.getTienNhan() + "",
        hd.getTienTra() + ""
      };
      dtmHoaDon.addRow(row);
    }
  }
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void mousePressed(MouseEvent e) {
    Object o = e.getSource();

    dtmChiTietHoaDon.setRowCount(0);

		if (o.equals(tableHoaDon)) {
      int row = tableHoaDon.getSelectedRow();
      ChiTietHoaDon_DAO cthd_DAO = new ChiTietHoaDon_DAO();
      ArrayList<ChiTietHoaDon> dsChiTietHoaDons = cthd_DAO.selectChiTietHoaDonTheoMa(tableHoaDon.getValueAt(row, 0) + "");

      for (ChiTietHoaDon cthd : dsChiTietHoaDons) {
        Object rowCTHD[] = {
          cthd.getSoLuong(), 
          cthd.getSanPham().getMaSP(),
          cthd.getHoaDon().getMaHoaDon(),
          cthd.getKichCo(),
          cthd.getMauSac(),
          cthd.getGiaBan()
        };
        dtmChiTietHoaDon.addRow(rowCTHD);
      }
    }
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
}