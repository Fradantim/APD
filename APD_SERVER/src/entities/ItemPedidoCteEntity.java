package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import exception.ObjetoInexistenteException;
import model.ItemPedidoCte;

import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table (name="ITEMS_PEDIDO")
public class ItemPedidoCteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Item_Pedido", updatable= false, nullable= false)
	private Integer idItem;
	@Column(name="cantidad")
	private Integer Cantidad;
	@Column(name="IDArticulo")
 	private String codDeBarrasArticulo;
	@Column(name="IDPedido")	
	private Integer pedido;

	public ItemPedidoCteEntity() {	}
	
	public ItemPedidoCteEntity(String codDeBarrasArticulo, Integer cantidad, Integer idpedido) {	
 		this.Cantidad=cantidad;
 		this.codDeBarrasArticulo=codDeBarrasArticulo; 
		this.pedido=idpedido;
	}
	
	public ItemPedidoCteEntity(ItemPedidoCte itemped) {	
		this.Cantidad=itemped.getCantidad();
		this.codDeBarrasArticulo=itemped.getArticulo().getCodDeBarras();
		this.pedido=itemped.getPedido().getIdPedidoCliente();
	}


	public ItemPedidoCte toNegocio() throws ObjetoInexistenteException {
		ItemPedidoCte item= new ItemPedidoCte(codDeBarrasArticulo,Cantidad,pedido);
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

}
