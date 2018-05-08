package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.AjusteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Ajuste;

public class AjusteDao {
	private static AjusteDao instancia;
	
	private AjusteDao() {}
	
	public static AjusteDao getInstance() {
		if(instancia == null)
			instancia = new AjusteDao();
		return instancia;
	}

	public Ajuste grabar(Ajuste ajuste) throws ObjetoInexistenteException{
		AjusteEntity ae = new AjusteEntity(ajuste);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio();
	}
	
	public Ajuste getById(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		AjusteEntity entity = (AjusteEntity) session.createQuery("from AjusteEntity where idMovimiento = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(entity != null){
			return entity.toNegocio();
		}
		else 
			throw new ObjetoInexistenteException("No existe un Ajuste con id "+ id);
	}
	
}
