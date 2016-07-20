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
package es.ucm.look.ar;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import es.ucm.look.ar.ar2D.AR2D;
import es.ucm.look.ar.ar3D.Renderer3D;
import es.ucm.look.ar.ar3D.core.TextureFactory;
import es.ucm.look.ar.util.DeviceOrientation;
import es.ucm.look.ar.util.LookARUtil;
import es.ucm.look.data.World;


/**
 * This activity recreates a first person OpenGL world.
 * 
 * @author Ángel Serrano Laguna
 * 
 */
public class LookVisual extends Activity implements SurfaceHolder.Callback {

	/**
	 * If app is using two dimension displaying
	 */
	private boolean uses2D;

	/**
	 * If app is using three dimension displaying
	 */
	private boolean uses3D;

	/**
	 * If app is using the camera as background
	 */
	private boolean usesCamera;

	/**
	 * If app is using a HUD with Anroid's views
	 */
	private boolean usesHud;

	/**
	 * If activity is full screen
	 */
	private boolean fullScreen;

	/**
	 * GLSurfaceView holding all the 3D painting
	 */
	protected GLSurfaceView glSurface;

	/**
	 * Renderer for the GLSurfaceView
	 */
	protected Renderer3D renderer;

	/**
	 * HUD container to add view over the first person world
	 */
	private ViewGroup hudContainer;

	/**
	 * Preview holding the camera view
	 */
	private Preview preview;

	/**
	 * View holding all 2D painting
	 */
	private AR2D view2D;

	/**
	 * World containing all the data
	 */
	private World world;

	/**
	 * Max distance to be shown
	 */
	private float maxDist;

	private FrameLayout container;

	/**
	 * Constructs an Look Augmented Reality frame with the given parameters
	 * 
	 * @param usesCamera
	 *            If app is using the camera as background
	 * @param uses3D
	 *            If app is using three dimension displaying
	 * @param uses2D
	 *            If app is using two dimension displaying
	 * @param usesHud
	 *            If app is using a HUD with Anroid's views
	 * @param maxDist
	 *            Max distance to be shown in the AR
	 * @param fullScreen
	 *            If activity is full screen
	 */
	public LookVisual(boolean usesCamera, boolean uses3D, boolean uses2D,
			boolean usesHud, float maxDist, boolean fullscreen) {
		LookARUtil.init(this);
		this.usesCamera = usesCamera;
		this.uses3D = uses3D;
		this.uses2D = uses2D;
		this.usesHud = usesHud;
		this.fullScreen = true;
		this.maxDist = maxDist;
		this.fullScreen = fullscreen;
	}

	
	
	/**
	 * Constructs an Look Augmented Reality frame with the given parameters with
	 * full screen
	 * 
	 * @param usesCamera
	 *            If app is using the camera as background
	 * @param uses3D
	 *            If app is using three dimension displaying
	 * @param uses2D
	 *            If app is using two dimension displaying
	 * @param usesHud
	 *            If app is using a HUD with Anroid's views
	 * @param maxDist
	 *            Max distance to be shown in the AR
	 * @param fullScreen
	 *            If activity is full screen
	 */
	public LookVisual(boolean usesCamera, boolean uses3D, boolean uses2D,
			boolean usesHud, float maxDist) {
		this(usesCamera, uses3D, uses2D, usesHud, maxDist, true);
	}
	
	public FrameLayout getContainerView(){
		return this.container;
	}

	/**
	 * Sets the world to be represented by this activity
	 * 
	 * @param world
	 *            the world to be represented by this activity
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Constructs a Look Augmented Reality frame with camera, 2D, 3D and HUD
	 */
	public LookVisual() {
		this(true, false, true, true, 50.0f);
	}

	/**
	 * Constructs a Look Augmented Reality frame with 2D, 3D and HUD
	 * 
	 * @param usesCamera
	 *            if the camera must be use as background
	 */
	public LookVisual(boolean usesCamera) {
		this(usesCamera, true, true, true, 50.0f);
	}

	/**
	 * Returns the world
	 * 
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//LookData.getInstance().getWorld().removeAllEntities();

		if (fullScreen)
			setFullScreen();

		container = new FrameLayout(this);

		if (usesCamera)
			addCamera(container);

		if (uses3D)
			add3D(container, maxDist); // adds the 3d elements to be placed OVER the camera view
		

		if (uses3D)
			container.addView(glSurface);

		if (usesCamera)
			container.addView(preview); // creates a camera view underneath the 3d layer
		
	//	container.addView(debugCamera);
	
		// if (uses2D)
		add2D(container, maxDist);

		if (usesHud)
			addHud(container);
		
		container.addView(new View(this){

			@Override
			public void draw(Canvas canvas) {
				// TODO Auto-generated method stub
				super.draw(canvas);

						Paint p=new Paint();
						p.setColor(Color.WHITE);
						if (renderer!=null && renderer.getCamera()!=null && renderer.getCamera().look!=null){
					 	 canvas.drawText(renderer.getCamera().look.toString(), 0,20,p );
						}
						//Log.i("prueba","escribiendo "+renderer.getCamera().look.toString());
					
			}
			
		});
				
		setContentView(container);
		Log.i("activity", "constructor");

	}

	/**
	 * Sets full screen for the activity
	 */
	private void setFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void addHud(FrameLayout container) {
		hudContainer = new FrameLayout(this);
		container.addView(hudContainer);
	}

	private void addCamera(FrameLayout container) {
		preview = new Preview(this);
		// container.addView(preview);
	}

	private void add2D(FrameLayout container, float maxDist) {
		view2D = new AR2D(world, this, maxDist);
		container.addView(view2D);
	}

	private void add3D(ViewGroup v, float maxDist) {
		renderer = new Renderer3D(world, true, maxDist);
		glSurface = new GLSurfaceView(this);
		glSurface.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		glSurface.setRenderer(renderer);
		glSurface.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		glSurface.getHolder().addCallback(this);

		// v.addView(glSurface);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (glSurface != null)
			glSurface.onResume();
		Log.i("activity", "onResume");
		DeviceOrientation.getDeviceOrientation(this).start(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (glSurface != null)
			glSurface.onPause();
		Log.i("activity", "onPause");
		if (TextureFactory.getInstance() != null)
			TextureFactory.getInstance().clear();
		
	}

	@Override
	protected void onDestroy() {	
		super.onDestroy();
	}

	/**
	 * Returns the renderer for the engine
	 * 
	 * @return the renderer for the engine
	 */
	public Renderer3D getRenderer() {
		return renderer;
	}

	@Override
	protected void onStop() {	
		super.onStop();		
		DeviceOrientation.getDeviceOrientation(this).stop();
	}



	/**
	 * Returns the HUD container. Views can be added to it
	 * 
	 * @return the HUD container
	 */
	public ViewGroup getHudContainer() {
		return hudContainer;
	}

	/**
	 * Returns the layer holding 2D
	 * 
	 * @return
	 */
	public AR2D get2DLayer() {
		return view2D;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i("activity", "gl surfaceChanged");

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i("activity", "gl surfaceCreated");

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i("activity", "gl surfaceDestroyed");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean processed = false;
		if (hudContainer != null)
			processed = hudContainer.onTouchEvent(event);
		if (!processed && view2D != null)
			processed = view2D.onTouchEvent(event);
		if (!processed && glSurface != null)
			processed = glSurface.onTouchEvent(event);
		return processed;
	}

	/**
	 * Returns the max distance object will be seen
	 * 
	 * @return
	 */
	public float getMaxDistance() {
		return maxDist;
	}

}
