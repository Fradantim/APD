package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;

@WebServlet("/ServletAgregarItemsPedido")
public class ServletAgregarItemsPedido extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletAgregarItemsPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("idCliente", new Random().nextInt(1000000-1)+1);
		//TODO Pedir al BD el unico pedido abierto del cliente
		request.getSession().setAttribute("idPedido", new Random().nextInt(1000000-1)+1);
		
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
