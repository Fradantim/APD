package client;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.naming.CommunicationException;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.FacturaDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoCteDTO;
import dto.ProveedorDTO;
import dto.UbicacionDTO;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.ObjetoInexistenteException;

public class Runner {

	BusinessDelegate bd;
	Method[] metodos;
	Scanner in;
	int columnas=120;
	
	public void NOexecute() {
		try {
			bd = new BusinessDelegate();
			metodos = this.getClass().getDeclaredMethods();
			int eleccion=0;
			in = new Scanner(System.in);
			while (eleccion != -1) {
				NONONOlistarMetodos();
				eleccion=in.nextInt();
				if(eleccion!=-1) {
					System.out.println("Eleccion: "+metodos[eleccion].getName());
					metodos[eleccion].invoke(this);
				}
			}
		} catch (Exception e){
			System.out.println("Error: "+e.getMessage());
		} 
	}
	
	void NONONOlistarMetodos() {
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
				System.out.printf(NOStringformatter(3), "("+i+") "+metodos[i].getName(), "("+(i+1)+") "+metodos[i+1].getName(),"("+(i+2)+") "+metodos[i+2].getName());
				i+=2;
			} else if(i+1< metodos.length) {
				System.out.printf(NOStringformatter(2), "("+i+") "+metodos[i].getName(), "("+(i+1)+") "+metodos[i+1].getName());
				i+=1;
			} else {
				System.out.printf(NOStringformatter(1), "("+i+") "+metodos[i].getName());
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
	
	void generarNuevoPedido() throws Exception{ 
		//int idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero
		System.out.println("Ingrese los datos separados por coma:\nint idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		int res= bd.generarNuevoPedido(Integer.parseInt(params[0]), params[1], params[2], params[3], params[4], params[5], params[6], params[7], Integer.parseInt(params[8]));
		System.out.println("Pedido cargado correctamente, id: "+res);
	}
	
	void getArticulos() throws RemoteException, CommunicationException{ 
		System.out.printf(NOStringformatter(7),
			"id", "codDeBarras","Desc","Presentacion","Tamano","Unidad","Precio");
		for(ArticuloDTO art: bd.getArticulos()) {
			System.out.printf(NOStringformatter(7),
				art.getId(), art.getCodDeBarras(),art.getDescripcion(),art.getPresentacion(),art.getTamano(),art.getUnidad(),"$"+art.getPrecioDeVenta());
		}

	}
	
	void agregarArticuloAPedido() throws RemoteException, ObjetoInexistenteException, NumberFormatException, CommunicationException{ 
		//
		System.out.println("Ingrese los datos separados por coma:\nString CodArticulo,int cant,int idPedido");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.agregarArticuloAPedido(params[0],Integer.parseInt(params[1]), Integer.parseInt(params[2]));;
		System.out.println("Item agregado al pedido correctamente");
	}
	
	void cerrarPedido(int idpedido) throws RemoteException, ObjetoInexistenteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos separados por coma:\nint idpedido");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.cerrarPedido(Integer.parseInt(params[0]));;
		System.out.println("pedido cerrado correctamente");
	}
	
	void getPedidosPendAprobCred() throws RemoteException, CommunicationException{ 
		System.out.printf(NOStringformatter(7),
				"idPedido", "fechaGeneracion","total","idCliente","saldoCliente","condicionFinanciera","estadoPedido");
		for(PedidoCteDTO ped: bd.getPedidosPendAprobCred()) {
			System.out.printf(NOStringformatter(7),
				ped.getId(), ped.getFechaGeneracion(), "$"+ped.getTotal(),ped,"$"+ped,ped,ped.getEstado());
		}
	}
	
	void rechazarPedidoCred() throws RemoteException, ObjetoInexistenteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos separados por coma:\nint idPedido, String motivo");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.rechazarPedidoCred(Integer.parseInt(params[0]),params[1]);;
		System.out.println("pedido rechazado correctamente");
	}

