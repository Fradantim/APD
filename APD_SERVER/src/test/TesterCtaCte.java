package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AjusteDao;
import dao.ArticuloDao;
import dao.ClienteDao;
import dao.CtaCteDao;
import entities.ArticuloEntity;
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

public class TesterCtaCte {
	public static void main(String[] args) throws Exception{
		System.out.println("---------------"); 	 
		System.out.println("Carga Clientes");
		System.out.println("---------------");
		ArrayList<Cliente> clientesNuevos = cargarClientes();
		ArrayList<Cliente> misClientes = new ArrayList<>();
		for (Cliente cli : clientesNuevos) {
			cli=cli.guardar();
			misClientes.add(cli);
			System.out.println("Cliente guardado id: "+cli.getIdCliente() + " ctacte id: "+cli.getCuenta().getIdCtaCte());
		}
		
		for (Cliente cli : misClientes) {
			System.out.println("Cliente recuperado id: "+cli.getIdCliente()+" "+ClienteDao.getInstance().getById(cli.getIdCliente()).getRazonSocial() +
					" ctacte id: "+CtaCteDao.getInstance().getByClienteId(cli.getIdCliente()).getIdCtaCte());
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
  	 
	public static ArrayList<PedidoCte> cargarPedidos() throws Exception, ParseException{
		ArrayList<PedidoCte> pedidosNuevos = new ArrayList<>();
		
		pedidosNuevos.add(new PedidoCte(1,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		pedidosNuevos.add(new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12));
		return pedidosNuevos;
	
	}
	

}