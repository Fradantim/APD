package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import exception.ObjetoInexistenteException;

public abstract class MovimientoCtaCte {
	protected int idMovimientoCtaCte;
	protected Date fecha;
	protected float importe;
	protected Cliente cliente;
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public int getIdMovimientoCtaCte() {
		return idMovimientoCtaCte;
	}
	public void setIdMovimientoCtaCte(int idMovimientoCtaCte) {
		this.idMovimientoCtaCte = idMovimientoCtaCte;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public Integer guardar() throws ObjetoInexistenteException {
		return null;
	}
	
	protected String getFormatedDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
		return format.format(fecha);
	}

}
