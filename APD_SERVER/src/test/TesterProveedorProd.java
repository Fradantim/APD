package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.ArticuloDao;
import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import dao.PedidoCteDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import entities.PedidoCteEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Producto;
import model.Proveedor;

public class TesterProveedorProd {
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
	 	
			
			System.out.println("---------------");	    
			System.out.println("Carga ItemsPed");
			System.out.println("---------------");

			ArrayList<ItemPedidoCte> ItemsPedidoNuevos = cargarItemsPedidos();

			for (ItemPedidoCte item : ItemsPedidoNuevos) {
				item = ItemPedidoCteDao.getInstance().grabar(item);
				System.out.println(item.getIdItem());
			} 

			List<ItemPedidoCte> itempedidosNuevos = ItemPedidoCteDao.getInstance().getAll();

			for (ItemPedidoCte item : itempedidosNuevos) {
				System.out.println("Item: id"+ item.getIdItem() + 
						" cantidad "+item.getCantidad() + " Item>art>codDeBarras: "+item.getArticulo().getCodDeBarras() + 
						" Item>Pedido>calle: "+item.getPedido().getCalle());			
				
			}

	 		System.out.println("Carga Producto");
	 		ArrayList<Producto> ProdNuevos = cargarProductos();
			for (Producto prod : ProdNuevos) {
				prod = ProductoDao.getInstance().grabar(prod);
				System.out.println("Producto guardado id: " + prod.getIdProducto());
			}
			
			List<ProductoEntity> prodENuevos = ProductoDao.getInstance().getAll();

			for (ProductoEntity prod : prodENuevos) {
				System.out.println("Prod: " + prod.getiDproducto() + " " + prod.getArticulo().getCodDeBarras() + prod.getArticulo().getId());
			}

		
		
  		System.out.println("Carga Proveedores");
 		ArrayList<Proveedor> ProveedoresNuevos = cargarProveedores();
 		for (Proveedor prov : ProveedoresNuevos) {
			prov = ProveedorDao.getInstance().grabar(prov);
    		System.out.println("Proveedor guardado id: " + prov.getId());
 		}
		
		List<ProveedorEntity> proveedorENuevos = ProveedorDao.getInstance().getAll();

		for (ProveedorEntity prov : proveedorENuevos) {
			System.out.println("Prov: " + prov.getIdProveedor() + " " + prov.getNombre());
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


	public static ArrayList<Proveedor> cargarProveedores() throws ObjetoInexistenteException {
  		ArrayList<Proveedor> ProveedoresNuevos = new ArrayList<>();
 		ProveedoresNuevos.add(new Proveedor(null,"Proveedor 1",new Date()));
 		ProveedoresNuevos.add(new Proveedor(null,"Proveedor 2",new Date()));
 		ProveedoresNuevos.add(new Proveedor(null,"Proveedor 3",new Date()));	
 		ProveedoresNuevos.add(new Proveedor(null,"Proveedor 4",new Date()));
		
 		Proveedor prov= new Proveedor(null,"Proveedor 5",new Date());
 		Proveedor prov2= new Proveedor(null,"Proveedor 6",new Date());
 		ArrayList<Producto> ProdNuevos = new ArrayList<>();
 		ArrayList<Producto> Prod2Nuevos = new ArrayList<>();
 		
 		ProdNuevos.add(new Producto(10.40F,1));
 		ProdNuevos.add(new Producto(20.50F,3));
 		ProdNuevos.add(new Producto(30.50F,3));
 		ProdNuevos.add(new Producto(40.50F,2));
 		prov.getProductos().addAll(ProdNuevos);
 		ProveedoresNuevos.add(prov);
 		
 		Prod2Nuevos.add(new Producto(50.40F,1));
 		Prod2Nuevos.add(new Producto(60.50F,3));
 		prov2.getProductos().addAll(Prod2Nuevos);
 		ProveedoresNuevos.add(prov2);
 		return ProveedoresNuevos;

  	}	
	
	
	public static ArrayList<Producto> cargarProductos() throws ObjetoInexistenteException {
 		ArrayList<Producto> ProdNuevos = new ArrayList<>();
 		ProdNuevos.add(new Producto(8.40F,1));
 		ProdNuevos.add(new Producto(12.50F,3));
 		ProdNuevos.add(new Producto(43.50F,3));
 		ProdNuevos.add(new Producto(43.50F,2));
  		return ProdNuevos;
 	}	
	
}

