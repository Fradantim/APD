package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.PedidoCte;
import model.Proveedor;

@Entity
@Table(name="PROVEEDORES")
public class ProveedorEntity {
	
	@Id 
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="Id_proveedor", unique=true, updatable = false, nullable = false)
	private Integer IdProveedor;
	@Column (name="Nombre", nullable=true)
	private String nombre;
	@Column (name="fecha_recepcion", nullable=true)
	private Date FechaRecepcion;
	
	public ProveedorEntity() {	}
	
	public ProveedorEntity(Date fechaRec, String nombre) {	
 		this.FechaRecepcion=fechaRec;
 		this.nombre=nombre;
	}
	
	public ProveedorEntity(Proveedor prov) {
		super();
		this.IdProveedor = prov.getId();
		this.nombre = prov.getNombre();
		this.FechaRecepcion = prov.getFechaRecepcion();
	}
	
	public Proveedor toNegocio()  {
		Proveedor prov= new Proveedor(IdProveedor,FechaRecepcion,nombre);
	    return prov;		
	}
	
	public Integer getIdProveedor() {
		return IdProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		IdProveedor = idProveedor;
	}

	public Date getFechaRecepcion() {
		return FechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		FechaRecepcion = fechaRecepcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
