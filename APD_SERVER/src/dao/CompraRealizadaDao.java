package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CompraRealizadaEntity;
import entities.MovimientoInventarioEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Articulo;
import model.CompraRealizada;
import model.MovimientoInventario;


public class CompraRealizadaDao {
	private static CompraRealizadaDao instancia;
	
	private CompraRealizadaDao() {}
	
	public static CompraRealizadaDao getInstance() {
		if(instancia == null)
			instancia = new CompraRealizadaDao();
		return instancia;
	}

	public Integer grabar(CompraRealizada compra){
		CompraRealizadaEntity ae = new CompraRealizadaEntity(compra);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdMovimiento();
	}
	
	public Integer getSumCantidadById(int id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Long res= (Long) session.createQuery("select sum(cantidad) from MovimientoInventarioEntity where ArticuloId = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(res == null){
			res=0L;
		}
		return res.intValue();
	}
	
	public List<MovimientoInventario> getByIdArticulo(String codDeBarras){
		List<MovimientoInventario> result = new ArrayList<>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<MovimientoInventarioEntity> entities = session.createQuery("from MovimientoInventarioEntity aE where aE.articulo.codDeBarras = ?").setParameter(0, codDeBarras).list();
		Articulo art=null;
		try {
			art= ArticuloDao.getInstance().getById(codDeBarras);
		} catch (ObjetoInexistenteException e) {
			//Esto nunca deberia salir....
			e.printStackTrace();
		}
		for(MovimientoInventarioEntity entity: entities) {
			MovimientoInventario mov = entity.toNegocio();
			mov.setArticulo(art);
			result.add(mov);
		}
		return result;
	}
}
