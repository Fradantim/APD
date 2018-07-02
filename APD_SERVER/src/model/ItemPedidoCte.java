package model;

import dao.ArticuloDao;
import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import exception.ObjetoInexistenteException;

public class ItemPedidoCte {
	private int idItem;
	private int cantidad;
	private Articulo articulo;
	private PedidoCte pedido;
	
	public ItemPedidoCte() {	}
	
	public ItemPedidoCte(String codbarras, Integer cantidad, Integer idped) throws ObjetoInexistenteException {	
		this.articulo= ArticuloDao.getInstance().getByCodArt(codbarras);
		this.cantidad=cantidad;
		this.pedido= PedidoCteDao.getInstance().getById(idped);
	}
	
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public float getTotalBruto() {
		return ItemPedidoCteDao.getInstance().getSumImporte(idItem);
	}
	
	public ItemPedidoCte guardar() throws ObjetoInexistenteException {
 		return ItemPedidoCteDao.getInstance().grabar(this);
	}

	public PedidoCte getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCte pedido) {
		this.pedido = pedido;
	}
}