package controller;

public class Controller {

	private static Controller controller;
	private AdministradorClientes administradorClientes;
	private AdministradorPedidos administradorPedidos;
	private AreaCompras areaCompras;
	private Almacen almacen;
	
	
	public Controller(){
		administradorClientes=AdministradorClientes.getInstance();
		administradorPedidos=AdministradorPedidos.getInstance();
		areaCompras=AreaCompras.getInstance();
		almacen=Almacen.getInstance();
	}
	
	public static Controller getInstance() {
		if(controller==null) {
			controller= new Controller();
		}
		return controller;
	}
}
