package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@ManyToOne
	@JoinColumn(name="idMovimientoCtaCte")	
	private FacturaEntity factura;
	
	@ManyToOne
	@JoinColumn(name="ArticuloId")	
	private ArticuloEntity articulo;
	
	public ItemFacturaEntity() {	}
	
	public ItemFacturaEntity(String codDeBarrasArticulo, Integer cantidad, FacturaEntity factura) {	
 		this.cantidad=cantidad;
 		this.codDeBarrasArticulo=codDeBarrasArticulo; 
		this.factura=factura;
	}
	
	public ItemFacturaEntity(ItemPedidoCte itemped, Factura factura) {	
		this.cantidad=itemped.getCantidad();
		this.codDeBarrasArticulo=itemped.getArticulo().getCodDeBarras();
		this.factura= new FacturaEntity(factura);
	}

	public ItemFacturaEntity(ItemFactura itemFactura) {
		this.cantidad=itemFactura.getCantidad();
		this.codDeBarrasArticulo=itemFactura.getArticulo().getCodDeBarras();
		this.factura=new FacturaEntity(itemFactura.getFactura());
	}

	public ItemFactura toNegocio() {
		ItemFactura item= new ItemFactura(idItem,articulo.toNegocio(), cantidad, factura.toNegocio());
		return item;
		
	}
}
