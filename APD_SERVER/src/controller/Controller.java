package controller;

import java.util.List;

import exception.ArticuloInexistenteException;
import exception.ClienteInexistenteException;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.PedidoCteInexistenteException;
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
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ClienteInexistenteException {
		return administradorPedidos.generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
	}
	
	public List<ArticuloView> getArticulos(){
		return null;
	}
	
	public void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws PedidoCteInexistenteException, ArticuloInexistenteException {
		administradorPedidos.agregarArticuloAPedido(CodArticulo, cant, idPedido);
	}
	
	public void cerrarPedido(int idpedido) throws PedidoCteInexistenteException {
		administradorPedidos.cerrarPedido(idpedido);
	}
	
	public List<PedidoCteView> getPedidosPendAprobCred(){
		return administradorPedidos.getPedidosPendAprobCred();
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws PedidoCteInexistenteException{
		administradorPedidos.rechazarPedidoCred(idPedido, motivo);
	}

	public void aceptarPedidoCred(int idPedido, String motivo) throws ExisteUnPedidoConArticulosDeEsosReservadosException, PedidoCteInexistenteException{
		administradorPedidos.aceptarPedidoCred(idPedido, motivo);
	}
	
	public List<PedidoCteView> getPedidosPendDesp(){
		return administradorPedidos.getPedidosPendDesp();
	}
	
	public void evaluarStock(int idpedido){
		
	}
	
	public void aceptarPedidoDesp(int idpedido) throws PedidoCteInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ArticuloInexistenteException {
		administradorPedidos.aceptarPedidoDesp(idpedido);
	}
	
	public List<OrdenDeCompraView> getOrdCompraRecibidas(){
		return null;
	}
	
	public List<UbicacionView> getUbicacionesVacias(){
		return null;
	}
	
	public void ajusteInvCompra(int ordenDeCompraId, List <String> ubicaciones){
		
	}
	
	public List<FacturaView> getFacturasInpagas(int clienteId) {
		return administradorClientes.getFacturasInpagas(clienteId);
	}
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) {
		administradorClientes.pagarFactura(idCliente, nroFactura, pago, especie);
	}
	
	public void agregarPago(int idCliente, float pago, String especie) {
		administradorClientes.agregarPago(idCliente, pago, especie);
	}
	
	public void ajusteInvRotura(String idUbicacionArticulo, String codBarras, int cant, int usuarioEncargado, int usrAutorizador, String destino) {
		
	}
	
	public void ajusteInvAjuste(String codBarras, int cant, String idUbicacionArticulo) {
		
	}
	
	public List<ProveedorView> obtenerProveedores(int articuloId){
		return null;
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
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) {
		
	}
	
	public List<TipoDocumentoView> getTipoDocumentos(){
		return null;
	}
	
}
