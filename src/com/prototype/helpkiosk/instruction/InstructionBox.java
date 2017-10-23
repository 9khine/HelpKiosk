package com.prototype.helpkiosk.instruction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class InstructionBox
{
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	public JButton box, step, instructionArea, instructionArea_parent, step_parent;
	public Instruction instruction;
	public int instructionID;
	public JPanel container_moreHelp = instructionSingleton.getMoreHelpContainer();
	public JPanel container_instruction = instructionSingleton.getContainer();
	public JButton moreHelpLink;
	public Border borderIfActive = new CompoundBorder(
									BorderFactory.createLineBorder(Color.ORANGE, 4),
									new EmptyBorder(2, 5, 1, 1));
	public Border borderIfDone = new CompoundBorder(
			BorderFactory.createLineBorder(Color.GREEN, 4),
			new EmptyBorder(2, 5, 1, 1));
	public Border borderIfInactive = new EmptyBorder(2,6,1,1);
	
	public JLabel checkmark = new JLabel( new ImageIcon("img/checkmark.png") );
	public JLabel checkmark_empty = new JLabel (new ImageIcon("img/checkmarkempty.png") );
	
	public JLabel menuIcon = new JLabel(new ImageIcon("img/menu.png"));
	public JLabel alarmIcon = new JLabel(new ImageIcon("img/alarm.png"));
	
	public InstructionBox(Instruction instruction, final int instructionID){
		this.instruction = instruction;
		this.instructionID = instructionID;
		
		moreHelpLink = new JButton("Show More Help");
		moreHelpLink.setVisible(false);
		moreHelpLink.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	instructionSingleton.showMoreHelpPanel(true);
			        	container_moreHelp.revalidate();
						container_moreHelp.repaint();
			        }
			    } 
			);
		
		box = new JButton();
		box.setLayout(new FlowLayout());
		styleButton(box);
		addActionListener(box);
		
		step = new JButton("Step " + (instructionID+1) + " : ");
		step.setLayout(new FlowLayout());
		step.setVerticalAlignment(SwingConstants.TOP);
		step.setFont(new Font("Helvetica", Font.PLAIN, 22));
		addActionListener(step);
		styleButton(step);
		
		instructionArea = new JButton(instruction.getInstruction());
		instructionArea.setFont(new Font("Helvetica", Font.PLAIN, 22));
		instructionArea.setLayout(new FlowLayout());
		instructionArea.setVerticalAlignment(SwingConstants.TOP);
		instructionArea.setHorizontalAlignment(SwingConstants.LEFT);
		instructionArea.setHorizontalTextPosition(AbstractButton.LEADING);
		styleButton(instructionArea);
		addActionListener(instructionArea);
		
		instructionSingleton.setInstructionArea(instructionArea);
		
		instructionArea_parent = new JButton();
		instructionArea_parent.setLayout(new BoxLayout(instructionArea_parent, BoxLayout.Y_AXIS));
		styleButton(instructionArea_parent);
		addActionListener(instructionArea_parent);
		
		
		
		step_parent = new JButton();
		step_parent.setLayout(new BoxLayout(step_parent, BoxLayout.Y_AXIS));
		styleButton(step_parent);
		addActionListener(step_parent);
		
		// TODO: edit text box size
		int length = instruction.getInstruction().length();
		int small = 40;
		int medsmall = 70;
		int medium = 90;
		int tall = 135;
		if (length < 40) {
			instructionArea.setPreferredSize(new Dimension(350, small));
			step.setPreferredSize(new Dimension(100, small));
		} else if (length < 75 && length >= 40) {
			instructionArea.setPreferredSize(new Dimension(350, medsmall));
			step.setPreferredSize(new Dimension(100, medsmall));
		} else if (length < 130 && length >= 75) {
			instructionArea.setPreferredSize(new Dimension(350, medium));
			step.setPreferredSize(new Dimension(100, medium));
		} else {
			instructionArea.setPreferredSize(new Dimension(350, tall));
			step.setPreferredSize(new Dimension(100, tall));
		}
		
		
