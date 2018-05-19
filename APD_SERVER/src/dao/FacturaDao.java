package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.FacturaDTO;
import entities.AjusteEntity;
import entities.FacturaEntity;
import entities.MovimientoInventarioEntity;
import exception.ObjetoInexistenteException;
import hbt.HibernateUtil;
import model.Articulo;
import model.Cliente;
import model.Factura;
import model.MovimientoCtaCte;
import model.MovimientoInventario;


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
			return entity.toNegocio();
		else 
			throw new ObjetoInexistenteException("No se encontro una factura con id "+idFactura);
	}
	
	public Integer grabar(Factura factura){
		FacturaEntity ae = new FacturaEntity(factura);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ae);
		session.getTransaction().commit();
		session.close();
		return ae.toNegocio().getIdMovimientoCtaCte();
	}
	
	public List<FacturaDTO> getDTOByStatus(Cliente cliente, String estado){
		List<FacturaDTO> facturasDTO = new ArrayList<>();
		for(Factura factura : getByStatus(cliente, estado)) {
			facturasDTO.add(factura.toDTO());
		}
		return facturasDTO;
	}
	
	public List<Factura> getByStatus(Cliente cliente, String estado){
		List<Factura> result = new ArrayList<>(); 
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<FacturaEntity> entities = session.createQuery("from MovimientoCtaCteEntity aE where aE.cliente.id = ? AND aE.estado = ?")
				.setParameter(0, cliente.getIdCliente()).setParameter(1, estado).list();
		for(FacturaEntity entity: entities) {
			Factura mov = entity.toNegocio();
			mov.setCliente(cliente);
			result.add(mov);
		}
		return result;
	}
	
	public float getSumImporteByIdCliente(int idCliente) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Double res= (Double) session.createQuery("select sum(importe) from MovimientoCtaCteEntity where cliente.id = ?")
					.setParameter(0, idCliente).uniqueResult();
		if(res == null){
			return 0F;
		}
		return res.floatValue();
	}
}
