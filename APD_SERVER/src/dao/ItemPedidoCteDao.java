package dao;

import java.util.List;

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

	
	public List<ItemPedidoCte> getByIdPedido(int idPedido) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ItemPedidoCteEntity entity = (ItemPedidoCteEntity) session.createQuery("from ArticuloEntity where id.idPedido = ?")
					.setParameter(0, idPedido)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return null;
		else 
			throw new ObjetoInexistenteException("No se encontraron items de Pedido con el id de pedido "+idPedido);
	}
	
	public void grabar(ItemPedidoCte itemPedidoCte){
		//TODO hacer metodo 
		//ClienteEntity ce = new ClienteEntity();
		/*JugadorEntity je = new JugadorEntity(jugador.getTipo(), jugador.getNumero(), jugador.getNombre());
		ClubEntity club = null;
		try {
			club = ClubDAO.getInstance().findByID(jugador.getClub().getIdClub());
		} catch (ClubException e) {
			e.printStackTrace();
		}
		je.setClub(club);
		je.setCategoria(jugador.getCategoria());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(je);
		session.getTransaction().commit();
		session.close();*/
	}
}
