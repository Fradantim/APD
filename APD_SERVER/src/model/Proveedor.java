package model;

import java.util.Date;

import dao.ProveedorDao;
import dto.ProveedorDTO;

public class Proveedor {
	
	private int idProveedor;
	private String nombre;
	private Date FechaRecepcion;

	public Proveedor(Integer id, Date fechaRecepcion, String nom) {
		this.idProveedor = id;
		this.FechaRecepcion = fechaRecepcion;
		this.nombre = nom;
	}


	public Proveedor() {
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return idProveedor;
	}

	public void setId(int id) {
		this.idProveedor = id;
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
	
	public ProveedorDTO toDTO() {
		return new ProveedorDTO(idProveedor, nombre,FechaRecepcion);
	}

	public Date getFechaRecepcion() {
		return FechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		FechaRecepcion = fechaRecepcion;
	}

}
