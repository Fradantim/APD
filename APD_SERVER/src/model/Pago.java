package model;

import java.util.Date;

import dao.PagoDao;

public class Pago extends MovimientoCtaCte{
	
	private String especie;

	public Pago() { }
	
	public Pago(Date fecha, float importe, String especie) { 
		this.fecha= fecha;
		this.importe = importe;
		this.especie = especie;
	}
	
	public Integer guardar() {
		this.idMovimientoCtaCte=PagoDao.getInstance().grabar(this);
		return this.idMovimientoCtaCte;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
}
