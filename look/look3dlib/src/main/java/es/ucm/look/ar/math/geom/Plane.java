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
 * Represents a geometric plane
 * 
 * @author Ángel Serrano
 * 
 */
public class Plane {
	private Point3 point;
	private Vector3 normal;
	private float d;

	/**
	 * Constructs a plane from a point and its normal
	 * 
	 * @param p
	 *            Point contained by the plane
	 * @param n
	 *            Normal vector for the plane
	 */
	public Plane(Point3 p, Vector3 n) {
		this.point = p;
		this.normal = n;
		d = -(point.x * n.x + point.y * n.y + point.z * n.z);
	}

	/**
	 * Constructs a plane from three points
	 * 
	 * @param p1
	 *            Point 1
	 * @param p2
	 *            Point 2
	 * @param p3
	 *            Point 3
	 */
	public Plane(Point3 p1, Point3 p2, Point3 p3) {
		this(p1, Vector3.normalVector(p1, p2, p3));
	}

	/**
	 * Returns the plane's normal vector
	 * 
	 * @return the plane's normal vector
	 */
	public Vector3 getNormal() {
		return normal;
	}

	/**
	 * Returns the t parameter for the intersection with the given ray. If plane
	 * and ray has no intersection returns <b>-1</b> representing infinite.
	 * Value returned must be used with {@link Ray#getPoint(float)}
	 * 
	 * @param ray
	 *            Ray to be checked
	 * @return the t parameter for the ray in the intersection point. If plane
	 *         and ray has no intersection returns <b>-1</b>
	 */
	public float intersects(Ray ray) {
		float dotProduct = ray.getVector().dotProduct(normal);
		if (Math.abs(dotProduct) > 0.01f) {
			Point3 p = ray.getPoint();
			float t = -(p.x * normal.x + p.y * normal.y + p.z * normal.z + d) / dotProduct;
			return t;
		} else
			return -1;
	}

	private static Plane volatilePlane = new Plane(new Point3(0, 0, 0), new Vector3(0, 0, 0));

	/**
	 * Returns a volatile plane from a point and its normal
	 * 
	 * @param p
	 *            Point contained by the plane
	 * @param n
	 *            Normal vector for the plane
	 */
	public static Plane getVolatilePlane(Point3 point, Vector3 normal) {
		volatilePlane.point = point;
		volatilePlane.normal = normal;
		volatilePlane.d = -(point.x * normal.x + point.y * normal.y + point.z * normal.z);
		return volatilePlane;
	}
}
