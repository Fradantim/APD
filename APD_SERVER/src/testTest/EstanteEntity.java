package testTest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TEST_ESTANTE")
public class EstanteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="armario_id")
	private ArmarioEntity armario;
		
	public EstanteEntity() {}
	
	public EstanteEntity(Estante estante) {
		this.id= estante.getId()==0 ? null : estante.getId();
		this.descripcion = estante.getDescripcion();
	}
	
	public EstanteEntity(Integer id, String descripcion) {
		super();
		this.id= id==0 ? null : id;
		this.descripcion = descripcion;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Estante toNegocio() {
		Estante est= new Estante(id, descripcion);
		est.setAmrario(armario.toNegocio());
		return est; 
	}

	public ArmarioEntity getArmario() {
		return armario;
	}

	public void setArmario(ArmarioEntity armario) {
		this.armario = armario;
	}
	
}
