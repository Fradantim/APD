package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
  		ArrayList<Articulo> articulosNuevos = cargarArticulos();
  	
 		System.out.println("Carga Articulos");
		for (Articulo art : articulosNuevos) {
			art = ArticuloDao.getInstance().grabar(art);
			System.out.println(art.getId());
		}
		System.out.println("----------------");
   		 
 		Articulo articuloRecuperado = ArticuloDao.getInstance().getById("00001107");;		
		System.out.println("Art: " + articuloRecuperado.getId()+" "+articuloRecuperado.getDescripcion() + " " + articuloRecuperado.getCodDeBarras());
		
		System.out.println("----------------");
		MovimientoInventario movimientoAjuste = new Ajuste(0,11,1,articuloRecuperado);
 		movimientoAjuste=movimientoAjuste.guardar();
 		System.out.println("Ajuste generado id: "+movimientoAjuste.getIdMovimiento());
 		
 		System.out.println("----------------");
 		MovimientoInventario ajusteRecuperado = AjusteDao.getInstance().getById(1);
 		
 		System.out.println("Ajuste recuperado por id: "+ajusteRecuperado.getIdMovimiento());
 		System.out.println("su articulo: "+ajusteRecuperado.getArticulo().getId());
 		
 		new Ajuste(0,12,1,ArticuloDao.getInstance().getById("00001105")).guardar();
 		 		
 		new CompraRealizada( 7, 1, ArticuloDao.getInstance().getById("00001107")).guardar();
 		new CompraRealizada(12, 1, ArticuloDao.getInstance().getById("00001105")).guardar();
 		
 		new Rotura( -1, 1, 2, 3, ArticuloDao.getInstance().getById("00001107")).guardar();
 		new Rotura(-10, 1, 2, 3, ArticuloDao.getInstance().getById("00001105")).guardar();
 		new VentaRealizada( -1, 1, ArticuloDao.getInstance().getById("00001107")).guardar();
 		new VentaRealizada(-10, 1, ArticuloDao.getInstance().getById("00001105")).guardar();
 		
 		
 		List<ArticuloEntity> articulosENuevos = ArticuloDao.getInstance().getAll();
 		System.out.println("Stocks:");
		for (ArticuloEntity art : articulosENuevos) {
			Articulo articuloModelo = art.toNegocio();
			System.out.println("Art: " + articuloModelo.getDescripcion() + "\t" + articuloModelo.getCodDeBarras()+ "\t" +articuloModelo.getStock());
		}
		System.out.println("----------------");
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
  	 
	public static ArrayList<PedidoCte> cargarPedidos(int idCliente) throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(idCliente,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		return pedidosNuevos;
	
	}
	
	
}