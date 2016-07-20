jALI (java Android Layer for Interaction) is a framework that allows developers to deploy Android applications in virtual worlds.
The jALI applications are connected to the virtual world as it was real.

PHAT (Physical Human Activity Tester) is an example of virtual world where Android applications developed with jALI can be tested.

This is a maven project of an example of an Android application using jALI.
This uses a [maven android plugin](https://code.google.com/p/maven-android-plugin/) and some requirements are needed.

### REQUIREMENTS:

- Java 1.7 (set variable JAVA_HOME)
- Maven 3.1.1+ installed, see http://maven.apache.org/download.html (set variable M2_HOME)
- Ant (set variable ANT_HOME)
- Android SDK (r21.1 or later, latest is best supported) installed, preferably with all platforms, see http://developer.android.com/sdk/index.html
- Make sure you have images for API 19 installed in your android SDK. It is required to have the IntelAtomx86 image to permit hardware acceleration. Instructions for Android are available in the [Android site](http://developer.android.com/tools/devices/emulator.html#acceleration)
- Set environment variable ANDROID_HOME to the path of your installed Android SDK and add $ANDROID_HOME/tools as well as $ANDROID_HOME/platform-tools to your $PATH. (or on Windows %ANDROID_HOME%\tools and %ANDROID_HOME%\platform-tools).
- Add binaries to environment variable PHAT (PATH=$PATH:$HOME/bin:$JAVA_HOME/bin:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$M2_HOME/bin)


### USAGE:
Make sure the Android SDK is available in the classpath. In particular, the following paths are needed 
*ANDROID_HOME/tools* and *ANDROID_HOME/platform-tools* are in the PATH variable.

To build your apk, simply:

- mvn clean install

To deploy your apk to the connected device:

- mvn android:deploy
