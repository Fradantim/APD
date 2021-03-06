package model;

import dao.CompraRealizadaDao;

public class CompraRealizada extends MovimientoInventario{

	private int pedidoCte;
	
	public CompraRealizada() { }
	
	public CompraRealizada(int cantidad, int pedidoCte) {
		this.cantidad=cantidad;
		this.pedidoCte=pedidoCte;
	}

	public int getPedidoCte() {
		return pedidoCte;
	}

	public void setPedidoCte(int pedidoCte) {
		this.pedidoCte = pedidoCte;
	}
	
	@Override
	public Integer guardar(){
		return CompraRealizadaDao.getInstance().grabar(this);
	}
}
