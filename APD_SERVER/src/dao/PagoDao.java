package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaPagoEntity;
import hbt.HibernateUtil;
import model.Pago;


public class PagoDao {
	private static PagoDao instancia;
	
	private PagoDao() {}
	
	public static PagoDao getInstance() {
		if(instancia == null)
			instancia = new PagoDao();
		return instancia;
	}

	
	public List<Pago> getByIdFactura(int idFactura) {
		
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
		//TODO 0hacer metodo 
		//ClienteEntity ce = new ClienteEntity();
		/*JugadorEntity je = new JugadorEntity(jugador.getTipo(), jugador.getNumero(), jugador.getNombre());
		ClubEntity club = null;
		try {
			club = ClubDAO.getInstance().findByID(jugador.getClub().getIdClub());
		} catch (ClubException e) {
			e.printStackTrace();
		}
		je.setClub(club);
		je.setCategoria(jugador.getCategoria());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(je);
		session.getTransaction().commit();
		session.close();*/
		return null;
	}
}
