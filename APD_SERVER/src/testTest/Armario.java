package testTest;

import java.util.List;

import exception.ObjetoInexistenteException;

public class Armario {
	private int id;
	private String desc;
	
	//la lista la tengo por si llego a necesitar implementar un cache, pero no estoy obligado a usarla
	@SuppressWarnings("unused")
	private List<Estante> estantes;

	public Armario(int id, String desc, List<Estante> estantes) {
		this.id = id;
		this.desc = desc;
		this.estantes = estantes;
	}
	
	public Armario(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public List<Estante> getEstantes(){
		return EstanteDao.getInstance().getByIdArmario(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void guardar(){
		this.id=ArmarioDao.getInstance().grabar(this);
	}
	
	public Integer agregarEstante(Estante estante) {
		estante.setAmrario(this);
		return estante.guardar();
	}
}
