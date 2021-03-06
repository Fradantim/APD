package model;

import java.util.Date;

import dao.ArticuloDao;
import dao.OrdenDeCompraDao;
import dao.ProveedorDao;
import dto.OrdenDeCompraDTO;
import exception.ObjetoInexistenteException;

public class OrdenDeCompra {
	public static String ESTADO_ELEGIR_PROV = "Pendiente eleccion proveedor";
	public static String ESTADO_PENDIENTE = "Pendiente de recepcion";
	public static String ESTADO_RECIBIDO = "Orden recibida";
	public static String ESTADO_UBICADA = "Orden Ubicada"; //(estado final)
	
	private Integer idOrdenCompra;
	private Articulo articulo;
	private Integer cantidad;
	private String estado;
	private Date fechaRecepcion;
	private Date fechaVencimiento;
	private Integer idPedido;
	private Proveedor proveedor;
	
	public OrdenDeCompra() {}
	
	public OrdenDeCompra(Integer idart, Integer cantidad, Integer idPedido, Integer idprov) throws ObjetoInexistenteException {
		super();
		this.articulo = ArticuloDao.getInstance().getByRealId(idart);
		this.cantidad = cantidad;
		this.idPedido = idPedido;
		this.fechaRecepcion = null;
		this.fechaVencimiento = null;
		if (idprov == null){
			this.proveedor = null;
			this.estado = ESTADO_ELEGIR_PROV;}
		else{
			this.proveedor = ProveedorDao.getInstance().getById(idprov);
			this.estado = ESTADO_PENDIENTE;}
	}

	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}
	public void setIdOrdenCompra(Integer idOrdenCompra) {
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
	public void setCantidad(Integer cantidad) {
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
	
	public void guardar() throws ObjetoInexistenteException {
		OrdenDeCompraDao.getInstance().grabar(this);
	}
	
	public OrdenDeCompraDTO toDTO() {
		return new OrdenDeCompraDTO(this.idOrdenCompra,this.articulo.getCodDeBarras(),this.articulo.getDescripcion(), this.cantidad, this.estado,
		this.fechaRecepcion,this.fechaVencimiento,this.idPedido);
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
