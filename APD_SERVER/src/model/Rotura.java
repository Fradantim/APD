package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dao.RoturaDao;

@Entity  
@DiscriminatorValue("AJUSTE")  
public class Rotura extends MovimientoInventario{
	private int encargado;
	private int usrAutoriza;
	private int ubicacionId;
	
	public Rotura() { }
	
	public Rotura(int cantidad, int encargado, int usrAutoriza, int ubicacionId) { 
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
	public Integer guardar() {
		return RoturaDao.getInstance().grabar(this);
	}

	public int getUbicacionId() {
		return ubicacionId;
	}

	public void setUbicacionId(int ubicacionId) {
		this.ubicacionId = ubicacionId;
	}
}
