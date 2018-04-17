package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Cliente;
import model.PedidoCte;
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
	
	private List<Cliente> clientes;
	
	public AdministradorClientes() {
		clientes = new ArrayList<>();
	}
	
	public ClienteView registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		return null;
	}
	
	public void bajaCliente(int idCliente) {
		
	}
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) {
		
	}
	
	public void agregarPago(int idCliente, float pago, String especie) {
		
	}
	
	public void generarFactura(int idCliente, Date fecha, int Bonificacion, PedidoCte pedidoCte) {
		
	}
	
	public void generarRemito(int idCliente, Date fecha, PedidoCte pedido) {
		
	}
	
	public List<FacturaView> getFacturasInpagas(int clienteId){
		return null;
	}
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		
	}
	
}
