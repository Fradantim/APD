package dto;

import java.io.Serializable;
import java.util.Date;

public class OrdenDeCompraDTO implements Serializable{
	
	private static final long serialVersionUID = 599176661438306782L;
	
	private int id;
	private String articuloCodDeBarras;
	private String articuloDescripcion;
	private int cantidad;
	private String estado;
	private Date fechaRecepcion;
		
	public OrdenDeCompraDTO(int id, String articuloCodDeBarras, String articuloDescripcion, int cantidad, String estado,
			Date fechaRecepcion) {
		super();
		this.id = id;
		this.articuloCodDeBarras = articuloCodDeBarras;
		this.articuloDescripcion = articuloDescripcion;
		this.cantidad = cantidad;
		this.estado = estado;
		this.fechaRecepcion = fechaRecepcion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArticuloCodDeBarras() {
		return articuloCodDeBarras;
	}
	public void setArticuloCodDeBarras(String articuloCodDeBarras) {
		this.articuloCodDeBarras = articuloCodDeBarras;
	}
	public String getArticuloDescripcion() {
		return articuloDescripcion;
	}
	public void setArticuloDescripcion(String articuloDescripcion) {
		this.articuloDescripcion = articuloDescripcion;
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
}
