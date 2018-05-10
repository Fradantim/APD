package model;

import dao.CompraRealizadaDao;
import exception.ObjetoInexistenteException;

public class CompraRealizada extends MovimientoInventario{

	private int pedidoCte;
	
	public CompraRealizada() { }
	
	public CompraRealizada(int cantidad, int pedidoCte, Articulo articulo) {
		this.cantidad=cantidad;
		this.pedidoCte=pedidoCte;
		this.setArticulo(articulo);
	}

	public int getPedidoCte() {
		return pedidoCte;
	}

	public void setPedidoCte(int pedidoCte) {
		this.pedidoCte = pedidoCte;
	}
	
	@Override
	public CompraRealizada guardar() throws ObjetoInexistenteException {
		return CompraRealizadaDao.getInstance().grabar(this);
	}
}
