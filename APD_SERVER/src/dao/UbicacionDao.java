package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.UbicacionDTO;
import entities.ClienteEntity;
import entities.ItemFacturaEntity;
import entities.UbicacionEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Cliente;
import model.ItemFactura;
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
	 * @throws ObjetoInexistenteException 
	 * @throws ArticuloInexistenteException
	 */
	public List<Ubicacion> getByIdArticulo(String codDeBarras) throws ObjetoInexistenteException {		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Ubicacion where codigo = ?").setParameter(0, codDeBarras);
		List<UbicacionEntity> entityList = q.list();
		if(entityList != null) {
			ArrayList<Ubicacion> modelList = new ArrayList<>();
		    for(UbicacionEntity entity: entityList) {
		    	modelList.add(entity.toNegocio());
		    }
		    return modelList;			
		}else { 
		    throw new ObjetoInexistenteException("No se encontro un Articulo con codigo"+codDeBarras);
		}		
	}
	
	public Ubicacion getById(int ubicacionId) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UbicacionEntity entity = (UbicacionEntity) session.createQuery("from UbicacionEntity where id.idPedido = ?")
					.setParameter(0, ubicacionId)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con id "+ubicacionId);
	}
	
	public List<UbicacionDTO> getVacias() throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();	
		Session session = sf.openSession();
		Query q = session.createQuery("from UbicacionEntity where id.cantidad = 0");
		List<Ubicacion> entityList = q.list();
		if(entityList != null) {
			ArrayList<UbicacionDTO> modelList = new ArrayList<>();
		    for(Ubicacion ubi: entityList) {
		    	modelList.add(ubi.toDTO());
		    }
		    return modelList;	
		}else { 
		    throw new ObjetoInexistenteException("No se encontro la Ubicacion");
		}
	}
	
	
	public List<Ubicacion> getByIds(List<String> ubicacionesIds) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from UbicacionEntity where id.idPedido = ?");
		List<UbicacionEntity> entityList = q.list();
		if(entityList != null){
			List<Ubicacion> result = new ArrayList<>();
			for(UbicacionEntity entity : entityList)
			result.add(entity.toNegocio());
			return result;
	    }else{ 
			throw new ObjetoInexistenteException("No se encontraron ubicaciones con el id ");
		}
	}
	
	public Integer grabar(Ubicacion ubicacion){
		//TODO hacer metodo 
	
		UbicacionEntity ub = new UbicacionEntity(ubicacion);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ub);
		session.getTransaction().commit();
		session.close();
		return ub.toNegocio().getIdUbicacion();

	}
}
