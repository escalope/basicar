An example of how to use QR files in an easy way. This code reuses parts of the ZXINGS library (https://github.com/zxing/zxing/)

The samples are the following:
- zxingscanner. A full QR recognizer. The code has been extracted from ZXINGS and adapted to this course
- basicqrscan. An example of how to integrate the QR activity developed in the ZXINGSSCanner into a regular application. It requires the APK from ZXINGSSCanner to be installed first.
- QRcreator. An utility to create custom QR codes from command line. More examples can be created from https://zxing.appspot.com/generator

REQUIREMENTS
------------

This project requires having installed:
-JDK 1.7 (At least)
-MAVEN 3.3 (At least)
-SDK Android (Image 19 for Intel loaded at least)

INSTALLING
----------

Go to the root folder of this software and run "mvn clean install". This will prepare the software for the installation either at the mobile phone or the simulated phone.

- Preparing the device
	+ If you don't have a mobile, prepare an AVD using the Android SDK. When defining the AVD, choose either the forward or the backward camera as your webcam. Start the simulator. 
	+ If you have a mobile, connect it to the computer and prepare it for the development (https://developer.xamarin.com/guides/android/getting_started/installation/set_up_device_for_development)
	+ To be sure everything works, type "adb devices". It should show either your connected mobile phone or the simulated one. 

- Install the zxing client with these steps
	+ Go to the zxingscanner folder and type "mvn package android:deploy"
	+ Go to the basicqrscan folder and type "mvn package android:deploy"

Now, the apps are compiled and ready from your phone

Running
-------
Prepare a sample QR. If you have none, try this https://zxing.appspot.com/generator or use the QR creator

To use the QR creator, go to the qrcreator folder and run

 mvn exec:java -Dexec.mainClass="qr.CrunchifyQRCode" -Dexec.args="http://www.ucm.es ucm.png"

To create a png image with filename ucm.png. Open the file or print it.

In the mobile phone,open the QRDemo app and press "Scan" when you are ready. Point at the QR and check the resulting URL in the log.

Troubleshooting
---------------

I get a "java.lang.OutOfMemoryError: GC overhead limit exceeded"
-  Add this to the .bashrc file
export MAVEN_OPTS="-Xmx1024M -Xss128M -XX:MaxPermSize=1024M -XX:+CMSClassUnloadingEnabled"


Credits
-------

Jorge J. Gómez Sanz (@jjgomezs, jjgomez@ucm.es)
