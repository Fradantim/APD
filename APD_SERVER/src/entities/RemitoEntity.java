package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.Articulo;
import model.Remito;

@Entity
@Table(name="REMITOS")
public class RemitoEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdRemito;
	@Column (name="Fecha")
	private Date Fecha;
	@Column (name="IdPedido")
	private int IdPedido;
	
	public RemitoEntity() {}
	
	public RemitoEntity(Date fecha, int idPedido) {
		super();
		this.Fecha = fecha;
		this.IdPedido = idPedido;
	}
	
	public RemitoEntity(Remito remito) {
		this.IdRemito = remito.getIdRemito();
		this.Fecha = remito.getFecha();
		this.IdPedido = remito.getIdPedido();
	}
	
	public Remito toNegocio(){
		return new Remito(IdRemito, Fecha, IdPedido);
	}

	public int getIdRemito() {
		return IdRemito;
	}

	public void setIdRemito(int idRemito) {
		IdRemito = idRemito;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public int getIdPedido() {
		return IdPedido;
	}

	public void setIdPedido(int idPedido) {
		IdPedido = idPedido;
	}
}
