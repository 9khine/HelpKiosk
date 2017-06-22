# HelpKiosk

## Introduction
--- from Demo --- 

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
