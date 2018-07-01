package controller;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.ArticuloDao;
import dao.PedidoCteDao;
import dao.ReservaArticuloDao;
import dto.PedidoCteDTO;
import dto.ReservaArticuloDTO;
import entities.PedidoCteEntity;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Remito;
import model.ReservaArticulo;

public class AdministradorPedidos {
	
	private static AdministradorPedidos administradorPedidos;
	private AreaTransporte areaTransporte;
	private AdministradorClientes administradorClientes;
	private AreaCompras areaCompras;
	private Almacen almacen;
	
	public AdministradorPedidos() {
		areaTransporte=AreaTransporte.getInstance();
		administradorClientes=AdministradorClientes.getInstance();
		areaCompras=AreaCompras.getInstance();
		almacen=Almacen.getInstance();
	}
	
	
	public static AdministradorPedidos getInstance() {
		if(administradorPedidos==null) {
			administradorPedidos= new AdministradorPedidos();
		}
		return administradorPedidos;
	}
	

	public int generarNuevoPedido(int idCli,String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ObjetoInexistenteException{
		PedidoCte nuevoPedidoCte= new PedidoCte( idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero,null);
		return nuevoPedidoCte.guardar();
	}

	
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido) throws ObjetoInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.agregarArticulo(ArticuloDao.getInstance().getById(CodArticulo), cant);
		//Actualizo el total bruto de los pedidos.	
		for (PedidoCteEntity pedidost: PedidoCteDao.getInstance().getAll()){
			PedidoCte pedidoart= pedidost.toNegocio();
			float totalbrut = 0.0F;
			for(ItemPedidoCte itemst: pedidoart.getItems()){
				totalbrut = totalbrut + itemst.getTotalBruto();
			}
			pedidoart.setTotalbruto(totalbrut);
			PedidoCteDao.getInstance().actualizar(pedidoart , totalbrut);
		}

	}
	
	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,
			String codigoPostal, String calle, String altura, String piso, int numero) {
		PedidoCteDao.getInstance().actualizarped (idPedido,  idCli,  pais,  provincia,  partido,codigoPostal,  calle,  altura,  piso,  numero);	
	}
	
	public void bajarPedido(int idPedido) {
		PedidoCteDao.getInstance().bajarPedido(idPedido);
		
	}


	public void cerrarPedido(int idPedido) throws ObjetoInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCteDTO.ESTADO_PENDIENTE_APROB_CRED);
		pedido.setFechaGeneracion(new Date());
		pedido.guardar();
	}
	
	public List<PedidoCteDTO> getPedidosPendAprobCred(){
		return getPedidosPorEstado(PedidoCteDTO.ESTADO_PENDIENTE_APROB_CRED);
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws ObjetoInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCteDTO.ESTADO_APROB_CRED_RECH);
		pedido.setMotivo(motivo);
		pedido.guardar();
	}
	
	public void aceptarPedidoCred(int idPedido, String motivo) throws ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException{
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		pedido.setEstado(PedidoCteDTO.ESTADO_APROB_CRED_APROB);
		pedido.setMotivo(motivo);
		pedido.guardar();
	}
	
	public void evaluarStock(int idPedido) throws ObjetoInexistenteException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		List<ItemPedidoCte> itemsPedido = pedido.getItems();
		
		boolean stockSuficiente = true;
		for(ItemPedidoCte item : itemsPedido) {
			if(item.getCantidad() > almacen.getStock(item.getArticulo().getCodDeBarras())){
				//hay un item del pedido para el cual no hay stock suficiente, marco el pedido como pendiente de stock y termino de procesar
				if(stockSuficiente) {
					//este guardar se ejecutaria solo con el primer item del que no hay stock suficiente
					pedido.setEstado(PedidoCteDTO.ESTADO_STOCK_PENDIENTE);
					pedido.guardar();
				}
				stockSuficiente=false;

				areaCompras.generarOrden(item.getArticulo(), item.getCantidad(), pedido);
			}
		}

		if(stockSuficiente) {
			pedido.setEstado(PedidoCteDTO.ESTADO_STOCK_SUFICIENTE);
			pedido.guardar();
		}
	}
	
	public void aceptarPedidoDesp(int idPedido) throws ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		PedidoCte pedido = PedidoCteDao.getInstance().getById(idPedido);
		
		//evaluo si no hay un pedido mas antiguo a este que tenga reservas pendientes por alguno de los articulos de este pedido.
		List<ItemPedidoCte> itemsPedido = pedido.getItems();
		for(ItemPedidoCte item : itemsPedido) {
			List<ReservaArticulo> reservasPendientes = ReservaArticuloDao.getInstance().getByArticuloAndStatus(item.getArticulo().getCodDeBarras(),ReservaArticuloDTO.STATUS_PENDIENTE);
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
		//50% chance de tener bonificacion o no
		if(new Random().nextBoolean()) {
			//bonificaciones 25/50/75%
			bonificacion = (new Random().nextInt(3-1) + 1)*25;
		}
		
		int nroFactura =administradorClientes.generarFactura(pedido.getCliente().getIdCliente(), new Date(), bonificacion, pedido);
			
		Remito remito= generarRemito(pedido.getCliente().getIdCliente(), new Date(), pedido);
		
		for(ItemPedidoCte item : itemsPedido) {
			almacen.ajusteInvVenta(item, nroFactura);
		}
		
		Date fechaEstimadaRecepcion= areaTransporte.prepararDespacho(remito);
		
		pedido.setFechaDespacho(new Date());
		pedido.setFechaRecepcion(fechaEstimadaRecepcion);
		pedido.setEstado(PedidoCteDTO.ESTADO_DESPACHADO);
		pedido.guardar();
		
		//Se fue stock, puede que algunos pedidos que antes tenian stock suficiente ya no lo tengan, evaluo cada caso correspondiente
		for(PedidoCte pedidoPendiente: PedidoCteDao.getInstance().getByStatus(PedidoCteDTO.ESTADO_STOCK_PENDIENTE)) {
			evaluarStock(pedidoPendiente.getIdPedidoCliente());
		}
			
	}
	
	public Remito generarRemito(int idCliente, Date fecha, PedidoCte pedido) throws ObjetoInexistenteException {
		//Cliente cliente = ClienteDao.getInstance().getById(idCliente);
		return pedido.generarRemito(fecha, pedido, pedido.getCliente());
	}
	
	public List <PedidoCteDTO> getPedidosPendDesp() {
		List<PedidoCteDTO> pedidos= getPedidosPorEstados(new String[] {PedidoCteDTO.ESTADO_APROB_CRED_APROB,
				PedidoCteDTO.ESTADO_STOCK_PENDIENTE,
				PedidoCteDTO.ESTADO_STOCK_SUFICIENTE});
		return pedidos;
	}
	
	private List <PedidoCteDTO> getPedidosPorEstado(String estado) {
		try {
			return PedidoCteDao.getInstance().getDTOByStatus(estado);
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	private List <PedidoCteDTO> getPedidosPorEstados(String[] estados) {
		ArrayList<PedidoCteDTO> pedidosDTO = new ArrayList<>();
		for(String estado: estados) {
			for(PedidoCte pedido: PedidoCteDao.getInstance().getByStatus(estado)) {
				try {
					pedidosDTO.add(pedido.toDTO());
				} catch (ObjetoInexistenteException e) {
					// Esto no deberia saltar....
					e.printStackTrace();
				}
			}
		}
		return pedidosDTO;
	}
	
	private List <PedidoCteDTO> getPedidosPorClienteYEstados(int idCliente, String[] estados) {
		ArrayList<PedidoCteDTO> pedidosDTO = new ArrayList<>();
		for(String estado: estados) {
			for(PedidoCte pedido: PedidoCteDao.getInstance().getByClienteAndStatus(idCliente,estado)) {
				try {
					pedidosDTO.add(pedido.toDTO());
				} catch (ObjetoInexistenteException e) {
					// Esto no deberia saltar....
					e.printStackTrace();
				}
			}
		}
		return pedidosDTO;
	}
	
	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException {
		return PedidoCteDao.getInstance().getOneByClienteAndStatus(idCliente, PedidoCteDTO.ESTADO_NUEVO);
	}


	/**
	 * Devuelve los pedidos que no estan nuevos/abiertos ni en estados finales
	 * @param idCliente
	 * @return
	 */
	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente) {
		List<PedidoCteDTO> pedidos= getPedidosPorClienteYEstados(idCliente, new String[] {PedidoCteDTO.ESTADO_PENDIENTE_APROB_CRED,
				PedidoCteDTO.ESTADO_APROB_CRED_RECH,
				PedidoCteDTO.ESTADO_APROB_CRED_APROB,
				PedidoCteDTO.ESTADO_STOCK_PENDIENTE,
				PedidoCteDTO.ESTADO_STOCK_SUFICIENTE});
		return pedidos;
	}
}
