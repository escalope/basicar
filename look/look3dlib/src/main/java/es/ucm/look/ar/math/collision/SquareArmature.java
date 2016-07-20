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
import es.ucm.look.ar.math.geom.Triangle;

public class SquareArmature implements Armature {

	private Triangle t1, t2;

	private Plane p;

	/**
	 * Constructs a square armature from 4 points. This 4 points must be
	 * contained for the same plane. If not, weird behavior will happen
	 * 
	 * @param topLeft
	 * @param bottomLeft
	 * @param bottomRight
	 * @param topRight
	 */
	public SquareArmature(Point3 topLeft, Point3 bottomLeft, Point3 bottomRight, Point3 topRight) {
		t1 = new Triangle(topLeft, bottomLeft, bottomRight);
		t2 = new Triangle(topLeft, topRight, bottomRight);
		p = t1.getPlane();
	} 

	@Override
	public boolean contains(Point3 p) {
		return t1.contains(p) || t2.contains(p);
	}

	@Override
	public Point3 getIntersectionPoint(Ray r) {
		float t = p.intersects(r);
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
		return getIntersectionPoint(r) != null;
	}

}
