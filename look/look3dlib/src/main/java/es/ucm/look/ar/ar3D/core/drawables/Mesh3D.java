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
package es.ucm.look.ar.ar3D.core.drawables;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import es.ucm.look.ar.ar3D.Drawable3D;
import es.ucm.look.ar.math.collision.Armature;

/**
 * Represents an 3D object that can be drawn.
 * 
 * @author Ángel Serrano
 * 
 */
public abstract class Mesh3D implements Drawable3D {

	protected FloatBuffer vertexBuffer;
	protected FloatBuffer normalBuffer;

	protected Armature armature;

	/**
	 * Constructs an entity
	 * 
	 * @param id
	 *            Entity id
	 */
	public Mesh3D() {
		
	}
	
	public void setVertexBuffer( FloatBuffer vertexBuffer ){
		this.vertexBuffer = vertexBuffer;
	}
	
	public void setNormalBuffer( FloatBuffer normalBuffer ){
		this.normalBuffer = normalBuffer;
	}

	/**
	 * Draw the entity into the {@link GL10} context
	 * 
	 * @param gl
	 *            {@link GL10} context
	 */
	public void draw(GL10 gl) {
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);

		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertexBuffer.capacity() / 3);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

	}

	/**
	 * Updates entity for the elapsed time
	 * 
	 * @param elapsed
	 *            elapsed time since last update
	 */
	public void update(long elapsed) {

	}

	/**
	 * Returns an {@link Armature} in the local system coordiantes of this
	 * entity
	 * 
	 * @return an {@link Armature} in the local system coordiantes of this
	 *         entity
	 */
	public Armature getArmarture() {
		return armature;
	}

}
