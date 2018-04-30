package client;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.List;

import javax.naming.CommunicationException;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.ObjetoInexistenteException;

public class Runner {

	private BusinessDelegate bd;
	private Method[] metodos;
	private int columnas=120;
	private int columna3=(columnas/3)-1;
	
	public void NONONOexecute() {
		try {
			bd = new BusinessDelegate();
			metodos = this.getClass().getDeclaredMethods();
			int eleccion=0;
			getArticulos();
			/*while (eleccion != -1) {
				NONONOlistarMetodos();
			}*/
		} catch (CommunicationException e){
			System.out.println("Error: "+e.getMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void NONONOlistarMetodos() {
		for(int i=0; i < columnas/2;i++) {
			System.out.print("-+");
		}
		System.out.println();
		for(int i=0; i < columnas/2;i++) {
			System.out.print("+-");
		}
		System.out.println();	
		System.out.println("Elija una opción:");
		System.out.println("(-1)\tSalir");
		for (int i = 0; i < metodos.length; i++) {
			//System.out.println("("+i+")\t"+metodos[i].getName());
			if(i+2< metodos.length) {
				System.out.printf("%-"+columna3+"."+columna3+"s;%-"+columna3+"."+columna3+"s;%-"+columna3+"."+columna3+"s%n", "("+i+") "+metodos[i].getName(), "("+(i+1)+") "+metodos[i+1].getName(),"("+(i+2)+") "+metodos[i+2].getName());
				i+=2;
			} else if(i+1< metodos.length) {
				System.out.printf("%-"+columna3+"."+columna3+"s;%-"+columna3+"."+columna3+"s%n", "("+i+") "+metodos[i].getName(), "("+(i+1)+") "+metodos[i+1].getName());
				i+=1;
			} else {
				System.out.printf("%-"+columna3+"."+columna3+"s%n", "("+i+") "+metodos[i].getName());
			}
			
		}
		for(int i=0; i < columnas;i++) {
			System.out.print("-");
		}
		System.out.println();
		for(int i=0; i < columnas;i++) {
			System.out.print("-");
		}
		System.out.println();			
	}
	
	private void generarNuevoPedido(int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) throws ObjetoInexistenteException{ 

	}
	
	//TODO evaluar necesidad
	private void getArticulos() throws RemoteException{ 
		try {
			for(ArticuloDTO articulo: bd.getArticulos()) {
				System.out.println(articulo.getCodDeBarras());
			}
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void agregarArticuloAPedido(String CodArticulo,int cant,int idPedido) throws RemoteException, ObjetoInexistenteException{ 

	}
	
	private void cerrarPedido(int idpedido) throws RemoteException, ObjetoInexistenteException{ 

	}
	
	private void getPedidosPendAprobCred() throws RemoteException{ 

	}
	
	private void rechazarPedidoCred(int idPedido, String motivo) throws RemoteException, ObjetoInexistenteException{ 

	}

	private void aceptarPedidoCred(int idPedido, String motivo) throws RemoteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException{ 

	}
	
	private void getPedidosPendDesp() throws RemoteException{ 

	}

	//TODO Evaluar necesidad
	private void evaluarStock(int idpedido) throws RemoteException{ 

	}
	
	private void aceptarPedidoDesp(int idpedido) throws RemoteException, ObjetoInexistenteException, ExisteUnPedidoConArticulosDeEsosReservadosException{ 

	}
	
	private void getOrdCompraRecibidas() throws RemoteException{ 

	}
	
	private void getUbicacionesVacias() throws RemoteException{ 

	}
	
	private void ajusteInvCompra(int ordenDeCompraId, List<String> ubicaciones) throws RemoteException, ObjetoInexistenteException { 

	}
	
	private void getFacturasInpagas(int clienteId) throws ObjetoInexistenteException, RemoteException{ 

	}
	
	private void pagarFactura(int idCliente, int nroFactura, float pago, String especie) throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException { 

	}
	
	private void agregarPago(int idCliente, float pago, String especie) throws RemoteException, ObjetoInexistenteException { 

	}
	
	private void ajusteInvRotura(String codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException { 

	}
	
	private void ajusteInvAjuste(String codBarras, int cant, int idUbicacionArticulo) throws RemoteException, ObjetoInexistenteException, LaUbicacionNoTieneEsteArticuloException { 

	}
	
	private void obtenerProveedores(int articuloId) throws RemoteException{ 

	}
	
	//TODO Evaluar necesidad
	private void getStock(String codigoDeBarras) throws RemoteException{ 

	}
	
	private void registrarCliente(String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException{ 

	}
	
	private void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException{ 

	}
	
	private void bajaCliente(int idCliente) throws RemoteException{ 

	}
	
	private void asignarProveedor(int ordenDeCompraId, int proveedorId) throws RemoteException, ObjetoInexistenteException { 

	}
	
	private void getTipoDocumentos() throws RemoteException{ 

	}

}
