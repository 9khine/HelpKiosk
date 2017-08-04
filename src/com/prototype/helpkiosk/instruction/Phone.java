package com.prototype.helpkiosk.instruction;

public class Phone {
	
	private Instruction[] phoneInstruction = new Instruction[4];
	
	
	public Phone(){
		populateInstruction();
	}

/*
 * TODO: update with Phone instructions
 */
	private void populateInstruction() {
		
		phoneInstruction[0] = new Instruction
				("<html>Tap Contacts <img src=\"http://i.imgur.com/XMA779I.png\" width=\"40\" height=\"40\"> on the Apps screen</html>", 
						false, new int[]{0}, "addContact", 0, true);
		
		phoneInstruction[1] = new Instruction
				("<html>Tap <img src=\"http://i.imgur.com/bkvC2B6.png\" width=\"40\" height=\"40\"> and select a storage location.</html>", 
						false, new int[]{0}, "addContact", 1, true);
		
		phoneInstruction[2] = new Instruction("<html>Enter contact information.</html>", 
				true, new int[]{1,11,12,13,2}, "addContact", 2, false);
		
		phoneInstruction[3] = new Instruction("<html>Tap SAVE</html>", 
				true, new int[]{3,4,5,6}, "addContact", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return phoneInstruction;
	}
	
	
	
}