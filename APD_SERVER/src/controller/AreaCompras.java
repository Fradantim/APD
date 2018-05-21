package controller;

import java.util.Date;
import java.util.List;

import dao.OrdenDeCompraDao;
import dao.ProveedorDao;
import dao.ReservaArticuloDao;
import dto.OrdenDeCompraDTO;
import dto.ProveedorDTO;
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
		List<ReservaArticulo> reservas = ReservaArticuloDao.getInstance().getByStatus(ReservaArticulo.STATUS_PENDIENTE);
		for(ReservaArticulo reserva: reservas) {
			if(reserva.getPedido().equals(pedidoCte)) {
				//Ya existe una orden de compra por este pedido, no corresponde generarle otra.
				return;
			}
			cantidadReservada+=reserva.getCantidad();
		}
		
		int cantidadAIngresar=0;
		List<OrdenDeCompra> ordenes = getOrdenesPorEstados(new String[] {OrdenDeCompra.ESTADO_PENDIENTE,OrdenDeCompra.ESTADO_RECIBIDO});
		for(OrdenDeCompra orden: ordenes) {
			cantidadAIngresar+=orden.getCantidad();
		}
		
		if(cantidadACubrir > cantidadAIngresar-cantidadReservada) {
			//no va a alcanzar el stock que ingrese, tengo que generar una orden de compra
			for(int contador=0; contador< Math.ceil(cantidadACubrir/articulo.getCantidadAComprar()); contador++) {
				OrdenDeCompra orden = new OrdenDeCompra(articulo.getId(), articulo.getCantidadAComprar(), pedidoCte.getIdPedidoCliente(),null);
				orden.setEstado(OrdenDeCompra.ESTADO_PENDIENTE);
				orden.guardar();
			}
		}

		//independientemente tengo que generar una reserva
		//TODO revisar si paso la orden de compra o como manejarlo
		OrdenDeCompra orden = new OrdenDeCompra();
		generarReservaArticulo(articulo, pedidoCte, cantidadACubrir,orden);
		
	}
	
	public void generarReservaArticulo(Articulo art, PedidoCte ped, int cant,OrdenDeCompra orden) throws ObjetoInexistenteException {
		ReservaArticulo reserva = new ReservaArticulo( cant, null, art.getCodDeBarras(), ped.getIdPedidoCliente(),orden.getIdOrdenCompra() );
		reserva.setEstado(ReservaArticulo.STATUS_PENDIENTE);
		reserva.guardar();
	}
	
	public OrdenDeCompra getOrden(int id){
		//TODO evaluar necesidad
		return null;
	}
	
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws ObjetoInexistenteException{
		List<OrdenDeCompra> ordenesPendientes = getOrdenesPorEstado(OrdenDeCompra.ESTADO_PENDIENTE);
		for(OrdenDeCompra ordenPendiente: ordenesPendientes) {
			if(ordenPendiente.getFechaRecepcion()!=null && ordenPendiente.getFechaRecepcion().before(new Date())) {
				ordenPendiente.setEstado(OrdenDeCompra.ESTADO_RECIBIDO);
				ordenPendiente.guardar();
			}
		}
		return getOrdenesDTOPorEstado(OrdenDeCompra.ESTADO_RECIBIDO);
	}
	
	public void evaluarReStock(MovimientoInventario Mov, int stockActual) throws ObjetoInexistenteException {
		List<OrdenDeCompra> ordenesPendientesDeUbicar 
			= getOrdenesPorEstadosYArticulo(new String[] {OrdenDeCompra.ESTADO_ELEGIR_PROV,OrdenDeCompra.ESTADO_PENDIENTE,
					OrdenDeCompra.ESTADO_RECIBIDO}, Mov.getArticulo().getCodDeBarras());
		
		List<ReservaArticulo> reservasPendientes = ReservaArticuloDao.getInstance().getByStatusYArticulo(ReservaArticulo.STATUS_PENDIENTE, Mov.getArticulo().getCodDeBarras());
		
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
		orden.setFechaRecepcion(proveedor.getFechaRecepcion(orden.getArticulo(), orden.getCantidad()));
		orden.setFechaVencimiento(proveedor.getFechaVencimiento(orden.getArticulo(), orden.getFechaRecepcion()));
		orden.setEstado(OrdenDeCompra.ESTADO_PENDIENTE);
		orden.guardar();
		//TODO actualizar la fecha en el producto 
	}
	
	public void cerrarReserva(PedidoCte pedidoCte) {
		//TODO hacer metodo
	}

	public List <ReservaArticulo> getReservasPendientesPrevias(int articuloId, Date fechaPedido) {
		//TODO hacer metodo
		return null;
	}
	
	public List<OrdenDeCompra> obtenerOrdenesCompra(Articulo articulo){
		//TODO evaluar necesidad
		return null;
	}
	
	public List<OrdenDeCompraDTO> getOrdenesDTOPorEstado(String estado){
		return OrdenDeCompraDao.getInstance().getDTOByStatus(estado);
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstado(String estado){
		return OrdenDeCompraDao.getInstance().getByStatus(estado);
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstados(String[] estados){
		//TODO hacer metodo
		return null;
	}
	
	public List<OrdenDeCompra> getOrdenesPorEstadosYArticulo(String[] estados,String CodBarras){
		//TODO hacer metodo
		return null;
	}
}
