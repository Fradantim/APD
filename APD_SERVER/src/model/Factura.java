package model;

import java.util.Date;
import java.util.List;

import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import view.FacturaView;

public class Factura extends MovimientoCtaCte {
	
	public static final String STATUS_INPAGA="Factura inpaga";
	public static final String STATUS_PAGA="Factura inpagada";
	
	private int idFactura;
	private int bonificacion;
	private String estado;
	
	public Factura() {	}
	
	public Factura(Date fecha, int bonificacion, PedidoCte pedido) {
		estado=STATUS_INPAGA;
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
		return null;
	}
	
	public void agregarItem(String codigoDeBarras, int cantidad) {
		
	}
	
	public void Pagar(Pago pago) throws LaFacturaYaTienePagosDeOtraEspecieException{
		
	}
	
	@Override
	public void guardar() {
		
	}
	
	public FacturaView toView() {
		return null;
	}
}
