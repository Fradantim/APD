package testTest;

import java.util.ArrayList;
import java.util.List;

import exception.ObjetoInexistenteException;

public class Armario {
	private int id;
	private String desc;
	private List<Estante> estantes;

	public Armario(int id, String desc, List<Estante> estantes) {
		this.id = id;
		this.desc = desc;
		this.estantes = estantes;
	}
	
	public Armario(int id, String desc) {
		this.id = id;
		this.desc = desc;
		//TODO 0 Consultar, si no me pasan los estantes en el momento tengo que crear una lista vacia?
		this.estantes= new ArrayList<>();
	}

	public List<Estante> getEstantes() {
		return estantes;
	}

	public void setEstantes(List<Estante> estantes) {
		this.estantes = estantes;
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
}
