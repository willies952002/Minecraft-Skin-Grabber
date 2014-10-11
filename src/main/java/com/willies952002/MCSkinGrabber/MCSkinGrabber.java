package com.willies952002.MCSkinGrabber;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MCSkinGrabber {

	private JFrame frmMinecraftSkinGrabber;
	private JTextField source;
	private JLabel lblSkinViewer;
	private JLabel lblUsername;
	private JEditorPane viewer;
	private final Action action = new SwingAction();
	private JComboBox<String> sourceSelect;
	File blankPage = new File(System.getProperty("user.dir")
			+ File.separator + "blank.html");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MCSkinGrabber window = new MCSkinGrabber();
					window.frmMinecraftSkinGrabber.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MCSkinGrabber() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		blankPage.createNewFile();
		blankPage.deleteOnExit();
		frmMinecraftSkinGrabber = new JFrame();
		frmMinecraftSkinGrabber.setTitle("Minecraft Skin Grabber 2.1");
		frmMinecraftSkinGrabber.getContentPane().setForeground(Color.WHITE);
		frmMinecraftSkinGrabber.getContentPane().setBackground(Color.BLACK);
		frmMinecraftSkinGrabber.setBounds(100, 100, 450, 375);
		frmMinecraftSkinGrabber.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinecraftSkinGrabber.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC, }));

		lblUsername = new JLabel("Username: ");
		lblUsername.setForeground(Color.WHITE);
		frmMinecraftSkinGrabber.getContentPane().add(lblUsername,
				"2, 2, right, fill");

		source = new JTextField();
		frmMinecraftSkinGrabber.getContentPane().add(source,
				"4, 2, default, fill");
		source.setColumns(10);

		sourceSelect = new JComboBox<String>();
		sourceSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblUsername.setText(e.getItem().toString() + ":");
				}
			}
		});
		sourceSelect.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Username", "Website", "UUID" }));
		frmMinecraftSkinGrabber.getContentPane().add(sourceSelect,
				"2, 4, 3, 1, fill, fill");

		JButton getSkin = new JButton("Get Skin");
		getSkin.setAction(action);
		frmMinecraftSkinGrabber.getContentPane().add(getSkin, "2, 6, 3, 1");

		lblSkinViewer = new JLabel();
		lblSkinViewer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkinViewer.setForeground(Color.WHITE);
		lblSkinViewer.setText("Skin Viewer");
		frmMinecraftSkinGrabber.getContentPane().add(lblSkinViewer,
				"2, 8, 3, 1, fill, default");

		viewer = new JEditorPane();
		frmMinecraftSkinGrabber.getContentPane().add(viewer,
				"2, 10, 3, 1, fill, fill");
	}

	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = -4036893338816448788L;

		public SwingAction() {
			putValue(NAME, "Get Skin");
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("EVENT FIRED");
			String src = "";
			int idx = sourceSelect.getSelectedIndex();
			System.out.println("SELECTED INDEX: " + idx);
			if (sourceSelect.getSelectedItem().equals("Username")) {
				try {
					src = Base64Decoder.getTextureURL(source.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				fetchMsg("USERNAME");
			}
			if (sourceSelect.getSelectedItem().equals("Website")) {
				src = source.getText();
				fetchMsg("WEBSITE");
			}
			if (sourceSelect.getSelectedItem().equals("UUID")) {
				try {
					src = Base64Decoder._decode(Base64Decoder.base
							+ source.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				fetchMsg("UUID");
			}

			try {
				File index = new File(System.getProperty("user.dir")
						+ File.separator + "index.html");
				File imageFile = new File(System.getProperty("user.dir")
						+ File.separator + src.substring(src.lastIndexOf('/'))
						+ ".png");
				System.out.println("READING IMAGE");
				System.out.println("SRC: " + src);
				BufferedImage image = ImageIO.read(new URL(src));
				System.out.print("IMAGE READ");
				System.out.println("WRITING IMAGE");
				ImageIO.write(image, "png", imageFile);
				System.out.println("IMAGE WRITEN");
				SkinViewPage blank = new SkinViewPage(
						imageFile.getParentFile() + File.separator
								+ "blank.html", "");
				System.out.println("VIEWER SET TO: " + blank.getPage());
				viewer.setPage(blank.getPage());
				if (index.exists()) {
					index.delete();
				}
				if (imageFile.exists()) {
					System.out.println("IMAGE WRITEN TO: "
							+ imageFile.getAbsolutePath());
					SkinViewPage svp = new SkinViewPage(
							imageFile.getParentFile() + File.separator
									+ "index.html", imageFile.getName());
					System.out.println("VIEWER SET TO: " + svp.getPage().getPath());
					viewer.setPage(svp.getPage());
					viewer.setPage(svp.getPage());
					viewer.setPage(svp.getPage());
				} else {
					System.out.println("IMAGE FAILED TO WRITE");
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		private void fetchMsg(String src) {
			System.out.println("Fethcing Skin From " + src);
		}
	}
}
