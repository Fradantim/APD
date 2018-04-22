package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Articulo;
import model.VentaRealizada;


public class VentaRealizadaDao {
	private static VentaRealizadaDao instancia;
	
	private VentaRealizadaDao() {}
	
	public static VentaRealizadaDao getInstance() {
		if(instancia == null)
			instancia = new VentaRealizadaDao();
		return instancia;
	}

	public VentaRealizada grabar(Articulo articulo){
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
