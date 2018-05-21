package testTest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name="armario_id")
	private List<EstanteEntity> estantes;
	
	public ArmarioEntity() {}
	
	public ArmarioEntity(Armario armario) {
		this.id= armario.getId()==0 ? null : armario.getId();
		this.descripcion = armario.getDesc();
	}
		
	public ArmarioEntity(Integer id, String desc) {
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
	
	public Armario toNegocio() {
		return new Armario(id, descripcion);
	}
}
