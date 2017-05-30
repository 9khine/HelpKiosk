package com.prototype.helpkiosk.instructioncontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.prototype.helpkiosk.instruction.Instruction;
import com.prototype.helpkiosk.instruction.InstructionBox;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

public class InstructionView extends JPanel {
	
	private String _ADDCONTACT = "ADDCONTACT";
	private String _TAKEPICTURE = "TAKEPICTURE";
	private String _CLOCK = "CLOCK";
	public Instruction[] instruction;
	private InstructionBox[] instructionBox;
	private String title;
	private boolean isActive = false;
	private Dimension rigidAreaDimension = new Dimension(0, 20);
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	
	public Border borderIfActive = new CompoundBorder(
									BorderFactory.createLineBorder(Color.ORANGE, 4),
									new EmptyBorder(2, 5, 1, 1));
	public Border borderIfInactive = new EmptyBorder(2,6,1,1);
	
	public InstructionView(JPanel panel, String instructionType){
		
		if (instructionType.equalsIgnoreCase(this._ADDCONTACT)){
			title = "Adding Contacts";
			instruction = instructionSingleton.getAddContactInstruction();
		}
		else if (instructionType.equalsIgnoreCase(this._TAKEPICTURE)){
			title = "Taking a Picture";
			instruction = instructionSingleton.getTakePictureInstruction();
		}
		else if (instructionType.equalsIgnoreCase(this._CLOCK)){
			title = "Setting an Alarm";
			instruction = instructionSingleton.getClockInstruction();
		}
		
		instructionBox = new InstructionBox[instruction.length];
	}
	
	public void buildView(JPanel panel){
		panel.add(Box.createRigidArea(this.rigidAreaDimension));
		panel.add(createTitleArea());
		JLabel guide = new JLabel("Please touch on a step to move on to the next step.");
		guide.setFont(new Font("Helvetica", Font.ITALIC, 15));
		panel.add(guide);
		panel.add(Box.createRigidArea(this.rigidAreaDimension));
		
		for (int i=0; i<instruction.length ; i++)
		{
			panel.add(createInstructionArea(instruction[i], i));
			panel.add(Box.createRigidArea(this.rigidAreaDimension));
		}
	}
	
	public JLabel createTitleArea(){
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleLabel.setHorizontalTextPosition(JLabel.LEFT);
		titleLabel.setVerticalTextPosition(JLabel.CENTER);
		return titleLabel;
	}
	
	public JButton createInstructionArea(Instruction instruction, int instructionID){
		
		instructionBox[instructionID] = new InstructionBox(instruction, instructionID);
		return instructionBox[instructionID].getBoxArea();
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public InstructionBox getInstructionBox(int instructionID){
		return instructionBox[instructionID];
	}
	
}
