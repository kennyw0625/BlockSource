package BS;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Element;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JList;

public class Main {
	private static JMenuBar menuBar;
	private static JMenu FileMenu;
	private static JMenu EditMenu;
	private static JMenu ViewMenu;
	private static JButton RunButton;
	private static JButton ThemeButton;
	private static JButton HintButton;

	private static JTextArea lines;
	private static int posX = 0, posY = 0;

	static JFrame frame;
	private static JPanel ContentPanel;
	static JTextArea ConsoleTextArea;
	private static JTextArea CodeTextArea;
	private static JTabbedPane tabbedPane;
	@SuppressWarnings({ "rawtypes"})
	private static JList list;
	private static JToolBar toolBar;
	private static JPanel BlockPanel;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException, InterruptedException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		SplashScreen.CallSplashScreen();
		//try {
		//} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
		//}

		UIManager.put("TabbedPane.selected", Color.GRAY);
		frame = new JFrame("Block Source");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setSize(385, 220);
		Image icon = ImageIO.read(bs.BlockSource);
		frame.setIconImage(icon);

		// Variables
		JPanel TopBarPanel = new JPanel();

		JLabel TopBarLabel = new JLabel();
		JButton MinimizeButton = new JButton();
		MinimizeButton.setIcon(new ImageIcon(Main.class.getResource("MinimizeIcon.png")));
		JButton FullScreenButton = new JButton();	
		FullScreenButton.setIcon(new ImageIcon(Main.class.getResource("FullScreenIcon.png")));
		JButton ExitButton = new JButton();
		ExitButton.setIcon(new ImageIcon(Main.class.getResource("ExitIcon.png")));

		ContentPanel = new JPanel();

		JPanel ToolBoxPanel = new JPanel();

		JPanel CodePanel = new JPanel();	
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		toolBar = new JToolBar();
		UIManager.setLookAndFeel(bs.defaultlf);
		toolBar.setPreferredSize(new Dimension(300, 23));

		menuBar = new JMenuBar();
		toolBar.add(menuBar);

		FileMenu = new JMenu("File");
		JMenuItem NewMenuItem = new JMenuItem("New", bs.NewIcon);
		JMenuItem OpenFileMenuItem = new JMenuItem("Open File...", bs.OpenFileIcon);
		JMenuItem SaveMenuItem = new JMenuItem("Save", bs.SaveIcon);
		JMenuItem SaveAsMenuItem = new JMenuItem("Save As...", bs.SaveAsIcon);

		EditMenu = new JMenu("Edit");
		JMenuItem CutMenuItem = new JMenuItem("Cut", bs.CutIcon);
		JMenuItem CopyMenuItem = new JMenuItem("Copy", bs.CopyIcon);
		JMenuItem PasteMenuItem = new JMenuItem("Paste", bs.PasteIcon);

		ViewMenu = new JMenu("View");
		JMenuItem CodeAreaMenuItem = new JMenuItem("Code Area", bs.CodeAreaIcon);
		JMenuItem ToolBoxMenuItem = new JMenuItem("ToolBox", bs.ToolBoxIcon);
		JMenuItem ConsoleMenuItem = new JMenuItem("Console", bs.ConsoleIcon);

		JMenu MenuSeparator = new JMenu("|");

