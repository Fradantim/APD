package dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Producto;

public class ProductoDao {
	private static ProductoDao instancia;
	
	private ProductoDao() {}
	
	public static ProductoDao getInstance() {
		if(instancia == null)
			instancia = new ProductoDao();
		return instancia;
	}

	public Producto grabar(Producto prod) throws ObjetoInexistenteException{
		ProductoEntity pr = new ProductoEntity(prod);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(pr);
		session.getTransaction().commit();
		session.close();
		return pr.toNegocio();
	}
	
	public List<ProductoEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ProductoEntity");
		List<ProductoEntity> list = q.list();
		return list;

	}

	

}
