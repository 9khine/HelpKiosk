package com.prototype.helpkiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.android.ddmlib.IDevice;
import com.android.hierarchyviewer.device.DeviceBridge;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

public class Workspace extends JFrame implements WindowListener {
	
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private LiveView liveView;
	private JPanel demoPanel;
	private IDevice device;
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	
	public Border blueLine = new CompoundBorder(new EmptyBorder(26, 15, 0, 0),
			BorderFactory.createLineBorder(new Color(0x3B70A3), 2));
	
	final public int default_width = 1440;
	final public int default_height = 900;
	private int rightPanel_width_percent = 60;
	
	public Workspace(){
		super("Help Kiosk");
		addWindowListener(this);
		instructionSingleton.initInstructions();
		setFont(new Font("Helvetica", Font.PLAIN, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.device = getCurrentDevice();
		//Uncomment to hide top bar
		//setUndecorated(true); 
		add(buildMainPanel());
		pack();
		
	}
	
	private JComponent buildMainPanel(){
		
		this.mainPanel = new JPanel();
		this.mainPanel.setBackground(Color.WHITE);
		this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.mainPanel.setPreferredSize(new Dimension(default_width, default_height));
		mainPanel.add(createRightPanel());
		mainPanel.add(createLeftPanel());
		
		
		return mainPanel;
	}
	
	private JPanel createLeftPanel(){
		
		leftPanel= new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(this.default_width * (100-this.rightPanel_width_percent)/100
												, this.default_height));
		
		this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createRigidArea(new Dimension(10, 26)));
		leftPanel.add(createLiveViewPanel());
		leftPanel.add(createDemoPanel());
		
		
		return leftPanel;
	}	

	private Container createRightPanel(){
		
		rightPanel= new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setPreferredSize(new Dimension(this.default_width * this.rightPanel_width_percent/100
												, this.default_height));
		Accordion accordion = instructionSingleton.getAccordion();
		accordion.setBackground(Color.WHITE);
		rightPanel.add(accordion, BorderLayout.CENTER);

		return rightPanel; 
	}
	
	
	private JComponent createDemoPanel(){
		this.demoPanel = new JPanel();
		demoPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 26, 15),
				BorderFactory.createLineBorder(new Color(0x3B70A3), 2)));
		demoPanel.setBackground(Color.WHITE);
		demoPanel.setLayout(new BoxLayout(demoPanel, BoxLayout.X_AXIS));
		
		demoPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		JPanel titleFiller = new JPanel();
		titleFiller.setLayout(new BorderLayout());
		titleFiller.setBackground(Color.WHITE);
			JLabel title = new JLabel("<html><br>DEMO<br>VIDEO</html>");
			title.setFont(new Font("Arial", Font.BOLD, 26));
			title.setForeground(new Color(0x3B70A3));
		titleFiller.add(title, BorderLayout.PAGE_START);
			JLabel ins = new JLabel("<html>Touch video<br>to play<br>and replay.</html>");
			ins.setFont(new Font("Arial", Font.ITALIC, 15));
			ins.setForeground(new Color(0x3B70A3));
		titleFiller.add(ins, BorderLayout.LINE_START);
		titleFiller.add(Box.createRigidArea(new Dimension(20, 130)), BorderLayout.PAGE_END);
		demoPanel.add(titleFiller);
						
		MediaPanel mediaPanel = new MediaPanel();
		mediaPanel.setBackground(Color.WHITE);
		mediaPanel.setPreferredSize(new Dimension(445, 250));
		instructionSingleton.setMediaContainer(mediaPanel);
		
		demoPanel.add(mediaPanel);
		demoPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return demoPanel;
	}
	
	private JComponent createLiveViewPanel() {
		this.liveView = new LiveView(this.device);
		liveView.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 15),
			BorderFactory.createMatteBorder(2, 2, 0, 2, new Color(0x3B70A3))));
		return this.liveView;
	}
	
	private IDevice getCurrentDevice(){
		DeviceBridge.initDebugBridge();
        
        sleep();
        
		System.out.println("init done.");
        IDevice[] devices = DeviceBridge.getDevices();
        for (IDevice d:devices)
        	System.out.println(d);
        
        return devices[0];
		
	}
	
	private static void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
            	System.out.println("exiting");
                dispose();
                instructionSingleton.setThreadfinish(true);
                System.exit(0);
	}
}
