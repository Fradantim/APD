package model;

import dto.TipoDocumentoDTO;

public class TipoDocumento {
	private int idTipoDocumento;
	private String sigla;

	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	public TipoDocumentoDTO toDTO() {
		return new TipoDocumentoDTO(idTipoDocumento, sigla);
	}
	
}
