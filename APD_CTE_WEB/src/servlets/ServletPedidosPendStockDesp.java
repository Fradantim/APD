package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.DomicilioDeFacturacionDTO;
import dto.PedidoCteDTO;
import dto.UsuarioDTO;

@WebServlet("/ServletPedidosPendStockDesp")
public class ServletPedidosPendStockDesp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878272431095331951L;

	public ServletPedidosPendStockDesp() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("idCliente", new Random().nextInt(1000000-1)+1);
		
		//TODO pedir los Pedidos al bussinessDelegate
		UsuarioDTO usu = new UsuarioDTO(1, "Javier", "Rabone", "Usuario", "Admin123$");
		ClienteDTO cli = new ClienteDTO(1, "Accenture", 20000, "34963780", 500, 42410822, "Aceptable", new DomicilioDeFacturacionDTO(1, "Argentina", "Buenos Aires", "Lanus", "1824", "Juan B. Justo", "2632", "4", 1), usu);

		System.out.println("IN");
		ArrayList<PedidoCteDTO> pedidos = new ArrayList<>();
		pedidos.add(new PedidoCteDTO(1, new Date(), 100, "Falta Stock", cli));
		pedidos.add(new PedidoCteDTO(2, new Date(), 120, "Falta Stock", cli));
		pedidos.add(new PedidoCteDTO(3, new Date(), 130, "Falta Stock", cli));
		pedidos.add(new PedidoCteDTO(4, new Date(), 200, "Falta Stock", cli));
		pedidos.add(new PedidoCteDTO(5, new Date(), 250, "Falta Stock", cli));
		
		request.setAttribute("pedidos", pedidos);
		
		
		request.getRequestDispatcher("/jsp/PedidosPendStockDesp.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id Recibido: "+request.getParameter("id"));
		System.out.println("cantidad: "+request.getParameter("cantidad"));
		System.out.println("idCliente: "+request.getSession().getAttribute("idCliente"));
		System.out.println("idPedido: "+request.getSession().getAttribute("idPedido"));		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		
		/*request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
		response.setStatus(400);*/
	}

}
