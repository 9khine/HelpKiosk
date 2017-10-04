package com.prototype.helpkiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jdesktop.swingworker.SwingWorker;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.hierarchyviewer.device.Window;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

class LiveView extends JPanel {
	private IDevice device;
	private BufferedImage image;
	private volatile boolean isLoading;
	private Timer timer;
	private int refreshRate = 500;
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	private int width, height;
	
	LiveView (IDevice device) {
		setLayout(new FlowLayout());
		setOpaque(true);
		setBackground(Color.WHITE);

		this.device = device;
		this.width = 240;
		this.height = 400;

		JPanel panel = buildViewer();
		add(panel);
	}

	private JPanel buildViewer() {
		JPanel panel = new JPanel();
		
		ImageIcon screenshot = new ImageIcon("/Users/pablo/git/HelpKioskKhine/screencapture/screen.png");
		Image screenShotImg = screenshot.getImage();
		Image scaledImage = screenShotImg.getScaledInstance(width,height,Image.SCALE_SMOOTH);
		
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		JLabel label = new JLabel(scaledIcon);
		JPanel viewPanel = new JPanel(new BorderLayout());
		viewPanel.add(label, BorderLayout.CENTER);

		viewPanel.setBackground(Color.WHITE);
		viewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		viewPanel.setBounds(0, 0, width, height);

		JLabel title = new JLabel("<html><br></html>");
		title.setFont(new Font("Helvetica", Font.BOLD, 23));
		title.setForeground(new Color(0x3B70A3));

		ImageIcon img = new ImageIcon("img/nexusoneinhand.png");

		JLabel bg = new JLabel(img);
		bg.setBackground(Color.WHITE);
		bg.setLayout(new BoxLayout(bg, BoxLayout.Y_AXIS));
		JPanel rigid = new JPanel();
		rigid.setOpaque(false);
		rigid.setLayout(new BoxLayout(rigid, BoxLayout.X_AXIS));
		rigid.setPreferredSize(new Dimension(100, 30));
		rigid.add(title);
		bg.add(rigid);

		// Left-side of live view spacer
		JPanel rigid2 = new JPanel();
		rigid2.setOpaque(false);
		rigid2.setPreferredSize(new Dimension(185, height));

		// Right-side of live view spacer
		JPanel rigid3 = new JPanel();
		rigid3.setOpaque(false);
		rigid3.setPreferredSize(new Dimension(105, height));

		JPanel filler = new JPanel();
		filler.setLayout(new BoxLayout(filler, BoxLayout.X_AXIS));
		filler.setOpaque(false);
		filler.add(rigid2);
		filler.add(viewPanel);
		filler.add(rigid3);

		JPanel rigid4 = new JPanel();
		rigid4.setLayout(null);
		rigid4.setOpaque(false);
		rigid4.setPreferredSize(new Dimension(width, 100));   
		instructionSingleton.setLowerContainer(rigid4);

		bg.add(filler);
		bg.add(rigid4);

		panel.add(bg);
		return panel;
	}


}