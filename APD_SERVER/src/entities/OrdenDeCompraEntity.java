package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import dao.ArticuloDao;
import dao.ProveedorDao;
import dto.OrdenDeCompraDTO;
import exception.ObjetoInexistenteException;
import model.OrdenDeCompra;

@Entity
@Table (name="ORDENES_DE_COMPRA")

public class OrdenDeCompraEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_OrdenCompra") 
	private int idOrdenCompra;	
	@Column(name="Cantidad", nullable=true)
	private int Cantidad;
	@Column(name="idPedido", nullable=true)
	private Integer idPedido;
	@Column(name="Estado", nullable=true)
	private String Estado;
	@Column(name="fecha_recepcion", nullable=true)
	private Date fechaRecepcion;
	@Column(name="fecha_vencimiento", nullable=true)
	private Date fechaVencimiento;

			
	@OneToMany (cascade=CascadeType.ALL, mappedBy = "ordenCompra")
	@JoinColumn(name="id_OrdenCompra")
	private List <ReservaArticuloEntity> reservaspedido ;

	@ManyToOne 
    @JoinColumn(name="id_Articulo")
    private ArticuloEntity articulo;
	
	@ManyToOne 
	@JoinColumn(name="Id_proveedor",nullable = true)
	private ProveedorEntity proveedorOC ;

	
	public OrdenDeCompraEntity() {}


	public OrdenDeCompraEntity(int cantidad, int idPedido, int idart, String estado, Integer IdProveedor) throws ObjetoInexistenteException {
		this.Cantidad = cantidad;
		this.idPedido = idPedido;
		this.articulo = ArticuloDao.getInstance().getByIdProd(idart);
		this.Estado = estado;
		this.fechaRecepcion = null;
		this.fechaVencimiento = null;
		if (IdProveedor == null)
			this.proveedorOC = null;
		else
			this.proveedorOC = ProveedorDao.getInstance().getByIdE(IdProveedor);
	}


	public OrdenDeCompraEntity(OrdenDeCompra orden) throws ObjetoInexistenteException {
		this.Cantidad = orden.getCantidad();
		this.idPedido = orden.getIdPedido();
		this.articulo = ArticuloDao.getInstance().getByIdProd(orden.getArticulo().getId());
		this.fechaRecepcion = orden.getFechaRecepcion();
		this.fechaVencimiento = orden.getFechaVencimiento();
		if (orden.getProveedor() == null){
			this.proveedorOC = null;
			this.Estado = "Pendiente eleccion proveedor";}
		else{
			this.proveedorOC = ProveedorDao.getInstance().getByIdE(orden.getProveedor().getId());
			this.Estado = "Pendiente de recepcion";}
			}
	
	
	public OrdenDeCompra toNegocio() throws ObjetoInexistenteException{
		OrdenDeCompra orden =null;
		orden = new OrdenDeCompra(this.articulo.getId(),this.Cantidad,this.idPedido,null);
		orden.setIdOrdenCompra(idOrdenCompra);
		if (this.proveedorOC == null){
			orden.setProveedor(null);
			orden.setEstado("Pendiente eleccion proveedor");}
		else{
			orden.setProveedor(ProveedorDao.getInstance().getById(proveedorOC.getIdProveedor()));;
			orden.setEstado("Pendiente de recepcion");}
		 
		return orden;
 
     }
		
	public OrdenDeCompraDTO toDTO() throws ObjetoInexistenteException {
		return new OrdenDeCompraDTO(this.idOrdenCompra,this.articulo.getCodDeBarras(),this.articulo.getDescripcion(),this.Cantidad,this.Estado,this.fechaRecepcion,this.fechaVencimiento,this.idPedido);
	}
	

	public Integer getId() {
		return idOrdenCompra;
	}


	public void setId(Integer id) {
		this.idOrdenCompra = id;
	}


	public int getCantidad() {
		return Cantidad;
	}


	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}


	public int getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}


	public ArticuloEntity getArticulo() {
		return articulo;
	}


	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}	  
	
	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		this.Estado = estado;
	}


	public int getIdOrdenCompra() {
		return idOrdenCompra;
	}


	public void setIdOrdenCompra(int idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}


	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}


	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public List<ReservaArticuloEntity> getReservaspedido() {
		return reservaspedido;
	}


	public void setReservaspedido(List<ReservaArticuloEntity> reservaspedido) {
		this.reservaspedido = reservaspedido;
	}


	public ProveedorEntity getPorveedorOC() {
		return proveedorOC;
	}


	public void setPorveedorOC(ProveedorEntity porveedorOC) {
		proveedorOC = porveedorOC;
	}


	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	    
}