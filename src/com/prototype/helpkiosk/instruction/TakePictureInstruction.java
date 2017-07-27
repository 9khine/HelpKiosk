package com.prototype.helpkiosk.instruction;

public class TakePictureInstruction {
	
	private Instruction[] instruction = new Instruction[4];
	
	
	public TakePictureInstruction(){
		populateInstruction();
	}


	private void populateInstruction() {
		instruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>"
										, false, new int[]{0}, "takePicture", 0, true);
		
		instruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>", false, new int[]{0}, "takePicture", 1, false);
		
//		instruction[2] = new Instruction("<html>Frame your subject on screen.</html>", true, new int[]{7,8}, "takePicture", 2, false);
		
		instruction[2] = new Instruction("<html>Tap <img src=\"http://i.imgur.com/shB233b.png\" width=\"40\" height=\"40\"/> to take a photo.</html>"
										, true, new int[]{9,10}, "takePicture", 2, true);
	}
	
	public Instruction[] getInstruction(){
		return instruction;
	}
	
	
	
}