//		if (length > 38 && length < 57) {
//			int height = (length/30+1)*34;
//			instructionArea.setPreferredSize(new Dimension(350, height));
//			step.setPreferredSize(new Dimension(100, height));
//		} else if (length > 56 && length < 192) {
//			if (length == 88 || length == 77 || length == 118 || length == 191) {
//				int height = (length/30+1)*30;
//				instructionArea.setPreferredSize(new Dimension(350, height));
//				step.setPreferredSize(new Dimension(100, height));
//			} else {
//				int height = (length/30+1)*34;
//				instructionArea.setPreferredSize(new Dimension(350, height));
//				step.setPreferredSize(new Dimension(100, height));
//			}
//		} else {
//			instructionArea.setPreferredSize(new Dimension(350, 34));
//			step.setPreferredSize(new Dimension(100, 34));
//		}
		
		instructionArea_parent.add(instructionArea);
		step_parent.add(step);
		
		//show or not show more help panel
		if(instruction.isHasMoreHelp())
		{
			JButton filler = new JButton("--------");
			filler.setForeground(Color.WHITE);
			styleButton(filler);
			
			instructionArea_parent.add(Box.createRigidArea(new Dimension(0, 10)));
			instructionArea_parent.add(moreHelpLink);
			
			step_parent.add(Box.createRigidArea(new Dimension(0, 10)));
			step_parent.add(filler);
		}
		

		box.add(step_parent);
		box.add(instructionArea_parent);
		
	}

	protected void setActive(int id) {
		MoreHelp moreHelp = instructionSingleton.getMoreHelp();
		
		int activeID = instructionSingleton.getActiveID();
		int maxID = instructionSingleton.getMaxID();
		
		container_moreHelp.removeAll();
		
		//activate this instruction with instruction id = id
		setBoxActive(true, false);
		instructionSingleton.setActiveID(id);
		activeID = id;
		
		// TODO: De-activate other instructions -- set as "Done"
		for (int i=0 ; i<=maxID ;  i++) {
			if (i != activeID) {
				instructionSingleton.getActiveView().getInstructionBox(i).setBoxInactive();
//				if(instructionSingleton.getActiveView().getInstructionBox(i).instruction.isActive()) {
//					instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(false, true);
//				}
			}
		}
		
		//building more help view related to this instruction
		instructionSingleton.buildMoreHelpView(
			moreHelp.getMoreHelpSet(instruction.getMoreHelpID()), 
			moreHelp.getAnswerSet(instruction.getMoreHelpID())
		);
		
		container_instruction.revalidate();
		container_instruction.repaint();
		
		container_moreHelp.revalidate();
		container_moreHelp.repaint();
		
	}
	
	public void setBoxInactive() {
		instruction.setActive(false);
		box.setBorder(borderIfInactive);
	}
	
	public void setBoxActive(boolean active, boolean done) {
		instruction.setActive(active);
		
		if (active && !done){
			box.setBorder(borderIfActive);
			if(instruction.isHasMoreHelp()){
				instructionSingleton.showMoreHelpPanel(false);
				moreHelpLink.setVisible(true);
			}else if(!instruction.isHasMoreHelp()){
				instructionSingleton.showMoreHelpPanel(false);
			}
			
			String type = null;
			String videoType = null;
			String name = null;
			
			//TODO - KEEP VIDEO TYPE NAME CONSISTENT WITH INSTRUCTION TYPE
			if (instruction.getId()==0) {
					type = "launcher-icon";
					if (instruction.getType()=="addContact") {
						videoType = "openContact"; 
					} else if (instruction.getType()=="takePicture") {
						videoType = "openCam";
					} else if (instruction.getType()=="clock") {
						videoType = "openClock";
					} else if (instruction.getType()=="viewImage") {
						videoType = "openGallery";
					}  else if (instruction.getType()=="makeCall") {
						videoType = "openPhone";
					}  else if (instruction.getType()=="sendMessage") {
					videoType = "openMessage";
					} else {
						type = "nothing";
						videoType = "nothing";
					}
			} 
			
			if (instruction.getType()=="addContact") {
				name = "contact";
				
				if (instruction.getId()==1) {
					type = "new-contact";
					videoType = "newContact";
				}
				if(instruction.getId()==2){
					type = "new-name";
					videoType = "enterInfo";
				}
				if(instruction.getId()==3){
					type = "saveContact";
					videoType = "saveContact";
				}
				
			} else if (instruction.getType()=="takePicture") {
				name = "camera";
				if (instruction.getId()==1) {
					type = "focus";
					videoType = "takePicture";
				}
				if (instruction.getId()==2) {
					type = "shutter";
					videoType = "nothing";
				}
				
			} else if (instruction.getType()=="clock") {
				name = "clock";
				
				if(instruction.getId()==1){
					type = "touchAlarm";
					videoType = "touchAlarm";
				}
				if(instruction.getId()==2){
					type = "setAlarm";
					videoType = "setAlarm";
				}
				if(instruction.getId()==3){
					type = "saveAlarm";
					videoType = "saveAlarm";
				}
				
			} else if (instruction.getType()=="sendMessage") {
				name = "sendMessage";

				if (instruction.getId()==1) { 
					type = "compose";
					videoType = "newMessage";
				}
				if (instruction.getId()==2) {
					type = "addRecipient";
					videoType = "enterMessage";
				}
				if (instruction.getId()==3) {
					type = "send";
					videoType = "sendMessage";
				}
				
			} else if (instruction.getType()=="viewImage") {
				name = "viewImage";

				if (instruction.getId()==1) {
					type = "selectImage";
					videoType = "select";
				}
				if (instruction.getId()==2) {
					type = "showMenus";
					videoType = "menuTap";
				}
			} else if (instruction.getType()=="makeCall") {
				name = "makeCall";
				if (instruction.getId()==1) {
					type = "enterNumber";
					videoType = "enterNumber";
				}
				if (instruction.getId()==2) {
					type = "placeCall";
					videoType = "call";
				}
			} else {
				type = "nothing";
				videoType = "nothing";
			}
			
			instructionSingleton.highlight(type, name);
			instructionSingleton.showVideo(videoType);
			
		}else if (!active){
			moreHelpLink.setVisible(false);
			box.setBorder(borderIfInactive);
			step.setFont(new Font("Helvetica", Font.PLAIN, 22));
			instructionArea.setFont(new Font("Helvetica", Font.PLAIN, 22));
		}
	}
	
	public void addActionListener(JButton button){
		button.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	if (instructionSingleton.getCameraView().isActive()) {
			        		if(instructionSingleton.getCameraView().getInstructionBox(1).instruction.isActive())
			        			setActive(instructionID);
			        		else if(instructionSingleton.getCameraView().getInstructionBox(2).instruction.isActive())
			        			setActive(instructionID);
			        	}
			        	
			        	if (instructionID==0||instructionID==3) {
			        		setActive(instructionID);
			        	} else {
			        		if (instructionSingleton.getActiveView().getInstructionBox(instructionID-1).instruction.isDone()) {
			        			setActive(instructionID);
			        		} else {
			        			// TODO: icon doesn't work properly (to fix later)
			        			ImageIcon messageIcon = new ImageIcon("img/logo.png");
				        		JOptionPane.showMessageDialog(null,
				        				"Please complete the previous step before you continue.",
				        				"Previous step is not done",
				        				JOptionPane.INFORMATION_MESSAGE,
				        			    messageIcon);
			        		}
			        	}
			        }
			    }
			);
		
	}

	public void styleButton(JButton button){
		button.setOpaque(true);
		button.setBackground(null);
		button.setBorder(borderIfInactive);
		button.setBackground(Color.WHITE);
	}
	
	public JButton getBoxArea(){
		return box;
	}
	
	public JButton getInstructionArea(){
		return this.instructionArea;
	}
	
}
