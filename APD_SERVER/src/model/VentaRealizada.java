package model;

import dao.VentaRealizadaDao;
import dto.ArticuloDTO;

public class VentaRealizada extends MovimientoInventario{
	private int idFactura;
	
	public VentaRealizada () { }
	
	public VentaRealizada (int cantidad, int facturaId, Articulo articulo) {
		this.setArticulo(articulo);
		this.idFactura= facturaId;
		this.cantidad=cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public ArticuloDTO toView() {
		//TODO hacer metodo
		return null;
	}
	
	@Override
	public VentaRealizada guardar() {
		return VentaRealizadaDao.getInstance().grabar(this);
	}
}
