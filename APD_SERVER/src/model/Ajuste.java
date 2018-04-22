package model;

import dao.AjusteDao;
import view.ArticuloView;

public class Ajuste extends MovimientoInventario {

	private int ubicacion;
	
	public Ajuste() { }
	
	public Ajuste(int cantidad, int ubicacion, Articulo articulo) {
		this.cantidad=cantidad;
		this.ubicacion = ubicacion;
		this.articulo= articulo;
	}
	
	public int getLugar() {
		return ubicacion;
	}
	public void setLugar(int lugar) {
		this.ubicacion = lugar;
	}
	
	public ArticuloView toView() {
		return null;
	}
	
	@Override
	public Ajuste guardar() {
		return AjusteDao.getInstance().grabar(this);
	}
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}
}
