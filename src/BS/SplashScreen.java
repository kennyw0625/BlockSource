package BS;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SplashScreen {
	
	public static void CallSplashScreen() throws IOException, InterruptedException {
		StartSplashScreen();
	}
	
	private static void StartSplashScreen() throws IOException, InterruptedException {
		
		JFrame frame = new JFrame();
		JLabel label = new JLabel();
		Image icon = ImageIO.read(bs.BlockSource);
		
		frame.setIconImage(icon);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().add(label);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		
		BufferedImage image = ImageIO.read(bs.SplashScreenLight);
		BufferedImage bufferedimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D createGraphics = bufferedimage.createGraphics();

		float a[] = new float[] {1f,1f,1f,1f};
		float b[] = new float[] {0,0,0,0};

		createGraphics.drawImage(image, null, 0, 0);
		RescaleOp r = new RescaleOp(a, b, null);
		BufferedImage splashscreen = r.filter(bufferedimage, null);
		
		label.setIcon(new ImageIcon(splashscreen));
		
		frame.pack();
		frame.setLocation(bs.dimensions.width/2-frame.getSize().width/2, bs.dimensions.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		
		Thread.sleep(0000);
		
		float value = 1f;
		
		for(int i = 0; i < 21; i++) {
			a = new float[] {1f,1f,1f,value};
			r = new RescaleOp(a, b, null);
			
			splashscreen = r.filter(bufferedimage, null);
			label.setIcon(new ImageIcon(splashscreen));
			value-=0.05f;
			
			Thread.sleep(25);
		}
		
		frame.dispose();
		
	}

}
