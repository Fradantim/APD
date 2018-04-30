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
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.ObjetoInexistenteException;
import interfaces.RemoteInterface;

public class BusinessDelegate {

	private RemoteInterface ri;
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
	
	public int generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal,
			String calle, String altura, String piso, int numero) throws CommunicationException,  ObjetoInexistenteException {
		try {
			return ri.generarNuevoPedido(idCli, pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<ArticuloDTO> getArticulos() throws CommunicationException {
		try {
			System.out.println("eeeeeee");
			return ri.getArticulos();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.agregarArticuloAPedido(CodArticulo, cant, idPedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
		
	}

	public void cerrarPedido(int idpedido) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.cerrarPedido(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
		
	}

	public List<PedidoCteDTO> getPedidosPendAprobCred() throws CommunicationException {
		try {
			return ri.getPedidosPendAprobCred();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void rechazarPedidoCred(int idPedido, String motivo) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.rechazarPedidoCred(idPedido, motivo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void aceptarPedidoCred(int idPedido, String motivo)
			throws CommunicationException,  ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException {
		try {
			ri.aceptarPedidoCred(idPedido, motivo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<PedidoCteDTO> getPedidosPendDesp() throws CommunicationException {
		try {
			return ri.getPedidosPendDesp();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void evaluarStock(int idpedido) throws CommunicationException {
		try {
			ri.evaluarStock(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void aceptarPedidoDesp(int idpedido)
			throws CommunicationException,  ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException {
		try {
			ri.aceptarPedidoDesp(idpedido);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}		
	}

	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws CommunicationException {
		try {
			return ri.getOrdCompraRecibidas();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<UbicacionDTO> getUbicacionesVacias() throws CommunicationException {
		try {
			return ri.getUbicacionesVacias();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void ajusteInvCompra(int ordenDeCompraId, List<String> ubicaciones) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.ajusteInvCompra(ordenDeCompraId, ubicaciones);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws CommunicationException,  ObjetoInexistenteException {
		try {
			return ri.getFacturasInpagas(clienteId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie)
			throws CommunicationException,  ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		try {
			ri.pagarFactura(idCliente, nroFactura, pago, especie);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void agregarPago(int idCliente, float pago, String especie) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.agregarPago(idCliente, pago, especie);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador)
			throws CommunicationException,  ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException {
		try {
			ri.ajusteInvRotura(codBarras, idUbicacion, cantidad, encargado, usrAutorizador);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo)
			throws CommunicationException,  ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException {
		try {
			ri.ajusteInvAjuste(codBarras, cant, idUbicacionArticulo);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<ProveedorDTO> obtenerProveedores(int articuloId) throws CommunicationException {
		try {
			return ri.obtenerProveedores(articuloId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public int getStock(String codigoDeBarras) throws CommunicationException {
		try {
			return ri.getStock(codigoDeBarras);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public ClienteDTO registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero) throws CommunicationException {
		try {
			return ri.registrarCliente(razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel,
			String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle,
			String altura, String piso, int numero) throws CommunicationException {
		try {
			ri.modificacionCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void bajaCliente(int idCliente) throws CommunicationException {
		try {
			ri.bajaCliente(idCliente);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public void asignarProveedor(int ordenDeCompraId, int proveedorId) throws CommunicationException,  ObjetoInexistenteException {
		try {
			ri.asignarProveedor(ordenDeCompraId, proveedorId);
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}

	public List<TipoDocumentoDTO> getTipoDocumentos() throws CommunicationException {
		try {
			return ri.getTipoDocumentos();
		} catch (RemoteException e) {
			throw new CommunicationException("Error de comunicacion");
		}
	}
}
