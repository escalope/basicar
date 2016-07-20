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
package es.ucm.look.locationProvider;

import android.app.Activity;
import android.os.Bundle;

public class test extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LocationProvider locationProvider = new LocationProvider(this);
        locationProvider.run();  // ESTO ACTUALIZA LA INFORMACION
        System.out.println("test: " + LocationProvider.getPosition()[0] + " " + LocationProvider.getPosition()[1]);
        System.out.println("test: " + LocationProvider.getDisplacement()[0] + " " + LocationProvider.getDisplacement()[1]);
        System.out.println("test: " + LocationProvider.getMapPosition()[0] + " " + LocationProvider.getMapPosition()[1]);
        System.out.println("test: " + LocationProvider.isMoving());


        
    }
}
