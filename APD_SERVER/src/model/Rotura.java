package model;

public class Rotura extends MovimientoCtaCte{
	private String encargado;
	private Usuario usrAutoriza;
	private String destino;
	
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public Usuario getUsrAutoriza() {
		return usrAutoriza;
	}
	public void setUsrAutoriza(Usuario usrAutoriza) {
		this.usrAutoriza = usrAutoriza;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Override
	public void guardar() {
		
	}
}
