package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PedidoCteDTO;

@WebServlet("/ServletFrontClientePedidosPendientes")
public class ServletFrontClientePedidosPendientes extends HttpServlet {

	private static final long serialVersionUID = 2323165581194757625L;

	public ServletFrontClientePedidosPendientes() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("pedidosPendientes")!=null)
			request.getSession().removeAttribute("pedidosPendientes");
		
		//TODO pedirle pedidos pendientes al bd
		ArrayList<PedidoCteDTO> pedidosPendientes = new ArrayList<>();
		for(int i=0; i< 7 ; i++) {
			pedidosPendientes.add(new PedidoCteDTO((i+1)*121, new Date(), 296*(i+1), "estaaaado", null,null));
		}
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		request.getSession().setAttribute("pedidosPendientes", pedidosPendientes);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
