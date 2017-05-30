package com.prototype.helpkiosk.instructioncontrol;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.prototype.helpkiosk.instruction.InstructionSingleton;



public class MoreHelpView{
	
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	private JPanel panel;
	
	public MoreHelpView(JPanel panel){
		this.panel = panel;
	}
	
	public void createEmptyFloorTab(JPanel panel){
		FloorTab ft = new FloorTab();
		panel.add(ft);
	}
	
	public void createFloorTab(JPanel panel, String[] questions, String[] answers){
		
		FloorTab ft = new FloorTab();
		panel.add(ft);
		for(int i=0 ; i<questions.length ; i++){
			JPanel jp = new JPanel();
			JTextArea answerArea = new JTextArea(answers[i]);
			jp.setBackground(Color.WHITE);
		    jp.setBorder(BorderFactory.createLineBorder(Color.GRAY));  
		    
		    answerArea.setLineWrap(true);
			answerArea.setEditable(false);
			answerArea.setOpaque(true);
			answerArea.setWrapStyleWord(true);
			answerArea.setMargin(new Insets(5,0,5,0));
			answerArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
			answerArea.setPreferredSize(new Dimension(210,250));
		    
		    jp.add(answerArea);
		    if(i==questions.length-1) ft.setLastAnswerArea(answerArea);
		    ft.addTab(questions[i] + " " + i, jp);
		}
		ft.showLast();
	}
}

class FloorTab extends JPanel implements ActionListener {
	GridBagConstraints gbc = new GridBagConstraints(0,1,1,1,1.0,1.0,
			GridBagConstraints.CENTER,
			GridBagConstraints.BOTH,
			new Insets(0,0,0,0),0,0);
	GridBagLayout gbl = new GridBagLayout();
	CardLayout cl = new CardLayout();
	JPanel panels = new JPanel(cl);
	java.util.ArrayList<JButton> buttons = new java.util.ArrayList<JButton>();
	int buttonTextLines=1;
	JTextArea answerArea;
	
	public FloorTab() {
		
		panels.setBackground(Color.WHITE);
		setLayout(gbl);
		add(panels, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty=0.0;
	}
	
	public void setLastAnswerArea(JTextArea answerArea){
		this.answerArea = answerArea;
		answerArea.setVisible(false);
	}
	
	public JTextArea getLastAnswerArea(){
		return answerArea;
	}
	
	public void addTab(String name, JPanel panel) {
		gbc.gridy = getComponentCount();
		if (gbc.gridy==1) gbc.gridy=0;
		JButton jb;
		jb = new JButton(name);
		jb.setHorizontalAlignment(SwingConstants.LEFT);
		jb.setFont(new Font("Helvetica", Font.PLAIN, 15));
		
		add(jb,gbc);
		buttons.add(jb);
		jb.addActionListener(this);
		panels.add(name,panel);
		
	}
	
	public void showLast() {
		int y=0;
		GridBagConstraints tmp;
		JButton srcButton = buttons.get(buttons.size()-1);
		for (int i=0; i<buttons.size(); i++) {
			JButton jb = buttons.get(i);
			tmp = gbl.getConstraints(jb);
			tmp.gridy = y++;
			gbl.setConstraints(jb, tmp);
			if (srcButton==jb) {
				tmp = gbl.getConstraints(panels);
				tmp.gridy = y++;
				gbl.setConstraints(panels, tmp);
			}
		}
		cl.show(panels,srcButton.getText());
	}
	
	public void actionPerformed(ActionEvent ae) {
		int y=0;
		GridBagConstraints tmp;
		JButton srcButton = (JButton)ae.getSource();
		for (int i=0; i<buttons.size(); i++) {
			if(i==buttons.size()-1) getLastAnswerArea().setVisible(true);
			JButton jb = buttons.get(i);
			tmp = gbl.getConstraints(jb);
			tmp.gridy = y++;
			gbl.setConstraints(jb, tmp);
			if (srcButton==jb) {
				tmp = gbl.getConstraints(panels);
				tmp.gridy = y++;
				gbl.setConstraints(panels, tmp);
			}
		}
		cl.show(panels,srcButton.getText());
		
	}
}