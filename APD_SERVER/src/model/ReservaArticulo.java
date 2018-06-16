package model;

import dao.ArticuloDao;
import dao.OrdenDeCompraDao;
import dao.PedidoCteDao;
import dao.ReservaArticuloDao;
import dto.ReservaArticuloDTO;
import exception.ObjetoInexistenteException;

public class ReservaArticulo {

	private int idReserva;
	private int cantidad;
	private String estado;
	private Articulo articulo;
	private PedidoCte pedido;
	private OrdenDeCompra orden;
	
	public ReservaArticulo() {	}
	
	public ReservaArticulo(int cant, String estado, String codart, Integer idpedido,
			 int ordenCompra) throws ObjetoInexistenteException {
		super();
		this.cantidad= cant;
		this.estado=estado;
		this.articulo = ArticuloDao.getInstance().getByCodArt(codart);
		this.pedido = PedidoCteDao.getInstance().getById(idpedido);
		this.orden = OrdenDeCompraDao.getInstance().getById(ordenCompra);
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public PedidoCte getPedido() {
		return pedido;
	}
	public void setPedido(PedidoCte pedido) {
		this.pedido = pedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void guardar() throws ObjetoInexistenteException {
		ReservaArticuloDao.getInstance().grabar(this);
	}
	
	
	public ReservaArticuloDTO toDTO() throws ObjetoInexistenteException {
		return new ReservaArticuloDTO(this.idReserva,this.cantidad, this.estado, this.articulo.getId(), this.pedido.getIdPedidoCliente(),this.orden.getIdOrdenCompra());
	}


	public OrdenDeCompra getOrden() {
		return orden;
	}

	public void setOrden(OrdenDeCompra orden) {
		this.orden = orden;
	}

}
