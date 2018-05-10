package dto;

import java.io.Serializable;
import java.util.Date;

public class FacturaDTO implements Serializable{

	private static final long serialVersionUID = -8682250635841752316L;

	private int id;
	private Date fecha;
	private int bonificacion;
	private String estado;
	private float importe;
	
	public FacturaDTO(int id, Date fecha, int bonificacion, String estado, float importe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.bonificacion = bonificacion;
		this.estado = estado;
		this.importe=importe;
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


	public float getImporte() {
		return importe;
	}


	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof FacturaDTO))return false;
	    FacturaDTO otherMyClass = (FacturaDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}
	
}
