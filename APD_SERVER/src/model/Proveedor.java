package model;

import java.util.Date;

import view.ProveedorView;

public class Proveedor {
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFechaRecepcion(int productoId, int cantidad) {
		return null;
	}
	
	public void guardar() {
		
	}
	
	public ProveedorView toView() {
		return null;
	}
}
