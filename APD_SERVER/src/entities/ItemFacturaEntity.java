package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.ArticuloDao;
import dao.FacturaDao;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Factura;
import model.ItemFactura;
import model.ItemPedidoCte;

@Entity
@Table (name="ITEMS_FACTURA")
public class ItemFacturaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Item_Factura", updatable= false, nullable= false)
	private Integer idItem;
	@Column(name="IDArticulo")
	private String codDeBarrasArticulo;
	@Column(name="cantidad")
	private int cantidad;
	@Column(name="IDFactura")	
	private Integer facturaId;
	
	public ItemFacturaEntity() {	}
	
	public ItemFacturaEntity(String codDeBarrasArticulo, Integer cantidad, Integer facturaId) {	
 		this.cantidad=cantidad;
 		this.codDeBarrasArticulo=codDeBarrasArticulo; 
		this.facturaId=facturaId;
	}
	
	public ItemFacturaEntity(ItemPedidoCte itemped, Factura factura) {	
		this.cantidad=itemped.getCantidad();
		this.codDeBarrasArticulo=itemped.getArticulo().getCodDeBarras();
		this.facturaId=factura.getIdMovimientoCtaCte();
	}

	public ItemFacturaEntity(ItemFactura itemFactura) {
		this.cantidad=itemFactura.getCantidad();
		this.codDeBarrasArticulo=itemFactura.getArticulo().getCodDeBarras();
		this.facturaId=itemFactura.getFactura().getIdMovimientoCtaCte();
	}

	public ItemFactura toNegocio() throws ObjetoInexistenteException {
		ItemFactura item= new ItemFactura(idItem, ArticuloDao.getInstance().getById(codDeBarrasArticulo), cantidad, FacturaDao.getInstance().getById(facturaId));
		return item;
		
	}
}
