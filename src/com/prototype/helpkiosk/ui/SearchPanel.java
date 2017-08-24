package com.prototype.helpkiosk.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.jdesktop.swingx.JXCollapsiblePane;

import com.prototype.helpkiosk.instruction.InstructionSingleton;
import com.prototype.helpkiosk.instructioncontrol.InstructionView;

public class SearchPanel extends JPanel {

	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();

	private Dimension btnSize = new Dimension(160, 50);

	public SearchPanel(){

	}

	public JPanel createPanel() {

		/*
		 * TODO create advanced tab (using jtabbedpane)
		 * 
		 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JTabbedPane.html
		 * https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
		 * 
		 */
//		JTabbedPane tabbedPane = new JTabbedPane();
//		ImageIcon icon = createImageIcon("images/middle.gif");
//
//		JComponent panel1 = makeTextPanel("Panel #1");
//		tabbedPane.addTab("Tab 1", icon, panel1,
//		                  "Does nothing");
//		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//
//		JComponent panel2 = makeTextPanel("Panel #2");
//		tabbedPane.addTab("Tab 2", icon, panel2,
//		                  "Does twice as much nothing");
//		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
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

		mainPanel.add(clockPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		mainPanel.add(contactPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		mainPanel.add(cameraPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		mainPanel.add(messagesPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		mainPanel.add(phonePanel());
		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));	

		mainPanel.add(galleryPanel());

		return mainPanel;
	}

	private JPanel instructionPanel() {
		JPanel instructionFiller = new JPanel();
		instructionFiller.setBackground(Color.WHITE);
		instructionFiller.setLayout(new FlowLayout((FlowLayout.LEFT)));
		instructionFiller.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));
		JLabel instruction = new JLabel();

		instruction.setText("Instructions:");
		instruction.setFont(new Font("Helvetica", Font.BOLD,  22));
		instruction.setForeground(Color.DARK_GRAY);

		instructionFiller.add(instruction);
		instructionFiller.add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

		return instructionFiller;
	}

