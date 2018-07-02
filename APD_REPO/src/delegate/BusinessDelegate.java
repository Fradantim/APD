package delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
import interfaces.RemoteInterface;

public class BusinessDelegate implements RemoteInterface{

	private RemoteInterface ri;
	private static BusinessDelegate instancia = null;
	
	public static BusinessDelegate GetInstancia() throws CommunicationException{
		if(instancia==null)
			instancia = new BusinessDelegate();
		return instancia;
	}
		
	public BusinessDelegate() throws CommunicationException {
		try {
			ri=(RemoteInterface) Naming.lookup("//localhost/DasVerruckteLagerhaus");
		} catch (MalformedURLException e) {
			throw new CommunicationException("La direccion indicada no es correcta.");
		} catch(RemoteException e) {
			throw new CommunicationException("Error en la comunicacion.");
		} catch (NotBoundException e) {
			throw new CommunicationException("Servidor no disponible.");
		}
		
	}
//LISTO	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal,
			String calle, String altura, String piso, int numero) throws CommunicationException,  ObjetoInexistenteException {
		try {
			return ri.generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
//LISTO	
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.agregarArticuloAPedido(CodArticulo, cant, idPedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
		
	}
	
//LISTO	
	public void ModificarPedido(int idPedido,int idCli, String pais, String provincia, String partido, String codigoPostal,
				String calle, String altura, String piso, int numero) throws CommunicationException,  ObjetoInexistenteException, RemoteException {
			ri.modificarPedido(idPedido, idCli,  pais,  provincia,  partido,  codigoPostal,calle,  altura,  piso,  numero);		
	}

//LISTO



	public List<ArticuloDTO> getArticulos() throws CommunicationException {
		try {
			return ri.getArticulos();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}


	public void cerrarPedido(int idpedido) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.cerrarPedido(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
		
	}

	public List<PedidoCteDTO> getPedidosPendAprobCred() throws CommunicationException {
		try {
			return ri.getPedidosPendAprobCred();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws CommunicationException,  ObjetoInexistenteException {
		try {
			return ri.getFacturasInpagas(clienteId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public FacturaDTO getById (int idfact) throws CommunicationException,  ObjetoInexistenteException {
		try {
			return ri.getById(idfact);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}


	public void rechazarPedidoCred(int idPedido, String motivo) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.rechazarPedidoCred(idPedido, motivo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void aceptarPedidoCred(int idPedido, String motivo)
			throws CommunicationException,  ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException {
		try {
			ri.aceptarPedidoCred(idPedido, motivo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public List<PedidoCteDTO> getPedidosPendDesp() throws CommunicationException {
		try {
			return ri.getPedidosPendDesp();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void evaluarStock(int idpedido) throws CommunicationException, ObjetoInexistenteException {
		try {
			ri.evaluarStock(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void aceptarPedidoDesp(int idpedido)
			throws CommunicationException,  ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		try {
			ri.aceptarPedidoDesp(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}		
	}

	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws CommunicationException, ObjetoInexistenteException {
		try {
			return ri.getOrdCompraRecibidas();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public List<UbicacionDTO> getUbicacionesVacias() throws CommunicationException, ObjetoInexistenteException {
		try {
			return ri.getUbicacionesVacias();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void ajusteInvCompra(int ordenDeCompraId, List<Integer> ubicaciones) throws CommunicationException,  ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		try {
			ri.ajusteInvCompra(ordenDeCompraId, ubicaciones);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}


	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie)
			throws CommunicationException,  ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		try {
			ri.pagarFactura(idCliente, nroFactura, pago, especie);
		} catch (RemoteException | CommunicationException  e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public Integer agregarPago(int idCliente, float pago, String especie) throws CommunicationException,  ObjetoInexistenteException {
		try {
			Integer idfact = ri.agregarPago(idCliente, pago, especie);
			return idfact;
		} catch (RemoteException  | CommunicationException  e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador)
			throws CommunicationException,  ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		try {
			ri.ajusteInvRotura(codBarras, idUbicacion, cantidad, encargado, usrAutorizador);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo)
			throws CommunicationException,  ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		try {
			ri.ajusteInvAjuste(codBarras, cant, idUbicacionArticulo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public List<ProveedorDTO> obtenerProveedores(int articuloId) throws CommunicationException {
		try {
			return ri.obtenerProveedores(articuloId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public int getStock(String codigoDeBarras) throws CommunicationException, ObjetoInexistenteException {
		try {
			return ri.getStock(codigoDeBarras);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public ClienteDTO registrarCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero,float limiteCredito, String nombre, String apellido, String password) throws CommunicationException {
		try {
			return ri.registrarCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero,limiteCredito, nombre, apellido, password);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public List<ClienteDTO> getClientes() throws CommunicationException {
		try {
			return ri.getClientes();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel,
			String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle,
			String altura, String piso, int numero) throws CommunicationException {
		try {
			ri.modificacionCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void bajaCliente(int idCliente) throws CommunicationException {
		try {
			ri.bajaCliente(idCliente);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.asignarProveedor(ordenDeCompraId, proveedorId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public List<TipoDocumentoDTO> getTipoDocumentos() throws CommunicationException {
		try {
			return ri.getTipoDocumentos();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public void altaArticulo(int articuloId, String codBarras, String descripcion, float tamano, String presentacion, 
			String unidad, float precio, int cantidadAComprar, int cantidadUbicable) throws CommunicationException {
		try {
			ri.altaArticulo(articuloId, codBarras, descripcion, tamano, presentacion, unidad, precio, cantidadAComprar, cantidadUbicable);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public List<OrdenDeCompraDTO> getOrdenesPendElecProveedor() throws CommunicationException{
		try {
			return ri.getOrdenesPendElecProveedor();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	@Override
	public void modificarPedido(int idPedido, int idCli, String pais, String provincia, String partido,
			String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException, CommunicationException {
		try {
			ri.modificarPedido(idPedido,idCli,pais,provincia,partido,codigoPostal,calle,altura,piso,numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
		
	}
	@Override
	public void BajaPedido(int idPedido) throws RemoteException, CommunicationException {
		try {
			ri.BajaPedido(idPedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
		
	}
	
	
	public UsuarioDTO login(int idUsuario, String contrasena)
			throws RemoteException, UsuarioContrasenaIncorrectosException, CommunicationException {
		try {
			//System.out.println("wiiiiiiii");
			return ri.login(idUsuario,contrasena);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public ClienteDTO getClienteByUsuario(int idUsuario) throws RemoteException, ObjetoInexistenteException, CommunicationException {
		try {
			return ri.getClienteByUsuario(idUsuario);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	public PedidoCteDTO getPedidoAbiertoByCliente(int idCliente) throws RemoteException, ObjetoInexistenteException, CommunicationException {
		try {
			return ri.getPedidoAbiertoByCliente(idCliente);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}
	
	public List<PedidoCteDTO> getPedidosPendientesByCliente(int idCliente)
			throws RemoteException, ObjetoInexistenteException, CommunicationException {
		try {
			return ri.getPedidosPendientesByCliente(idCliente);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion "+e.getMessage());
		}
	}

	@Override
	public void registrarUsuario(int idUsuario, String nombre, String apellido, String nivelRol, String password)
			throws RemoteException, CommunicationException {
		ri.registrarUsuario(idUsuario, nombre, apellido, nivelRol, password);
	}


}
