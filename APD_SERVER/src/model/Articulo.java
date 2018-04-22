package model;

import java.util.List;

import dao.ArticuloDao;
import dao.UbicacionDao;
import exception.ArticuloInexistenteException;
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
		VentaRealizada venta = new VentaRealizada(cantidad,facturaid,this);
		venta.guardar();
		
		int cantidadADescontar=cantidad;
		//saco los elementos de las ubicaciones con vencimientos mas proximos
		for(Ubicacion ubicacion : getUbicaciones()) {
			if(cantidadADescontar>0) {
				int cantidadDescontable = ubicacion.getCantidadFisica();
				if(cantidadDescontable < cantidadADescontar) {
					//vacie la ubicacion
					ubicacion.setCantidadFisica(0);
					cantidadADescontar-=cantidadDescontable;
				} else {
					//vacie todo lo que tenia que vaciar
					ubicacion.setCantidadFisica(cantidadDescontable-cantidadADescontar);
					cantidadADescontar=0;
				}
				ubicacion.guardar();
			}
		}
	}
	
	public void ajusteInvCompra(OrdenDeCompra compra, List<Ubicacion> ubicaciones) {
		//TODO HACEMEEEEEEEEEE
	}
	
	public void ajusteInvRotura(String idUbicacion, int cantidad, Usuario encargado, Usuario usrAutorizador, String destino) {
		//TODO hacer metodo
	}
	 
	public void ajusteInvAjuste(int cantidad, String idUbicacion) {
		//TODO hacer metodo
	} 
	
	public List <Lote> obtenerVencidos(){
		//TODO evaluar necesidad
		return null;
	}
	
	public ArticuloView toView() {
		//TODO hacer metodo
		return null;

	}
	
	/**
	 * 
	 * @param codDeBarras
	 * @return Ubicaciones ordenadas por fecha de vencimiento, la mas proxima a vencer primero
	 */
	public List<Ubicacion> getUbicaciones() {
		try {
			return UbicacionDao.getInstance().getByIdArticulo(codDeBarras);
		} catch (ArticuloInexistenteException e) {
			// TODO Consultar, que hago con estas excepcion? en la teoria no deberian ocurrir.
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MovimientoInventario> getMovimientos() {
		//TODO hacer metodo
		return null;
	}

	public void guardar() {
		ArticuloDao.getInstance().grabar(this);
	}
}
