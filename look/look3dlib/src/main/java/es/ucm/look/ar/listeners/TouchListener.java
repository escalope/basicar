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
package es.ucm.look.ar.listeners;

import es.ucm.look.data.WorldEntity;

/**
 * Listener for touch events
 * 
 * @author Ángel Serrano
 * 
 */
public interface TouchListener {

	/**
	 * Called on touch down event
	 * 
	 * @param e
	 *            entity touched
	 * @param x
	 *            x screen coordinate for the event
	 * @param y
	 *            y screen coordinate for the event
	 * @return if the event has been processed
	 */
	boolean onTouchDown(WorldEntity e, float x, float y);

	/**
	 * Called on touch up event
	 * 
	 * @param e
	 *            entity touched
	 * @param x
	 *            x screen coordinate for the event
	 * @param y
	 *            y screen coordinate for the event
	 * @return if the event has been processed
	 */
	boolean onTouchUp(WorldEntity e, float x, float y);

	/**
	 * Called on touch move event
	 * 
	 * @param e
	 *            entity touched
	 * @param x
	 *            x screen coordinate for the event
	 * @param y
	 *            y screen coordinate for the event
	 * @return if the event has been processed
	 */
	boolean onTouchMove(WorldEntity e, float x, float y);

}
