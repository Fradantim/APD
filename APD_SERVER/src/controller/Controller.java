package controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoCteDTO;
import dto.ProveedorDTO;
import dto.TipoDocumentoDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import exception.UsuarioContrasenaIncorrectosException;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;

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
		return administradorPedidos.generarNuevoPedido(idCli,pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
	}

	public void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws ObjetoInexistenteException {
		administradorPedidos.agregarArticuloAPedido(CodArticulo, cant, idPedido);
	}

	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,
			String codigoPostal, String calle, String altura, String piso, int numero) {
		administradorPedidos.modificarPedido(idPedido, idCli,  pais,  provincia,  partido,codigoPostal,  calle,  altura,  piso,  numero);	
	}

	public void bajarPedido(int idPedido) {
		administradorPedidos.bajarPedido(idPedido);
		
	}

	
	public int generarNuevoPedido(int idped, int idCli, Date fecgen, Date fecdes, Date fecrec,Float tot,String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero,String est, String mot) throws ObjetoInexistenteException {
//		return administradorPedidos.generarNuevoPedido(idped,idCli, fecgen, fecdes, fecrec,tot,pais, provincia, partido, codigoPostal, calle, altura, piso, numero,est,mot);
		return administradorPedidos.generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
	}
	
	
	
	public List<ArticuloDTO> getArticulos(){
		return almacen.getArticulos();
	}
	
	
	public void cerrarPedido(int idpedido) throws ObjetoInexistenteException {
		administradorPedidos.cerrarPedido(idpedido);
	}
	
	public List<PedidoCteDTO> getPedidosPendAprobCred(){
		return administradorPedidos.getPedidosPendAprobCred();
	}
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws ObjetoInexistenteException{
		administradorPedidos.rechazarPedidoCred(idPedido, motivo);
	}

	public void aceptarPedidoCred(int idPedido, String motivo) throws ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException{
		administradorPedidos.aceptarPedidoCred(idPedido, motivo);
	}
	
	public List<PedidoCteDTO> getPedidosPendDesp(){
		return administradorPedidos.getPedidosPendDesp();
	}
	
	public void evaluarStock(int idpedido) throws ObjetoInexistenteException{
		administradorPedidos.evaluarStock(idpedido);
	}
	
	public void aceptarPedidoDesp(int idpedido) throws ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		administradorPedidos.aceptarPedidoDesp(idpedido);
	}
	
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws ObjetoInexistenteException{
		return areaCompras.getOrdCompraRecibidas();
	}
	
	public List<UbicacionDTO> getUbicacionesVacias() throws ObjetoInexistenteException{
		return almacen.getUbicacionesVacias();
	}
	
	public void ajusteInvCompra(int ordenDeCompraId, List <Integer> ubicaciones) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		almacen.ajusteInvCompra(ordenDeCompraId, ubicaciones);
	}
	
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException {
		return administradorClientes.getFacturasInpagas(clienteId);
	}
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		administradorClientes.pagarFactura(idCliente, nroFactura, pago, especie);
	}
	
	public void agregarPago(int idCliente, float pago, String especie) throws ObjetoInexistenteException {
		administradorClientes.agregarPago(idCliente, pago, especie);
	}
	
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		almacen.ajusteInvRotura(codBarras, idUbicacion, cantidad, encargado, usrAutorizador);
	}
	
	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo) throws ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		almacen.ajusteInvAjuste(codBarras, cant, idUbicacionArticulo);
	}
	
	public List<ProveedorDTO> obtenerProveedores(int articuloId){
		return areaCompras.obtenerProveedores(articuloId);
	}
	
	public int getStock(String codigoDeBarras) throws ObjetoInexistenteException{
		return almacen.getStock(codigoDeBarras);
	}
	
	public ClienteDTO registrarCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero, float limiteCredito, String nombre, String apellido, String password) {
		return administradorClientes.registrarCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero , limiteCredito, nombre, apellido, password);
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
	
	public List<TipoDocumentoDTO> getTipoDocumentos(){
		return null;
	}

	public void altaArticulo(int articuloId, String codBarras, String descripcion, float tamano, String presentacion, String unidad, float precio, 
			int cantidadAComprar, int cantidadUbicable){
		almacen.altaArticulo(articuloId, codBarras, descripcion, tamano, presentacion, unidad, precio, cantidadAComprar, cantidadUbicable);
	}
	
	public UsuarioDTO login(int idUsuario, String contrasena) throws RemoteException, UsuarioContrasenaIncorrectosException {
		return sistCtrlUsuarios.login(idUsuario, contrasena);
	}

	public ClienteDTO getClienteByUsuario(int idUsuario) throws RemoteException, ObjetoInexistenteException {
		return administradorClientes.getClienteByUsuario(idUsuario);
	}

	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException {
		return administradorPedidos.getPedidoAbiertoByCliente(idCliente);
	}
	
	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException {
		return administradorPedidos.getPedidosPendientesByCliente(idCliente);
	}
	
	public List<ClienteDTO> getClientes() throws RemoteException{
		return administradorClientes.getClientes();
	}
}
