package com.prototype.helpkiosk.instruction;

public class Message {
	
	private Instruction[] messageInstruction = new Instruction[4];
	
	
	public Message(){
		populateInstruction("sendMsg");
	}

	private void populateInstruction(String input) {
		if (input == "sendMsg") {
			sendMsgInstruction();
		} else if (input == "viewMsg") {
			// we're viewing!
			viewMsgInstruction();
		}
	}
	
	private void viewMsgInstruction() {
		messageInstruction[0] = new Instruction("<html>Tap Messages <img src=\"http://imgur.com/z5swwBJ.png\" width=\"40\" height=\"40\"> on the Apps screen.</html>", 
				false, new int[]{0}, "viewMessage", 0, true);
		
		messageInstruction[1] = new Instruction("<html>On the messages list, select a contact.</html>", 
				false, new int[]{0}, "viewMessage", 1, true);
		
		messageInstruction[2] = new Instruction("<html>View your conversation.</html>", 
				false, new int[]{0}, "viewMessage", 2, false);
	}

	private void sendMsgInstruction() {
		messageInstruction[0] = new Instruction("<html>Tap Messages <img src=\"http://imgur.com/z5swwBJ.png\" width=\"40\" height=\"40\"> on the Apps screen.</html>", 
				false, new int[]{0}, "sendMessage", 0, true);
		
		messageInstruction[1] = new Instruction("<html>Tap <img src=\"http://imgur.com/DolazUT.png\" width=\"40\" height=\"40\">.</html>",
				false, new int[]{0}, "sendMessage", 1, true);
		
		messageInstruction[2] = new Instruction("<html>Add recipients and enter a message.</html>", 
				true, new int[]{20,21}, "sendMessage", 2, false);
		
		messageInstruction[3] = new Instruction("<html>Tap SEND to send the message.</html>", 
				true, new int[]{20,21}, "sendMessage", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return messageInstruction;
	}
}
