package entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import exception.ObjetoInexistenteException;
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
	
	@Column(name="id_cliente")
 	private Integer Cli;
	
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
	
	public PedidoCteEntity(Integer idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, Integer numero) throws ObjetoInexistenteException 
	{	
		super();
		this.FechaGeneracion = new Date();
		this.FechaDespacho = null;
		this.FechaRecepcion = null;
		this.total = 0.00F;
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
		this.Cli=idCli;
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
		this.Cli=pedido.getCliente().getIdCliente();
		
	}


	public PedidoCte toNegocio() throws ObjetoInexistenteException {
		PedidoCte pedido= new PedidoCte(Cli, pais, provincia, partido,codpostal,calle,alt , piso, numero);
		pedido.setIdPedidoCliente(IdPedidoCte);
		return pedido;
		
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
