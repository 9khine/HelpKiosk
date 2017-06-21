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
		
		// TODO:
		// change the device strings based on the device being used
		// change the instruction strings based on the Android version you use for the prototype
		//
		// currently set for the LG phone running 4.0.2
		
		// TODO: update camera items for LG phone
		public String DONE_TYPE = "W/EntityModifier";
		public String SNAPSHOT = "V/camera  (  573): Start autofocus.";
		
		public static String ACT_APP_LAUNCH = "android.intent.action.MAIN";
		public static String CAT_APP_LAUNCH = "android.intent.category.LAUNCHER";
		public static String CAT_HOME_LAUNCH = "android.intent.category.HOME";
		
		// Device strings
		public static String CMP_LAUNCH_HOME = "com.sec.android.app.launcher/.activities.LauncherActivity";
		
		// Instruction strings
		public static String CMP_LAUNCH_CONTACTS = "com.android.contacts/.activities.PeopleActivity";
		public static String CMP_LAUNCH_CAMERA = "com.sec.android.app.camera/.Camera";
		public static String CMP_LAUNCH_CLOCK = "com.sec.android.app.clockpackage/.ClockPackage";
		
		/*CLOCK*/
		public static String CMP_ACTIVATE_ALARM = "com.google.android.deskclock/com.android.deskclock.AlarmClock";
		public static String CMP_SET_ALARM = "com.google.android.deskclock/com.android.deskclock.SetAlarm";
		
		// TODO: find new way to set alarm codes from logcat output
		public static String NEW_ALARM_CODE = "";
		// 06-21 13:24:35.594 23640 23640 D AlarmListView: resizeLayoutWithDrag : 2
		// 06-21 13:25:54.054 23640 23640 D AlarmMainActivity: onSaveAlarm()
		public static String ACTIVATE_ALARM_CODE = "";
		// 06-21 13:14:04.904 23640 23640 D AlarmProvider: setAlarmActive()
		public static String DEACTIVATE_ALARM_CODE = "";
		//06-21 13:14:57.024 23640 23640 D AlarmProvider: setAlarmActive to false
		
		/*CONTACTS*/
		public static String CMP_NEW_CONTACT = "com.android.contacts/.activities.ContactEditorActivity";
		

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
			int count_home = 0;
			
			try 
			{				
				String home = System.getProperty("user.home");
				Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat -c");
				Process p = Runtime.getRuntime().exec(home + "/android-sdks/platform-tools/adb logcat");
				
				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
	
				String line = null;
	
				while ((line = br.readLine()) != null) 
				{
					if(line.indexOf(this.DONE_TYPE)==0){
						instructionSingleton.highlight("nothing", "contact");
					}
					if(line.indexOf(this.SNAPSHOT)==0){
						instructionSingleton.highlight("nothing", "contact");
					}
					if(line.indexOf("I ActivityManager")==31)
					{
						System.out.println(line);
						setInfo(line);
	
						System.out.println("*** act = "+ this.act);
						System.out.println("*** cat = "+ this.cat);
						System.out.println("*** cmp = "+ this.cmp);
						
						if(getCmp()!=null)
						{	
							//LAUNCHER
							if (getCmp().equals(CMP_LAUNCH_HOME)){
								System.out.println("HOME ACTIVE " + ++count_home + " TIMES ");
								
								//reset learn/do panel
								if(instructionSingleton.getActiveView()!=null){
									for(int i=0 ; i<=instructionSingleton.getMaxID() ; i++){
										instructionSingleton.getActiveView().getInstructionBox(i).setBoxActive(false, false);
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().validate();
										instructionSingleton.getActiveView().getInstructionBox(i).getInstructionArea().repaint();
									}
								}
								
							}
							
							//LAUNCH CLOCK
							else if (getCmp().equals(CMP_LAUNCH_CLOCK)){
								System.out.println("CLOCK ACTIVE " + ++count_clock + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH CONTACTS
							else if (getCmp().equals(CMP_LAUNCH_CONTACTS)){
								System.out.println("CONTACTS ACTIVE " + ++count_contacts + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.showVideo("nothing");
							}
							
							//LAUNCH CAMERA
							else if (getCmp().equals(CMP_LAUNCH_CAMERA)){
								System.out.println("CAMERA ACTIVE " + ++count_camera + " TIMES ");
								instructionSingleton.getActiveView().getInstructionBox(0).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.showVideo("nothing");
							}
							
							//Click on new contact
							else if (getCmp().equals(CMP_NEW_CONTACT)){
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.showVideo("nothing");
								instructionSingleton.updateLowerPanel("menu", false);
							}
							
							//Click on activate alarm
							else if (getCmp().equals(CMP_ACTIVATE_ALARM)){
								instructionSingleton.getActiveView().getInstructionBox(1).instruction.setDone(true);
								instructionSingleton.highlight("nothing", "contact");
								instructionSingleton.showVideo("nothing");
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
