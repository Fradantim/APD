package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.Lote;
import model.Ubicacion;

@Entity
@Table(name="LOTE")
public class LoteEntity {
 
  @Id 
  @GeneratedValue(strategy = GenerationType.AUTO)
  @OneToOne  
  @JoinColumn(name="IdLote")
  @Column (name="IdLote", unique=true)
  private Integer idLote;
  @Column (name="FechaVencimiento", nullable= true)
  private Date fechaVencimiento;
  
  @OneToOne
  @JoinColumn(name="LoteId_Lote")
  private UbicacionEntity ubicacion;
  
  public LoteEntity() {}
  
  public LoteEntity(Integer IdLote, Date Fecha){
		super();
		this.idLote = IdLote;
		this.fechaVencimiento = Fecha;
	}
	
	public LoteEntity(Lote lot) {
		super();
		this.idLote = lot.getIdLote();
		this.fechaVencimiento = lot.getFechaVencimiento();		
	}
	
	public Lote toNegocio(){
		return new Lote(idLote,fechaVencimiento);
	}

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public UbicacionEntity getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionEntity ubicacion) {
		this.ubicacion = ubicacion;
	} 
  
}
