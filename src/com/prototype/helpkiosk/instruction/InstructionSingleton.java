package com.prototype.helpkiosk.instruction;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.prototype.helpkiosk.instructioncontrol.InstructionView;
import com.prototype.helpkiosk.instructioncontrol.MoreHelpView;
import com.prototype.helpkiosk.instructioncontrol.StateThread;
import com.prototype.helpkiosk.ui.Accordion;
import com.prototype.helpkiosk.ui.MediaPanel;

public class InstructionSingleton {

	private static InstructionSingleton instance = null;
	
	private Contact contact;
	private Camera camera;
	private Clock clock;
	private Message message;
	private Phone phone;
	private Gallery gallery;
	
	private Instruction[] contactInstruction;
	private Instruction[] cameraInstruction;
	private Instruction[] clockInstruction;
	private Instruction[] messageInstruction;
	private Instruction[] phoneInstruction;
	private Instruction[] galleryInstruction;
	
	private InstructionView CONTACTVIEW;
	private InstructionView CAMERAVIEW;
	private InstructionView CLOCKVIEW;
	private InstructionView MESSAGEVIEW;
	private InstructionView PHONEVIEW;
	private InstructionView GALLERYVIEW;
	
	private MoreHelpView MOREHELPVIEW;
	
	private JPanel container;
	private JPanel container_noshow;
	private JPanel container_parent;
	
	private JPanel highlightContainer;
	private MediaPanel mediaContainer;
	
	private JPanel moreHelpContainer;
	private Accordion accordion;
	
	private JPanel lowerContainer;
	private JPanel lowerPanel;
	private Component lowerBox = Box.createRigidArea(new Dimension(0, 70));
	
	private MoreHelp moreHelp;
	private InstructionView activeView = null;
	private int activeID = -1;
	private int maxID = 0;
	
	private JPanel viewPanel;
	public StateThread stateThread;

	private JPanel lowerLabel;

	private JButton instructionArea;
	
	private boolean threadfinish = false;
	
	protected InstructionSingleton(){
		
	}
	
	public static InstructionSingleton getInstance(){
		if (instance == null){
			instance = new InstructionSingleton();
		}
		return instance;
	}
	
	public void initInstructions(){
		stateThread = new StateThread();
		stateThread.start();
		
		accordion = new Accordion();
		
		this.contact = new Contact();
		this.camera = new Camera();
		this.clock = new Clock();
		this.message = new Message();
		this.phone = new Phone();
		this.gallery = new Gallery();
	
		setAddContactInstruction(contact.getInstruction());
		setTakePictureInstruction(camera.getInstruction());
		setClockInstruction(clock.getInstruction());
		setMessageInstruction(message.getInstruction());
		setPhoneInstruction(phone.getInstruction());
		setGalleryInstruction(gallery.getInstruction());

		
		this.CONTACTVIEW = new InstructionView(this.getContainer(), "CONTACT");
		this.CAMERAVIEW = new InstructionView(this.getContainer(), "CAMERA");
		this.CLOCKVIEW = new InstructionView(this.getContainer(), "CLOCK");
		this.MESSAGEVIEW = new InstructionView(this.getContainer(), "MESSAGE");
		this.PHONEVIEW = new InstructionView(this.getContainer(), "PHONE");
		this.GALLERYVIEW = new InstructionView(this.getContainer(), "GALLERY");
		
		this.MOREHELPVIEW = new MoreHelpView(this.getMoreHelpContainer());
		this.moreHelp = new MoreHelp();		
	}
	
	public StateThread getStateThread(){
		return stateThread;
	}
	
	public String getCMP(){
		return stateThread.getCmp();	
	}
	
	/*
	 * TODO create a new class for video
	 */
	public void showVideo(String type){
		
		mediaContainer.removeAll();
		//mediaContainer.setVisible(true);
		String videoUrl = this.mediaContainer.selectURL(type);
				
		try {
			if(videoUrl!=""){
				// TODO: use this.getClass().getResource("/path/to/our.mp3"); to get real URL if URL is a problem
				URL mediaURL = new URL(videoUrl);
				this.mediaContainer.setURL(mediaURL);
				mediaContainer.show();
				
			}
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL");
		}
		
		mediaContainer.validate();
		mediaContainer.repaint();
	}
	
