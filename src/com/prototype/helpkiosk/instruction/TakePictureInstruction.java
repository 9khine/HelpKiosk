package com.prototype.helpkiosk.instruction;

public class TakePictureInstruction {
	
	private Instruction[] instruction = new Instruction[4];
	
	
	public TakePictureInstruction(){
		populateInstruction();
	}


	private void populateInstruction() {
		instruction[0] = new Instruction("<html>Touch the Camera icon on the Home screen or in the Launcher.</html>"
										, false, new int[]{0}, "takePicture", 0, true);
		
		instruction[1] = new Instruction("<html>If necessary, drag the control to the Camera position.</html>"
										, false, new int[]{0}, "takePicture", 1, false);
		
		instruction[2] = new Instruction("<html>Frame your subject on screen.</html>", true, new int[]{7,8}, "takePicture", 2, false);
		
		instruction[3] = new Instruction("<html>Touch the Shutter icon on screen or press the Trackball to take a photo.</html>"
										, true, new int[]{9,10}, "takePicture", 3, true);
	}
	
	public Instruction[] getInstruction(){
		return instruction;
	}
	
	
	
}
