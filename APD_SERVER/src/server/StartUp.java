package server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.CommunicationException;

import controller.Controller;
import dao.ProductoDao;
import dao.ProveedorDao;
import dao.UbicacionDao;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.UsuarioDTO;
import entities.PedidoCteEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Producto;
import model.Proveedor;
import model.Ubicacion;
import model.Usuario;

public class StartUp {

	Random r;
	Controller c;
	
	public StartUp(){
		r= new Random();
		c= Controller.getInstance();
	}
	
	public void altaUsuarios() {
		System.out.println("Alta de Usuarios.");
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario(0, "Administrador Almacen", "Administrador Almacen", UsuarioDTO.ROL_ADMIN_ALMACEN, "123456"));
		usuarios.add(new Usuario(0, "Administrador Clientes", "Administrador Clientes", UsuarioDTO.ROL_ADMIN_CLIENTE, "123456"));
		usuarios.add(new Usuario(0, "Facturacion Despacho", "Facturacion Despacho", UsuarioDTO.ROL_FACTURACION_DESPACHO, "123456"));

		for(Usuario u: usuarios) {
			u.guardar();
		}
		
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaClientes(){
		System.out.println("Alta de Clientes.");
		ArrayList<ClienteDTO> clientes = new ArrayList<>();
		
		clientes.add(new ClienteDTO(0, "Timpone", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Franco", "Timpone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rodriguez", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Santiago", "Rodriguez", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rabone", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Javier", "Rabone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cannatella", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Yesica", "Cannatella", UsuarioDTO.ROL_CLIENTE, "123456")));
		
		for(ClienteDTO cte: clientes) {
			c.registrarCliente(0, cte.getRazonSocial(), 0, cte.getDocumento(), cte.getTelefono(),
					cte.getCondicionFinanciera(), "", "", "", "", "", "", "", 0, cte.getLimiteCredito(),
					cte.getUsuario().getNombre(), cte.getUsuario().getApellido(), cte.getUsuario().getContrasena());
		}
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaArticulos(){
		System.out.println("Alta de articulos.");
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001109", "asd", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001110", "2389", 300, "bolsa", "gr", 35, 200, 100));
		
		for(Articulo art: articulosNuevos) {
			c.altaArticulo(0, art.getCodDeBarras(), art.getDescripcion(), art.getTamano(),
					art.getPresentacion(), art.getUnidad(), art.getPrecioDeVenta(),
					art.getCantidadAComprar(), art.getCantidadUbicable());
		}
		
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	

	public void altaProductos(){
		System.out.println("Alta PRODUCTOS");
		try {
			for(ArticuloDTO art: c.getArticulos()) {
				new Producto(10.0F,art.getId()).guardar();
			}
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		System.out.println("PERSISTIDOS OK");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaProveedores() {
		System.out.println("Alta PROVEEDORES");
 		ArrayList<Proveedor> proveedoresNuevos = new ArrayList<>();
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor TODO",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 1",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 2",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 3",new Date()));	
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 4",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 5",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 6",new Date()));
 		proveedoresNuevos.add(new Proveedor(null,"Proveedor 7",new Date()));
 		try {
	 		for(Proveedor p: proveedoresNuevos) {
				ProveedorDao.getInstance().grabar(p);
	 		}
 		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
 		System.out.println("PERSISTIDOS OK");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
		
	}
	
	
	public void asociarProdProv() {
		System.out.println("ASOCIA PROVEEDOR PRODUCTO");
		try {
			for(ProveedorEntity pE: ProveedorDao.getInstance().getAll()) {
				Proveedor p =pE.toNegocio();
				for(ProductoEntity pdE: ProductoDao.getInstance().getAll()) {
					if(p.getId()==1) {
						p.getProductos().add(pdE.toNegocio());
					}else if(r.nextBoolean()) {
						p.getProductos().add(pdE.toNegocio());
					}
				ProveedorDao.getInstance().grabar(p);	
				}
			}
		}catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		System.out.println("FIN");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	private String getStatusSumPedidos(List<PedidoCteEntity> pedidos) {
		String res= "Statuses: ";
		Map<String,Integer> map = new HashMap<>();		
		for(PedidoCteEntity p: pedidos) {
			if(map.get(p.getEstadoPedido()) != null) {
				map.put(p.getEstadoPedido(), map.get(p.getEstadoPedido())+1);
			}else {
				map.put(p.getEstadoPedido(), 1);
			}
		}
		
		for (String key: map.keySet()) {
			res=res+key+" "+map.get(key)+"; ";
		}
		return res;
	}
	
	public void altaUbicaciones() {
		String calles[]= new String[] {"A","B","C"};
		int bloques=3, estantes=4, posiciones=2;
		for(int iCalle=0; iCalle<calles.length; iCalle++) {
			for(int iBloque=0; iBloque<bloques; iBloque++) {
				for(int iEstante=0; iEstante<estantes; iEstante++) {
					for(int iPosticion=0; iPosticion<posiciones; iPosticion++) {
						UbicacionDao.getInstance().grabar(new Ubicacion(0, calles[iCalle], iBloque+1, iEstante+1, iPosticion+1, 0, null, null));
					}
				}
			}
		}	
	}	
	
}
