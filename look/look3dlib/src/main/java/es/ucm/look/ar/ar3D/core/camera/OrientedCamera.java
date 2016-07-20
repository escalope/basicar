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
package es.ucm.look.ar.ar3D.core.camera;

import javax.microedition.khronos.opengles.GL10;

import es.ucm.look.ar.util.DeviceOrientation;
import es.ucm.look.ar.util.LookARUtil;

public class OrientedCamera extends Camera3D {

	private DeviceOrientation orientation;

	private static final float DIFF = 0.05f;

	private float azimuth, roll, pitch;

	public OrientedCamera( ) {
		orientation = DeviceOrientation.getDeviceOrientation(LookARUtil.getApp());
	}

	public void setCamera(GL10 gl) {
		if (Math.abs(orientation.getAzimuth() - azimuth) > DIFF || 
				Math.abs(orientation.getPitch() - pitch) > DIFF || 
				Math.abs(orientation.getRoll() - roll) > DIFF) {
			azimuth = orientation.getAzimuth();
			roll = orientation.getRoll();
			pitch = orientation.getPitch();

			look.set(0.0f, 0.0f, -1.0f);
			eye.set(0.0f, 0.0f, 0.0f);
			up.set(0.0f, 1.0f, 0.0f);
			calcVectors();

			yaw(azimuth);
			pitch((float) (pitch + Math.PI ) );
		}
		super.setCamera(gl);
	}

}
