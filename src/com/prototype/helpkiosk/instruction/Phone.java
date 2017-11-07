package com.prototype.helpkiosk.instruction;

public class Phone {

	private Instruction[] phoneInstruction = new Instruction[3];


	public Phone(){
		populateInstruction("makeCall");
	}

	private void populateInstruction(String input) {
		if (input.equals("makeCall")) {
			makingCallInstruction();
		} else if (input.equals("receiveCalls")) {
			receivingCallInstruction();
		}
	}
	
	private void makingCallInstruction() {
		phoneInstruction[0] = new Instruction
				("<html>Tap Phone <img src=\"http://i.imgur.com/X61ThUy.png\" width=\"40\" height=\"40\"> on the Apps screen.</html>", 
						false, new int[]{0}, "makeCall", 0, true);

		phoneInstruction[1] = new Instruction
				("<html>Enter the phone number. If the keys does not appear on the screen, "
						+ "tap <img src=\"http://i.imgur.com/kKP12Uh.png\" width=\"40\" height=\"40\"> to open the keypad.</html>", 
						true, new int[]{0}, "makeCall", 1, false);
		
		phoneInstruction[2] = new Instruction("<html>Tap <img src=\"http://i.imgur.com/rW0SoDK.png\" width=\"40\" height=\"40\"> "
				+ "to make a voice call.</html>",
				// ", or tap <img src=\"http://i.imgur.com/TKvVvHN.png\" width=\"40\" height=\"40\"> to make a video call."
				true, new int[]{0}, "makeCall", 2, false);
	}

	private void receivingCallInstruction() {
		
		// TODO update instruction
		phoneInstruction[0] = new Instruction
				("<html>Tap Phone <img src=\"http://i.imgur.com/X61ThUy.png\" width=\"40\" height=\"40\"> on the Apps screen</html>", 
						false, new int[]{0}, "receiveCalls", 0, false);

		phoneInstruction[1] = new Instruction
				("<html>Enter the phone number. If the keys does not appear on the screen, "
						+ "tap <img src=\"http://i.imgur.com/kKP12Uh.png\" width=\"40\" height=\"40\"> to open the keypad.</html>", 
						false, new int[]{0}, "receiveCalls", 1, false);

		phoneInstruction[2] = new Instruction("<html>Tap <img src=\"http://i.imgur.com/rW0SoDK.png\" width=\"40\" height=\"40\"> "
				+ "to make a voice call, or tap <img src=\"http://i.imgur.com/TKvVvHN.png\" width=\"40\" height=\"40\"> "
				+ "to make a video call.</html>", 
				true, new int[]{0}, "receiveCalls", 2, false);
	}

	public Instruction[] getInstruction(){
		return phoneInstruction;
	}



}
