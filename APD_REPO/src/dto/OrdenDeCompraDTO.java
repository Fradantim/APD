package dto;

import java.io.Serializable;
import java.util.Date;

public class OrdenDeCompraDTO implements Serializable{
	
	private static final long serialVersionUID = 599176661438306782L;
	public static String ESTADO_ELEGIR_PROV = "Pendiente eleccion proveedor";
	public static String ESTADO_PENDIENTE = "Pendiente de recepcion";
	public static String ESTADO_RECIBIDO = "Orden recibida";
	public static String ESTADO_UBICADA = "Orden Ubicada"; //(estado final)
	
	private int idOrdenCompra;
	private int cantidad;
	private String estado;
	private Date fechaRecepcion;
	private Date fechaVencimiento;
	private int idPedido;
	private ArticuloDTO articulo;
	
	public OrdenDeCompraDTO(int id, int cantidad, String estado,
			Date fechaRecepcion, Date fechaVen, int idped, ArticuloDTO articulo) {
		super();
		this.idOrdenCompra = id;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fechaRecepcion = fechaRecepcion;
		this.fechaVencimiento= fechaVen;
		this.idPedido = idped;
		this.articulo=articulo;
	}
	
	public int getId() {
		return idOrdenCompra;
	}
	public void setId(int id) {
		this.idOrdenCompra = id;
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
	
	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof OrdenDeCompraDTO))return false;
	    OrdenDeCompraDTO otherMyClass = (OrdenDeCompraDTO)other;
	    if(otherMyClass.getId()==idOrdenCompra) {
	    	return true;
	    }return false;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
}
