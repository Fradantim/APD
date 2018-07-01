package test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import controller.AreaCompras;
import dao.OrdenDeCompraDao;
import dao.PedidoCteDao;
import dao.ReservaArticuloDao;
import exception.ObjetoInexistenteException;
import model.OrdenDeCompra;
import model.PedidoCte;
import model.ReservaArticulo;

public class TesterMetodosAreaCompras {
	
	public static void main(String[] args) throws Exception{
		
/*		ArrayList<PedidoCte> pedidosNuevos = null;
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
			ped = PedidoCteDao.getInstance().grabar(ped);
 			System.out.println("Pedido guardado id: " + ped.getIdPedidoCliente());
		} 
		
		System.out.println("---------------"); 	 
		System.out.println("Carga Ordenes");
		System.out.println("---------------");
		ArrayList<OrdenDeCompra> ordenesNuevas = cargarOrdenes();
		for (OrdenDeCompra ordenes : ordenesNuevas) {
			ordenes = OrdenDeCompraDao.getInstance().grabar(ordenes);
			System.out.println("Orden guardada id: "+ ordenes.getIdOrdenCompra());
		}
		
		System.out.println("---------------"); 	 
		System.out.println("Carga Reservas");
		System.out.println("---------------"); 	 
 		ArrayList<ReservaArticulo> reservasNuevas = cargarReservas();
		for (ReservaArticulo reservas : reservasNuevas) {
			reservas = ReservaArticuloDao.getInstance().grabar(reservas);
			System.out.println("Reserva guardada id: "+reservas.getIdReserva());
		}
*/
	    
		try {
		System.out.println("Consulta Reservas Previas Caso 1");
		System.out.println("---------------"); 	
	    Date fecha = new Date();
		List<ReservaArticulo> reservas = ReservaArticuloDao.getInstance().getByArtIdYfecha(1, fecha);
		for (ReservaArticulo reser : reservas) {
			System.out.println("Reservas: " + reser.getIdReserva() + " " + reser.getEstado() );
		}
		System.out.println("Consulta Reservas Previas Caso 2");
		System.out.println("---------------"); 	
		
		List<ReservaArticulo> reserva2 = ReservaArticuloDao.getInstance().getByArtIdYfecha(5, fecha);
		for (ReservaArticulo reser : reserva2) {
			System.out.println("Reservas: " + reser.getIdReserva() + " " + reser.getEstado() );
		}
	
		System.out.println("Consulta Reservas Previas Caso 3");
		System.out.println("---------------"); 	
		

		List<ReservaArticulo> reserva3 = ReservaArticuloDao.getInstance().getByArtIdYfecha(8, fecha);
		for (ReservaArticulo reser : reserva3) {
			System.out.println("Reservas: " + reser.getIdReserva() + " " + reser.getEstado() );
		}	
		
		List<ReservaArticulo> reserva4 = ReservaArticuloDao.getInstance().getByArtIdYfecha(1,new Date());
		for (ReservaArticulo reser : reserva4) {
			System.out.println("Reservas: " + reser.getIdReserva() + " " + reser.getEstado() );
		}			

		} catch (ObjetoInexistenteException e1) {
			System.out.println("Mensaje: " + e1.getMessage());
		} 


		System.out.println("Consulta Ordenes de compra por estados Caso 1");
		System.out.println("---------------------------------------------"); 	
		String[] listaEstados = new String[1];
		listaEstados[0] = "Pendiente eleccion proveedor";
		List<OrdenDeCompra> ordenes = AreaCompras.getInstance().getOrdenesPorEstados(listaEstados);
		for (OrdenDeCompra order : ordenes) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado());
		}	
		
		System.out.println("Consulta Ordenes de compra por estados Caso 2");
		System.out.println("---------------------------------------------"); 	
		String[] listaEstados2 = new String[2];
		listaEstados2[0] = "Pendiente eleccion proveedor";
		listaEstados2[1] = "Pendiente de recepcion";
		List<OrdenDeCompra> ordenes2 = AreaCompras.getInstance().getOrdenesPorEstados(listaEstados2);
		for (OrdenDeCompra order : ordenes2) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado());
		}			

		System.out.println("Consulta Ordenes de compra por estados Caso 3");
		System.out.println("---------------------------------------------"); 	
		String[] listaEstados3 = new String[1];
		listaEstados3[0] = "prueba1";
		List<OrdenDeCompra> ordenes3 = AreaCompras.getInstance().getOrdenesPorEstados(listaEstados3);
		for (OrdenDeCompra order : ordenes3) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado());
		}			

 
		System.out.println("Consulta Ordenes de compra por estados Caso 4");
		System.out.println("---------------------------------------------"); 	
		String[] listaEstados4 = new String[3];
		listaEstados4[0] = "Pendiente eleccion proveedor";
		listaEstados4[1] = "Pendiente de recepcion";
		listaEstados4[2] = "prueba1";
		List<OrdenDeCompra> ordenes4 = AreaCompras.getInstance().getOrdenesPorEstados(listaEstados4);
		for (OrdenDeCompra order : ordenes4) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado());
		}		



		System.out.println("Consulta Ordenes de compra por estados y articulo Caso 1");
		System.out.println("--------------------------------------------------------"); 	
		String[] listaEstados5 = new String[1];
		listaEstados5[0] = "Pendiente eleccion proveedor";
		List<OrdenDeCompra> ordenes5 = AreaCompras.getInstance().getOrdenesPorEstadosYArticulo(listaEstados5, "00001106");
		for (OrdenDeCompra order : ordenes5) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado() + " " + order.getArticulo().getCodDeBarras());
		}	
		
		System.out.println("Consulta Ordenes de compra por estados y articulo Caso 2");
		System.out.println("--------------------------------------------------------"); 	
		String[] listaEstados6 = new String[2];
		listaEstados6[0] = "Prueba2";
		listaEstados6[1] = "Pendiente eleccion proveedor";
		List<OrdenDeCompra> ordenes6 = AreaCompras.getInstance().getOrdenesPorEstadosYArticulo(listaEstados6, "00001106");
		for (OrdenDeCompra order : ordenes6) {
			System.out.println("ordenes: " + order.getIdOrdenCompra() + " " + order.getEstado() + " " + order.getArticulo().getCodDeBarras());
		}	


	}
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception {
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		pedidosNuevos.add(new PedidoCte(5, "Argentina", "buenos aires", "Lomas de Zamora", "1832", "Colombres", "1888", "C",4,null, null));
		pedidosNuevos.add(new PedidoCte(4, "Argentina", "buenos aires", "Lanus", "1824", "25 de Mayo", "1200", "C",4,null, null));
		return pedidosNuevos;
	}	
	private static ArrayList<OrdenDeCompra> cargarOrdenes() throws ObjetoInexistenteException {
		ArrayList<OrdenDeCompra> OrdenesNuevas = new ArrayList<>();
		OrdenesNuevas.add(new OrdenDeCompra(1,20,1,1));
		OrdenesNuevas.add(new OrdenDeCompra(2,20,1,null));
		OrdenesNuevas.add(new OrdenDeCompra(4,20,1,null));
		OrdenesNuevas.add(new OrdenDeCompra(2,20,1,2));
		return OrdenesNuevas;
	}
	private static ArrayList<ReservaArticulo> cargarReservas() throws ObjetoInexistenteException {
		ArrayList<ReservaArticulo> reserNuevas = new ArrayList<>();
		reserNuevas.add(new ReservaArticulo(23,"STATUS_PENDIENTE","00001105",1,1));
		reserNuevas.add(new ReservaArticulo(4,"STATUS_PENDIENTE","00001105",1,1));
		return reserNuevas;
	}
	}
 
