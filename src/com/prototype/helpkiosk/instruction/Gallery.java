package com.prototype.helpkiosk.instruction;

public class Gallery {
	
	private Instruction[] galleryInstruction = new Instruction[4];
	
	
	public Gallery(){
		populateInstruction();
	}

	private void populateInstruction() {
		/* View image instruction*/
		galleryInstruction[0] = new Instruction
				// TODO insert Gallery app image
				("<html>Tap Gallery <img src=\"http://i.imgur.com/bkvC2B6.png\" width=\"40\" height=\"40\"> "
						+ "on the Apps screen</html>", 
						false, new int[]{0}, "viewImage", 0, true);
		
		galleryInstruction[1] = new Instruction
				// TODO 
				("<html>Select an image.</html>", 
						false, new int[]{0}, "viewImage", 1, true);
		
		galleryInstruction[2] = new Instruction("<html>To hide or show the menus, tap the screen.</html>", 
				true, new int[]{1,11,12,13,2}, "viewImage", 2, false);
	}
	
	public Instruction[] getInstruction(){
		return galleryInstruction;
	}
	
	
	
}
