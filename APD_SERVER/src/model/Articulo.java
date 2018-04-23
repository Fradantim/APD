package model;

import java.util.List;

import controller.AreaCompras;
import dao.ArticuloDao;
import dao.UbicacionDao;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.ObjetoInexistenteException;
import view.ArticuloView;
import view.OrdenDeCompraView;

public class Articulo {
	private String codDeBarras;
	private String descripcion;
	private float tama�o;
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
	public float getTama�o() {
		return tama�o;
	}
	public void setTama�o(float tama�o) {
		this.tama�o = tama�o;
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
		CompraRealizada nuevaCompra = new CompraRealizada(compra.getCantidad(), compra.getIdPedido(), this);
		nuevaCompra.guardar();
		
		int cantidadAUbicar=nuevaCompra.getCantidad();
		
		for(Ubicacion ubicacion: ubicaciones) {
			ubicacion.setArticulo(this);
			ubicacion.setLote(new Lote(compra.getFechaVencimiento()));
			if(cantidadAUbicar>getCantidadUbicable()) {
				ubicacion.setCantidadFisica(getCantidadUbicable());
				cantidadAUbicar-=getCantidadUbicable();
			} else {
				ubicacion.setCantidadFisica(cantidadAUbicar);
				cantidadAUbicar=0;
			}
			ubicacion.guardar();
		}
		
	}
	
	public void ajusteInvRotura(int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException {
		Ubicacion ubicacion = UbicacionDao.getInstance().getById(idUbicacion);
		if(!ubicacion.getArticulo().equals(this)) {
			throw new LaUbicacionNoTieneEsteArticuloException("La ubicacion "+idUbicacion+" no tiene articulos "+codDeBarras);
		}
		ubicacion.setCantidadFisica(ubicacion.getCantidadFisica()-cantidad);
		ubicacion.guardar();
		
		Rotura rotura = new Rotura(cantidad, encargado, usrAutorizador, idUbicacion, this);
		rotura.guardar();
		
		AreaCompras.getInstance().evaluarReStock(rotura, getStock());
	}
	 
	public void ajusteInvAjuste(int cantidad, int idUbicacion) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException {
		//TODO hacer metodo
		Ubicacion ubicacion = UbicacionDao.getInstance().getById(idUbicacion);
		if(!ubicacion.getArticulo().equals(this)) {
			throw new LaUbicacionNoTieneEsteArticuloException("La ubicacion "+idUbicacion+" no tiene articulos "+codDeBarras);
		}
		ubicacion.setCantidadFisica(ubicacion.getCantidadFisica()-cantidad);
		ubicacion.guardar();
		
		Ajuste ajuste= new Ajuste(cantidad, idUbicacion, this);
		ajuste.guardar();
		
		if(cantidad>0) {
			AreaCompras.getInstance().evaluarReStock(ajuste,getStock());
		}
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
		return UbicacionDao.getInstance().getByIdArticulo(codDeBarras);
	}
	
	public List<MovimientoInventario> getMovimientos() {
		//TODO hacer metodo
		return null;
	}

	public void guardar() {
		ArticuloDao.getInstance().grabar(this);
	}
	
	public int getStock() {
		//TODO hacer metodo
		return 0;
	}
}
