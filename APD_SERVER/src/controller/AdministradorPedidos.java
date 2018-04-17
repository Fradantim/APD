package controller;

public class AdministradorPedidos {
	
	private static AdministradorPedidos administradorPedidos;
	private AreaTransporte areaTransporte;
	private AdministradorClientes administradorClientes;
	private AreaCompras areaCompras;
	private Almacen almacen;
	
	public AdministradorPedidos() {
		areaTransporte=AreaTransporte.getInstance();
		administradorClientes=AdministradorClientes.getInstance();
		administradorPedidos=AdministradorPedidos.getInstance();
		areaCompras=AreaCompras.getInstance();
		almacen=Almacen.getInstance();
	}
	
	
	public static AdministradorPedidos getInstance() {
		if(administradorPedidos==null) {
			administradorPedidos= new AdministradorPedidos();
		}
		return administradorPedidos;
	}
}
