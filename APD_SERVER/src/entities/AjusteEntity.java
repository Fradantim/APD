package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
		super(ajuste.getCantidad(), new ArticuloEntity(ajuste.getArticulo()));
		this.ubicacionId = ajuste.getUbicacion();
	}
	
	public Ajuste toNegocio(){
		Ajuste ajuste= new Ajuste(idMovimiento, cantidad, ubicacionId);
		ajuste.setArticulo(articulo.toNegocio());
		return ajuste;
	}
}
