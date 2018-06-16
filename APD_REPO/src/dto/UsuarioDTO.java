package dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = -4391755172804698329L;
	public static String ROL_CLIENTE = "Cliente";
	public static String ROL_ADMIN_CLIENTE = "Administrador de Clientes";
	public static String ROL_FACTURACION_DESPACHO = "Facturacion / Despacho";
	public static String ROL_ADMIN_ALMACEN = "Administrador de Almacen";
	
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String nivelRol;
	private String contrasena;
	
	public UsuarioDTO(int idUsuario, String nombre, String apellido, String nivelRol, String contrasena) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nivelRol = nivelRol;
		this.contrasena = contrasena;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNivelRol() {
		return nivelRol;
	}
	public void setNivelRol(String nivelRol) {
		this.nivelRol = nivelRol;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof UsuarioDTO))return false;
	    UsuarioDTO otherMyClass = (UsuarioDTO)other;
	    if(otherMyClass.getIdUsuario()==idUsuario) {
	    	return true;
	    }return false;
	}
	
}
