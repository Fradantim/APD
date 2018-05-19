package model;

import java.util.Date;

import dao.PagoDao;

public class Pago extends MovimientoCtaCte{
	
	public static final String ESPECIE_BONIFICABLE="Efectivo"; 
	
	private String especie;
	private Factura factura;

	public Pago() { }
	
	public Pago(Date fecha, float importe, String especie,Factura factura) { 
		this.fecha= fecha;
		this.importe = importe;
		this.especie = especie;
		this.factura = factura;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}
