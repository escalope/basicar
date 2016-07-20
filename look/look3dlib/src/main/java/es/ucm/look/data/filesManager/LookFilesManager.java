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
package es.ucm.look.data.filesManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import es.ucm.look.data.remote.ConfigNet;

/**
 * A file manager for accessing binary files remotely
 * 
 * @author Sergio
 *
 */
public class LookFilesManager {

	private static LookFilesManager instance;

	/**
	 * Base directory
	 */
	private File directory;
	
	private String route;

	private LookFilesManager() {
		route = Environment
		.getExternalStorageDirectory() + "/Look/";
		File f = new File(route);
		f.mkdirs();
		this.directory = f;
	}

	/**
	 * Method singleton to access a unique instance of LookFilesManager
	 * @return
	 */
	public static LookFilesManager getFileManager() {
		if (instance == null)
			instance = new LookFilesManager();
		return instance;
	}

	

	/**
	 * Returns an input stream for a file
	 * 
	 * @param uriFile
	 *            uri file
	 * @return the input stream
	 */
	public InputStream getInputStream(String uriFile) {
		File f = new File(directory, uriFile);
		try {
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the bitmap for the given file
	 * 
	 * @param uriFile the uri file
	 * @return the bitmap
	 */
	public Bitmap getImageBitmap(String uriFile) {
		Bitmap bm = null;

		File isFile = new File(directory, uriFile);
		if (isFile.exists()) {

			bm = BitmapFactory.decodeFile(isFile.getAbsolutePath());
		} else if ( ConfigNet.getInstance().isConnected() ){

			try {
				URL aURL = new URL(ConfigNet.getInstance().getFilesURL(uriFile));
				URLConnection conn = aURL.openConnection();
				conn.connect();
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				bm = BitmapFactory.decodeStream(bis);

				// Save the image in the sd
				FileOutputStream out = new FileOutputStream(new File(route, uriFile ));

				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				is.close();

				bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.flush();
				out.close();

				bis.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}
}
