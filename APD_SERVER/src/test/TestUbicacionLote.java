package test;
/*import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;*/

import java.util.ArrayList;

import dao.ClienteDao;
import exception.ObjetoInexistenteException;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.Ubicacion;

public class TestUbicacionLote {
	public static void main(String[] args) throws ObjetoInexistenteException{
		System.out.println("Carga Ubicacion");
		System.out.println("---------------");
		for (Ubicacion ubi : cargarUbicaciones()) {
			ubi.guardar();
			System.out.println("Ubicacion guardado: "+ubi.getIdUbicacion());
		}
   
		for (Cliente clie : ClienteDao.getInstance().getAll()) {
			System.out.println("Cliente recuperado: " + clie.getIdCliente() + " " + clie.getRazonSocial() + " cliente>Domicilio>calle: "+clie.getDomicilio().getCalle());
		}
 	
		
	}

	public static ArrayList<Ubicacion> cargarUbicaciones(){
		ArrayList<Ubicacion> ubicacionesNuevas = new ArrayList<>();
		
		//no asignarle ids a los elementos nuevos, los ids los da la tabla
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",1,4,1, 10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",2,4,2,10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",3,2,3,10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",4,2,4,10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",5,1,5,10));
		
		return ubicacionesNuevas;
	}

}
