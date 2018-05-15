package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dao.ArticuloDao;
import exception.ObjetoInexistenteException;
import model.Rotura;

@Entity  
@DiscriminatorValue("ROTURA") 
public class RoturaEntity extends MovimientoInventarioEntity{
	
	@Column (name="Encargado", nullable=true)
	private int encargado;
	@Column (name="UsuarioAutoriza", nullable=true)
	private int usrAutoriza;
	@Column (name="lugar", nullable=true)
	private int ubicacionId;
	
	public RoturaEntity() {}
	
	public RoturaEntity(Rotura rotura) {
		super(rotura.getCantidad(), new ArticuloEntity(rotura.getArticulo()));
		this.encargado = rotura.getEncargado();
		this.usrAutoriza = rotura.getUsrAutoriza();
		this.ubicacionId = rotura.getUbicacionId();
	}
	
	public Rotura toNegocio() throws ObjetoInexistenteException {
		Rotura rotura= new Rotura(cantidad,encargado,usrAutoriza,ubicacionId, articulo.toNegocio());
		//ajuste.setIdMovimiento(idMovimiento);
		return rotura;
	}
	
	public int getEncargado() {
		return encargado;
	}
	public void setEncargado(int encargado) {
		this.encargado = encargado;
	}
	public int getUsrAutoriza() {
		return usrAutoriza;
	}
	public void setUsrAutoriza(int usrAutoriza) {
		this.usrAutoriza = usrAutoriza;
	}
	public int getUbicacionId() {
		return ubicacionId;
	}
	public void setUbicacionId(int ubicacionId) {
		this.ubicacionId = ubicacionId;
	}
}
