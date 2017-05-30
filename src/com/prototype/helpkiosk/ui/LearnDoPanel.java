package com.prototype.helpkiosk.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.prototype.helpkiosk.instruction.InstructionSingleton;

public class LearnDoPanel {
	
	private JPanel panel;
	private JPanel instructionPanel;
	private JPanel child_instructionPanel;
	private JPanel moreHelpPanel;
	private JPanel child_moreHelpPanel;
	
	private int instructionpanel_width = 530;
	private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
	
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	private JPanel child_moreHelpPanel_noshow;
	
	public LearnDoPanel(){
		initSpace();
		this.initInstruction();
	}
	
	public void initSpace(){
		panel = new JPanel();
		
		panel.setBorder(new LineBorder(new Color(0x3B70A3), 2));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(createInstructionPanel());
			panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(createMoreHelpPanel());
			panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
	}
	
	public JPanel createPanel(){	
		return panel;
	}
	
	private JPanel createInstructionPanel(){
		instructionPanel = new JPanel();
		instructionPanel.setBackground(Color.WHITE);
		instructionPanel.setPreferredSize(new Dimension(instructionpanel_width, 900));
		
		this.child_instructionPanel = new JPanel();
		instructionPanel.add(this.child_instructionPanel);
		return instructionPanel;
	}
	
	private JPanel createMoreHelpPanel(){
		moreHelpPanel = new JPanel();
		
		TitledBorder titleBorder = new TitledBorder("More Help");
		titleBorder.setTitleFont(new Font("Helvetica", Font.ITALIC, 20));
		
		moreHelpPanel.setLayout(new BoxLayout(moreHelpPanel, BoxLayout.Y_AXIS));
		moreHelpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		JButton toSearch = new JButton("Go back to Find Info panel");
		toSearch.setPreferredSize(toSearch.getMaximumSize());
		toSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					instructionSingleton.getAccordion().getAccordion().setSelectedIndex(1);
			}
		});
			
		JPanel toSearchPanel = new JPanel();
		toSearchPanel.setLayout(new BoxLayout(toSearchPanel, BoxLayout.X_AXIS));
		toSearchPanel.setOpaque(false);
		toSearchPanel.add(Box.createRigidArea(new Dimension(50, 20)));
		toSearchPanel.add(toSearch);
		toSearchPanel.add(Box.createRigidArea(new Dimension(10,20)));
		
		moreHelpPanel.add(toSearchPanel);
		moreHelpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		child_moreHelpPanel = new JPanel();
		child_moreHelpPanel.setBorder(titleBorder);
		child_moreHelpPanel.setBackground(Color.WHITE);
		child_moreHelpPanel.setLayout(new BoxLayout(child_moreHelpPanel, BoxLayout.Y_AXIS));
		
		child_moreHelpPanel_noshow = new JPanel();
		child_moreHelpPanel_noshow.setBackground(Color.WHITE);
		child_moreHelpPanel_noshow.setPreferredSize(new Dimension(265,0));
		
		moreHelpPanel.add(child_moreHelpPanel);
		moreHelpPanel.add(instructionSingleton.getLowerBox());
		moreHelpPanel.setBorder(null);
		moreHelpPanel.setBackground(Color.WHITE);
		
		return moreHelpPanel;
	}
	
	public void initInstruction(){
        child_instructionPanel.setLayout(new BoxLayout(child_instructionPanel, BoxLayout.Y_AXIS));
        child_instructionPanel.setSize(new Dimension(instructionpanel_width, 900));
        child_instructionPanel.setBackground(Color.WHITE);
        
        instructionSingleton.setContainer(child_instructionPanel);
		instructionSingleton.setContainer_parent(moreHelpPanel);
		instructionSingleton.setContainer_noshow(child_moreHelpPanel_noshow);
		
        instructionSingleton.setMoreHelpContainer(child_moreHelpPanel);
	}
	
}
