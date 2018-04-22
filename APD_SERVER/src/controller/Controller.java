package controller;

import java.util.List;

import exception.ObjetoInexistenteException;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import view.ArticuloView;
import view.ClienteView;
import view.FacturaView;
import view.OrdenDeCompraView;
import view.PedidoCteItemView;
import view.PedidoCteView;
import view.ProveedorView;
import view.TipoDocumentoView;
import view.UbicacionView;

public class Controller {

	private static Controller controller;
	private AdministradorClientes administradorClientes;
	private AdministradorPedidos administradorPedidos;
	private AreaCompras areaCompras;
	private Almacen almacen;
	private SistemaDeControlDeUsuarios sistCtrlUsuarios;
	
	
	public Controller(){
		administradorClientes=AdministradorClientes.getInstance();
		administradorPedidos=AdministradorPedidos.getInstance();
		areaCompras=AreaCompras.getInstance();
		almacen=Almacen.getInstance();
		sistCtrlUsuarios=SistemaDeControlDeUsuarios.getInstance();
	}
	
	public static Controller getInstance() {
		if(controller==null) {
			controller= new Controller();
		}
		return controller;
	}
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ObjetoInexistenteException {
		return administradorPedidos.generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
	}
	
	public List<ArticuloView> getArticulos(){
		return null;
	}
	
	public void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws ObjetoInexistenteException {
		administradorPedidos.agregarArticuloAPedido(CodArticulo, cant, idPedido);
	}
	
	public void cerrarPedido(int idpedido) throws ObjetoInexistenteException {
		administradorPedidos.cerrarPedido(idpedido);
	}
	
	public List<PedidoCteView> getPedidosPendAprobCred(){
		return administradorPedidos.getPedidosPendAprobCred();
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws ObjetoInexistenteException{
		administradorPedidos.rechazarPedidoCred(idPedido, motivo);
	}

	public void aceptarPedidoCred(int idPedido, String motivo) throws ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException{
		administradorPedidos.aceptarPedidoCred(idPedido, motivo);
	}
	
	public List<PedidoCteView> getPedidosPendDesp(){
		return administradorPedidos.getPedidosPendDesp();
	}
	
	public void evaluarStock(int idpedido){
		//TODO Evaluar necesidad
	}
	
	public void aceptarPedidoDesp(int idpedido) throws ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException {
		administradorPedidos.aceptarPedidoDesp(idpedido);
	}
	
	public List<OrdenDeCompraView> getOrdCompraRecibidas(){
		return areaCompras.getOrdCompraRecibidas();
	}
	
	public List<UbicacionView> getUbicacionesVacias(){
		return almacen.getUbicacionesVacias();
	}
	
	public void ajusteInvCompra(int ordenDeCompraId, List <String> ubicaciones) throws ObjetoInexistenteException {
		almacen.ajusteInvCompra(ordenDeCompraId, ubicaciones);
	}
	
	public List<FacturaView> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException {
		return administradorClientes.getFacturasInpagas(clienteId);
	}
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		administradorClientes.pagarFactura(idCliente, nroFactura, pago, especie);
	}
	
	public void agregarPago(int idCliente, float pago, String especie) throws ObjetoInexistenteException {
		administradorClientes.agregarPago(idCliente, pago, especie);
	}
	
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException {
		almacen.ajusteInvRotura(codBarras, idUbicacion, cantidad, encargado, usrAutorizador);
	}
	
	public void ajusteInvAjuste(String codBarras, int cant, String idUbicacionArticulo) {
		almacen.ajusteInvAjuste(codBarras, cant, idUbicacionArticulo);
	}
	
	public List<ProveedorView> obtenerProveedores(int articuloId){
		return areaCompras.obtenerProveedores(articuloId);
	}
	
	public List <PedidoCteItemView> obtenerArticulos(int idpedido){
		return null;
	}
	
	public int getStock(String codigoDeBarras){
		return 0;
	}
	
	public ClienteView registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		return administradorClientes.registrarCliente(razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
	}
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		administradorClientes.modificacionCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
	}
	
	public void bajaCliente(int idCliente) {
		administradorClientes.bajaCliente(idCliente);
	}
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws ObjetoInexistenteException {
		areaCompras.asignarProveedor(ordenDeCompraId, proveedorId);
	}
	
	public List<TipoDocumentoView> getTipoDocumentos(){
		return null;
	}
	
}
