package model;

public class ItemPedidoCte {
	private int idItem;
	private int cantidad;
	private Articulo articulo;
	
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
}
