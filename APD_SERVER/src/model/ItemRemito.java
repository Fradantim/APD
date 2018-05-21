package model;

public class ItemRemito {
	private int idItem;
	private Articulo articulo;
	private int cantidad;
	private Remito remito;

	public ItemRemito() { }
	
	public ItemRemito(ItemPedidoCte item) { 
		this.setArticulo(item.getArticulo());
		this.setCantidad(item.getCantidad());
	}
	
	public ItemRemito(Articulo articulo, int cantidad, Remito remito) { 
		this.setArticulo(articulo);
		this.setCantidad(cantidad);
		this.setRemito(remito);
	}
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Remito getRemito() {
		return remito;
	}

	public void setRemito(Remito remito) {
		this.remito = remito;
	}
}
