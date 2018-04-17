package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ItemPedidoCte;
import model.PedidoCte;
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
	
	public int generarNuevoPedido(int idCli, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero){
		return 0;
	}
	
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idpedido) {
		
	}
	
	public void cerrarPedido(int idpedido) {
		
	}
	
	public List<PedidoCteView> getPedidosPendAprobCred(){
		return getPedidosPorEstado(PedidoCte.ESTADO_PENDIENTE_APROB_CRED);
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) {
		
	}
	
	public void aceptarPedidoCred(int idPedido, String motivo) {
		
	}
	
	public void evaluarStock(int idpedido) {
		
	}
	
	public void aceptarPedidoDesp(int idpedido) {
		
	}
	
	public void setFechaDespacho(int idPedidoCte, Date fechaRecepcion) {
		
	}
	
	public void setFechaRecepcion(int idPedidoCte, Date fechaRecepcion) {
		
	}
	
	public Date informarFechaRecepcion(int idPedidoCte) {
		return null;
	}
	
	public void emitirOrdenDePedido(PedidoCte pedidoCte) {
		
	}
	
	public List<PedidoCte> listarPedidosPendientes(){
		return null;
	}
	
	public void informarPedidoCompleto(int idpedido) {
		
	}
	
	public List<PedidoCte> listarPedidosCompletos() {
		return null;
	}
	
	public List<ItemPedidoCte> obtenerItems(int idPedido){
		return null;
	}
		
	public List <PedidoCteView> getPedidosPendDesp() {
		List<PedidoCteView> pedidos= getPedidosPorEstado(PedidoCte.ESTADO_PENDIENTE_APROB_CRED);
		pedidos.addAll(getPedidosPorEstado(PedidoCte.ESTADO_STOCK_PENDIENTE));
		pedidos.addAll(getPedidosPorEstado(PedidoCte.ESTADO_STOCK_SUFICIENTE));
		//acá va a haber que ordenar en algun momento...
		return pedidos;
	}
	
	private List <PedidoCteView> getPedidosPorEstado(String estado) {
		return null;
	}
}
