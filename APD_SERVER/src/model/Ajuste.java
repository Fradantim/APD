package model;

import view.ArticuloView;

public class Ajuste extends MovimientoInventario {

	private Ubicacion lugar;
	private boolean estado;
	
	public Ubicacion getLugar() {
		return lugar;
	}
	public void setLugar(Ubicacion lugar) {
		this.lugar = lugar;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public ArticuloView toView() {
		return null;
	}
	
	@Override
	public Ajuste guardar() {
		return null;
	}
}
