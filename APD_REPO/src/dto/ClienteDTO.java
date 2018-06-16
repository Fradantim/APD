package dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -6623479648053536332L;

	private int id;
	private String razonSocial;
	private float limiteCredito;
	private String tipoDocumento;
	private String documento;
	private float saldo;
	private int telefono;
	private String condicionFinanciera;
	private DomicilioDeFacturacionDTO domicilio;
	private UsuarioDTO usuario;
	
	public ClienteDTO(int id, String razonSocial, float limiteCredito, String documento,
			float saldo, int telefono, String condicionFinanciera, DomicilioDeFacturacionDTO domicilio, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.limiteCredito = limiteCredito;
		this.documento = documento;
		this.saldo = saldo;
		this.telefono = telefono;
		this.condicionFinanciera = condicionFinanciera;
		this.domicilio = domicilio;
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCondicionFinanciera() {
		return condicionFinanciera;
	}
	public void setCondicionFinanciera(String condicionFinanciera) {
		this.condicionFinanciera = condicionFinanciera;
	}

	public DomicilioDeFacturacionDTO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioDeFacturacionDTO domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ClienteDTO))return false;
	    ClienteDTO otherMyClass = (ClienteDTO)other;
	    if(otherMyClass.getId()==id) {
	    	return true;
	    }return false;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
