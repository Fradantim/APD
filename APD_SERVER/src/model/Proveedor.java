package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ProveedorDao;
import dto.ProveedorDTO;
import entities.ProductoEntity;
import exception.ObjetoInexistenteException;

public class Proveedor {
	
	private int idProveedor;
	private String nombre;
	private Date FechaRecepcion;
	private Date FechaCompra;
	private List <Producto> productos ;

	public Proveedor() {
	}

	public Proveedor( Date fechaRecepcion, String nom, Date fechaCompra) {
		this.FechaCompra = fechaCompra;
		this.FechaRecepcion = fechaRecepcion;
		this.nombre = nom;
		this.productos = new ArrayList<Producto>();
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
	
	public void guardar() throws ObjetoInexistenteException {
		ProveedorDao.getInstance().grabar(this);
	}
	
	public ProveedorDTO toDTO() {
		return new ProveedorDTO(idProveedor, nombre,FechaRecepcion,FechaCompra);
	}

	public Date getFechaRecepcion() {
		return FechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		FechaRecepcion = fechaRecepcion;
	}


	public Date getFechaCompra() {
		return FechaCompra;
	}


	public void setFechaCompra(Date fechaCompra) {
		FechaCompra = fechaCompra;
	}


	public List <Producto> getProductos() {
		return productos;
	}


	public void setProductos(List <Producto> productos) {
		this.productos = productos;
	}

}
