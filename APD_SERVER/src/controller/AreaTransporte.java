package controller;

import java.util.Date;


import model.Remito;

public class AreaTransporte {

	private static AreaTransporte areaTransporte;
	
	public static AreaTransporte getInstance() {
		if(areaTransporte==null) {
			areaTransporte= new AreaTransporte();
		}
		return areaTransporte;
	}
	
	public Date prepararDespacho(Remito remito){
		return new Date((new Date()).getTime()+60*10000);
	}
}
