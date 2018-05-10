package model;

import dao.AjusteDao;
import exception.ObjetoInexistenteException;

public class Ajuste extends MovimientoInventario {

	private int ubicacion;
	
	public Ajuste() { }
	
	public Ajuste(int idMovimiento, int cantidad, int ubicacion, Articulo articulo) {
		this.idMovimiento=idMovimiento;
		this.cantidad=cantidad;
		this.ubicacion = ubicacion;
		this.setArticulo(articulo);
	}
	
	public int getLugar() {
		return ubicacion;
	}
	public void setLugar(int lugar) {
		this.ubicacion = lugar;
	}
	
	@Override
	public Ajuste guardar() throws ObjetoInexistenteException {
		return AjusteDao.getInstance().grabar(this);
	}
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}
}
