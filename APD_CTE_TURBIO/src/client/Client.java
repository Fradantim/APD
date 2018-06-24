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
			
		} catch (CommunicationException e) {
			System.out.println("OOOooops");
			e.printStackTrace();
		}
		
}}
