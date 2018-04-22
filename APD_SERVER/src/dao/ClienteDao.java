package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEntity;
import exception.ClienteInexistenteException;
import hbt.HibernateUtil;
import model.Cliente;

public class ClienteDao {

	private static ClienteDao instancia;
	
	private ClienteDao() {}
	
	public static ClienteDao getInstance() {
		if(instancia == null)
			instancia = new ClienteDao();
		return instancia;
	}

	public Cliente getById(int idCliente) throws ClienteInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEntity entity = (ClienteEntity) session.createQuery("from ClienteEntity where id.id = ?")
					.setParameter(0, idCliente)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new Cliente();
		else 
			throw new ClienteInexistenteException("No se encontro un Cliente con id "+idCliente);
	}
	
	public void grabar(Cliente Cliente){
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
	}
}
