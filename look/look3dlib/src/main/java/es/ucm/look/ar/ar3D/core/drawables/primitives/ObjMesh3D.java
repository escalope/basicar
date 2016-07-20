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
package es.ucm.look.ar.ar3D.core.drawables.primitives;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import es.ucm.look.ar.ar3D.core.drawables.Mesh3D;
import es.ucm.look.ar.ar3D.parser.MeshObjParser;
import es.ucm.look.ar.math.collision.SphericalArmature;

public class ObjMesh3D extends Mesh3D {
	private FloatBuffer vertexBuffer;
	private FloatBuffer normalBuffer;
	private FloatBuffer textureBuffer;
	private int numVertices;

	public ObjMesh3D(Context c, int resourceId) {
		create( c.getResources().openRawResource(resourceId) );
	}
	
	public ObjMesh3D(InputStream inputStream){
		create(inputStream);
	}
	
	public void create( InputStream inputStream ){
		MeshObjParser parser = new MeshObjParser();
		if (parser.parse(inputStream)) {

			float vertices[] = parser.getVertices();

			ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
			byteBuf.order(ByteOrder.nativeOrder());
			vertexBuffer = byteBuf.asFloatBuffer();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);

			numVertices = vertices.length / 3;

			float normals[] = parser.getNormals();

			ByteBuffer byteBuf2 = ByteBuffer.allocateDirect(normals.length * 4);
			byteBuf2.order(ByteOrder.nativeOrder());
			normalBuffer = byteBuf2.asFloatBuffer();
			normalBuffer.put(normals);
			normalBuffer.position(0);

			float texture[] = parser.getTextureCoords();

			ByteBuffer byteBuf4 = ByteBuffer.allocateDirect(texture.length * 4);
			byteBuf4.order(ByteOrder.nativeOrder());
			textureBuffer = byteBuf4.asFloatBuffer();
			textureBuffer.put(texture);
			textureBuffer.position(0);

			armature = new SphericalArmature(parser.getCenter(),
					parser.getRadius());
		}
	}

	public void draw(GL10 gl) {

		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);

		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, numVertices);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

}
