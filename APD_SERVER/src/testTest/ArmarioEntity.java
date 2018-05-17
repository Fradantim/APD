package testTest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TEST_ARMARIO")
public class ArmarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String descripcion;
	
	@OneToMany
	private List<EstanteEntity> estantes;
	
	public ArmarioEntity() {}
	
	public ArmarioEntity(Armario armario) {
		this.id= armario.getId()==0 ? null : armario.getId();
		this.descripcion = armario.getDesc();
		//TODO 0Consultar Tengo que guardar en el momento todos los estantes!?
		this.estantes = new ArrayList<>();
		for(Estante estante: armario.getEstantes())
			estantes.add(new EstanteEntity(estante));
	}
		
	public ArmarioEntity(Integer id, String desc, List<EstanteEntity> estantes) {
		super();
		this.id = id;
		this.descripcion = desc;
		this.estantes = estantes;
	}
	
	public ArmarioEntity(Integer id, String desc) {
		super();
		this.id = id;
		this.descripcion = desc;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return descripcion;
	}
	public void setDesc(String desc) {
		this.descripcion = desc;
	}
	public List<EstanteEntity> getEstantes() {
		return estantes;
	}
	public void setEstantes(List<EstanteEntity> estantes) {
		this.estantes = estantes;
	}
	
	public Armario toNegocio() {
		return new Armario(id, descripcion);
	}
	
	public Armario toNegocioFull() {
		List<Estante> estantesModelo = new ArrayList<>();
		for(EstanteEntity estanteEntity: getEstantes()) {
			estantesModelo.add(estanteEntity.toNegocio());
		}
		return new Armario(id, descripcion,estantesModelo);
	}
}
