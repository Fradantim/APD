package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import exception.ObjetoInexistenteException;
import model.MovimientoInventario;

@Entity
@Table(name="MovInventario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="Tipo",discriminatorType=DiscriminatorType.STRING)
public abstract class MovimientoInventarioEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column (name="idMovInventario")
	protected Integer idMovimiento;
	@Column (name="Cantidad", nullable=true)
	protected int cantidad;
	@Column (name="Articulo_CodigoDeBarras", nullable=true)
	protected String articuloCodDeBarra;
	
	public MovimientoInventarioEntity() { }
	
	public MovimientoInventarioEntity(int cantidad, String articuloCodDeBarra) {
		super();
		this.cantidad = cantidad;
		this.articuloCodDeBarra = articuloCodDeBarra;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getArticuloCodDeBarra() {
		return articuloCodDeBarra;
	}

	public void setArticuloCodDeBarra(String articuloCodDeBarra) {
		this.articuloCodDeBarra = articuloCodDeBarra;
	}
	
	public MovimientoInventario toNegocio() throws ObjetoInexistenteException {
		return null;
	}
	
}
