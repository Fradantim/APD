package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import exception.UsuarioContrasenaIncorrectosException;

public interface RemoteInterface extends Remote{
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException, ObjetoInexistenteException;
	
	public void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws RemoteException, ObjetoInexistenteException;

	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,String codigoPostal, String calle, String altura, String piso, int numero)throws RemoteException;;
	
	public void BajaPedido(int idPedido) throws RemoteException;
	
	public List<ArticuloDTO> getArticulos() throws RemoteException;
	
	public void cerrarPedido(int idpedido) throws RemoteException, ObjetoInexistenteException;
	
	public List<PedidoCteDTO> getPedidosPendAprobCred() throws RemoteException;
	
	public void rechazarPedidoCred(int idPedido, String motivo) throws RemoteException, ObjetoInexistenteException;

	public void aceptarPedidoCred(int idPedido, String motivo) throws RemoteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException;
	
	public List<PedidoCteDTO> getPedidosPendDesp() throws RemoteException;

	//TODO Evaluar necesidad
	public void evaluarStock(int idpedido) throws RemoteException, ObjetoInexistenteException;
	
	public void aceptarPedidoDesp(int idpedido) throws RemoteException, ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException;
	
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws RemoteException, ObjetoInexistenteException;
	
	public List<UbicacionDTO> getUbicacionesVacias() throws RemoteException, ObjetoInexistenteException;
	
	public void ajusteInvCompra(int ordenDeCompraId, List <String> ubicaciones) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException ;
	
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException, RemoteException;
	
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException ;
	
	public void agregarPago(int idCliente, float pago, String especie) throws RemoteException, ObjetoInexistenteException ;
	
	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException ;
	
	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException ;
	
	public List<ProveedorDTO> obtenerProveedores(int articuloId) throws RemoteException;
	
	public int getStock(String codigoDeBarras) throws RemoteException,ObjetoInexistenteException;
	
	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException;
	
	public void bajaCliente(int idCliente) throws RemoteException;
	
	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws RemoteException, ObjetoInexistenteException ;
	
	public List<TipoDocumentoDTO> getTipoDocumentos() throws RemoteException;
	
	public void altaArticulo(int articuloId, String codBarras, String descripcion, float tamano, String presentacion, String unidad, float precio, int cantidadAComprar, int cantidadUbicable) throws RemoteException;

	public ClienteDTO registrarCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero, float limiteCredito, String nombre, String apellido, String password) throws RemoteException;
	
	public UsuarioDTO login(int idUsuario, String contrasena) throws RemoteException,UsuarioContrasenaIncorrectosException;
	
	public ClienteDTO getClienteByUsuario(int idUsuario) throws RemoteException, ObjetoInexistenteException;
	
	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException;

	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException;

	public List<ClienteDTO> getClientes() throws RemoteException;
}
