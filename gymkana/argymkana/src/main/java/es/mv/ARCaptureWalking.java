package es.mv;

import java.util.Timer;

import es.ucm.look.ar.*;
import es.ucm.look.ar.ar3D.core.drawables.Entity3D;
import es.ucm.look.ar.ar3D.core.drawables.primitives.Cube;
import es.ucm.look.ar.ar3D.core.drawables.primitives.ObjMesh3D;
import es.ucm.look.ar.listeners.TouchListener;
import es.ucm.look.ar.math.geom.Point3;
import es.ucm.look.ar.util.LookARUtil;
import es.ucm.look.data.EntityData;
import es.ucm.look.data.LookData;
import es.ucm.look.data.World;
import es.ucm.look.data.WorldEntity;
import es.ucm.look.data.WorldEntityFactory;
import es.ucm.look.location.LocationManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.content.Intent;
import android.graphics.*;

import java.util.*;


public class ARCaptureWalking extends LookVisual {

	int score=0;
	private long secondsToFinish=20;
	private android.os.CountDownTimer timer=null;
	private LocationManager lm=null; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		World world=new World();
		WorldEntityFactory wef=new WorldEntityFactory();
		EntityData ed=new EntityData(1, 
				"example", 10f, 10f, 2f);	
		WorldEntity we = wef.createWorldEntity(ed);
		EntityData ed2=new EntityData(2, 
					"example", 12f, 10f, 2f);	
			WorldEntity we2 = wef.createWorldEntity(ed2);
			we2.setDrawable3D(new Cube());
		Intent intent=getIntent();
		String mode=intent.getStringExtra("CAPTURE_MODE");
		if (mode.equalsIgnoreCase("CUBE")){
			we.setDrawable3D(new Cube());	
		} else {
			ObjMesh3D object=new ObjMesh3D(LookARUtil.getApp(), R.raw.ufo);;
			we.setDrawable3D(new Entity3D(object));
		}
	
		world.addEntity(we);
		world.addEntity(we2);
		we.setTouchable(true);
		we.addTouchListener(new TouchListener() {
			@Override
			public boolean onTouchUp(WorldEntity e, float x, float y) {
				Intent intentResult=new Intent();
				intentResult.putExtra("CAPTUREDID",""+e.getId());
				intentResult.putExtra("MESSAGE","capturado a las "+Calendar.getInstance(TimeZone.getDefault()).getTime());
				setResult(RESULT_OK,intentResult);
				timer.cancel();
				finish();
				return false;
			}

			@Override
			public boolean onTouchMove(WorldEntity e, float x, float y) {
				return false;
			}

			@Override
			public boolean onTouchDown(WorldEntity e, float x, float y) {
				Intent intentResult=new Intent();
				intentResult.putExtra("CAPTUREDID",""+e.getId());
				intentResult.putExtra("MESSAGE","capturado a las "+Calendar.getInstance(TimeZone.getDefault()).getTime());
				setResult(RESULT_OK,intentResult);
				timer.cancel();
				finish();
				return false;
			}
		});

		timer=new  	android.os.CountDownTimer (secondsToFinish*1000, 1000) {

			public void onTick(long millisUntilFinished) {
				secondsToFinish= millisUntilFinished / 1000;

			}

			public void onFinish() {

				Intent intentResult=new Intent();
				intentResult.putExtra("MESSAGE","Fracaso a las "+ Calendar.getInstance(TimeZone.getDefault()).getTime());
				setResult(RESULT_CANCELED,intentResult);

				finish();
			}
		}.start();

		this.setWorld(world);
		
		
		FrameLayout container = getContainerView();
		container.addView(new View(this){

			@Override
			public void draw(Canvas canvas) {
				// TODO Auto-generated method stub
				super.draw(canvas);
				Paint p=new Paint();
				p.setColor(Color.WHITE);

				p.setColor(Color.WHITE);
				p.setTextSize(18); // set font size
				Typeface currentTypeFace =   p.getTypeface();
				Typeface bold = Typeface.create(currentTypeFace, Typeface.BOLD);
				p.setTypeface(bold);
				canvas.drawText("seconds remaining: " + secondsToFinish ,0f,40f,p);						
				//Log.i("prueba","escribiendo "+renderer.getCamera().look.toString());					
			}			
		});
		LookData.getInstance().startLocation(1000,true,false);


	}
	
	



	public ARCaptureWalking(){
		super(false, true, false, true, 50.0f, false);
		

	}




}
