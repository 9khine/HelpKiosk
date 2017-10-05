package com.prototype.helpkiosk.instruction;

public class Gallery {
	
	private Instruction[] galleryInstruction = new Instruction[3];
	
	
	public Gallery(){
		populateInstruction("viewImage");
	}

	private void populateInstruction(String input) {
		if (input.equals("viewImage")) {
			viewImageInstruction();
		} else if (input.equals("viewVideo")) {
			viewVideoInstruction();
		} else if (input.equals("deleteImage")) {
			deleteImageInstruction();
		}
	}
	
	private void viewImageInstruction() {
		galleryInstruction[0] = new Instruction
				("<html>Tap <b>Gallery</b> <img src=\"http://i.imgur.com/3vP5Y7u.png\" width=\"40\" height=\"40\">"
						+ "on the Apps screen</html>", 
						false, new int[]{0}, "viewImage", 0, false);
		
		galleryInstruction[1] = new Instruction
				("<html>Select an image.</html>", 
						false, new int[]{0}, "viewImage", 1, false);
				
		galleryInstruction[2] = new Instruction("<html>To hide or show the menus, tap the screen.</html>", 
				true, new int[]{0}, "viewImage", 2, false);
	}
	
	private void viewVideoInstruction() {
		
		// TODO update instruction
		galleryInstruction[0] = new Instruction
				("<html>Tap Gallery <img src=\"http://i.imgur.com/3vP5Y7u.png\" width=\"40\" height=\"40\"> "
						+ "on the Apps screen</html>", 
						false, new int[]{0}, "viewVideo", 0, true);
		
		galleryInstruction[1] = new Instruction
				("<html>Select an image.</html>", 
						false, new int[]{0}, "viewVideo", 1, true);
		
		galleryInstruction[2] = new Instruction("<html>To hide or show the menus, tap the screen.</html>", 
				true, new int[]{1,11,12,13,2}, "viewVideo", 2, false);
	}
	
	private void deleteImageInstruction() {
		
		// TODO update instruction
		galleryInstruction[0] = new Instruction
				("<html>Tap Gallery <img src=\"http://i.imgur.com/bkvC2B6.png\" width=\"40\" height=\"40\"> "
						+ "on the Apps screen</html>", 
						false, new int[]{0}, "deleteImage", 0, true);
		
		galleryInstruction[1] = new Instruction
				("<html>Select an image.</html>", 
						false, new int[]{0}, "deleteImage", 1, true);
		
		galleryInstruction[2] = new Instruction("<html>To hide or show the menus, tap the screen.</html>", 
				true, new int[]{1,11,12,13,2}, "deleteImage", 2, false);
	}
	
	public Instruction[] getInstruction(){
		return galleryInstruction;
	}
	
	
	
}
