package com.prototype.helpkiosk.instruction;

public class AddContactInstruction {
	
	private Instruction[] contactInstruction = new Instruction[4];
	
	
	public AddContactInstruction(){
		populateInstruction();
	}


	private void populateInstruction() {
		contactInstruction[0] = new Instruction("<html>Open your contacts.</html>", false, new int[]{0}, "addContact", 0, true);
		
		contactInstruction[1] = new Instruction("<html>Press Menu <img src=\"http://www.viliaingriany.com/menu.png\" /> and touch <b>New contact</b>.</html>"
												, false, new int[]{0}, "addContact", 1, true);
		
		contactInstruction[2] = new Instruction("<html>Enter the contact's name.</html>", true, new int[]{1,11,12,13,2}, "addContact", 2, false);
		
		contactInstruction[3] = new Instruction("<html>Touch a category of contact information " +
												"such as phone numbers and email addresses to " +
												"enter that kind of information about your contact. " +
												"When you're finished, touch <b>Done</b></html>"
												, true, new int[]{3,4,5,6}, "addContact", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return contactInstruction;
	}
	
	
	
}
