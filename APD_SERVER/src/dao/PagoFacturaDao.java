package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaPagoEntity;
import entities.PagoEntity;
import hbt.HibernateUtil;
import model.Pago;


public class PagoFacturaDao {
	private static PagoFacturaDao instancia;
	
	private PagoFacturaDao() {}
	
	public static PagoFacturaDao getInstance() {
		if(instancia == null)
			instancia = new PagoFacturaDao();
		return instancia;
	}

	
	public List<Pago> getByIdFactura(int idFactura) {
		//TODO 0HACEME
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		FacturaPagoEntity entity = (FacturaPagoEntity) session.createQuery("from FacturaPagoEntity where id.idFactura = ?")
					.setParameter(0, idFactura)
					.uniqueResult();
		//if(entity != null)
			//TODO hacer carga
			return null;
	}
	
	public Integer grabar(Pago movimiento){
		PagoEntity pago = new PagoEntity(movimiento);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pago);
		session.getTransaction().commit();
		session.close();
		return pago.getIdMovimientoCtaCte();
	}
}
