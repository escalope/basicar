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
package es.ucm.look.ar.math.collision;

import es.ucm.look.ar.math.geom.Plane;
import es.ucm.look.ar.math.geom.Point3;
import es.ucm.look.ar.math.geom.Ray;
import es.ucm.look.ar.math.geom.Vector3;

/**
 * A Spherical Armature. It's created from a point (sphere's center) and a
 * radius (sphere's radius)
 * 
 * @author Ángel Serrano
 * 
 */
public class SphericalArmature implements Armature {

	private Point3 center;
	private float radius;

	/**
	 * Constructs a spherical armature from its center and its radius
	 * 
	 * @param center
	 *            Center point
	 * @param radius
	 *            Sphere radius
	 */
	public SphericalArmature(Point3 center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean contains(Point3 p) {
		if (p != null) {
			float distance = Vector3.getVolatileVector(p, center).module();
			return distance <= radius;
		}
		return false;
	}

	@Override
	public Point3 getIntersectionPoint(Ray r) {
		float t = Plane.getVolatilePlane(center, r.getVector()).intersects(r);
		if (t >= 0) {
			Point3 p = r.getPoint(t);
			if (this.contains(p)) {
				return p;
			} else
				return null;
		} else
			return null;
	}

	@Override
	public boolean intersects(Ray r) {
		Point3 p = this.getIntersectionPoint(r);
		return this.contains(p);

	}

	public float getRadius() {
		return radius;
	}

}
