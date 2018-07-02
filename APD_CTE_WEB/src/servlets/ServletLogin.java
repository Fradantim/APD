package servlets;

import java.io.IOException;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.UsuarioDTO;
import exception.ObjetoInexistenteException;
import exception.UsuarioContrasenaIncorrectosException;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletLogin() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: No pudo conectarse al BusinessDelegate!\"}");;
			response.setStatus(400);
			return;
		}
		
		UsuarioDTO user;
		
		try {
			user = bd.login(Integer.parseInt(request.getParameter("user")), request.getParameter("pass"));			
		} catch (NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: El usuario debe ser numerico!\"}");;
			response.setStatus(400);
			return;
		} catch (UsuarioContrasenaIncorrectosException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: Usuario o contraseña incorrectos!\"}");;
			response.setStatus(400);
			return;
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR de comunicacion ! "+e.getMessage()+"\"}");;
			e.printStackTrace();
			response.setStatus(400);
			return;
		}

		request.getSession().setAttribute("usuarioLogueado",user);
		request.getSession().setAttribute("rolUsuarioCliente",UsuarioDTO.ROL_CLIENTE);
		request.getSession().setAttribute("rolUsuarioAdminCliente",UsuarioDTO.ROL_ADMIN_CLIENTE);
		request.getSession().setAttribute("rolUsuarioAdminAlmacen",UsuarioDTO.ROL_ADMIN_ALMACEN);
		request.getSession().setAttribute("rolUsuarioFacturacionDespacho",UsuarioDTO.ROL_FACTURACION_DESPACHO);

		if(user.getNivelRol().equals(UsuarioDTO.ROL_CLIENTE)) {
			ClienteDTO cliente;
			try {
				cliente = bd.getClienteByUsuario(user.getIdUsuario());
				request.getSession().setAttribute("cliente", cliente);
			} catch (ObjetoInexistenteException e) {
				response.getWriter().print("{\"errorMessage\": \"ERROR: El usuario ingresado es de un cliente, pero no posee un cliente asociado!\"}");;
				response.setStatus(400);
				return;
			} catch (CommunicationException e) {
				response.getWriter().print("{\"errorMessage\": \"ERROR de comunicacion ! "+e.getMessage()+"\"}");;
				e.printStackTrace();
				response.setStatus(400);
				return;
			}
			request.getSession().setAttribute("cliente", cliente);
		}
		
		if(user.getNivelRol().equals(UsuarioDTO.ROL_CLIENTE)) {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/frontCliente.jsp"+"\"}");;
		}
		if(user.getNivelRol().equals(UsuarioDTO.ROL_ADMIN_CLIENTE)) {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletAdminCred"+"\"}");;
		}
		if(user.getNivelRol().equals(UsuarioDTO.ROL_ADMIN_ALMACEN)) {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletOrdPendAsigProv"+"\"}");;
		}
		if(user.getNivelRol().equals(UsuarioDTO.ROL_FACTURACION_DESPACHO)) {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletPedidosPendStockDesp"+"\"}");;
		}
		
	}
}
