package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;

import dao.ArticuloDao;
import dao.ClienteDao;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.ItemPedidoCte;
import model.PedidoCte;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table (name="ITEMS_PEDIDO")
public class ItemPedidoCteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Item_Pedido", updatable= false, nullable= false)
	private Integer idItem;
	@Column(name="cantidad")
	private Integer Cantidad;
	
	@OneToOne
	@JoinColumn(name="id_articulo")
 	private ArticuloEntity articulo;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id_pedido",nullable= false)
	private PedidoCteEntity pedido;

	@Transient
	private ArticuloEntity aux;
	@Transient
	private PedidoCteEntity aux2;

	public ItemPedidoCteEntity() {	}
	
	public ItemPedidoCteEntity(String codbarras, Integer cantidad, Integer idpedido) {	
 		this.Cantidad=cantidad;
 		this.articulo.setCodDeBarras(codbarras); 
		this.pedido.setIdPedidoCte(idpedido);
	}
	
	public ItemPedidoCteEntity(ItemPedidoCte itemped) {	
		
		this.Cantidad=itemped.getCantidad();
		this.aux = new ArticuloEntity(itemped.getArticulo());
		this.articulo = aux;
		this.articulo.setCodDeBarras(itemped.getArticulo().getCodDeBarras());
		this.aux2 = new PedidoCteEntity(itemped.getPedido());
		this.pedido = aux2;
		this.pedido.setIdPedidoCte(itemped.getPedido().getIdPedidoCliente());
	}


	public ItemPedidoCte toNegocio() throws ObjetoInexistenteException {
		return new ItemPedidoCte(this.articulo.getCodDeBarras(),this.Cantidad,this.pedido.getIdPedidoCte());
		
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

	public PedidoCteEntity getPedido() throws ObjetoInexistenteException {
		return pedido;
	}

	public void setPedido(PedidoCteEntity pedido) {
		this.pedido = pedido;
	}



}
