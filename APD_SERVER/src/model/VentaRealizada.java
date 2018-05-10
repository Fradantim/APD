package model;

import dao.VentaRealizadaDao;
import exception.ObjetoInexistenteException;

public class VentaRealizada extends MovimientoInventario{
	private int idFactura;
	
	public VentaRealizada () { }
	
	public VentaRealizada (int cantidad, int facturaId, Articulo articulo) {
		this.setArticulo(articulo);
		this.idFactura= facturaId;
		this.cantidad=cantidad;
		this.articulo=articulo;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	@Override
	public VentaRealizada guardar() throws ObjetoInexistenteException {
		return VentaRealizadaDao.getInstance().grabar(this);
	}
}
