package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEntity;
import exception.ArticuloInexistenteException;
import hbt.HibernateUtil;
import model.Articulo;
import view.ArticuloView;

public class ArticuloDao {
	private static ArticuloDao instancia;
	
	private ArticuloDao() {}
	
	public static ArticuloDao getInstance() {
		if(instancia == null)
			instancia = new ArticuloDao();
		return instancia;
	}

	public Articulo getById(String codDeBarras) throws ArticuloInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity where id.codDeBarras = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new Articulo();
		else 
			throw new ArticuloInexistenteException("No se encontro un Articulo con codigo de Barras "+codDeBarras);
	}
	
	public List<ArticuloView> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity entity = (ArticuloEntity) session.createQuery("from ArticuloEntity");
		//TODO hacer metodo
		return null;
	}
	
	public void grabar(Articulo articulo){
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
