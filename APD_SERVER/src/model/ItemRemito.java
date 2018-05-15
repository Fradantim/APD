package model;

import dao.ArticuloDao;
import dao.ItemPedidoCteDao;
import dao.ItemRemitoDao;
import exception.ObjetoInexistenteException;

public class ItemRemito {
	private int idItem;
	private Articulo articulo;
	private int cantidad;
	private int idRemito;
	
	public ItemRemito() { }
	
	public ItemRemito(ItemPedidoCte item, int idRemito) { 
		this.setArticulo(item.getArticulo());
		this.setCantidad(item.getCantidad());
		this.setIdRemito(idRemito);
	}
	
	public ItemRemito(String codDeBarras, int idRemito, int cantidad) { 
		try {
			this.setArticulo(ArticuloDao.getInstance().getById(codDeBarras));
			this.setCantidad(cantidad);
			this.setIdRemito(idRemito);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void guardar() {
		ItemRemitoDao.getInstance().grabar(this);
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
	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
}
