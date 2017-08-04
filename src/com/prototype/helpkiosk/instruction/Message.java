package com.prototype.helpkiosk.instruction;

public class Message {
	
	private Instruction[] messageInstruction = new Instruction[4];
	
	
	public Message(){
		populateInstruction();
	}

private void populateInstruction() {
		// TODO Auto-generated method stub
		
	}

	/*
 * TODO: update with Message instructions
 */
	private void populateInstruction(String input) {
		if (input == "send") {
			sendMsgInstruction();
		} else {
			// we're viewing!
			viewMsgInstruction();
		}
		
	}
	
	private void viewMsgInstruction() {
		// TODO Auto-generated method stub
		messageInstruction[0] = new Instruction("<html>Tap Messages on the Apps screen.</html>", 
						false, new int[]{0}, "viewMessage", 0, true);
		
		messageInstruction[1] = new Instruction("<html>On the messages list, select a contact.</html>", 
						false, new int[]{0}, "addContact", 1, true);
		
		messageInstruction[2] = new Instruction("<htmlView your conversation.</html>", 
				true, new int[]{1,11,12,13,2}, "addContact", 2, false);
	}

	private void sendMsgInstruction() {
		// TODO Auto-generated method stub
		messageInstruction[0] = new Instruction("<html>Tap Messages on the Apps screen.</html>", 
						false, new int[]{0}, "addContact", 0, true);
		
		messageInstruction[1] = new Instruction("<html>Tap COMPOSEICON.</html>", // TODO: insert compose icon here
						false, new int[]{0}, "addContact", 1, true);
		
		messageInstruction[2] = new Instruction("<html>Enter contact information.</html>", 
				true, new int[]{1,11,12,13,2}, "addContact", 2, false);
		
		messageInstruction[3] = new Instruction("<html>Tap SAVE</html>", 
				true, new int[]{3,4,5,6}, "addContact", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return messageInstruction;
	}
}
