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
package es.ucm.look.locationProvider;

import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import es.ucm.look.ar.util.DeviceOrientation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Adapter of the INS to the Look! framework
 * 
 * @author Jorge Creixell Rojo
 * Based on Indoor Navigation System for Handheld Devices
 * by Manh Hung V. Le, Dimitris Saragas, Nathan Webb
 * 
 */
public class LocationProvider implements SensorEventListener, Runnable {

	/**
	 * Application context.
	 */
	private static Context CONTEXT;

	/**
	 * Android Sensor Manager.
	 */
	private SensorManager realSensorManager;
	private SensorManagerSimulator simulatedSensorManager;

	private org.openintents.sensorsimulator.hardware.SensorEventListener sevl;

	/**
	 * Contructor. Initializes the INS and registers listeners.
	 * 
	 * @param context
	 *            Application context.
	 */
	public LocationProvider(Context context) {
		LocationProvider.CONTEXT = context;

		Positioning.initialize();
		if (!DeviceOrientation.isRealSensorsAvailable()){
			simulatedSensorManager = 	DeviceOrientation.getSimulatedSensorManager(context);
			

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
					processEvent(arg0.type,arg0.values);						
				}

			};

	
			simulatedSensorManager.registerListener(sevl, 
					simulatedSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					SensorManager.SENSOR_DELAY_GAME);
			simulatedSensorManager.registerListener(sevl, 
					simulatedSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
					SensorManager.SENSOR_DELAY_GAME);
			simulatedSensorManager.registerListener(sevl, 
					simulatedSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 
					SensorManager.SENSOR_DELAY_GAME);
		} else {
			realSensorManager = 	DeviceOrientation.getSensorManager(context);
			realSensorManager.registerListener(this,	realSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					SensorManager.SENSOR_DELAY_FASTEST);
			realSensorManager.registerListener(this,	realSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
					SensorManager.SENSOR_DELAY_FASTEST);
			realSensorManager.registerListener(this,	realSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 
					SensorManager.SENSOR_DELAY_FASTEST);
		}


		Positioning.startPositioning();
		Positioning.resetINS();

	}

	/**
	 * Add new motion sample to the queue
	 * 
	 * @return current context.
	 */
	public static Context getContext() {
		return CONTEXT;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}


	private void processEvent(int type,float[] data){
		if (type == Sensor.TYPE_ACCELEROMETER) {
			DeviceSensor.setDevA(data);
			Positioning.updateINS();

		} else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
			DeviceSensor.setDevM(data);
			DeviceSensor.toEarthCS();
		} else if (type == Sensor.TYPE_ORIENTATION) {
			DeviceSensor.setDevO(data);
		}
	}

	public void stop(){
		if (!DeviceOrientation.isRealSensorsAvailable()){
			DeviceOrientation.getSimulatedSensorManager(null).unregisterListener(sevl);
			if (DeviceOrientation.getSimulatedSensorManager(null).isConnectedSimulator())
				DeviceOrientation.getSimulatedSensorManager(null).disconnectSimulator();
		} else {
			DeviceOrientation.getSensorManager(null).unregisterListener(this);
		}

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		processEvent(event.sensor.getType(),Util.copyArray(event.values));
	}

	/**
	 * Recalculates distance moved
	 */
	@Override
	public void run() {
		Positioning.process();

	}

	public void resetINS() {
		Positioning.resetINS();
	}

	/**
	 * Returns position in map coordinates.
	 * 
	 * @return Position in map coordinates
	 */
	public static float[] getMapPosition() {
		return Positioning.mapPosition();
	}

	/**
	 * Returns raw position
	 * 
	 * @return Position in world coordinates
	 */
	public static float[] getPosition() {
		return Positioning.position();
	}

	/**
	 * Returns relative displacement.
	 * 
	 * @return relative displacement
	 */

	public static float[] getDisplacement() {
		return Positioning.displacement();
	}

	/**
	 * Returns whether the device is moving or not.
	 * 
	 * @return whether the device is moving or not
	 */
	public static boolean isMoving() {
		return Positioning.isMoving();
	}

}
