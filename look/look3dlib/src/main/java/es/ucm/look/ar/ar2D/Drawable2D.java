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
package es.ucm.look.ar.ar2D;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Implemented by all those classes that can be drawn in a {@link Canvas}
 * 
 * @author Ángel Serrano
 * 
 */
public interface Drawable2D {

	/**
	 * Draws the element in the canvas
	 * 
	 * @param c
	 *            the canvas
	 */
	void draw(Canvas c);

	/**
	 * Updates the drawable
	 * 
	 * @param elapsed
	 *            elapsed time since last updated
	 */
	void update(long elapsed);

	/**
	 * Fills the touchable zone for the drawable only with the the given Paint.
	 * This method is used processing screen touches. It can be empty if
	 * drawable is not receiving touch events
	 * 
	 * @param c
	 *            the canvas
	 * @param p
	 *            the paint
	 */
	void drawTouchableArea(Canvas c, Paint p);

}
