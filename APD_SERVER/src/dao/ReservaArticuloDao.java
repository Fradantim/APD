package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ReservaArticuloEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
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
		ReservaArticuloEntity entity = (ReservaArticuloEntity) session.createQuery("from ReservaArticuloEntity where id.id = ?")
					.setParameter(0, idReservaArticulo)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new ReservaArticulo();
		else 
			throw new ObjetoInexistenteException("No se encontro una reserva con id "+idReservaArticulo);
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
	
	public void grabar(ReservaArticulo reservaArticulo){
		//TODO hacer metodo 
		//ClienteEntity ce = new ClienteEntity();
		/*JugadorEntity je = new JugadorEntity(jugador.getTipo(), jugador.getNumero(), jugador.getNombre());
		ClubEntity club = null;
		try {
			club = ClubDAO.getInstance().findByID(jugador.getClub().getIdClub());
		} catch (ClubException e) {
			e.printStackTrace();
		}
		je.setClub(club);
		je.setCategoria(jugador.getCategoria());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(je);
		session.getTransaction().commit();
		session.close();*/
	}
	
	public List<ReservaArticulo> getByStatus(String estado){
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
	
	public List<ReservaArticulo> getByStatusYArticulo(String estado, String articuloId){
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
}
