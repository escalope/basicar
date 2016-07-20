/*******************************************************************************
 * Look! is a Framework of Augmented Reality for Android. 
 * 
 * Copyright (C) 2011 
 * 		Sergio Bellón Alcarazo
 * 		Jorge Creixell Rojo
 * 		Ángel Serrano Laguna
 * 	
 * 	   Final Year Project developed to Sistemas Informáticos 2010/2011 - Facultad de Informática - Universidad Complutense de Madrid - Spain
 * 	
 * 	   Project led by: Jorge J. Gómez Sánz
 * 
 * 
 * ****************************************************************************
 * 
 * This file is part of Look! (http://lookar.sf.net/)
 * 
 * Look! is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program.  If not, see http://www.gnu.org/licenses/
 ******************************************************************************/
package es.ucm.look.ar.util;

import org.openintents.sensorsimulator.db.SensorSimulator;
import org.openintents.sensorsimulator.db.SensorSimulatorConvenience;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

/**
 * A class holding device's orientation
 * 
 * @author Ángel Serrano
 * 
 */
public class DeviceOrientation implements 
SensorEventListener {

	private static DeviceOrientation instance;

	private static SensorManagerSimulator mSM;

	private static SensorManager realSM;


	private float[] inR = new float[16];
	private float[] outR = new float[16];
	private float[] I = new float[16];
	private float[] gravity = new float[3];
	private float[] geomag = new float[3];
	private float[] orientVals = new float[3];

	/**
	 * Device azimuth ( y coordinate )
	 */
	private float azimuth;

	private float azimuthOffset = 0.0f;

	/**
	 * Device pitch ( x coordinate )
	 */
	private float pitch;

	private float pitchOffset = 0.0f;

	/**
	 * Device roll ( z coordinate )
	 */
	private float roll;

	private float rollOffset = 0.0f;

	private org.openintents.sensorsimulator.hardware.SensorEventListener sevl;

	public float getAzimuth() {
		return azimuth;
	}

	public float getPitch() {
		return pitch;
	}

	public float getRoll() {
		return roll;
	}

	private DeviceOrientation(Context context) {


	}

	/**
	 * Method that checks for the 1.5 SDK Emulator bug...
	 * From SensorManagerSimulator code
	 * 
	 * @return boolean true or false
	 */
	public static boolean isRealSensorsAvailable() {		
		if (Build.MODEL.contains("sdk") || Build.MODEL.contains("SDK") || Build.MODEL.contains("emulator") || Build.MODEL.contains("Emulator")) {
			// We are on Emulator
			return false;
		}
		return true;
	}
	
	public static synchronized SensorManagerSimulator getSimulatedSensorManager(Context context){
		if (mSM==null) {
			mSM = 	SensorManagerSimulator.getSystemService(
					context, Context.SENSOR_SERVICE);
			if (!mSM.isConnectedSimulator())
				mSM.connectSimulator();
		}
		return mSM;
	}
	
	public static synchronized SensorManager getSensorManager(Context context){
		if (realSM==null)
			realSM=(SensorManager) context.getSystemService( Context.SENSOR_SERVICE);	
		return realSM;
	}

	public void start(Context context){
		/*mSensorSimulatorConvenience=new SensorSimulatorConvenience(context);

		mSensorSimulatorConvenience.setPreference(
				SensorSimulator.KEY_IPADDRESS, "10.0.2.2");
		mSensorSimulatorConvenience.setPreference(
				SensorSimulator.KEY_SOCKET, "8010");*/

		if (!isRealSensorsAvailable()){
			mSM = getSimulatedSensorManager(context);

			mSM.connectSimulator();

			sevl=
				new org.openintents.sensorsimulator.hardware.SensorEventListener(){

				@Override
				public void onAccuracyChanged(
						org.openintents.sensorsimulator.hardware.Sensor arg0,
						int arg1) {

				}

				@Override
				public void onSensorChanged(
						org.openintents.sensorsimulator.hardware.SensorEvent arg0) {					
					processEvent(arg0.accuracy,arg0.type,arg0.values);						
				}

			};

			//	mSM = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
			mSM.registerListener(sevl, 
					mSM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					SensorManager.SENSOR_DELAY_GAME);
			mSM.registerListener(sevl, 
					mSM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
					SensorManager.SENSOR_DELAY_GAME);
		} else {
			realSM = 	getSensorManager(context);
			realSM.registerListener(this,	realSM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					SensorManager.SENSOR_DELAY_GAME);
			realSM.registerListener(this,	realSM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
					SensorManager.SENSOR_DELAY_GAME);
		}

	}

	public void stop(){
		if (!isRealSensorsAvailable()){
			getSimulatedSensorManager(null).unregisterListener(sevl);
			if (getSimulatedSensorManager(null).isConnectedSimulator())
			 getSimulatedSensorManager(null).disconnectSimulator();
		} else {
			getSensorManager(null).unregisterListener(this);
		}

	}
	public static DeviceOrientation getDeviceOrientation(Context c) {
		if (instance == null)
			instance = new DeviceOrientation(c);
		return instance;
	}



	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	/**
	 * Returns the matrix rotation for the current device orientation
	 * 
	 * @return the matrix rotation for the current device orientation
	 */
	public float[] getRotationMatrix() {
		return outR;
	}

	public void onSensorChanged(SensorEvent event) {
		processEvent(event.accuracy,event.sensor.getType(),event.values);	

	}

	private void processEvent(int accuracy, int type, float[] values) {

		/*		if (accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
			return;*/

		/*if (azimuth!=0 || pitch!=0 || roll!=0){
			Log.d("lookar","a:"+azimuth+" p:"+pitch+" r:"+roll);
		}*/

		switch (type) {
		case Sensor.TYPE_ACCELEROMETER:
			gravity = values.clone();
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			geomag = values.clone();
			break;
		}

		if (gravity != null && geomag != null) {

			boolean success = SensorManager.getRotationMatrix(inR, I, gravity, geomag);
			if (success) {

				SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
				SensorManager.getOrientation(outR, orientVals);

				this.azimuth = orientVals[0] + azimuthOffset;
				this.pitch = orientVals[1] + pitchOffset;
				this.roll = orientVals[2] + rollOffset;


			}
		}

	}

	public void setAzimuthOffset(float azimuthOffset) {
		this.azimuthOffset = azimuthOffset;
	}

	public void setPitchOffset(float pitchOffset) {
		this.pitchOffset = pitchOffset;
	}

	public void setRollOffset(float rollOffset) {
		this.rollOffset = rollOffset;
	}


}
