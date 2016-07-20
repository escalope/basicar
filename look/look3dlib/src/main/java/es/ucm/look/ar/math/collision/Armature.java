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

import es.ucm.look.ar.math.geom.Point3;
import es.ucm.look.ar.math.geom.Ray;

/**
 * An armature is a container for 3D objects. It will be used for collision
 * tests. Armature are usually less complicated than the actual 3D object. They
 * are usually cubes or spheres, to simplify calculations.
 * 
 * @author Ángel Serrano
 * 
 */
public interface Armature {

	/**
	 * Returns <b>true</b> if the given point is contained by the armature.
	 * <b>false</b> otherwise
	 * 
	 * @param p
	 *            the point
	 * @return <b>true</b> if the given point is contained by the armature.
	 *         <b>false</b> otherwise
	 */
	public boolean contains(Point3 p);

	/**
	 * Test whether a ray intersects with the armature. If it does, returns the
	 * intersection point. If it doesn't, returns <b>null</b>
	 * 
	 * @param r
	 *            the ray
	 * @return the intersection point. <b>null</b> if there is no intersection
	 */
	public Point3 getIntersectionPoint(Ray r);

	/**
	 * Return if the given ray intersects with the armature
	 * 
	 * @param r
	 *            the ray
	 * @return <b>true</b> if there is intersection. <b>false</b> otherwise
	 */
	public boolean intersects(Ray r);

}