	/*
	 * TODO create a new class for highlights
	 */
    public void highlight(String type, String name)
    {	
    	JPanel glass = this.getHighlightContainer();
    	glass.removeAll();
    	
    	JPanel box = new JPanel();
    	box.setOpaque(false);
		box.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
		
		JPanel box2 = new JPanel();
    	box2.setOpaque(false);
		box2.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
		
    	
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		filler.setLayout(new BoxLayout(filler, BoxLayout.X_AXIS));
		
		if (type=="nothing") {}
		
		// TODO: put highlighting in correct places highlighting here
		// use names and types from InstructionBox.setBoxActive();
		// Dimension(width, height)
		// Live View dimensions: width = 240; height = 400
		if (name=="camera") {
			if (type=="launcher-icon") {
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 80)));
    			filler.add(Box.createRigidArea(new Dimension(117, 60)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(60, 60)));
			} else if (type=="focus") {
				box.setPreferredSize(new Dimension(225,290));
				glass.add(Box.createRigidArea(new Dimension(250, 25)));
    			filler.add(box);
			} else if (type=="shutter") {
				box.setPreferredSize(new Dimension(60,55));
				glass.add(Box.createRigidArea(new Dimension(250, 328)));
    			filler.add(box);
			}
		}
		
		if (name=="contact") {
			if (type=="launcher-icon") {
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 80)));
    			filler.add(Box.createRigidArea(new Dimension(60, 60)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(120, 60)));
			} else if (type=="new-contact") {
				box.setPreferredSize(new Dimension(50,50));
				this.updateLowerPanel("menu", false);
				glass.add(Box.createRigidArea(new Dimension(350, 350)));
				filler.add(Box.createRigidArea(new Dimension(170, 50)));
//					JLabel two = new JLabel(" 2 ");
//					two.setOpaque(true);
//					two.setBackground(Color.ORANGE);
//					two.setForeground(Color.WHITE);
//					two.setFont(new Font("Helvetica", Font.BOLD, 22));
//				filler.add(two);
				filler.add(box);
//				this.updateLowerPanel("menu", true);
			} else if (type=="new-name") {
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(230,70));
				glass.add(Box.createRigidArea(new Dimension(250, 130)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(50, 100)));
			} else if (type=="done") {
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(180,50));
				glass.add(Box.createRigidArea(new Dimension(250, 180)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(150, 50)));
			}
		}
		
		if (name=="clock") {
			if(type=="launcher-icon") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 80)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(165, 60)));
			} else if (type=="openAlarm") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(50,50));
				glass.add(Box.createRigidArea(new Dimension(250, 2)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(178, 50)));
			} else if (type=="makeGreen") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(50,50));
				glass.add(Box.createRigidArea(new Dimension(250, 75)));
				glass.add(Box.createRigidArea(new Dimension(10, 50)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(190, 50)));
			} else if (type=="changeOrAdd") {
				this.updateLowerPanel("back", false);
			} else if (type=="done") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(90,50));
				glass.add(Box.createRigidArea(new Dimension(250, 340)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(168, 60)));
			} else if (type=="goBack") {
				this.updateLowerPanel("back", true);
			}
		}
		
		if (name=="sendMessage") {
			if (type=="launcher-icon") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 318)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(58, 60)));
			} else if (type=="compose") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(45,45));
				glass.add(Box.createRigidArea(new Dimension(250, 340)));
				filler.add(Box.createRigidArea(new Dimension(183, 45)));
				filler.add(box);
			} else if (type=="addRecipient") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(225,25));
				box2.setPreferredSize(new Dimension(195,25));
				glass.add(Box.createRigidArea(new Dimension(250, 42)));
				glass.add(box);
				glass.add(Box.createRigidArea(new Dimension(250, 110)));
				filler.add(box2);
				filler.add(Box.createRigidArea(new Dimension(40, 25)));
			} else if (type=="send") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(40,30));
				glass.add(Box.createRigidArea(new Dimension(250, 183)));
				filler.add(Box.createRigidArea(new Dimension(195, 30)));
				filler.add(box);
			}
		}
		
		if (name=="viewImage") {
			if (type=="launcher-icon") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 80)));
				filler.add(Box.createRigidArea(new Dimension(170, 60)));
    			filler.add(box);
			} else if (type=="selectImage") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(230,350));
				glass.add(Box.createRigidArea(new Dimension(250, 28)));
    			filler.add(box);
			} else if (type=="showMenus") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(230,390));
    			filler.add(box);
			}
		}
		
		if (name=="makeCall") {
			if (type=="launcher-icon") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 318)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(165, 60)));
			} else if (type=="placeCall") {
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(85,45));
				glass.add(Box.createRigidArea(new Dimension(250, 340)));
				filler.add(box);
			}
		}
			
		glass.add(filler);
		glass.validate();
    	glass.repaint();
    }
	
	public void showMoreHelpPanel(boolean show){
		if(show){
			this.getContainer_parent().remove(getLowerBox());
			this.getContainer_parent().remove(this.getContainer_noshow());
			this.getContainer_parent().add(this.getMoreHelpContainer());
			this.getContainer_parent().add(getLowerBox());
			
		}else if(!show){
			this.getContainer_parent().remove(getLowerBox());
			this.getContainer_parent().remove(this.getMoreHelpContainer());
			this.getContainer_parent().add(this.getContainer_noshow());
			this.getContainer_parent().add(getLowerBox());
		}
	}
	
	public void setActiveView(InstructionView activeView) {
		this.activeView = activeView;
	}

	public InstructionView getActiveView() {
		return activeView;
	}
	
	public void buildAppView(InstructionView view){
		view.buildView(getContainer());
	}
	
