package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ItemPedidoCteEntity;
import entities.PedidoCteEntity;
import entities.ReservaArticuloEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.ReservaArticulo;

public class ReservaArticuloDao {
	private static ReservaArticuloDao instancia;
	
	private ReservaArticuloDao() {}
	
	public static ReservaArticuloDao getInstance() {
		if(instancia == null)
			instancia = new ReservaArticuloDao();
		return instancia;
	}

	public ReservaArticulo getById(int idReservaArticulo) throws ObjetoInexistenteException{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ReservaArticuloEntity entity = (ReservaArticuloEntity) session.createQuery("from ReservaArticuloEntity where idReservaArticulo = ?")
					.setParameter(0, idReservaArticulo)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro una reserva con id "+idReservaArticulo);
	}
	
	
	public ReservaArticulo grabar(ReservaArticulo reservaArticulo) throws ObjetoInexistenteException{
		ReservaArticuloEntity rae = new ReservaArticuloEntity(reservaArticulo);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(rae);
		session.getTransaction().commit();
		session.close();
		return rae.toNegocio();
	}
	
	public List<ReservaArticuloEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ReservaArticuloEntity");
		List<ReservaArticuloEntity> list = q.list();
		return list;

	}

	
	public List<ReservaArticulo> getByStatus(String estado){
		try {
			List<ReservaArticulo> result = new ArrayList<>(); 
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			List<ReservaArticuloEntity> entities = session.createQuery("from ReservaArticuloEntity where estado = ?")
					.setParameter(0, estado).list();
			for(ReservaArticuloEntity entity: entities) {
				ReservaArticulo mov= entity.toNegocio();
				result.add(mov);
			}
		return result;
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<ReservaArticulo> getByStatusYArticulo(String estado, String articuloId){
		try {
			List<ReservaArticulo> result = new ArrayList<>(); 
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			List<ReservaArticuloEntity> entities = session.createQuery("from ReservaArticuloEntity where estado = ? AND articulo.id = ?")
					.setParameter(0, estado).setParameter(1, articuloId).list();
			for(ReservaArticuloEntity entity: entities) {
				ReservaArticulo mov= entity.toNegocio();
				result.add(mov);
			}
		return result;
		} catch (ObjetoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ReservaArticulo> getByArticuloAndStatus(String codDeBarras, String Status) {
		//TODO hacer metodo
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ReservaArticuloEntity entity = (ReservaArticuloEntity) session.createQuery("from ReservaArticuloEntity where id.id = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		//if(entity != null)
			//TODO hacer carga
			return null;
		//else 
			//throw new ReservaInexistenteException("No se encontro una reserva con id "+codDeBarras);
	}

	public List<ReservaArticulo> getByArtIdYfecha(int articuloId, Date fechaPedido) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ReservaArticuloEntity where articulo.id = ? and pedidoReserva.FechaGeneracion < ?")
				.setParameter(0, articuloId)
				.setParameter(1, fechaPedido);
		List<ReservaArticuloEntity> list = q.list();
		

		if(list.isEmpty()!= true){
			ArrayList<ReservaArticulo> modelList = new ArrayList<>();
			for(ReservaArticuloEntity entity: list) {
				modelList.add(entity.toNegocio());
			}
			return modelList;
		}
		else 
			throw new ObjetoInexistenteException("No se encontraron Reservas previas para ese articulo "+ articuloId + " fecha:" + fechaPedido );
	}

	
}
