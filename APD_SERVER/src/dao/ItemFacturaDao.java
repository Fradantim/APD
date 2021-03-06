package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ItemFacturaEntity;
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

	
	public List<ItemFactura> getByIdFactura(int idFactura){
		
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
			return new ArrayList<>();
	}
	
	public Integer grabar(ItemFactura itemFactura){
		ItemFacturaEntity ae = new ItemFacturaEntity(itemFactura);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdItem();
	}
	
	public float getSumImporteByIdFactura(int idFactura) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Double res= (Double) session.createQuery("select sum(cantidad*articulo.precioDeVenta) from ItemFacturaEntity where idMovimientoCtaCte = ?")
					.setParameter(0, idFactura)
					.uniqueResult();
		if(res == null){
			return 0F;
		}
		return res.floatValue();
	}
}
