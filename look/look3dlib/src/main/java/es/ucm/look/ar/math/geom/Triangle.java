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
 * Represents a geometric triangle
 * 
 * @author Ángel Serrano
 * 
 */
public class Triangle {

	private Point3 p1, p2, p3;
	
	private Vector3 normal;

	/**
	 * Constructs a triangle from 3 points
	 * 
	 * @param p1
	 *            Point 1
	 * @param p2
	 *            Point 2
	 * @param p3
	 *            Point 3
	 */
	public Triangle(Point3 p1, Point3 p2, Point3 p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.normal = Vector3.normalVector(p1, p2, p3);
	}

	/**
	 * Returns the plain that contains this triangle
	 * 
	 * @return the plain that contains this triangle
	 */
	public Plane getPlane() {
		return new Plane(p1, p2, p3);
	}

	/**
	 * Returns if the given point is contained in this triangle.
	 * 
	 * @param p
	 *            the point. It must be contained in the same plane as the
	 *            triangle
	 * @return <b>true</b> if the point is contained by the triangle,
	 *         <b>false</b> otherwise
	 */
	public boolean contains(Point3 p) {
		int max = normal.getGreatestComponent();
		Point2 p1 = new Point2(this.p1, max);
		Point2 p2 = new Point2(this.p2, max);
		Point2 p3 = new Point2(this.p3, max);
		Point2 i = new Point2(p, max);

		return (checkSide(p1, p2, i) == checkSide(p2, p3, i)) && (checkSide(p1, p2, i) == checkSide(p3, p1, i));
	}

	private int checkSide(Point2 o, Point2 d, Point2 p) {
		return (int) Math.signum(-(d.y - o.y) * (p.x - o.x) + (d.x - o.x) * (p.y - o.y));
	}

	/**
	 * Returns a 3 elements array with the points
	 * 
	 * @return a 3 elements array with the points
	 */
	public Point3[] getPoints() {
		return new Point3[] { p1, p2, p3 };
	}

}
