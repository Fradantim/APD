package entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import dao.ArticuloDao;
import dao.PedidoCteDao;
import exception.ObjetoInexistenteException;
import model.ItemPedidoCte;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Table (name="ITEMS_PEDIDO")
public class ItemPedidoCteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Item_Pedido", unique=true, updatable = false, nullable = false)
	private Integer idItem;

	@Column(name="cantidad")
	private Integer Cantidad;
	
	@ManyToOne
	@JoinColumn(name="ArticuloId")
	private  ArticuloEntity articulo;
	
	@ManyToOne 
	@JoinColumn(name="id_pedido", nullable = false )
	private PedidoCteEntity pedido;

	
	public ItemPedidoCteEntity() {	}
	
	public ItemPedidoCteEntity(String codDeBarrasArticulo, Integer cantidad, Integer idpedido) throws ObjetoInexistenteException {	
 		this.Cantidad=cantidad;
  		this.articulo= ArticuloDao.getInstance().getByCodigo(codDeBarrasArticulo); 
  		this.pedido= PedidoCteDao.getInstance().getByIdPed(idpedido);
	}
	
	public ItemPedidoCteEntity(ItemPedidoCte itemped) throws ObjetoInexistenteException {
		this.Cantidad=itemped.getCantidad();
  		this.articulo= ArticuloDao.getInstance().getByCodigo(itemped.getArticulo().getCodDeBarras());
  		this.pedido= PedidoCteDao.getInstance().getByIdPed(itemped.getPedido().getIdPedidoCliente());
	}


	public ItemPedidoCte toNegocio() throws ObjetoInexistenteException {
		ItemPedidoCte item= new ItemPedidoCte(this.articulo.getCodDeBarras(),Cantidad,this.pedido.getIdPedidoCte());
		item.setIdItem(idItem);
		return item;
		
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getCantidad() {
		return Cantidad;
	}

	public void setCantidad(Integer cantidad) {
		Cantidad = cantidad;
	}
	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public PedidoCteEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCteEntity pedido) {
		this.pedido = pedido;
	}


}
