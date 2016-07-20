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
package es.ucm.look.ar.math.geom;

/**
 * Represents a 2D point
 * 
 * @author Ángel Serrano
 * 
 */
public class Point2 {

	/**
	 * x coordinate
	 */
	public float x;

	/**
	 * y coordinate
	 */
	public float y;

	/**
	 * Constructs a point from its 2D coordinates
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Point2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a 2D point from a 3D point, giving one coordinate to be
	 * removed from the 3D point
	 * 
	 * @param p
	 *            3D point
	 * @param remove
	 *            coordinate index to be removed. If 0, x will be removed,
	 *            assigning 2D x to 3D y and 2D y to 3D z. If 1, y will be
	 *            removed, and so on... If <em>remove</em> if greater than 2 or
	 *            less than 0, the z coordinate will be removed
	 */
	public Point2(Point3 p, int remove) {
		switch (remove) {
		case 0:
			x = p.y;
			y = p.z;
			break;
		case 1:
			x = p.x;
			y = p.z;
			break;
		default:
			x = p.x;
			y = p.y;
		}
	}

	/**
	 * Adds given x and y to point x and way
	 * 
	 * @param x
	 * @param y
	 */
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Adds a point to this one, adding given point x and y coordinates to this
	 * x and y point
	 * 
	 * @param p
	 *            the point
	 */
	public void add(Point2 p) {
		this.add(p.x, p.y);
	}

	/**
	 * Returns an array with the point's coordinates
	 * 
	 * @return an array with the point's coordinates
	 */
	public float[] getCoordinatesArray() {
		return new float[] { x, y };
	}

}
