package com.prototype.helpkiosk.instruction;

public class MoreHelp {
	
	private String[] moreHelpContent;
	private String[] answer;
	
	private String[] aboutTitle;
	private String[] aboutContent;
	
	public MoreHelp(){
		moreHelpContent = new String[22];
		answer = new String[22];
		aboutTitle = new String[7];
		aboutContent = new String[7];
		init();
	}
	
	public void init(){
		populateAboutTitle();
		populateAboutContent();
		populateContent();
		populateAnswer();	
	}

	private void populateContent() {
		
		moreHelpContent[0] = "";
		
		/*For adding a contact*/
		moreHelpContent[1] = "<html>How do I erase characters?</html>";
		moreHelpContent[11]= "<html>How do I use the suggestions?</html>";
		moreHelpContent[12]= "<html>How do I add a new word to<br>the suggestions dictionary?</html>";
		moreHelpContent[13]= "<html>How do I enter numbers,<br>symbols and other characters?</html>";
		moreHelpContent[2] = "<html>How do I go from one field to another?</html>";
		moreHelpContent[3] = "<html>How do I see other fields<br>(e.g., phone number, email address)?</html>";
		moreHelpContent[4] = "<html>How do I add more than one<br>entry for a category,<br>for example, to add both work and<br>home numbers?</html>";
		moreHelpContent[5] = "<html>How do I change category labels,<br>such as <strong>Mobile</strong> and <b>Work</b> for<br>a phone number?</html>";
		moreHelpContent[6] = "<html>How do I select a picture for<br>this contact?</html>";
		
		/*Take Picture*/
		moreHelpContent[7] = "<html>How do I zoom in or out?</html>";
		moreHelpContent[8] = "<html>How do I control the exposure?</html>";
		moreHelpContent[9] = "<html>How do I take a picture more quickly?</html>";
		moreHelpContent[10]= "<html>How do I cancel taking the picture?</html>";
		
		/*Clock*/
		moreHelpContent[14]= "<html>How do I set the time of the alarm?</html>";
		moreHelpContent[15]= "<html>How do I select a ringtone<br>for the alarm?</html>";
		moreHelpContent[16]= "<html>How do I get the phone to vibrate,<br>in addition to playing the ringtone?</html>";
		moreHelpContent[17]= "<html>How do I set the days when I want<br>the alarm to sound?</html>";
		moreHelpContent[18]= "<html>How do I enter a name for the alarm?</html>";
		moreHelpContent[19]= "<html>How do I add a new alarm?</html>";
		
		/*Messages*/
		moreHelpContent[20]= "<html>How do I block unwanted messages?</html>";
		moreHelpContent[21]= "<html>How do I set the message notification?</html>";
	}
	
	public String[] getMoreHelpSet(int[] IDset){
		String[] set = new String[IDset.length];
		for(int i=0 ; i<set.length ; i++){
			set[i] = moreHelpContent[IDset[i]];
		}
		return set;
	}
	
	public String[] getAnswerSet(int[] IDset){
		String[] set = new String[IDset.length];
		for(int i=0 ; i<set.length ; i++)
			set[i] = answer[IDset[i]];
		return set;
	}
	
	public String[] getAboutSet(int[] IDset){
		String[] set = new String[IDset.length];
		for(int i=0 ; i<set.length ; i++){
			set[i] = aboutTitle[IDset[i]];
		}
		return set;
	}
	
	public String[] getAboutAnswer(int[] IDset){
		String[] set = new String[IDset.length];
		for(int i=0 ; i<set.length ; i++)
			set[i] = aboutContent[IDset[i]];
		return set;
	}
	
