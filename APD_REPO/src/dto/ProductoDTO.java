package dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable{

	private static final long serialVersionUID = -1078524224595687594L;
	private float precio;
	private Integer idproducto;
	private Integer idarticulo;
	
	
	public ProductoDTO(Integer idprod, Integer idart, float precio) {
		super();
		this.idproducto = idprod;
		this.idarticulo = idart;
		this.precio = precio;
		
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Integer getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}
	public Integer getIdArticulo() {
		return idarticulo;
	}
	public void setIdArticulo(Integer idArticulo) {
		this.idarticulo = idArticulo;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ProductoDTO))return false;
	    ProductoDTO otherMyClass = (ProductoDTO)other;
	    if(otherMyClass.getIdproducto()==idproducto) {
	    	return true;
	    }return false;
	}
	public Integer getIdarticulo() {
		return idarticulo;
	}
	public void setIdarticulo(Integer idarticulo) {
		this.idarticulo = idarticulo;
	}


}