	void aceptarPedidoCred() throws RemoteException, ExisteUnPedidoConArticulosDeEsosReservadosException, ObjetoInexistenteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos separados por coma:\nint idPedido, String motivo");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.aceptarPedidoCred(Integer.parseInt(params[0]),params[1]);;
		System.out.println("pedido rechazado correctamente");
	}
	
	void getPedidosPendDesp() throws RemoteException, CommunicationException{ 
		System.out.printf(NOStringformatter(7),
				"idPedido", "fechaGeneracion","total","idCliente","saldoCliente","condicionFinanciera","estadoPedido");
		for(PedidoCteDTO ped: bd.getPedidosPendDesp()) {
			System.out.printf(NOStringformatter(7),
				ped.getId(), ped.getFechaGeneracion(), "$"+ped.getTotal(),"$",ped.getEstado());
		}
	}

	//TODO Evaluar necesidad
	void evaluarStock(int idpedido) throws Exception{ 
		System.out.println("Ingrese los datos:\nint idPedido");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.evaluarStock(Integer.parseInt(params[0]));
	}
	
	void aceptarPedidoDesp(int idpedido) throws Exception{ 
		System.out.println("Ingrese los datos:\nint idPedido");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.aceptarPedidoDesp(Integer.parseInt(params[0]));
	}
	
	void getOrdCompraRecibidas() throws Exception{ 
		System.out.printf(NOStringformatter(5),
				"idPedido", "fechaGeneracion","total","idCliente","saldoCliente");
		for(OrdenDeCompraDTO ord: bd.getOrdCompraRecibidas()) {
			System.out.printf(NOStringformatter(5),
				ord.getId(), ord.getArticulo().getCodDeBarras(), ord.getArticulo().getDescripcion(),ord.getCantidad(),ord.getEstado());
		}
	}
	
	void getUbicacionesVacias() throws RemoteException, CommunicationException, ObjetoInexistenteException{ 
		System.out.printf(NOStringformatter(5),
				"idUbicacion", "calle","bloque","estante","posicion");
		for(UbicacionDTO ub: bd.getUbicacionesVacias()) {
			System.out.printf(NOStringformatter(5),
				ub.getId(), ub.getCalle(), ub.getBloque(),ub.getEstante(),ub.getPosicion());
		}
	}
	
	void ajusteInvCompra() throws Exception{ 
		System.out.println("Ingrese los datos :\nint ordenDeCompraId");
		String ordenDeCompraId= in.nextLine();
		ordenDeCompraId= in.nextLine();
		System.out.println("Ingrese una lista de ubicaciones id separadas por coma");
		String ubicaciones= in.nextLine();
		ubicaciones= in.nextLine();
		String[] paramUbicaciones = ubicaciones.split(",");
		bd.ajusteInvCompra(Integer.parseInt(ordenDeCompraId), new ArrayList<>(Arrays.asList(paramUbicaciones)));
	}
	
	void getFacturasInpagas() throws ObjetoInexistenteException, RemoteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos: \nint clienteId");
		String clienteid= in.nextLine();
		clienteid= in.nextLine();
		System.out.printf(NOStringformatter(5),
				"idFactura", "fecha","estado","importa","bonificacion");
		for(FacturaDTO fac: bd.getFacturasInpagas(Integer.parseInt(clienteid))) {
			System.out.printf(NOStringformatter(5),
				fac.getId(),fac.getFecha(), fac.getEstado(), "$"+fac.getImporte(),fac.getBonificacion());
		}
	}
	
	void pagarFactura() throws RemoteException, ObjetoInexistenteException, LaFacturaYaTienePagosDeOtraEspecieException, NumberFormatException, CommunicationException { 
		System.out.println("Ingrese los datos separados por coma:\nint idCliente, int nroFactura, float pago, String especie");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.pagarFactura(Integer.parseInt(params[0]),Integer.parseInt(params[1]),Float.parseFloat(params[2]),params[3]);
		System.out.println("Factura pagada correctamente");
	}
	
	void agregarPago() throws RemoteException, ObjetoInexistenteException, NumberFormatException, CommunicationException { 
		System.out.println("Ingrese los datos separados por coma:\nint idCliente, float pago, String especie");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.agregarPago(Integer.parseInt(params[0]),Float.parseFloat(params[1]),params[2]);
		System.out.println("Pago agregado correctamente");
	}
	
	void ajusteInvRotura() throws Exception{  
		System.out.println("Ingrese los datos separados por coma:\nString codBarras, int idUbicacion, int cantidad, int encargado, int usrAutorizador");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.ajusteInvRotura(params[0],Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),Integer.parseInt(params[4]));
		System.out.println("Ajuste agregado correctamente");
	}
	
	void ajusteInvAjuste() throws Exception{  
		System.out.println("Ingrese los datos separados por coma:\nString codBarras, int cant, int idUbicacionArticulo");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.ajusteInvAjuste(params[0],Integer.parseInt(params[1]),Integer.parseInt(params[2]));
		System.out.println("Ajuste agregado correctamente");
	}
	
	void obtenerProveedores() throws Exception{  
		System.out.println("Ingrese los datos:\nint articuloId");
		String articuloId= in.nextLine();
		articuloId= in.nextLine();
		System.out.printf(NOStringformatter(2),"idProveedor", "nombre");
		for(ProveedorDTO pro: bd.obtenerProveedores(Integer.parseInt(articuloId))) {
			System.out.printf(NOStringformatter(2),
				pro.getId(), pro.getNombre());
		}
	}
	
	
	void getStock() throws RemoteException, CommunicationException, ObjetoInexistenteException{ 
		System.out.println("Ingrese los datos:\nString codigoDeBarras");
		String articuloId= in.nextLine();
		articuloId= in.nextLine();
		System.out.println("Stock; "+bd.getStock(articuloId));
		
	}
	
	void registrarCliente() throws RemoteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos separados por coma:\nString razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero, float limiteCredito");
		String input= in.nextLine();
		input= in.nextLine();
		String[] param = input.split(",");
		//bd.registrarCliente(param[0], Integer.parseInt(param[1]), param[2], Integer.parseInt(param[3]), param[4], param[5], param[6], param[7], param[8], param[9], param[10], param[11], Integer.parseInt(param[12]),Float.parseFloat(param[13]),param[14],param[15],param[16]);
		System.out.println("Ajuste agregado correctamente");
	}
	
	void modificacionCliente(int idCliente, String razonSocial, int documentoId, String CUIT, int tel, String condicion, String pais, String provicia, String Partido, String codigoPostal, String calle, String altura, String piso, int numero) throws RemoteException{ 
		//TODO hacer metodo
	}
	
	void bajaCliente(int idCliente) throws RemoteException{ 
		//TODO hacer metodo
	}
	
	void asignarProveedor(int ordenDeCompraId, int proveedorId) throws RemoteException, ObjetoInexistenteException { 
		//TODO hacer metodo
	}
	
	void getTipoDocumentos() throws RemoteException{ 
		//TODO hacer metodo
	}

	String NOStringformatter(int cantColumnas){
		int size=(columnas/cantColumnas)-1;
		String output ="";
		for(int i=0; i < cantColumnas; i++) {
			output=output+"%-"+size+"."+size+"s;";
		}
		output=output+"%n";
		return output;
	}
}
