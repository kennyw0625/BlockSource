package BS;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public class bs {

	static Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
	static LookAndFeel defaultlf = UIManager.getLookAndFeel();
	static boolean lightmode = true;
	static URL TopBar = bs.class.getResource("TopBar.png");
	static URL SplashScreenLight = bs.class.getResource("SplashScreenLight.png");
	static URL BlockSource = bs.class.getResource("BlockSource.png");
	static ImageIcon HintHelpIcon = new ImageIcon(bs.class.getResource("HintHelpIcon.png"));
	static ImageIcon NewIcon = new ImageIcon(bs.class.getResource("NewIcon.png"));
	static ImageIcon OpenFileIcon = new ImageIcon(bs.class.getResource("OpenFileIcon.png"));
	static ImageIcon SaveIcon = new ImageIcon(bs.class.getResource("SaveIcon.png"));
	static ImageIcon SaveAsIcon = new ImageIcon(bs.class.getResource("SaveAsIcon.png"));
	static ImageIcon CutIcon = new ImageIcon(bs.class.getResource("CutIcon.png"));
	static ImageIcon CopyIcon = new ImageIcon(bs.class.getResource("CopyIcon.png"));
	static ImageIcon PasteIcon = new ImageIcon(bs.class.getResource("PasteIcon.png"));
	static ImageIcon CodeAreaIcon = new ImageIcon(bs.class.getResource("CodeAreaIcon.png"));
	static ImageIcon ToolBoxIcon = new ImageIcon(bs.class.getResource("ToolBoxIcon.png"));
	static ImageIcon ConsoleIcon = new ImageIcon(bs.class.getResource("ConsoleIcon.png")); 
	@SuppressWarnings("rawtypes")
	static DefaultListModel CodeItems = new DefaultListModel();								
	static File OpenedFile;
	static HashMap<String, String> storedVariables = new HashMap<String, String>();
	static LinkedList <JButton> blocks = new LinkedList<JButton>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
	}

}
