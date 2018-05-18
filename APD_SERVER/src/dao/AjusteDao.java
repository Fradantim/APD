package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.AjusteEntity;
import entities.ItemFacturaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Ajuste;
import model.Articulo;
import model.ItemFactura;
import model.MovimientoInventario;

public class AjusteDao {
	private static AjusteDao instancia;
	
	private AjusteDao() {}
	
	public static AjusteDao getInstance() {
		if(instancia == null)
			instancia = new AjusteDao();
		return instancia;
	}

	public Integer grabar(Ajuste ajuste){
		AjusteEntity ae = new AjusteEntity(ajuste);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdMovimiento();
	}
	
	public MovimientoInventario getById(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		AjusteEntity entity = (AjusteEntity) session.createQuery("from MovimientoInventarioEntity where idMovimiento = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(entity != null){
			return entity.toNegocio();
		}
		else 
			throw new ObjetoInexistenteException("No existe un Ajuste con id "+ id);
	}
	
	public List<Ajuste> getByIdArticulo(String codDeBarras){
		List<Ajuste> result = new ArrayList<>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<AjusteEntity> entities = session.createQuery("from MovimientoInventarioEntity aE where aE.articulo.codDeBarras = ?").setParameter(0, codDeBarras).list();
		Articulo art=null;
		try {
			art= ArticuloDao.getInstance().getById(codDeBarras);
		} catch (ObjetoInexistenteException e) {
			//Esto nunca deberia salir....
			e.printStackTrace();
		}
		for(AjusteEntity entity: entities) {
			Ajuste ajuste = entity.toNegocio();
			ajuste.setArticulo(art);
			result.add(ajuste);
		}
		return result;
	}
	
}
