package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ClienteDTO;
import entities.ArticuloEntity;
import entities.ClienteEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Cliente;

public class ClienteDao {

	private static ClienteDao instancia;
	
	private ClienteDao() {}
	
	public static ClienteDao getInstance() {
		if(instancia == null)
			instancia = new ClienteDao();
		return instancia;
	}

	public Cliente getById(int idCliente) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEntity entity = (ClienteEntity) session.createQuery("from ClienteEntity where id = ?")
					.setParameter(0, idCliente)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un Cliente con id "+idCliente);
	}
	
	public List<ClienteDTO> getAllDTO() {
		List<ClienteEntity> clientesE= getAll();
		List<ClienteDTO> clientes= new ArrayList<>();
		for(ClienteEntity ce : clientesE){
			clientes.add(ce.toNegocio().toDTO());
		}
		return clientes;
	}
	
	public List<ClienteEntity> getAll() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ClienteEntity");
		List<ClienteEntity> list = q.list();
		return list;
	}

	public Cliente grabar(Cliente cliente){
		ClienteEntity ce = new ClienteEntity(cliente);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ce);
		session.getTransaction().commit();
		session.close();
		return ce.toNegocio();
	}
}
