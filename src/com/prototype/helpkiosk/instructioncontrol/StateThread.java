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
		
		public String DONE_TYPE = "W/EntityModifier";
		public String SNAPSHOT = "V/camera  (  573): Start autofocus.";
		
		public static String ACT_APP_LAUNCH = "android.intent.action.MAIN";
		public static String CAT_APP_LAUNCH = "android.intent.category.LAUNCHER";
		
		public static String CMP_LAUNCH_HOME = "com.android.launcher/com.android.launcher2.Launcher";
		public static String CMP_LAUNCH_CONTACTS = "com.android.contacts/.DialtactsContactsEntryActivity";
		public static String CMP_LAUNCH_CAMERA = "com.google.android.camera/com.android.camera.Camera";
		public static String CMP_LAUNCH_CLOCK = "com.google.android.deskclock/com.android.deskclock.DeskClock";
		
		/*CLOCK*/
		public static String CMP_ACTIVATE_ALARM = "com.google.android.deskclock/com.android.deskclock.AlarmClock";
		public static String CMP_SET_ALARM = "com.google.android.deskclock/com.android.deskclock.SetAlarm";
		
		/*CONTACTS*/
		public static String CMP_NEW_CONTACT = "com.android.contacts/.ui.EditContactActivity";
		

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
				Runtime.getRuntime().exec("C:/Documents and Settings/Rock/android-sdk-windows/tools/adb logcat -c");
				Process p = Runtime.getRuntime().exec("C:/Documents and Settings/Rock/android-sdk-windows/tools/adb logcat");
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
					if(line.indexOf("I/ActivityManager")==0)
					{
						System.out.println(line);
						setInfo(line);
	
						System.out.println("*** act = "+ getAct());
						System.out.println("*** cat = "+ getCat());
						System.out.println("*** cmp = "+ getCmp());
						
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
							
							//Click on new contact
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
				if(temp[i].indexOf("act=")==0)
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
