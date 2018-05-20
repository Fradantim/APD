package test;

import java.text.ParseException;
import java.util.ArrayList;

import dao.ClienteDao;
import dao.ItemPedidoCteDao;
import dao.UsuarioDao;
import exception.ObjetoInexistenteException;
import model.Articulo;
import model.Cliente;
import model.DomicilioDeFacturacion;
import model.ItemPedidoCte;
import model.PedidoCte;
import model.Usuario;

public class TesterUsuario {
	public static void main(String[] args) throws ObjetoInexistenteException{
		System.out.println("Carga Usuarios");
		System.out.println("---------------");
		for (Usuario usr : cargarUsuarios()) {
			usr.guardar();
			System.out.println("Usuario guardado: "+usr.getIdUsuario());
		}
   
		System.out.println("Recuperar Usuario");
		System.out.println("---------------");
		Usuario usr = UsuarioDao.getInstance().getById(1);
		System.out.println("Usuario recuperado: " + usr.getIdUsuario());
	}
	
	public static ArrayList<Usuario> cargarUsuarios(){
		ArrayList<Usuario> usuariosNuevos = new ArrayList<>();
				
		usuariosNuevos.add(new Usuario(0, "Javier", "Rabone", "Admin", "Abc123"));
		usuariosNuevos.add(new Usuario(0, "Pepe", "Argento", "Compras", "Abc123"));
		usuariosNuevos.add(new Usuario(0, "Frank", "Fabbra", "Deposito", "Abc123"));
		
		return usuariosNuevos;
	}
}
