package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import exception.ObjetoInexistenteException;

public abstract class MovimientoCtaCte {
	protected int idMovimientoCtaCte;
	protected Date fecha;
	protected String detalle;
	protected float importe;
	protected CtaCte cuentaCliente;
	
	public CtaCte getCuentaCliente() {
		return cuentaCliente;
	}
	public void setCuentaCliente(CtaCte cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public MovimientoCtaCte guardar() throws ObjetoInexistenteException {
		return null;
	}
	
	protected String getFormatedDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
		return format.format(fecha);
	}


}
