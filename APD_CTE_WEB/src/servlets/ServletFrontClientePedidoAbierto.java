package servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.PedidoCteDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletFrontClientePedidoAbierto")
public class ServletFrontClientePedidoAbierto extends HttpServlet {

	private static final long serialVersionUID = 6855766612753227180L;

	public ServletFrontClientePedidoAbierto() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("pedidoAbierto")!=null)
			request.getSession().removeAttribute("pedidoAbierto");

		PedidoCteDTO pedidoAbierto=null;
		try {
			pedidoAbierto = BusinessDelegate.GetInstancia().getPedidoAbiertoByCliente(((ClienteDTO)request.getSession().getAttribute("cliente")).getId());
		} catch (CommunicationException e1) {
			request.getSession().setAttribute("errorMessage", "Error de comunicacion al recuperar facturas inpagas.");
			response.setStatus(400);
			e1.printStackTrace();
			return;
		} catch (ObjetoInexistenteException e1) {
			//esto no esta mal
			//request.getSession().setAttribute("errorMessage", "Error interno al recuperar facturas inpagas.");
			//response.setStatus(400);
			//e1.printStackTrace();
			return;
		}
		request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
