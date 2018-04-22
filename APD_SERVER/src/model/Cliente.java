package model;

import java.util.Date;
import java.util.List;

import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.PedidoCteInexistenteException;
import view.FacturaView;

public class Cliente {
	private int idCliente;
	private String razonSocial;
	private float limiteCredito;
	private TipoDocumento tipoDocumento;
	private String documento;
	private DomicilioDeFacturacion domicilio;
	private CtaCte cuentaCliente;
	private int telefono;
	private String condicionFinanciera;
	
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
	public CtaCte getCuenta() {
		return cuentaCliente;
	}
	public void setCuenta(CtaCte cuenta) {
		this.cuentaCliente = cuenta;
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
	
	public float getSaldo() {
		return 0;
	}
	
	public int generarFactura(Date fecha, int bonificacion, PedidoCte pedido) {
		return cuentaCliente.generarFactura(fecha, bonificacion, pedido);
	}
	
	public Remito generarRemito (Date fecha, PedidoCte pedido) throws PedidoCteInexistenteException {
		return new Remito(fecha, pedido.getItems());
	}
	
	public void pagarFactura(int nroFactura, String especie) throws LaFacturaYaTienePagosDeOtraEspecieException{
		
	}
	
	public List<FacturaView> getFacturasInpagas(){
		return null;
	}
	
	public void guardar() {
		
	}
}
