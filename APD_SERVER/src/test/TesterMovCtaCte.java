package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.AjusteDao;
import dao.ArticuloDao;
import dao.ClienteDao;
import dto.FacturaDTO;
import entities.ArticuloEntity;
import exception.ObjetoInexistenteException;
import model.Ajuste;
import model.Articulo;
import model.Cliente;
import model.CompraRealizada;
import model.DomicilioDeFacturacion;
import model.Factura;
import model.ItemFactura;
import model.ItemPedidoCte;
import model.MovimientoInventario;
import model.PedidoCte;
import model.Rotura;
import model.VentaRealizada;

public class TesterMovCtaCte {
	public static void main(String[] args) throws Exception{
		System.out.println("---------------");
		System.out.println("Carga Articulos");
		persistirArticulos();
		System.out.println("---------------");
		System.out.println("Carga Clientes");
		persistirClientes();
		
		int minFacturas=2;
		int maxFacturas=5;
		
		int minItems=1;
		int maxItems=ArticuloDao.getInstance().getAll().size();
		
		int minCantidad=1;
		int maxCantidad=20;
		
		System.out.println("---------------");
		System.out.println("Carga Facturas");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cargando facturas para cliente "+cliente.getIdCliente());
			for(int i1=0; i1<getRand(minFacturas, maxFacturas);i1++) {
				PedidoCte pedido = new PedidoCte(cliente.getIdCliente(),"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12);
				pedido=pedido.guardar();
				for(int i2=0; i2<getRand(minItems, maxItems);i2++) {
					pedido.agregarArticulo(ArticuloDao.getInstance().getByRealId(i2+1), getRand(minCantidad, maxCantidad));
				}
				cliente.generarFactura(new Date(), getRand(0, 5)*25, pedido);
			}
		}
		
		System.out.println("---------------");
		System.out.println("Recupero Facturas");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente "+cliente.getIdCliente()+" facturasInpagas "+cliente.getFacturasInpagas().size());
			for(FacturaDTO factura : cliente.getFacturasInpagas()) {
				System.out.println("\tFactura Recuperada "+factura.getId() + " "+ factura.getEstado()+" $"+ factura.getImporte());
			}
		}
		
		//TODO 0 Factura; Item Factura; NC; Pagos; PAGO-FACTURA
		
 		/*
 		ArrayList<Articulo> articulosNuevos= cargarArticulos();
		System.out.println("---------------");
 		System.out.println("Carga Articulos");
 		System.out.println("---------------");
		for (Articulo art : articulosNuevos) {
			art = ArticuloDao.getInstance().grabar(art);
			System.out.println(art.getId());
		}

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
		
		
		
		int maxFacturas=5;
		int maxItems=10;
		int maxCantidad=15;
		
		Random r = new Random();
		for (Cliente cli: misClientes){
			for(int iFactura =0; iFactura<r.nextInt(maxFacturas-1) + 1; iFactura++){
				Factura facturaNueva = new Factura(new Date(), r.nextInt(100-1) + 1, cli.getCuenta());
				facturaNueva.setEstado(Factura.STATUS_INPAGA);
				facturaNueva=facturaNueva.guardar();
				
				for(int iItem =0; iItem<r.nextInt(maxItems-1) + 1; iItem++){
					ItemFactura itemFactura = new ItemFactura(0,ArticuloDao.getInstance().getById(""), r.nextInt(maxCantidad-1) + 1,facturaNueva);
					itemFactura.guardar();
				}
				System.out.println("Factura guardada: "+facturaNueva.getIdMovimientoCtaCte());
			}
		}
		
		
		*/
		
	}
 	
 
	public static void persistirArticulos(){
		new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100).guardar();
		new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100).guardar();
		new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100).guardar();
		new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100).guardar();
		new Articulo(0, "00001109", "asd", 300, "bolsa", "gr", 35, 200, 100).guardar();
		new Articulo(0, "00001110", "2389", 300, "bolsa", "gr", 35, 200, 100).guardar();
	}
	
	public static void persistirClientes(){
		DomicilioDeFacturacion domicilio = new DomicilioDeFacturacion("Argentina", "Buenos Aires", "Lanus", "1824", "Arias", "255", "3", 3);
		
		new Cliente(0, "Accenture", 200, "34963780", domicilio, 42419999, "condicionFin").guardar();
		new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin").guardar();
		new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin").guardar();
		new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin").guardar();
		new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin").guardar();
		

	}
  	 
	public static void persistirPedidos() throws Exception, ParseException{
		new PedidoCte(1,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12).guardar();
		new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12).guardar();
		new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12).guardar();
		new PedidoCte(2,"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12).guardar();
	
	}

	private static int getRand(int min, int max) {
		return (new Random()).nextInt(max-min) + min;
	}

}