	private void populateAnswer() {
		answer[0] = "";
		
		/*For adding a contact*/
		answer[1] = "Use the Delete key to erase characters to the left of the cursor.";
		answer[11]= "i. Press the left or right arrow in the strip to view more suggestions.\n\n"+
					"ii. Press space or a punctuation mark to enter the red suggested word.\nOR touch canother suggested word to enter.";
		answer[12]= "Touch and hold the leftmost word to add it to the distionary.";
		answer[13]= "i. Press the Symbols key to switch\nto the numbers and symbols\nkeyboard. Press the Alt key to\nview additional symbols; press it again\nto switch back.\n\n" +
					"ii. Touch & hold a vowel or the\nC, N, or S key to open a\nsmall window where you can touch\nan accented vowel or other\nalternate letter.\n\n" +
					"iii. Touch & hold the Period key ( . ) to open a small window with a set of common symbol keys.\n\n" +
					"iv. Touch & hold a number or symbol key to open a window of additional symbols.";
		answer[2] = "Press Next Button or roll the trackball.";
		answer[3] = "Scroll the page to view all categories.";
		answer[4] = "Touch a category's plus button to add more than one entry for that category.";
		answer[5] = "Touch the button to the left of the item of contact information to open a menu with preset labels or touch Custom in the menu to create your own label.";
		answer[6] = "Touch the Picture Frame icon to take photo or select one form the device's Gallery.\n\n" +
					"This selected photo will display next to the name in your list of contacts and in other applications";
		
		/*Take Picture*/
		answer[7] = "i. Touch the screen to open the Zoom control and then touch the plus or minus button to zoom in or out gradually.\n\n" +
					"ii. OR Double-tap the screen to zoom all the way in or out.";
		answer[8] = "i. Touch the slider to open the camera settings.\n\n" +
					"ii. The preview image changes as you change the settings.";
		answer[9] = "i. You can touch & hold the Shutter icon to focus first, before taking a picture, then lift your finger.\n\n" +
					"ii. OR You can press & hold the Trackball to focus first, before taking a picture, then lift your finger";
		answer[10]= "Slide your finger off the Shutter icon or roll the trackball and release your finger.";
	
		/*Clock*/
		answer[14]= "i. Touch 'TIME'\n\n" +
					"ii. In the dialog that opens, you change the time by touching the\n(+) or (-) buttons and the AM or\nPM buttons. Or touch a time\nto type the time you want.";
		answer[15]= "i. Touch 'RINGTONE'\n\n" +
					"ii. You can select a ringtone that comes with the phone or one that yousaved as a ringtone by using\nthe Music application.";
		answer[16]= "Touch vibrate.";
		answer[17]= "Touch repeat";
		answer[18]= "Touch label";
		answer[19]= "Touch ADD ALARM to add a new one.";
		
		/*MESSAGES*/
		answer[20]= "Block messages from specific numbers or messages that contain phrases added to your block list.\n" +
					"1 Tap Messages on the Apps screen.\n\n" +
					"2 Tap MORE → Settings → Block messages.\n\n" +
					"3 Tap Block list.\n" +
					"To add phrases to block, tap Blocked phrases.\n\n" +
					// TODO: insert plus icon (+)
					"<html>4 Tap INBOX or CONTACTS to select contacts and tap Tap <img src=\"http://imgur.com/64dwlgv\" width=\"40\" height=\"40\">.</html>\n\n" +
					"To manually enter a number, tap Enter number, and then enter a phone number.\n" +
					"When you receive messages from the blocked numbers or messages including blocked phrases, you will not be notified."
					+ "To view blocked messages, tap MORE → Settings → Block messages → Blocked messages.";
		answer[21]= "You can change notification sound, display options, and more.\n\n" +
					"1 Tap Messages on the Apps screen.\n\n" +
					"2 Tap MORE → Settings → Notifications, and then tap the switch to activate it.\n\n" +
					"3 Change the notification settings.\n" +
					"• Notification sound: Change the notification sound.\n" +
					"• Vibrate: Set the device to vibrate when you receive messages.\n" +
					"• Pop-up display: Set the device to display messages in pop-up windows.\n" +
					"• Preview message: Set the device to display message content on the locked screen and in pop-up windows.\n\n\n" +
					"Setting a message reminder\n\n" +
					"You can set an alert at an interval to let you know that you have unchecked notifications.\n" +
					"If this feature is not activated, open the Apps screen, tap Settings → Accessibility → Notification reminder,"
					+ "and then tap the switch to activate it. Then, tap the Messages switch to activate it.";
	}
	
	private void populateAboutTitle() {
		aboutTitle[0] = "";
		
		/*For adding a contact*/
		aboutTitle[1] = "<html>About: Contacts</html>";
		aboutTitle[2] = "<html>About: Adding Contacts</html>";
		
		/*CAMERA*/
		aboutTitle[3] = "<html>About: Camera</html>";
		
		/*CLOCK*/
		aboutTitle[4] = "<html>About: Clock</html>";
		aboutTitle[5] = "<html>About: Alarm</html>";
		
		/*MESSAGES*/
		aboutTitle[6] = "<html>About: Messages</html>";
		
	}
	
	private void populateAboutContent(){
		aboutContent[0] = "";
		
		/*For adding a contact*/
		aboutContent[1] = "Contacts gives you quick and easy access to the people you want to reach. " +
						"When you first turn on your phone and sign into your Google Account, " +
						"any existing Google contacts are downloaded to your phone. " +
						"After that, changes to your contacts are synchronized. " +
						"Information about your contacts is shared with other applications, " +
						"such a Gmail, Google Talk, Messaging, Gallery (for sharing photos and videos), and so on." +
						"\n\nIf you have more than one account with contact information, " +
						"Contacts merges duplicate contacts into a single entry. " +
						"You can also manage that process manually.";
		
		aboutContent[2] = "You can add contacts on your phone and synchronize them with the contacts in your" +
				"Google Account, Microsoft Exchange ActiveSync account, or other accounts that support syncing contacts." +
				"\n\nWhen you reply to or forward an email message to an email address that is not in Contacts, " +
				"the email address is added as a contact. Contacts tries to join new" +
				"addresses with existing contacts, to create a single entry. You can also manage that" +
				"process manually.";
		
		/*CAMERA*/
		aboutContent[3] = "Camera is a combination camera and camcorder that you use to shoot " +
							"and share pictures and videos. Pictures and videos are stored on " +
							"the phone's microSD card, so you must install microSD card " +
							"to use Camera. You can copy your pictures and videos from " +
							"the microSD card to a computer, as described in Connecting " +
							"to a computer via USB.";
		
		/*CLOCK*/
		aboutContent[4] = "In addition to displaying the date and time, the Clock application displays information about the weather and your phone. You can also use Clock to turn your phone into an alarm clock.";
		aboutContent[5] = "You can set an alarm by modifying an existing alarm or by adding a new one. You can change a number of settings for the alarms you set. You can also change how times are displayed in Clock in the Settings applications. ";
		
		/*MESSAGES*/
		aboutContent[6] = "Send and view messages by conversation.";
	}
	
}
