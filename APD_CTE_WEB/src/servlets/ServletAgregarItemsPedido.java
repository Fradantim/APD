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

import dto.ArticuloDTO;
import dto.PedidoCteDTO;

@WebServlet("/ServletAgregarItemsPedido")
public class ServletAgregarItemsPedido extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletAgregarItemsPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("pedidoAbierto")==null) {
			//TODO Si el atributo "pedidoAbierto" es nulo al BD el unico pedido abierto del cliente
			PedidoCteDTO pedidoAbierto=new PedidoCteDTO(222222, new Date(), 0, "", null);
			request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
			if(pedidoAbierto==null) {
				request.setAttribute("errorMessage", "Ud no tiene un pedido abierto para agregarle items.");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			}
		}
		//TODO pedir los articulos al bussinessDelegate
		System.out.println("IN");
		ArrayList<ArticuloDTO> articulos = new ArrayList<>();
		articulos.add(new ArticuloDTO(1, "00001105", "papita", 300, "bolsa", "gr", 10,1,1));
		articulos.add(new ArticuloDTO(2, "00001106", "coca", 300, "bolsa", "gr", 20,1,1));
		articulos.add(new ArticuloDTO(3, "00001107", "chicle", 300, "bolsa", "gr", 15,1,1));
		articulos.add(new ArticuloDTO(4, "00001108", "salmon", 300, "bolsa", "gr", 117,1,1));
		articulos.add(new ArticuloDTO(5, "00001109", "asd", 300, "bolsa", "gr", 22,1,1));
		articulos.add(new ArticuloDTO(6, "00001110", "2389", 300, "bolsa", "gr", 543,1,1));
		
		request.setAttribute("articulos", articulos);
		request.getRequestDispatcher("/jsp/agregarItemAPedido.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id Recibido: "+request.getParameter("id"));
		System.out.println("cantidad: "+request.getParameter("cantidad"));
		//System.out.println("idCliente: "+request.getSession().getAttribute("idCliente"));
		System.out.println("idPedido: "+((PedidoCteDTO)request.getSession().getAttribute("pedidoAbierto")).getId());		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			response.getWriter().print("{\"errorMessage\": \"Ooooops error no controlado\"}");;
			response.setStatus(400);
		}
	}
}
