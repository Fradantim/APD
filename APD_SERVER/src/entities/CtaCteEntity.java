package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.ClienteDao;
import exception.ObjetoInexistenteException;
import model.CtaCte;

@Entity
@Table(name="CtaCte")
public class CtaCteEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column (name="IdCtaCte")
	private Integer idCtaCte;
	@Column (name="IdCliente", nullable=true)	
	private Integer idCliente; 

	public CtaCteEntity() {	}

	public CtaCteEntity(CtaCte ctaCte) {
		this.idCliente = ctaCte.getCliente().getIdCliente();
	}

	public Integer getIdCtaCte() {
		return idCtaCte;
	}

	public void setIdCtaCte(Integer idCtaCte) {
		this.idCtaCte = idCtaCte;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	public CtaCte toNegocio() throws ObjetoInexistenteException{
		CtaCte guardada= new CtaCte(idCtaCte,ClienteDao.getInstance().getById(idCliente));
		return guardada;
	}
}
