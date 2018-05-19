package model;

import java.util.Date;
import java.util.List;

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
	
	public float getPendienteDeAbonar() throws ObjetoInexistenteException {
		return importe-getTotalAbonado();
	}
	
	public Integer asociarPago(Pago pago) {
		getPagosAsociados().add(pago);
		guardar();
		System.out.println(">>> asocie pago "+ pago.getIdMovimientoCtaCte());
		return pago.guardar();
	}

	public float getTotalAbonado() {
		float importePagado=0;
		for(Pago pago: getPagosAsociados()) {
			importePagado+=pago.getImporte();
		}
		return importePagado;
	}
}