	private JPanel clockPanel() {

		/* Clock */

		JPanel clockPanel = contactPanel();
		clockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		clockPanel.setBorder(BorderFactory.createTitledBorder("Clock: "));
		clockPanel.setBackground(Color.WHITE);
		clockPanel.setSize(new Dimension(this.getWidth(), 70));
		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
		filler.setOpaque(false);
		clockPanel.add(filler);

		clockPanel.removeAll();

		JButton setAlarm = new JButton("<html><body style=\"text-align: center\">Setting<br>Alarm</html>");
		setAlarm.setPreferredSize(btnSize);
		JButton stopAlarm = new JButton("<html><body style=\"text-align: center\">Stopping<br>Alarms</html>");
		stopAlarm.setPreferredSize(btnSize);
		JButton deleteAlarm = new JButton("<html><body style=\"text-align: center\">Deleting<br>Alarms</html>");
		deleteAlarm.setPreferredSize(btnSize);

		clockPanel.add(instructionView(setAlarm, 3));
		clockPanel.add(instructionView(stopAlarm, 3));
		clockPanel.add(instructionView(deleteAlarm, 3));
		clockPanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));

		clockPanel.validate();
		clockPanel.repaint();

		return clockPanel;
	}

	private JPanel contactPanel() {

		/*Contact*/

		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		contactPanel.setBorder(BorderFactory.createTitledBorder("Contacts: "));
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
		contactPanel.add(instructionView(searchContacts, 1));

		contactPanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));
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
		JButton lockScreenPic = new JButton("<html><body style=\"text-align: center\">Launching Camera<br>on Lock Screen</html>");
		lockScreenPic.setPreferredSize(btnSize);

		cameraPanel.add(instructionView(takePicture, 2));
		cameraPanel.add(instructionView(takeVideo, 2));
		cameraPanel.add(instructionView(lockScreenPic, 2));
		cameraPanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));

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
		messagesPanel.add(instructionView(viewMsgs, 4));
		messagesPanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));

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
		phonePanel.add(instructionView(receiveCalls, 5));

		phonePanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));

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
		galleryPanel.add(instructionView(viewVids, 6));
		galleryPanel.add(instructionView(deleting, 6));

		galleryPanel.add(new JButton("<html><body style=\"color: blue\">more ▾</html>"));

		galleryPanel.validate();
		galleryPanel.repaint();

		return galleryPanel;
	}

	private JButton instructionView(JButton button, final int appNumber) {

		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						cleanPanel();
						InstructionView view;

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
							instructionSingleton.getContainer().removeAll();
							instructionSingleton.setActiveView(view);
							instructionSingleton.setMaxID(instructionSingleton.getActiveView().instruction.length-1);
						}else{
							instructionSingleton.setActiveView(view);
							instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
						}

						instructionSingleton.buildAppView(view); 
						instructionSingleton.updateInstructionView();
						view.setActive(true);

						// TODO: fix moreHelp to take more than array list

						/* Contact */
						//						instructionSingleton.buildMoreHelpView(
						//								instructionSingleton.getMoreHelp().getAboutSet(new int[] {1,2}),
						//								instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {1,2})
						//								);

						/* Camera */
						//						instructionSingleton.buildMoreHelpView(///
						//								instructionSingleton.getMoreHelp().getAboutSet(new int[] {3}),
						//								instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {3})
						//								);

						/* Clock */
						//						instructionSingleton.buildMoreHelpView(
						//								instructionSingleton.getMoreHelp().getAboutSet(new int[] {4,5}),
						//								instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {4,5})
						//								);

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
		instructionSingleton.getMoreHelpContainer().removeAll();
		instructionSingleton.updateInstructionView();
		instructionSingleton.showMoreHelpPanel(true);
	}

	class TransparentButton extends JButton {
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


	//	public JPanel createPanel() {
	//		JPanel mainPanel = new JPanel();
	//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	//		mainPanel.setBackground(Color.WHITE);
	//		mainPanel.setBorder(new CompoundBorder(
	//				BorderFactory.createLineBorder(new Color(0x3B70A3), 4),
	//				new EmptyBorder(10, 20, 10, 20)));
	//
	//		/*
	//		 * Instruction
	//		 */
	//		JPanel instructionFiller = new JPanel();
	//		instructionFiller.setBackground(Color.WHITE);
	//		instructionFiller.setLayout(new FlowLayout());
	//		instructionFiller.add(Box.createRigidArea(new Dimension(50, 50)));
	//		JLabel instruction = new JLabel();
	//
	//		instruction.setText("Touch a word/phrase that is related to what you want to learn :");
	//		instruction.setFont(new Font("Helvetica", Font.BOLD,  22));
	//		instruction.setForeground(Color.DARK_GRAY);
	//		instructionFiller.add(instruction);
	//		instructionFiller.add(Box.createRigidArea(new Dimension(0, 50)));
	//		mainPanel.add(instructionFiller);
	//
	//		/*
	//		 * Tag Cloud
	//		 */
	//		JPanel tagFiller = new JPanel();
	//		tagFiller.setBackground(Color.WHITE);
	//		tagFiller.setPreferredSize(new Dimension(800, 350));
	//		tagFiller.setLayout(new FlowLayout());
	//		tagFiller.add(Box.createRigidArea(new Dimension(10, 350)));
	//		tagFiller.add(createTagCloud());
	//		tagFiller.add(Box.createRigidArea(new Dimension(10, 350)));
	//		mainPanel.add(tagFiller);
	//
	//		/*
	//		 * OR
	//		 */
	//		JLabel OR = new JLabel();
	//		OR.setText("OR");
	//		OR.setFont(new Font("Helvetica", Font.BOLD,  22));
	//		OR.setForeground(Color.DARK_GRAY);
	//		mainPanel.add(OR);
	//
	//		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 20)));
	//
	//		/*
	//		 * Search Box
	//		 */
	//		JPanel searchFiller = new JPanel();
	//		searchFiller.setOpaque(true);
	//		searchFiller.setBackground(Color.WHITE);
	//		searchFiller.setLayout(new BoxLayout(searchFiller, BoxLayout.X_AXIS));
	//		searchFiller.setPreferredSize(new Dimension(250, 20));
	//		final JTextField searchBox = new JTextField(50);
	//		searchBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
	//		searchBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	//		searchBox.setPreferredSize(new Dimension(75, 20));
	//		searchBox.setText(" Type word(s) to search");
	//		searchBox.addFocusListener(new FocusAdapter() {
	//			public void focusGained(final FocusEvent fe) {
	//				SwingUtilities.invokeLater(new Runnable() {
	//					public void run() {
	//						searchBox.selectAll();}
	//				});
	//			}
	//		});
	//		searchFiller.add(Box.createRigidArea(new Dimension(75,20)));
	//		searchFiller.add(searchBox);
	//		searchFiller.add(Box.createRigidArea(new Dimension(10,20)));
	//		searchFiller.add(new JButton("SEARCH")).addMouseListener(
	//				new MouseAdapter() { 
	//					public void mousePressed(MouseEvent me) { 
	//						System.out.println("Search pressed"); 
	//						System.out.println(me); 
	//					} 
	//				});
	//		searchFiller.add(Box.createRigidArea(new Dimension(50,20)));
	//		mainPanel.add(searchFiller);
	//
	//		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 50)));
	//
	//		/*
	//		 * Search Result
	//		 */
	//		searchResult = new JPanel();
	//		searchResult.setLayout(new FlowLayout());
	//		searchResult.setBorder(BorderFactory.createTitledBorder("Matched Instruction"));
	//		searchResult.setBackground(Color.WHITE);
	//		searchResult.setSize(new Dimension(this.getWidth(), 70));
	//		TransparentButton filler = new TransparentButton("FILL", (float) 0.0);
	//		filler.setOpaque(false);
	//		searchResult.add(filler);
	//		mainPanel.add(searchResult);
	//
	//		mainPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), 100)));
	//
	//		return mainPanel;
	//	}


	/*
	 * -------------------------- Tag Cloud ----------------------------------------
	 */
	//	private JLabel createTagCloud() {
	//		JLabel tagCloud;
	//		ImageIcon img = new ImageIcon("img/cloudtag.png");
	//		tagCloud = new JLabel(img);
	//		tagCloud.setPreferredSize(new Dimension(604, 349));
	//		tagCloud.setLayout(new BoxLayout(tagCloud, BoxLayout.Y_AXIS));
	//		
	//		/*Clock*/
	//		JPanel clockPanel = new JPanel();
	//		clockPanel.setOpaque(false);
	//		clockPanel.setLayout(new BoxLayout(clockPanel, BoxLayout.X_AXIS));
	//		clockPanel.add(Box.createRigidArea(new Dimension(90, 30)));
	//		TransparentButton clockButton = new TransparentButton("                                  ", (float)0.1);
	//		clockButton.setLayout(new BoxLayout(clockButton, BoxLayout.Y_AXIS));
	//		clockButton.add(new JLabel("                                  "));
	//		clockButton.add(new JLabel("                                  "));
	//		clockButton.add(new JLabel("                                  "));
	//		clockButton.add(new JLabel("                                  "));
	//		clockButton.addActionListener(
	//				new ActionListener() {
	//					public void actionPerformed(ActionEvent e) {
	//						searchResult.removeAll();
	//						JButton setAlarm = new JButton("Setting Alarm");
	//						setAlarm.addActionListener(
	//								new ActionListener(){
	//									public void actionPerformed(ActionEvent e){
	//										//clean panel from take picture view
	//										cleanPanel();
	//										//Show add contact view
	//										if(!instructionSingleton.getClockView().isActive()){
	//											if(instructionSingleton.getAccordion().getAccordion().getSelectedIndex()!=0)
	//												instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//											instructionSingleton.getContainer().removeAll();
	//											instructionSingleton.setActiveView(instructionSingleton.getClockView());
	//											instructionSingleton.setMaxID(instructionSingleton.getActiveView().instruction.length-1);
	//
	//										}else{
	//											instructionSingleton.setActiveView(instructionSingleton.getClockView());
	//											instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//										}
	//										instructionSingleton.buildClockView();
	//										instructionSingleton.updateInstructionView();
	//										instructionSingleton.getClockView().setActive(true);
	//
	//										instructionSingleton.buildMoreHelpView(
	//												instructionSingleton.getMoreHelp().getAboutSet(new int[] {4,5}),
	//												instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {4,5})
	//												);
	//									}
	//								}
	//								);
	//						searchResult.add(setAlarm);
	//						searchResult.add(new JButton("Remove Alarm"));
	//						searchResult.add(new JButton("Activate Alarm"));
	//						searchResult.add(new JButton("Set Date and Time"));
	//
	//						searchResult.validate();
	//						searchResult.repaint();
	//					}
	//				}
	//				);
	//		clockPanel.add(clockButton);
	//		clockPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), this.getHeight())));
	//		tagCloud.add(clockPanel);	
	//		tagCloud.add(Box.createRigidArea(new Dimension(this.getWidth(), 45)));
	//		
	//		/*Contact*/
	//		JPanel contactPanel = new JPanel();
	//		contactPanel.setOpaque(false);
	//		contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.X_AXIS));
	//		contactPanel.add(Box.createRigidArea(new Dimension(100, 30)));
	//		TransparentButton contactButton = new TransparentButton("                                  ", (float)0.1);
	//		contactButton.setLayout(new BoxLayout(contactButton, BoxLayout.Y_AXIS));
	//		contactButton.add(new JLabel("                                  "));
	//		contactButton.add(new JLabel("                                  "));
	//		contactButton.addActionListener(
	//				new ActionListener() {
	//					public void actionPerformed(ActionEvent e) {
	//						searchResult.removeAll();
	//						JButton addContacts = new JButton("Adding Contacts");
	//						addContacts.addActionListener(
	//								new ActionListener(){
	//									public void actionPerformed(ActionEvent e){
	//										//clean panel from take picture view
	//										cleanPanel();
	//										
	//										//Show add contact view
	//										if(!instructionSingleton.getAddContactView().isActive()){
	//											if(instructionSingleton.getAccordion().getAccordion().getSelectedIndex()!=0)
	//												instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//											instructionSingleton.getContainer().removeAll();
	//											instructionSingleton.setActiveView(instructionSingleton.getAddContactView());
	//											instructionSingleton.setMaxID(instructionSingleton.getActiveView().instruction.length-1);
	//										}else{
	//											instructionSingleton.setActiveView(instructionSingleton.getAddContactView());
	//											instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//										}
	//										instructionSingleton.buildAddContactView();
	//										instructionSingleton.updateInstructionView();
	//
	//										instructionSingleton.getAddContactView().setActive(true);
	//
	//										instructionSingleton.buildMoreHelpView(
	//												instructionSingleton.getMoreHelp().getAboutSet(new int[] {1,2}),
	//												instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {1,2})
	//												);
	//									}
	//								}
	//								);
	//						searchResult.add(addContacts);
	//						searchResult.add(new JButton("Remove Contacts"));
	//						searchResult.add(new JButton("Edit Contacts"));
	//						searchResult.add(new JButton("Make a call from Contacts"));
	//
	//						searchResult.validate();
	//						searchResult.repaint();
	//					}
	//				}
	//				);
	//		contactPanel.add(contactButton);
	//		contactPanel.add(Box.createRigidArea(new Dimension(this.getWidth(), this.getHeight())));
	//		tagCloud.add(contactPanel);
	//		
	//		/*Camera*/
	//		JPanel cameraPanel = new JPanel();
	//		cameraPanel.setOpaque(false);
	//		cameraPanel.setLayout(new BoxLayout(cameraPanel, BoxLayout.X_AXIS));
	//		cameraPanel.add(Box.createRigidArea(new Dimension(5, 30)));
	//		TransparentButton cameraButton = new TransparentButton("                                  ", (float)0.1);
	//		cameraButton.setLayout(new BoxLayout(cameraButton, BoxLayout.Y_AXIS));
	//		cameraButton.add(new JLabel("                                        "));
	//		cameraButton.add(new JLabel("                                        "));
	//		cameraButton.add(new JLabel("                                        "));
	//		cameraButton.addActionListener(
	//				new ActionListener() {
	//					public void actionPerformed(ActionEvent e) {
	//						searchResult.removeAll();
	//						JButton takePicture = new JButton("Taking Pictures");
	//						takePicture.addActionListener(
	//								new ActionListener() {
	//									public void actionPerformed(ActionEvent e){
	//										//clean panel from take picture view
	//										cleanPanel();
	//										//Show add contact view
	//										if(!instructionSingleton.getTakePictureView().isActive()){
	//											if(instructionSingleton.getAccordion().getAccordion().getSelectedIndex()!=0)
	//												instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//											instructionSingleton.getContainer().removeAll();
	//											instructionSingleton.setActiveView(instructionSingleton.getTakePictureView());///
	//											instructionSingleton.setMaxID(instructionSingleton.getActiveView().instruction.length-1);
	//
	//										}else{
	//											instructionSingleton.setActiveView(instructionSingleton.getTakePictureView());///
	//											instructionSingleton.getAccordion().getAccordion().setSelectedIndex(0);
	//										}
	//										instructionSingleton.buildTakePictureView();///
	//										instructionSingleton.updateInstructionView();
	//										instructionSingleton.getTakePictureView().setActive(true);///
	//
	//										instructionSingleton.buildMoreHelpView(///
	//												instructionSingleton.getMoreHelp().getAboutSet(new int[] {3}),
	//												instructionSingleton.getMoreHelp().getAboutAnswer(new int[] {3})
	//												);
	//									}
	//								}
	//								);
	//						searchResult.add(takePicture);
	//						searchResult.add(new JButton("Send a picture to a contact"));
	//						searchResult.add(new JButton("Browse pictures"));
	//						searchResult.add(new JButton("Take a video"));
	//
	//						searchResult.validate();
	//						searchResult.repaint();
	//					}
	//				}
	//				);
	//		cameraPanel.add(cameraButton);
	//		cameraPanel.add(Box.createRigidArea(new Dimension(100, 50)));
	//		tagCloud.add(cameraPanel);
	//		tagCloud.add(Box.createRigidArea(new Dimension(this.getWidth(), 100)));
	//		return tagCloud;
	//	}


}
