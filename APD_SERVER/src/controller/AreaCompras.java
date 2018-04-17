package controller;

import java.util.Date;
import java.util.List;

import model.Articulo;
import model.MovimientoInventario;
import model.OrdenDeCompra;
import model.PedidoCte;
import model.ReservaArticulo;
import view.OrdenDeCompraView;
import view.ProveedorView;

public class AreaCompras {

	private static AreaCompras areaCompras;
	
	public static AreaCompras getInstance() {
		if(areaCompras==null) {
			areaCompras= new AreaCompras();
		}
		return areaCompras;
	}
	
	public void generarOrden(String codigoDeBarras, int cantidad, int idPedidoCliente) {
		
	}
	
	public void generarReservaArticulo(Articulo art, PedidoCte ped, int cant) {
		
	}
	
	public OrdenDeCompra getOrden(int id){
		return null;
	}
	
	public List<OrdenDeCompraView> getOrdCompraRecibidas(){
		return null;
	}
	
	public void evaluarReStock(int articuloId, MovimientoInventario Mov) {
		
	}
	
	public List<ProveedorView> obtenerProveedores(int articuloId){
		return null;
	}
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) {
		
	}
	
	public void cerrarReserva(PedidoCte pedidoCte) {
		
	}

	public List <ReservaArticulo> getReservasPendientesPrevias(int articuloId, Date fechaPedido) {
		return null;
	}
	
	public List<OrdenDeCompra> obtenerOrdenesCompra(Articulo articulo){
		return null;
	}
}
