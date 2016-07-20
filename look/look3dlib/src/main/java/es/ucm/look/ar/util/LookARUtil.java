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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import es.ucm.look.ar.LookVisual;

/**
 * A class with static common functions
 * 
 * @author Ángel Serrano
 * 
 */
public class LookARUtil {

	/**
	 * Default camera angle
	 */
	public static final float CAMERA_VIEW_ANGLE = (float) (Math.PI / 3);

	private static LookVisual lookAr;

	/**
	 * Init the LookARUtil
	 * 
	 * @param lookAr
	 *            Application
	 */
	public static void init(LookVisual lookAr) {
		LookARUtil.lookAr = lookAr;
	}

	/**
	 * Returns the main activity
	 * 
	 * @return the main activity
	 */
	public static LookVisual getApp() {
		return lookAr;
	}

	/**
	 * Returns the display for the app
	 * 
	 * @return the display for the app
	 */
	public static Display getDisplay() {
		return lookAr.getWindowManager().getDefaultDisplay();
	}

	/**
	 * Make a FloatBuffer from an array of floats
	 * 
	 * @param f
	 *            The array
	 * @return the FloatBuffer
	 */
	public static FloatBuffer makeFloatBuffer(float[] f) {
		ByteBuffer bytBuffer = ByteBuffer.allocateDirect(f.length * 4);
		bytBuffer.order(ByteOrder.nativeOrder());

		FloatBuffer floatBuffer = bytBuffer.asFloatBuffer();
		floatBuffer.put(f);
		floatBuffer.position(0);

		return floatBuffer;
	}

	public static View getView(int resourceId, ViewGroup v) {
		return lookAr.getLayoutInflater().inflate(resourceId, v);
	}

	/**
	 * Returns the minimum between the width and the height of the screen
	 * 
	 * @return the minimum between the width and the height of the screen
	 */
	public static int getMinimumSize() {
		if (getDisplay().getHeight() > getDisplay().getWidth()) {
			return getDisplay().getWidth();
		} else
			return getDisplay().getHeight();
	}

}
