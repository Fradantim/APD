package entities;

import javax.persistence.Embeddable;

import model.Cliente;
import model.DomicilioDeFacturacion;

@Embeddable
public class DomicilioDeFacturacionEntity {
	private String pais;
	private String provincia;
	private String partido;
	private String codigoPostal;
	private String calle;
	private String altura;
	private String piso;
	private int numero;
	
	public DomicilioDeFacturacionEntity() {	}
	
	public DomicilioDeFacturacionEntity(String pais, String provincia, String partido, String codigoPostal, String calle, String altura, String piso, int numero) {
		this.pais = pais;
		this.provincia = provincia;
		this.partido = partido;
		this.codigoPostal = codigoPostal;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.numero = numero;
	}
	
	public DomicilioDeFacturacion toNegocio(){
		return new DomicilioDeFacturacion(pais, provincia, partido, codigoPostal, calle, altura, piso, numero);
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
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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
