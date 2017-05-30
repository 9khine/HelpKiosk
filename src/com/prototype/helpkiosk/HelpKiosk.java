package com.prototype.helpkiosk;

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
		      }
		    });
	}

}
