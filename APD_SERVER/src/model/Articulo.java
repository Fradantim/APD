package model;

import java.util.ArrayList;
import java.util.List;

import controller.AreaCompras;
import dao.AjusteDao;
import dao.ArticuloDao;
import dao.CompraRealizadaDao;
import dao.RoturaDao;
import dao.UbicacionDao;
import dao.VentaRealizadaDao;
import dto.ArticuloDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;

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

	public void ajusteInvVenta(int cantidad, int facturaid) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		agregarMovimientoVenta(new VentaRealizada(cantidad,facturaid));
		
		int cantidadADescontar=cantidad;
		//saco los elementos de las ubicaciones con vencimientos mas proximos
		for(Ubicacion ubicacion : getUbicaciones()) {
			if(cantidadADescontar>0) {
				int cantidadDescontable = ubicacion.getCantidadFisica();
				if(cantidadDescontable < cantidadADescontar) {
					//vacie la ubicacion
					ubicacion.ajustarStock(this, ubicacion.getCantidadFisica());
					cantidadADescontar-=cantidadDescontable;
				} else {
					//vacie todo lo que tenia que vaciar
					ubicacion.ajustarStock(this, cantidadADescontar);
					cantidadADescontar=0;
				}
			}
		}
	}
	
	public void ajusteInvCompra(OrdenDeCompra compra, List<Ubicacion> ubicaciones) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		CompraRealizada nuevaCompra = new CompraRealizada(compra.getCantidad(), compra.getIdPedido());
		agregarMovimientoCompra(nuevaCompra);
		
		int cantidadAUbicar=nuevaCompra.getCantidad();
		
		for(Ubicacion ubicacion: ubicaciones) {
			ubicacion.setLote(new Lote(compra.getFechaVencimiento()));
			if(cantidadAUbicar>getCantidadUbicable()) {
				ubicacion.ajustarStock(this, getCantidadUbicable());
				cantidadAUbicar-=getCantidadUbicable();
			} else {
				ubicacion.ajustarStock(this, cantidadAUbicar);
				cantidadAUbicar=0;
			}
		}
		
	}
	
	public void ajusteInvRotura(int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Ubicacion ubicacion = UbicacionDao.getInstance().getById(idUbicacion);
		ubicacion.ajustarStock(this, cantidad);		
		
		Rotura rotura = new Rotura(cantidad, encargado, usrAutorizador, idUbicacion);
		agregarMovimientoRotura(rotura);
		
		AreaCompras.getInstance().evaluarReStock(rotura, getStock());
	}
	 
	public void ajusteInvAjuste(int cantidad, int idUbicacion) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Ubicacion ubicacion = UbicacionDao.getInstance().getById(idUbicacion);
		ubicacion.ajustarStock(this, cantidad);

		Ajuste ajuste= new Ajuste(0,cantidad, idUbicacion);
		agregarMovimientoAjuste(ajuste);
		
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
		//llama a compra, pero esta haciendo contra la tabla madre que trae todas las extensiones de movInventario
		return CompraRealizadaDao.getInstance().getByIdArticulo(codDeBarras);
	}

	public Integer guardar() {
		this.id = ArticuloDao.getInstance().grabar(this);
		return this.id;
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
	
	

	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */
	public Integer agregarMovimientoCompra(CompraRealizada mov) {
		mov.setArticulo(this);
		return CompraRealizadaDao.getInstance().grabar(mov);
	}

	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */

	public Integer agregarMovimientoVenta(VentaRealizada mov) {
		mov.setArticulo(this);
		return VentaRealizadaDao.getInstance().grabar(mov);
	}

	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */

	public Integer agregarMovimientoAjuste(Ajuste mov) {
		mov.setArticulo(this);
		return AjusteDao.getInstance().grabar(mov);
	}
	

	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */
	public Integer agregarMovimientoRotura(Rotura mov) {
		mov.setArticulo(this);
		return RoturaDao.getInstance().grabar(mov);
	}
}
