package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CompraRealizadaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.CompraRealizada;


public class CompraRealizadaDao {
	private static CompraRealizadaDao instancia;
	
	private CompraRealizadaDao() {}
	
	public static CompraRealizadaDao getInstance() {
		if(instancia == null)
			instancia = new CompraRealizadaDao();
		return instancia;
	}

	public CompraRealizada grabar(CompraRealizada compra) throws ObjetoInexistenteException{
		CompraRealizadaEntity ae = new CompraRealizadaEntity(compra);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio();
	}
	
	public Integer getSumCantidadById(String codDeBarras) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Long res= (Long) session.createQuery("select sum(cantidad) from MovimientoInventarioEntity where ArticuloId = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		if(res == null){
			res=0L;
		}
		return res.intValue();
	}
}
