package com.prototype.helpkiosk.instructioncontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		
		// TODO: find new way to set alarm codes from logcat output
		public static String NEW_ALARM_CODE = "";
		// 06-21 13:24:35.594 23640 23640 D AlarmListView: resizeLayoutWithDrag : 2
		// 06-21 13:25:54.054 23640 23640 D AlarmMainActivity: onSaveAlarm()
		public static String ACTIVATE_ALARM_CODE = "";
		// 06-23 12:00:29.986 12047 12047 D AlarmProvider: setAlarmActive() - id: 4,willChangeButtonActive: true,activeNow: 0
		public static String DEACTIVATE_ALARM_CODE = "";
		// 06-21 13:14:57.024 23640 23640 D AlarmProvider: setAlarmActive to false
		
		// Alt option
		// cmp = com.sec.android.app.clockpackage/.alarm.activity.AlarmEditActivity
		
		private String alarmCode = null;
		
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
			
			try 
			{				
				String home = System.getProperty("user.home");
				// TODO: change this to work on display (we should rename the android-sdk fodler to -sdks on the Windows to solve this problem
				//Windows version:
//				Runtime.getRuntime().exec(home + "/android-sdk/platform-tools/adb logcat -c");
//				Process p = Runtime.getRuntime().exec(home + "/android-sdk/platform-tools/adb logcat");
				// Mac version:
				Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat -c");
				Process p = Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat");
				
				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
	
				String line = null;
	
				while ((line = br.readLine()) != null) 
				{
					if(line.indexOf(this.DONE_TYPE)==0) {
						instructionSingleton.highlight("nothing", "contact");
					}
					
					if(line.indexOf(this.SNAPSHOT)==0) {
						instructionSingleton.highlight("nothing", "contact");
					}
					
					if (line.indexOf("D AlarmProvider")==31) {
						// Click alarm (inside clock)
						System.out.println(line);
						int i = getAlarmInfo(line);
						if (i==12 && line.indexOf("true")==97) {
							// TODO: click on activate alarm, logcat line is: (length = 12)
							// 06-23 12:00:29.986 12047 12047 D AlarmProvider: setAlarmActive() - id: 4,willChangeButtonActive: true,activeNow: 0
							//																				this is what changes ^
//							/Users/pablo/android-sdks/platform-tools/adb logcat AlarmManager:V *:S
							
							System.out.println("ALARM CODE IS: " + i + "; inside setAlarmActive()");
							
							instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
							instructionSingleton.highlight("nothing", "contact");
							//instructionSingleton.showVideo("nothing");
						}
					} else if (line.indexOf("D ComposerPerformance")==31) {
						// TODO: check if compose is clicked, should work now probably...?
						// format of logcat line: 08-30 17:17:05.828 11610 11610 D ComposerPerformance: create new message
						System.out.println(line);
						
						System.out.println("Compose was clicked! How exciting...");
						
						instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
						instructionSingleton.highlight("nothing", "contact");
//						instructionSingleton.showVideo("nothing");
						
//						int i = getAlarmInfo(line);
//						if (line.indexOf("ComposerPerformance")==33) {
//							System.out.println("Compose was clicked! How exciting...");
//							
//							instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
//							instructionSingleton.highlight("nothing", "contact");
////							instructionSingleton.showVideo("nothing");
//						}
					} else if (line.indexOf("I ActivityManager")==31) {
//						System.out.println(line);
						setInfo(line);
	
//						System.out.println("*** act = "+ this.act);
//						System.out.println("*** cat = "+ this.cat);
//						System.out.println("*** cmp = "+ this.cmp);
						
						if (getCmp()!=null) {	
							//LAUNCHER
							if (getCmp().equals(CMP_LAUNCH_HOME)) {
								System.out.println("HOME ACTIVE " + ++count_home + " TIMES ");
								
								//reset learn/do panel
								if(instructionSingleton.getActiveView()!=null){
									for(int i=0 ; i<=instructionSingleton.getMaxID() ; i++){
										//instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(false, false);
										instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(true, true);
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().validate();
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().repaint();
									}
								}
								
							}
							
							//LAUNCH CLOCK
							else if (getCmp().equals(CMP_LAUNCH_CLOCK)) {
								System.out.println("CLOCK ACTIVE " + ++count_clock + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH CONTACTS
							else if (getCmp().equals(CMP_LAUNCH_CONTACTS)){
								System.out.println("CONTACTS ACTIVE " + ++count_contacts + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
								instructionSingleton.showVideo("openContact");
							}
							
							//LAUNCH CAMERA
							else if (getCmp().equals(CMP_LAUNCH_CAMERA)){
								System.out.println("CAMERA ACTIVE " + ++count_camera + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH MESSAGES
							else if (getCmp().equals(CMP_LAUNCH_MESSAGES)){
								System.out.println("MESSAGES ACTIVE " + ++count_messages + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH PHONE
							else if (getCmp().equals(CMP_LAUNCH_PHONE)){
								System.out.println("PHONE ACTIVE " + ++count_phone + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH GALLERY
							else if (getCmp().equals(CMP_LAUNCH_GALLERY)){
								System.out.println("GALLERY ACTIVE " + ++count_gallery + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
							}
							
							//Click on new contact
							else if (getCmp().equals(CMP_NEW_CONTACT)){
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
								instructionSingleton.updateLowerPanel("menu", false);
							}
							
							//Click on activate alarm
							else if (getCmp().equals(CMP_ACTIVATE_ALARM)){
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								//instructionSingleton.showVideo("nothing");
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
