package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ItemFacturaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.ItemFactura;

public class ItemFacturaDao {
	private static ItemFacturaDao instancia;
	
	private ItemFacturaDao() {}
	
	public static ItemFacturaDao getInstance() {
		if(instancia == null)
			instancia = new ItemFacturaDao();
		return instancia;
	}

	
	public List<ItemFactura> getByIdFactura(int idFactura) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ItemFacturaEntity entity = (ItemFacturaEntity) session.createQuery("from ItemFactura where id.idFactura = ?")
					.setParameter(0, idFactura)
					.uniqueResult();
		//if(entity != null)
			//TODO hacer carga
			return null;
	
	}
	
	public void grabar(ItemFactura itemFactura){
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
