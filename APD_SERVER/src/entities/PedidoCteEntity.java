package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import exception.ObjetoInexistenteException;
import model.PedidoCte;


@Entity
@Table(name="PEDIDOS")
public class PedidoCteEntity {
 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_pedido", unique=true)
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cliente")
 	private ClienteEntity Cli;
	@Transient
	private ClienteEntity aux;
	
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

	
	
	public PedidoCteEntity() {	}
	
	public PedidoCteEntity(Integer IdPedCte, Date FecGeneracion, Date FecDespacho, Date FecRecepcion,
			   float tot,	String EstadoPed, ClienteEntity cliente, String pa, String prov,
			   String part,	String cp,	String cal,	String altura,	 String pi, Integer num,String motivo) 
	{
		super();
		this.IdPedidoCte = IdPedCte;
		this.FechaGeneracion = FecGeneracion;
		this.FechaDespacho = FecDespacho;
		this.FechaRecepcion = FecRecepcion;
		this.total = tot;
		this.EstadoPedido = EstadoPed;
	 	this.pais = pa;
		this.provincia = prov ;
		this.partido = part;
		this.codpostal = cp;
		this.calle = cal;
		this.alt = altura;
		this.piso = pi;
		this.numero = num;
		this.Cli = cliente;
		this.motivo=motivo;
	}

	public PedidoCteEntity(PedidoCte pedido) {
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

	public PedidoCte toNegocio() throws ObjetoInexistenteException {
		return new PedidoCte(IdPedidoCte,this.Cli.getId(), FechaDespacho, FechaGeneracion, FechaRecepcion,total, pais, provincia, partido,codpostal,calle,alt , piso, numero,EstadoPedido,motivo);
		
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
		return total;
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
