package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ProveedorDTO;
import entities.ProveedorEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Proveedor;


public class ProveedorDao {
	private static ProveedorDao instancia;
	
	private ProveedorDao() {}
	
	public static ProveedorDao getInstance() {
		if(instancia == null)
			instancia = new ProveedorDao();
		return instancia;
	}

	public Proveedor getById(int proveedorId) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProveedorEntity entity = (ProveedorEntity) session.createQuery("from ProveedorEntity where id.id = ?")
					.setParameter(0, proveedorId)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new Proveedor();
		else 
			throw new ObjetoInexistenteException("No se encontro un Proveedor con id "+proveedorId);
	}
	
	public List<ProveedorDTO> getByArticulo(int articuloId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProveedorEntity entity = (ProveedorEntity) session.createQuery("from ArticuloEntity");
		//TODO hacer metodo
		return null;
	}
	
	
	
	public void grabar(Proveedor proveedor){
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
