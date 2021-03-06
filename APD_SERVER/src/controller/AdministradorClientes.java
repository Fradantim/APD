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
import model.Usuario;

public class AdministradorClientes {

	private static AdministradorClientes administradorClientes;
	
	public static AdministradorClientes getInstance() {
		if(administradorClientes==null) {
			administradorClientes= new AdministradorClientes();
		}
		return administradorClientes;
	}
	
	//TODO Agregar usuario y contraseņa a la firma del metodo
	//TODO crear e instanciar el objeto de usuario y persistirlo
	//TODO crear cliente, enchufarle el usuario y persistir el cliente
	
	public ClienteDTO registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero, float limiteCredito, String nombre, String apellido, String password) {
		
		Usuario usuario = new Usuario(0, nombre, apellido, Cliente.ROL_CLIENTE, password);
		usuario.guardar();
		DomicilioDeFacturacion domicilio =new DomicilioDeFacturacion(pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
		Cliente cliente = new Cliente(0, razonSocial, limiteCredito, CUIT, domicilio, tel, condicion, usuario);
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
