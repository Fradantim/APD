package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.ArticuloDao;
import dao.ClienteDao;
import dao.ItemRemitoDao;
import dao.PedidoCteDao;
import dao.RemitoDao;
import entities.PedidoCteEntity;
import entities.RemitoEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.ItemRemito;
import model.PedidoCte;
import model.Remito;

public class TesterRemitos {
	public static void main(String[] args) throws Exception{
  		System.out.println("Carga Articulos");
 		ArrayList<Articulo> articulosNuevos = cargarArticulos();
		for (Articulo art : articulosNuevos) {
			art.guardar();
			System.out.println("Articulo guardado id: "+art.getId());
		}
		
		System.out.println(" ");
		System.out.println("---------------"); 	 
		System.out.println("Carga Clientes");
		System.out.println("---------------");
		ArrayList<Cliente> clientesNuevos = cargarClientes();
		for (Cliente cli : clientesNuevos) {
			cli.guardar();
			System.out.println(cli.getIdCliente());
		}
		
		ArrayList<PedidoCte> pedidosNuevos = null;
		System.out.println("Carga Pedidos");
		System.out.println("---------------");
  		try {
			pedidosNuevos = cargarPedidos();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
  		

		for (PedidoCte ped : pedidosNuevos) {
			PedidoCteDao.getInstance().grabar(ped);
 			System.out.println(ped.getIdPedidoCliente());
		} 
		
		List<Articulo> articulosENuevos = ArticuloDao.getInstance().getAll();

		for (Articulo art : articulosENuevos) {
			System.out.println("Art: " + art.getDescripcion() + " " + art.getCodDeBarras());
		}
 	
		
   
		List<Cliente> clientesENuevos = ClienteDao.getInstance().getAll();

		for (Cliente clie : clientesENuevos) {
			System.out.println("Cliente: " + clie.getIdCliente() + " " + clie.getRazonSocial());
		}
 	
		System.out.println(" ");
		System.out.println("---------------");	    
		System.out.println("Carga Pedidos");
		System.out.println("---------------");
  		try {
			pedidosNuevos = cargarPedidos();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
  		

		for (PedidoCte ped : pedidosNuevos) {
			PedidoCteDao.getInstance().grabar(ped);
 			System.out.println(ped.getIdPedidoCliente());

		} 

		List<PedidoCteEntity> pedidosENuevos = PedidoCteDao.getInstance().getAll();

		for (PedidoCteEntity pedi : pedidosENuevos) {
			System.out.println("Pedido: " + pedi.getIdPedidoCte() + pedi.getProvincia());
		}		
		
		System.out.println("---------------");	    
		System.out.println("Carga ItemsPed");
		System.out.println("---------------");

		//ArrayList<ItemPedidoCte> ItemsPedidoNuevos = cargarItemsPedidos();

		
		int minItems=1;
		int maxItems=ArticuloDao.getInstance().getAll().size();
		for(PedidoCteEntity pedidoE: PedidoCteDao.getInstance().getAll()) {
			PedidoCte pedido= pedidoE.toNegocio();
			System.out.println("Pedido "+ pedido.getIdPedidoCliente());
			for(int i1=0; i1<getRand(minItems, maxItems); i1++) {
				pedido.agregarArticulo(ArticuloDao.getInstance().getByRealId(i1+1), getRand(1,10));
				System.out.println("\t"+i1+ "items guardados");
			}
		}
		
		System.out.println("Recupero pedidos e items");
		System.out.println("---------------");
		for(PedidoCteEntity pedidoE: PedidoCteDao.getInstance().getAll()) {
			PedidoCte pedido= pedidoE.toNegocio();
			System.out.println("Pedido "+ pedido.getIdPedidoCliente());
			for(ItemPedidoCte item: pedido.getItems()) {
				System.out.println("\tItem "+item.getIdItem()+ " item>art>codDeBarras "+item.getArticulo().getCodDeBarras()+ " cant "+item.getCantidad());
			}
		}
		
		System.out.println("Carga de Remitos e items remitos");
		System.out.println("---------------");
		for(PedidoCteEntity pedido: PedidoCteDao.getInstance().getAll()) {
			Remito rem = new Remito(new Date(), pedido.toNegocio());
			rem.guardar();

			for(ItemPedidoCte itemPedido: pedido.toNegocio().getItems()) {
				ItemRemito ir = new ItemRemito(itemPedido);
				ir.setRemito(rem);
				ItemRemitoDao.getInstance().grabar(ir);
			}
		}
		
		System.out.println("Recupero Remito");
		System.out.println("---------------");
		Remito remi = RemitoDao.getInstance().getById(1);
		System.out.println(remi.getIdRemito());
		
		System.out.println("RecuperoItemsRemito");
		System.out.println("---------------");
		List<ItemRemito> itemsrem = ItemRemitoDao.getInstance().getByIdRemito(1);
		for(ItemRemito itemRemito: itemsrem) {
			System.out.println(itemRemito.getIdItem());
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
		
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin"));
		
		return clientesNuevos;
	}
  	 
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception {
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		pedidosNuevos.add(new PedidoCte(5, "Argentina", "buenos aires", "Lomas de Zamora", "1832", "Colombres", "1888", "C",4));
		pedidosNuevos.add(new PedidoCte(4, "Argentina", "buenos aires", "Lanus", "1824", "25 de Mayo", "1200", "C",4));
		return pedidosNuevos;
	}	
 	
	public static ArrayList<ItemPedidoCte> cargarItemsPedidos() throws ObjetoInexistenteException {
 		ArrayList<ItemPedidoCte> ItemsNuevos = new ArrayList<>();
  		ItemsNuevos.add(new ItemPedidoCte("00001108",5,1));
 		ItemsNuevos.add(new ItemPedidoCte("00001110",8,1));
 		ItemsNuevos.add(new ItemPedidoCte("00001105",23,2));	
 		ItemsNuevos.add(new ItemPedidoCte("00001107",54,1));
  		return ItemsNuevos;
 	}	
	
	private static int getRand(int min, int max) {
		return (new Random()).nextInt(max-min) + min;
	}
}
