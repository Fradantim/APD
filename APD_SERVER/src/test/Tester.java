package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ArticuloDao;
import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import entities.ArticuloEntity;
import entities.ClienteEntity;
import entities.ItemPedidoCteEntity;
import entities.PedidoCteEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.PedidoCte;

public class Tester {
	public static void main(String[] args) throws ObjetoInexistenteException{
//    		ArrayList<Articulo> articulosNuevos;
//     		ArrayList<Cliente> clientesNuevos;
//    		ArrayList<PedidoCte> pedidosNuevos = null;
    		 
 
//    		articulosNuevos = cargarArticulos();
//     		clientesNuevos = cargarClientes();
     		 
		
/*    		
		System.out.println("---------------");
 		System.out.println("Carga Articulos");
 		System.out.println("---------------");
		for (Articulo art : articulosNuevos) {
			art = ArticuloDao.getInstance().grabar(art);
			System.out.println(art.getId());
		}
   		 
		List<ArticuloEntity> articulosENuevos = ArticuloDao.getInstance().getAll();

		for (ArticuloEntity art : articulosENuevos) {
			System.out.println("Art: " + art.getDescripcion() + " " + art.getCodDeBarras());
		}
 	
		System.out.println(" ");
		System.out.println("---------------"); 	 
		System.out.println("Carga Clientes");
		System.out.println("---------------");
		for (Cliente cli : clientesNuevos) {
			cli = ClienteDao.getInstance().grabar(cli);
			System.out.println(cli.getIdCliente());
		}
   
		List<ClienteEntity> clientesENuevos = ClienteDao.getInstance().getAll();

		for (ClienteEntity clie : clientesENuevos) {
			System.out.println("Cliente: " + clie.getId() + " " + clie.getRazonSocial());
		}
 	
		System.out.println(" ");
		System.out.println("---------------");	    
		System.out.println("Carga Pedidos");
		System.out.println("---------------");
  		try {
			pedidosNuevos = cargarPedidos();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  		

		for (PedidoCte ped : pedidosNuevos) {
			ped = PedidoCteDao.getInstance().grabar(ped);
			System.out.println(PedidoCteDao.getInstance().getIdLastId());

		} 

		List<PedidoCteEntity> pedidosENuevos = PedidoCteDao.getInstance().getAll();

		for (PedidoCteEntity pedi : pedidosENuevos) {
			System.out.println("Pedido: " + pedi.getIdPedidoCte());
		}
  	
*/		
		
		System.out.println("---------------");	    
		System.out.println("Carga ItemsPed");
		System.out.println("---------------");

		ArrayList<ItemPedidoCte> ItemsPedidoNuevos = cargarItemsPedidos();

		for (ItemPedidoCte item : ItemsPedidoNuevos) {
			item = ItemPedidoCteDao.getInstance().grabar(item);
			System.out.println(item.getIdItem());

		} 

		List<ItemPedidoCteEntity> itempedidosENuevos = ItemPedidoCteDao.getInstance().getAll();

		for (ItemPedidoCteEntity item : itempedidosENuevos) {
			System.out.println("Items" + item.getIdItem());
		}

/*		
		System.out.println(" ");
		System.out.println("---------------------");		
		System.out.println("Busca articulo por Id");
		System.out.println("---------------------");
 		Articulo articulo = null;
		try {
			articulo = ArticuloDao.getInstance().getById("00001107");
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Art: " + articulo.getDescripcion() + " " + articulo.getCodDeBarras());
  	
		System.out.println(" ");
		System.out.println("--------------------");  
		System.out.println("Busca pedido por Id");
		System.out.println("---------------------");
 		PedidoCte pedido = null;
		try {
			pedido = PedidoCteDao.getInstance().getById(1);
			Integer IdPedido = PedidoCteDao.getInstance().getIdById(1);
			pedido.setIdPedidoCliente(IdPedido);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Pedido: " + pedido.getIdPedidoCliente() + " " + pedido.getCliente().getRazonSocial());
		try {
			pedido = PedidoCteDao.getInstance().getById(4);
			Integer IdPedido = PedidoCteDao.getInstance().getIdById(4);
			pedido.setIdPedidoCliente(IdPedido);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Pedido: " + pedido.getIdPedidoCliente() + " " + pedido.getCliente().getRazonSocial());
*/
	}
 	
/* 
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
		
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin"));
		
		return clientesNuevos;
	}
  	 
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		pedidosNuevos.add(new PedidoCte(5, "Argentina", "buenos aires", "Lomas de Zamora", "1832", "Colombres", "1888", "C",4));
		pedidosNuevos.add(new PedidoCte(4, "Argentina", "buenos aires", "Lanus", "1824", "25 de Mayo", "1200", "C",4));
		return pedidosNuevos;
	}	
*/	
	public static ArrayList<ItemPedidoCte> cargarItemsPedidos() throws ObjetoInexistenteException {
//		ArrayList<ItemPedidoCte> ItemsNuevos = new ArrayList<>();
//		ItemsNuevos.add(new ItemPedidoCte("999999",20,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001108",5,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001110",8,1));
//		ItemsNuevos.add(new ItemPedidoCte("00001105",23,2));
//		ItemsNuevos.add(new ItemPedidoCte("00001107",54,2));
		ItemPedidoCteDao.getInstance().grabar(new ItemPedidoCte("999999",20,1));
//		return ItemsNuevos;
		return null;
	}	
	
}