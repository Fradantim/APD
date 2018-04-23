package test;

import java.util.ArrayList;
import java.util.List;

import dao.ArticuloDao;
import entities.ArticuloEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;

public class Tester {
	public static void main(String[] args){
		System.out.println("eeeeeeeeee");
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001109", "asd", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001110", "2389", 300, "bolsa", "gr", 35, 200, 100));
		
		for(Articulo art: articulosNuevos){
			ArticuloDao.getInstance().grabar(art);
		}
		
		Articulo articulo=null;
		try {
			articulo= ArticuloDao.getInstance().getById("00001107");
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("Art: "+articulo.getDescripcion()+" "+articulo.getCodDeBarras());
		
		List<ArticuloEntity> articulosENuevos =ArticuloDao.getInstance().getAll();
		
		for(ArticuloEntity art: articulosENuevos){
			System.out.println("Art: "+art.getDescripcion()+" "+art.getCodDeBarras());
		}
		
		//for()
	}
}
