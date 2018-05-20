package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import entities.RemitoEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Factura;
import model.Remito;

public class RemitoDao {
private static RemitoDao instancia;
	
	private RemitoDao() {}
	
	public static RemitoDao getInstance() {
		if(instancia == null)
			instancia = new RemitoDao();
		return instancia;
	}
	
	public Remito getById(int idRemito) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity entity = (RemitoEntity) session.createQuery("from RemitoEntity where idRemito = ?")
					.setParameter(0, idRemito)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro el remito con id "+ idRemito);
	}
	
	public RemitoEntity getByIdRem(int idRemito) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity entity = (RemitoEntity) session.createQuery("from RemitoEntity where idRemito = ?")
					.setParameter(0, idRemito)
					.uniqueResult();
		if(entity != null)
			return entity;
		else 
			throw new ObjetoInexistenteException("No se encontro el remito con id "+ idRemito);
	}
	
	public Integer grabar(Remito remito){
		RemitoEntity re = new RemitoEntity(remito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(re);
		session.getTransaction().commit();
		session.close();
		return re.toNegocio().getIdRemito();
	}
}
