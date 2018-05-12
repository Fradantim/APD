package dao;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entities.ItemPedidoCteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.ItemPedidoCte;

public class ItemPedidoCteDao {
	private static ItemPedidoCteDao instancia;
	
	private ItemPedidoCteDao() {}
	
	public static ItemPedidoCteDao getInstance() {
		if(instancia == null)
			instancia = new ItemPedidoCteDao();
		return instancia;
	}

 	
	public ItemPedidoCte getByIdPedido(int id_pedido) throws ObjetoInexistenteException{
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ItemPedidoCteEntity entity = (ItemPedidoCteEntity) session.createQuery("from ItemPedidoCteEntity where id_pedido = ?")
					.setParameter(0, id_pedido)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un ItemPedidoCte con idItem "+id_pedido);
	}
 	
	public ItemPedidoCte grabar(ItemPedidoCte itemPedidoCte) throws ObjetoInexistenteException{
		ItemPedidoCteEntity Ipce = new ItemPedidoCteEntity(itemPedidoCte);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(Ipce);
		session.getTransaction().commit();
		session.close();
        return Ipce.toNegocio();

	}
	
	public List<ItemPedidoCteEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ItemPedidoCteEntity");
		List<ItemPedidoCteEntity> list = q.list();
		return list;

	}
	
}
