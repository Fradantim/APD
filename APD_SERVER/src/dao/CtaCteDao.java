package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CtaCteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.CtaCte;

public class CtaCteDao {

	private static CtaCteDao instancia;
	
	private CtaCteDao() {}
	
	public static CtaCteDao getInstance() {
		if(instancia == null)
			instancia = new CtaCteDao();
		return instancia;
	}

	public CtaCte getById(int idCtaCte) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CtaCteEntity entity = (CtaCteEntity) session.createQuery("from CtaCteEntity where idCtaCte = ?")
					.setParameter(0, idCtaCte)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro una CuentaCliente con id "+idCtaCte);
	}
	
	public CtaCte getByClienteId(int idCliente) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CtaCteEntity entity = (CtaCteEntity) session.createQuery("from CtaCteEntity where idCliente = ?")
					.setParameter(0, idCliente)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro una CuentaCliente con idCliente "+idCliente);
	}
	
	public CtaCte grabar(CtaCte ctacte) throws ObjetoInexistenteException{
		CtaCteEntity ce = new CtaCteEntity(ctacte);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ce);
		session.getTransaction().commit();
		session.close();
		return ce.toNegocio();
	}
}
