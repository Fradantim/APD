package dto;

import java.io.Serializable;

public class ReservaArticuloDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idReserva;
	private int cantidad;
	private String estado;
	private int idarticulo;
	private int idpedido;
	private int idorden;

	public ReservaArticuloDTO(int id, int cant, String estado,int idart, 
			int idped,int idord) {
		super();
		this.idReserva = id;
		this.cantidad = cant;
		this.estado = estado;
		this.idarticulo = idart;
		this.idpedido = idped;
		this.idorden = idord;
	}
	

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdarticulo() {
		return idarticulo;
	}

	public void setIdarticulo(int idarticulo) {
		this.idarticulo = idarticulo;
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getIdorden() {
		return idorden;
	}

	public void setIdorden(int idorden) {
		this.idorden = idorden;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ReservaArticuloDTO))return false;
	    ReservaArticuloDTO otherMyClass = (ReservaArticuloDTO)other;
	    if(otherMyClass.getIdReserva()==idReserva) {
	    	return true;
	    }return false;
	}

	
}
