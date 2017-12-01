package com.prototype.helpkiosk;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.prototype.helpkiosk.ui.Workspace;

public class HelpKiosk {
	
	public static void main(String[] args) {
		initUserInterface();

	}
	
	private static void initUserInterface(){
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		        Workspace workspace = new Workspace();
		        workspace.setDefaultCloseOperation(3);
		        workspace.setLocationRelativeTo(null);
		        workspace.setVisible(true);
		        
		        // Splash screen
		        Object[] options = {"Get started!"};
				JPanel splash = new JPanel();
				ImageIcon img = new ImageIcon("img/splash01.png");
				JLabel image = new JLabel(img);
				splash.setOpaque(false);
				splash.add(image);
				splash.setPreferredSize(new Dimension(600, 400));
				JOptionPane.showOptionDialog(null,
						splash,
						"Welcome to HelpKiosk!",
						JOptionPane.YES_NO_OPTION,
        			    JOptionPane.QUESTION_MESSAGE,
        			    null,
        			    options, 
        			    options[0]);
		      }
		    });
	}

}
