package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.RoturaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Rotura;

public class RoturaDao {
	private static RoturaDao instancia;
	
	private RoturaDao() {}
	
	public static RoturaDao getInstance() {
		if(instancia == null)
			instancia = new RoturaDao();
		return instancia;
	}

	public Integer grabar(Rotura rotura){
		RoturaEntity ae = new RoturaEntity(rotura);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdMovimiento();
	}
	
}
