package com.ar.utilidades;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class UBean {
	
	public static ArrayList<Field> obtenerAtributos(Object obj){
		
		Class<? extends Object> c = obj.getClass();
		
		ArrayList<Field> arrayListFields = new ArrayList<Field>(Arrays.asList(c.getDeclaredFields()));
		
		return arrayListFields;
	}
	
	public static void ejecutarSet(Object o, String att, Object valor) {
		Object p = null;
		Class<? extends Object> c = o.getClass();
		
		String nombreAttASettear = "set"+att;
		
		Constructor[] constructor = c.getConstructors();
		
		for(Constructor cons:constructor) {
			if(cons.getParameterCount() == 0) {
				try {
					p = cons.newInstance(null);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
		}
		
		Method[] metodos = c.getDeclaredMethods();
			
		for(Method m:metodos) {
			if(m.getName().equalsIgnoreCase(nombreAttASettear)) {
				Object[] params = new Object[1];
				params[0] = valor; 
					
				try {
					m.invoke(p, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}

		
		
	}
	
	public static Object ejecutarGet(Object o, String att) {
		Object p = null;
		Class<? extends Object> c = o.getClass();
		
		String nombreAttAGettear = "get"+att;
		
		Constructor[] constructor = c.getConstructors();
		
		for(Constructor cons:constructor) {
			if(cons.getParameterCount() == 0) {
				try {
					p = cons.newInstance(null);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
		}
		
		Method[] metodos = c.getDeclaredMethods();
		Object getObtenido = null;
		for(Method m:metodos) {
			if(m.getName().equalsIgnoreCase(nombreAttAGettear)) {
				try {
					getObtenido = m.invoke(p, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		
		return getObtenido;
	}
	
}
