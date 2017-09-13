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
- Launching Eclipse through terminal to connect directly to adb 
(Set run configuration - class = HelpKiosk.java, 
Go to terminal:
- Go to eclipse/Contents/MacOX/
- enter and run ./eclipse 
- Eclipse should launch,
Try running the application again)

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

(Change the build path first to avoid errors everywhere, makes it a little smoother)

To make the code runnable on a Windows machine, the build path needs to be configured:

1. Add external JARs from the local lib directory:
  - ddmlib-25.3.2.jar
  - ddms-25.3.2.jar
  - ddmuilib-25.3.2.jar
  - hierarchyviewer2lib-25.3.2.jar
  - guava-19.0.jar
2. Link the org.eclipse.swt project (go to the Projects tab in the Configure Build Path window)

Also,  there are 4 lines of code that need to be commented out/uncommented:

1. Two lines of code in StateThread.java where adb is invoked (the path to adb needs to be changed)
2. Two lines of code in Workspace.java: (1) where initDebugBridge() is called (the Windows Android SDK has a different signature for that method) and (2) at the top in the import statements
