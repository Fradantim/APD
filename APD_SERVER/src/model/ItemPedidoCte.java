package model;

import dao.ItemPedidoCteDao;

public class ItemPedidoCte {
	private int idItem;
	private int cantidad;
	private Articulo articulo;
	private PedidoCte pedido;
	
	public ItemPedidoCte() {	}
	
	public ItemPedidoCte(Articulo articulo, int cantidad, PedidoCte pedido) {	
		this.articulo=articulo;
		this.cantidad=cantidad;
		this.pedido=pedido;
	}
	
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public float getTotalBruto() {
		return articulo.getPrecioDeVenta()*cantidad;
	}
	
	public void guardar() {
		ItemPedidoCteDao.getInstance().grabar(this);
	}

	public PedidoCte getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCte pedido) {
		this.pedido = pedido;
	}
}
