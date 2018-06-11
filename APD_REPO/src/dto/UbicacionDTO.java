package dto;

import java.io.Serializable;

public class UbicacionDTO implements Serializable{

	private static final long serialVersionUID = -2593308794895445596L;
	
	private int id;
	private String calle;
	private int bloque;
	private int estante;
	private int posicion;
	private int cantidad;
	
	public UbicacionDTO(int id, String calle, int bloque, int estante, int posicion, int cantidad) {
		super();
		this.id = id;
		this.calle = calle;
		this.bloque = bloque;
		this.estante = estante;
		this.posicion = posicion;
		this.cantidad = cantidad;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getBloque() {
		return bloque;
	}
	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof UbicacionDTO))return false;
	    UbicacionDTO otherMyClass = (UbicacionDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}
}
