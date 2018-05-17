package testTest;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;

public class EstanteDao {
private static EstanteDao instancia;
	
	private EstanteDao() {}
	
	public static EstanteDao getInstance() {
		if(instancia == null)
			instancia = new EstanteDao();
		return instancia;
	}

	public Estante getById(int id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EstanteEntity entity = (EstanteEntity) session.createQuery("from EstanteEntity where id = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(entity != null)
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro un EstanteEntity con id "+id);
	}
	
	public List<Estante> getByIdArmario(int id) throws ObjetoInexistenteException {
		//TODO 0Consultar Este metodo se usa asi? / se usa?
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from EstanteEntity where armario_id = ?")
				.setParameter(0, id);
		List<EstanteEntity> list = q.list();
		if(list != null) {
			ArrayList<Estante> estantes = new ArrayList<>();
			for(EstanteEntity entity: list) {
				estantes.add(entity.toNegocio());
			}
			return estantes;
		}
		else 
			throw new ObjetoInexistenteException("No se encontro un EstanteEntity con id "+id);
	}
	
	public Integer grabar(Estante estante){
		EstanteEntity estanteEntity = new EstanteEntity(estante);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(estanteEntity);
		session.getTransaction().commit();
		session.close();
		return estanteEntity.getId();
	}
	
	public List<Estante> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from EstanteEntity");
		List<EstanteEntity> list = q.list();
		ArrayList<Estante> estantes = new ArrayList<>();
		for(EstanteEntity entity: list) {
			estantes.add(entity.toNegocio());
		}
		return estantes;

	}
}
