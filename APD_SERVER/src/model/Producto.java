package model;

import dao.ProductoDao;
import dao.ProveedorDao;
import dto.ProductoDTO;
import dto.ProveedorDTO;
import exception.ObjetoInexistenteException;

public class Producto {

	private float precio;
	private Integer idPedido;
	private Integer idproducto;
	private Integer idArticulo;
	
	public Producto(Integer pedido, Integer prod, Float pre , Integer art) {
		 this.idArticulo = art;
		 this.idPedido = pedido;
		 this.idproducto = prod;
		 this.precio = pre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public void guardar() throws ObjetoInexistenteException {
		ProductoDao.getInstance().grabar(this);
	}
	
	public ProductoDTO toDTO() {
		return new ProductoDTO(idPedido, idproducto,idArticulo,precio);
	}

}
