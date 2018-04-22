package model;

import java.util.Date;

import dao.NotaCreditoDao;

public class NotaCredito extends MovimientoCtaCte {
	private Factura facturaBonificada;

	public NotaCredito() { }
	
	public NotaCredito(Date fecha, float importe, Factura factura) {
		this.fecha=fecha;
		this.importe=importe;
		this.facturaBonificada=factura;
	}
	
	public Factura getFacturaBonificada() {
		return facturaBonificada;
	}

	public void setFacturaBonificada(Factura facturaBonificada) {
		this.facturaBonificada = facturaBonificada;
	}
	
	@Override
	public NotaCredito guardar() {
		return NotaCreditoDao.getInstance().grabar(this);
	}
}
