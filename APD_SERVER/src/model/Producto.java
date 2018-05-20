package model;

import java.util.ArrayList;
import java.util.List;

import dao.ArticuloDao;
import dao.ProductoDao;
import exception.ObjetoInexistenteException;

public class Producto {

	private float precio;
	private Integer idProducto;
	private Articulo articulo;
	private List <Proveedor> proveedor ;
	
	public Producto(Float pre, Integer idart) throws ObjetoInexistenteException {
		 this.precio = pre;
		 this.articulo = ArticuloDao.getInstance().getByRealId(idart);
		 this.proveedor = new ArrayList<Proveedor>();
	}

	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public void guardar() throws ObjetoInexistenteException {
		ProductoDao.getInstance().grabar(this);
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public List <Proveedor> getProveedor() {
		return proveedor;
	}

	public void setProveedor(List <Proveedor> proveedor) {
		this.proveedor = proveedor;
	}
	
/*	public ProductoDTO toDTO() {
		return new ProductoDTO(idProducto,articulo,precio);
		
	}*/

}
