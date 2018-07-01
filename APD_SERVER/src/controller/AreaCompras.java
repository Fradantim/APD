package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.OrdenDeCompraDao;
import dao.ProveedorDao;
import dao.ReservaArticuloDao;
import dto.OrdenDeCompraDTO;
import dto.ProveedorDTO;
import dto.ReservaArticuloDTO;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.MovimientoInventario;
import model.OrdenDeCompra;
import model.PedidoCte;
import model.Proveedor;
import model.ReservaArticulo;

public class AreaCompras {

	private static AreaCompras areaCompras;
	
	public static AreaCompras getInstance() {
		if(areaCompras==null) {
			areaCompras= new AreaCompras();
		}
		return areaCompras;
	}
	
	public void generarOrden(Articulo articulo, int cantidadACubrir, PedidoCte pedidoCte) throws ObjetoInexistenteException {
		int cantidadReservada=0;
		List<ReservaArticulo> reservas = ReservaArticuloDao.getInstance().getByStatus(ReservaArticuloDTO.STATUS_PENDIENTE);
		for(ReservaArticulo reserva: reservas) {
			if(reserva.getPedido().equals(pedidoCte) && reserva.getArticulo().equals(articulo)) {
				//Ya existe una orden de compra por este pedido, no corresponde generarle otra.
				return;
			}
			cantidadReservada+=reserva.getCantidad();
		}
		System.out.println("ped"+pedidoCte.getIdPedidoCliente()+" art"+articulo.getId());
		int cantidadAIngresar=0;
		List<OrdenDeCompra> ordenes = getOrdenesPorEstados(new String[] {OrdenDeCompraDTO.ESTADO_PENDIENTE,OrdenDeCompraDTO.ESTADO_RECIBIDO});
		for(OrdenDeCompra orden: ordenes) {
			cantidadAIngresar+=orden.getCantidad();
		}
		
		if(cantidadACubrir > cantidadAIngresar-cantidadReservada) {
			//no va a alcanzar el stock que ingrese, tengo que generar una orden de compra
			for(int contador=0; contador< Math.ceil(cantidadACubrir/articulo.getCantidadAComprar()); contador++) {
				OrdenDeCompra orden = new OrdenDeCompra(articulo.getId(), articulo.getCantidadAComprar(), pedidoCte.getIdPedidoCliente(),null);
				orden.setEstado(OrdenDeCompraDTO.ESTADO_PENDIENTE);
				orden.guardar();
			}
		}

		//independientemente tengo que generar una reserva
		generarReservaArticulo(articulo, pedidoCte, cantidadACubrir);
		
	}
	
	public void generarReservaArticulo(Articulo art, PedidoCte ped, int cant) throws ObjetoInexistenteException {
		OrdenDeCompra orden = new OrdenDeCompra(art.getId(), cant, ped.getIdPedidoCliente(), null);
		orden.setEstado(OrdenDeCompraDTO.ESTADO_ELEGIR_PROV);
		orden.guardar();
		ReservaArticulo reserva = new ReservaArticulo( cant, null, art.getCodDeBarras(), ped.getIdPedidoCliente(),orden.getIdOrdenCompra() );
		reserva.setEstado(ReservaArticuloDTO.STATUS_PENDIENTE);
		reserva.guardar();
	}
	

	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws ObjetoInexistenteException{
		List<OrdenDeCompra> ordenesPendientes = getOrdenesPorEstado(OrdenDeCompraDTO.ESTADO_PENDIENTE);
		for(OrdenDeCompra ordenPendiente: ordenesPendientes) {
			if(ordenPendiente.getFechaRecepcion()!=null && ordenPendiente.getFechaRecepcion().before(new Date())) {
				ordenPendiente.setEstado(OrdenDeCompraDTO.ESTADO_RECIBIDO);
				ordenPendiente.guardar();
			}
		}
		return getOrdenesDTOPorEstado(OrdenDeCompraDTO.ESTADO_RECIBIDO);
	}
	
