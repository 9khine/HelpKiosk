package com.prototype.helpkiosk.instruction;

public class Camera {
	
	private Instruction[] cameraInstruction = new Instruction[4];
	
	
	public Camera(){
		populateInstruction("takePic");
	}

	private void populateInstruction(String input) {
		if (input == "takePic") {
			takePicInstruction();
		} else if (input == "takeVid") {
			takeVidInstruction();
		} else {
			// if not taking something we launching! for now...
			launchLockInstruction();
		}
	}

	private void takePicInstruction() {
		cameraInstruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>",
				false, new int[]{0}, "takePicture", 0, true);
		
		// TODO: can we use logcat to check if the camera focuses?
		cameraInstruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>",
				false, new int[]{0}, "takePicture", 1, false);
				
		cameraInstruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/kHEJ1u5.png\" width=\"40\" height=\"40\"> to take a photo.</html>",
				true, new int[]{7,8}, "takePicture", 2, true);
	}
	
	private void takeVidInstruction() {
		cameraInstruction[0] = new Instruction("<html>Tap <b>Camera</b> on the Apps screen.</html>",
				false, new int[]{0}, "takePicture", 0, true);

		cameraInstruction[1] = new Instruction("<html>Tap the image on the preview screen where the camera should focus.</html>",
				false, new int[]{0}, "takePicture", 1, false);

		cameraInstruction[2] = new Instruction("<html>Tap <img src=\"http://imgur.com/ElPEohd.png\" width=\"40\" height=\"40\"> to take a photo.</html>",
				true, new int[]{9,10}, "takePicture", 2, false);
	}
	
	private void launchLockInstruction() {
		cameraInstruction[0] = new Instruction("<html>To quickly take photos, launch <b>Camera</b> on the locked screen.</html>",
				false, new int[]{0}, "takePicture", 0, false);
		
		cameraInstruction[1] = new Instruction("<html>On the locked screen, drag <img src=\"http://imgur.com/WdjXH0h.png\" width=\"40\" height=\"40\"> outside the large circle.</html>",
				true, new int[]{7,8}, "takePicture", 1, false);
	}
	
	public Instruction[] getInstruction(){
		return cameraInstruction;
	}
}
