package testTest;

public class Estante {
	private int id;
	private String descripcion;
	private Armario amrario;
	
	public Armario getAmrario() {
		return amrario;
	}
	public void setAmrario(Armario amrario) {
		this.amrario = amrario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Estante(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Integer guardar() {
		this.id=EstanteDao.getInstance().grabar(this);
		return this.id;
	}
}
