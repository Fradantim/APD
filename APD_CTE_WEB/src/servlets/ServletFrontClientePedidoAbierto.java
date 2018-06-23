package servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PedidoCteDTO;

@WebServlet("/ServletFrontClientePedidoAbierto")
public class ServletFrontClientePedidoAbierto extends HttpServlet {

	private static final long serialVersionUID = 6855766612753227180L;

	public ServletFrontClientePedidoAbierto() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("pedidoAbierto")!=null)
			request.getSession().removeAttribute("pedidoAbierto");
		//TODO pedirle el pedido abierto al bd
		PedidoCteDTO pedidoAbierto = new PedidoCteDTO(24, null, 169, "Nuevito", null);

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
