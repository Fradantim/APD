package model;

import java.util.Date;
import java.util.List;

import dao.AcreditacionesDao;
import dao.FacturaDao;
import dao.ItemFacturaDao;
import dao.PagoDao;
import dto.FacturaDTO;

public class Factura extends MovimientoCtaCte {
	
	public static final String STATUS_INPAGA="Factura inpaga";
	public static final String STATUS_PAGA="Factura inpagada";
	
	private int bonificacion;
	private String estado;
	
	public Factura() {	}
	
	public Factura(Date fecha, int bonificacion, CtaCte cuentaCliente) {
		this.fecha=fecha;
		this.bonificacion=bonificacion;
		this.cuentaCliente= cuentaCliente;
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
	
	@Override
	public Factura guardar() {
		return FacturaDao.getInstance().grabar(this);
	}
	
	public FacturaDTO toDTO() {
		return new FacturaDTO(idMovimientoCtaCte, getFecha(), bonificacion, estado,getImporte());
	}
	
	public void ingresarItems(List <ItemPedidoCte> itemsPedido) {
		for(ItemPedidoCte item: itemsPedido) {
			ItemFactura itemFactura = new ItemFactura(item, this);
			itemFactura.guardar();
		}
	}
	
	public List <MovimientoCtaCte> getAcreditaciones() {
		return AcreditacionesDao.getInstance().getByIdFactura(idMovimientoCtaCte);
	}
	
	public float getTotal() {
		List<ItemFactura> items = ItemFacturaDao.getInstance().getByIdFactura(idMovimientoCtaCte);
		float total=0;
		for(ItemFactura item: items) {
			total+=item.getCantidad()*item.getArticulo().getPrecioDeVenta();
		}
		return total;
	}
	
	public float getPendienteDeAbonar() {
		return getTotal()-getTotalAbonado();
	}
	
	/**
	 * Devuelve suma de Pagos y NCs asociados a la factura
	 * @return
	 */
	public float getTotalAbonado() {
		List <MovimientoCtaCte> creditos = AcreditacionesDao.getInstance().getByIdFactura(idMovimientoCtaCte);
		float total=0;
		for(MovimientoCtaCte credito: creditos) {
			total+=credito.getImporte();
		}
		return total;
	}
	
	public List <Pago> getPagos() {
		return PagoDao.getInstance().getByIdFactura(idMovimientoCtaCte);
	}
}
