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
package es.ucm.look.ar.ar3D;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;
import es.ucm.look.ar.LookVisual;
import es.ucm.look.ar.ar3D.core.TextureFactory;
import es.ucm.look.ar.ar3D.core.camera.Camera3D;
import es.ucm.look.ar.ar3D.core.camera.OrientedCamera;
import es.ucm.look.ar.util.LookARUtil;
import es.ucm.look.data.World;

/**
 * Renderer3D is the general renderer for the GLSurfaceView
 * 
 * @author Ángel Serrano
 * 
 */
public class Renderer3D implements Renderer {

	/**
	 * Some values for
	 * {@link GLU#gluPerspective(GL10, float, float, float, float)}. We store
	 * them cause they'll be required to do some calculations
	 */
	private float fov, ratio, nearDist, farDist;

	private int fps;

	private int counter;

	private int accTime;

	private long lastTime = -1;

	private LookVisual context;

	private boolean transparent;

	private Camera3D camera;
	
	

//	private World world;

	/**
	 * Constructs a Renderer
	 * 
	 * @param transparent
	 *            if the background must be transparent
	 * @param maxDist
	 *            max distance to be shown
	 */
	public Renderer3D(World world, boolean transparent, float maxDist) {
		this.context = LookARUtil.getApp();
		this.transparent = transparent;
		this.camera = new OrientedCamera();

		fov = (float) Math.toDegrees(LookARUtil.CAMERA_VIEW_ANGLE);
		nearDist = 0.1f;
		farDist = maxDist;

		fps = 0;
		counter = 0;
		accTime = 0;
		//this.world=LookARUtil.getApp().getWorld();
	}

	/**
	 * Constructs a render with opaque background
	 * 
	 */
	public Renderer3D(World world) {
		this(world, false, 50.0f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		initLights(gl);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);

		if (transparent) {
			gl.glClearColor(0, 0, 0, 0);
		} else {
			gl.glClearColor(0, 0, 0, 1);
		}

		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		TextureFactory.init(context, gl);
		
	}

	/**
	 * Init the lights
	 * 
	 * @param gl
	 *            GL10 context
	 */
	private void initLights(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, new float[] { 0.3f,
				0.3f, 0.3f, 1.0f }, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[] { 1.0f,
				1.0f, 0.0f, 0.0f }, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT_AND_DIFFUSE, new float[] {
				1.0f, 1.0f, 1.0f, 1.0f }, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, new float[] { 1.0f,
				1.0f, 1.0f, 1.0f }, 0);
		gl.glEnable(GL10.GL_LIGHT0);

	}

	public void onDrawFrame(GL10 gl) {
		updateFPS();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		camera.setCamera(gl);
		LookARUtil.getApp().getWorld().draw( gl );


	}
	
	



	private void updateFPS() {
		long now = System.currentTimeMillis();
		counter++;
		if (lastTime != -1) {
			accTime += now - lastTime;
			if (accTime > 1000) {
				if (fps != 0)
					fps = (fps + counter) / 2;
				else
					fps = counter;

				counter = 0;
				accTime = 0;
				Log.i("fps", "FPS: " + fps);
				/*if (camera!=null)
				Log.i("lookingat", "FPS: " +camera.look);*/
			}
		}
		lastTime = now;
	}

	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if (height == 0) {
			height = 1;
		}

		ratio = (float) width / (float) height;

		gl.glViewport(0, 0, width, height);
		set3D(gl);
	}

	/**
	 * Sets 3D projection
	 * 
	 * @param gl
	 *            GL10 context
	 */
	private void set3D(GL10 gl) {
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		GLU.gluPerspective(gl, fov, ratio, nearDist, farDist);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

	}

	/**
	 * Returns the fps
	 * 
	 * @return the fps
	 */
	public int getFPS() {
		return fps;
	}

	public void setCamera(Camera3D camera) {
		this.camera = camera;
	}
	
	public Camera3D getCamera() {
		return camera;
	}

}
