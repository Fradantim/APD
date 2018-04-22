package model;

public class NotaCredito extends MovimientoCtaCte {
	private Factura facturaBonificada;

	public Factura getFacturaBonificada() {
		return facturaBonificada;
	}

	public void setFacturaBonificada(Factura facturaBonificada) {
		this.facturaBonificada = facturaBonificada;
	}
	
	public void bonificar(Factura factura) {
		
	}
	
	@Override
	public NotaCredito guardar() {
		return null;
	}
}
