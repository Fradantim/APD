package controller;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dto.ClienteDTO;
import dto.FacturaDTO;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.ObjetoInexistenteException;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.PedidoCte;
import model.Remito;

public class AdministradorClientes {

	private static AdministradorClientes administradorClientes;
	
	public static AdministradorClientes getInstance() {
		if(administradorClientes==null) {
			administradorClientes= new AdministradorClientes();
		}
		return administradorClientes;
	}
	
	public ClienteDTO registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero, float limiteCredito) {
		
		
		DomicilioDeFacturacion domicilio =new DomicilioDeFacturacion(pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
		Cliente cliente = new Cliente(0, razonSocial, limiteCredito, CUIT, domicilio, tel, condicion);
		cliente.guardar();
		try {
			return cliente.toDTO();
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void bajaCliente(int idCliente) {
		//TODO hacer metodo
	}
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		Cliente cliente = ClienteDao.getInstance().getById(idCliente);
		cliente.pagarFactura(nroFactura, pago, especie);
	}
	
	public void agregarPago(int idCliente, float pago, String especie) throws ObjetoInexistenteException {
		Cliente cliente = ClienteDao.getInstance().getById(idCliente);
		cliente.agregarPago(pago, especie);
	}
	
	public int generarFactura(int idCliente, Date fecha, int bonificacion, PedidoCte pedidoCte) throws ObjetoInexistenteException {
		Cliente cliente = ClienteDao.getInstance().getById(idCliente);
		return cliente.generarFactura(fecha, bonificacion, pedidoCte);
	}
	
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException{
		Cliente cliente = ClienteDao.getInstance().getById(clienteId);
		return cliente.getFacturasInpagas();
	}
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		//TODO hacer metodo
	}
	
}
