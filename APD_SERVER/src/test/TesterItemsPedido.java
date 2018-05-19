package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AjusteDao;
import dao.ArticuloDao;
import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import entities.ArticuloEntity;
import entities.ClienteEntity;
import entities.ItemPedidoCteEntity;
import entities.PedidoCteEntity;
import exception.ObjetoInexistenteException;
import model.Ajuste;
import model.Articulo;
import model.Cliente;
import model.CompraRealizada;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.MovimientoInventario;
import model.PedidoCte;
import model.Rotura;
import model.VentaRealizada;

public class TesterItemsPedido {
	public static void main(String[] args) throws Exception{
  		/*System.out.println("Carga Articulos");
 		ArrayList<Articulo> articulosNuevos = cargarArticulos();
		for (Articulo art : articulosNuevos) {
			art =art.guardar();
			System.out.println("Articulo guardado id: "+art.getId());
		}
		System.out.println("---------------");
   		 
		System.out.println("---------------"); 	 
		System.out.println("Carga Clientes");
		System.out.println("---------------");
		ArrayList<Cliente> clientesNuevos = cargarClientes();
		for (Cliente cli : clientesNuevos) {
			cli=cli.guardar();
			System.out.println("Cliente guardado id: "+cli.getIdCliente());
		}
   
		System.out.println(" ");
		System.out.println("---------------");	    
		System.out.println("Carga Pedidos");
		System.out.println("---------------");
		ArrayList<PedidoCte> pedidosNuevos = cargarPedidos();
  		
		for (PedidoCte ped : pedidosNuevos) {
			ped=ped.guardar();
			System.out.println("Pedido guardado Id: "+ped.getIdPedidoCliente());
		} 

		
		System.out.println("---------------");	    
		System.out.println("Carga ItemsPed en pedido 1");
		System.out.println("---------------");

		ArrayList<ItemPedidoCte> ItemsPedidoNuevos = cargarItemsPedidos(1);

		for (ItemPedidoCte item : ItemsPedidoNuevos) {
			item=item.guardar();
			System.out.println("Item guardado id: "+item.getIdItem());
		} 
		
		System.out.println("---------------");	    
		System.out.println("Carga ItemsPed en pedido 2");
		System.out.println("---------------");

		ItemsPedidoNuevos = cargarItemsPedidos(2);

		for (ItemPedidoCte item : ItemsPedidoNuevos) {
			item=item.guardar();
			System.out.println("Item guardado id: "+item.getIdItem());
		} 

		System.out.println("---------------");
		System.out.println("Items pedidos getAll");
		List<ItemPedidoCteEntity> itempedidosENuevos = ItemPedidoCteDao.getInstance().getAll();
		for (ItemPedidoCteEntity item : itempedidosENuevos) {
			System.out.println("Item id:" + item.getIdItem());
		}
		
		System.out.println("---------------");
		System.out.println("Items pedidos getbyIdItem 7");
		System.out.println("Item id: "+ItemPedidoCteDao.getInstance().getById(7).getIdItem());
		
		System.out.println("---------------");
		System.out.println("Items pedidos getByIdPedido 1");
		List<ItemPedidoCte> itempedidosNuevos  = ItemPedidoCteDao.getInstance().getByIdPedido(1);
		for (ItemPedidoCte item : itempedidosNuevos) {
			System.out.println("Item id:" + item.getIdItem());
		}
		System.out.println("---------------");
		System.out.println("Items pedidos getByIdPedido 2");
		itempedidosNuevos  = ItemPedidoCteDao.getInstance().getByIdPedido(2);
		for (ItemPedidoCte item : itempedidosNuevos) {
			System.out.println("Item id:" + item.getIdItem());
		}
*/
		
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
		
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin"));
		
		return clientesNuevos;
	}
  	 
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		
		pedidosNuevos.add(new PedidoCte(1,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		return pedidosNuevos;
	
	}
	
	public static ArrayList<ItemPedidoCte> cargarItemsPedidos(int idPedido) throws ObjetoInexistenteException {
		ArrayList<ItemPedidoCte> ItemsNuevos = new ArrayList<>();
		ItemsNuevos.add(new ItemPedidoCte("00001105",20,idPedido));
		ItemsNuevos.add(new ItemPedidoCte("00001106",5,idPedido));
		ItemsNuevos.add(new ItemPedidoCte("00001107",8,idPedido));
		ItemsNuevos.add(new ItemPedidoCte("00001108",23,idPedido));
		ItemsNuevos.add(new ItemPedidoCte("00001109",54,idPedido));
		return ItemsNuevos;
	}		
}