package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dao.ArticuloDao;
import exception.ObjetoInexistenteException;
import model.Ajuste;

@Entity  
@DiscriminatorValue("AJUSTE")  
public class AjusteEntity extends MovimientoInventarioEntity{

	@Column (name="lugar", nullable=true)
	private int ubicacionId;

	public int getUbicacionId() {
		return ubicacionId;
	}

	public void setUbicacionId(int ubicacionId) {
		this.ubicacionId = ubicacionId;
	}

	public AjusteEntity() {}
	
	public AjusteEntity(Ajuste ajuste) {
		super(ajuste.getCantidad(), ajuste.getArticulo().getCodDeBarras());
		this.ubicacionId = ajuste.getUbicacion();
	}
	
	public Ajuste toNegocio() throws ObjetoInexistenteException {
		Ajuste ajuste= new Ajuste(idMovimiento, cantidad, ubicacionId, ArticuloDao.getInstance().getById(articuloCodDeBarra));
		//ajuste.setIdMovimiento(idMovimiento);
		return ajuste;
	}
}
