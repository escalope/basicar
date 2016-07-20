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
package es.ucm.look.locationProviderWifi;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Example Client for the Service WifiService.
 * 
 * @author Jorge Creixell Rojo
 * 
 */
public class Cliente extends Activity {

	/**
	 * Service to bind.
	 */
	private WifiService mBoundService;
	
	/**
	 * If the service is bound.
	 */
	private boolean mIsBound = false;
	
	/**
	 * Timer to retrieve location information in regular time intervals.
	 */
	private Timer timer;
	
	/**
	 * The connection to the service.
	 */
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mBoundService = ((WifiService.ServicioBinder) service).getService();
			mBoundService.start();

			timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				public void run() {
					
					//Obtener aqui la posicion
					System.out.println("Cliente: " + mBoundService.getPosicion().toString());
				}
			};
			timer.scheduleAtFixedRate(timerTask, 0, 1000);

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBoundService = null;

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		doBindService();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
		}
		doUnbindService();
	}

	/**
	 * Bind the service
	 */
	void doBindService() {
		bindService(new Intent(Cliente.this, WifiService.class), mConnection,
				Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	/**
	 * UnBind the service.
	 */
	void doUnbindService() {
		if (mIsBound) {
			// Detach our existing connection.
			unbindService(mConnection);
			mIsBound = false;
		}
	}

}
