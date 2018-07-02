package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.ArticuloDao;
import exception.ObjetoInexistenteException;
import model.Producto;


@Entity
@Table(name="PRODUCTOS")
public class ProductoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Producto", updatable= false, nullable= false)
	private Integer iDproducto;
	@Column(name="precio")
 	private Float precio;
	@ManyToOne
	@JoinColumn(name="id_articulo")
	private  ArticuloEntity articulo;

    @ManyToMany(mappedBy = "productos")
	private List <ProveedorEntity> proveedores ;
	

	public ProductoEntity() {	}
	
	public ProductoEntity( Float pre, Integer idart) throws ObjetoInexistenteException {
		this.precio = pre;
 		this.articulo = ArticuloDao.getInstance().getByIdProd(idart);;
 	}
	
	public ProductoEntity(Producto prod) throws ObjetoInexistenteException {
		super();

		this.iDproducto = prod.getIdProducto();
		this.precio = prod.getPrecio();
  		this.articulo = ArticuloDao.getInstance().getByIdProd(prod.getArticulo().getId());
		
	}

	public List<ProveedorEntity> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorEntity> proveedores) {
		this.proveedores = proveedores;
	}

	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public Producto toNegocio() throws ObjetoInexistenteException  {
		Producto prod= new Producto(precio,articulo.getId());
		prod.setIdProducto(iDproducto);
	    return prod;		
	}

	
	public Integer getiDproducto() {
		return iDproducto;
	}
	public void setiDproducto(Integer iDproducto) {
		this.iDproducto = iDproducto;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
}
