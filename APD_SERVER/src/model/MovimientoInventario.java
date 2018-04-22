package model;

public abstract class MovimientoInventario {
	
	protected int idMovimiento;
	protected int cantidad;
	protected Articulo articulo;
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public MovimientoInventario guardar() {
		return null;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

}
