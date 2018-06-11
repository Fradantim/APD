package entities;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import exception.ObjetoInexistenteException;
import model.ItemPedidoCte;
import model.PedidoCte;


@Entity
@Table(name="PEDIDOS")
public class PedidoCteEntity {
 
	@Id 
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_pedido", unique=true, updatable = false, nullable = false)
	private Integer IdPedidoCte;
	@Column (name="fecha_generacion", nullable=true)
	private Date FechaGeneracion;
	@Column (name="fecha_despacho", nullable=true)
	private Date FechaDespacho;
	@Column (name="fecha_recepcion", nullable=true)
	private Date FechaRecepcion;
	@Column (name="total", nullable=true)
	private Float total;
	@Column (name="estado_pedido", nullable=true)
	private String EstadoPedido;
	
	@ManyToOne()
	@JoinColumn(name="id_cliente",nullable= false)
 	private ClienteEntity Cli;
	
	@Transient
	private ClienteEntity aux;

	@OneToOne
	@JoinColumn(name="id_pedido",nullable= false)
 	private RemitoEntity rem;
	
	@Column(name="pais_pedido", nullable=true) 
	private String pais;
	@Column(name="provincia_pedido", nullable=true) 
	private String provincia;
	@Column(name="partido_pedido", nullable=true) 
	private String partido;
	@Column(name="codigo_postal", nullable=true) 
	private String codpostal;
	@Column(name="calle_direccion", nullable=true) 
	private String calle;
	@Column(name="altura_calle", nullable=true) 
	private String alt;
	@Column(name="piso_depto", nullable=true) 
	private String piso;
	@Column(name="nro_depto", nullable=true) 
	private Integer numero;
	@Column(name="motivo", nullable=true) 
	private String motivo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	@JoinColumn(name="id_pedido", nullable=false)
	private List <ItemPedidoCteEntity> itemsPedido ;
	
	@OneToMany (cascade=CascadeType.ALL, mappedBy = "pedidoReserva")
	@JoinColumn(name="id_pedido")
	private List <ReservaArticuloEntity> reservaspedido ;

	
	public PedidoCteEntity() {	}
	
	public PedidoCteEntity(Integer idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, Integer numero) throws ObjetoInexistenteException 
	{	
		super();
		this.FechaGeneracion = new Date();
		this.FechaDespacho = null;
		this.FechaRecepcion = null;
		this.total = this.getTotal();
		this.EstadoPedido = "Nuevo";
	 	this.pais = pais;
		this.provincia = provincia ;
		this.partido = partido;
		this.codpostal = codigoPostal;
		this.calle = calle;
		this.alt = altura;
		this.piso = piso;
		this.numero = numero;
		this.motivo=null;
		this.Cli.setId(idCli);
	}

			
			
	public PedidoCteEntity(PedidoCte pedido) throws ObjetoInexistenteException {
		super();
		this.IdPedidoCte = pedido.getIdPedidoCliente();
		this.FechaGeneracion = pedido.getFechaGeneracion();
		this.FechaDespacho = pedido.getFechaDespacho() ;
		this.FechaRecepcion = pedido.getFechaRecepcion();
       	this.total = pedido.getTotalbruto();
      	this.EstadoPedido = pedido.getEstado() ;
		this.pais = pedido.getPais() ;
		this.provincia =  pedido.getProvincia();
		this.partido = pedido.getPartido();
		this.codpostal = pedido.getCodigoPostal();
		this.calle = pedido.getCalle();
		this.alt = pedido.getAltura();
		this.piso = pedido.getPiso();
		this.numero = pedido.getNumero();
		this.aux = new ClienteEntity(pedido.getCliente());
		this.Cli = aux;
		this.Cli.setId(pedido.getCliente().getIdCliente());

		
	}


	public PedidoCte toNegocio(){
		PedidoCte pedido =null;
		try {
			pedido = new PedidoCte(this.Cli.getId(), pais, provincia, partido,codpostal,calle,alt , piso, numero);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		pedido.setIdPedidoCliente(IdPedidoCte);
		return pedido;
		
	}

	public ClienteEntity getCli() {
		return Cli;
	}

	public void setCli(ClienteEntity cli) {
		Cli = cli;
	}

	public RemitoEntity getRem() {
		return rem;
	}

	public void setRem(RemitoEntity rem) {
		this.rem = rem;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<ItemPedidoCteEntity> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(List<ItemPedidoCteEntity> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public List<ReservaArticuloEntity> getReservaspedido() {
		return reservaspedido;
	}

	public void setReservaspedido(List<ReservaArticuloEntity> reservaspedido) {
		this.reservaspedido = reservaspedido;
	}

	public Integer getIdPedidoCte() {
		return IdPedidoCte;
	}

	public void setIdPedidoCte(Integer idPedidoCte) {
		this.IdPedidoCte = idPedidoCte;
	}

	public Date getFechaGeneracion() {
		return FechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.FechaGeneracion = fechaGeneracion;
	}

	public Date getFechaDespacho() {
		return FechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.FechaDespacho = fechaDespacho;
	}

	public Date getFechaRecepcion() {
		return FechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.FechaRecepcion = fechaRecepcion;
	}

	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getEstadoPedido() {
		return EstadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.EstadoPedido = estadoPedido;
	}


	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
