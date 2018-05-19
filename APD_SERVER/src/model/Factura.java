package model;

import java.util.Date;
import java.util.List;

import dao.AcreditacionesDao;
import dao.FacturaDao;
import dao.ItemFacturaDao;
import dao.PagoDao;
import dto.FacturaDTO;
import exception.ObjetoInexistenteException;

public class Factura extends MovimientoCtaCte {
	
	public static final String STATUS_INPAGA="Factura inpaga";
	public static final String STATUS_PAGA="Factura inpagada";
	
	private int bonificacion;
	private String estado;
	
	public Factura() {	}
	
	public Factura(Date fecha, int bonificacion) {
		this.fecha=fecha;
		this.bonificacion=bonificacion;
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
	public Integer guardar() {
		this.idMovimientoCtaCte= FacturaDao.getInstance().grabar(this);
		return this.idMovimientoCtaCte;
	}
	
	public FacturaDTO toDTO() {
		return new FacturaDTO(idMovimientoCtaCte, getFecha(), bonificacion, estado,getImporte());
	}
	
	public void ingresarItems(List <ItemPedidoCte> itemsPedido) throws ObjetoInexistenteException {
		for(ItemPedidoCte item: itemsPedido) {
			ItemFactura itemFactura = new ItemFactura(item, this);
			itemFactura.guardar();
		}
	}
	
	public List <MovimientoCtaCte> getAcreditaciones() {
		return AcreditacionesDao.getInstance().getByIdFactura(idMovimientoCtaCte);
	}
	
	public float getTotal() throws ObjetoInexistenteException {
		//TODO 0Mejorar con SUM de HQL
		List<ItemFactura> items = ItemFacturaDao.getInstance().getByIdFactura(idMovimientoCtaCte);
		float total=0;
		for(ItemFactura item: items) {
			total+=item.getCantidad()*item.getArticulo().getPrecioDeVenta();
		}
		return total;
	}
	
	public float getPendienteDeAbonar() throws ObjetoInexistenteException {
		return getTotal()-getTotalAbonado();
	}
	
	/**
	 * Devuelve suma de Pagos y NCs asociados a la factura
	 * @return
	 */
	public float getTotalAbonado() {
		//TODO 0Mejorar con SUM de HQL llamar a PagoDao y NotaCreditoDao
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
	
	@Override
	public String getDetalle() {
		return "Factura nro "+idMovimientoCtaCte+" "+bonificacion+"% bonificada, generada "+getFormatedDate();
	}
}
