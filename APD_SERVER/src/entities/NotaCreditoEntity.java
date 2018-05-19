package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import model.NotaCredito;

@Entity  
@DiscriminatorValue("NOTACREDITO")
public class NotaCreditoEntity extends MovimientoCtaCteEntity{
	//TODO 0haciendo
	
	@OneToOne
	@JoinColumn(name="idMovimientoCtaCte")	
	private FacturaEntity facturaBonificada;

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
		if(facturaBonificada !=null)
			return new NotaCredito(getFecha(), getImporte(), facturaBonificada.toNegocio());
		else
			return new NotaCredito(getFecha(), getImporte(), null);
	}
}
