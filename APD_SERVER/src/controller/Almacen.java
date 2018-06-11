package controller;

import java.util.List;

import dao.ArticuloDao;
import dao.OrdenDeCompraDao;
import dao.UbicacionDao;
import dto.ArticuloDTO;
import dto.UbicacionDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import model.Articulo;
import model.ItemPedidoCte;
import model.OrdenDeCompra;
import model.Ubicacion;

public class Almacen {

	private static Almacen almacen;
	
	public static Almacen getInstance() {
		if(almacen==null) {
			almacen= new Almacen();
		}
		return almacen;
	}
	
	public List<ArticuloDTO> getArticulos(){
		return ArticuloDao.getInstance().getAllDTO();
	}

/*	
	public List <UbicacionDTO> getUbicacionesVacias(){

	
	public List <UbicacionDTO> getUbicacionesVacias() throws ObjetoInexistenteException{

		return UbicacionDao.getInstance().getVacias();
	}
	
	public void ajusteInvCompra(int ordenDeCompraId, List <String> ubicacionesIds) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		OrdenDeCompra ordenDeCompra = OrdenDeCompraDao.getInstance().getById(ordenDeCompraId);
		List<Ubicacion> ubicaciones = UbicacionDao.getInstance().getByIds(ubicacionesIds);
		Articulo articuloIngresado = ordenDeCompra.getArticulo();
		
		articuloIngresado.ajusteInvCompra(ordenDeCompra, ubicaciones);
	}*/
	
	public void ajusteInvVenta(ItemPedidoCte itemPedido, int facturaId) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Articulo articulo = ArticuloDao.getInstance().getById(itemPedido.getArticulo().getCodDeBarras());
		articulo.ajusteInvVenta(itemPedido.getCantidad(), facturaId);
	}
	
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Articulo articulo = ArticuloDao.getInstance().getById(codBarras);
		articulo.ajusteInvRotura(idUbicacion , cantidad, encargado, usrAutorizador);
	}
	
	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Articulo articulo = ArticuloDao.getInstance().getById(codBarras);
		articulo.ajusteInvAjuste(cant, idUbicacionArticulo);
	}
	
	public int getStock(String codigoDeBarras) throws ObjetoInexistenteException {
		return ArticuloDao.getInstance().getById(codigoDeBarras).getStock();
	}
}
