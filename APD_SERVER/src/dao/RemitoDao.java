package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEntity;
import entities.ClienteEntity;
import entities.RemitoEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Articulo;
import model.ItemPedidoCte;
import model.Remito;

public class RemitoDao {
	
	private static RemitoDao instancia;
	
	private RemitoDao() {}
	
	public static RemitoDao getInstance() {
		if(instancia == null)
			instancia = new RemitoDao();
		return instancia;
	}
	
	//get by ID
	public Remito getById(int idRemito) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity entity = (RemitoEntity) session.createQuery("from RemitoEntity where IdRemito = ?")
					.setParameter(0, idRemito)
					.uniqueResult();
		if(entity != null){
			return entity.toNegocio();
		}
		else 
			throw new ObjetoInexistenteException("No existe un Remito con Id "+ idRemito);
	}
	//getAll
	
	//Guardar junto con sus items
	public Remito grabar(Remito remito){
		RemitoEntity re = new RemitoEntity(remito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(re);
		session.getTransaction().commit();
		session.close();
		return re.toNegocio();
	}
	
	//Remito no tiene items remito, Articulo -> mov inventarios
}
