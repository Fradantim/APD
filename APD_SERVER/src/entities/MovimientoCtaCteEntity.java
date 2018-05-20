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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MovimientoCtaCte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="Tipo",discriminatorType=DiscriminatorType.STRING)
public abstract class MovimientoCtaCteEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column (name="idMovimientoCtaCte")
	protected Integer idMovimientoCtaCte;
	@Column (name="Fecha")
	protected Date fecha;
	@Column (name="Importe")
	protected float importe;

	@ManyToOne
	@JoinColumn (name="id")
	protected ClienteEntity cliente;

	public MovimientoCtaCteEntity() {}
	
	public MovimientoCtaCteEntity(int idMovimientoCtaCte, Date fecha, float importe,
			ClienteEntity cliente) {
		super();
		this.idMovimientoCtaCte= idMovimientoCtaCte==0 ? null : idMovimientoCtaCte;
		this.fecha = fecha;
		this.importe = importe;
		this.cliente = cliente;
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
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
}
