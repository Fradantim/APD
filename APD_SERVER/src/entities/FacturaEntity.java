package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import model.Factura;

@Entity  
@DiscriminatorValue("FACTURA")  
public class FacturaEntity extends MovimientoCtaCteEntity{
	
	@Column (name="Bonificacion")
	private int bonificacion;
	@Column (name="ESTADO")
	private String estado;
	
	@ManyToMany()
	@JoinTable(
	     name = "FACTURAS_PAGOS",
	     joinColumns = @JoinColumn(name = "FACTURA_ID"),
	     inverseJoinColumns = @JoinColumn(name = "PAGO_ID")
	    )
	private List<PagoEntity> pagosAsociados;
	
	public FacturaEntity() {}
	
	public FacturaEntity(int idMovimientoCtaCte, Date fecha, String detalle, float importe,
			ClienteEntity cliente, int bonificacion, String estado) {
		super(idMovimientoCtaCte, fecha, importe,cliente);
		this.bonificacion = bonificacion;
		this.estado = estado;
	}
	
	public FacturaEntity(Factura factura) {
		super(factura.getIdMovimientoCtaCte(), factura.getFecha(), factura.getImporte(), new ClienteEntity(factura.getCliente()));
		this.bonificacion = factura.getBonificacion();
		this.estado = factura.getEstado();
	}
	
	public int getBonificacion() {
		return bonificacion;
	}
	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Factura toNegocio(){
		Factura factura = new Factura(fecha,bonificacion);
		factura.setCliente(cliente.toNegocio());
		factura.setIdMovimientoCtaCte(idMovimientoCtaCte);
		factura.setEstado(estado);
		factura.setImporte(importe);
		return factura;
	}
}
