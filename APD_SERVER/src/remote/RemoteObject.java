package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import controller.Controller;
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
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
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
	public List<ArticuloDTO> getArticulos() throws RemoteException {
		return Controller.getInstance().getArticulos();
	}

	@Override
	public void agregarArticuloAPedido(String CodArticulo, int cant, int idPedido)
			throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().agregarArticuloAPedido(CodArticulo, cant, idPedido);
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
	public void evaluarStock(int idpedido) throws RemoteException {
		Controller.getInstance().evaluarStock(idpedido);
	}

	@Override
	public void aceptarPedidoDesp(int idpedido)
			throws RemoteException, ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().aceptarPedidoDesp(idpedido);
	}


/*	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws RemoteException {
		try {
			return Controller.getInstance().getOrdCompraRecibidas();
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	@Override
	public List<UbicacionDTO> getUbicacionesVacias() throws RemoteException {
		return Controller.getInstance().getUbicacionesVacias();
	}

	@Override
	public void ajusteInvCompra(int ordenDeCompraId, List<String> ubicaciones)
			throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException, LaUbicacionNoTieneSuficientesArticulosParaRemoverException, SuperaLaCantidadUbicableEnLaUbicacionException {
		Controller.getInstance().ajusteInvCompra(ordenDeCompraId, ubicaciones);
	}

	@Override
	public List<FacturaDTO> getFacturasInpagas(int clienteId) throws ObjetoInexistenteException, RemoteException {
		return Controller.getInstance().getFacturasInpagas(clienteId);
	}

	@Override
	public void pagarFactura(int idCliente, int nroFactura, float pago, String especie)
			throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException {
		Controller.getInstance().pagarFactura(idCliente, nroFactura, pago, especie);
	}

	@Override
	public void agregarPago(int idCliente, float pago, String especie)
			throws RemoteException, ObjetoInexistenteException {
		Controller.getInstance().agregarPago(idCliente, pago, especie);
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
	public ClienteDTO registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion,
			String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso,
			int numero) throws RemoteException {
		return Controller.getInstance().registrarCliente(razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
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
	public List<OrdenDeCompraDTO> getOrdCompraRecibidas() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
