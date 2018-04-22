package model;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import exception.ClienteInexistenteException;
import exception.PedidoCteInexistenteException;
import view.PedidoCteView;

public class PedidoCte {
	public static String ESTADO_NUEVO = "Nuevo";
	public static String ESTADO_CANCEL = "Cancelado"; //(estado final)
	public static String ESTADO_PENDIENTE_APROB_CRED = "Pendiente aprobacion crediticia";
	public static String ESTADO_APROB_CRED_RECH	= "Aprobacion crediticia rechazada";
	public static String ESTADO_APROB_CRED_APROB = "Aprobacion crediticia aprobada";
	public static String ESTADO_STOCK_PENDIENTE = "Pendiente de ingreso de stock";
	public static String ESTADO_STOCK_SUFICIENTE = "Stock suficiente para despacho";
	public static String ESTADO_DESPACHADO = "Pedido despachado"; // (estado final)
	
	private int idPedidoCliente;
	private Date fechaGeneracion;
	private Date fechaDespacho;
	private Date fechaRecepcion;
	private String pais;
	private String provincia;
	private String partido;
	private String codigoPostal;
	private String calle;
	private String altura;
	private String piso;
	private int numero;
	private String estado;
	private String motivo;
	private Cliente cliente;
	
	public PedidoCte() {
		
	}
	
	public PedidoCte(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ClienteInexistenteException {
		this.cliente=ClienteDao.getInstance().getById(idCli);
		this.pais=pais;
		this.provincia=provincia;
		this.partido=partido;
		this.codigoPostal=codigoPostal;
		this.calle=calle;
		this.altura=altura;
		this.piso=piso;
		this.numero=numero;
	}

	
	public int getIdPedidoCliente() {
		return idPedidoCliente;
	}
	public void setIdPedidoCliente(int idPedidoCliente) {
		this.idPedidoCliente = idPedidoCliente;
	}
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public Date getFechaDespacho() {
		return fechaDespacho;
	}
	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
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
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getTotalBruto() {
		return 0;
	}

	public void agregarArticulo(Articulo articulo, int cantidad) {
		ItemPedidoCte itemPedidoCte = new ItemPedidoCte(articulo,cantidad,this);
		itemPedidoCte.guardar();
	}
	
	public PedidoCte guardar() {
		return PedidoCteDao.getInstance().grabar(this);
	}
	
	public PedidoCteView toView() {
		return null;
	}
	
	public ItemPedidoCte obtenerItemPedido(int id){
		return null;
	}
	
	public List<ItemPedidoCte> getItems() throws PedidoCteInexistenteException{
		return ItemPedidoCteDao.getInstance().getByIdPedido(idPedidoCliente);
	}
	
	public void informarMotivoRechazo(String motivo) {
		this.setEstado(ESTADO_APROB_CRED_RECH);
		this.setMotivo(motivo);
	}

	public boolean aprobarPedido() {
		return false;
	}
	
	public Date informarFechaRecepcion() {
		return null;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof PedidoCte))return false;
	    PedidoCte otherMyClass = (PedidoCte)other;
	    if(otherMyClass.getIdPedidoCliente()==getIdPedidoCliente()) {
	    	return true;
	    }
	    return false;
	}
}
