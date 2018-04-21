package model;

import java.util.List;

import view.ArticuloView;
import view.OrdenDeCompraView;

public class Articulo {
	private String codDeBarras;
	private String descripcion;
	private float tamaño;
	private String presentacion;
	private String unidad;
	private float precioDeVenta;
	private int cantidadAComprar;
	private int cantidadUbicable;
	
	public String getCodDeBarras() {
		return codDeBarras;
	}
	public void setCodDeBarras(String codDeBarras) {
		this.codDeBarras = codDeBarras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getTamaño() {
		return tamaño;
	}
	public void setTamaño(float tamaño) {
		this.tamaño = tamaño;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public float getPrecioDeVenta() {
		return precioDeVenta;
	}
	public void setPrecioDeVenta(float precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	public int getCantidadAComprar() {
		return cantidadAComprar;
	}
	public void setCantidadAComprar(int cantidadAComprar) {
		this.cantidadAComprar = cantidadAComprar;
	}
	public int getCantidadUbicable() {
		return cantidadUbicable;
	}
	public void setCantidadUbicable(int cantidadUbicable) {
		this.cantidadUbicable = cantidadUbicable;
	}

	public void ajusteInvVenta(int cantidad, int facturaid) {
		
	}
	
	public void ajusteInvCompra(OrdenDeCompraView compra, List<Ubicacion> ubicaciones) {
		
	}
	
	public void ajusteInvRotura(String idUbicacion, int cantidad, Usuario encargado, Usuario usrAutorizador, String destino) {
		
	}
	 
	public void ajusteInvAjuste(int cantidad, String idUbicacion) {
		
	} 
	
	public List <Lote> obtenerVencidos(){
		return null;
	}
	
	public ArticuloView toView() {
		return null;

	}
	
	public List<Ubicacion> getUbicaciones() {
		return null;
	}
	
	public List<MovimientoInventario> getMovimientos() {
		return null;
	}

	public void guardar() {
		
	}
}
