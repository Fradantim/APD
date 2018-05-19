package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entities.ItemRemitoEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.ItemRemito;

public class ItemRemitoDao {
	private static ItemRemitoDao instancia;
	
	private ItemRemitoDao() {}
	
	public static ItemRemitoDao getInstance() {
		if(instancia == null)
			instancia = new ItemRemitoDao();
		return instancia;
	}
	
public List<ItemRemito> getByIdRemito(int idRemito) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ItemRemitoEntity where remito.idRemito = ?").setParameter(0, idRemito);
		List<ItemRemitoEntity> entityList = q.list();
		if(entityList != null){
			ArrayList<ItemRemito> modelList = new ArrayList<>();
			for(ItemRemitoEntity entity: entityList) {
				modelList.add(entity.toNegocio());
			}
			return modelList;
		}
		else 
			throw new ObjetoInexistenteException("No se encontraron ItemRemito con idRemito "+ idRemito);
	}
	
	public Integer grabar(ItemRemito itemRemito) throws ObjetoInexistenteException{
		ItemRemitoEntity ae = new ItemRemitoEntity(itemRemito);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdItem();
	}
	
}
