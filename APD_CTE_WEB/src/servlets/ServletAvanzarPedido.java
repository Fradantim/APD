package servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PedidoCteDTO;

@WebServlet("/ServletAvanzarPedido")
public class ServletAvanzarPedido extends HttpServlet {

	private static final long serialVersionUID = -3135089762499750508L;

	public ServletAvanzarPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PedidoCteDTO pedidoAbierto = (PedidoCteDTO) request.getSession().getAttribute("pedidoAbierto");
		
		//TODO llamar al BD y darle el idPedido para avanzar
		System.out.println("id Recibido: "+pedidoAbierto.getId());
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		
		if("1".equals("")) {
			response.getWriter().print("{\"errorMessage\": \"Epa Epa!!!\"}");;
			response.setStatus(400);
		} else {
			request.getSession().removeAttribute("pedidoAbierto");
			//TODO evaluar si llevar a un frontend de cliente
			response.getWriter().print("{\"message\": \"Pedido avanzado correctamente.\" , \"forwardTo\": \""+request.getContextPath()+"/jsp/bannerSuperior.jsp"+"\"}");
		}
	}

}
