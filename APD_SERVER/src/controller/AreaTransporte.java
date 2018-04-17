package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Remito;

public class AreaTransporte {

	private static AreaTransporte areaTransporte;
	
	public static AreaTransporte getInstance() {
		if(areaTransporte==null) {
			areaTransporte= new AreaTransporte();
		}
		return areaTransporte;
	}
	
	private List<Remito> remitos;
	
	public AreaTransporte() {
		remitos = new ArrayList<>();
	}
	
	
	public Date prepararDespacho(Remito remito){
		remitos.add(remito);
		return new Date((new Date()).getTime()+60*10000);
	}
}
