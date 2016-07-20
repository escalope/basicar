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
package es.ucm.look.locationProviderWifi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * File write utilities.
 * 
 * @author Jorge Creixell Rojo
 * Based on Indoor Navigation System for Handheld Devices
 * by Manh Hung V. Le, Dimitris Saragas, Nathan Webb
 * 
 */
public class DeviceWriter {
	private String path;
	private String fileName;
	private FileOutputStream fos;
	private OutputStreamWriter osw;

	/*
	 * Constructor with default value;
	 */
	public DeviceWriter() {
		path = new String("/sdcard/");
		fileName = new String("samples.txt");
		initialize();
	}

	/*
	 * Constructor with a specified input
	 */
	public DeviceWriter(String s) {
		if (s.contains("/")) {
			this.path = s.substring(0, s.lastIndexOf("/") + 1);
			this.fileName = s.substring(s.lastIndexOf("/") + 1);
		} else {
			this.path = "/sdcard/";
			this.fileName = s;
		}
		initialize();
	}

	/*
	 * Write a string to the specified file
	 */
	public void write(String s) {
		try {
			this.osw.write(s);
			this.osw.flush();
		} catch (IOException e) {
		}
	}

	public void close() {
		try {
			this.osw.flush();
			this.osw.close();
			this.fos.close();
		} catch (IOException e) {
		}
	}

	public String toString() {
		return new String(this.path + this.fileName);

	}

	/*
	 * Initialize the output stream in order to write to the specified file.
	 */
	private void initialize() {
		File f = new File(new String(this.path + this.fileName));
		try {
			this.fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
		}
		this.osw = new OutputStreamWriter(this.fos);
	}

	/*
	 * ::::::::::::::::::::::::::::::::::::
	 * 
	 * get & set methods ::::::::::::::::::::::::::::::::::::
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return this.path;
	}

	public String getFileName() {
		return this.fileName;
	}
}
