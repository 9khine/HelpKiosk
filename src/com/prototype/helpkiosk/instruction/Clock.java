package com.prototype.helpkiosk.instruction;

public class Clock {
	
	private Instruction[] clockInstruction = new Instruction[4];
	
	public Clock() {
		populateInstruction("setAlarm");
	}

	private void populateInstruction(String input) {
		// TODO Auto-generated method stub
		if (input =="setAlarm") {
			setAlarmInstruction();
		} else if (input == "stopAlarm") {
			stopAlarmINstruction();
		} else if (input == "delAlarm") {
			delAlarmInstruction();
		}
	}

	private void setAlarmInstruction() {
		clockInstruction[0] = new Instruction("<html>On the Apps screen, tap Clock.</html>",
				false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch Alarm <img src=\"http://i.imgur.com/W5xzbuc.png\" width=\"40\" height=\"40\"/></html>",
				false, new int[]{0}, "clock", 1, true);
		
		clockInstruction[2] = new Instruction("<html>Set an alarm time.</html>",
				false, new int[]{0}, "clock", 2, false);
		
		clockInstruction[3] = new Instruction("<html>Tap <b>Save</b>.</html>",
				true, new int[]{14,15,16,17,18,19}, "clock", 3, false);
	}
	
	private void stopAlarmINstruction() {
		// TODO Auto-generated method stub
		clockInstruction[0] = new Instruction("<html>Tap DISMISS to stop an alarm.</html>",
				false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>If you have previously enabled the snooze option, tap SNOOZE to repeat the alarm after a specified length of time.</html>",
				false, new int[]{0}, "clock", 1, true);
	}
	
	private void delAlarmInstruction() {
		// TODO Auto-generated method stub
		clockInstruction[0] = new Instruction("<html>On the Apps screen, tap Clock.</html>", false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch Alarm <img src=\"http://i.imgur.com/W5xzbuc.png\" width=\"40\" height=\"40\"/></html>",
				false, new int[]{0}, "clock", 1, true);
		
		clockInstruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/hTNCAo4\" width=\"40\" height=\"40\"/> on an alarm in the list of alarms.</html>",
				false, new int[]{0}, "clock", 2, false);
	}
	
	public Instruction[] getInstruction() {
		return clockInstruction;
	}	
}
