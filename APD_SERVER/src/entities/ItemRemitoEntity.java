package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.ItemPedidoCte;
import model.ItemRemito;
import model.Remito;

@Entity
@Table(name="ITEMREMITOS")
public class ItemRemitoEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdItemRemito;
	@Column (name="Cantidad")
	private int Cantidad;
	@Column (name="IdRemito")
	private int IdRemito;
	@Column (name="Articulo_CodigoDeBarras")
	private String ArtCodigoDeBarras;
	
	public ItemRemitoEntity() {}
	
	public ItemRemitoEntity(int itemRemitoEntity, Date fecha, int idRemito, int cantidad, String artCodBarras) {
		super();
		this.IdItemRemito = itemRemitoEntity;
		this.Cantidad = cantidad;
		this.IdRemito = idRemito;
		this.ArtCodigoDeBarras = artCodBarras;
	}
	
	public ItemRemitoEntity(ItemRemito itemRemito) {
		this.IdItemRemito = itemRemito.getIdItem();
		this.Cantidad = itemRemito.getCantidad();
		this.IdRemito = itemRemito.getIdRemito();
		this.ArtCodigoDeBarras = itemRemito.getArticulo().getCodDeBarras();
	}
	
	public ItemRemito toNegocio(){
		return new ItemRemito(ArtCodigoDeBarras, IdRemito, Cantidad);
	}

	public int getIdItemRemito() {
		return IdItemRemito;
	}

	public void setIdItemRemito(int idItemRemito) {
		IdItemRemito = idItemRemito;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public int getIdRemito() {
		return IdRemito;
	}

	public void setIdRemito(int idRemito) {
		IdRemito = idRemito;
	}

	public String getArtCodigoDeBarras() {
		return ArtCodigoDeBarras;
	}

	public void setArtCodigoDeBarras(String artCodigoDeBarras) {
		ArtCodigoDeBarras = artCodigoDeBarras;
	}
}
