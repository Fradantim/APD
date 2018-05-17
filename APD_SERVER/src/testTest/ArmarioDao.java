package testTest;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;

public class ArmarioDao {
	
	private static ArmarioDao instancia;
	
	private ArmarioDao() {}
	
	public static ArmarioDao getInstance() {
		if(instancia == null)
			instancia = new ArmarioDao();
		return instancia;
	}

	public Armario getById(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArmarioEntity entity = (ArmarioEntity) session.createQuery("from ArmarioEntity where id = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un ArmarioEntity con id "+id);
	}
	
	public Armario getFullById(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArmarioEntity entity = (ArmarioEntity) session.createQuery("from ArmarioEntity where id = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocioFull();
		else 
			throw new ObjetoInexistenteException("No se encontro un ArmarioEntity con id "+id);
	}
	
	public Integer grabar(Armario armario){
		ArmarioEntity armarioEntity = new ArmarioEntity(armario);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(armarioEntity);
		session.getTransaction().commit();
		session.close();
		return armarioEntity.getId();
	}
	
	public List<ArmarioEntity> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ArmarioEntity");
		List<ArmarioEntity> list = q.list();
		return list;

	}
}
