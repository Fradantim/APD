package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.OrdenDeCompraEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.OrdenDeCompra;
import view.OrdenDeCompraView;

public class OrdenDeCompraDao {
	private static OrdenDeCompraDao instancia;
	
	private OrdenDeCompraDao() {}
	
	public static OrdenDeCompraDao getInstance() {
		if(instancia == null)
			instancia = new OrdenDeCompraDao();
		return instancia;
	}

	public OrdenDeCompra getById(int idOrden) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		OrdenDeCompraEntity entity = (OrdenDeCompraEntity) session.createQuery("from OrdenDeCompraEntity where id.id = ?")
					.setParameter(0, idOrden)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new OrdenDeCompra();
		else 
			throw new ObjetoInexistenteException("No se encontro una orden de compra con id "+idOrden);
	}
	
	public void grabar(OrdenDeCompra orden){
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
	
	public List<OrdenDeCompraView> getViewByStatus(String estado){
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
	
	public List<OrdenDeCompra> getByStatus(String estado){
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
}
