package model;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dao.CtaCteDao;
import dto.ClienteDTO;
import dto.FacturaDTO;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.ObjetoInexistenteException;

public class Cliente {
	private int idCliente;
	private String razonSocial;
	private float limiteCredito;
	private TipoDocumento tipoDocumento;
	private String documento;
	private DomicilioDeFacturacion domicilio;
	private int telefono;
	private String condicionFinanciera;
	
	public Cliente(int id, String razonSocial, float limiteCredito, String documento, DomicilioDeFacturacion domicilio,
			int telefono, String condicionFinanciera) {
		super();
		this.idCliente = id;
		this.razonSocial = razonSocial;
		this.limiteCredito = limiteCredito;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.condicionFinanciera = condicionFinanciera;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public DomicilioDeFacturacion getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioDeFacturacion domicilio) {
		this.domicilio = domicilio;
	}
	public CtaCte getCuenta() throws ObjetoInexistenteException {
		return CtaCteDao.getInstance().getByClienteId(idCliente);
	}

	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCondicionFinanciera() {
		return condicionFinanciera;
	}
	public void setCondicionFinanciera(String condicionFinanciera) {
		this.condicionFinanciera = condicionFinanciera;
	}
	
	public float getSaldo() throws ObjetoInexistenteException {
		return getCuenta().getSaldo();
	}
	
	public int generarFactura(Date fecha, int bonificacion, PedidoCte pedido) throws ObjetoInexistenteException {
		return getCuenta().generarFactura(fecha, bonificacion, pedido);
	}
	
	public Remito generarRemito (Date fecha, PedidoCte pedido) throws ObjetoInexistenteException {
		return new Remito(fecha, pedido.getItems());
	}
	
	public void pagarFactura(int nroFactura, float valorPago, String especie) throws LaFacturaYaTienePagosDeOtraEspecieException, ObjetoInexistenteException{
		getCuenta().pagarFactura(nroFactura, valorPago, especie);
	}
	
	public void agregarPago(float pago, String especie) throws ObjetoInexistenteException {
		getCuenta().agregarPago(pago, especie);
	}
	
	public List<FacturaDTO> getFacturasInpagas() throws ObjetoInexistenteException{
		return getCuenta().getFacturasInpagas();
	}
	
	public ClienteDTO toDTO() throws ObjetoInexistenteException {
		return new ClienteDTO(idCliente, razonSocial, limiteCredito, tipoDocumento.getSigla(), documento, getSaldo(), telefono, condicionFinanciera, getDomicilio().toDTO());
	}
	
	public Cliente guardar() throws ObjetoInexistenteException {
		Cliente guardado= ClienteDao.getInstance().grabar(this);
		CtaCte ctacte = new CtaCte(0,guardado);
		ctacte.guardar();
		return guardado;
	}
}
