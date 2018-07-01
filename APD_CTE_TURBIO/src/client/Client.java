package client;

import java.lang.reflect.InvocationTargetException;

import javax.naming.CommunicationException;

import delegate.BusinessDelegate;
import server.Server;

public class Client {

	public static void main (String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		BusinessDelegate bd=null;
		Runner runner=null;
		
		Server.main(new String [] {});
		
		try {
			bd = new BusinessDelegate();
			runner = new Runner();
			runner.altaUbicaciones();
			runner.altaArticulos();
			//runner.listarArticulos();
			runner.altaUsuarios();
			runner.altaClientes();
			runner.altaProductos();
			runner.altaProveedores();
			runner.asociarProdProv();
			runner.generarPedidos();
			runner.agregarItemsAPedido();
			runner.cerrarPedidoCte();
			runner.pedidoAprobRech();
			runner.evaluarStocks();
			runner.elegirProveedor();
			//runner.listarOrdDeCompraDAO();
			runner.banca(6);
			//runner.listarOrdDeCompraRecibidas();
			runner.ubicarOrdenesRecibidas();
			runner.evaluarStocks();
			runner.aceptarPedDesp();
			
		} catch (CommunicationException e) {
			System.out.println("OOOooops");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("OOOooops");
			e.printStackTrace();
		}
		
}}
