package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import entities.ItemFacturaEntity;
import entities.ItemPedidoCteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.ItemFactura;
import model.ItemPedidoCte;

public class ItemFacturaDao {
	private static ItemFacturaDao instancia;
	
	private ItemFacturaDao() {}
	
	public static ItemFacturaDao getInstance() {
		if(instancia == null)
			instancia = new ItemFacturaDao();
		return instancia;
	}

	
	public List<ItemFactura> getByIdFactura(int idFactura) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ItemFactura where facturaId = ?").setParameter(0, idFactura);
		List<ItemFacturaEntity> entityList = q.list();
		if(entityList != null){
			ArrayList<ItemFactura> modelList = new ArrayList<>();
			for(ItemFacturaEntity entity: entityList) {
				modelList.add(entity.toNegocio());
			}
			return modelList;
		}
		else 
			throw new ObjetoInexistenteException("No se encontraron ItemFactura con idFactura "+idFactura);
	}
	
	public ItemFactura grabar(ItemFactura itemFactura) throws ObjetoInexistenteException{
		ItemFacturaEntity ae = new ItemFacturaEntity(itemFactura);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio();
	}
}
