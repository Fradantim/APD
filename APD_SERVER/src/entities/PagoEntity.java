package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import dao.FacturaDao;
import model.Pago;

@Entity  
@DiscriminatorValue("PAGO")
public class PagoEntity extends MovimientoCtaCteEntity{

	@ManyToMany
	@JoinColumn(name="idFactura")	
	private List<FacturaEntity> facturasPagadas;

	public PagoEntity(Pago p) {
		super(p.getIdMovimientoCtaCte(), p.getFecha(), p.getImporte(), new ClienteEntity(p.getCliente()));
		facturasPagadas= new ArrayList<>();
		
		//traer todas las facturas del pago
		//facturasPagadas.addAll(FacturaDao.ge)
		facturasPagadas.add(new FacturaEntity(p.getFactura()));
	}
	
}
