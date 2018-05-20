package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import model.NotaCredito;

@Entity  
@DiscriminatorValue("NOTACREDITO")
public class NotaCreditoEntity extends MovimientoCtaCteEntity{

	@OneToOne
	@JoinColumn(name="idFacturaBonificada")	
	private FacturaEntity facturaBonificada;

	public NotaCreditoEntity() {	}
	
	public NotaCreditoEntity(NotaCredito nc) {
		super(nc.getIdMovimientoCtaCte(), nc.getFecha(), nc.getImporte(), new ClienteEntity(nc.getCliente()));
		if(nc.getFacturaBonificada() != null)
			this.facturaBonificada = new FacturaEntity(nc.getFacturaBonificada());
	}

	public FacturaEntity getFacturaBonificada() {
		return facturaBonificada;
	}

	public void setFacturaBonificada(FacturaEntity facturaBonificada) {
		this.facturaBonificada = facturaBonificada;
	}

	public NotaCredito toNegocio() {
		NotaCredito nc =new NotaCredito(getFecha(), getImporte(), null);
		nc.setIdMovimientoCtaCte(idMovimientoCtaCte);
		if(facturaBonificada !=null) {
			nc.setFacturaBonificada(facturaBonificada.toNegocio());
		}
		return nc;
	}
}
