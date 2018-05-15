package test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.ArticuloDao;
import dao.ItemRemitoDao;
import dao.RemitoDao;
import entities.ArticuloEntity;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemRemito;
import model.PedidoCte;
import model.Remito;

public class TesterRemito {
	public static void main(String[] args) throws Exception{
		ArrayList<Articulo> articulosNuevos = cargarArticulos();
		System.out.println("---------------");
 		System.out.println("Carga Articulos");
 		System.out.println("---------------");
		for (Articulo art : articulosNuevos) {
			art = ArticuloDao.getInstance().grabar(art);
			System.out.println(art.getId());
		}
		
		ArrayList<Remito> remitosNuevos = cargarRemitos();
		
		System.out.println("Carga Remitos");
		for (Remito rem : remitosNuevos) {
			rem = RemitoDao.getInstance().grabar(rem);
			System.out.println(rem.getIdRemito());
		}
		
		ArrayList<ItemRemito> itemRemitosNuevos = cargarItemRemitos();
		System.out.println("Carga Item Remitos");
		for (ItemRemito irem : itemRemitosNuevos) {
			irem = ItemRemitoDao.getInstance().grabar(irem);
			System.out.println(irem.getIdItem());
		}
		
	}
	
	public static ArrayList<Remito> cargarRemitos(){
		ArrayList<Remito> remitosNuevos = new ArrayList<>();
		remitosNuevos.add(new Remito(0, new java.util.Date(), 1));
		remitosNuevos.add(new Remito(0, new java.util.Date(), 2));
		remitosNuevos.add(new Remito(0, new java.util.Date(), 3));
		remitosNuevos.add(new Remito(0, new java.util.Date(), 4));
		remitosNuevos.add(new Remito(0, new java.util.Date(), 5));
		return remitosNuevos;
	}
	
	public static ArrayList<ItemRemito> cargarItemRemitos(){
		ArrayList<ItemRemito> itemRemitosNuevos = new ArrayList<>();
		itemRemitosNuevos.add(new ItemRemito("AAA", 1, 2));
		itemRemitosNuevos.add(new ItemRemito("AAA", 1, 1));
		itemRemitosNuevos.add(new ItemRemito("AAA", 2, 3));
		itemRemitosNuevos.add(new ItemRemito("AAA", 2, 2));
		itemRemitosNuevos.add(new ItemRemito("AAA", 2, 5));
		return itemRemitosNuevos;
	}
	
	public static ArrayList<Articulo> cargarArticulos(){
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "AAA", "papita", 300, "bolsa", "gr", 35, 200, 100));
		return articulosNuevos;
	}
	
	public static ArrayList<Cliente> cargarClientes(){
		ArrayList<Cliente> clientesNuevos = new ArrayList<>();
		
		DomicilioDeFacturacion domicilio = new DomicilioDeFacturacion("Argentina", "Buenos Aires", "Lanus", "1824", "Arias", "255", "3", 3);
		
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin"));
		
		return clientesNuevos;
	}
}
