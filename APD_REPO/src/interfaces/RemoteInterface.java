package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.naming.CommunicationException;

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

public interface RemoteInterface extends Remote{
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException, ObjetoInexistenteException, CommunicationException;
	
	public void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws RemoteException, ObjetoInexistenteException, CommunicationException;

	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,String codigoPostal, String calle, String altura, String piso, int numero)throws RemoteException;;
	
	public void BajaPedido(int idPedido) throws RemoteException;
	
	public List<ArticuloDTO> getArticulos() throws RemoteException, CommunicationException;
	
	public void cerrarPedido(int idpedido) throws RemoteException, ObjetoInexistenteException, CommunicationException;
	
	public List<PedidoCteDTO> getPedidosPendAprobCred() throws RemoteException, CommunicationException;
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws RemoteException, ObjetoInexistenteException, CommunicationException;

	public void aceptarPedidoCred(int idPedido, String motivo) throws RemoteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException, CommunicationException;
	
	public List<PedidoCteDTO> getPedidosPendDesp() throws RemoteException, CommunicationException;

	//TODO Evaluar necesidad
	public void evaluarStock(int idpedido) throws RemoteException, ObjetoInexistenteException, CommunicationException;
	
	public void aceptarPedidoDesp(int idpedido) throws RemoteException, ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException, CommunicationException;
	
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws RemoteException, ObjetoInexistenteException, CommunicationException;
	
	public List<UbicacionDTO> getUbicacionesVacias() throws RemoteException, ObjetoInexistenteException, CommunicationException;
	
	public void ajusteInvCompra(int ordenDeCompraId, List <Integer> ubicaciones) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException, CommunicationException ;
	
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException, RemoteException, CommunicationException;
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException, CommunicationException ;
	
	public void agregarPago(int idCliente, float pago, String especie) throws RemoteException, ObjetoInexistenteException, CommunicationException ;
	
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException, CommunicationException ;
	
	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException, CommunicationException ;
	
	public List<ProveedorDTO> obtenerProveedores(int articuloId) throws RemoteException, CommunicationException;
	
	public int getStock(String codigoDeBarras) throws RemoteException,ObjetoInexistenteException, CommunicationException;
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException, CommunicationException;
	
	public void bajaCliente(int idCliente) throws RemoteException, CommunicationException;
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws RemoteException, ObjetoInexistenteException, CommunicationException ;
	
	public List<TipoDocumentoDTO> getTipoDocumentos() throws RemoteException, CommunicationException;
	
	public void altaArticulo(int articuloId, String codBarras, String descripcion, float tamano, String presentacion, String unidad, float precio, int cantidadAComprar, int cantidadUbicable) throws RemoteException, CommunicationException;

	public ClienteDTO registrarCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero, float limiteCredito, String nombre, String apellido, String password) throws RemoteException, CommunicationException;
	
	public UsuarioDTO login(int idUsuario, String contrasena) throws RemoteException,UsuarioContrasenaIncorrectosException;
	
	public ClienteDTO getClienteByUsuario(int idUsuario) throws RemoteException, ObjetoInexistenteException;
	
	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException;

	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException;

	public List<ClienteDTO> getClientes() throws RemoteException, CommunicationException;

	public List<OrdenDeCompraDTO> getOrdenesPendElecProveedor() throws RemoteException, CommunicationException;
}
