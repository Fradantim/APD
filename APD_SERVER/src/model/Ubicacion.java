package model;

import dao.UbicacionDao;
import dto.UbicacionDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;

public class Ubicacion {
	private int idUbicacion;
	private String calle;
	private int bloque;
	private int estante;
	private int posicion;
	private Articulo articulo;
	private Lote lote;
	private int cantidadFisica;
	
	public Ubicacion(int IdUbicacionArticulo, String Calle, int Bloque, int Estante, int Posicion,
			int CantidadFisica){
		super();
		this.idUbicacion = IdUbicacionArticulo;
		this.calle = Calle;
		this.bloque = Bloque;
		this.estante = Estante;
		this.cantidadFisica= CantidadFisica;
	}
	
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
	

	public void setCantidadFisica(int cantidadFisica) {
		this.cantidadFisica = cantidadFisica;
	}
	
	public Integer guardar() {
		this.idUbicacion=UbicacionDao.getInstance().grabar(this);
		return this.idUbicacion;
	}
	
	public UbicacionDTO toDTO() {
		return new UbicacionDTO(idUbicacion, calle, bloque, estante, posicion);
	}
	
	
	/**
	 * Si la cantidad baja a 0 se remueve la asociacion al articulo y se guarda la ubicacion
	 * @param cantidadFisica
	 * @throws LaUbicacionNoTieneEsteArticuloException 
	 * @throws LaUbicacionNoTieneSuficientesArticulosParaRemoverException 
	 * @throws SuperaLaCantidadUbicableEnLaUbicacionException 
	 */
	public void ajustarStock(Articulo art, int cantidad) throws LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		if(articulo!=null) {
			if(!articulo.equals(art)) {
				throw new LaUbicacionNoTieneEsteArticuloException("En la ubicacion "+idUbicacion+" no se pueden agregar articulos "+art.getCodDeBarras()+" por que ya posee articulos "+articulo.getCodDeBarras());
			}
		}
		
		if(cantidad>0) {
			//Agrego items
			if(cantidad>0)
			if (articulo== null)
				articulo=art;
			if(cantidadFisica+cantidad>art.getCantidadUbicable()) {
				throw new SuperaLaCantidadUbicableEnLaUbicacionException("No pueden agregarse "+cantidad+" articulos "+
						art.getCodDeBarras()+" a la ubicacion "+idUbicacion+" por que ya tiene "+cantidadFisica+
						" y superaria el limite de la cantidad ubicable del articulo de "+art.getCantidadUbicable()+ " articulos.");
			}
		}else {
			//remuevo items
			if(cantidad>cantidadFisica) {
				throw new LaUbicacionNoTieneSuficientesArticulosParaRemoverException("En la ubicacion "+idUbicacion+" no se pueden remover "+cantidad+" articulos por que posee "+cantidadFisica);
			}
		}
		cantidadFisica=cantidadFisica+cantidad;
		
		if(cantidadFisica<=0) {
			articulo=null;
			lote=null;
		}
		guardar();
	}
	
}
