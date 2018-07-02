package test;


import java.util.ArrayList;
import java.util.Date;

import dao.ClienteDao;
import dao.LoteDao;
import exception.ObjetoInexistenteException;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.Lote;
import model.Ubicacion;

public class TestUbicacionLote {
	public static void main(String[] args) throws ObjetoInexistenteException{
		System.out.println("Carga Ubicacion");
		System.out.println("---------------");
		for (Ubicacion ubi : cargarUbicaciones()) {
			ubi.guardar();
			System.out.println("Ubicacion guardado: "+ubi.getIdUbicacion());
		}   
		for (Lote lot : cargarLotes()) {
			lot.guardar();
			System.out.println("Lote recuperado: " + lot.getId());
		}		
	}
	
	public static ArrayList<Ubicacion> cargarUbicaciones(){
		ArrayList<Ubicacion> ubicacionesNuevas = new ArrayList<>();
		
		//no asignarle ids a los elementos nuevos, los ids los da la tabla
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",1,4,1, 10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",2,4,2,10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",3,2,3,20));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",4,2,4,10));
		ubicacionesNuevas.add(new Ubicacion(0,"calle 1",5,1,5,10));		
		return ubicacionesNuevas;
	}
	
	public static ArrayList<Lote> cargarLotes(){
		ArrayList<Lote> lotesNuevos = new ArrayList<>();
		Date fecha = new Date();
		lotesNuevos.add(new Lote(fecha));		
		return lotesNuevos;
	}

}
