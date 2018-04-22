package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hbt.HibernateUtil;
import model.NotaCredito;


public class NotaCreditoDao {
	private static NotaCreditoDao instancia;
	
	private NotaCreditoDao() {}
	
	public static NotaCreditoDao getInstance() {
		if(instancia == null)
			instancia = new NotaCreditoDao();
		return instancia;
	}

	public NotaCredito grabar(NotaCredito notaCredito){
		//TODO hacer metodo 
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
