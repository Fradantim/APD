package model;

public class CompraRealizada extends MovimientoInventario{

	private int pedidoCte;

	public int getPedidoCte() {
		return pedidoCte;
	}

	public void setPedidoCte(int pedidoCte) {
		this.pedidoCte = pedidoCte;
	}
	
	@Override
	public void guardar() {
		
	}
}
