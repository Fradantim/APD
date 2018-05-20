package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import exception.ObjetoInexistenteException;
import model.Producto;
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
	@Column (name="fecha_compra", nullable=true)
	private Date FechaCompra;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="PROVEEDORES_PRODUCTOS", joinColumns={@JoinColumn(name="Id_proveedor")}, inverseJoinColumns={@JoinColumn(name="id_Producto")})
	private List <ProductoEntity> productos ;

	
	public ProveedorEntity() {	}
	
	public ProveedorEntity(Date fecharecep, String nombre, Date fechacomp) {	
		this.FechaCompra = fechacomp;
 		this.FechaRecepcion=fecharecep;
 		this.nombre=nombre;
 		
	}
	
	public ProveedorEntity(Proveedor prov) throws ObjetoInexistenteException {
		super();
		this.IdProveedor = prov.getId();
		this.nombre = prov.getNombre();
		this.FechaCompra= prov.getFechaCompra();
		this.FechaRecepcion = prov.getFechaRecepcion();
		
		ArrayList<ProductoEntity> prodAsociados = new ArrayList<>();
			for(Producto prod: prov.getProductos()) {
				prodAsociados.add(new ProductoEntity(prod));

			}
		this.productos = prodAsociados;
		
		
	}
	
	public Proveedor toNegocio()  {
		Proveedor prov= new Proveedor(FechaRecepcion,nombre,FechaCompra);
		prov.setId(IdProveedor);
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
