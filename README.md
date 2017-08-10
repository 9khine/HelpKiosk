# HelpKiosk

## Introduction
Older adults (age 65+) have trouble learning to use mobile phones:
1. Natural decline in their sensory, perceptual, motor and cognitive abilities
2. Problems with device user interface (UI) → Small display → effective interactive help
3. A lack of experience with computers and devices

Our work explores the use of a larger display to augment the small phone display to support older adults in the learning process
Initiated and created (in 2011) by past UBC PhD student - Rock Leung

To better understand the learning needs of older adults:
- Conducted survey with 131 respondents and analyzed a sub-sample of 94 response sets
 (Chi2011_388_submitted: Help Kiosk: Augmented displays help older adults use mobile phones)
 
The key result:
“Older adults prefer using manuals while younger adults prefer a trial and error”

Design Principles:
- Support Authentic Tasks and Self-directed learning 
- Utilize Real-Time device state to personalize experience
- Provide both generic and specific instructions
- Offer opportunities to explore and practice safely → Exploratory mode (TBD)
- Minimize demands on working memory


## Set up instruction:
1. Install Eclipse (or any other java IDE)
2. Clone project using git: https://github.com/9khine/HelpKiosk.git
3. Import Help Kiosk project into Eclipse

Previous errors and solutions:
- JMF external jar file

## Wireless ADB

In Terminal run these 2 commands:
(where ~ is the path to your android-sdks directory)

~/android-sdks/platform-tools/adb tcpip 5555

~/android-sdks/platform-tools/adb connect DEVICE_IP_ADDRESS
(to find the device ip address go into wifi settings)

to check it's working simply run:
~/android-sdks/platform-tools/adb devices
it should show a device named DEVICE_IP_ADDRESS:5555

this is from the Android ADB guide https://developer.android.com/studio/command-line/adb.html#wireless

## Changing between Windows & Mac (for display)

To make the code runnable on a Windows machine there are 4 lines of code that need to be commented out/uncommented:

1. Two lines of code in StateThread.java where adb is invoked (the path to adb needs to be changed)
2. Two lines of code in Workspace.java: (1) where initDebugBridge() is called (the Windows Android SDK has a different signature for that method) and (2) at the top in the import statements

Also, the build path needs to be updated (I think this necessary but always check, maybe don't need to remove things after doing it once)
1. Remove ddmlib.jar, ddms.jar, ddmuilib.jar, and hierarchyviewer.jar
2. Add new versions of those 4 (eg ddmlib2.25...jar) from the local lib directory
3. Add guava19.0.jar to build path (also in local lib directory)
4. Link the org.eclipse.swt project (go to add projects to build path)