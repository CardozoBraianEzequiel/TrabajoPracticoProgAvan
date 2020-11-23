package com.ar.utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	public static String getProperty(String nombreProperty) {
		Properties propiedades = new Properties();
		try {
			propiedades
			 .load(new FileInputStream(
					 System.getProperty("user.dir")+"/resources/framework.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return propiedades.getProperty(nombreProperty);
	}
	
}
