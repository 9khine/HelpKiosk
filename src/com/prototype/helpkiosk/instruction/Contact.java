package com.prototype.helpkiosk.instruction;

public class Contact {

	private Instruction[] contactInstruction = new Instruction[4];


	public Contact(){
		populateInstruction("addContact");
	}


	private void populateInstruction(String input) {
		if (input.equals("addContact")) {
			addContactInstruction();
		} else if (input.equals("searchContact")) {
			searchContactInstruction();
		}
	}

	private void addContactInstruction() {
		contactInstruction[0] = new Instruction
				("<html>Tap Contacts <img src=\"http://i.imgur.com/XMA779I.png\" width=\"40\" height=\"40\"> on the Apps screen.</html>", 
						false, new int[]{0}, "addContact", 0, true);

		contactInstruction[1] = new Instruction
				("<html>Tap <img src=\"http://i.imgur.com/bkvC2B6.png\" width=\"40\" height=\"40\">.</html>", 
						false, new int[]{0}, "addContact", 1, true);

		contactInstruction[2] = new Instruction("<html>Enter contact information.</html>", 
				true, new int[]{1,11,12,13,2}, "addContact", 2, false);

		contactInstruction[3] = new Instruction("<html>Tap SAVE.</html>", 
				true, new int[]{3,4,5,6}, "addContact", 3, false);
	}

	private void searchContactInstruction() {
		// TODO
		contactInstruction[0] = new Instruction
				("<html>Tap Contacts <img src=\"http://i.imgur.com/XMA779I.png\" width=\"40\" height=\"40\"> on the Apps screen.</html>", 
						false, new int[]{0}, "searchContact", 0, true);

		contactInstruction[1] = new Instruction
				("<html>Tap <img src=\"http://i.imgur.com/bkvC2B6.png\" width=\"40\" height=\"40\"> and select a storage location.</html>", 
						false, new int[]{0}, "searchContact", 1, true);

		contactInstruction[2] = new Instruction("<html>Enter contact information.</html>", 
				true, new int[]{1,11,12,13,2}, "searchContact", 2, false);

		contactInstruction[3] = new Instruction("<html>Tap SAVE.</html>", 
				true, new int[]{3,4,5,6}, "searchContact", 3, false);
	}

	public Instruction[] getInstruction(){
		return contactInstruction;
	}



}
