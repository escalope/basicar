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

import android.os.RemoteException;
import es.ucm.look.data.EntityData;

/**
 * General interface for classes adding or changing data to the application
 * 
 * @author Ángel Serrano
 * 
 */
public interface DataSetter {

	/**
	 * Adds an entity to the world
	 * 
	 * @param data
	 * @throws RemoteException 
	 */
	public void addEntity(EntityData data);

	/**
	 * Modifies the position for an entity
	 * 
	 * @param data
	 *            data representing the entity
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 */
	public void updatePosition(EntityData data, float x, float y, float z);

	/**
	 * Updates the value from a entity data property
	 * 
	 * @param data
	 *            entity data
	 * @param property
	 *            the property name
	 * @param newValue
	 *            the nuew value for the property
	 * @throws RemoteException 
	 */
	public void updateProperty(EntityData data, String property, String newValue);
}
