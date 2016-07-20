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
package es.ucm.look.data.remote;

/**
 * Here are defined the Constants for the Callbacks and the commons fields of databases
 * 
 * @author Sergio
 * 
 */
public class LookProperties {

	//--------------- actions to recognize the callback-----------
	
	/**
	 * Action name for when we get logged
	 */
	public static final int ACTION_LOGIN = 0;

	/**
	 * Action name for when an element is added
	 */
	public static final int ACTION_ADD_ELEMENT = 1;
	
	/**
	 * Action name for when the local entities are updated
	 */
	public static final int UPDATE_DB = 2;
	
	/**
	 * Action name for when a property is modified
	 */
	public static final int ACTION_MODIFY_PROPERTY = 3;
	

	public static final int ACTION_GET_PROPERTIES_USER = 4;
	
	//--------------- fields commons in the database---------------
	
	// COMMON FIELDS
	public static final String FIELD_ID = "id";
	
	// MAIN TABLE
	public static final String FIELD_POS_X = "pos_x";
	public static final String FIELD_POS_Y = "pos_y";
	public static final String FIELD_POS_Z = "pos_z";
	public static final String FIELD_LAST_UPDATE = "last_update";
	
	// PROPERTY TABLE
	public static final String FIELD_PROPERTY = "property";
	public static final String FIELD_VALUE = "value";

	//COMMON PROPERTIES
	public static final String PROPERTY_TYPE = "type";






}
