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
		
		int length = instruction.getInstruction().length(); 
		System.out.println(length);
		if (length>38 && length<57){
			instructionArea.setPreferredSize(new Dimension(350,(length/30+1)*34));
			step.setPreferredSize(new Dimension(100,(length/30+1)*34));
		}else if (length>56 && length <192){
			if(length==88 || length==77 || length==118|| length==191){
				instructionArea.setPreferredSize(new Dimension(350,(length/30+1)*30));
				step.setPreferredSize(new Dimension(100,(length/30+1)*30));
			}else{
				instructionArea.setPreferredSize(new Dimension(350,(length/30)*30));
				step.setPreferredSize(new Dimension(100,(length/30)*30));
			}
		}else{
			instructionArea.setPreferredSize(new Dimension(350, 34));
			step.setPreferredSize(new Dimension(100, 34));
		}
		
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
		
		//De-activate other instructions -- set as "Done"
		for(int i=0 ; i<=maxID ;  i++){
			if(i!=activeID){
				if(instructionSingleton.getActiveView().getInstructionBox(i).instruction.isActive()){
					instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(false, true);
				}
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
	
	public void setBoxActive(boolean active, boolean done)
	{
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
			if(instruction.getId()==0){
					type = "launcher-icon";
					if(instruction.getType()=="addContact")
						videoType = "openContact"; 
					else if(instruction.getType()=="takePicture") 
						videoType = "openCam";
					else 
						videoType = "openClock";
			}
			if(instruction.getType()=="addContact"){
				name = "contact";
				if(instruction.getId()==1){
					type = "new-contact";
					videoType = "newContact";
				}
				if(instruction.getId()==2){
					type = "new-name";
					videoType = "newName";
				}
				if(instruction.getId()==3){
					type = "nothing";
					videoType = "addothercomponent";
				}
				if(instruction.getId()==4){
					type = "done";
					videoType = "contact_done";
				}
				
			}else if(instruction.getType()=="takePicture"){
				name = "camera";
				if(instruction.getId()==3){
					type = "shutter";
					videoType = "takePicture";
				}
			}else if(instruction.getType()=="clock"){
				name = "clock";
				if(instruction.getId()==1){
					type = "openAlarm";
					videoType = "openAlarm";
				}
				if(instruction.getId()==2){
					type = "makeGreen";
					videoType = "makeGreen";
				}
				if(instruction.getId()==3){
					type = "changeOrAdd";
					videoType = "changeOrAdd";
				}
				if(instruction.getId()==4){
					type = "done";
					videoType = "clockdone";
				}
				if(instruction.getId()==5){
					type = "goBack";
					videoType = "goBack";
				}
			}else{
				type = "nothing";
				videoType = "openContact";
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
			        	if(instructionSingleton.getTakePictureView().isActive()){
			        		if(instructionSingleton.getTakePictureView().getInstructionBox(1).instruction.isActive())
			        			setActive(instructionID);
			        		else if(instructionSingleton.getTakePictureView().getInstructionBox(2).instruction.isActive())
			        			setActive(instructionID);
			        	}
			        	if(instructionID==0||instructionID==3) setActive(instructionID);
			        	else{
			        		if(instructionSingleton.getActiveView().getInstructionBox(instructionID-1).instruction.isDone())
				        		setActive(instructionID);
				        	else
				        		JOptionPane.showMessageDialog(null, "It looks like you have not completed the previous step yet.\nPlease try previous step before going on with this step.", "Previous step is not done", 0);
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
