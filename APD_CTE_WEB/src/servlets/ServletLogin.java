package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ClienteDTO;
import dto.UsuarioDTO;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletLogin() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO autenticar
		String username=request.getParameter("user");
		//TODO Si el login no existe tengo que devolver codigo 2XX		
		
		//TODO recuperar bien el idUsuario ingresado
		UsuarioDTO user=new UsuarioDTO(1, "username", "apellido", UsuarioDTO.ROL_CLIENTE, "contrasena");
		switch (username) {
	        case "Cliente":{
	        	user.setNivelRol(UsuarioDTO.ROL_CLIENTE);
	        	break;
	        }
	        case "AdminCliente":{
	        	user.setNivelRol(UsuarioDTO.ROL_ADMIN_CLIENTE);
	        	break;
	        }
	        case "AdminAlmacen":{
	        	user.setNivelRol(UsuarioDTO.ROL_ADMIN_ALMACEN);
	        	break;
	        }
	        case"Despa":{
	        	user.setNivelRol(UsuarioDTO.ROL_FACTURACION_DESPACHO);
	        	break;
	        }
	        default:{
	        	user.setNivelRol(UsuarioDTO.ROL_CLIENTE);
	        	break;
	        }
		}
		
		request.getSession().setAttribute("usuarioLogueado",user);
		request.getSession().setAttribute("rolUsuarioCliente",UsuarioDTO.ROL_CLIENTE);
		request.getSession().setAttribute("rolUsuarioAdminCliente",UsuarioDTO.ROL_ADMIN_CLIENTE);
		request.getSession().setAttribute("rolUsuarioAdminAlmacen",UsuarioDTO.ROL_ADMIN_ALMACEN);
		request.getSession().setAttribute("rolUsuarioFacturacionDespacho",UsuarioDTO.ROL_FACTURACION_DESPACHO);

		if(user.getNivelRol().equals(UsuarioDTO.ROL_CLIENTE)) {
			//TODO Traer el clienteDTO del cliente 
			ClienteDTO cliente = new ClienteDTO(101010, "razonSocial", 10000, "documento", 12021, 0, "condicionFinanciera", null, null);
			cliente.setTipoDocumento("DNI");
			request.getSession().setAttribute("cliente", cliente);
		}
		
		//TODO cargar errores aca (segun sucedan)
		if("aaa".equals( username.trim().toLowerCase() )) {
			response.getWriter().print("{\"errorMessage\": \"Epa Epa!!!\"}");;
			response.setStatus(400);
		} else {
			//TODO evaluar un home mas dinamico para el cliente
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/bannerSuperior.jsp"+"\"}");;
		}
	}

}
