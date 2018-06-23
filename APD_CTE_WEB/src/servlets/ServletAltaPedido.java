package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.PedidoCteDTO;

@WebServlet("/ServletAltaPedido")
public class ServletAltaPedido extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletAltaPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Evaluar si el cliente puede o no hacer un nuevo pedido
		
		PedidoCteDTO pedidoAbierto=null;
		if(pedidoAbierto!=null) {
			request.setAttribute("errorMessage", "Ud ya tiene el pedido "+pedidoAbierto.getId() + " en estado "+ pedidoAbierto.getEstado()+".");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/jsp/altaPedido.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pais =request.getParameter("pais");
		System.out.println("Atributos recuperados del Back End");
		System.out.println("Pais "+ pais);
		System.out.println("Provincia " +request.getParameter("provincia"));
		System.out.println("Partido " +request.getParameter("partido"));
		System.out.println("Codigo Postal " +request.getParameter("codigoPostal"));
		System.out.println("Calle " +request.getParameter("calle"));
		System.out.println("Altura " +request.getParameter("altura"));
		System.out.println("Piso " +request.getParameter("piso"));
		System.out.println("Numero " +request.getParameter("numero"));
		
		//TODO dar alta de pedido, recuperar el idPedido 
		PedidoCteDTO pedidoAbierto=new PedidoCteDTO(111111, new Date(), 0, "", null); 
		request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
		
		if("1".equals( pais.trim() )) {
			response.getWriter().print("{\"errorMessage\": \"Epa Epa!!!\"}");;
			response.setStatus(400);
		} else {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletAgregarItemsPedido"+"\"}");
		}
	}

}
