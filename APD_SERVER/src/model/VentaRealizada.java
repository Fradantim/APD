package model;

import view.ArticuloView;

public class VentaRealizada extends MovimientoInventario{
	private int idFactura;

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
	public void guardar() {
		
	}
}
