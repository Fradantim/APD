package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.UbicacionDTO;
import entities.LoteEntity;

import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Lote;


public class LoteDao {
private static LoteDao instancia;
	
	private LoteDao() {}
	
	public static LoteDao getInstance() {
		if(instancia == null)
			instancia = new LoteDao();
		return instancia;
	}

	/**
	 * 
	 * @param codDeBarras
	 * @return Ubicaciones ordenadas por fecha de vencimiento, la mas proxima a vencer primero, la entity tendra una tabla con los 2 elementos juntos
	 * @throws ArticuloInexistenteException
	 */
		
	public Lote getById(int loteId) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		LoteEntity entity = (LoteEntity) session.createQuery("from LoteEntity where id.id = ?")
					.setParameter(0, loteId)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return null;
		else 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con id "+loteId);
	}
	
	public List<Lote> getByIds(List<String> lotesIds) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		LoteEntity entity = (LoteEntity) session.createQuery("from LoteEntity where id.id = ?")
					//.setParameter(0, codDeBarras)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return null;
		else 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con el id ");
	}
	
	public Integer grabar(Lote lote){
		//TODO hacer metodo 
	
		LoteEntity lot = new LoteEntity(lote);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(lot);
		session.getTransaction().commit();
		session.close();
		return lot.toNegocio().getId();
		

	}

	/*public Object getAll() {
		
		return null;
	}*/

}
