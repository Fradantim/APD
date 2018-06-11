package entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.UbicacionDTO;
import model.Ubicacion;

@Entity
@Table(name="UBICACION")
public class UbicacionEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="IdUbicacionArticulo")
	private Integer idUbicacionArticulo;
	@Column (name="Calle", nullable= true)
	private String calle;
	@Column (name="Bloque", nullable=true)
	private Integer bloque; //REVISAR BD
	@Column (name="Estante", nullable=true)
	private Integer estante;
	@Column (name="Posicion", nullable=true)
	private Integer posicion;
	@Column (name="CantidadFisica", nullable=true)
	private Integer cantidadFisica;
	
	@OneToOne
	@JoinColumn(name="IdLote")
	private LoteEntity lote;
	
	public LoteEntity getLoteAsociado() {
		return lote;
	}

	public void setLoteAsociado(LoteEntity loteAsociado) {
		this.lote = loteAsociado;
	}
	@ManyToOne
	@JoinColumn(name="ArticuloId", nullable=true)
	private ArticuloEntity articulo;
		
	public UbicacionEntity() {	}
	
	public UbicacionEntity(Integer IdUbicacionArticulo, String Calle, Integer Bloque, Integer Estante, Integer Posicion,
			Integer CantidadFisica){
		super();
		this.idUbicacionArticulo = IdUbicacionArticulo;
		this.calle = Calle;
		this.bloque = Bloque;
		this.estante = Estante;
		this.cantidadFisica= CantidadFisica;
	}
	
	public UbicacionEntity(Ubicacion ubi) {
		super();
		this.idUbicacionArticulo= ubi.getIdUbicacion()==0 ? null : ubi.getIdUbicacion();
		this.calle = ubi.getCalle();
		this.bloque = ubi.getBloque();
		this.estante = ubi.getEstante();
		this.cantidadFisica = ubi.getCantidadFisica();
		this.posicion=ubi.getPosicion();
		if(ubi.getArticulo()!=null)
			this.articulo=new ArticuloEntity(ubi.getArticulo());
	}
	
	public Ubicacion toNegocio(){
		return new Ubicacion(idUbicacionArticulo, calle, bloque, estante, posicion, cantidadFisica);
	}
	
	public Integer getIdUbicacionArticulo() {
		return idUbicacionArticulo;
	}

	public void setIdUbicacionArticulo(Integer idUbicacionArticulo) {
		this.idUbicacionArticulo = idUbicacionArticulo;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getBloque() {
		return bloque;
	}

	public void setBloque(Integer bloque) {
		this.bloque = bloque;
	}

	public int getEstante() {
		return estante;
	}

	public void setEstante(int estante) {
		this.estante = estante;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getCantidadFisica() {
		return cantidadFisica;
	}

	public void setCantidadFisica(Integer cantidadFisica) {
		this.cantidadFisica = cantidadFisica;
	}

	public ArticuloEntity getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloEntity Art) {
		this.articulo=Art;
	}
	

	

}
