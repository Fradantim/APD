package dto;

import java.io.Serializable;

public class DomicilioDeFacturacionDTO implements Serializable {

	private static final long serialVersionUID = 140886137538147787L;

	private int id;
	private String pais;
	private String provincia;
	private String partido;
	private String codPotal;
	private String calle;
	private String altura;
	private String piso;
	private int numero;
	
	public DomicilioDeFacturacionDTO(int id, String pais, String provincia, String partido, String codPotal,
			String calle, String altura, String piso, int numero) {
		super();
		this.id = id;
		this.pais = pais;
		this.provincia = provincia;
		this.partido = partido;
		this.codPotal = codPotal;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.numero = numero;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public String getCodPotal() {
		return codPotal;
	}
	public void setCodPotal(String codPotal) {
		this.codPotal = codPotal;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
