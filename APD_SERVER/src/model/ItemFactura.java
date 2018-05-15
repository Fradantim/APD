package model;

import dao.ItemFacturaDao;
import exception.ObjetoInexistenteException;

public class ItemFactura {
	
	public ItemFactura(int idItem, Articulo articulo, int cantidad, Factura factura) {
		super();
		this.idItem = idItem;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.factura = factura;
	}

	private int idItem;
	private Articulo articulo;
	private int cantidad;
	private Factura factura;
	
	public ItemFactura() {} 
	
	public ItemFactura(ItemPedidoCte item, Factura factura ) {
		this.articulo=item.getArticulo();
		this.cantidad=item.getCantidad();
		this.setFactura(factura);
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
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public void guardar() throws ObjetoInexistenteException {
		ItemFacturaDao.getInstance().grabar(this);
		
	}
}
