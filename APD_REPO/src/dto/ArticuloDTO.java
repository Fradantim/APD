package dto;

import java.io.Serializable;

public class ArticuloDTO  implements Serializable{
	
	private static final long serialVersionUID = -6669623250456489526L;
	
	private int id;
	private String codDeBarras;
	private String descripcion;
	private float tamano;
	private String presentacion;
	private String unidad;
	private float precioDeVenta;
	private int cantidadAComprar;
	private int cantidadUbicable;

	public ArticuloDTO(int id, String codDeBarras, String descripcion, float tamano, String presentacion, String unidad,
			float precioDeVenta) {
		super();
		this.id = id;
		this.codDeBarras = codDeBarras;
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.presentacion = presentacion;
		this.unidad = unidad;
		this.precioDeVenta = precioDeVenta;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public void setTamano(float tamano) {
		this.tamano = tamano;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ArticuloDTO))return false;
	    ArticuloDTO otherMyClass = (ArticuloDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}	
	
	
}
