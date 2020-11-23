package com.ar.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UConexion {
	private static UConexion uConexion;
	private Connection conn;
	
	public static UConexion getInstance() {

		//If para que sea solo 1 instancia
		if(uConexion == null) {
			uConexion = new UConexion();	
		}

		return uConexion;
	}

	public Connection abrirConexion() {
		if(this.conn == null) {
			try {
				Class.forName(Propiedades.getProperty("com.ar.Driver").toString());
				String pathConexion = Propiedades.getProperty("com.ar.ubicaciondb").toString();
		
				this.conn = DriverManager.getConnection(pathConexion, Propiedades.getProperty("com.ar.user"), Propiedades.getProperty("com.ar.password"));
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return this.conn;
	}
	
	public void cerrarConexion() {
		if(this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
