package dto;

import java.io.Serializable;
import java.util.Date;

public class PedidoCteDTO implements Serializable {

	private static final long serialVersionUID = -11707653909330059L;

	private int id;
	private Date fechaGeneracion;
	private float total;
	private String estado;
	private int idCliente;
	private float clienteSaldo;
	private String clienteCondicionFinanciera;
	
	public PedidoCteDTO(int id, Date fechaGeneracion, float total, String estado, int idCliente, float clienteSaldo,
			String clienteCondicionFinanciera) {
		super();
		this.id = id;
		this.fechaGeneracion = fechaGeneracion;
		this.total = total;
		this.estado = estado;
		this.idCliente = idCliente;
		this.clienteSaldo = clienteSaldo;
		this.clienteCondicionFinanciera = clienteCondicionFinanciera;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public float getClienteSaldo() {
		return clienteSaldo;
	}

	public void setClienteSaldo(float clienteSaldo) {
		this.clienteSaldo = clienteSaldo;
	}

	public String getClienteCondicionFinanciera() {
		return clienteCondicionFinanciera;
	}

	public void setClienteCondicionFinanciera(String clienteCondicionFinanciera) {
		this.clienteCondicionFinanciera = clienteCondicionFinanciera;
	}
	
}
