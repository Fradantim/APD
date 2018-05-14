package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Factura;

@Entity  
@DiscriminatorValue("FACTURA")  
public class FacturaEntity extends MovimientoCtaCteEntity{
	
	@Column (name="Bonificacion")
	private int bonificacion;
	@Column (name="ESTADO")
	private String estado;
	
	public FacturaEntity() {}
	
	public FacturaEntity(int idMovimientoCtaCte, Date fecha, String detalle, float importe,
			Integer cuentaCliente, int bonificacion, String estado) {
		super(idMovimientoCtaCte, fecha, detalle, importe,cuentaCliente);
		this.bonificacion = bonificacion;
		this.estado = estado;
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
		//TODO 0HACEME
		//return new Factura(fecha,bonificacion,Cta)
		return null;
	}
}
