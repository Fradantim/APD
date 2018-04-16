package controller;

public class AreaTransporte {

	private static AreaTransporte areaTransporte;
	
	public static AreaTransporte getInstance() {
		if(areaTransporte==null) {
			areaTransporte= new AreaTransporte();
		}
		return areaTransporte;
	}
}
