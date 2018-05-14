package model;

import java.util.Date;
import java.util.List;

import dao.FacturaDao;
import dto.FacturaDTO;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.ObjetoInexistenteException;

public class CtaCte {
	private int idCtaCte;
	private Cliente cliente; 

	
	public CtaCte() {}
	
	public CtaCte(int idCtaCte, Cliente cliente) {
		super();
		this.idCtaCte = idCtaCte;
		this.cliente = cliente;
	}

	public int getIdCtaCte() {
		return idCtaCte;
	}

	public void setIdCtaCte(int idCtaCte) {
		this.idCtaCte = idCtaCte;
	}
	
	public float getSaldo() {
		//TODO hacer metodo
		return 0;
	}
	
	public int generarFactura(Date fecha, int bonificacion, PedidoCte pedido) {
		Factura factura = new Factura(fecha, bonificacion, this);
		factura.setEstado(Factura.STATUS_INPAGA);
		factura = factura.guardar();
		factura.ingresarItems(pedido.getItems());
			
		return factura.getIdMovimientoCtaCte();
	}
	
	public void pagarFactura(int nroFactura, float valorPago, String especie) throws LaFacturaYaTienePagosDeOtraEspecieException, ObjetoInexistenteException {
		Factura factura = FacturaDao.getInstance().getById(nroFactura);
		List <Pago> pagosDeEstaFactura = factura.getPagos();
		boolean facturaMismaEspecie=true;
		float montoAAgregar = valorPago;
		for(Pago pago: pagosDeEstaFactura) {
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
			Pago nuevoPago = new Pago(new Date(), montoAAgregar,especie,this, null);
			nuevoPago.guardar();
		}
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<FacturaDTO> getFacturasInpagas(){
		return FacturaDao.getInstance().getDTOByStatus(Factura.STATUS_INPAGA);
	}
	
	public void agregarPago(float valorPago, String especie) {
		List <Factura> facturasInpagas = FacturaDao.getInstance().getByStatus(Factura.STATUS_INPAGA);
		float montoAAgregar = valorPago;
		for(Factura factura: facturasInpagas) {
			if(montoAAgregar> 0) {
				List <Pago> pagosDeEstaFactura = factura.getPagos();
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
				}
			}
		}
		//puede que el pago exceda las facturas que pueda cubrir, entonces genera un pago sobre la cuenta y no sobre una factura particular
		if(montoAAgregar!=0) {
			Pago nuevoPago = new Pago(new Date(), montoAAgregar,especie,this, null);
			nuevoPago.guardar();
		}
	}
	

	/**
	 * Imputa un pago sobre una factura particular
	 * @param factura
	 * @param valorPago
	 * @param especie
	 * @return sobrante del pago
	 */
	private float imputarPagoSobreFactura(Factura factura, float valorPago, String especie) {
		float montoAAgregar=valorPago;
		if(factura.getBonificacion()!=0 && especie.equals(Pago.ESPECIE_BONIFICABLE) 
				&& (factura.getPendienteDeAbonar()+valorPago >= factura.getTotal()*factura.getBonificacion()/100 )) {
			//generacion de NC	
			//NotaCredito
			NotaCredito notaCredito = new NotaCredito(new Date(), factura.getTotal()*(1-factura.getBonificacion()/100), factura);
			notaCredito.guardar();
		}
		Pago nuevoPago;
		if (montoAAgregar > factura.getTotalAbonado()) {
			nuevoPago = new Pago(new Date(), montoAAgregar-factura.getTotalAbonado(),especie,this, factura);
			montoAAgregar-=montoAAgregar-factura.getTotalAbonado();
		} else {
			nuevoPago = new Pago(new Date(), montoAAgregar-factura.getTotalAbonado(),especie,this, factura);
			montoAAgregar=0;
		}
		nuevoPago.guardar();
		
		if(factura.getPendienteDeAbonar()==0) {
			factura.setEstado(Factura.STATUS_PAGA);
			factura.guardar();
		}
		
		return montoAAgregar;
	}
}
