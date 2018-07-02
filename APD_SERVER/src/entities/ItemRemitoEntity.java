package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.ArticuloDao;
import dao.PedidoCteDao;
import dao.RemitoDao;
import exception.ObjetoInexistenteException;
import model.ItemPedidoCte;
import model.ItemRemito;

@Entity
@Table (name="ITEMS_REMITO")
public class ItemRemitoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Item_Remito", unique=true, updatable = false, nullable = false)
	private Integer idItem;

	@Column(name="cantidad")
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name="ArticuloId")
	private  ArticuloEntity articulo;
	
	@ManyToOne 
	@JoinColumn(name="id_remito", nullable = false )
	private RemitoEntity remito;
	
	public ItemRemitoEntity() {}
	
	public ItemRemitoEntity(String codDeBarrasArticulo, Integer cantidad, Integer idremito) throws ObjetoInexistenteException {	
 		this.cantidad=cantidad;
  		this.articulo= ArticuloDao.getInstance().getByCodigo(codDeBarrasArticulo); 
  		this.remito= RemitoDao.getInstance().getByIdRem(idremito);
	}
	
	public ItemRemitoEntity(ItemRemito itemrem) throws ObjetoInexistenteException {
		this.idItem= itemrem.getIdItem()==0 ? null : itemrem.getIdItem();
		this.cantidad=itemrem.getCantidad();
  		this.articulo= ArticuloDao.getInstance().getByCodigo(itemrem.getArticulo().getCodDeBarras());
  		this.remito= RemitoDao.getInstance().getByIdRem(itemrem.getRemito().getIdRemito());
	}
	
	public ItemRemito toNegocio() throws ObjetoInexistenteException {
		ItemRemito item= new ItemRemito(articulo.toNegocio(), cantidad, this.remito.toNegocio());
		item.setIdItem(idItem);
		return item;
	}
	
	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public RemitoEntity getRemito() {
		return remito;
	}

	public void setRemito(RemitoEntity remito) {
		this.remito = remito;
	}
}
