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

import java.util.HashMap;
import java.util.Map;

import es.ucm.look.ar.ar3D.core.drawables.primitives.ObjMesh3D;
import es.ucm.look.ar.util.LookARUtil;

public class DrawablesDataBase {
	
	private Map<Integer, ObjMesh3D> drawables;
	
	private static DrawablesDataBase instance;
	
	private DrawablesDataBase( ){
		drawables = new HashMap<Integer, ObjMesh3D>();
	}
	
	public static DrawablesDataBase getInstance( ){
		if ( instance == null ){
			instance = new DrawablesDataBase( );
		}
		
		return instance;
	}
	
	public void putDrawable( Integer id, ObjMesh3D d ){
		drawables.put(id, d);
	}
	
	public ObjMesh3D getDrawable3D( Integer id ){
		if ( !drawables.containsKey(id)){
			drawables.put(id, new ObjMesh3D( LookARUtil.getApp(), id));
		}
		return drawables.get(id);
	}
	

}
