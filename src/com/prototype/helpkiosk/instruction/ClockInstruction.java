package com.prototype.helpkiosk.instruction;

public class ClockInstruction {
	
	private Instruction[] clockInstruction = new Instruction[4];
	
	
	public ClockInstruction(){
		populateInstruction();
	}


	private void populateInstruction() {
		clockInstruction[0] = new Instruction("<html>Touch the Clock icon.</html>", false, new int[]{0}, "clock", 0, true);
		
		clockInstruction[1] = new Instruction("<html>Touch the Alarm <img src=\"http://www.viliaingriany.com/alarm.png\" /> icon.</html>"
												, false, new int[]{0}, "clock", 1, true);
		
		clockInstruction[2] = new Instruction("<html>If alarm is not underlined in green, " +
												"touch the icon next to an alarm to turn it on.</html>", false, new int[]{0}, "clock", 2, false);
		
		clockInstruction[3] = new Instruction("<html>Touch an existing alarm to change " +
												"its time and other attributes. " +
												"When you’re finished, touch <b>Done</b>.</html>"
												, true, new int[]{14,15,16,17,18,19}, "clock", 3, false);
	}
	
	public Instruction[] getInstruction(){
		return clockInstruction;
	}
	
	
	
}
