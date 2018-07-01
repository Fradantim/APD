package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.AjusteDao;
import dao.ArticuloDao;
import entities.ArticuloEntity;
import exception.ObjetoInexistenteException;
import model.Ajuste;
import model.Articulo;
import model.Cliente;
import model.CompraRealizada;
import model.DomicilioDeFacturacion;
import model.MovimientoInventario;
import model.PedidoCte;
import model.Rotura;
import model.VentaRealizada;

public class TesterMovimientoInventario {
	public static void main(String[] args) throws Exception{
  		
  		int minArticulos=2;
  		int maxArticulos=5;
 		System.out.println("Carga Articulos");
 		for(int i1=0; i1<getRand(minArticulos, maxArticulos) ; i1++) {
 			Articulo art = new Articulo(0, "0"+i1, "TESTpapita", 300, "bolsa", "gr", 35, 200, 100);
 			art.guardar();
 			System.out.println("Articulo grabado: "+art.getId());
 		}

 		System.out.println("--------------------------");
 		int minMovs=1;
 		int maxMovs=3;
 		for(Articulo art: ArticuloDao.getInstance().getAll()) {
 			System.out.println("Carga de Movs para Art id "+art.getId());
 			for(int i1=0; i1<getRand(minMovs, maxMovs) ; i1++) {
 				Ajuste mov= new Ajuste(0, getRand(-100, 100), 1);
 				art.agregarMovimientoAjuste(mov);
 				System.out.println("\tMovAjuste guardado id: "+mov.getIdMovimiento()+ " Mov>Art>id: "+mov.getArticulo().getId() +" "+mov.getArticulo().getDescripcion());
 			}
 			for(int i1=0; i1<getRand(minMovs, maxMovs) ; i1++) {
 				CompraRealizada mov= new CompraRealizada(getRand(1, 50)*art.getCantidadAComprar(), 1);
 				art.agregarMovimientoCompra(mov);
 				System.out.println("\tMovCompra guardado id: "+mov.getIdMovimiento()+ " Mov>Art>id: "+mov.getArticulo().getId() +" "+mov.getArticulo().getDescripcion());
 			}
 			for(int i1=0; i1<getRand(minMovs, maxMovs) ; i1++) {
 				Rotura mov= new Rotura(getRand(-100, -1),1,1,1);
 				art.agregarMovimientoRotura(mov);
 				System.out.println("\tMovRotura guardado id: "+mov.getIdMovimiento()+ " Mov>Art>id: "+mov.getArticulo().getId() +" "+mov.getArticulo().getDescripcion());
 			}
 			for(int i1=0; i1<getRand(minMovs, maxMovs) ; i1++) {
 				VentaRealizada mov= new VentaRealizada(getRand(-100, -1), 1);
 				art.agregarMovimientoVenta(mov);
 				System.out.println("\tMovVenta guardado id: "+mov.getIdMovimiento()+ " Mov>Art>id: "+mov.getArticulo().getId() +" "+mov.getArticulo().getDescripcion());
 			}
 		}
 		
 		System.out.println("--------------------------");
 		for(Articulo art: ArticuloDao.getInstance().getAll()) {
 			System.out.println("Recupero data de Art "+art.getId());
 			System.out.println("\tMovs: "+art.getMovimientos().size()+" stock: "+art.getStock());
 			for(MovimientoInventario mov : art.getMovimientos()) {
 				System.out.println("\tMov id "+mov.getIdMovimiento()+" cantidad "+mov.getCantidad()+ " mov>Art>id" + mov.getArticulo().getId()+ " "+mov.getArticulo().getDescripcion());
 			}
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
		/*
		clientesNuevos.add(new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin"));
		clientesNuevos.add(new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin"));
		*/
		return clientesNuevos;
	}
  	 
	public static ArrayList<PedidoCte> cargarPedidos(int idCliente) throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12,null));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12,null));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12,null));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12,null));
		return pedidosNuevos;
	
	}
	
	private static int getRand(int min, int max) {
		return (new Random()).nextInt(max-min) + min;
	}
	
}