package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEntity;
import entities.UsuarioEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Cliente;
import model.Usuario;

public class UsuarioDao {
	private static UsuarioDao instancia;
	
	private UsuarioDao() {}
	
	public static UsuarioDao getInstance() {
		if(instancia == null)
			instancia = new UsuarioDao();
		return instancia;
	}
	
	public Usuario getById(int idUsuario) throws ObjetoInexistenteException {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity entity = (UsuarioEntity) session.createQuery("from UsuarioEntity where idUsuario = ?")
					.setParameter(0, idUsuario)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un Usuario con id "+idUsuario);
	}
	
	public Integer grabar(Usuario usuario){
		UsuarioEntity ue = new UsuarioEntity(usuario);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ue);
		session.getTransaction().commit();
		session.close();
		return ue.toNegocio().getIdUsuario();
	}
}
