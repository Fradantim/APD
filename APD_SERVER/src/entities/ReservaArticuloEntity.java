package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dao.ArticuloDao;
import dao.OrdenDeCompraDao;
import dao.PedidoCteDao;
import exception.ObjetoInexistenteException;
import model.ReservaArticulo;

@Entity
@Table (name="RESERVAS")
public class ReservaArticuloEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_reserva", unique=true, updatable = false, nullable = false)
	private Integer idReservaArticulo;
	@Column(name="cantidad")
	private Integer Cant;
	@Column(name="estado")
	private String Estado;
	
	@OneToOne 
	@JoinColumn(name="id_Articulo")
	private ArticuloEntity articulo;

	@ManyToOne
	@JoinColumn(name="id_pedido")
	private PedidoCteEntity pedidoReserva;
	
	@ManyToOne
	@JoinColumn(name="id_OrdenCompra", nullable=true)
	private OrdenDeCompraEntity ordenCompra;

	public ReservaArticuloEntity() {}

	public ReservaArticuloEntity(int cant, String estado, Integer idart, Integer idpedido,
			 int ordenCompra) throws ObjetoInexistenteException {
		super();
		this.Cant = cant;
		this.Estado = estado;
		this.articulo = ArticuloDao.getInstance().getByIdProd(idart);
		this.pedidoReserva = PedidoCteDao.getInstance().getByIdPed(idpedido);
		this.ordenCompra = OrdenDeCompraDao.getInstance().getByIdEntity(ordenCompra);
	}


	public ReservaArticuloEntity(ReservaArticulo reserva) throws ObjetoInexistenteException {
		super();
		this.Cant = reserva.getCantidad();
		this.Estado = reserva.getEstado();
		this.articulo = ArticuloDao.getInstance().getByIdProd(reserva.getArticulo().getId());
		this.pedidoReserva = PedidoCteDao.getInstance().getByIdPed(reserva.getPedido().getIdPedidoCliente());
		this.ordenCompra = OrdenDeCompraDao.getInstance().getByIdEntity(reserva.getOrden().getIdOrdenCompra());
	}
	
	public ReservaArticulo toNegocio() throws ObjetoInexistenteException{
		ReservaArticulo reserva =null;
		reserva = new ReservaArticulo(Cant ,Estado, this.articulo.getCodDeBarras(), this.pedidoReserva.getIdPedidoCte(),this.ordenCompra.getId());
		reserva.setIdReserva(idReservaArticulo);
		return reserva;
	}

	
	
	public int getIdReservaArticulo() {
		return idReservaArticulo;
	}

	public void setIdReservaArticulo(int idReservaArticulo) {
		this.idReservaArticulo = idReservaArticulo;
	}

	public int getCant() {
		return Cant;
	}

	public void setCant(int cant) {
		Cant = cant;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public PedidoCteEntity getPedido() {
		return pedidoReserva;
	}

	public void setPedido(PedidoCteEntity pedido) {
		this.pedidoReserva = pedido;
	}

	public OrdenDeCompraEntity getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenDeCompraEntity ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public PedidoCteEntity getPedidoReserva() {
		return pedidoReserva;
	}

	public void setPedidoReserva(PedidoCteEntity pedidoReserva) {
		this.pedidoReserva = pedidoReserva;
	}

	public void setIdReservaArticulo(Integer idReservaArticulo) {
		this.idReservaArticulo = idReservaArticulo;
	}

	public void setCant(Integer cant) {
		Cant = cant;
	}

	
}
