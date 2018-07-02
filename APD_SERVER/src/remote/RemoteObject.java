package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import javax.naming.CommunicationException;

import controller.Controller;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoCteDTO;
import dto.ProveedorDTO;
import dto.TipoDocumentoDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import exception.UsuarioContrasenaIncorrectosException;
import interfaces.RemoteInterface;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface{
	
	private static final long serialVersionUID = -421644367989846198L;	

	public RemoteObject() throws RemoteException,  RemoteException {}

	@Override
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal,
			String calle, String altura, String piso, int numero) throws ObjetoInexistenteException {
		return Controller.getInstance().generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
	}

	@Override
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido)
			throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().agregarArticuloAPedido(CodArticulo, cant, idPedido);
	}

	@Override
	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,
			String codigoPostal, String calle, String altura, String piso, int numero)throws RemoteException {
		Controller.getInstance().modificarPedido(idPedido,  idCli,  pais,  provincia,  partido, codigoPostal,  calle,  altura,  piso,  numero);		
	}

	@Override
	public void BajaPedido(int idPedido) throws RemoteException {
		Controller.getInstance().bajarPedido(idPedido);
		
	}
	
	
	@Override
	public List<ArticuloDTO> getArticulos() throws RemoteException {
		return Controller.getInstance().getArticulos();
	}


	@Override
	public void cerrarPedido(int idpedido) throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().cerrarPedido(idpedido);
	}

	@Override
	public List<PedidoCteDTO> getPedidosPendAprobCred() throws RemoteException {
		return Controller.getInstance().getPedidosPendAprobCred();
	}

	@Override
	public void rechazarPedidoCred(int idPedido, String motivo) throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().rechazarPedidoCred(idPedido, motivo);
	}

	@Override
	public void aceptarPedidoCred(int idPedido, String motivo)
			throws RemoteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException {
		Controller.getInstance().aceptarPedidoCred(idPedido, motivo);
	}

	@Override
	public List<PedidoCteDTO> getPedidosPendDesp() throws RemoteException {
		return Controller.getInstance().getPedidosPendDesp();
	}

	@Override
	public void evaluarStock(int idpedido) throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().evaluarStock(idpedido);
	}

	@Override
	public void aceptarPedidoDesp(int idpedido)
			throws RemoteException, ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().aceptarPedidoDesp(idpedido);
	}


	@Override
	public List<UbicacionDTO> getUbicacionesVacias() throws ObjetoInexistenteException{
		return Controller.getInstance().getUbicacionesVacias();
	}

	@Override
	public void ajusteInvCompra(int ordenDeCompraId, List<Integer> ubicaciones)
			throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().ajusteInvCompra(ordenDeCompraId, ubicaciones);
	}


	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws  RemoteException, ObjetoInexistenteException {
		return Controller.getInstance().getFacturasInpagas(clienteId);
	}

	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie)
			throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		Controller.getInstance().pagarFactura(idCliente, nroFactura, pago, especie);
	}

	@Override
	public Integer agregarPago(int idCliente, float pago, String especie)
			throws RemoteException, ObjetoInexistenteException {
		
		return Controller.getInstance().agregarPago(idCliente, pago, especie);
	}

	@Override
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador)
			throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().ajusteInvRotura(codBarras, idUbicacion, cantidad, encargado, usrAutorizador);
	}

	@Override
	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo)
			throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().ajusteInvAjuste(codBarras, cant, idUbicacionArticulo);		
	}

	@Override
	public List<ProveedorDTO> obtenerProveedores(int articuloId) throws RemoteException {
		return Controller.getInstance().obtenerProveedores(articuloId);
	}

	@Override
	public int getStock(String codigoDeBarras) throws RemoteException, ObjetoInexistenteException {
		return Controller.getInstance().getStock(codigoDeBarras);
	}

	@Override
	public ClienteDTO registrarCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero, float limiteCredito, String nombre, String apellido, String password) throws RemoteException {
		return Controller.getInstance().registrarCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero, limiteCredito, nombre, apellido, password);
	}

	@Override
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel,
			String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle,
			String altura, String piso, int numero) throws RemoteException {
		Controller.getInstance().modificacionCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
	}

	@Override
	public void bajaCliente(int idCliente) throws RemoteException {
		Controller.getInstance().bajaCliente(idCliente);
	}

	@Override
	public void asignarProveedor(int ordenDeCompraId, int proveedorId)
			throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().asignarProveedor(ordenDeCompraId, proveedorId);
	}

	@Override
	public List<TipoDocumentoDTO> getTipoDocumentos() throws RemoteException {
		return Controller.getInstance().getTipoDocumentos();
	}

	@Override
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws RemoteException, ObjetoInexistenteException {
		return Controller.getInstance().getOrdCompraRecibidas();
	}

	@Override
	public void altaArticulo(int articuloId, String codBarras, String descripcion, 
			float tamano, String presentacion, String unidad, float precio, 
			int cantidadAComprar, int cantidadUbicable) throws RemoteException {
		Controller.getInstance().altaArticulo(articuloId, codBarras, descripcion, 
				tamano, presentacion, unidad, precio, 
				cantidadAComprar, cantidadUbicable);
	}

	@Override
	public UsuarioDTO login(int idUsuario, String contrasena)
			throws RemoteException, UsuarioContrasenaIncorrectosException {
		return Controller.getInstance().login(idUsuario, contrasena);
	}

	@Override
	public ClienteDTO getClienteByUsuario(int idUsuario) throws RemoteException, ObjetoInexistenteException {
		return Controller.getInstance().getClienteByUsuario(idUsuario);
	}

	@Override
	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException {
		return Controller.getInstance().getPedidoAbiertoByCliente(idCliente);
	}

	@Override
	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException{
		return Controller.getInstance().getPedidosPendientesByCliente(idCliente);
	}
	
	@Override
	public List<ClienteDTO> getClientes() throws RemoteException{
		return Controller.getInstance().getClientes();
	}

	@Override
	public List<OrdenDeCompraDTO> getOrdenesPendElecProveedor() throws RemoteException {
		return Controller.getInstance().getOrdenesPendElecProveedor();
	}

	@Override
	public FacturaDTO getById(int idfac) throws RemoteException, ObjetoInexistenteException, CommunicationException {
		return Controller.getInstance().getById(idfac);
	}


}
