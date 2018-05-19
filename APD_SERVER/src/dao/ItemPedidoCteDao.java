package dao;


import java.util.ArrayList;
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

 	
	public List<ItemPedidoCte> getByIdPedido(int id_pedido) throws ObjetoInexistenteException{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ItemPedidoCteEntity where pedido = ?").setParameter(0, id_pedido);
		List<ItemPedidoCteEntity> entityList = q.list();
		if(entityList != null) {
			ArrayList<ItemPedidoCte> modelList = new ArrayList<>();
			for(ItemPedidoCteEntity entity: entityList) {
				modelList.add(entity.toNegocio());
			}
			return modelList;
		}
		else 
			throw new ObjetoInexistenteException("No se encontraron ItemPedidoCte con id_pedido "+id_pedido);
	}
	
	public ItemPedidoCte getById(int idItemPedido) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ItemPedidoCteEntity entity = (ItemPedidoCteEntity) session.createQuery("from ItemPedidoCteEntity where idItem = ?")
					.setParameter(0, idItemPedido)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un ItemPedidoCte con id "+idItemPedido);
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
	
	public List<Object[]> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("SELECT it.idItem, it.Cantidad, it.articulo.codDeBarras, it.pedido.IdPedidoCte  from ItemPedidoCteEntity as it  ");
		List<Object[]> list = q.list();
		return list;
		
		
		
	}  
	
	

	
}
