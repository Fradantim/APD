package dto;

import java.io.Serializable;
import java.util.Date;

public class ProveedorDTO implements Serializable{

	private static final long serialVersionUID = -5397613112291339287L;
	
	private int id;
	private String nombre;
	private Date FechaRecepcion;
	
	public ProveedorDTO(int id, String nombre, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.FechaRecepcion = fecha;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ProveedorDTO))return false;
	    ProveedorDTO otherMyClass = (ProveedorDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}

	public Date getFechaRecepcion() {
		return FechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		FechaRecepcion = fechaRecepcion;
	}
}
