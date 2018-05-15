package entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import model.Cliente;
import model.DomicilioDeFacturacion;

@Entity
@Table(name="CLIENTES")
public class ClienteEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToOne(mappedBy = "Cli")
	private Integer id;
	@Column (name="razon_social")
	private String razonSocial;
	@Column (name="limite_credito", nullable=true)
	private float limiteCredito;
	@Column (name="condicion_financiera", nullable=true)
	private String condicionFinanciera;
	@Column (name="documento", nullable=true)
	private String documento;
	@Column (name="saldo", nullable=true)
	private float saldo;
	@Column (name="telefono", nullable=true)
	private int telefono;
	@Embedded 
	private DomicilioDeFacturacionEntity domicilioDeFacturacion;
	
	public ClienteEntity() {	}
	
	public ClienteEntity(Cliente cli) {
		super();
		this.condicionFinanciera = cli.getCondicionFinanciera();
		this.documento = cli.getDocumento();
		this.limiteCredito = cli.getLimiteCredito();
		this.razonSocial = cli.getRazonSocial();
		//TODO obtener saldo
		this.saldo = 0;
		this.telefono = cli.getTelefono();
		
		DomicilioDeFacturacionEntity domicilioEntity = new DomicilioDeFacturacionEntity(
				cli.getDomicilio().getPais(), 
				cli.getDomicilio().getProvincia(), 
				cli.getDomicilio().getPartido(), 
				cli.getDomicilio().getCodigoPostal(),
				cli.getDomicilio().getCalle(),
				cli.getDomicilio().getAltura(),
				cli.getDomicilio().getPiso(),
				cli.getDomicilio().getNumero()
				);
		this.domicilioDeFacturacion = domicilioEntity;
	}
	
	public ClienteEntity(String condicionFinanciera, String documento, float limiteCredito, String razonSocial, float saldo,
			int telefono, DomicilioDeFacturacion domicilioFacturacion) {
		super();
		this.condicionFinanciera = condicionFinanciera;
		this.documento = documento;
		this.limiteCredito = limiteCredito;
		this.razonSocial = razonSocial;
		this.saldo = saldo;
		this.telefono = telefono;
		
		DomicilioDeFacturacionEntity domicilioEntity = new DomicilioDeFacturacionEntity(
				domicilioFacturacion.getPais(), 
				domicilioFacturacion.getProvincia(), 
				domicilioFacturacion.getPartido(), 
				domicilioFacturacion.getCodigoPostal(),
				domicilioFacturacion.getCalle(),
				domicilioFacturacion.getAltura(),
				domicilioFacturacion.getPiso(),
				domicilioFacturacion.getNumero()
				);
		
		this.domicilioDeFacturacion = domicilioEntity;
	}
	
	public Cliente toNegocio(){
		return new Cliente(id, razonSocial, limiteCredito, documento, domicilioDeFacturacion.toNegocio(), telefono, condicionFinanciera);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id!=0)
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

	public String getCondicionFinanciera() {
		return condicionFinanciera;
	}

	public void setCondicionFinanciera(String condicionFinanciera) {
		this.condicionFinanciera = condicionFinanciera;
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

	public DomicilioDeFacturacionEntity getDomicilioDeFacturacion() {
		return domicilioDeFacturacion;
	}

	public void setDomicilioDeFacturacion(DomicilioDeFacturacionEntity domicilioDeFacturacion) {
		this.domicilioDeFacturacion = domicilioDeFacturacion;
	}
}


