package com.prototype.helpkiosk.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.*;
import javax.swing.border.*;

import org.jdesktop.swingx.JXCollapsiblePane;

import com.prototype.helpkiosk.instruction.InstructionSingleton;
import com.prototype.helpkiosk.instructioncontrol.InstructionView;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel {

	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();

	private Dimension btnSize = new Dimension(160, 50);
	private Font btnFont = new Font("Helvetica", Font.PLAIN, 17);
	private Font panelTitles = new Font("Helvetica", Font.BOLD, 17);

	public SearchPanel(){
		
	}

	public JPanel createPanel() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new CompoundBorder(
				BorderFactory.createLineBorder(new Color(0x3B70A3), 4),
				new EmptyBorder(10, 20, 10, 20)));

		/*
		 * Instruction
		 */	
		mainPanel.add(instructionPanel());
		
		mainPanel.add(messagesPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));

		mainPanel.add(phonePanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));

		mainPanel.add(clockPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));

		mainPanel.add(cameraPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));	

		mainPanel.add(contactPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));

		mainPanel.add(galleryPanel());
		
		return mainPanel;
	}

	private JPanel instructionPanel() {
		JPanel instructionFiller = new JPanel();
		instructionFiller.setBackground(Color.WHITE);
		instructionFiller.setLayout(new FlowLayout((FlowLayout.LEFT)));
		JLabel heading = new JLabel();
		heading.setText("Start by choosing a task... ");
		heading.setFont(new Font("Helvetica", Font.BOLD,  24));
		heading.setForeground(Color.DARK_GRAY);

		instructionFiller.add(heading);
		instructionFiller.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		return instructionFiller;
	}

	private JPanel clockPanel() {

		/* Clock */

		JPanel clockPanel = new JPanel();
		clockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		clockPanel.setBorder(BorderFactory.createTitledBorder("Clock: "));
		((javax.swing.border.TitledBorder) clockPanel.getBorder()).setTitleFont(panelTitles);

		clockPanel.setBackground(Color.WHITE);
		clockPanel.setSize(new Dimension(this.getWidth(), 70));
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		clockPanel.add(filler);

		clockPanel.removeAll();

		JButton setAlarm = new JButton("<html><body style=\"text-align: center\">Setting an<br>Alarm</html>");
		setAlarm.setPreferredSize(btnSize);
		JButton stopAlarm = new JButton("<html><body style=\"text-align: center\">Stopping<br>Alarms</html>");
		stopAlarm.setPreferredSize(btnSize);
		JButton deleteAlarm = new JButton("<html><body style=\"text-align: center\">Deleting<br>Alarms</html>");
		deleteAlarm.setPreferredSize(btnSize);

		clockPanel.add(instructionView(setAlarm, 3));
		clockPanel.add(instructionView(stopAlarm, 3)).setEnabled(false);
		clockPanel.add(instructionView(deleteAlarm, 3)).setEnabled(false);
		clockPanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);

		clockPanel.validate();
		clockPanel.repaint();

		return clockPanel;
	}

	private JPanel contactPanel() {

		/*Contact*/

		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		contactPanel.setBorder(BorderFactory.createTitledBorder("Contacts: "));
		((javax.swing.border.TitledBorder) contactPanel.getBorder()).setTitleFont(panelTitles);
		contactPanel.setBackground(Color.WHITE);
		contactPanel.setSize(new Dimension(this.getWidth(), 70));
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		contactPanel.add(filler);

		contactPanel.removeAll();

		JButton addContacts = new JButton("<html><body style=\"text-align: center\">Adding<br>Contacts</html>");
		addContacts.setPreferredSize(btnSize);
		JButton searchContacts = new JButton("<html><body style=\"text-align: center\">Searching<br>for Contacts</html>");
		searchContacts.setPreferredSize(btnSize);

		contactPanel.add(instructionView(addContacts, 1));
		contactPanel.add(instructionView(searchContacts, 1)).setEnabled(false);

		contactPanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);
		// TODO add expandable panel after clicking more

		contactPanel.validate();
		contactPanel.repaint();

		return contactPanel;
	}

	private JPanel cameraPanel() {

		/*Camera*/

		JPanel cameraPanel = new JPanel();
		cameraPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		cameraPanel.setBorder(BorderFactory.createTitledBorder("Camera: "));
		((javax.swing.border.TitledBorder) cameraPanel.getBorder()).setTitleFont(panelTitles);
		cameraPanel.setBackground(Color.WHITE);
		cameraPanel.setSize(new Dimension(this.getWidth(), 70));
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		cameraPanel.add(filler);
		cameraPanel.removeAll();

		JButton takePicture = new JButton("<html><body style=\"text-align: center\">Taking<br>Pictures</html>");
		takePicture.setPreferredSize(btnSize);
		JButton takeVideo = new JButton("<html><body style=\"text-align: center\">Taking<br>Videos</html>");
		takeVideo.setPreferredSize(btnSize);
		JButton lockScreenPic = new JButton("<html><body style=\"text-align: center\">Launch Camera<br>on Lock Screen</html>");
		lockScreenPic.setPreferredSize(btnSize);

		cameraPanel.add(instructionView(takePicture, 2));
		cameraPanel.add(instructionView(takeVideo, 2)).setEnabled(false);
		cameraPanel.add(instructionView(lockScreenPic, 2)).setEnabled(false);
		cameraPanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);

		cameraPanel.validate();
		cameraPanel.repaint();

		return cameraPanel;
	}

	private JPanel messagesPanel() {

		/*Messages*/

		JPanel messagesPanel = new JPanel();
		messagesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		messagesPanel.setSize(new Dimension(this.getWidth(), 70));
		messagesPanel.setBorder(BorderFactory.createTitledBorder("Messages: "));
		((javax.swing.border.TitledBorder) messagesPanel.getBorder()).setTitleFont(panelTitles);
		messagesPanel.setBackground(Color.WHITE);
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		messagesPanel.add(filler);

		messagesPanel.removeAll();

		JButton sendMsgs = new JButton("<html><body style=\"text-align: center\">Send<br>Messages</html>");
		sendMsgs.setPreferredSize(btnSize);
		JButton viewMsgs = new JButton("<html><body style=\"text-align: center\">View<br>Messages</html>");
		viewMsgs.setPreferredSize(btnSize);

		messagesPanel.add(instructionView(sendMsgs, 4));
		messagesPanel.add(instructionView(viewMsgs, 4)).setEnabled(false);
		messagesPanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);

		messagesPanel.validate();
		messagesPanel.repaint();

		return messagesPanel;
	}

	private JPanel phonePanel() {

		/*Phone*/

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		phonePanel.setSize(new Dimension(this.getWidth(), 70));
		phonePanel.setBorder(BorderFactory.createTitledBorder("Phone: "));
		((javax.swing.border.TitledBorder) phonePanel.getBorder()).setTitleFont(panelTitles);
		phonePanel.setBackground(Color.WHITE);
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		phonePanel.add(filler);

		phonePanel.removeAll();

		JButton makeCalls = new JButton("<html><body style=\"text-align: center\">Making<br>Calls</html>");
		makeCalls.setPreferredSize(btnSize);
		JButton receiveCalls = new JButton("<html><body style=\"text-align: center\">Receiving<br>Calls</html>");
		receiveCalls.setPreferredSize(btnSize);

		phonePanel.add(instructionView(makeCalls, 5));
		phonePanel.add(instructionView(receiveCalls, 5)).setEnabled(false);

		phonePanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);

		phonePanel.validate();
		phonePanel.repaint();

		return phonePanel;
	}

	private JPanel galleryPanel() {

		/*Gallery*/

		JPanel galleryPanel = new JPanel();
		galleryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		galleryPanel.setSize(new Dimension(this.getWidth(), 70));
		galleryPanel.setBorder(BorderFactory.createTitledBorder("Gallery: "));
		((javax.swing.border.TitledBorder) galleryPanel.getBorder()).setTitleFont(panelTitles);
		galleryPanel.setBackground(Color.WHITE);
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		galleryPanel.add(filler);

		galleryPanel.removeAll();

		JButton viewImages = new JButton("<html><body style=\"text-align: center\">Viewing<br>Images</html>");
		viewImages.setPreferredSize(btnSize);
		JButton viewVids = new JButton("<html><body style=\"text-align: center\">Viewing<br>Videos</html>");
		viewVids.setPreferredSize(btnSize);
		JButton deleting = new JButton("<html><body style=\"text-align: center\">Delete an Image<br>or a Video</html>");
		deleting.setPreferredSize(btnSize);

		galleryPanel.add(instructionView(viewImages, 6));
		galleryPanel.add(instructionView(viewVids, 6)).setEnabled(false);
		galleryPanel.add(instructionView(deleting, 6)).setEnabled(false);

		galleryPanel.add(new JButton("<html><body style=\"color: blue\">more...</html>")).setEnabled(false);

		galleryPanel.validate();
		galleryPanel.repaint();

		return galleryPanel;
	}

	private JButton instructionView(JButton button, final int appNumber) {
		
		button.setFont(btnFont);
		
		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cleanPanel();
						InstructionView view;
												
						String log = String.format("Starting task: " + e.getActionCommand() + "%ntime = " + System.nanoTime() + "%n");
						try {
							Files.write(Paths.get("log.txt"), log.getBytes(), StandardOpenOption.APPEND);
						} catch (IOException f) {
							f.printStackTrace();
						}

						/*
						 * appNumber reference:
						 * 
						 * Contact - 1
						 * Camera - 2
						 * Clock - 3
						 * Message - 4
						 * Phone - 5
						 * Gallery - 6
						 * 
						 * */ 

						switch(appNumber) {
						
						case 1:	view = instructionSingleton.getContactView();
						break;
						case 2:	view = instructionSingleton.getCameraView();
						break;
						case 3:	view = instructionSingleton.getClockView();
						break;
						case 4:	view = instructionSingleton.getMessageView();
						break;
						case 5:	view = instructionSingleton.getPhoneView();
						break;
						case 6:	view = instructionSingleton.getGalleryView();
						break;

						// if none matches, set to contact view
						default: view = instructionSingleton.getContactView();
						System.out.println("!! - Invalid appNumber - !!");
						break;
						}

						if(!view.isActive()){
							if(instructionSingleton.getAccordion().getAccordion().getSelectedIndex()!=0)
								instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
							//instructionSingleton.getContainer().removeAll();
							instructionSingleton.setActiveView(view);
							instructionSingleton.setMaxID(instructionSingleton.getActiveView().instruction.length-1);
						}else{
							instructionSingleton.setActiveView(view);
							instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
						}

						instructionSingleton.buildAppView(view); 
						instructionSingleton.updateInstructionView();
						view.setActive(true);
					}
				}
				);

		return button;
	}

	public void cleanPanel(){

		if(instructionSingleton.getContactView().isActive())
		{
			instructionSingleton.getContactView().setActive(false);
		}
		if(instructionSingleton.getCameraView().isActive())
		{
			instructionSingleton.getCameraView().setActive(false);
		}
		if(instructionSingleton.getClockView().isActive())
		{
			instructionSingleton.getCameraView().setActive(false);
		}
				
		instructionSingleton.getContainer().removeAll();

		instructionSingleton.updateInstructionView();
	}

	public class TransparentButton extends JButton {
		private float opacity;
		public TransparentButton(String text, float opacity) { 
			super(text);
			this.opacity = opacity;
			setOpaque(false); 
		} 

		public void paint(Graphics g) { 
			Graphics2D g2 = (Graphics2D) g.create(); 
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)); 
			super.paint(g2); 
			g2.dispose(); 
		} 
	}
	
	/*
	 * TODO create collapsible panel for More button
	 * 
	 * http://javadoc.geotoolkit.org/external/swingx/org/jdesktop/swingx/JXCollapsiblePane.html
	 * https://www.programcreek.com/java-api-examples/index.php?api=org.jdesktop.swingx.JXCollapsiblePane
	 * 
	 */
	class CollapsibleTreePanel {
		
		public CollapsibleTreePanel(JComponent treeComponent) {
			final JXCollapsiblePane _collapsibleTreePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.LEFT);
			//_collapsibleTreePane.getContentPane().setBackground(WidgetUtils.BG_COLOR_DARK);
			_collapsibleTreePane.add(treeComponent);
			_collapsibleTreePane.setAnimated(false);

			JButton _toggleTreeViewButton = new JButton();
			_toggleTreeViewButton.setBorder(null);
			_toggleTreeViewButton.setOpaque(false);
			_toggleTreeViewButton.setContentAreaFilled(false);
			_toggleTreeViewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean collapsed = _collapsibleTreePane.isCollapsed();
					if (collapsed) {
						//_toggleTreeViewButton.setIcon(imageManager.getImageIcon("images/widgets/tree-panel-collapse.png"));
						//_toggleTreeViewButton.setBorder(null);
					} else {
						//_toggleTreeViewButton.setIcon(imageManager.getImageIcon("images/widgets/tree-panel-expand.png"));
						//_toggleTreeViewButton.setBorder(new EmptyBorder(0, 2, 0, 0));
					}
					//_collapsibleTreePane.setCollapsed(!collapsed);
				}
			});
		}
	}

}
