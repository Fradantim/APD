package controller;

import java.util.List;

import dao.ArticuloDao;
import dao.OrdenDeCompraDao;
import dao.UbicacionDao;
import exception.ArticuloInexistenteException;
import exception.OrdenDeCompraInexistenteException;
import exception.UbicacionInexistenteException;
import model.Articulo;
import model.ItemPedidoCte;
import model.Lote;
import model.OrdenDeCompra;
import model.Ubicacion;
import model.Usuario;
import view.ArticuloView;
import view.UbicacionView;

public class Almacen {

	private static Almacen almacen;
	
	public static Almacen getInstance() {
		if(almacen==null) {
			almacen= new Almacen();
		}
		return almacen;
	}
	
	public List<ArticuloView> getArticulos(){
		return ArticuloDao.getInstance().getAll();
	}
	
	public List <UbicacionView> getUbicacionesVacias(){
		return UbicacionDao.getInstance().getVacias();
	}
	
	public void ajusteInvCompra(int ordenDeCompraId, List <String> ubicacionesIds) throws OrdenDeCompraInexistenteException, UbicacionInexistenteException {
		OrdenDeCompra ordenDeCompra = OrdenDeCompraDao.getInstance().getById(ordenDeCompraId);
		List<Ubicacion> ubicaciones = UbicacionDao.getInstance().getByIds(ubicacionesIds);
		Articulo articuloIngresado = ordenDeCompra.getArticulo();
		
		articuloIngresado.ajusteInvCompra(ordenDeCompra, ubicaciones);
		
		
	}
	
	public void ajusteInvVenta(ItemPedidoCte itemPedido, int facturaId) throws ArticuloInexistenteException {
		Articulo articulo = ArticuloDao.getInstance().getById(itemPedido.getArticulo().getCodDeBarras());
		articulo.ajusteInvVenta(itemPedido.getCantidad(), facturaId);
		
	}
	
	public void ajusteInvRotura(String idUbicacionArticulo, String codBarras, int cant, Usuario encargado, Usuario usrAutorizador, String destino) {
		
	}
	
	public void ajusteInvAjuste(String codBarras, int cant, String idUbicacionArticulo) {
		
	}
	
	public void ubicarArticuloLote(Articulo articulo, Lote lote, int cant, Ubicacion ubicacion) {
		
	}
	
	public Lote buscarLote(String idUbicacionArticulo){
		return null;
	}
	
	public Ubicacion obtenerUbicacionLibre(){
		return null;
	}
	
	public Articulo obtenerArticulo(String codigoDeBarras){
		return null;
	}
	
	public boolean obtenerArticuloStock(int cantidad){
		return true;
	}
	
	
	public List <Ubicacion> getUbicaciones(){
		return null;
	}
	
	public int getStock(String codigoDeBarras) {
		return 0;
	}
}
