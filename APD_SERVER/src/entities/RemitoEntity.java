package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import dao.ArticuloDao;
import dao.ClienteDao;
import exception.ObjetoInexistenteException;
import model.Cliente;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Remito;

@Entity
@Table(name="REMITOS")
public class RemitoEntity {
	@Id 
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_remito", unique=true, updatable = false, nullable = false)
	private Integer idRemito;
	
	@Column (name="fecha", nullable=true)
	private Date fecha;
	
	@OneToOne
	@JoinColumn(name="id_pedido")
 	private PedidoCteEntity ped;
	
	@OneToMany()
	@JoinColumn(name="id_remito")
	private List<ItemRemitoEntity> itemsRemito ;
	
	public RemitoEntity() {	}
	
	public RemitoEntity(Integer idPed) throws ObjetoInexistenteException 
	{	
		super();
		this.fecha = new Date();
		this.ped.setIdPedidoCte(idPed);
	}
	
	public RemitoEntity(Remito remito) throws ObjetoInexistenteException {
		super();
		this.idRemito = remito.getIdRemito()==0 ? null : remito.getIdRemito();
		this.fecha = remito.getFecha();
		PedidoCteEntity pedidoEntity = new PedidoCteEntity(remito.getPedido());
		this.ped = pedidoEntity;
	}
	
	public PedidoCteEntity getPed() {
		return ped;
	}

	public void setPed(PedidoCteEntity ped) {
		this.ped = ped;
	}

	public Remito toNegocio() {
		Remito remito= new Remito(this.fecha, this.ped.toNegocio());
		remito.setIdRemito(idRemito);
		return remito;
		
	}

	public Integer getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(Integer idRemito) {
		this.idRemito = idRemito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ItemRemitoEntity> getItemsRemito() {
		return itemsRemito;
	}

	public void setItemsRemito(List<ItemRemitoEntity> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}
}
