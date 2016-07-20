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
package es.ucm.look.data;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import android.os.RemoteException;
import android.util.Log;

import es.ucm.look.ar.math.geom.Point3;
import es.ucm.look.ar.util.LookARUtil;
import es.ucm.look.data.filesManager.LookFilesManager;
import es.ucm.look.data.interfaces.DataHandler;
import es.ucm.look.data.local.BasicDataHandler;
import es.ucm.look.location.LocationManager;

/**
 * A class holding all the information required across all activities
 * 
 * 
 */
public class LookData {

	private static LookData instance = null;

	/**
	 * Entity's container
	 */
	private World world;

	/**
	 * Location provider
	 */
	private LocationManager location;

	/**
	 * Data getter
	 */
	private DataHandler dataHandler;

	/**
	 * World entity factory
	 */
	private WorldEntityFactory factory = new WorldEntityFactory();

	private Timer timerLocation;

	public Point3 locationOffset = new Point3(0, 0, 0);

	/**
	 * Device location
	 */
	private Point3 deviceLocation = new Point3(0, 0, 0);

	/**
	 * Date of last update
	 */
	private Date lastUpdate = new Date(1900, 1, 1);

	/**
	 * Maximum distance for an element to be added into the world
	 */
	private float distance = -1;

	private LookData() {
		// can call to setdatahandler(..)
		dataHandler = new BasicDataHandler();

		this.setWorld(new World());

		// Create the main directory "look" in the sd for save the files
		// filesManager = new LookFilesManager();
		// File dir = new File(ConfigNet.routeSD);
		// dir.mkdirs();
	}

	public static LookData getInstance() {
		if (instance == null)
			createInstance();
		return instance;
	}

	private synchronized static void createInstance() {
		if (instance == null) {
			instance = new LookData();
		}
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public void setWorldEntityFactory(WorldEntityFactory factory) {
		this.factory = factory;
	}

	/**
	 * Sets the maximum distance for an element to be added into the world
	 * 
	 * @param distance
	 *            the distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * Updates the data for the world
	 * 
	 * @throws RemoteException
	 */
	public void updateData() {
		if (dataHandler != null && factory != null) {
			for (EntityData data : dataHandler.getElementsUpdated(
					deviceLocation.x, deviceLocation.y, deviceLocation.z,
					distance, lastUpdate)) {
				WorldEntity w = factory.createWorldEntity(data);
				if (w != null)
					world.addEntity(w);
			}
		}

		// Sets last update to now
		lastUpdate = new Date();
	}

	public void setWorld(World w) {
		this.world = w;
		if (LookARUtil.getApp() != null)
			LookARUtil.getApp().setWorld(w);
	}

	/**
	 * Starts the location provider
	 * 
	 * @param time
	 *            time between updats
	 * @param inertial
	 *            if the inertial system must be used
	 * @param wifi
	 *            if the wifi system must be used
	 */
	public void startLocation(int time, boolean inertial, boolean wifi) {
		location = new LocationManager(LookARUtil.getApp(), inertial, wifi);
		location.start();

		timerLocation = new Timer();
		timerLocation.schedule(new TimerTask() {

			private float factor = 5f;

			@Override
			public void run() {
				if (location != null && world != null) {
					//synchronized (world.getLocation()) {
						float x = locationOffset.x - location.getPosition().x
								* factor;
						float y = locationOffset.z;// - location.getPosition().z * factor;
						float z =  locationOffset.y + location.getPosition().y
						* factor;
					

						world.getLocation().set(x, y, z);
						Log.d("position","x:"+world.getLocation().x+" y:"+world.getLocation().y+" z:"+world.getLocation().z);
						EntityData entityData = new EntityData();
						entityData.setId(1);						
						dataHandler.updatePosition(entityData, x, y, z);
				//	}
				}

			}

		}, 0, time);
	}

	/**
	 * Stops location
	 */
	public void stopLocation() {
		if (timerLocation != null)
			timerLocation.cancel();

		if (location != null) {
			location.stop();
			location = null;
		}
	}

	/**
	 * Returns the current location
	 * 
	 * @return the current location
	 */
	public Point3 getLocation() {
		return world.getLocation();
	}

	public World getWorld() {
		return world;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

/*	public ServiceManager getServiceManager() {
		return ((RemoteDBHandler) dataHandler).getServiceManager();
	}*/

	public LookFilesManager getFilesManager() {
		return LookFilesManager.getFileManager();
	}

	/*public Logger getLogger() {
		if (dataHandler instanceof RemoteDBHandler) {
			return ((RemoteDBHandler) dataHandler).getLogger();
		}
		return null;
	}*/

	public String getPropertyValue(int id, String propertyName) {
		return world.getWorldEntity(id).getData()
				.getPropertyValue(propertyName);
	}

	public Map<String, String> getAllProperties(int id) {
		return world.getWorldEntity(id).getData().getProperties();
	}

}
