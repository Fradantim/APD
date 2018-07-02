package model;

import dao.AjusteDao;

public class Ajuste extends MovimientoInventario {

	private int ubicacion;
	
	public Ajuste() { }
	
	public Ajuste(int idMovimiento, int cantidad, int ubicacion) {
		this.idMovimiento=idMovimiento;
		this.cantidad=cantidad;
		this.ubicacion = ubicacion;
	}
	
	public int getLugar() {
		return ubicacion;
	}
	public void setLugar(int lugar) {
		this.ubicacion = lugar;
	}
	
	@Override
	public Integer guardar(){
		this.idMovimiento=AjusteDao.getInstance().grabar(this);
		return this.idMovimiento;
	}
	public int getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}
}
