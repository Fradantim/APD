package controller;

public class Almacen {

	private static Almacen almacen;
	
	public static Almacen getInstance() {
		if(almacen==null) {
			almacen= new Almacen();
		}
		return almacen;
	}
}
