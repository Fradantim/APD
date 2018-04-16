package controller;

public class SistemaDeControlDeUsuarios {
	private static SistemaDeControlDeUsuarios sistCtrlUsuarios;
	
	public static SistemaDeControlDeUsuarios getInstance() {
		if(sistCtrlUsuarios==null) {
			sistCtrlUsuarios= new SistemaDeControlDeUsuarios();
		}
		return sistCtrlUsuarios;
	}
}
