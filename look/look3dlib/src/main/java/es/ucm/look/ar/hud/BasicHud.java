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

import javax.microedition.khronos.opengles.GL10;

public class BasicHud implements HUD {

	public Button b;

	public BasicHud(int width, int height) {
		float buttonWidth = width / 5;
		float buttonHeight = height / 5;
		b = new Button(width - buttonWidth - 5, height - buttonHeight - 5, buttonWidth, buttonHeight);
	}

	@Override
	public void draw(GL10 gl, float width, float height ) {
		b.draw(gl);

	}

	@Override
	public boolean touch(float x, float y, int type) {
		if ( b.contains(x, y) ){
			b.actionPerformed();
			return true;
		}
		return false;
		
	}

}
