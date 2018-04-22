package controller;

import java.util.List;

import dao.ArticuloDao;
import exception.ArticuloInexistenteException;
import model.Articulo;
import model.ItemPedidoCte;
import model.Lote;
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
		return null;
	}
	
	public void ajusteInvCompra(int ordenDeCompraIda, List <String> ubicaciones) {
		
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
