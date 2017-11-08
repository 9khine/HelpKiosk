package com.prototype.helpkiosk.instruction;

public class Clock {
	
	private Instruction[] clockInstruction = new Instruction[4];
	
	public Clock() {
		populateInstruction("setAlarm");
	}

	private void populateInstruction(String input) {
		if (input =="setAlarm") {
			setAlarmInstruction();
		} else if (input == "stopAlarm") {
			stopAlarmInstruction();
		} else if (input == "delAlarm") {
			delAlarmInstruction();
		}
	}

	// TODO: for the clock I think we should assume there will be alarms in existence already, therefore we should have instructions to add a new alarm
	// the old instructions are beneath, the new ones are to add an alarm to an existing list of alarms
	private void setAlarmInstruction() {
		clockInstruction[0] = new Instruction("<html>On the Apps screen, tap <b>Clock</b> "
				+ "<img src=\"http://i.imgur.com/siXC6Z4.png\" width=\"40\" height=\"40\"/>.</html>",
				false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch Alarm <img src=\"http://i.imgur.com/W5xzbuc.png\" width=\"40\" height=\"40\"/></html>",
				false, new int[]{0}, "clock", 1, true);
		
		clockInstruction[2] = new Instruction("<html>Tap ADD, and set an alarm time.</html>",
				false, new int[]{14,15,16}, "clock", 2, false);
		
		clockInstruction[3] = new Instruction("<html>Tap <b>Save</b>.</html>",
				true, new int[]{14,15,16}, "clock", 3, false);
	}
	
	private void setAlarmInstructionOLD() {
		clockInstruction[0] = new Instruction("<html>On the Apps screen, tap <b>Clock</b> "
				+ "<img src=\"http://i.imgur.com/siXC6Z4.png\" width=\"40\" height=\"40\"/>.</html>",
				false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch Alarm <img src=\"http://i.imgur.com/W5xzbuc.png\" width=\"40\" height=\"40\"/></html>",
				false, new int[]{0}, "clock", 1, true);
		
		clockInstruction[2] = new Instruction("<html>Set an alarm time.</html>",
				true, new int[]{14,15,16}, "clock", 2, false);
		
		clockInstruction[3] = new Instruction("<html>Tap <b>Save</b>.</html>",
				true, new int[]{14,15,16}, "clock", 3, false);
	}
	
	private void stopAlarmInstruction() {
		clockInstruction[0] = new Instruction("<html>Tap <b>DISMISS</b> to stop an alarm.</html>",
				false, new int[]{0}, "stopAlarm", 0, false);
		
		clockInstruction[1] = new Instruction("<html>If you have previously enabled the snooze option, tap <b>SNOOZE</b> to repeat the alarm after a specified length of time.</html>",
				true, new int[]{16}, "stopAlarm", 1, false);
	}
	
	private void delAlarmInstruction() {
		clockInstruction[0] = new Instruction("<html>On the Apps screen, tap <b>Clock</b> "
				+ "<img src=\"http://imgur.com/siXC6Z4.png\" width=\"40\" height=\"40\"/>.</html>",
				false, new int[]{0}, "deleteAlarm", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch Alarm <img src=\"http://i.imgur.com/W5xzbuc.png\" width=\"40\" height=\"40\"/></html>",
				false, new int[]{0}, "deleteAlarm", 1, true);
		
		clockInstruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/hTNCAo4.png\" width=\"40\" height=\"40\"/> on an alarm in the list of alarms.</html>",
				false, new int[]{0}, "deleteAlarm", 2, false);
	}
	
	public Instruction[] getInstruction() {
		return clockInstruction;
	}	
}
