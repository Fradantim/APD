package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.Articulo;

@Entity
@Table(name="ARTICULOS")
public class ArticuloEntity  {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_articulo")
	@OneToOne  
	@JoinColumn(name="id_articulo")
	private Integer id;
	@Column (name="codigo_de_barras", unique=true)
	private String codDeBarras;
	@Column (name="descripcion", nullable=true)
	private String descripcion;
	@Column (name="tamano", nullable=true)
	private float tamano;
	@Column (name="presentacion", nullable=true)
	private String presentacion;
	@Column (name="unidad", nullable=true)
	private String unidad;
	@Column (name="precio_de_venta", nullable=true)
	private float precioDeVenta;
	@Column (name="cantidad_a_comprar", nullable=true)
	private int cantidadAComprar;
	@Column (name="cantidad_ubicable", nullable=true)
	private int cantidadUbicable;
	
	@OneToMany
	@JoinColumn(name="ArticuloId")
	private List<MovimientoInventarioEntity> movimientos;
	
	@OneToMany
	@JoinColumn(name="ArticuloId")	
	private List<ItemFacturaEntity> itemFactura;
	
	@OneToMany
	@JoinColumn(name="ArticuloId")
	private List<UbicacionEntity> ubicaciones;
	
	public ArticuloEntity() {	}

	public ArticuloEntity(String codDeBarras, String descripcion, float tamano, String presentacion, String unidad,
			float precioDeVenta, int cantidadAComprar, int cantidadUbicable) {
		super();
		this.codDeBarras = codDeBarras;
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.presentacion = presentacion;
		this.unidad = unidad;
		this.precioDeVenta = precioDeVenta;
		this.cantidadAComprar = cantidadAComprar;
		this.cantidadUbicable = cantidadUbicable;
	}
	
	public ArticuloEntity(Articulo art) {
		super();
		this.id= art.getId()==0 ? null : art.getId();
		this.codDeBarras = art.getCodDeBarras();
		this.descripcion = art.getDescripcion();
		this.tamano = art.getTamano();
		this.presentacion = art.getPresentacion();
		this.unidad = art.getUnidad();
		this.precioDeVenta = art.getPrecioDeVenta();
		this.cantidadAComprar = art.getCantidadAComprar();
		this.cantidadUbicable = art.getCantidadUbicable();
	}
	

	public Articulo toNegocio(){
		return new Articulo(id, codDeBarras, descripcion, tamano, presentacion, unidad, precioDeVenta, cantidadAComprar, cantidadUbicable);
	}
	
	public String getCodDeBarras() {
		return codDeBarras;
	}
	public void setCodDeBarras(String codDeBarras) {
		this.codDeBarras = codDeBarras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getTamano() {
		return tamano;
	}
	public void setTamano(float tamano) {
		this.tamano = tamano;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public float getPrecioDeVenta() {
		return precioDeVenta;
	}
	public void setPrecioDeVenta(float precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	public int getCantidadUbicable() {
		return cantidadUbicable;
	}
	public void setCantidadUbicable(int cantidadUbicable) {
		this.cantidadUbicable = cantidadUbicable;
	}

	public int getCantidadAComprar() {
		return cantidadAComprar;
	}

	public void setCantidadAComprar(int cantidadAComprar) {
		this.cantidadAComprar = cantidadAComprar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id!=0)
			this.id = id;
	}
	
	/*public List<MovimientoInventarioEntity> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoInventarioEntity> movimientos) {
		this.movimientos = movimientos;
	}
	public void setMovimientos(List<MovimientoInventarioEntity> movimientos) {
		this.movimientos = movimientos;
	}*/
	
}
