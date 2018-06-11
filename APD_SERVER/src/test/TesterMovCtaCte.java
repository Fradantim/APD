package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.AjusteDao;
import dao.ArticuloDao;
import dao.ClienteDao;
import dao.FacturaDao;
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
import model.NotaCredito;
import model.Pago;
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
		int maxFacturas=3;
		
		int minItems=1;
		int maxItems=2;//ArticuloDao.getInstance().getAll().size();
		
		int minCantidad=1;
		int maxCantidad=20;
		
		System.out.println("---------------");
		System.out.println("Carga Facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cargando facturas para cliente "+cliente.getIdCliente());
			for(int i1=0; i1<getRand(minFacturas, maxFacturas);i1++) {
				PedidoCte pedido = new PedidoCte(cliente.getIdCliente(),"Argentina","BSAS","PARTIDO","1666","calle","altura","PB",12);
				pedido.guardar();
				for(int i2=0; i2<getRand(minItems, maxItems);i2++) {
					pedido.agregarArticulo(ArticuloDao.getInstance().getByRealId(i2+1), getRand(minCantidad, maxCantidad));
				}
				cliente.generarFactura(new Date(), getRand(0, 4)*25, pedido);
				//cliente.generarFactura(new Date(), 0, pedido);
			}
		}
		
		System.out.println("---------------");
		System.out.println("Recupero Facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente "+cliente.getIdCliente()+" facturasInpagas "+cliente.getFacturasInpagas().size()+
					" Saldo $"+cliente.getSaldo());
			for(FacturaDTO factura : cliente.getFacturasInpagas()) {
				System.out.println("\tFactura Recuperada "+factura.getId() + " "+ factura.getEstado()+" $"+ factura.getImporte());
			}
		}
		
		/*
		//Esto tiene que seguir funcionando
		System.out.println("---------------");
		System.out.println("Genero NC no asociadas a facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente preNCs"+cliente.getIdCliente()+" Saldo $"+cliente.getSaldo());
			for(FacturaDTO factura : cliente.getFacturasInpagas()) {
				NotaCredito mov = new NotaCredito(new Date(), -factura.getImporte()/2, null);
				cliente.agregarMovimientoNotaDeCredito(mov);
				System.out.println("\tNC guardada "+mov.getIdMovimientoCtaCte() +" $"+ mov.getImporte());
			}
			System.out.println("\tCliente postNCs"+cliente.getIdCliente()+" Saldo $"+cliente.getSaldo());
		}
		
		System.out.println("---------------");
		System.out.println("Genero NC asociadas a facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente preNCs"+cliente.getIdCliente()+" Saldo $"+cliente.getSaldo());
			for(FacturaDTO factura : cliente.getFacturasInpagas()) {
				Factura facturaABonificar=FacturaDao.getInstance().getById(factura.getId());
				NotaCredito mov = new NotaCredito(new Date(), -factura.getImporte()/2, facturaABonificar);
				cliente.agregarMovimientoNotaDeCredito(mov);
				System.out.println("\tNC guardada "+mov.getIdMovimientoCtaCte() +" $"+ mov.getImporte()+" bonifica a Factura "+mov.getFacturaBonificada().getIdMovimientoCtaCte()+" nc>factBonif>estado:"+mov.getFacturaBonificada().getEstado());
			}
			System.out.println("\tCliente postNCs"+cliente.getIdCliente()+" Saldo $"+cliente.getSaldo());
		}
		*/
		
		/*
		//Esto tiene que seguir funcionando
		int maxCantPagosPorFactura=3;
		System.out.println("---------------");
		System.out.println("Genero Pagos asociadas a facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente "+cliente.getIdCliente()+" prePagos Saldo $"+cliente.getSaldo());
			for(FacturaDTO factura : cliente.getFacturasInpagas()) {
				Factura facturaM = FacturaDao.getInstance().getById(factura.getId());
				int cantPagos=getRand(1, maxCantPagosPorFactura);
				System.out.println("\tCargo pagos a factura "+facturaM.getIdMovimientoCtaCte()+" ($"+facturaM.getImporte()+" "+facturaM.getBonificacion()+"%)");
				for(int i1=0; i1<cantPagos; i1++) {
					float monto= -(factura.getImporte()*(1-factura.getBonificacion()/100F))/cantPagos;
					cliente.pagarFactura(facturaM.getIdMovimientoCtaCte(),monto, Pago.ESPECIE_BONIFICABLE);
					System.out.println("\t\t("+i1+")Pago por $"+monto);
				}
				
				System.out.println("\tRecupero pagos a factura "+facturaM.getIdMovimientoCtaCte()+" ($"+facturaM.getImporte()+" "+facturaM.getBonificacion()+"%) pendienteAbonar: $"+facturaM.getPendienteDeAbonar());
				List<Pago> pagosAsociados = facturaM.getPagosAsociados();
				for(Pago pago: pagosAsociados) {
					System.out.println("\t\tPago guardado: "+pago.getIdMovimientoCtaCte()+" $"+pago.getImporte());
				}
			}
			System.out.println("\tCliente "+cliente.getIdCliente()+" postPagos Saldo $"+cliente.getSaldo());
		}*/
		
		int maxCantPagosPorFactura=3;
		System.out.println("---------------");
		System.out.println("Genero Pagos excedientes a los montos de facturas");
		System.out.println("---------------");
		for(Cliente cliente: ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente "+cliente.getIdCliente()+" prePagos Saldo $"+cliente.getSaldo());
			List<FacturaDTO> facturasDTO = cliente.getFacturasInpagas();
			//pago la 1er factura de mas
			
			Factura facturaAPagar=FacturaDao.getInstance().getById(facturasDTO.get(0).getId());
			cliente.pagarFactura(facturaAPagar.getIdMovimientoCtaCte(), -(facturaAPagar.getImporte()*(1-facturaAPagar.getBonificacion()/100F))-10, Pago.ESPECIE_BONIFICABLE);
			
			System.out.println("\tFactura "+facturaAPagar.getIdMovimientoCtaCte() +" ($"+facturaAPagar.getImporte()+" "+facturaAPagar.getBonificacion()+"%)");
			for(Pago pago: facturaAPagar.getPagosAsociados()) {
				System.out.println("\t\tPago guardado: "+pago.getIdMovimientoCtaCte()+" $"+pago.getImporte());
			}
			System.out.println("Cliente "+cliente.getIdCliente()+" postPagos Saldo $"+cliente.getSaldo());
			System.out.println("- - - - - - - -");
		}
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
		/*new Cliente(0, "YPF", 200, "12314", domicilio, 123, "condicionFin").guardar();
		new Cliente(0, "UADE", 200, "4332", domicilio, 1234, "condicionFin").guardar();
		new Cliente(0, "Shell", 200, "54666", domicilio, 12345, "condicionFin").guardar();
		new Cliente(0, "Test", 200, "88777", domicilio, 123456, "condicionFin").guardar();*/
		

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