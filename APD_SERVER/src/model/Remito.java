package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ItemPedidoCteDao;
import dao.RemitoDao;

public class Remito {
	private int idRemito;
	private int idPedido;
	private Date fecha;
	
	public Remito() { 	}

	public Remito(int idRemito, Date Fecha, int idPedidoCliente) {
		this.fecha = Fecha;
		this.idPedido = idPedidoCliente;
		this.idRemito = idRemito;
	}
	
	public int getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Remito guardar() {
		return RemitoDao.getInstance().grabar(this);
	}
	
}
