package model;

import java.util.Date;
import java.util.List;

import dao.FacturaDao;
import dao.ItemFacturaDao;
import dao.NotaCreditoDao;
import dto.FacturaDTO;
import exception.ObjetoInexistenteException;

public class Factura extends MovimientoCtaCte {
	
	public static final String STATUS_INPAGA="Factura inpaga";
	public static final String STATUS_PAGA="Factura pagada";
	
	private int bonificacion;
	private String estado;
	
	private List<Pago> pagosAsociados;
	
	public List<Pago> getPagosAsociados() {
		if(pagosAsociados==null)
			pagosAsociados=  FacturaDao.getInstance().getPagosByIdFactura(idMovimientoCtaCte);
		return pagosAsociados;
	}

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
		this.importe=ItemFacturaDao.getInstance().getSumImporteByIdFactura(this.idMovimientoCtaCte);
		guardar();
	}
	
	public float getPendienteDeAbonar() {
		return importe+getTotalAbonado()+getTotalBonificado();
	}
	
	public Integer asociarPago(Pago pago) {
		getPagosAsociados().add(pago);
		guardar();
		pago.guardar();
		if(getPendienteDeAbonar()==0) {
			setEstado(Factura.STATUS_PAGA);
			guardar();
		}
		return pago.getIdMovimientoCtaCte();
	}

	public float getTotalAbonado() {
		float importePagado=0;
		for(Pago pago: getPagosAsociados()) {
			importePagado+=pago.getImporte();
		}
		return importePagado;
	}
	
	public float getTotalBonificado() {
		NotaCredito nc = NotaCreditoDao.getInstance().getNotaDeCreditoByIdFactura(idMovimientoCtaCte);
		if(nc==null) {
			return 0;
		}
		return nc.getImporte();
	}
}
