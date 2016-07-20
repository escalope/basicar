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
package es.ucm.look.ar.hud;

import es.ucm.look.ar.ar3D.core.drawables.primitives.TrianglePrimitive;
import es.ucm.look.ar.math.geom.Point3;

public class Button extends TrianglePrimitive {

	private float x, y, width, height;
	
	private ActionListener actionListener;
	
	private boolean pressed;

	public Button(float x, float y, float width, float height) {
		super(new Point3(x, y + height, 0.0f), new Point3(x + width, y + height, 0.0f), new Point3(x + width / 2, y, 0.0f));
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		pressed = false;
	}

	public boolean contains(float touchX, float touchY) {
		return x < touchX && touchX < x + width && y < touchY && touchY < y + height;
	}
	
	public void setPressed( boolean p ){
		this.pressed = p;
	}
	
	public boolean isPressed(){
		return pressed;
	}
	
	public void actionPerformed( ){
		actionListener.actionPerformed(this);
	}
	
	public void setActionListener( ActionListener listener ){
		this.actionListener = listener;
	}
}