//	public void buildContactView(){
//		this.CONTACTVIEW.buildView(getContainer());
//	}
//	
	
	public void buildMoreHelpView(String[] questions, String[] answers){
		if(questions[0]=="") 
			buildEmptyMoreHelpView();
		else 
			this.MOREHELPVIEW.createFloorTab(getMoreHelpContainer(), questions, answers);
	}
	
	public void buildEmptyMoreHelpView(){
		this.MOREHELPVIEW.createEmptyFloorTab(getMoreHelpContainer());
	}
	
	public void buildEmptyMoreHelpView(JPanel container){
		this.MOREHELPVIEW.createEmptyFloorTab(container);
	}

	public void setAddContactInstruction(Instruction[] addContactInstruction) {
		this.contactInstruction = addContactInstruction;
	}

	public Instruction[] getContactInstruction() {
		return contactInstruction;
	}

	public void setTakePictureInstruction(Instruction[] takePictureInstruction) {
		this.cameraInstruction = takePictureInstruction;
	}

	public Instruction[] getTakePictureInstruction() {
		return cameraInstruction;
	}
	
	public void setClockInstruction(Instruction[] clockInstruction) {
		this.clockInstruction = clockInstruction;
	}

	public Instruction[] getClockInstruction() {
		return clockInstruction;
	}
	
	public void setMessageInstruction(Instruction[] messageInstruction) {
		this.messageInstruction = messageInstruction;
	}

	public Instruction[] getMessageInstruction() {
		return messageInstruction;
	}
	
	public void setPhoneInstruction(Instruction[] phoneInstruction) {
		this.phoneInstruction = phoneInstruction;
	}

	public Instruction[] getPhoneInstruction() {
		return phoneInstruction;
	}
	
	public void setGalleryInstruction(Instruction[] galleryInstruction) {
		this.galleryInstruction = galleryInstruction;
	}

	public Instruction[] getGalleryInstruction() {
		return galleryInstruction;
	}

	/*
	 ***Parent and child container for instruction**** 
	 * */
	public void setContainer(JPanel container) {
		this.container = container;
	}

	public JPanel getContainer() {
		return container;
	}
	
	public void setContainer_noshow(JPanel container_noshow) {
		this.container_noshow = container_noshow;
	}

	public JPanel getContainer_noshow() {
		return container_noshow;
	}

	public void setContainer_parent(JPanel container_parent) {
		this.container_parent = container_parent;
	}

	public JPanel getContainer_parent() {
		return container_parent;
	}
	/*
	 ************************************************** 
	 * */
	
	public void updateInstructionView(){
		SwingUtilities.updateComponentTreeUI(this.getContainer());
		SwingUtilities.updateComponentTreeUI(this.getMoreHelpContainer());
	}

	public void setAccordion(Accordion accordion) {
		this.accordion = accordion;
	}
	
	public Accordion getAccordion() {
		return accordion;
	}
	
	public InstructionView getContactView(){
		return this.CONTACTVIEW;
	}
	
	public InstructionView getCameraView(){
		return this.CAMERAVIEW;
	}
	
	public InstructionView getClockView(){
		return this.CLOCKVIEW;
	}
	
	public InstructionView getMessageView(){
		return this.MESSAGEVIEW;
	}
	
	public InstructionView getPhoneView(){
		return this.PHONEVIEW;
	}
	
	public InstructionView getGalleryView(){
		return this.GALLERYVIEW;
	}

	public void setMoreHelpContainer(JPanel moreHelpContainer) {
		this.moreHelpContainer = moreHelpContainer;
	}

	public JPanel getMoreHelpContainer() {
		return moreHelpContainer;
	}

	public MoreHelp getMoreHelp() {
		return moreHelp;
	}

	public void setMOREHELPVIEW(MoreHelpView mOREHELPVIEW) {
		MOREHELPVIEW = mOREHELPVIEW;
	}

	public MoreHelpView getMOREHELPVIEW() {
		return MOREHELPVIEW;
	}

	public void setActiveID(int activeID) {
		this.activeID = activeID;
	}

	public int getActiveID() {
		return activeID;
	}

	public void setMaxID(int maxID) {
		this.maxID = maxID;
	}

	public int getMaxID() {
		return maxID;
	}

	public Component getLowerBox() {
		return lowerBox;
	}

	public void setViewPanel(JPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

	public JPanel getViewPanel() {
		return viewPanel;
	}

	public void setHighlightContainer(JPanel highlightContainer) {
		this.highlightContainer = highlightContainer;
	}

	public JPanel getHighlightContainer() {
		return highlightContainer;
	}

	public void setMediaContainer(MediaPanel mediaContainer) {
		this.mediaContainer = mediaContainer;
	}

	public JPanel getMediaContainer() {
		return mediaContainer;
	}
	
	public void setLowerPanel(JPanel lowerPanel) {
		this.lowerPanel = lowerPanel;
	}

	public void updateLowerPanel(String type, boolean active) {
		if(type=="back") {
			if(active){
				JPanel back = new JPanel();
	    		back.setLayout(new FlowLayout());
	    		back.setOpaque(false);
	    		back.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
	    		back.setBounds(200, 10, 40, 40);
	    		getLowerContainer().add(back);
			}else{
				getLowerContainer().removeAll();
			}
		}
		if(type=="menu"){
			if(active){
				JPanel back = new JPanel();
	    		back.setLayout(new FlowLayout());
	    		back.setOpaque(false);
	    		back.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
	    		back.setBounds(265, 10, 40, 40);
	    		
	    		JPanel backPanel = new JPanel(new FlowLayout());
		    		JLabel backLabel = new JLabel(" 1 ");
		    		backLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
		    		backLabel.setBackground(Color.ORANGE);
		    		backLabel.setForeground(Color.WHITE);
		    		backLabel.setOpaque(true);
		    	backPanel.add(backLabel);
		    	backPanel.setBounds(235, 10, 40, 40);
	    		backPanel.setOpaque(false);
	    		
	    		getLowerContainer().add(backPanel);
	    		getLowerContainer().add(back);
			}else{
				getLowerContainer().removeAll();
			}
		}
		SwingUtilities.updateComponentTreeUI(getLowerContainer());
		this.getLowerContainer().validate();
		this.getLowerContainer().repaint();
	}

	public void setLowerContainer(JPanel lowerContainer) {
		this.lowerContainer = lowerContainer;
	}

	public JPanel getLowerContainer() {
		return lowerContainer;
	}

	public void setLowerLabel(JPanel backLabel) {
		this.lowerLabel = backLabel;
	}
	
	public JPanel getLowerLabel(){
		return lowerLabel;
	}

	public void setInstructionArea(JButton instructionArea) {
		this.instructionArea = instructionArea;
	}
	
	public JButton getInstructionArea(){
		return this.instructionArea;
	}

	public void setThreadfinish(boolean threadfinish) {
		this.threadfinish = threadfinish;
	}

	public boolean isThreadfinish() {
		return threadfinish;
	}
	
}
