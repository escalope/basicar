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
package es.ucm.look.locationProvider.map;


/**
 * Maps INS coordinates to MAP coordinates.
 * 
 * @author Jorge Creixell Rojo
 * Based on Indoor Navigation System for Handheld Devices
 * by Manh Hung V. Le, Dimitris Saragas, Nathan Webb
 * 
 */
public class Mapa {
	/**
	 * Scale factor
	 */
	public static float SCALE = 0.033264f; // 1 pixel = 0.1 meter
	
	/**
	 * Orientation factor.
	 */
	public static float NORTH = -0.277777777777f; //-0.221992086112412f;

	/**
	 * Initialize all the variables
	 */
	public static void initialize() {

	}

	/**
	 * Convert to map coordination system
	 * 
	 * Return the distance move in pixel in the frame system of the current
	 * building with specified offset angle
	 * 
	 * @param north
	 *            the distance toward north in meter
	 * @param east
	 *            the distance toward east in meter
	 * @return the distance in x and y axis in pixel
	 */
	public static float[] toMapCS(float[] csEarth) {

		float[] csBuilding = new float[2]; // building coordinate system
		float[] csMap = new float[2]; // map coordinate system
		float a = -Mapa.NORTH;

		csBuilding[0] = (float) (Math.cos(a) * csEarth[0] + Math.sin(a)
				* csEarth[1]);
		csBuilding[1] = (float) (-Math.sin(a) * csEarth[1] + Math.cos(a)
				* csEarth[1]);

		csMap[0] = -csBuilding[0];
		csMap[1] = csBuilding[1];
		return csMap;
	}

	/**
	 * Convert the vector from meter to pixels
	 * 
	 * @param meter
	 *            The vector in meter
	 * @return The vector in pixel
	 */
	public static int[] toMapScale(float[] meter) {
		return toMapScale(meter[0], meter[1]);
	}

	/**
	 * Convert the vector from meter to pixels
	 * 
	 * @param x
	 *            the x coordination point right in meter
	 * @param y
	 *            the y coordination point bottom in meter
	 * @return the vector in pixel
	 */
	public static int[] toMapScale(float x, float y) {
		int[] pixel = new int[2];
		pixel[0] = (int) (x / Mapa.SCALE);
		pixel[1] = (int) (y / Mapa.SCALE);
		return pixel;
	}
	
}
