package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="MovimientoCtaCte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="Tipo",discriminatorType=DiscriminatorType.STRING)
public abstract class MovimientoCtaCteEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column (name="idMovimientoCtaCte")
	protected int idMovimientoCtaCte;
	@Column (name="Fecha")
	protected Date fecha;
	@Column (name="Detalle")
	protected String detalle;
	@Column (name="Importe")
	protected float importe;
	@Column (name="CtaCte_IdCtaCte")
	protected Integer cuentaCliente;
	
	public MovimientoCtaCteEntity() {
	}
	
	public MovimientoCtaCteEntity(int idMovimientoCtaCte, Date fecha, String detalle, float importe,
			Integer cuentaCliente) {
		super();
		this.idMovimientoCtaCte = idMovimientoCtaCte;
		this.fecha = fecha;
		this.detalle = detalle;
		this.importe = importe;
		this.cuentaCliente = cuentaCliente;
	}
	
	public int getIdMovimientoCtaCte() {
		return idMovimientoCtaCte;
	}
	public void setIdMovimientoCtaCte(int idMovimientoCtaCte) {
		this.idMovimientoCtaCte = idMovimientoCtaCte;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Integer getCuentaCliente() {
		return cuentaCliente;
	}
	public void setCuentaCliente(Integer cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}
	
	
	
}
