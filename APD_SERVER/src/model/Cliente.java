package model;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dao.FacturaDao;
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
	private Usuario usuario;
	
	public Cliente(int id, String razonSocial, float limiteCredito, String documento, DomicilioDeFacturacion domicilio,
			int telefono, String condicionFinanciera, Usuario usuario) {
		super();
		this.idCliente = id;
		this.razonSocial = razonSocial;
		this.limiteCredito = limiteCredito;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.condicionFinanciera = condicionFinanciera;
		this.usuario = usuario;
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
		//llama al factura Dao, pero va contra la tabla madre que tiene todos los movimientos
		return FacturaDao.getInstance().getSumImporteByIdCliente(idCliente);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public int generarFactura(Date fecha, int bonificacion, PedidoCte pedido) throws ObjetoInexistenteException {
		Factura factura = new Factura(fecha, bonificacion);
		factura.setEstado(FacturaDTO.STATUS_INPAGA);
		agregarMovimientoFactura(factura);
		
		factura.ingresarItems(pedido.getItems());
			
		return factura.getIdMovimientoCtaCte();
	}
	
	public void pagarFactura(int nroFactura, float valorPago, String especie) throws LaFacturaYaTienePagosDeOtraEspecieException, ObjetoInexistenteException {
		Factura factura = FacturaDao.getInstance().getById(nroFactura);
		System.out.println("Factura" + " " + factura.getIdMovimientoCtaCte() + " " + factura.getBonificacion());

		List <Pago> pagosDeEstaFactura = factura.getPagosAsociados();

		System.out.println("Factura" + " " + factura.getBonificacion());
		boolean facturaMismaEspecie=true;
		float montoAAgregar = valorPago ;
		for(Pago pago: pagosDeEstaFactura) {
			System.out.println("Pago:" + " " + pago.getImporte() + " especie:" + pago.getEspecie());
			if(!pago.getEspecie().equals(especie)) {
				//si la factura ya tiene un pago de otra especie no puedo agregar el pago aca
				facturaMismaEspecie=false;
				throw new LaFacturaYaTienePagosDeOtraEspecieException("La factura "+factura.getIdMovimientoCtaCte()+" ya tiene pagos de otra especie.");
			}
		}
		if(facturaMismaEspecie) {
			montoAAgregar=imputarPagoSobreFactura(factura, valorPago, especie);
		}
		//puede que el pago exceda las facturas que pueda cubrir, entonces genera un pago sobre la cuenta y no sobre una factura particular
		if(montoAAgregar!=0) {
			agregarMovimientoPago(new Pago(new Date(), montoAAgregar,especie));
		}
	}
	
	public Integer agregarPago(float valorPago, String especie) throws ObjetoInexistenteException {
		List <Factura> facturasInpagas = FacturaDao.getInstance().getByStatus(this,FacturaDTO.STATUS_INPAGA);

		float montoAAgregar = valorPago;
		int idfactura = 0;
		for(Factura factura: facturasInpagas) {
			if(montoAAgregar< 0) {
				List <Pago> pagosDeEstaFactura = factura.getPagosAsociados();
				boolean facturaMismaEspecie=true;
				
				for(Pago pago: pagosDeEstaFactura) {
					if(!pago.getEspecie().equals(especie)) {
						//si la factura ya tiene un pago de otra especie no puedo agregar el pago aca
						facturaMismaEspecie=false;
						break;
					}
				}
				if(facturaMismaEspecie) {
					montoAAgregar=imputarPagoSobreFactura(factura, valorPago, especie);
					idfactura = factura.getIdMovimientoCtaCte();
				}
			}
		}
		
		//puede que el pago exceda las facturas que pueda cubrir, entonces genera un pago sobre la cuenta y no sobre una factura particular
		if(montoAAgregar!=0) {
			agregarMovimientoPago(new Pago(new Date(), montoAAgregar,especie));		
		}
		return idfactura;
		
	}
	
	public List<FacturaDTO> getFacturasInpagas(){
		return FacturaDao.getInstance().getDTOByStatus(this,FacturaDTO.STATUS_INPAGA);
	}
	
	/**
	 * Imputa un pago sobre una factura particular
	 * @param factura
	 * @param valorPago
	 * @param especie
	 * @return sobrante del pago
	 * @throws ObjetoInexistenteException 
	 */
	private float imputarPagoSobreFactura(Factura factura, float valorPago, String especie) throws ObjetoInexistenteException {
		float montoAAgregar=valorPago;
		if(factura.getBonificacion()!=0 && especie.equals(FacturaDTO.ESPECIE_BONIFICABLE) 
				&& (factura.getPendienteDeAbonar()-valorPago >= factura.getImporte()*(1-factura.getBonificacion()/100 ))) {
			//generacion de NC NotaCredito
			//System.out.println(">>> factura "+factura.getIdMovimientoCtaCte()+
			//		" ($"+factura.getImporte()+" "+factura.getBonificacion()+"%) monto a bonificar "+-factura.getImporte()*(factura.getBonificacion()/100F));
			agregarMovimientoNotaDeCredito(new NotaCredito(new Date(), -factura.getImporte()*(factura.getBonificacion()/100F), factura));
		}

		//System.out.println("Pago: "+montoAAgregar+" -pendiente"+-factura.getPendienteDeAbonar());
		if( -montoAAgregar > factura.getPendienteDeAbonar()) {
			montoAAgregar=-factura.getPendienteDeAbonar();
		}
			
		//Pago nuevoPago = new Pago(new Date(), montoAAgregar-factura.getTotalAbonado(),especie);
		Pago nuevoPago = new Pago(new Date(), montoAAgregar,especie);
		agregarMovimientoPago(nuevoPago);
		factura.asociarPago(nuevoPago);
		
		return valorPago-montoAAgregar;
	}
	
	public ClienteDTO toDTO(){
		return new ClienteDTO(idCliente, razonSocial, limiteCredito, documento, getSaldo(), telefono, condicionFinanciera, getDomicilio().toDTO(), getUsuario().toDto());
	}
	
	public Integer guardar(){
		this.idCliente= ClienteDao.getInstance().grabar(this);
		return this.idCliente;
	}
	
	public void eliminar(){
		ClienteDao.getInstance().eliminar(this);
	}
	
	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */
	public Integer agregarMovimientoFactura(Factura mov) {
		mov.setCliente(this);
		return mov.guardar();
	}
	
	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */
	public Integer agregarMovimientoPago(Pago mov) {
		mov.setCliente(this);
		return mov.guardar();
	}
	
	/**
	 * Este metodo deberia ser privado, pero se deja publico exclusivamente para testeo y solo para eso debe ser usado
	 */
	public Integer agregarMovimientoNotaDeCredito(NotaCredito mov) {
		mov.setCliente(this);
		mov.guardar();
		if(mov.getFacturaBonificada()!=null) {
			Factura facturaBonificada= mov.getFacturaBonificada();
			if(facturaBonificada.getImporte()<=-mov.getImporte()) {
				facturaBonificada.setEstado(FacturaDTO.STATUS_PAGA);
				facturaBonificada.guardar();
			}
		}
		return mov.getIdMovimientoCtaCte();
	}
	
	
}
