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
package es.ucm.look.locationProviderWifi.wifi;

/**
 * Stores information of a defined node.
 * 
 * @author Jorge Creixell Rojo
 * 
 */
public class Lugar {
	/**
	 * floor.
	 */
	private int planta;
	
	/**
	 * X coordinate.
	 */
	private int x;
	
	/**
	 * Y coordinate.
	 */
	private int y;
	
	/**
	 * Name of the node.
	 */
	private String nombre;
	
	/**
	 * Parameterized contructor
	 * @param planta
	 *      floor
	 * @param x
	 *      X coordinate
	 * @param y
	 *      Y coordinate
	 * @param nombre
	 *      Node name
	 */
	public Lugar(int planta, int x, int y, String nombre) {
		super();
		this.planta = planta;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
	}
	
	/**
	 * Default constructor.
	 */
	public Lugar() {
		super();
		this.planta = 0;
		this.x = 0;
		this.y = 0;
		this.nombre = "-";
	}
	
	/**
	 * Returns the floor 
	 * 
	 * @return floor
	 */
	public int getPlanta() {
		return planta;
	}
	
	/**
	 * Returns the X coordinate
	 * 
	 * @return X coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the Y coordinate
	 * 
	 * @return Y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the name 
	 * 
	 * @return name
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Returns the node as a string
	 * 
	 * @return string representing the node information
	 */
	public String toString() {
		return new String(nombre + ": " + planta + " " + x + " " + y);
	}

}
