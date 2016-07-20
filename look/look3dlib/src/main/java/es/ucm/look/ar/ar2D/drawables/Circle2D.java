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
package es.ucm.look.ar.ar2D.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import es.ucm.look.ar.ar2D.Drawable2D;

/**
 * A 2D drawable circle, centered at ( 0, 0 )
 * 
 * @author Ángel Serrano
 * 
 */
public class Circle2D implements Drawable2D {

	private boolean drawCircle;

	private float radius;
	
	private Paint p;
	
	public Circle2D(float radius ){
		this( radius, false, Color.BLACK );
	}

	public Circle2D(float radius, boolean drawCircle, int color ) {
		this.radius = radius;
		this.drawCircle = drawCircle;
		p = new Paint();
		p.setColor(color);
		
	}

	@Override
	public void draw(Canvas c) {
		if (drawCircle) {
			c.drawCircle(0, 0, radius, p);
		}
	}

	@Override
	public void update(long elapsed) {

	}

	@Override
	public void drawTouchableArea(Canvas c, Paint p) {
		c.drawCircle(0, 0, radius, p);
	}

}
