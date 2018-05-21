package model;

import java.util.Date;
import dao.RemitoDao;

public class Remito {
	
	private int idRemito;
	private Date fecha;
	private PedidoCte pedido;
	
	public Remito() { 	}
		
	public Remito(Date fecha, PedidoCte pedido){
		this.fecha = fecha;
		this.pedido = pedido;
	}

	public PedidoCte getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCte pedido) {
		this.pedido = pedido;
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
	
	public Integer guardar() {
		this.idRemito=RemitoDao.getInstance().grabar(this);
		return this.idRemito;
	}
}
