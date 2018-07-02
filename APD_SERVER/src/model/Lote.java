package model;

import java.util.Date;

import dao.LoteDao;
import dao.UbicacionDao;

public class Lote {
	private int idLote;
	private Date fechaVencimiento;
	
	
	public Lote(Date Fecha) {
		this.fechaVencimiento = Fecha;
	}
	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int guardar() {
		this.idLote=LoteDao.getInstance().grabar(this);
		//this.idLote
		return this.idLote;
	}

	public int getId() {
		return idLote;
	}

	public void setId(int idLote) {
		this.idLote = idLote;
	}
	
	
}
