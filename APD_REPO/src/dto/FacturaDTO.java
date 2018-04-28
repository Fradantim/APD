package dto;

import java.io.Serializable;
import java.util.Date;

public class FacturaDTO implements Serializable{

	private static final long serialVersionUID = -8682250635841752316L;

	private int id;
	private Date fecha;
	private int bonificacion;
	private String estado;
	
	public FacturaDTO(int id, Date fecha, int bonificacion, String estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.bonificacion = bonificacion;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getBonificacion() {
		return bonificacion;
	}
	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
