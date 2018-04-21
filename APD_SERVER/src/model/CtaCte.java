package model;

import java.util.Date;
import java.util.List;

import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import view.FacturaView;

public class CtaCte {
	private int idCtaCte;

	public int getIdCtaCte() {
		return idCtaCte;
	}

	public void setIdCtaCte(int idCtaCte) {
		this.idCtaCte = idCtaCte;
	}
	
	public float getSaldo() {
		return 0;
	}
	
	public int generarFactura(Date fecha, int bonificacion, PedidoCte pedido) {
		return 0;
	}
	
	public void pagarFactura(int nroFactura, float Pago, String especie) throws LaFacturaYaTienePagosDeOtraEspecieException {
		
	}
	
	public List<FacturaView> getFacturasInpagas(){
		return null;
	}
	
	public void guardar() {
		
	}
}
