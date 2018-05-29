package test;

import java.text.ParseException;
import java.util.ArrayList;

import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Usuario;

public class TesterCliente {
	public static void main(String[] args) throws ObjetoInexistenteException{
		System.out.println("Carga Clientes");
		System.out.println("---------------");
		
		for (Cliente cli : cargarClientes()) {
			cli.guardar();
			Usuario usuario = new Usuario(0,"Javier", "Rabone", Cliente.ROL_CLIENTE, "ABC123");			
			usuario.guardar();
			cli.setUsuario(usuario);			
			cli.guardar();
			System.out.println("Cliente guardado: "+cli.getIdCliente());
		}
   
		for (Cliente clie : ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente recuperado: " + clie.getIdCliente() + " " + clie.getRazonSocial() + " cliente>Domicilio>calle: "+clie.getDomicilio().getCalle());
		}
 	
		
	}
 	
  
	public static ArrayList<Articulo> cargarArticulos(){
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001109", "asd", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001110", "2389", 300, "bolsa", "gr", 35, 200, 100));
		return articulosNuevos;
	}
	
	public static ArrayList<Cliente> cargarClientes(){
		ArrayList<Cliente> clientesNuevos = new ArrayList<>();
		
		DomicilioDeFacturacion domicilio = new DomicilioDeFacturacion("Argentina", "Buenos Aires", "Lanus", "1824", "Arias", "255", "3", 3);
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin", null));
		clientesNuevos.add(new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin", null));
		clientesNuevos.add(new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin", null));
		clientesNuevos.add(new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin", null));
		clientesNuevos.add(new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin", null));
		
		return clientesNuevos;
	}
  	 
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		pedidosNuevos.add(new PedidoCte(5, "Argentina", "buenos aires", "Lomas de Zamora", "1832", "Colombres", "1888", "C",4));
		pedidosNuevos.add(new PedidoCte(4, "Argentina", "buenos aires", "Lanus", "1824", "25 de Mayo", "1200", "C",4));
		return pedidosNuevos;
	}	
 	
	public static ArrayList<ItemPedidoCte> cargarItemsPedidos() throws ObjetoInexistenteException {
//		ArrayList<ItemPedidoCte> ItemsNuevos = new ArrayList<>();
//		ItemsNuevos.add(new ItemPedidoCte("999999",20,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001108",5,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001110",8,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001105",23,2));
//		ItemsNuevos.add(new ItemPedidoCte("00001107",54,2));
		ItemPedidoCteDao.getInstance().grabar(new ItemPedidoCte("00001105",20,1));
//		return ItemsNuevos;
		return null;
	}	
	
}