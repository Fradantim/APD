package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Remito {
	//TODO Consultar, tengo que persistirlo?
	
	private int idRemito;
	private Date fecha;
	private List<ItemRemito> items;
	
	public Remito() { 	}
		
	public Remito(Date fecha, List<ItemPedidoCte> itemsP) {
		this.fecha = fecha;
		items = new ArrayList<>();
		for(ItemPedidoCte itemP : itemsP) {
			this.items.add(new ItemRemito(itemP));
		}
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
	public List<ItemRemito> getItems() {
		return items;
	}
	public void setItems(List<ItemRemito> items) {
		this.items = items;
	}
	
}
