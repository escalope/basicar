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
 * Represents a geometric ray, compound of a {@link Point3} and a
 * {@link Vector3}
 * 
 * @author Ángel Serrano
 * 
 */
public class Ray {

	private Point3 point;
	private Vector3 vector;

	/**
	 * Constructs a ray from a point and a vector
	 * 
	 * @param p
	 *            the point
	 * @param v
	 *            the vector
	 */
	public Ray(Point3 p, Vector3 v) {
		this.point = p;
		this.vector = v;
	}

	/**
	 * Returns the point in the ray that corresponds to the given t parameter
	 * 
	 * @param t
	 *            t parameter
	 * @return the corresponding point
	 */
	public Point3 getPoint(float t) {
		Point3 p = new Point3(t * vector.x, t * vector.y, t * vector.z);
		p.add(point);
		return p;
	}

	/**
	 * Returns the starting point for this ray
	 * 
	 * @return the starting point for this ray
	 */
	public Point3 getPoint() {
		return point;
	}

	/**
	 * Returns the vector defining the ray
	 * 
	 * @return the vector defining the ray
	 */
	public Vector3 getVector() {
		return vector;
	}
	
	public void setVector(float x, float y, float z){
		this.vector.set(x, y, z);
	}
	
	private static Ray r = new Ray( new Point3( 0, 0, 0 ), new Vector3( 0, 0, 0 ));
	
	public static Ray getVolatileRay( Point3 p, Vector3 v ){
		r.point = p;
		r.vector = v;
		return r;
	}

}
