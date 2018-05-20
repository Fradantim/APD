package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import entities.NotaCreditoEntity;
import entities.PagoEntity;
import hbt.HibernateUtil;
import model.NotaCredito;
import model.Pago;


public class NotaCreditoDao {
	private static NotaCreditoDao instancia;
	
	private NotaCreditoDao() {}
	
	public static NotaCreditoDao getInstance() {
		if(instancia == null)
			instancia = new NotaCreditoDao();
		return instancia;
	}

	public Integer grabar(NotaCredito notaCredito){
		NotaCreditoEntity entity = new NotaCreditoEntity(notaCredito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return entity.getIdMovimientoCtaCte();
	}
	
	/**
	 * Una factura puede no tener Nota de Credito asociada, entonces devolvera nulo
	 * @param idFactura
	 * @return
	 */
	public NotaCredito getNotaDeCreditoByIdFactura(int idFactura){
		List<NotaCredito> result = new ArrayList<>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		NotaCreditoEntity entity = (NotaCreditoEntity) session.createQuery("from MovimientoCtaCteEntity where idFacturaBonificada = ?")
				.setParameter(0, idFactura).uniqueResult();
		if(entity==null) {
			return null;
		}
		return entity.toNegocio();
	}
}
