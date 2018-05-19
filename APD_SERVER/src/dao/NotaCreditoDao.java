package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.NotaCreditoEntity;
import hbt.HibernateUtil;
import model.NotaCredito;


public class NotaCreditoDao {
	private static NotaCreditoDao instancia;
	
	private NotaCreditoDao() {}
	
	public static NotaCreditoDao getInstance() {
		if(instancia == null)
			instancia = new NotaCreditoDao();
		return instancia;
	}

	public Integer grabar(NotaCredito notaCredito){
		NotaCreditoEntity entity = new NotaCreditoEntity(notaCredito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return entity.getIdMovimientoCtaCte();
	}
}
