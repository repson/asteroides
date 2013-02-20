package com.example.asteroides;

import java.util.Vector;

public interface AlmacenPuntuaciones {

	public void guardaPuntuacion(int puntos, String nombre, long fecha);
	
	public Vector<String> listaPuntuaciones(int cantidad);
}
