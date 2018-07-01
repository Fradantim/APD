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
import dto.PedidoCteDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletAvanzarPedido")
public class ServletAvanzarPedido extends HttpServlet {

	private static final long serialVersionUID = -3135089762499750508L;

	public ServletAvanzarPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PedidoCteDTO pedidoAbierto = (PedidoCteDTO) request.getSession().getAttribute("pedidoAbierto");
		
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			request.getSession().setAttribute("errorMessage", "Ooooops no pudo conectarse al cliente.");
			response.setStatus(400);
			return;
		}
		
		try {
			bd.cerrarPedido(pedidoAbierto.getId());
		} catch (CommunicationException e1) {
			request.getSession().setAttribute("errorMessage", "Error de comunicacion.");
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e1) {
			request.getSession().setAttribute("errorMessage", "Error interno no controlado.");
			response.setStatus(400);
			return;
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		request.getSession().removeAttribute("pedidoAbierto");
		response.getWriter().print("{\"message\": \"Pedido avanzado correctamente.\" , \"forwardTo\": \""+request.getContextPath()+"/jsp/frontCliente.jsp"+"\"}");
	}

}
