package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import model.Pago;

@Entity  
@DiscriminatorValue("PAGO")
public class PagoEntity extends MovimientoCtaCteEntity{

	@Column (name="especie")
	private String especie;
	
	@ManyToMany()
	private List<FacturaEntity> facturasPagadas;

	public PagoEntity() {	}

	public PagoEntity(Pago p) {
		super(p.getIdMovimientoCtaCte(), p.getFecha(), p.getImporte(), new ClienteEntity(p.getCliente()));
		facturasPagadas= new ArrayList<>();
		especie=p.getEspecie();
		
		//TODO traer todas las facturas del pago ?
		//facturasPagadas.addAll(FacturaDao.ge)
		//facturasPagadas.add(new FacturaEntity(p.getFactura()));
	}
	
	public Pago toNegocio() {
		return new Pago(getFecha(), getImporte(), especie);
	}
	
}
