package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import model.Lote;

@Embeddable
public class LoteEntity {
 
  @Column (name="FechaVencimiento", nullable= true)
  private Date fechaVencimiento;
  
  public LoteEntity() {}
  
  public LoteEntity(Integer IdLote, Date Fecha){
		super();
		this.fechaVencimiento = Fecha;
	}
	
	public LoteEntity(Lote lot) {
		super();
		this.fechaVencimiento = lot.getFechaVencimiento();		
	}
	
	public Lote toNegocio(){
		return new Lote(fechaVencimiento);
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
