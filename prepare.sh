set -e
mvn clean install
cd look/sensorsimulatorsettings
mvn android:deploy
cd ../../qr/zxingscanner
mvn android:deploy
cd ../../look/sensorsimulator
mvn exec:java -Dexec.mainClass=org.openintents.tools.simulator.main.SensorSimulatorMain
