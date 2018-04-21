package model;

import java.util.Date;

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
	public static String getESTADO_ELEGIR_PROV() {
		return ESTADO_ELEGIR_PROV;
	}
	public static void setESTADO_ELEGIR_PROV(String eSTADO_ELEGIR_PROV) {
		ESTADO_ELEGIR_PROV = eSTADO_ELEGIR_PROV;
	}
	public static String getESTADO_PENDIENTE() {
		return ESTADO_PENDIENTE;
	}
	public static void setESTADO_PENDIENTE(String eSTADO_PENDIENTE) {
		ESTADO_PENDIENTE = eSTADO_PENDIENTE;
	}
	public static String getESTADO_RECIBIDO() {
		return ESTADO_RECIBIDO;
	}
	public static void setESTADO_RECIBIDO(String eSTADO_RECIBIDO) {
		ESTADO_RECIBIDO = eSTADO_RECIBIDO;
	}
	public static String getESTADO_UBICADA() {
		return ESTADO_UBICADA;
	}
	public static void setESTADO_UBICADA(String eSTADO_UBICADA) {
		ESTADO_UBICADA = eSTADO_UBICADA;
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
		
	}
	
	public OrdenDeCompraView toView() {
		return null;
	}
}
