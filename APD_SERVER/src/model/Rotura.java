package model;

import dao.RoturaDao;

public class Rotura extends MovimientoInventario{
	private int encargado;
	private int usrAutoriza;
	private int ubicacionId;
	
	public Rotura() { }
	
	public Rotura(int cantidad, int encargado, int usrAutoriza, int ubicacionId, Articulo articulo) { 
		this.cantidad=cantidad;
		this.encargado=encargado;
		this.usrAutoriza=usrAutoriza;
		this.ubicacionId=ubicacionId;
	}
	
	public int getEncargado() {
		return encargado;
	}
	public void setEncargado(int encargado) {
		this.encargado = encargado;
	}
	public int getUsrAutoriza() {
		return usrAutoriza;
	}
	public void setUsrAutoriza(int usrAutoriza) {
		this.usrAutoriza = usrAutoriza;
	}
	
	
	@Override
	public Rotura guardar() {
		return RoturaDao.getInstance().grabar(this);
	}

	public int getUbicacionId() {
		return ubicacionId;
	}

	public void setUbicacionId(int ubicacionId) {
		this.ubicacionId = ubicacionId;
	}
}
