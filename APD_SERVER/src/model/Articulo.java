package model;

import java.util.List;

import controller.AreaCompras;
import dao.ArticuloDao;
import dao.CompraRealizadaDao;
import dao.UbicacionDao;
import dto.ArticuloDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.ObjetoInexistenteException;

public class Articulo {
	private int id;
	private String codDeBarras;
	private String descripcion;
	private float tamano;
	private String presentacion;
	private String unidad;
	private float precioDeVenta;
	private int cantidadAComprar;
	private int cantidadUbicable;
	
	public Articulo(int id, String codDeBarras, String descripcion, float tamaño, String presentacion, String unidad,
			float precioDeVenta, int cantidadAComprar, int cantidadUbicable) {
		super();
		this.id = id;
		this.codDeBarras = codDeBarras;
		this.descripcion = descripcion;
		this.tamano = tamaño;
		this.presentacion = presentacion;
		this.unidad = unidad;
		this.precioDeVenta = precioDeVenta;
		this.cantidadAComprar = cantidadAComprar;
		this.cantidadUbicable = cantidadUbicable;
	}
	
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
	public float getTamano() {
		return tamano;
	}
	public void setTamaño(float tamano) {
		this.tamano = tamano;
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

	public void ajusteInvVenta(int cantidad, int facturaid) throws ObjetoInexistenteException {
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
	
	public void ajusteInvCompra(OrdenDeCompra compra, List<Ubicacion> ubicaciones) throws ObjetoInexistenteException {
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
		
		Ajuste ajuste= new Ajuste(0,cantidad, idUbicacion, this);
		ajuste.guardar();
		
		if(cantidad>0) {
			AreaCompras.getInstance().evaluarReStock(ajuste,getStock());
		}
	} 
	
	public List <Lote> obtenerVencidos(){
		//TODO evaluar necesidad
		return null;
	}
	
	public ArticuloDTO toDTO() {
		return new ArticuloDTO(id, codDeBarras, descripcion, tamano, presentacion, unidad, precioDeVenta);
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

	public Articulo guardar() {
		return ArticuloDao.getInstance().grabar(this);
	}
	
	public int getStock() {
		//llama a compra, pero esta haciendo contra la tabla madre que trae todas las extensiones de movInventario
		return CompraRealizadaDao.getInstance().getSumCantidadById(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
