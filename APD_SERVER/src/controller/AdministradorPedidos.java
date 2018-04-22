package controller;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.ArticuloDao;
import dao.PedidoCteDao;
import dao.ReservaArticuloDao;
import exception.ArticuloInexistenteException;
import exception.ClienteInexistenteException;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.PedidoCteInexistenteException;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Remito;
import model.ReservaArticulo;
import view.PedidoCteView;

public class AdministradorPedidos {
	
	private static AdministradorPedidos administradorPedidos;
	private AreaTransporte areaTransporte;
	private AdministradorClientes administradorClientes;
	private AreaCompras areaCompras;
	private Almacen almacen;
	
	public AdministradorPedidos() {
		areaTransporte=AreaTransporte.getInstance();
		administradorClientes=AdministradorClientes.getInstance();
		administradorPedidos=AdministradorPedidos.getInstance();
		areaCompras=AreaCompras.getInstance();
		almacen=Almacen.getInstance();
	}
	
	
	public static AdministradorPedidos getInstance() {
		if(administradorPedidos==null) {
			administradorPedidos= new AdministradorPedidos();
		}
		return administradorPedidos;
	}
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ClienteInexistenteException{
		PedidoCte nuevoPedidoCte= new PedidoCte(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
		nuevoPedidoCte.setEstado(PedidoCte.ESTADO_NUEVO);
		nuevoPedidoCte = nuevoPedidoCte.guardar();
		return nuevoPedidoCte.getIdPedidoCliente();
	}
	
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido) throws PedidoCteInexistenteException, ArticuloInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.agregarArticulo(ArticuloDao.getInstance().getById(CodArticulo), cant);
		
	}
	
	public void cerrarPedido(int idPedido) throws PedidoCteInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCte.ESTADO_PENDIENTE_APROB_CRED);
		pedido.setFechaGeneracion(new Date());
		pedido.guardar();
	}
	
	public List<PedidoCteView> getPedidosPendAprobCred(){
		return getPedidosPorEstado(PedidoCte.ESTADO_PENDIENTE_APROB_CRED);
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws PedidoCteInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCte.ESTADO_APROB_CRED_RECH);
		pedido.guardar();
	}
	
	public void aceptarPedidoCred(int idPedido, String motivo) throws ExisteUnPedidoConArticulosDeEsosReservadosException, PedidoCteInexistenteException{
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCte.ESTADO_APROB_CRED_RECH);
		pedido.guardar();
	}
	
	public void evaluarStock(int idPedido) throws PedidoCteInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		List<ItemPedidoCte> itemsPedido = pedido.getItems();
		
		boolean stockSuficiente = true;
		for(ItemPedidoCte item : itemsPedido) {
			if(item.getCantidad() > almacen.getStock(item.getArticulo().getCodDeBarras())){
				//hay un item del pedido para el cual no hay stock suficiente, marco el pedido como pendiente de stock y termino de procesar
				if(stockSuficiente) {
					//este guardar se ejecutaria solo con el primer item del que no hay stock suficiente
					pedido.setEstado(PedidoCte.ESTADO_STOCK_PENDIENTE);
					pedido.guardar();
				}
				stockSuficiente=false;
				areaCompras.generarOrden(item.getArticulo(), item.getCantidad(), pedido);
			}
		}

		if(stockSuficiente) {
			pedido.setEstado(PedidoCte.ESTADO_STOCK_SUFICIENTE);
			pedido.guardar();
		}
	}
	
	public void aceptarPedidoDesp(int idPedido) throws PedidoCteInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ArticuloInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		
		//evaluo si no hay un pedido mas antiguo a este que tenga reservas pendientes por alguno de los articulos de este pedido.
		List<ItemPedidoCte> itemsPedido = pedido.getItems();
		for(ItemPedidoCte item : itemsPedido) {
			List<ReservaArticulo> reservasPendientes = ReservaArticuloDao.getInstance().getByArticuloAndStatus(item.getArticulo().getCodDeBarras(),ReservaArticulo.STATUS_PENDIENTE);
			for(ReservaArticulo reservaPendiente: reservasPendientes) {
				if(reservaPendiente.getPedido().getFechaGeneracion().before(pedido.getFechaGeneracion())) {
					//existe al menos un pedido anterior con al menos una reserva abierta por un articulo que este nuevo pedido quiere despachar
					throw new ExisteUnPedidoConArticulosDeEsosReservadosException("El pedido "+pedido.getIdPedidoCliente()+
							" no puede despacharse por que (al menos) el pedido anterior "+reservaPendiente.getPedido().getIdPedidoCliente()+
							" tiene una reserva pendiente de cumplirse por el articulo "+item.getArticulo().getCodDeBarras());
				}
			}
		}
		
		//Si no encuentro inconsistencias...
		
		//logica para elegir bonificacion
		int bonificacion=0;
		if(new Random().nextBoolean()) {
			//50% chance de tener bonificacion o no
			//bonificaciones del 25 al 75%
			bonificacion = new Random().nextInt(75-25) + 25;
		}
		
		int nroFactura=0;
		try {
			nroFactura=administradorClientes.generarFactura(pedido.getCliente().getIdCliente(), new Date(), bonificacion, pedido);
		} catch (ClienteInexistenteException e) {
			// TODO Consultar, que hago con estas excepcion? en la teoria no deberian ocurrir.
			e.printStackTrace();
			return;
		}
		
		Remito remito;
		try {
			remito = administradorClientes.generarRemito(pedido.getCliente().getIdCliente(), new Date(), pedido);
		} catch (ClienteInexistenteException e) {
			// TODO Consultar, que hago con estas excepcion? en la teoria no deberian ocurrir.
			e.printStackTrace();
			return;
		}
		
		for(ItemPedidoCte item : itemsPedido) {
			almacen.ajusteInvVenta(item, nroFactura);
		}
		
		Date fechaEstimadaRecepcion= areaTransporte.prepararDespacho(remito);
		
		pedido.setFechaDespacho(new Date());
		pedido.setFechaRecepcion(fechaEstimadaRecepcion);
		pedido.setEstado(PedidoCte.ESTADO_DESPACHADO);
		pedido.guardar();
		
		//Se fue stock, puede que algunos pedidos que antes tenian stock suficiente ya no lo tengan, evaluo cada caso correspondiente
		for(PedidoCte pedidoPendiente: PedidoCteDao.getInstance().getByStatus(PedidoCte.ESTADO_STOCK_PENDIENTE)) {
			evaluarStock(pedidoPendiente.getIdPedidoCliente());
		}
			
	}
	
	public void setFechaDespacho(int idPedidoCte, Date fechaRecepcion) {
		//TODO hacer metodo 
	}
	
	public void setFechaRecepcion(int idPedidoCte, Date fechaRecepcion) {
		//TODO hacer metodo 
	}
	
	public Date informarFechaRecepcion(int idPedidoCte) {
		//TODO hacer metodo 
		return null;
	}
	
	public void emitirOrdenDePedido(PedidoCte pedidoCte) {
		//TODO hacer metodo 
	}
	
	public List<PedidoCte> listarPedidosPendientes(){
		//TODO evaluar necesidad 
		return null;
	}
	
	public void informarPedidoCompleto(int idpedido) {
		//TODO evaluar necesidad
	}
	
	public List<PedidoCte> listarPedidosCompletos() {
		//TODO evaluar necesidad
		return null;
	}
	
	public List<ItemPedidoCte> obtenerItems(int idPedido){
		//TODO evaluar necesidad
		return null;
	}
		
	public List <PedidoCteView> getPedidosPendDesp() {
		List<PedidoCteView> pedidos= getPedidosPorEstados(new String[] {PedidoCte.ESTADO_PENDIENTE_APROB_CRED,PedidoCte.ESTADO_STOCK_PENDIENTE,PedidoCte.ESTADO_STOCK_SUFICIENTE});
		return pedidos;
	}
	
	private List <PedidoCteView> getPedidosPorEstado(String estado) {
		PedidoCteDao.getInstance().getByStatus(estado);
		return null;
	}
	
	private List <PedidoCteView> getPedidosPorEstados(String[] estados) {
		//TODO hacer metodo 
		return null;
	}
	
}
