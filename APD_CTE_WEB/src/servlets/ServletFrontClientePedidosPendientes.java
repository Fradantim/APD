package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

@WebServlet("/ServletFrontClientePedidosPendientes")
public class ServletFrontClientePedidosPendientes extends HttpServlet {

	private static final long serialVersionUID = 2323165581194757625L;

	public ServletFrontClientePedidosPendientes() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("pedidosPendientes")!=null)
			request.getSession().removeAttribute("pedidosPendientes");

		List<PedidoCteDTO> pedidosPendientes = null;
		try {
			pedidosPendientes = BusinessDelegate.GetInstancia().getPedidosPendientesByCliente(((ClienteDTO)request.getSession().getAttribute("cliente")).getId());
		} catch (CommunicationException e1) {
			request.getSession().setAttribute("errorMessage", "Error de comunicacion al recuperar facturas inpagas.");
			response.setStatus(400);
			e1.printStackTrace();
			return;
		} catch (ObjetoInexistenteException e1) {
			request.getSession().setAttribute("errorMessage", "Error interno al recuperar facturas inpagas.");
			response.setStatus(400);
			e1.printStackTrace();
			return;
		}
		request.getSession().setAttribute("pedidosPendientes", pedidosPendientes);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
