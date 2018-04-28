package dto;

import java.io.Serializable;

public class ProveedorDTO implements Serializable{

	private static final long serialVersionUID = -5397613112291339287L;
	
	private int id;
	private String nombre;
	
	public ProveedorDTO(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	
}
