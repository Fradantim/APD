package model;

import java.util.Date;

public class Lote {
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
	
	public void guardar() {
		//TODO hacer metodo
	}
}
