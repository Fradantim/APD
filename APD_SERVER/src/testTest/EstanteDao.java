package testTest;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Estante> getByIdArmario(int id) {
		ArrayList<Estante> result = new ArrayList<>();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<EstanteEntity> entities = session.createQuery("from EstanteEntity eE where eE.armario.id = ?").setParameter(0, id).list();
		Armario armario = null;
		try {
			armario = ArmarioDao.getInstance().getById(id);
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		for(EstanteEntity entity: entities) {
			Estante estante = entity.toNegocio();
			estante.setAmrario(armario);
			result.add(estante);
		}
		return result;
	}
	
	public Integer grabar(Estante estante){
		EstanteEntity estanteEntity = new EstanteEntity(estante);
		ArmarioEntity armarioEntity = null;
		try {
			armarioEntity= new ArmarioEntity(ArmarioDao.getInstance().getById(estante.getAmrario().getId()));
		} catch (ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		estanteEntity.setArmario(armarioEntity);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(estanteEntity);
		session.getTransaction().commit();
		session.close();
		return estanteEntity.getId();
	}
}
