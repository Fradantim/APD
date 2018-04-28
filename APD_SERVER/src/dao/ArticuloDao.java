package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ArticuloDTO;
import entities.ArticuloEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Articulo;

public class ArticuloDao {
	private static ArticuloDao instancia;
	
	private ArticuloDao() {}
	
	public static ArticuloDao getInstance() {
		if(instancia == null)
			instancia = new ArticuloDao();
		return instancia;
	}

	public Articulo getById(String codDeBarras) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity where codDeBarras = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		if(entity != null){
			return entity.toNegocio();
		}
		else 
			throw new ObjetoInexistenteException("No existe un Articulo con codigo de barras "+ codDeBarras);
	}
	
	public List<ArticuloDTO> getAllDTO() {
		List<ArticuloEntity> articlosE= getAll();
		List<ArticuloDTO> articulos= new ArrayList<>();
		for(ArticuloEntity ae : articlosE){
			articulos.add(ae.toNegocio().toDTO());
		}
		return articulos;
	}
	
	public List<ArticuloEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ArticuloEntity");
		List<ArticuloEntity> list = q.list();
		return list;
	}
	
	public Articulo grabar(Articulo articulo){
		ArticuloEntity ae = new ArticuloEntity(articulo);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio();
	}
}
