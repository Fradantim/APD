package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.PedidoCteDTO;
import entities.PedidoCteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
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
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
	
}
