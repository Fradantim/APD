package model;

import java.util.Date;

import dao.ProveedorDao;
import view.ProveedorView;

public class Proveedor {
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFechaRecepcion(Articulo articulo, int cantidad) {
		//dentro de 1 hora
		return new Date((new Date()).getTime()+60L*10000L);
	}
	
	public Date getFechaVencimiento(Articulo articulo, Date fechaRecepcion) {
		//suma un mes a la fecha de recepcion ingresada
		return new Date(fechaRecepcion.getTime()+30L*24L*60L*10000L);
	}
	
	public void guardar() {
		ProveedorDao.getInstance().grabar(this);
	}
	
	public ProveedorView toView() {
		return null;
	}
}
