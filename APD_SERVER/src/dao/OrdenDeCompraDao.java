package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.OrdenDeCompraDTO;
import entities.OrdenDeCompraEntity;
import entities.ReservaArticuloEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.OrdenDeCompra;
import model.ReservaArticulo;

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
		OrdenDeCompraEntity entity = (OrdenDeCompraEntity) session.createQuery("from OrdenDeCompraEntity where idOrdenCompra = ?")
					.setParameter(0, idOrden)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro una orden de compra con id " + idOrden);
	}
	
	public OrdenDeCompraEntity getByIdEntity(int idOrden) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		OrdenDeCompraEntity entity = (OrdenDeCompraEntity) session.createQuery("from OrdenDeCompraEntity where idOrdenCompra = ?")
					.setParameter(0, idOrden)
					.uniqueResult();
		if(entity != null)
			return entity;
		else 
			throw new ObjetoInexistenteException("No se encontro una orden de compra con id "+idOrden);
	}

	public Integer grabar(OrdenDeCompra ordenDeCompra) throws ObjetoInexistenteException{
		OrdenDeCompraEntity oce = new OrdenDeCompraEntity(ordenDeCompra);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(oce);
		session.getTransaction().commit();
		session.close();
        return oce.getId();
	}
	
	public List<OrdenDeCompraEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from OrdenDeCompraEntity");
		List<OrdenDeCompraEntity> list = q.list();
		return list;

	}


	public List<OrdenDeCompraDTO> getDTOByStatus(String estado) throws ObjetoInexistenteException{
		List<OrdenDeCompraDTO> ordenesDTO = new ArrayList<>();
		for(OrdenDeCompra orden: getByStatus(estado)) {
			ordenesDTO.add(orden.toDTO());
		}
		return ordenesDTO;
	}
	
	public List<OrdenDeCompra> getByStatus(String estado) throws ObjetoInexistenteException{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from OrdenDeCompraEntity where Estado = ?")
					.setParameter(0, estado);
		List<OrdenDeCompraEntity> list = q.list();
		ArrayList<OrdenDeCompra> modelList = new ArrayList<>();
		for(OrdenDeCompraEntity entity: list) {
			modelList.add(entity.toNegocio());
		}
		return modelList;
	}
		
	
	public List<OrdenDeCompra> getByStatusArt(String estado,String CodBarras) throws ObjetoInexistenteException{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from OrdenDeCompraEntity oc where oc.Estado = ? and oc.articulo.codDeBarras = ? ")
					.setParameter(0, estado)
					.setParameter(1, CodBarras);
		List<OrdenDeCompraEntity> list = q.list();
		
		if(list.isEmpty()!= true){
			ArrayList<OrdenDeCompra> modelList = new ArrayList<>();
			for(OrdenDeCompraEntity entity: list) {
				modelList.add(entity.toNegocio());
			}
			return modelList;
		}
		else 
			throw new ObjetoInexistenteException("No se encontro una orden de compra con estado: "+ estado + " y articulo: " + CodBarras );


	}
}
