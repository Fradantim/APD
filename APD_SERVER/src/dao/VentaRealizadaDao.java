package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.VentaRealizadaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.VentaRealizada;


public class VentaRealizadaDao {
	private static VentaRealizadaDao instancia;
	
	private VentaRealizadaDao() {}
	
	public static VentaRealizadaDao getInstance() {
		if(instancia == null)
			instancia = new VentaRealizadaDao();
		return instancia;
	}

	public VentaRealizada grabar(VentaRealizada venta) throws ObjetoInexistenteException{
		VentaRealizadaEntity ae = new VentaRealizadaEntity(venta);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio();
	}
	
}
