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
package es.ucm.look.data.interfaces;

import java.util.Date;
import java.util.List;

import es.ucm.look.data.EntityData;

/**
 * Implemented by the classes which provides data to the application
 * 
 * @author Ángel Serrano
 * 
 */
public interface DataGetter {

	/**
	 * Returns all elements near the given point with the given radius that
	 * changed since last update
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @param radius
	 *            radius to be checked. If radius is -1, is considered as
	 *            infinitum
	 * @param date
	 *            time of the last update. If date is null, all elements will be
	 *            returned
	 * @return the list with the ids
	 */
	public List<EntityData> getElementsUpdated(float x, float y, float z,
			float radius, Date date);

}
