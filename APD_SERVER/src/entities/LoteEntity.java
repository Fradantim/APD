package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.Lote;
import model.Proveedor;


@Entity
@Table(name="LOTE")
public class LoteEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="IdLote")
	private Integer idLote; 
	@Column (name="FechaVencimiento", nullable= true)
	private Date fechaVencimiento;
	
	@OneToOne
	@JoinColumn(name="IdLote")
	private UbicacionEntity ubicacion;
  
  public LoteEntity() {}
  
  public LoteEntity(Integer IdLote, Date Fecha){
		super();
		this.fechaVencimiento = Fecha;
	}
	
	public LoteEntity(Lote lot) {
		super();
		this.idLote=(lot.getId()==0)? null : lot.getId();
		this.fechaVencimiento = lot.getFechaVencimiento();		
	}
	
	public Lote toNegocio(){
		Lote lot =  new Lote(fechaVencimiento);
		lot.setId(idLote);
		return lot;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
