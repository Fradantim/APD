package controller;

import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.ObjetoInexistenteException;
import model.Cliente;
import model.PedidoCte;
import model.Remito;
import view.ClienteView;
import view.FacturaView;

public class AdministradorClientes {

	private static AdministradorClientes administradorClientes;
	
	public static AdministradorClientes getInstance() {
		if(administradorClientes==null) {
			administradorClientes= new AdministradorClientes();
		}
		return administradorClientes;
	}
	
	public ClienteView registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		//TODO hacer metodo
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
	
	public Remito generarRemito(int idCliente, Date fecha, PedidoCte pedido) throws ObjetoInexistenteException {
		Cliente cliente = ClienteDao.getInstance().getById(idCliente);
		try {
			return cliente.generarRemito(fecha, pedido);
		} catch (ObjetoInexistenteException e) {
			// TODO Consultar, que hago con estas excepcion? en la teoria no deberian ocurrir.
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FacturaView> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException{
		Cliente cliente = ClienteDao.getInstance().getById(clienteId);
		return cliente.getFacturasInpagas();
	}
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		//TODO hacer metodo
	}
	
}
