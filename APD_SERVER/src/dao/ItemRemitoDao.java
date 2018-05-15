package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ItemRemitoEntity;
import entities.RemitoEntity;
import hbt.HibernateUtil;
import model.ItemRemito;
import model.Remito;

public class ItemRemitoDao {

	private static ItemRemitoDao instancia;
	
	private ItemRemitoDao() {}
	
	public static ItemRemitoDao getInstance() {
		if(instancia == null)
			instancia = new ItemRemitoDao();
		return instancia;
	}
	
	//TODO Grabar
	public ItemRemito grabar(ItemRemito itemRemito){
		ItemRemitoEntity ire = new ItemRemitoEntity(itemRemito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ire);
		session.getTransaction().commit();
		session.close();
		return ire.toNegocio();
	}
}
