package model;

import java.util.Date;

import dao.PagoDao;

public class Pago extends MovimientoCtaCte{
	
	public static final String ESPECIE_BONIFICABLE="Efectivo"; 
	
	private String especie;
	private Factura factura;

	public Pago() { }
	
	public Pago(Date fecha, float importe, String especie,CtaCte cuenta, Factura factura) { 
		this.fecha= fecha;
		this.importe = importe;
		this.especie = especie;
		this.cuentaCliente= cuenta;
		this.factura = factura;
	}
	
	public Pago guardar() {
		return PagoDao.getInstance().grabar(this);
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
