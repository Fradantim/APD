package dto;

import java.io.Serializable;

public class TipoDocumentoDTO implements Serializable{

	private static final long serialVersionUID = -4567082373687895396L;
	
	private int id;
	private String sigla;
	
	public TipoDocumentoDTO(int id, String sigla) {
		super();
		this.id = id;
		this.sigla = sigla;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
