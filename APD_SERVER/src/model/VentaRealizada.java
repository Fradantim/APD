package model;

import dao.VentaRealizadaDao;

public class VentaRealizada extends MovimientoInventario{
	private int idFactura;
	
	public VentaRealizada () { }
	
	public VentaRealizada (int cantidad, int facturaId) {
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
	
	@Override
	public Integer guardar(){
		return VentaRealizadaDao.getInstance().grabar(this);
	}
}
