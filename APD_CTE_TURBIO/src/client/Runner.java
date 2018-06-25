package client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;

import dao.ClienteDao;
import dao.OrdenDeCompraDao;
import dao.PedidoCteDao;
import dao.ProductoDao;
import dao.ProveedorDao;
import dao.UbicacionDao;
import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoCteDTO;
import dto.ProveedorDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;
import entities.OrdenDeCompraEntity;
import entities.PedidoCteEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.OrdenDeCompra;
import model.PedidoCte;
import model.Producto;
import model.Proveedor;
import model.Ubicacion;
import model.Usuario;

public class Runner {

	BusinessDelegate bd;
	Random r;
	
	public Runner() throws CommunicationException {
		bd= new BusinessDelegate();
		r= new Random();
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
	
	public void altaClientes() throws CommunicationException {
		System.out.println("Alta de Clientes.");
		ArrayList<ClienteDTO> clientes = new ArrayList<>();
		
		clientes.add(new ClienteDTO(0, "Timpone", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Franco", "Timpone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rodriguez", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Santiago", "Rodriguez", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Rabone", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Javier", "Rabone", UsuarioDTO.ROL_CLIENTE, "123456")));
		clientes.add(new ClienteDTO(0, "Cannatella", 100000, "35448996", 0, 123456789, "condFinanciera", null, new UsuarioDTO(0, "Yesica", "Cannatella", UsuarioDTO.ROL_CLIENTE, "123456")));
		
		for(ClienteDTO c: clientes) {
			bd.registrarCliente(0, c.getRazonSocial(), 0, c.getDocumento(), c.getTelefono(),
					c.getCondicionFinanciera(), "", "", "", "", "", "", "", 0, c.getLimiteCredito(),
					c.getUsuario().getNombre(), c.getUsuario().getApellido(), c.getUsuario().getContrasena());
		}
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaArticulos() throws CommunicationException {
		System.out.println("Alta de articulos.");
		ArrayList<Articulo> articulosNuevos = new ArrayList<>();
		articulosNuevos.add(new Articulo(0, "00001105", "papita", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001106", "coca", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001107", "chicle", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001108", "salmon", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001109", "asd", 300, "bolsa", "gr", 35, 200, 100));
		articulosNuevos.add(new Articulo(0, "00001110", "2389", 300, "bolsa", "gr", 35, 200, 100));
		
		for(Articulo art: articulosNuevos) {
			bd.altaArticulo(0, art.getCodDeBarras(), art.getDescripcion(), art.getTamano(),
					art.getPresentacion(), art.getUnidad(), art.getPrecioDeVenta(),
					art.getCantidadAComprar(), art.getCantidadUbicable());
		}
		
		System.out.println("PERSISTIDOS OK");
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void listarArticulos() throws CommunicationException {
		System.out.println("LISTANDO ARTICULOS");
		for(ArticuloDTO art: bd.getArticulos()) {
			System.out.println("Art: "+art.getId()+" desc: "+art.getDescripcion());
		}
		System.out.println("FIN");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void altaProductos() throws CommunicationException {
		System.out.println("Alta PRODUCTOS");
		try {
			for(ArticuloDTO art: bd.getArticulos()) {
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
	
	public void generarPedidos() throws CommunicationException {
		System.out.println("DANDO DE ALTA PEDIDOS");
		try {
			for(Cliente cte: ClienteDao.getInstance().getAll()) {
				bd.generarNuevoPedido(cte.getIdCliente(), "", "", "", "", "", "", "", 0);
				bd.generarNuevoPedido(cte.getIdCliente(), "", "", "", "", "", "", "", 0);
			}
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		

		
		
		System.out.println("FIN: "+getStatusSumPedidos(PedidoCteDao.getInstance().getAll()));
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
	
	public void agregarItemsAPedido() throws CommunicationException {
		System.out.println("AGREGANDO ITEMS A PEDIDOS");
		int Low = 10;
		int High = 100;
		try {
			String res ="";
			for(PedidoCte p: PedidoCteDao.getInstance().getByStatus(PedidoCteDTO.ESTADO_NUEVO)) {
				for(ArticuloDTO a: bd.getArticulos()) {
					if(r.nextBoolean()) {
						bd.agregarArticuloAPedido(a.getCodDeBarras(), r.nextInt(High-Low) + Low, p.getIdPedidoCliente());
					}
				}
				PedidoCte ped = PedidoCteDao.getInstance().getById(p.getIdPedidoCliente());
				res= res+"\t(id "+ped.getIdPedidoCliente()+"; "+ped.getItems().size()+" items; $"+ped.getTotalbruto()+")\n";
			}
			System.out.println("FIN: \n"+res);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		}catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cerrarPedidoCte() throws CommunicationException {
		System.out.println("AVANZANDO PEDIDOS");
		try {
			for(PedidoCte p: PedidoCteDao.getInstance().getByStatus(PedidoCteDTO.ESTADO_NUEVO)) {
				bd.cerrarPedido(p.getIdPedidoCliente());
			}
			System.out.println("FIN: "+getStatusSumPedidos(PedidoCteDao.getInstance().getAll()));
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		}catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pedidoAprobRech() throws CommunicationException {
		System.out.println("APROBANDO / RECHAZANDO PEDIDOS");
		try {
			for(PedidoCteDTO p: bd.getPedidosPendAprobCred()) {
				if(p.getId()%2==0) {
					bd.aceptarPedidoCred(p.getId(), "aprobado");
					//TODO 0 Evaluar por que no todos los articulos tienen orden de compra
				}else {
					bd.rechazarPedidoCred(p.getId(), "rechazado");
				}
			}
			System.out.println("FIN: "+getStatusSumPedidos(PedidoCteDao.getInstance().getAll()));
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		}catch(Exception e) {
			System.out.println("Oooops "+e.getMessage());
		}
	}
	
	public void evaluarStocks() {
		System.out.println("EVALUANDO STOCK PEDIDOS");
		try {
			for(PedidoCteDTO p: bd.getPedidosPendDesp()) {
				bd.evaluarStock(p.getId());
			}
			System.out.println("FIN: "+getStatusSumPedidos(PedidoCteDao.getInstance().getAll()));
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		}catch(Exception e) {
			System.out.println("Oooops "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void listarOrdDeCompraDAO() {
		System.out.println("LISTANDO ORDENES");
		//for(OrdenDeCompraDTO ord: bd.getOrdCompraRecibidas())
		for(OrdenDeCompraEntity ord: OrdenDeCompraDao.getInstance().getAll()) {
			System.out.println("\tid:"+ord.getId() + "; Llega:"+ord.getFechaRecepcion()+"; Estado:"+ord.getEstado() + "; Art:"+ord.getArticulo().getDescripcion());
		}
		System.out.println("FIN");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void listarOrdDeCompraRecibidas() throws CommunicationException {
		System.out.println("LISTANDO ORDENES RECIBIDAS");
		try {
			for(OrdenDeCompraDTO ord: bd.getOrdCompraRecibidas()) {
				System.out.println("\tid:"+ord.getId() + "; Llega:"+ord.getFechaRecepcion()+"; Estado:"+ord.getEstado() + "; Art:"+ord.getArticulo().getDescripcion());
			}
			System.out.println("FIN");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
	}
	
	public void banca(long timeout) throws InterruptedException{
		System.out.println("Espero "+timeout+" segs");
		System.out.print("\t");
		for(int i=0; i<timeout; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.print((i+1)+"... ");
		}
		System.out.println("FIN");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------");
	}
	
	public void elegirProveedor() throws CommunicationException {
		System.out.println("ELIGIENDO PROVEEDORES");
		try {
			for(OrdenDeCompraDTO ord: bd.getOrdenesPendElecProveedor()) {
				List<ProveedorDTO> proveedores = bd.obtenerProveedores(ord.getArticulo().getId());
				if(proveedores.size()==1) {
					bd.asignarProveedor(ord.getId(), proveedores.get(0).getId());
				} else {
					int Low = 1;
					int High = proveedores.size();
					int provIndexElegido=r.nextInt(High-Low) + Low;
					bd.asignarProveedor(ord.getId(), proveedores.get(provIndexElegido).getId());
				}
				
			}
			listOrdenes(OrdenDeCompraDao.getInstance().getAll());
			System.out.println("FIN");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
	}
	
	public void ubicarOrdenesRecibidas() throws CommunicationException {
		System.out.println("UBICANDO ORDENES RECIBIDAS PEDIDOS");
		try {
			for(OrdenDeCompraDTO ord: bd.getOrdCompraRecibidas()) {
				List<Integer> ubicaciones = new ArrayList<>();
				double cantUbNecesarias=Math.ceil(new Double(ord.getCantidad())/ new Double(ord.getArticulo().getCantidadUbicable()));
				
				for(UbicacionDTO ub: bd.getUbicacionesVacias()) {
					if(ubicaciones.size()<cantUbNecesarias) {
						ubicaciones.add(ub.getId());
						cantUbNecesarias++;
					}else {
						break;
					}
					
				}
				bd.ajusteInvCompra(ord.getId(), ubicaciones);
			}
			//TODO 0 Evaluar que peguen bien los nros...
			listOrdenes(OrdenDeCompraDao.getInstance().getAll());
			System.out.println("FIN: ");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("-------------------------------------------------------------------");
		}catch(Exception e) {
			System.out.println("Oooops "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String listOrdenes(List<OrdenDeCompraEntity> ordenes) {
		String res= "Statuses: ";
		Map<String,Integer> map = new HashMap<>();		
		for(OrdenDeCompraEntity ord: ordenes) {
			System.out.println("\tid:"+ord.getId() +
					"; Llega:"+ord.getFechaRecepcion()+
					"; Estado:"+ord.getEstado() +
					"; Art:"+ord.getArticulo().getDescripcion() +
					"; Prov:"+ord.getPorveedorOC().getNombre()
					);
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
						UbicacionDao.getInstance().grabar(new Ubicacion(0, calles[iCalle], iBloque+1, iEstante+1, iPosticion+1, 0));
					}
				}
			}
		}	
	}
}
