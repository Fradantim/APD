package model;

import java.util.Date;

public class Lote {
	private int idLote;
	private Date fechaVencimiento;
	
	public Lote(int IdLote, Date Fecha) {
		this.idLote = IdLote;
		this.fechaVencimiento = Fecha;
	}
	
	public Lote(Date fechaVencimiento) { 
		this.fechaVencimiento= fechaVencimiento;
	}
	
	public int getIdLote() {
		return idLote;
	}
	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public void guardar() {
		//TODO hacer metodo
	}
}
