package com.prototype.helpkiosk.instructioncontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.prototype.helpkiosk.instruction.InstructionBox;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

public class StateThread extends Thread 
	{
		private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
		
		private static String act;
		private static String cat;
		private static String cmp;
		
		// TODO: update camera items for S7
		public String DONE_TYPE = "W/EntityModifier";
		public String SNAPSHOT = "V/camera  (  573): Start autofocus.";
		
		public static String ACT_APP_LAUNCH = "android.intent.action.MAIN";
		public static String CAT_APP_LAUNCH = "android.intent.category.LAUNCHER";
		public static String CAT_HOME_LAUNCH = "android.intent.category.HOME";
		
		// Device strings
		public static String CMP_LAUNCH_HOME = "com.sec.android.app.launcher/.activities.LauncherActivity";
		
		// LAUNCH Instruction strings
		public static String CMP_LAUNCH_CONTACTS = "com.android.contacts/.activities.PeopleActivity";
		public static String CMP_LAUNCH_CAMERA = "com.sec.android.app.camera/.Camera";
		public static String CMP_LAUNCH_CLOCK = "com.sec.android.app.clockpackage/.ClockPackage";
		public static String CMP_LAUNCH_MESSAGES = "com.android.mms/.ui.ConversationComposer";
		public static String CMP_LAUNCH_PHONE = "com.android.contacts/com.android.dialer.DialtactsActivity";
		public static String CMP_LAUNCH_GALLERY = "com.sec.android.gallery3d/.app.GalleryOpaqueActivity";
		
		/*CLOCK*/
		public static String CMP_ACTIVATE_ALARM = "com.google.android.deskclock/com.android.deskclock.AlarmClock";
		public static String CMP_SET_ALARM = "com.google.android.deskclock/com.android.deskclock.SetAlarm";
		
		// TODO: find new way to set alarm codes
		public static String NEW_ALARM_CODE = "";
		// 06-21 13:24:35.594 23640 23640 D AlarmListView: resizeLayoutWithDrag : 2
		// 06-21 13:25:54.054 23640 23640 D AlarmMainActivity: onSaveAlarm()
		public static String ACTIVATE_ALARM_CODE = "";
		// 06-23 12:00:29.986 12047 12047 D AlarmProvider: setAlarmActive() - id: 4,willChangeButtonActive: true,activeNow: 0
		public static String DEACTIVATE_ALARM_CODE = "";
		// 06-21 13:14:57.024 23640 23640 D AlarmProvider: setAlarmActive to false
				
		/*CONTACTS*/
		public static String CMP_NEW_CONTACT = "com.android.contacts/.activities.ContactEditorActivity";
		
		/*MESSAGES*/
		public static String COMPOSE_BUTTON = "ComposerPerformance";
		

		public void run() {
		    try {
		    	while(!instructionSingleton.isThreadfinish()) 
		    		runStuff();
		    }
		    catch(Exception ex) {
		    	System.err.println("Thread error: run(): " + ex);
		    }
	    }
	
		public void runStuff(){
			int count_camera = 0;
			int count_contacts = 0;
			int count_clock = 0;
			int count_messages = 0;
			int count_phone = 0;
			int count_gallery = 0;
			int count_home = 0;
			
			try {				
				String home = System.getProperty("user.home");
				// TODO: switch Mac/Windows
				// Windows version:
				Runtime.getRuntime().exec(home + "/android-sdk/platform-tools/adb logcat -c");
				Process p = Runtime.getRuntime().exec(home + "/android-sdk/platform-tools/adb logcat"
						+ " ActivityManager:I" // update cmp/act/cat codes - see below for details
						+ " AlarmMainActivity:D" // alarm panel selected
						+ " AlarmListView:D" // new alarm created
						+ " TextFieldsEditorView:D" // contact edited
						+ " ContactEditorFragment:D" // contact saved
						+ " ComposerPerformance:D" // new message created
						+ " Mms/BottomPanel:I" // message typed
						+ " Mms/WorkingMessage:D" // message sent
						+ " DialpadFragment:I" // phone number dialed
						+ " Telecom:I" // call placed
						+ " Camera5:V" // camera
						+ " Gallery_Performance:I" // view gallery image
						+ " PhotoView:D" // show image menues
						+ " *:S");
						// TODO: gallery, camera
				// Mac version:
//				Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat -c");
//				Process p = Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat ActivityManager:I AlarmProvider:D ComposerPerformance:D *:S");

				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				
				while ((line = br.readLine()) != null) {
					
					if (line.indexOf("D AlarmMainActivity")==31) {
						/* SET ALARM PANE ACTIVE */
						if (line.indexOf("onResume")==52) {														
							instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
							instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
							instructionSingleton.highlight("clock", "contact");
							instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
						}
						// D/AlarmMainActivity(10704): createNewAlarm()
						if (line.indexOf("createNewAlarm()")==52) {
							// 12-06 17:47:05.793 10704 10704 D AlarmMainActivity: createNewAlarm()
							instructionSingleton.getActiveView().getInstructionBox(3).instruction.setDone(true);
							instructionSingleton.getActiveView().getInstructionBox(3).box.setBorder(InstructionBox.borderIfDone);
							instructionSingleton.highlight("nothing", "contact");
						}
					} else if (line.indexOf("D AlarmListView")==31) {
						/* ADD NEW ALARM */
						if (line.indexOf("resizeLayoutWithDrag : 2")==48) {														
							instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
							instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
							instructionSingleton.highlight("nothing", "contact");
							instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
						}
					} else if (line.indexOf("afterPhoneNumberFormattingTextWatcher")==55) {
						/* CONTACT INFO ENTERED */
						instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
					} else if (line.indexOf("contact save button clicked")==56) {
						/* CONTACT SAVED */
						instructionSingleton.getActiveView().getInstructionBox(3).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(3).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
					} else if (line.indexOf("D ComposerPerformance")==31) {
						/* NEW MESSAGE */
						instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
					} else if (line.indexOf("android.widget.EditText")==69) {
						/* COMPOSE MESSAGE */
						instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
					} else if (line.indexOf("[SEND]")==53) {
						/* SEND MESSAGE */
						instructionSingleton.getActiveView().getInstructionBox(3).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(3).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
					} else if (line.indexOf("I DialpadFragment: currNumber")==31) {
						/* DIAL NUMBER */
						instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
					} else if (line.indexOf("I Telecom : : perf - placeCall")==31) {
						/* CALL NUMBER */
						instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
					} else if (line.indexOf("I Gallery_Performance: DetailViewState")==31) {
						/* VIEW IMAGE */
						instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
					} else if (line.indexOf("D PhotoView")==31) {
						/* SHOW IMAGE MENUES */
						instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
						instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
						instructionSingleton.highlight("nothing", "contact");
						instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
					} else if (line.indexOf("V Camera5")==31) {
						if (line.indexOf("initQuickViewTouch")==43) {
							/* FOCUS CAMERA */
							instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
							instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
							instructionSingleton.highlight("nothing", "contact");
							instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
						}
						if (line.indexOf("handleShutterKeyReleased")==43) {
							/* TAKE PICTURE */
							instructionSingleton.getActiveView().getInstructionBox(2).instruction.setDone(true);
							instructionSingleton.getActiveView().getInstructionBox(2).box.setBorder(InstructionBox.borderIfDone);
							instructionSingleton.highlight("nothing", "contact");
							instructionSingleton.getActiveView().getInstructionBox(3).setBoxActive(true, false);
						}
					} else if (line.indexOf("I ActivityManager")==31) {
						/* update cmp/cat/act codes */
						setInfo(line);
	
						if (getCmp()!=null) {	
							//LAUNCHER
							if (getCmp().equals(CMP_LAUNCH_HOME)) {
								System.out.println("HOME ACTIVE " + ++count_home + " TIMES ");
								
								instructionSingleton.highlight("nothing", "nothing");
								
								//reset learn/do panel
								if(instructionSingleton.getActiveView() != null){
									for(int i=0 ; i<=instructionSingleton.getMaxID() ; i++){
										//instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(false, false);
										instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(true, true);
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().validate();
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().repaint();
									}
								}
							}
							
							/*
							 * ------------------   CONTACT   -------------------
							 */
							//LAUNCH CONTACTS
							else if (getCmp().equals(CMP_LAUNCH_CONTACTS)){
								System.out.println("CONTACTS ACTIVE " + ++count_contacts + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(1).setBoxActive(true, false);
							}
							
							//Click on new contact
							else if (getCmp().equals(CMP_NEW_CONTACT)){
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.updateLowerPanel("menu", false);
								instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);
							}
							
							/*
							 * ------------------   CAMERA   -------------------
							 */
							//LAUNCH CAMERA
							else if (getCmp().equals(CMP_LAUNCH_CAMERA)){
								System.out.println("CAMERA ACTIVE " + ++count_camera + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(1).setBoxActive(true, false);
							}
							
							/*
							 * ------------------   MESSAGE   -------------------
							 */
							//LAUNCH MESSAGES
							else if (getCmp().equals(CMP_LAUNCH_MESSAGES)){
								System.out.println("MESSAGES ACTIVE " + ++count_messages + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(1).setBoxActive(true, false);
							}
							
							/*
							 * ------------------   PHONE   -------------------
							 */
							//LAUNCH PHONE
							else if (getCmp().equals(CMP_LAUNCH_PHONE)){
								System.out.println("PHONE ACTIVE " + ++count_phone + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(1).setBoxActive(true, false);
							}
							
							/*
							 * ------------------   GALLERY   -------------------
							 */
							//LAUNCH GALLERY
							else if (getCmp().equals(CMP_LAUNCH_GALLERY)){
								System.out.println("GALLERY ACTIVE " + ++count_gallery + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(1).setBoxActive(true, false);
							}
							
							/*
							 * ------------------   CLOCK   -------------------
							 */
														
							//LAUNCH CLOCK
							else if (getCmp().equals(CMP_LAUNCH_CLOCK)) {
								System.out.println("CLOCK ACTIVE " + ++count_clock + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(0).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.getActiveView().getInstructionBox(1).box.setBorder(InstructionBox.borderIfDone);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.getActiveView().getInstructionBox(2).setBoxActive(true, false);

							}
						}
					}
				}
			} 
			catch (IOException e) 
			{
				System.err.println(e);
				e.printStackTrace();
			}
	
		}
	
		private int getAlarmInfo(String line) {
			// return length of alarm logcat line
			// counts the spaces, use this to determine what type of alarm command it is
			// also works for messages
			String[] temp = null;
			temp = line.split(" ");
			return temp.length;
		}

		private static void setInfo(String input){
			String[] temp = null;
			String catTemp;
			temp = input.split(" ");
			for (int i=0 ; i<temp.length ; i++)
			{
				if(temp[i].indexOf("act")==1)
				{
					setAct(temp[i].split("=")[1]);
				}
				else if(temp[i].indexOf("cat")==0)
				{
					catTemp = temp[i].split("=")[1];
					setCat(catTemp.substring(1, catTemp.length()-1));
				}
				else if(temp[i].indexOf("cmp")==0)
				{
					setCmp(temp[i].split("=")[1]);
				}
				else
				{
					continue;
				}
			}
		}
	
		public static void setAct(String act) {
			StateThread.act = act;
		}
	
		public String getAct() {
			return act;
		}
	
		public static void setCat(String cat) {
			StateThread.cat = cat;
		}
	
		public String getCat() {
			return cat;
		}
	
		public static void setCmp(String cmp) {
			StateThread.cmp = cmp;
		}
	
		public String getCmp() {
			return cmp;
		}
	}