	public List<OrdenDeCompraDTO> getOrdenesPendElecProveedor(){
		try {
			return getOrdenesDTOPorEstado(OrdenDeCompraDTO.ESTADO_ELEGIR_PROV);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public void evaluarReStock(MovimientoInventario Mov, int stockActual) throws ObjetoInexistenteException {
		List<OrdenDeCompra> ordenesPendientesDeUbicar 
			= getOrdenesPorEstadosYArticulo(new String[] {OrdenDeCompraDTO.ESTADO_ELEGIR_PROV,OrdenDeCompraDTO.ESTADO_PENDIENTE,
					OrdenDeCompraDTO.ESTADO_RECIBIDO}, Mov.getArticulo().getCodDeBarras());
		
		List<ReservaArticulo> reservasPendientes = ReservaArticuloDao.getInstance().getByStatusYArticulo(ReservaArticuloDTO.STATUS_PENDIENTE, Mov.getArticulo().getCodDeBarras());
		
		int cantidadPedida=0;
		for(OrdenDeCompra orden: ordenesPendientesDeUbicar) {
			cantidadPedida+=orden.getCantidad();
		}
		
		int cantidadReservada=0;
		for(ReservaArticulo reserva: reservasPendientes) {
			cantidadReservada+=reserva.getCantidad();
		}
		
		if(cantidadPedida-cantidadReservada > stockActual) {
			int cantidadDeOrdenes=1;
			while(cantidadDeOrdenes*Mov.getArticulo().getCantidadAComprar() < cantidadPedida-cantidadReservada) {
				cantidadDeOrdenes++;
			}
			
			for(int contador=0; contador<cantidadDeOrdenes; contador++) {
				generarOrden(Mov.getArticulo(), Mov.getArticulo().getCantidadAComprar(), null);
			}
		}
		
	}
	
	public List<ProveedorDTO> obtenerProveedores(int articuloId){
		return ProveedorDao.getInstance().getByArticulo(articuloId);
	}
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws ObjetoInexistenteException {
		OrdenDeCompra orden = OrdenDeCompraDao.getInstance().getById(ordenDeCompraId);
		Proveedor proveedor = ProveedorDao.getInstance().getById(proveedorId);
		orden.setProveedor(proveedor);
		orden.setFechaRecepcion(proveedor.getFechaRecepcion(orden.getArticulo(), orden.getCantidad()));
		orden.setFechaVencimiento(proveedor.getFechaVencimiento(orden.getArticulo(), orden.getFechaRecepcion()));
		orden.setEstado(OrdenDeCompraDTO.ESTADO_PENDIENTE);
		orden.guardar();
		//TODO actualizar la fecha en el producto 
	}
	
	public List <ReservaArticulo> getReservasPendientesPrevias(int articuloId, Date fechaPedido) throws ObjetoInexistenteException {
		
		List <ReservaArticulo> reservas = ReservaArticuloDao.getInstance().getByArtIdYfecha(articuloId,fechaPedido);
		return reservas;
	}
	
	public List<OrdenDeCompraDTO> getOrdenesDTOPorEstado(String estado) throws ObjetoInexistenteException{
		return OrdenDeCompraDao.getInstance().getDTOByStatus(estado);
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstado(String estado) throws ObjetoInexistenteException{
		return OrdenDeCompraDao.getInstance().getByStatus(estado);
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstados(String[] estados){
		ArrayList<OrdenDeCompra> ordenes = new ArrayList<>();
		for(String estado: estados) {
			try {
				for(OrdenDeCompra orden: OrdenDeCompraDao.getInstance().getByStatus(estado)) {
					ordenes.add(orden);
				}
			} catch (ObjetoInexistenteException e) {
				//System.out.println("Mensaje: " + e.getMessage());
			}
		}
		return ordenes;
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstadosYArticulo(String[] estados,String CodBarras) throws ObjetoInexistenteException{
		ArrayList<OrdenDeCompra> ordenes = new ArrayList<>();
		for(String estado: estados) {
			try {
			for(OrdenDeCompra orden: OrdenDeCompraDao.getInstance().getByStatusArt(estado,CodBarras)) {
				ordenes.add(orden);
				
			}		
			} catch (ObjetoInexistenteException e) {
				//System.out.println("Mensaje: " + e.getMessage());
			}

		}
		return ordenes;
	}
}
