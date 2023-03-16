package GUI;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen_2 {
	public static void main(String[] arg) throws MalformedURLException {
		JWindow window = new JWindow();

		JLabel lbl = new JLabel(
			new ImageIcon(
				new File("./images/screenLoading.gif").getAbsolutePath()
			), 
			SwingConstants.CENTER
		);
		window.getContentPane().add(lbl);
		window.setBounds(500, 150, 480, 240);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		try {
			Thread.sleep(4100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		window.dispose();
		new GUI_DangNhap().setVisible(true);

	}
}
