package com.prototype.helpkiosk.instruction;

public class Camera {
	
	private Instruction[] instruction = new Instruction[4];
	
	
	public Camera(){
		populateInstruction("takePic");
	}

	/*
 * TODO: update with new Camera instructions
 */
	private void populateInstruction(String input) {
		if (input == "takePic") {
			takePicInstruction();
		} else if (input == "takeVid") {
			takeVidInstruction();
		} else {
			// if not taking something we launching!
			launchLockInstruction();
		}
	}

	private void takePicInstruction() {
		instruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>"
										, false, new int[]{0}, "takePicture", 0, true);
		
		instruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>", false, new int[]{0}, "takePicture", 1, false);
				
		instruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/kHEJ1u5\" width=\"40\" height=\"40\"> to take a photo.</html>"
										, true, new int[]{9,10}, "takePicture", 2, true);
	}
	
	private void takeVidInstruction() {
		instruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>"
				, false, new int[]{0}, "takePicture", 0, true);

		instruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>", false, new int[]{0}, "takePicture", 1, false);

		instruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/ElPEohd\" width=\"40\" height=\"40\"> to take a photo.</html>"
				, true, new int[]{9,10}, "takePicture", 2, true);
	}
	
	private void launchLockInstruction() {
		instruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>"
				, false, new int[]{0}, "takePicture", 0, true);

		instruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>", false, new int[]{0}, "takePicture", 1, false);

		instruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/ElPEohd\" width=\"40\" height=\"40\"> to take a photo.</html>"
				, true, new int[]{9,10}, "takePicture", 2, true);
	}
	
	public Instruction[] getInstruction(){
		return instruction;
	}
}
