package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Ajuste;



public class AjusteDao {
	private static AjusteDao instancia;
	
	private AjusteDao() {}
	
	public static AjusteDao getInstance() {
		if(instancia == null)
			instancia = new AjusteDao();
		return instancia;
	}

	public Ajuste grabar(Ajuste ajuste){
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
