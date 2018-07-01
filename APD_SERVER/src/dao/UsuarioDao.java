package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
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
	
	public Usuario getByIdAndPass(int idUsuario,String password) throws ObjetoInexistenteException {
		//TODO Testear
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity entity = (UsuarioEntity) session.createQuery("from UsuarioEntity where idUsuario = ? and contrasena = ?")
					.setParameter(0, idUsuario).setParameter(0, password)
					.uniqueResult();
		System.out.println("e");
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un Usuario con id "+idUsuario+" y contraseña "+password);
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
	
	public void eliminar(Usuario usuario){
		UsuarioEntity ue = new UsuarioEntity(usuario);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(ue);
		session.getTransaction().commit();
		session.close();
	}
}
