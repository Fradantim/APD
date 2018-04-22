package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaPagoEntity;
import hbt.HibernateUtil;
import model.MovimientoCtaCte;
import model.Pago;


public class AcreditacionesDao {
	private static AcreditacionesDao instancia;
	
	private AcreditacionesDao() {}
	
	public static AcreditacionesDao getInstance() {
		if(instancia == null)
			instancia = new AcreditacionesDao();
		return instancia;
	}

	
	public List<MovimientoCtaCte> getByIdFactura(int idFactura) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		//traer pagos y NCs!!!
		/*FacturaPagoEntity entity = (FacturaPagoEntity) session.createQuery("from FacturaPagoEntity where id.idFactura = ?")
					.setParameter(0, idFactura)
					.uniqueResult();*/
		//if(entity != null)
			//TODO hacer carga
			return null;
	}
}
