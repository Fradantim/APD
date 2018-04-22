package model;

import view.ArticuloView;

public class VentaRealizada extends MovimientoInventario{
	private int idFactura;
	
	public VentaRealizada () { }
	
	public VentaRealizada (int cantidad, int facturaId, Articulo articulo) {
		this.articulo= articulo;
		this.idFactura= facturaId;
		this.cantidad=cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public ArticuloView toView() {
		return null;
	}
	
	@Override
	public VentaRealizada guardar() {
		return null;
	}
}