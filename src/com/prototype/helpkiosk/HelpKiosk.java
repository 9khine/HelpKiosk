package com.prototype.helpkiosk;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.plaf.FontUIResource; 

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
		        
		        String log = String.format("===========================%nStarting new session%ntime = " + System.currentTimeMillis() + "%n%n");
				try {
					Files.write(Paths.get("log.txt"), log.getBytes(), StandardOpenOption.APPEND);
				} catch (IOException f) {
					f.printStackTrace();
				}
		        
		        // Splash screen
		        Object[] options = {"    Get started!    "};
				JPanel splash = new JPanel();
				ImageIcon img = new ImageIcon("img/splash01.png");
				JLabel image = new JLabel(img);
				JLabel html = new JLabel("<html>"
						+ "<div width=\"1160\" height=\"100\" style=\"border: 1px solid black; background-color: #518AC0; color: white;\">"
							+ "<h1 style=\"padding: 20px;\">Welcome to HelpKiosk!</h1>"
						+ "</div>"
						+ "<div style=\"padding: 20px 0px 0px 60px;\">"
							+ "<p></p>"
							+ "<h2>Learn to us your Samsung S7 smartphone with the help of:</h2>"
							+ "<p></p>"
							+ "<h2> - Step by step instructions</h2>"
							+ "<p></p>"
							+ "<h2> - A live view highlighting  what to click</h2>"
							+ "<p></p>"
							+ "<h2> - Demo videos showing how to complete steps</h2>"
							+ "<p></p>"
							+ "<p></p>"
							+ "<p></p>"
						+ "</div>"
						+ "<h2 style=\"text-align: center; color: #518AC0;\">Get started by clicking the button below.</h2>"
						+ "</html>");
				splash.setOpaque(false);
				splash.add(html);
				splash.setPreferredSize(new Dimension(1480, 720));
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.BOLD,24))); 
				JOptionPane.showOptionDialog(null,
						splash,
						"Welcome to HelpKiosk!",
						JOptionPane.YES_NO_OPTION,
        			    JOptionPane.QUESTION_MESSAGE,
        			    null,
        			    options, 
        			    options[0]);
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.BOLD,14))); 
		      }
		    });
	}

}
