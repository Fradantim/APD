package server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.CommunicationException;

import controller.Controller;
import dao.ArticuloDao;
import dao.ClienteDao;
import dao.PagoDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import dao.UbicacionDao;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.OrdenDeCompraDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;
import entities.ClienteEntity;
import entities.PedidoCteEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;
import model.Articulo;
import model.Cliente;
import model.CompraRealizada;
import model.Factura;
import model.OrdenDeCompra;
import model.Pago;
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
		
		clientes.add(new ClienteDTO(0, "Timpone1", 100000, "35448996", 0, 123456789, "Cliente con crédito", null, new UsuarioDTO(0, "Franco", "Timpone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rodriguez2", 100000, "35448996", 0, 123456789, "Cliente con crédito", null, new UsuarioDTO(0, "Santiago", "Rodriguez", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rabone3", 100000, "35448996", 0, 123456789, "Cliente sin crédito", null, new UsuarioDTO(0, "Javier", "Rabone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cannatella4", 100000, "35448996", 0, 123456789, "Cliente sin crédito", null, new UsuarioDTO(0, "Yesica", "Cannatella", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cliente5", 100000, "35448996", 0, 123456789, "Cliente con crédito", null, new UsuarioDTO(0, "Franco", "Timpone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cliente6", 100000, "35448996", 0, 123456789, "Cliente con crédito", null, new UsuarioDTO(0, "Santiago", "Rodriguez", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cliente7", 100000, "35448996", 0, 123456789, "Cliente con crédito", null, new UsuarioDTO(0, "Javier", "Rabone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cliente8", 100000, "35448996", 0, 123456789, "Cliente sin crédito", null, new UsuarioDTO(0, "Yesica", "Cannatella", UsuarioDTO.ROL_CLIENTE, "123456")));
		
		
		for(ClienteDTO cte: clientes) {
			c.registrarCliente(0, cte.getRazonSocial(), 0, cte.getDocumento(), cte.getTelefono(),
					cte.getCondicionFinanciera(), "", "", "", "", "", "", "", 0, cte.getLimiteCredito(),
					cte.getUsuario().getNombre(), cte.getUsuario().getApellido(), cte.getUsuario().getContrasena());
		}
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void setFinanciero() {
		System.out.println("Alta de Financiero.");
		for(Cliente cte : ClienteDao.getInstance().getAll()) {
			if(cte.getCondicionFinanciera().equals("Cliente con crédito")) {
				Pago p = new Pago();
				p.setCliente(cte);
				p.setEspecie("Efectivo");
				p.setImporte(-10000);
				p.setFecha(new Date());
				p.guardar();
			}else {
				Factura f= new Factura();
				f.setBonificacion(0);
				f.setCliente(cte);
				f.setEstado(FacturaDTO.STATUS_PAGA);
				f.setFecha(new Date());
				f.setImporte(10000);
				f.guardar();
			}
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaArticulos(){
		System.out.println("Alta de articulos.");
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "00001101", "lechuga", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001102", "Manzana", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001103", "medias", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001104", "anteojos", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001109", "fideos", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001110", "plasma", 12, "caja", "kg", 35, 200, 100));
		
		for(Articulo art: articulosNuevos) {
			c.altaArticulo(0, art.getCodDeBarras(), art.getDescripcion(), art.getTamano(),
					art.getPresentacion(), art.getUnidad(), art.getPrecioDeVenta(),
					art.getCantidadAComprar(), art.getCantidadUbicable());
		}
		
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	

	public void agregarStock() {
		for(Articulo art : ArticuloDao.getInstance().getAll()) {
			OrdenDeCompra orden = new OrdenDeCompra();
			orden.setArticulo(art);
			orden.setCantidad(1000);
			orden.setEstado(OrdenDeCompraDTO.ESTADO_UBICADA);
			orden.setFechaRecepcion(new Date());
			orden.setFechaVencimiento(new Date());
			orden.setIdPedido(null);
			orden.setProveedor(null);
			orden.setIdPedido(0);
			int cantUb=orden.getCantidad()/art.getCantidadUbicable();
			List <Ubicacion> ubicaciones = new ArrayList<>();
			try {
				for(UbicacionDTO ub: c.getUbicacionesVacias()) {
					if(cantUb>0) {
						cantUb--;
						ubicaciones.add(
								/*new Ubicacion(ub.getId(), ub.getCalle(), ub.getCalle(), ub.getEstante(), ub.getPosicion(),
								0, null, null)*/
								new Ubicacion(ub.getId(), ub.getCalle(), ub.getBloque(), ub.getEstante(), ub.getPosicion(), 0, null, null)
								);
					}
				}
			} catch (ObjetoInexistenteException e) {
				e.printStackTrace();
			}
			try {
				art.ajusteInvCompra(orden, ubicaciones);
			} catch (ObjetoInexistenteException | LaUbicacionNoTieneEsteArticuloException
					| LaUbicacionNoTieneSuficientesArticulosParaRemoverException
					| SuperaLaCantidadUbicableEnLaUbicacionException e) {
				e.printStackTrace();
			}
			
		}
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
