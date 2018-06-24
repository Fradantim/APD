package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ProveedorDTO;
import entities.PedidoCteEntity;
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
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un Proveedor con id "+proveedorId);
	}
	public ProveedorEntity getByIdE(int proveedorId) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ProveedorEntity entity = (ProveedorEntity) session.createQuery("from ProveedorEntity where id.id = ?")
					.setParameter(0, proveedorId)
					.uniqueResult();
		if(entity != null)
			return entity;
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
	
	
	
	public Proveedor grabar(Proveedor proveedor) throws ObjetoInexistenteException{
		ProveedorEntity prove = new ProveedorEntity(proveedor);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(prove);
		session.getTransaction().commit();
		session.close();
		return prove.toNegocio();
	}
	
	public List<ProveedorEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ProveedorEntity");
		List<ProveedorEntity> list = q.list();
		return list;

	}

}
