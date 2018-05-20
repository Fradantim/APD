package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.Cliente;
import model.Usuario;

@Entity
@Table(name="USUARIOS")
public class UsuarioEntity {
	@Id 
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_usuario", unique=true, updatable = false, nullable = false)
	private Integer idUsuario;
	@Column (name="nombre")
	private String nombre;
	@Column (name="apellido")
	private String apellido;
	@Column (name="nivel_rol")
	private String nivelRol;
	@Column (name="contrasena")
	private String contrasena;
	
	public UsuarioEntity() {}
	
	public UsuarioEntity(Usuario user) {
		super();
		idUsuario = user.getIdUsuario() == 0 ? null : user.getIdUsuario();
		nombre = user.getNombre();
		apellido = user.getApellido();
		nivelRol = user.getNivelRol();
		contrasena = user.getContrasena();
	}
	
	public UsuarioEntity(String nom, String ape, String nr, String contra) {
		super();
		nombre = nom;
		apellido = ape;
		nivelRol = nr;
		contrasena = contra;
	}
	
	public Usuario toNegocio(){
		return new Usuario(idUsuario, nombre, apellido, nivelRol, contrasena);
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
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
}