		RunButton = new JButton("Run (Shift + F5)");
		RunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsoleTextArea.setText(null);
				BlockPanel.removeAll();
				bs.blocks.clear();
				String File [] = CodeTextArea.getText().split("\\r?\\n");
				Parser.parse(File);
				double resized75a = (frame.getWidth() * 0.75);
				int resized75 = (int) resized75a;
				double resized25a = (frame.getWidth() * 0.25);
				int resized25 = (int)resized25a;
				for (int i = 0; i < bs.blocks.size(); i++) {
					bs.blocks.get(i).setLocation(new Point((resized75 + 12), bs.blocks.get(i).getY()));
					bs.blocks.get(i).setSize(new Dimension((resized25 - 50), bs.blocks.get(i).getHeight()));
					BlockPanel.add(bs.blocks.get(i));
				}
			}
		});

		ThemeButton = new JButton("Light/Dark Mode");

		HintButton = new JButton(bs.HintHelpIcon);

		JPanel CodeAreaPanel = new JPanel();
		JPanel BlockAreaPanel = new JPanel();

		JPanel ConsolePanel = new JPanel();
		ConsoleTextArea = new JTextArea();

		// Scroll panes
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane CodeScrollPane = new JScrollPane();
		JScrollPane ConsoleScrollPane = new JScrollPane();
		UIManager.setLookAndFeel(bs.defaultlf);


		// TopBar Panel
		TopBarPanel.setBackground(new Color(81, 36, 81, 204));
		TopBarLabel.setIcon(new ImageIcon(getTopBar()));		
		TopBarPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				posX = e.getX();
				posY = e.getY();
			}
		});
		TopBarPanel.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){		
				frame.setLocation(e.getXOnScreen()-posX, e.getYOnScreen()-posY);		
			}
		});

		// Triple Buttons
		MinimizeButton.setBackground(new Color(81, 36, 81));
		MinimizeButton.setBorderPainted(false);
		MinimizeButton.setFocusable(false);
		MinimizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		MinimizeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				MinimizeButton.setBackground(new Color(150, 79, 150));
			}
			public void mouseExited(MouseEvent e) {
				MinimizeButton.setBackground(new Color(81, 36, 81));
			}
		});

		FullScreenButton.setBackground(new Color(81, 36, 81));
		FullScreenButton.setBorderPainted(false);
		FullScreenButton.setFocusable(false);
		FullScreenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			}
		});
		FullScreenButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				FullScreenButton.setBackground(new Color(150, 79, 150));
			}
			public void mouseExited(MouseEvent e) {
				FullScreenButton.setBackground(new Color(81, 36, 81));
			}
		});

		ExitButton.setBackground(new Color(81, 36, 81));
		ExitButton.setBorderPainted(false);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ExitButton.setBackground(new Color(221, 81, 69));
			}
			public void mouseExited(MouseEvent e) {
				ExitButton.setBackground(new Color(81, 36, 81));
			}
		});

		// Content Panel
		ToolBoxPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ToolBoxPanel.setLayout(new CardLayout(0, 0));

		CodePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		CodePanel.setLayout(new BorderLayout(0, 0));
		CodePanel.add(toolBar, BorderLayout.NORTH);

		ConsolePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ConsolePanel.setLayout(new CardLayout(0, 0));
		ConsolePanel.add(ConsoleScrollPane);

		ConsoleScrollPane.setViewportView(ConsoleTextArea);

		// TooLBox Panel
		ToolBoxPanel.add(scrollPane);

		list = new JList(bs.CodeItems);
		scrollPane.setViewportView(list);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		bs.CodeItems.addElement("--- BASICS ---");
		bs.CodeItems.addElement("Output (new line)");
		bs.CodeItems.addElement("Output (current line)");
		bs.CodeItems.addElement("Output variable");
		bs.CodeItems.addElement("Input");
		bs.CodeItems.addElement("Comment");


		bs.CodeItems.addElement("--- MATH ---");
		bs.CodeItems.addElement("Add");
		bs.CodeItems.addElement("Subtract");
		bs.CodeItems.addElement("Multiply");
		bs.CodeItems.addElement("Divide");
		bs.CodeItems.addElement("Modulo");
		bs.CodeItems.addElement("Power");
		bs.CodeItems.addElement("Square root");


		bs.CodeItems.addElement("--- RANDOM ---");
		bs.CodeItems.addElement("Random integer");
		bs.CodeItems.addElement("Random decimal");


		bs.CodeItems.addElement("--- DATA ---");
		bs.CodeItems.addElement("New string");
		bs.CodeItems.addElement("New integer");
		bs.CodeItems.addElement("New large integer");
		bs.CodeItems.addElement("New decimal");
		bs.CodeItems.addElement("New boolean");
		bs.CodeItems.addElement("New array");
		bs.CodeItems.addElement("New linkedlist");
		bs.CodeItems.addElement("New dictionary");


		bs.CodeItems.addElement("--- LOGIC ---");
		bs.CodeItems.addElement("For loop");
		bs.CodeItems.addElement("While loop");
		bs.CodeItems.addElement("if");
		bs.CodeItems.addElement("else if");
		bs.CodeItems.addElement("else");
		bs.CodeItems.addElement("boolean operators");


		bs.CodeItems.addElement("--- FILE IO ---");
		bs.CodeItems.addElement("New file");
		bs.CodeItems.addElement("New directory");
		bs.CodeItems.addElement("Write to file");
		bs.CodeItems.addElement("Read line from file");
		bs.CodeItems.addElement("Read whole file");


		// Code Panel	
		CodeTextArea = new JTextArea();
		CodeTextArea.setEditable(false);
		CodeTextArea.append("File -> Open File.../New");
		CodePanel.add(tabbedPane, BorderLayout.CENTER);
		lines = new JTextArea("1");
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setForeground(Color.BLACK);
		lines.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lines.setEditable(false);

		CodeTextArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				NewMenuItem.setEnabled(false);
				OpenFileMenuItem.setEnabled(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

		});

		CodeTextArea.getDocument().addDocumentListener(new DocumentListener() {
			public String getText() {
				int position = CodeTextArea.getDocument().getLength();
				Element root = CodeTextArea.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex(position) + 2; i++) {
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			public void changedUpdate(DocumentEvent e) {
				lines.setText(getText());
			}
			public void insertUpdate(DocumentEvent e) {
				lines.setText(getText());
			}
			public void removeUpdate(DocumentEvent e) {
				lines.setText(getText());
			}
		});

		CodeScrollPane.setViewportView(CodeTextArea);
		CodeScrollPane.setRowHeaderView(lines);
		CodeScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		CodeScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 15));

		tabbedPane.addTab("Code", null, CodeAreaPanel, null);
		tabbedPane.addTab("Blocks", null, BlockAreaPanel, null);
		BlockAreaPanel.setLayout(new CardLayout(0, 0));

		BlockPanel = new JPanel();
		BlockAreaPanel.add(BlockPanel, "name_18117298458700");
		tabbedPane.setForeground(Color.LIGHT_GRAY);

		CodeAreaPanel.setLayout(new CardLayout(0, 0));
		CodeAreaPanel.add(CodeScrollPane);

		// Console


		// Configure MenuBar
		menuBar.setBackground(new Color(255, 255, 255));

		// Triple Menu
		FileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		FileMenu.setBackground(new Color(255, 255, 255));
		FileMenu.setFocusPainted(false);
		FileMenu.setFocusable(false);
		FileMenu.setOpaque(true);
		FileMenu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					FileMenu.setBackground(new Color(228, 238, 247));
				}else {
					FileMenu.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					FileMenu.setBackground(new Color(255, 255, 255));
				}else {
					FileMenu.setBackground(Color.DARK_GRAY);
				}
			}
		});

		EditMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		EditMenu.setBackground(new Color(255, 255, 255));
		EditMenu.setFocusPainted(false);
		EditMenu.setFocusable(false);
		EditMenu.setOpaque(true);
		EditMenu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					EditMenu.setBackground(new Color(228, 238, 247));
				}else {
					EditMenu.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					EditMenu.setBackground(new Color(255, 255, 255));
				}else {
					EditMenu.setBackground(Color.DARK_GRAY);
				}
			}
		});

		ViewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ViewMenu.setBackground(new Color(255, 255, 255));
		ViewMenu.setFocusPainted(false);
		ViewMenu.setFocusable(false);
		ViewMenu.setOpaque(true);
		ViewMenu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					ViewMenu.setBackground(new Color(228, 238, 247));
				}else {
					ViewMenu.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					ViewMenu.setBackground(new Color(255, 255, 255));
				}else {
					ViewMenu.setBackground(Color.DARK_GRAY);
				}
			}
		});

		// Separator
		MenuSeparator.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		MenuSeparator.setEnabled(false);

		// Triple Buttons 2.0		
		RunButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		RunButton.setBackground(new Color(225, 225, 225));
		RunButton.setPreferredSize(new Dimension(10, 8));
		RunButton.setBorderPainted(false);
		RunButton.setFocusPainted(false);
		RunButton.setFocusable(false);
		RunButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					RunButton.setBackground(new Color(228, 238, 247));
				}else {
					RunButton.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					RunButton.setBackground(new Color(225, 225, 225));
				}else {
					RunButton.setBackground(Color.DARK_GRAY);
				}
			}
		});

		ThemeButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ThemeButton.setBackground(new Color(225, 225, 225));
		ThemeButton.setPreferredSize(new Dimension(10, 8));
		ThemeButton.setBorderPainted(false);
		ThemeButton.setFocusPainted(false);
		ThemeButton.setFocusable(false);
		ThemeButton.setOpaque(true);
		ThemeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					ThemeButton.setBackground(new Color(228, 238, 247));
				}else {
					ThemeButton.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					ThemeButton.setBackground(new Color(225, 225, 225));
				}else {
					ThemeButton.setBackground(Color.DARK_GRAY);
				}
			}
		});
		ThemeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleTheme();
			}

		});

		HintButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		HintButton.setBackground(new Color(225, 225, 225));
		HintButton.setPreferredSize(new Dimension(10, 0));
		HintButton.setBorderPainted(false);
		HintButton.setFocusPainted(false);
		HintButton.setFocusable(false);
		HintButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if(bs.lightmode) {
					HintButton.setBackground(new Color(228, 238, 247));
				}else {
					HintButton.setBackground(Color.GRAY);
				}
			}
			public void mouseExited(MouseEvent e) {
				if(bs.lightmode) {
					HintButton.setBackground(new Color(225, 225, 225));
				}else {
					HintButton.setBackground(Color.DARK_GRAY);
				}
			}
		});

		// MenuItems
		SaveMenuItem.setEnabled(false);
		SaveAsMenuItem.setEnabled(false);
		CutMenuItem.setEnabled(false);
		CopyMenuItem.setEnabled(false);
		PasteMenuItem.setEnabled(false);
		NewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		FileMenu.add(NewMenuItem);
		NewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewMenuItem.setEnabled(false);
				OpenFileMenuItem.setEnabled(false);
				SaveMenuItem.setEnabled(false);
				SaveAsMenuItem.setEnabled(true);

				CutMenuItem.setEnabled(true);
				CopyMenuItem.setEnabled(true);
				PasteMenuItem.setEnabled(true);

				CodeTextArea.setEditable(true);
				CodeTextArea.setText(null);
			}
		});

		OpenFileMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		FileMenu.add(OpenFileMenuItem);	
		OpenFileMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {}

				JFileChooser fc = new JFileChooser();

				try {
					UIManager.setLookAndFeel(bs.defaultlf);
				} catch (UnsupportedLookAndFeelException e1) {}

				FileNameExtensionFilter filter = new FileNameExtensionFilter("BLCKS FILES", "blcks", "blocksource");
				fc.setFileFilter(filter);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int response = fc.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {

					File file = fc.getSelectedFile();

					try {

						NewMenuItem.setEnabled(false);
						OpenFileMenuItem.setEnabled(false);
						SaveMenuItem.setEnabled(true);
						SaveAsMenuItem.setEnabled(true);


						CutMenuItem.setEnabled(true);
						CopyMenuItem.setEnabled(true);
						PasteMenuItem.setEnabled(true);

						CodeTextArea.setText(null);
						Scanner sc = new Scanner(file);
						bs.OpenedFile = file;
						while(sc.hasNextLine()) {
							CodeTextArea.append(sc.nextLine()+"\n");
						}
						CodeTextArea.setEditable(true);
						sc.close();

					} catch (FileNotFoundException ex) {

						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException exc) {}

						JOptionPane.showMessageDialog(null, "File Not Found", "Info: " + "FileNotFoundException", JOptionPane.INFORMATION_MESSAGE);

						try {
							UIManager.setLookAndFeel(bs.defaultlf);
						} catch (UnsupportedLookAndFeelException exc) {}

					}
				}	
			}
		});

		SaveMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		FileMenu.add(SaveMenuItem);
		SaveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (!bs.OpenedFile.exists()) {
						bs.OpenedFile.createNewFile();

						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException exc) {}

						JOptionPane.showMessageDialog(null, "File Not Found, created a new file", "Info: " + "FileNotFoundException", JOptionPane.INFORMATION_MESSAGE);

						try {
							UIManager.setLookAndFeel(bs.defaultlf);
						} catch (UnsupportedLookAndFeelException exc) {}

					}

					NewMenuItem.setEnabled(true);
					OpenFileMenuItem.setEnabled(true);
					SaveMenuItem.setEnabled(true);
					SaveAsMenuItem.setEnabled(true);
					FileWriter fw = new FileWriter(bs.OpenedFile);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(CodeTextArea.getText());
					bw.close();

				} catch (IOException ex) {}

			}
		});

		SaveAsMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		FileMenu.add(SaveAsMenuItem);
		SaveAsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {}

				JFileChooser fc = new JFileChooser();

				try {
					UIManager.setLookAndFeel(bs.defaultlf);
				} catch (UnsupportedLookAndFeelException e1) {}

				FileNameExtensionFilter filter = new FileNameExtensionFilter("BLCKS FILES", "blcks", "blocksource");
				fc.setFileFilter(filter);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int response = fc.showSaveDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {

					File file = fc.getSelectedFile();

					try {
						if (!file.exists()) {
							if(!file.getName().endsWith(".blcks")) {
								file = new File(file.toString() + ".blcks");
							}
							file.createNewFile();
							bs.OpenedFile = file;

							try {
								UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException exc) {}

							JOptionPane.showMessageDialog(null, "File Not Found, created a new file", "Info: " + "FileNotFoundException", JOptionPane.INFORMATION_MESSAGE);

							try {
								UIManager.setLookAndFeel(bs.defaultlf);
							} catch (UnsupportedLookAndFeelException exc) {}

						}

						NewMenuItem.setEnabled(true);
						OpenFileMenuItem.setEnabled(true);
						SaveMenuItem.setEnabled(true);
						SaveAsMenuItem.setEnabled(true);
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(CodeTextArea.getText());
						bw.close();

					} catch (IOException ex) {}
				}	
			}
		});

		CutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		EditMenu.add(CutMenuItem);
		CutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StringSelection stringSelection = new StringSelection(CodeTextArea.getSelectedText()); 
				CodeTextArea.replaceRange(null, CodeTextArea.getSelectionStart(), CodeTextArea.getSelectionEnd());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

			}
		});

		CopyMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		EditMenu.add(CopyMenuItem);
		CopyMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				StringSelection stringSelection = new StringSelection(CodeTextArea.getSelectedText()); 
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

			}
		});

		PasteMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		EditMenu.add(PasteMenuItem);
		PasteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					//String save = CodeTextArea.getText(CodeTextArea.getCaretPosition(), CodeTextArea.getText().length());
					//CodeTextArea.replaceRange(null, CodeTextArea.getCaretPosition(), CodeTextArea.getText().length());
					CodeTextArea.append((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception ex){}
				
			}
		});

		CodeAreaMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ViewMenu.add(CodeAreaMenuItem);

		ToolBoxMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ViewMenu.add(ToolBoxMenuItem);

		ConsoleMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ViewMenu.add(ConsoleMenuItem);

		// MenuBar Add
		menuBar.add(FileMenu);		
		menuBar.add(EditMenu);
		menuBar.add(ViewMenu);
		menuBar.add(MenuSeparator);
		menuBar.add(RunButton);
		menuBar.add(ThemeButton);
		menuBar.add(HintButton);

		JLabel ResizeLabel = new JLabel();
		ResizeLabel.setIcon(new ImageIcon(Main.class.getResource("ResizeIcon.png")));

		ResizeLabel.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent mouseEvent) {
				int x = mouseEvent.getXOnScreen();
				int y = mouseEvent.getYOnScreen();
				int width = x-frame.getLocation().x;
				int height = y-frame.getLocation().y;
				if(width >= 495 && height >= 350) {
					frame.setSize(new Dimension(width, height));
				}
			}

		});

		// IDK WHAT TO PUT HERE T-T
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					int i = list.getSelectedIndex();
					if(i == 1) {
						CodeTextArea.append("\nprintln(arg)");
					}else if(i == 2) {
						CodeTextArea.append("\nprint(arg)");
					}else if(i == 3) {
						CodeTextArea.append("\nprintvar(var)");
					}else if(i == 4) {
						CodeTextArea.append("\ninput()");
					}else if(i == 5) {
						CodeTextArea.append("\n// ...");
					}else if(i == 7) {
						CodeTextArea.append("\nadd(num1, num2)");
					}else if(i == 8) {
						CodeTextArea.append("\nsubtract(num1, num2)");
					}else if(i == 9) {
						CodeTextArea.append("\nmultiply(num1, num2)");
					}else if(i == 10) {
						CodeTextArea.append("\ndivide(num1, num2)");
					}else if(i == 11) {
						CodeTextArea.append("\nmodulo(num1, num2)");
					}else if(i == 12) {
						CodeTextArea.append("\npower(num1, num2)");
					}else if(i == 13) {
						CodeTextArea.append("\nsqrt(num)");
					}else if(i == 15) {
						CodeTextArea.append("\nrandomInteger(min, max)");
					}else if(i == 16) {
						CodeTextArea.append("\nrandomDecimal(min, max)");
					}else if(i == 18) {
						CodeTextArea.append("\nstring varname = \"arg\";");
					}else if(i == 19) {
						CodeTextArea.append("\nint varname = arg;");
					}else if(i == 20) {
						CodeTextArea.append("\nlong varname = arg;");
					}else if(i == 21) {
						CodeTextArea.append("\ndouble varname = arg;");
					}else if(i == 22) {
						CodeTextArea.append("\nboolean varname = true;");
					}else if(i == 23) {
						CodeTextArea.append("\ndatatype [] varname = new datatype [units];");
					}else if(i == 24) {
						CodeTextArea.append("\nLinkedList <datatype> varname = new LinkedList <datatype>();");
					}else if(i == 25) {
						CodeTextArea.append("\nDictionary <datatype, datatype> varname = new Dictionary <datatype, datatype>();");
					}else if(i == 27) {
						CodeTextArea.append("\nfor num in range (arg1, arg2) :: function1; : function2;");
					}else if(i == 28) {
						CodeTextArea.append("\nwhile (arg) :: function1; : function2;");
					}else if(i == 29) {
						CodeTextArea.append("\nif (args) {\n\t// TODO\n}");
					}else if(i == 30) {
						CodeTextArea.append("\nelse if (args) {\n\t// TODO\n}");
					}else if(i == 31) {
						CodeTextArea.append("\nelse {\n\t// TODO\n}");
					}else if(i == 32) {
						CodeTextArea.append("\n// Boolean operators ;-;");
					}else if(i == 34) {
						CodeTextArea.append("\n// New File ;-;");
					}else if(i == 35) {
						CodeTextArea.append("\n// New Directory ;-;");
					}else if(i == 36) {
						CodeTextArea.append("\n// Write to File ;-;");
					}else if(i == 37) {
						CodeTextArea.append("\n// Read Line From File ;-;");
					}else if(i == 38) {
						CodeTextArea.append("\n// Read Whole File ;-;");
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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

		});


		// Layout
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(1)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(ContentPanel, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
										.addGap(12))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(TopBarPanel, GroupLayout.PREFERRED_SIZE, 394, Short.MAX_VALUE)
										.addGap(0))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(TopBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addComponent(ContentPanel, GroupLayout.PREFERRED_SIZE, 177, Short.MAX_VALUE))
				);
		GroupLayout gl_ContentPanel = new GroupLayout(ContentPanel);
		gl_ContentPanel.setHorizontalGroup(
				gl_ContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_ContentPanel.createSequentialGroup()
						.addComponent(ToolBoxPanel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_ContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(ConsolePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(CodePanel, GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE))
						.addGap(11))
				.addGroup(gl_ContentPanel.createSequentialGroup()
						.addContainerGap(367, Short.MAX_VALUE)
						.addComponent(ResizeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				);
		gl_ContentPanel.setVerticalGroup(
				gl_ContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ContentPanel.createSequentialGroup()
						.addGroup(gl_ContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(ToolBoxPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addGroup(gl_ContentPanel.createSequentialGroup()
										.addComponent(CodePanel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(ConsolePanel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ResizeLabel))
				);
		GroupLayout gl_TopBarPanel = new GroupLayout(TopBarPanel);
		gl_TopBarPanel.setHorizontalGroup(
				gl_TopBarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TopBarPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(TopBarLabel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(MinimizeButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(FullScreenButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ExitButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(639, Short.MAX_VALUE))
				);
		gl_TopBarPanel.setVerticalGroup(
				gl_TopBarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TopBarPanel.createSequentialGroup()
						.addGroup(gl_TopBarPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(MinimizeButton, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
								.addComponent(FullScreenButton, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
								.addComponent(ExitButton, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
						.addContainerGap())
				.addComponent(TopBarLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
				);
		ContentPanel.setLayout(gl_ContentPanel);
		TopBarPanel.setLayout(gl_TopBarPanel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setSize(new Dimension(1280, 720));
		frame.setLocation(bs.dimensions.width/2-frame.getSize().width/2, bs.dimensions.height/2-frame.getSize().height/2);
		frame.setVisible(true);
	}

	private static BufferedImage getTopBar() throws IOException {
		BufferedImage image = ImageIO.read(bs.TopBar);
		BufferedImage bufferedimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D createGraphics = bufferedimage.createGraphics();

		float a[] = new float[] {1f,1f,1f,0.8f};
		float b[] = new float[] {0,0,0,0};

		createGraphics.drawImage(image, null, 0, 0);
		RescaleOp r = new RescaleOp(a, b, null);
		BufferedImage TopBar = r.filter(bufferedimage, null);

		return TopBar;
	}

	protected static void toggleTheme() {
		if(bs.lightmode) {
			menuBar.setBackground(Color.DARK_GRAY);
			FileMenu.setBackground(Color.DARK_GRAY);
			EditMenu.setBackground(Color.DARK_GRAY);
			ViewMenu.setBackground(Color.DARK_GRAY);
			RunButton.setBackground(Color.DARK_GRAY);
			ThemeButton.setBackground(Color.DARK_GRAY);
			HintButton.setBackground(Color.DARK_GRAY);				
			FileMenu.setForeground(Color.WHITE);
			EditMenu.setForeground(Color.WHITE);
			ViewMenu.setForeground(Color.WHITE);
			RunButton.setForeground(Color.WHITE);
			ThemeButton.setForeground(Color.WHITE);
			HintButton.setForeground(Color.WHITE);
			ContentPanel.setBackground(Color.DARK_GRAY);
			ConsoleTextArea.setBackground(new Color(43, 43, 43));
			ConsoleTextArea.setForeground(Color.LIGHT_GRAY);
			CodeTextArea.setBackground(new Color(43, 43, 43));
			CodeTextArea.setForeground(Color.LIGHT_GRAY);
			tabbedPane.setBackground(Color.LIGHT_GRAY);
			tabbedPane.setForeground(Color.WHITE);
			lines.setBackground(new Color(49, 51, 53));
			lines.setForeground(Color.LIGHT_GRAY);
			list.setBackground(new Color(60, 63, 65));
			list.setForeground(Color.LIGHT_GRAY);
			toolBar.setBackground(Color.DARK_GRAY);
			BlockPanel.setBackground(new Color(43, 43, 43));
			bs.lightmode = false;
		}else {
			menuBar.setBackground(Color.WHITE);
			FileMenu.setBackground(Color.WHITE);
			EditMenu.setBackground(Color.WHITE);
			ViewMenu.setBackground(Color.WHITE);
			RunButton.setBackground(new Color(225, 225, 225));
			ThemeButton.setBackground(new Color(225, 225, 225));
			HintButton.setBackground(new Color(225, 225, 225));				
			FileMenu.setForeground(Color.BLACK);
			EditMenu.setForeground(Color.BLACK);
			ViewMenu.setForeground(Color.BLACK);
			RunButton.setForeground(Color.BLACK);
			ThemeButton.setForeground(Color.BLACK);
			HintButton.setForeground(Color.BLACK);
			ContentPanel.setBackground(new Color(240, 240, 240));
			ConsoleTextArea.setBackground(Color.WHITE);
			ConsoleTextArea.setForeground(Color.BLACK);
			CodeTextArea.setBackground(Color.WHITE);
			CodeTextArea.setForeground(Color.BLACK);
			tabbedPane.setBackground(new Color(240, 240, 240));
			tabbedPane.setForeground(Color.BLACK);
			lines.setBackground(Color.LIGHT_GRAY);
			lines.setForeground(Color.BLACK);
			list.setBackground(Color.white);
			list.setForeground(Color.BLACK);
			toolBar.setBackground(new Color(240, 240, 240));
			BlockPanel.setBackground(new Color(240, 240, 240));
			bs.lightmode = true;
		}
	}
}
