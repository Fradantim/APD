package test;

import java.util.ArrayList;
import java.util.List;

import dao.ArticuloDao;
import dao.ClienteDao;
import entities.ArticuloEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;

public class Tester {
	public static void main(String[] args){
		ArrayList<Articulo> articulosNuevos;
		ArrayList<Cliente> clientesNuevos;
		
		articulosNuevos = cargarArticulos();
		clientesNuevos = cargarClientes();

		System.out.println("Carga Articulos");
		for (Articulo art : articulosNuevos) {
			art = ArticuloDao.getInstance().grabar(art);
			System.out.println(art.getId());
		}
		
		System.out.println("Carga Clientes");
		for (Cliente cli : clientesNuevos) {
			cli = ClienteDao.getInstance().grabar(cli);
			System.out.println(cli.getIdCliente());
		}

		Articulo articulo = null;
		try {
			articulo = ArticuloDao.getInstance().getById("00001107");
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("Art: " + articulo.getDescripcion() + " " + articulo.getCodDeBarras());

		List<ArticuloEntity> articulosENuevos = ArticuloDao.getInstance().getAll();

		for (ArticuloEntity art : articulosENuevos) {
			System.out.println("Art: " + art.getDescripcion() + " " + art.getCodDeBarras());
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
}
