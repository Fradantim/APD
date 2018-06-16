package dto;

import java.io.Serializable;
import java.util.Date;

public class PedidoCteDTO implements Serializable {

	private static final long serialVersionUID = -11707653909330059L;
	public static String ESTADO_NUEVO = "Nuevo";
	public static String ESTADO_CANCEL = "Cancelado"; //(estado final)
	public static String ESTADO_PENDIENTE_APROB_CRED = "Pendiente aprobacion crediticia";
	public static String ESTADO_APROB_CRED_RECH	= "Aprobacion crediticia rechazada";
	public static String ESTADO_APROB_CRED_APROB = "Aprobacion crediticia aprobada";
	public static String ESTADO_STOCK_PENDIENTE = "Pendiente de ingreso de stock";
	public static String ESTADO_STOCK_SUFICIENTE = "Stock suficiente para despacho";
	public static String ESTADO_DESPACHADO = "Pedido despachado"; // (estado final)
	
	private int id;
	private Date fechaGeneracion;
	private float total;
	private String estado;
	private ClienteDTO cliente;
	
	public PedidoCteDTO(int id, Date fechaGeneracion, float total, String estado, ClienteDTO cliente) {
		super();
		this.id = id;
		this.fechaGeneracion = fechaGeneracion;
		this.total = total;
		this.estado = estado;
		this.cliente= cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof PedidoCteDTO))return false;
	    PedidoCteDTO otherMyClass = (PedidoCteDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
}
