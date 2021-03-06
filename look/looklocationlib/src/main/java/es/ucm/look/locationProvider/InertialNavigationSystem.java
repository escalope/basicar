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

import java.util.LinkedList;

/**
 * Calculates the relative distance
 * 
 * @author Jorge Creixell Rojo
 * Based on Indoor Navigation System for Handheld Devices
 * by Manh Hung V. Le, Dimitris Saragas, Nathan Webb
 * 
 */
public class InertialNavigationSystem {
	public static final int MAX_SIZE = 1000;
	public static final float HUMAN_SPEED_WALK = 5000.0f / 3600.0f; // 5km/3600s

	private LinkedList<Motion> motions;

	public InertialNavigationSystem() {
		motions = new LinkedList<Motion>();
	}

	public void reset() {
		if (!motions.isEmpty())
			motions.clear();
		long time = System.currentTimeMillis();
		motions.add(new Motion(time));
	}

	/**
	 * Add new motion sample to the queue
	 * 
	 * @param a
	 *            acceleration
	 * @param t
	 *            time
	 */
	public void addMotion() {
		// If the queue is full, remove the first element
		if (motions.size() == MAX_SIZE) {
			motions.removeFirst();
		}
		long time = System.currentTimeMillis();
		float dt = (time - motions.getLast().time) / 1000.0f;
		float[] d = Util.copyArray(motions.getLast().distance);
		Motion current = new Motion(time);
		if (DeviceSensor.isMoving()) {
			// moved distance
			float distance = HUMAN_SPEED_WALK * dt;
			float heading = (float) Math.toRadians(DeviceSensor.getHeading());
			current.distance[0] = d[0] + distance * (float) Math.sin(heading);
			current.distance[1] = d[1] + distance * (float) Math.cos(heading);
		} else {
			current.distance[0] = d[0];
			current.distance[1] = d[1];
		}
		// add the new motion element to the end of the queue
		motions.addLast(current);
	}

	public float[] displacement() {
		float[] d = Util.copyArray(motions.getLast().distance);
		return d;
	}
}
