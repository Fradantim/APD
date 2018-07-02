package dao;

import java.util.ArrayList;
import java.util.List;

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
	public ArticuloEntity getByCodigo(String codDeBarras) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity where codDeBarras = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		if(entity != null){
			return entity;
		}
		else 
			throw new ObjetoInexistenteException("No existe un Articulo con codigo de barras "+ codDeBarras);
	}
	
	public Articulo getByRealId(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity where id = ?")
					.setParameter(0, id).uniqueResult();
		if(entity != null){
			return entity.toNegocio();
		}
		else 
			throw new ObjetoInexistenteException("No existe un Articulo con Id "+ id);
	}
	
	public ArticuloEntity getByIdProd(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity where id = ?")
					.setParameter(0, id).uniqueResult();
		if(entity != null){
			return entity;
		}
		else 
			throw new ObjetoInexistenteException("No existe un Articulo con codigo de barras "+ id);
	}

	
	public Articulo  getByCodArt(String codDeBarras) throws ObjetoInexistenteException {
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
		List<Articulo> articlos= getAll();
		List<ArticuloDTO> articulosDTO= new ArrayList<>();
		for(Articulo art : articlos){
			articulosDTO.add(art.toDTO());
		}
		return articulosDTO;
	}
	
	public List<Articulo> getAll() {
		List<Articulo> articulos = new ArrayList<>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<ArticuloEntity> list = session.createQuery("from ArticuloEntity").list();
		for(ArticuloEntity entity: list) {
			articulos.add(entity.toNegocio());
		}
		return articulos;
	}
	
	public Integer grabar(Articulo articulo){
		ArticuloEntity ae = new ArticuloEntity(articulo);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getId();
	}
}
