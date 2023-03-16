
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import entity.NhanVien;

public class GUI_ThongTinCaNhan extends JPanel implements ActionListener{
	private JTextField txtdiachi, 
											txtten, 
											txtEmail;

	private Component txtSDTKH;

	private JDateChooser ngaysinh;

	private Button btnsua, 
									btnluu;
									
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	int with = screenSize.width;
	int height = screenSize.height;

	private ImageIcon imgImage;
	
	public GUI_ThongTinCaNhan(NhanVien nvInfo) {
		setBackground(new Color(147,190,221));
		panelNhanViencon();
		setVisible(true);
	}
	
	private void panelNhanViencon() {
		setLayout(new BorderLayout());
		Box b = Box.createVerticalBox();
		Box bcenter = Box.createHorizontalBox();
		Box b1,b2,b3,b4,b5,b6,b7,b8,bcenter1,bcenter2,boxlb;

		JPanel pnThongTinCaNhan = new JPanel();
		JPanel pnThongTinCaNhanTitle = new JPanel();
		pnThongTinCaNhan.setLayout(new FlowLayout());
		JLabel lbThongTinCaNhan = new JLabel("Thông Tin Nhân Viên");
		lbThongTinCaNhan.setFont(new Font("Verdana",Font.BOLD,30));
		JLabel lbAnhCaNhan = new JLabel("");
		lbAnhCaNhan.setSize(new Dimension(400, 400));
		pnThongTinCaNhanTitle.add(lbThongTinCaNhan);
		pnThongTinCaNhan.add(lbAnhCaNhan);
		lbAnhCaNhan.setBorder(BorderFactory.createTitledBorder("Ảnh Nhân Viên"));
		lbAnhCaNhan.setBorder(BorderFactory.createLineBorder(Color.black));
		b.add(pnThongTinCaNhanTitle);
		b.add(pnThongTinCaNhan);
		b.add(Box.createVerticalStrut(10));
		b.setBorder(BorderFactory.createTitledBorder("Thông Tin Cá Nhân"));
		
		b.add(b1 = Box.createHorizontalBox());			
		JLabel lbten = new JLabel("Tên Nhân Viên: ");
		txtten = new JTextField();
		b1.add(lbten);
		b1.add(txtten);
		b.add(Box.createVerticalStrut(10));
		
		JLabel lbgioitinh = new JLabel("Giới Tính: ");
		JComboBox Gioitinhbox = new JComboBox<>();
		Gioitinhbox.setPreferredSize(new Dimension(100,20));
		Gioitinhbox.addItem("Nam");
		Gioitinhbox.addItem("Nữ");
		b1.add(lbgioitinh);
		b1.add(Gioitinhbox);
		
		JLabel lbdantoc = new JLabel("Dân Tộc: ");
		JComboBox cbbdantoc = new JComboBox<>();
		cbbdantoc.setPreferredSize(new Dimension(100,20));
		cbbdantoc.addItem("kinh");
		cbbdantoc.addItem("Nữ");
		b1.add(lbdantoc);
		b1.add(cbbdantoc);
		
		b.add(Box.createVerticalStrut(20));
		b.add(b2 = Box.createHorizontalBox());
		JLabel lbsdt = new JLabel("Số Điện Thoại: ");
		txtSDTKH = new JTextField();
		b2.add(lbsdt);
		b2.add(txtSDTKH);
		
		JLabel lbngaysinh = new JLabel("Ngày Sinh: ");
		b2.add(lbngaysinh);
		b2.add(ngaysinh = new JDateChooser());
		b2.add(Box.createHorizontalStrut(20));
		JLabel lbemail = new JLabel("Email: ");
		txtEmail = new JTextField();
		b2.add(lbemail);
		b2.add(txtEmail);
		
		b.add(Box.createVerticalStrut(20));
		b.add(b3 = Box.createHorizontalBox());
		JLabel lbthanhpho = new JLabel("Thành Phố: ");
		JComboBox cbbthanhpho = new JComboBox();
		cbbthanhpho.setPreferredSize(new Dimension(150,20));
		b3.add(lbthanhpho);
		b3.add(cbbthanhpho);
		JLabel lbquan = new JLabel("Quận: ");
		JComboBox cbbquanBox = new JComboBox();
		cbbquanBox.setPreferredSize(new Dimension(150,20));
		b3.add(lbquan);
		b3.add(cbbquanBox);
		JLabel lbphuong = new JLabel("Phường: ");
		JComboBox cbbphuong = new JComboBox();
		cbbphuong.setPreferredSize(new Dimension(150,20));
		b3.add(lbphuong);
		b3.add(cbbphuong);
		JLabel lbdiachi = new JLabel("Địa Chỉ: ");
		txtdiachi = new JTextField();
		b3.add(lbdiachi);
		b3.add(txtdiachi);
		
		b.add(Box.createVerticalStrut(20));
		b.add(b4 = Box.createHorizontalBox());
		JLabel lbcmnd = new JLabel("Chứng Minh Nhân dân: ");
		JTextField txtcmnd = new JTextField();
		b4.add(lbcmnd);
		b4.add(txtcmnd);
		JLabel lbngaycap = new JLabel("Ngày Cấp: ");
		JDateChooser DateNgayCap = new JDateChooser();
		b4.add(lbngaycap);
		b4.add(DateNgayCap);
		JLabel lbnoicap = new JLabel("Nơi Cấp: ");
		JTextField txtnoicap = new JTextField();
		b4.add(lbnoicap);
		b4.add(txtnoicap);
		
		b.add(Box.createVerticalStrut(30));
		b.add(b5 = Box.createVerticalBox());
		
	
		b5.add(Box.createVerticalStrut(20));
		b5.add(b6 = Box.createHorizontalBox());
		
		JLabel lbmaNV = new JLabel("Mã Nhân Viên: ");
		JTextField txtManv = new JTextField();
		
		b6.add(lbmaNV);
		b6.add(txtManv);
		JLabel lbngaylam = new JLabel("Ngày vào làm: ");
		JDateChooser DateNgayVaolam = new JDateChooser();
		b6.add(lbngaylam);
		b6.add(DateNgayVaolam);
		b5.add(Box.createVerticalStrut(10));
		b5.add(b7 = Box.createHorizontalBox());
		JLabel lbChucVu = new JLabel("Chức Vụ: ");
		JComboBox cbbchucvu = new JComboBox();
		b7.add(lbChucVu);
		b7.add(cbbchucvu);
		b7.add(Box.createHorizontalStrut(1000));
		b5.add(Box.createVerticalStrut(100));
		
		
		
		b.add(Box.createVerticalStrut(10));
		add(b,BorderLayout.CENTER);
		btnsua = new Button("Sửa");
		btnluu = new Button("Lưu");
	

		JPanel pnlsouth = new JPanel();
//		pnlsouth.setLayout(new BoxLayout(pnlsouth, BoxLayout.X_AXIS));
//		pnlsouth.setLayout(new bord);
		add(pnlsouth,BorderLayout.SOUTH);
//		pnlsouth.add(Box.createHorizontalStrut(550));
		pnlsouth.add(btnsua);
		btnsua.setPreferredSize(new Dimension(160, 45));
//		btnsua.setFont(new Font("Time new roman", 30, 30));
//		pnlsouth.add(Box.createHorizontalStrut(50));
		pnlsouth.add(btnluu);
		btnluu.setPreferredSize(new Dimension(160, 45));
//		pnlsouth.add(Box.createHorizontalStrut(550));
		
		
		pnlsouth.add(Box.createVerticalStrut(10));
		b5.setBackground(new Color(147,190,221));
		b5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		b5.setBorder(BorderFactory.createTitledBorder("Thông Tin Công Việc"));
		
		pnlsouth.setBackground(new Color(147,190,221));
		pnThongTinCaNhan.setBackground(new Color(147,190,221));
		pnThongTinCaNhanTitle.setBackground(new Color(147,190,221));
	
		lbmaNV.setPreferredSize(lbcmnd.getPreferredSize());
		lbsdt.setPreferredSize(lbcmnd.getPreferredSize());
		lbthanhpho.setPreferredSize(lbcmnd.getPreferredSize());
		lbChucVu.setPreferredSize(lbcmnd.getPreferredSize());
		lbten.setPreferredSize(lbcmnd.getPreferredSize());
		
		String imgString = "C:\\Users\\Admin\\Desktop\\imageIcon.jfif";
		imgImage = new ImageIcon(imgString);
		Image imgSanphamnhap = imgImage.getImage();
		Image imgspImage = imgSanphamnhap.getScaledInstance(lbAnhCaNhan.getWidth(),lbAnhCaNhan.getHeight(), Image.SCALE_FAST);
		imgImage = new ImageIcon(imgspImage);
		lbAnhCaNhan.setIcon(imgImage);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}