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
package es.ucm.look.data;

import android.graphics.Color;
import es.ucm.look.ar.ar2D.drawables.Text2D;
import es.ucm.look.ar.ar3D.core.Color4;
import es.ucm.look.ar.ar3D.core.drawables.Entity3D;
import es.ucm.look.ar.ar3D.core.drawables.Mesh3D;
import es.ucm.look.ar.ar3D.core.drawables.primitives.Cube;

/**
 * A general world entity factory. Creates world entities from type and
 * properties. Must be extended for add functionality
 * 
 * @author Ángel Serrano
 * 
 */
public class WorldEntityFactory {
	
	protected Cube cube = new Cube();

	/**
	 * Creates a world entity for the given data
	 * 
	 * @param data
	 *            the data
	 */
	public WorldEntity createWorldEntity(EntityData data) {
		WorldEntity w = new WorldEntity(data);
		w.setDrawable2D(new Text2D( "Entiy " + data.getId() + "; Type: " + data.getType()));		
		w.setDrawable3D(new Cube());
		return w;
	}
	
	public WorldEntity createWorldEntity(EntityData data, int color, float size) {
		WorldEntity w = new WorldEntity(data);
		w.setDrawable2D(new Text2D( "Entiy " + data.getId() + "; Type: " + data.getType()));
		Mesh3D m3d=new Cube();
		Entity3D e3d=new Entity3D(m3d);
		e3d.setLighted(true);
		e3d.setMaterial(new Color4(Color.red(color),Color.green(color),Color.blue(color)));
		w.setDrawable3D(e3d);
		return w;
	}

}
