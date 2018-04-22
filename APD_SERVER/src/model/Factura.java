package model;

import java.util.Date;
import java.util.List;

import dao.FacturaDao;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.PedidoCteInexistenteException;
import view.FacturaView;

public class Factura extends MovimientoCtaCte {
	
	public static final String STATUS_INPAGA="Factura inpaga";
	public static final String STATUS_PAGA="Factura inpagada";
	
	private int idFactura;
	private int bonificacion;
	private String estado;
	
	public Factura() {	}
	
	public Factura(Date fecha, int bonificacion, CtaCte cuentaCliente) {
		this.fecha=fecha;
		this.bonificacion=bonificacion;
		this.cuentaCliente= cuentaCliente;
	}
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
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
	
	public List<ItemFactura> getItems(){
		//TODO evaluar necesidad
		return null;
	}
	
	public void Pagar(Pago pago) throws LaFacturaYaTienePagosDeOtraEspecieException{
		//TODO hacer metodo 
	}
	
	@Override
	public Factura guardar() {
		return FacturaDao.getInstance().grabar(this);
	}
	
	public FacturaView toView() {
		return null;
	}
	
	public void ingresarItems(List <ItemPedidoCte> itemsPedido) {
		for(ItemPedidoCte item: itemsPedido) {
			ItemFactura itemFactura = new ItemFactura(item, this);
			itemFactura.guardar();
		}
	}
}
