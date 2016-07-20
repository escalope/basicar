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
package es.ucm.look.ar.ar3D.core;

public class Color4 {

	public float[] rgba;
	
	private static final float FACTOR = 0.2f;

	public Color4(float r, float g, float b, float a) {
		rgba = new float[] { r, g, b, a };
		normalizeLevels();
	}

	public Color4(float r, float g, float b) {
		this(r, g, b, 1.0f);
	}
	
	public Color4( float[] rgba ) throws IllegalArgumentException {
		if ( rgba.length != 4 ){
			throw new IllegalArgumentException( "rgba array must have 4 components");
		}
		this.rgba = rgba;
		normalizeLevels();
	}
	
	private void normalizeLevels( ){
		for ( int i = 0; i < rgba.length; i++ ){
			rgba[i] = rgba[i] > 1.0f ? 1.0f : rgba[i];
			rgba[i] = rgba[i] < 0.0f ? 0.0f : rgba[i];
		}
	}
	
	public Color4 darker( ){
		return new Color4( rgba[0] - FACTOR, rgba[1] - FACTOR, rgba[2] - FACTOR, rgba[3]);
	}
	
	public Color4 brighter( ){
		return new Color4( rgba[0] + FACTOR, rgba[1] + FACTOR, rgba[2] + FACTOR, rgba[3]);
	}

}
