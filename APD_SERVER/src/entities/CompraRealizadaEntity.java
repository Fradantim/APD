package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.CompraRealizada;

@Entity  
@DiscriminatorValue("COMPRA")  
public class CompraRealizadaEntity extends MovimientoInventarioEntity {
	@Column (name="idPedidoCliente", nullable=true)
	private int pedidoCte;

	public int getPedidoCte() {
		return pedidoCte;
	}

	public void setPedidoCte(int pedidoCte) {
		this.pedidoCte = pedidoCte;
	}
	
	public CompraRealizadaEntity() { }
	
	public CompraRealizadaEntity(CompraRealizada compra) {
		super(compra.getCantidad(),new ArticuloEntity(compra.getArticulo()));
		this.pedidoCte = compra.getPedidoCte();
	}
	
	public CompraRealizada toNegocio(){
		CompraRealizada compra = new CompraRealizada(cantidad, pedidoCte);
		compra.setIdMovimiento(idMovimiento);
		compra.setArticulo(articulo.toNegocio());
		return compra;
	}
	
}
