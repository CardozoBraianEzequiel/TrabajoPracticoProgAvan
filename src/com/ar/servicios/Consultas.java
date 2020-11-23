package com.ar.servicios;

import java.beans.Statement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ar.anotaciones.Columna;
import com.ar.anotaciones.Id;
import com.ar.anotaciones.Tabla;
import com.ar.utilidades.UBean;
import com.ar.utilidades.UConexion;

public class Consultas {
	
	public static Object guardar(Object o) {
		String consulta = "insert into ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		
		consulta = consulta + nombreTabla + " (";
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {
			consulta = consulta + attr.getAnnotation(Columna.class).nombre() + ",";
		}
		
		consulta = consulta.substring(0, consulta.length()-1);
		
		consulta = consulta += ") values (";
		
		for(Field a:attrs) {
			Object getteado = UBean.ejecutarGet(o, a.getName());
			if(getteado.getClass().getTypeName().equalsIgnoreCase(String.class.getName())) {
				consulta = consulta + "'" + UBean.ejecutarGet(o, a.getName()) + "',";
			}else {
				consulta = consulta + UBean.ejecutarGet(o, a.getName())+ ",";
			}
		}
		
		consulta = consulta.substring(0, consulta.length()-1);
		consulta = consulta += ")";
		
		System.out.println(consulta);
		int idObjetoInsertado = 0;
		
		try {
			UConexion uConn = UConexion.getInstance();
			Connection conn = uConn.abrirConexion();
			PreparedStatement ps;
		
			ps = conn.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				   int claveGenerada = rs.getInt(1);
				   System.out.println("Clave generada = " + claveGenerada);
				}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object retorno = null;
		for(Constructor cons:o.getClass().getConstructors()) {
			if(cons.getParameterCount() == 0) {
				try {
					retorno = cons.newInstance(null);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		
		ArrayList<Field> fields = UBean.obtenerAtributos(retorno);
		
		for (int i = 0; i < fields.size(); i++) {
			  Id idTabla = fields.get(i).getAnnotation(Id.class);
			  Columna columna = fields.get(i).getAnnotation(Columna.class);
			  if( idTabla!=null ) {
				  UBean.ejecutarSet(retorno, fields.get(i).getName(), UBean.ejecutarGet(o, fields.get(i).getName()) );
			  }		
		 }
		
		
		return Consultas.obtenerPorId(o.getClass(), retorno);
	}
	
	public static void modificar(Object o) {
		String consulta = "update ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		String idTabla = "";
		consulta = consulta + nombreTabla + " set ";
		
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {

			if(attr.isAnnotationPresent(Id.class)) {
				idTabla = UBean.ejecutarGet(o, attr.getName()).toString();
			} else {
				consulta += attr.getAnnotation(Columna.class).nombre() + "=";
				consulta += "'" + UBean.ejecutarGet(o, attr.getName())+ "' ";	
			}
		}
		
		consulta += "where id=" + idTabla;
		
		System.out.println(consulta);
		
		try {
			UConexion uConn = UConexion.getInstance();
			Connection conn = uConn.abrirConexion();
			PreparedStatement ps;
		
			ps = conn.prepareStatement(consulta);
			
			ResultSet res = ps.executeQuery();
			
			uConn.cerrarConexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void eliminar(Object o) {
		String consulta = "delete from ";
		String nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		String idTabla = "";
		
		consulta = consulta + nombreTabla + " where id=";
		
		ArrayList<Field> attrs = UBean.obtenerAtributos(o);
		
		for(Field attr: attrs) {

			if(attr.isAnnotationPresent(Id.class)) {
				idTabla = UBean.ejecutarGet(o, attr.getName()).toString();
			} 
		}
		
		consulta = consulta + idTabla;
		
		System.out.println(consulta);
		
		try {
			UConexion uConn = UConexion.getInstance();
			Connection conn = uConn.abrirConexion();
			PreparedStatement ps;
		
			ps = conn.prepareStatement(consulta);
			
			ResultSet res = ps.executeQuery();
			
			uConn.cerrarConexion();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Object obtenerPorId(Class c, Object id) {
		String consulta = "select * from ";
		String nombreTabla = ((Tabla)c.getAnnotation(Tabla.class)).nombre();
		String idTabla = "";
		String nombreCampoId = "";
		consulta = consulta + nombreTabla + " where ";
		Object retorno = null;
		
		ArrayList<Field> attrs = UBean.obtenerAtributos(id);
		
		for(Field attr: attrs) {

			if(attr.isAnnotationPresent(Id.class)) {
				idTabla = UBean.ejecutarGet(id, attr.getName()).toString();
				nombreCampoId = attr.getAnnotation(Columna.class).nombre();
			} 
		}
		
		consulta = consulta + nombreCampoId + "=" + idTabla;
		try {
			System.out.println(consulta);
			UConexion uConn = UConexion.getInstance();
			Connection conn = uConn.abrirConexion();
			PreparedStatement ps = conn.prepareStatement(consulta);
		
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				
				for(Constructor cons:c.getConstructors()) {
					if(cons.getParameterCount() == 0) {
						retorno = cons.newInstance(null);
						break;
					}
				}
				
				ArrayList<Field> fields = UBean.obtenerAtributos(retorno);
				
				for (int i = 0; i < fields.size(); i++) {
					  Columna columna = fields.get(i).getAnnotation(Columna.class);
					  if( columna!=null ) {
						  UBean.ejecutarSet(retorno, fields.get(i).getName(), res.getObject(columna.nombre(), fields.get(i).getType()) );
					  }		
				 }
				
				uConn.cerrarConexion();
				return retorno;
			}
			
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
		
	}
	
}
