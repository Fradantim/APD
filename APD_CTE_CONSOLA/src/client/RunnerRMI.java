package client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
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
import exception.ObjetoInexistenteException;

public class RunnerRMI {

	BusinessDelegate bd;
	Method[] metodos;
	Scanner in;
	int columnas=120;
	
	public void NOexecute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
			NONONOlistarMetodos();
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

//GENERAR NUEVO PEDIDO	
	void generarNuevoPedido() throws Exception{ 
		System.out.println("Ingrese los datos separados por coma:\nint idCli, String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		int res= bd.generarNuevoPedido(Integer.parseInt(params[0]), params[1], params[2], params[3], params[4], params[5], params[6], params[7], Integer.parseInt(params[8]));
		System.out.println("Pedido cargado correctamente, id: "+res);
	}

//AGREGAR ARTICULO A PEDIDO	
	void agregarArticuloAPedido() throws RemoteException, ObjetoInexistenteException, NumberFormatException, CommunicationException{ 
		System.out.println("Ingrese los datos separados por coma:\nString CodArticulo,int cant,int idPedido");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.agregarArticuloAPedido(params[0],Integer.parseInt(params[1]), Integer.parseInt(params[2]));;
		System.out.println("Item agregado al pedido correctamente");
	}

//MODIFICAR PEDIDO	
	void ModificarPedido() throws Exception{ 
		System.out.println("Ingrese los datos separados por coma:\nint idPedido, int idCli, String pais, String provincia, String partido,String codigoPostal, String calle, String altura, String piso, int numero");
		System.out.println("Si no se desea actualizar ingresar:\n 0 para numerico '' para caracteres");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.ModificarPedido(Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2], params[3], params[4], params[5], params[6], params[7],params[8], Integer.parseInt(params[9]));
		System.out.println("Pedido modificado correctamente, id: "+params[0] );
	}

//BAJA PEDIDO	
	void BajarPedido() throws Exception{ 
		System.out.println("Ingrese nro de pedido a dar de baja");
		String input= in.nextLine();
		input= in.nextLine();
		String[] params = input.split(",");
		bd.BajarPedido(Integer.parseInt(params[0]));
		System.out.println("Pedido dado de baja, id: "+params[0] );
	}

//TODO Agregar un metodo que permita dar de baja mas de un pedido a la vez
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
