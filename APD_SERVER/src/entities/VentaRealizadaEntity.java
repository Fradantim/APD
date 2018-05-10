package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dao.ArticuloDao;
import exception.ObjetoInexistenteException;
import model.VentaRealizada;

@Entity  
@DiscriminatorValue("VENTA") 
public class VentaRealizadaEntity extends MovimientoInventarioEntity{
	
	@Column (name="idFactura", nullable=true)
	private int idFactura;

	public VentaRealizadaEntity() {}
	
	public VentaRealizadaEntity(VentaRealizada venta) {
		super(venta.getCantidad(), venta.getArticulo().getCodDeBarras());
		this.idFactura = venta.getIdFactura();

	}
	
	public VentaRealizada toNegocio() throws ObjetoInexistenteException {
		VentaRealizada venta= new VentaRealizada(cantidad,idFactura, ArticuloDao.getInstance().getById(articuloCodDeBarra));
		return venta;
	}
	
	
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
}
