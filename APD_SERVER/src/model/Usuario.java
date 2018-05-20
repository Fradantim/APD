package model;

import dao.UsuarioDao;

public class Usuario {
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String nivelRol;
	private String contrasena;
	
	public Usuario(int idUsuario, String nombre, String apellido, String nivelRol, String contrasena) {
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
	
	public void guardar() {
		UsuarioDao.getInstance().grabar(this);
	}
	
}
