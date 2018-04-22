package model;

import java.util.Date;

import dao.OrdenDeCompraDao;
import view.OrdenDeCompraView;

public class OrdenDeCompra {
	public static String ESTADO_ELEGIR_PROV = "Pendiende de eleccion de proveedor";
	public static String ESTADO_PENDIENTE = "Pendiente de recepcion";
	public static String ESTADO_RECIBIDO = "Orden recibida";
	public static String ESTADO_UBICADA = "Orden Ubicada"; //(estado final)
	
	private int idOrdenCompra;
	private Articulo articulo;
	private int cantidad;
	private String estado;
	private Date fechaRecepcion;
	
	public OrdenDeCompra() {}
	
	public OrdenDeCompra(Articulo articulo, int cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}
	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	
	public void guardar() {
		OrdenDeCompraDao.getInstance().grabar(this);
	}
	
	public OrdenDeCompraView toView() {
		return null;
	}
}
