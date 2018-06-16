package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.ClienteDTO;
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
	
	public Cliente getByIdUsuario(int idUsuario) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEntity entity = (ClienteEntity) session.createQuery("from ClienteEntity where id_usuario = ?")
					.setParameter(0, idUsuario)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un Cliente con id "+ idUsuario);
	}
	
	public List<ClienteDTO> getAllDTO(){
		List<ClienteDTO> clientes = new ArrayList<>();
		for(Cliente ce : getAll()){
			clientes.add(ce.toDTO());
		}
		return clientes;
	}
	
	public List<Cliente> getAll() {
		List<Cliente> result = new ArrayList<>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<ClienteEntity> list = session.createQuery("from ClienteEntity").list();
		for(ClienteEntity entity: list) {
			result.add(entity.toNegocio());
		}
		return result;
	}

	public Integer grabar(Cliente cliente){
		ClienteEntity ce = new ClienteEntity(cliente);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ce);
		session.getTransaction().commit();
		session.close();
		return ce.toNegocio().getIdCliente();
	}
}
