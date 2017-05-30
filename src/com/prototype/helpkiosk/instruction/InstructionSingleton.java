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
	
	private AddContactInstruction addContact;
	private TakePictureInstruction takePicture;
	private ClockInstruction clock;
	
	private Instruction[] addContactInstruction;
	private Instruction[] takePictureInstruction;
	private Instruction[] clockInstruction;
	
	private InstructionView ADDCONTACTVIEW;
	private InstructionView TAKEPICTUREVIEW;
	private InstructionView CLOCKVIEW;
	
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
		
		this.addContact = new AddContactInstruction();
		this.takePicture = new TakePictureInstruction();
		this.clock = new ClockInstruction();
	
		setAddContactInstruction(addContact.getInstruction());
		setTakePictureInstruction(takePicture.getInstruction());
		setClockInstruction(clock.getInstruction());
		
		this.ADDCONTACTVIEW = new InstructionView(this.getContainer(), "ADDCONTACT");
		this.TAKEPICTUREVIEW = new InstructionView(this.getContainer(), "TAKEPICTURE");
		this.CLOCKVIEW = new InstructionView(this.getContainer(), "CLOCK");
		
		this.MOREHELPVIEW = new MoreHelpView(this.getMoreHelpContainer());
		this.moreHelp = new MoreHelp();
	}
	
	public StateThread getStateThread(){
		return stateThread;
	}
	
	public String getCMP(){
		return stateThread.getCmp();	
	}
	
	public void showVideo(String type){
		
		mediaContainer.removeAll();
		
		URL mediaURL;
		String videoUrl = null;
		if(type=="openContact"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/addContacts/OpenContactsHD.MPG";
		}else if(type=="newContact"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/addContacts/new-contact.MPG";
		}else if(type=="newName"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/addContacts/addname.MPG";
		}
		else if(type=="addothercomponent"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/addContacts/addothercomponent.MPG";
		}
		else if(type=="contact_done"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/addContacts/contact_done.MPG";
		}
		else if(type=="openCam"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/takePicture/cam.MPG";
		}else if(type=="takePicture"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/takePicture/take-picture.MPG";
		}else if(type=="openClock"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/openclock.MPG";
		}else if(type=="openAlarm"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/openalarm.MPG";
		}else if(type=="makeGreen"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/makegreen.MPG";
		}else if(type=="changeOrAdd"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/changeoradd.MPG";
		}else if(type=="clockdone"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/clock_done.MPG";
		}else if(type=="goBack"){
	        videoUrl = "file:/C:/Documents and Settings/Rock/Desktop/HelpKiosk/video/clock/clock_back.MPG";
		}else if(type=="nothing"){
			videoUrl = "";
		}
		
		try {
			if(videoUrl!=""){
				mediaURL = new URL(videoUrl);
				this.mediaContainer.setURL(mediaURL);
				mediaContainer.show();
			}
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL");
		}
		
		mediaContainer.validate();
		mediaContainer.repaint();
	}
	
    public void highlight(String type, String name)
    {	
    	JPanel glass = this.getHighlightContainer();
    	glass.removeAll();
    	
    	JPanel box = new JPanel();
    	box.setOpaque(false);
		box.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
		
    	
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		filler.setLayout(new BoxLayout(filler, BoxLayout.X_AXIS));
		
		if(type=="nothing")
		{
			
		}
		
		if(name=="camera")
		{
			if(type=="launcher-icon"){
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 115)));
    			filler.add(Box.createRigidArea(new Dimension(117, 60)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(60, 60)));
			}
			if(type=="shutter"){
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 330)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(178, 60)));
			}
		}
		
		if(name=="contact")
		{
			if(type=="launcher-icon")
			{
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 115)));
    			filler.add(Box.createRigidArea(new Dimension(60, 60)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(120, 60)));
			}
			if(type=="new-contact")
			{
				box.setPreferredSize(new Dimension(120,60));
				this.updateLowerPanel("menu", false);
				glass.add(Box.createRigidArea(new Dimension(250, 290)));
				filler.add(Box.createRigidArea(new Dimension(110, 60)));
					JLabel two = new JLabel(" 2 ");
					two.setOpaque(true);
					two.setBackground(Color.ORANGE);
					two.setForeground(Color.WHITE);
					two.setFont(new Font("Helvetica", Font.BOLD, 22));
				filler.add(two);
				filler.add(box);
				this.updateLowerPanel("menu", true);
			}
			if(type=="new-name"){
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(230,70));
				glass.add(Box.createRigidArea(new Dimension(250, 130)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(50, 100)));
			}
			if(type=="done"){
				this.updateLowerPanel("menu", false);
				box.setPreferredSize(new Dimension(180,50));
				glass.add(Box.createRigidArea(new Dimension(250, 180)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(150, 50)));
			}
		}
		
		if(name=="clock")
		{
			if(type=="launcher-icon")
			{
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 115)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(170, 60)));
			}
			if(type=="openAlarm")
			{
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(60,60));
				glass.add(Box.createRigidArea(new Dimension(250, 330)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(178, 60)));
			}
			if(type=="makeGreen"){
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(50,50));
				glass.add(Box.createRigidArea(new Dimension(250, 75)));
				glass.add(Box.createRigidArea(new Dimension(10, 50)));
				filler.add(box);
				filler.add(Box.createRigidArea(new Dimension(190, 50)));
			}
			if(type=="changeOrAdd"){
				this.updateLowerPanel("back", false);
			}if(type=="done"){
				this.updateLowerPanel("back", false);
				box.setPreferredSize(new Dimension(90,50));
				glass.add(Box.createRigidArea(new Dimension(250, 340)));
    			filler.add(box);
    			filler.add(Box.createRigidArea(new Dimension(168, 60)));
			}
			if(type=="goBack"){
				this.updateLowerPanel("back", true);
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
	
	public void buildAddContactView(){
		this.ADDCONTACTVIEW.buildView(getContainer());
	}
	
	public void buildTakePictureView(){
		this.TAKEPICTUREVIEW.buildView(getContainer());
	}
	
	public void buildClockView(){
		this.CLOCKVIEW.buildView(getContainer());
	}
	
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
		this.addContactInstruction = addContactInstruction;
	}

	public Instruction[] getAddContactInstruction() {
		return addContactInstruction;
	}

	public void setTakePictureInstruction(Instruction[] takePictureInstruction) {
		this.takePictureInstruction = takePictureInstruction;
	}

	public Instruction[] getTakePictureInstruction() {
		return takePictureInstruction;
	}
	
	public void setClockInstruction(Instruction[] clockInstruction) {
		this.clockInstruction = clockInstruction;
	}

	public Instruction[] getClockInstruction() {
		return clockInstruction;
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
	
	public InstructionView getAddContactView(){
		return this.ADDCONTACTVIEW;
	}
	
	public InstructionView getTakePictureView(){
		return this.TAKEPICTUREVIEW;
	}
	
	public InstructionView getClockView(){
		return this.CLOCKVIEW;
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
