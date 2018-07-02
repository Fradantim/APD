package controller;

import java.rmi.RemoteException;

import dao.UsuarioDao;
import dto.UsuarioDTO;
import exception.ObjetoInexistenteException;
import exception.UsuarioContrasenaIncorrectosException;

public class SistemaDeControlDeUsuarios {
	private static SistemaDeControlDeUsuarios sistCtrlUsuarios;
	
	public static SistemaDeControlDeUsuarios getInstance() {
		if(sistCtrlUsuarios==null) {
			sistCtrlUsuarios= new SistemaDeControlDeUsuarios();
		}
		return sistCtrlUsuarios;
	}
	
	public UsuarioDTO login(int idUsuario, String contrasena) throws RemoteException, UsuarioContrasenaIncorrectosException {
		try {
			return UsuarioDao.getInstance().getByIdAndPass(idUsuario, contrasena).toDto();
		} catch (ObjetoInexistenteException e) {
			throw new UsuarioContrasenaIncorrectosException(e.getMessage());
		}
	}
	//TODO hacer clase
}
