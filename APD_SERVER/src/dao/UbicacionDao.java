package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.UbicacionDTO;
import entities.UbicacionEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Ubicacion;

public class UbicacionDao {
	private static UbicacionDao instancia;
	
	private UbicacionDao() {}
	
	public static UbicacionDao getInstance() {
		if(instancia == null)
			instancia = new UbicacionDao();
		return instancia;
	}

	/**
	 * 
	 * @param codDeBarras
	 * @return Ubicaciones ordenadas por fecha de vencimiento, la mas proxima a vencer primero, la entity tendra una tabla con los 2 elementos juntos
	 * @throws ArticuloInexistenteException
	 */
	public List<Ubicacion> getByIdArticulo(String codDeBarras) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UbicacionEntity entity = (UbicacionEntity) session.createQuery("from UbicacionEntity where id.idPedido = ?")
					.setParameter(0, codDeBarras)
					.uniqueResult();
		//if(entity != null)
			//TODO hacer carga
			return null;
	}
	
	public Ubicacion getById(int ubicacionId) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UbicacionEntity entity = (UbicacionEntity) session.createQuery("from UbicacionEntity where id.idPedido = ?")
					.setParameter(0, ubicacionId)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return null;
		else 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con id "+ubicacionId);
	}
	
	public List<UbicacionDTO> getVacias() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UbicacionEntity entity = (UbicacionEntity) session.createQuery("from UbicacionEntity where id.cantidad = 0")
					.uniqueResult();
		//if(entity != null)
			//TODO hacer carga
			return null;

	}
	
	
	public List<Ubicacion> getByIds(List<String> ubicacionesIds) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UbicacionEntity entity = (UbicacionEntity) session.createQuery("from UbicacionEntity where id.idPedido = ?")
					//.setParameter(0, codDeBarras)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return null;
		else 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con el id ");
	}
	
	public Ubicacion grabar(Ubicacion ubicacion){
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
