package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.PedidoCteDTO;
import entities.FacturaEntity;
import entities.PagoEntity;
import entities.PedidoCteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Factura;
import model.Pago;
import model.PedidoCte;

public class PedidoCteDao {
	private static PedidoCteDao instancia;
	
	private PedidoCteDao() {}
	
	public static PedidoCteDao getInstance() {
		if(instancia == null)
			instancia = new PedidoCteDao();
		return instancia;
	}

	public PedidoCte getById(int idPedido) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		PedidoCteEntity entity = (PedidoCteEntity) session.createQuery("from PedidoCteEntity where IdPedidoCte = ?")
					.setParameter(0, idPedido)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un PedidoCte con id "+idPedido);
	}
	
	public PedidoCteEntity getByIdPed(int idPedido) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		PedidoCteEntity entity = (PedidoCteEntity) session.createQuery("from PedidoCteEntity where IdPedidoCte = ?")
					.setParameter(0, idPedido)
					.uniqueResult();
		if(entity != null)
			return entity;
		else 
			throw new ObjetoInexistenteException("No se encontro un PedidoCte con id "+idPedido);
	}

	
	public PedidoCte grabar(PedidoCte pedido) throws ObjetoInexistenteException{
		PedidoCteEntity pce = new PedidoCteEntity(pedido);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(pce);
		session.getTransaction().commit();
		session.close();
		return pce.toNegocio();
	}
	
	public List<PedidoCteEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from PedidoCteEntity");
		List<PedidoCteEntity> list = q.list();
		return list;

	}
	
	public List<PedidoCteDTO> getDTOByStatus(String estado) throws ObjetoInexistenteException{
		List<PedidoCteDTO> pedidosDTO = new ArrayList<>();
		for(PedidoCte pedido: getByStatus(estado)) {
			pedidosDTO.add(pedido.toDTO());
		}
		return pedidosDTO;
	}
	
	public List<PedidoCte> getByStatus(String estado){
		List<PedidoCte> result = new ArrayList<>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<PedidoCteEntity> entities = session.createQuery("from PedidoCteEntity where EstadoPedido = ?")
				.setParameter(0, estado).list();
		for(PedidoCteEntity entity: entities) {
			PedidoCte mov = entity.toNegocio();
			result.add(mov);
		}
		return result;
	}

	public void actualizar(PedidoCte pedido, float tot) throws HibernateException, ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query query =  session.createQuery("update PedidoCteEntity set total = :totalbruto where IdPedidoCte = :idpedido")
				.setParameter("totalbruto", tot)
				.setParameter("idpedido", pedido.getIdPedidoCliente());
		int result = query.executeUpdate();
	}

	public void actualizarped(int idPedido, int idCli, String pais, String provincia, String partido,String codigoPostal, String calle, String altura, String piso, int numero) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		switch(idCli){
		case 0:
			 break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set Cli.id = :idcliente where IdPedidoCte = :idpedido")
					.setParameter("idcliente", idCli)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}
		
		switch(pais){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set pais = :pais where IdPedidoCte = :idpedido")
					.setParameter("pais", pais)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}
		
		switch(provincia){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set provincia = :provincia where IdPedidoCte = :idpedido")
					.setParameter("provincia", provincia)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

		switch(partido){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set partido = :partido where IdPedidoCte = :idpedido")
					.setParameter("partido", partido)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

		switch(codigoPostal){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set codpostal = :codigoPostal where IdPedidoCte = :idpedido")
					.setParameter("codigoPostal", codigoPostal)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

		switch(calle){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set calle = :calle where IdPedidoCte = :idpedido")
					.setParameter("calle", calle)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

		switch(altura){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set alt = :altura where IdPedidoCte = :idpedido")
					.setParameter("altura", altura)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

		switch(piso){
		case "''":
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set piso = :piso where IdPedidoCte = :idpedido")
					.setParameter("piso", piso)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}
	
		switch(numero){
		case 0:
			break;
		default: 
			{		
				Query query =  session.createQuery("update PedidoCteEntity set numero = :numero where IdPedidoCte = :idpedido")
					.setParameter("numero", numero)
					.setParameter("idpedido", idPedido);
				int result = query.executeUpdate();	
			}
			break;
		}

	}

	public void bajarPedido(int idPedido) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query query =  session.createQuery("delete from PedidoCteEntity where IdPedidoCte = :idpedido")
					.setParameter("idpedido", idPedido);
		int result = query.executeUpdate();

	}
	
}
