package model;

import dao.UbicacionDao;
import dto.UbicacionDTO;

public class Ubicacion {
	private int idUbicacion;
	private String calle;
	private int bloque;
	private int estante;
	private int posicion;
	private Articulo articulo;
	private Lote lote;
	private int cantidadFisica;
	
	public int getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getBloque() {
		return bloque;
	}
	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public int getCantidadFisica() {
		return cantidadFisica;
	}
	
	/**
	 * Si la cantidad baja a 0 se remueve la asociacion al articulo
	 * @param cantidadFisica
	 */
	public void setCantidadFisica(int cantidadFisica) {
		this.cantidadFisica = cantidadFisica;
		if(cantidadFisica==0) {
			articulo=null;
			//TODO borrar Lote asociado
		}
	}
	
	public Ubicacion guardar() {
		return UbicacionDao.getInstance().grabar(this);
	}
	
	public UbicacionDTO toDTO() {
		return new UbicacionDTO(idUbicacion, calle, bloque, estante, posicion);
	}
	
}
