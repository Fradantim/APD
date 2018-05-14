package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.FacturaDTO;
import entities.FacturaEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Factura;


public class FacturaDao {
	private static FacturaDao instancia;
	
	private FacturaDao() {}
	
	public static FacturaDao getInstance() {
		if(instancia == null)
			instancia = new FacturaDao();
		return instancia;
	}

	public Factura getById(int idFactura) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		FacturaEntity entity = (FacturaEntity) session.createQuery("from MovimientoCtaCteEntity where idMovimientoCtaCte = ?")
					.setParameter(0, idFactura)
					.uniqueResult();
		if(entity != null)
			//TODO hacer carga
			return new Factura();
		else 
			throw new ObjetoInexistenteException("No se encontro una factura con id "+idFactura);
	}
	
	public Factura grabar(Factura factura){
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
		return null;
	}
	
	public List<FacturaDTO> getDTOByStatus(String estado){
		List<FacturaDTO> facturasDTO = new ArrayList<>();
		for(Factura factura : getByStatus(estado)) {
			facturasDTO.add(factura.toDTO());
		}
		return facturasDTO;
	}
	
	public List<Factura> getByStatus(String estado){
		//TODO hacer metodo buscar como recuperar lista de hql
		return null;
	}
}
