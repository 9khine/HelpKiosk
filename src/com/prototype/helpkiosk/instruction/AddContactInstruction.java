package com.prototype.helpkiosk.instruction;

public class AddContactInstruction {
	
	private Instruction[] contactInstruction = new Instruction[4];
	
	
	public AddContactInstruction(){
		populateInstruction();
	}


	private void populateInstruction() {
		contactInstruction[0] = new Instruction("<html>Tap Contacts <img src=\"img/add-contact-icon.png\"> on the Apps screen</html>", false, new int[]{0}, "addContact", 0, true);
		
		contactInstruction[1] = new Instruction("<html>Tap <img src=\"img/add-contact-icon.png\"> and select a storage location.</html>"
												, false, new int[]{0}, "addContact", 1, true);
		
		contactInstruction[2] = new Instruction("<html>Enter contact information.</html>", true, new int[]{1,11,12,13,2}, "addContact", 2, false);
		
		contactInstruction[3] = new Instruction("<html>Tap SAVE</html>"
												, true, new int[]{3,4,5,6}, "addContact", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return contactInstruction;
	}
	
	
	
}
