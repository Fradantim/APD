package model;

public class ReservaArticulo {
	public static final String STATUS_PENDIENTE="Reserva pendiente";
	public static final String STATUS_CUMPLIDA="Reserva cumplida";
	
	private int idReserva;
	private Articulo articulo;
	private PedidoCte pedido;
	private int cantidad;
	private String estado;
	
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

	public void guardar() {
		
	}
	
}
