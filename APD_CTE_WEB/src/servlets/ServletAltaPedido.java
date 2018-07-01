package servlets;

import java.io.IOException;
import java.util.Date;

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

@WebServlet("/ServletAltaPedido")
public class ServletAltaPedido extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletAltaPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			request.setAttribute("errorMessage", "ERROR de comunicacion.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		
		PedidoCteDTO pedidoAbierto=null;
		try {
			pedidoAbierto = bd.getPedidoAbiertoByCliente(((ClienteDTO)request.getSession().getAttribute("cliente")).getId());
		} catch (ObjetoInexistenteException e) {
			//e.printStackTrace();
		} catch (CommunicationException e) {
			request.setAttribute("errorMessage", "Error de comunicacion. "+e.getMessage());
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return; 
		}		
		
		if(pedidoAbierto!=null) {
			request.setAttribute("errorMessage", "Ud ya tiene el pedido "+pedidoAbierto.getId() + " en estado "+ pedidoAbierto.getEstado()+".");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/jsp/altaPedido.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			request.setAttribute("errorMessage", "Error de comunicacion.");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		
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
		
		try {
			bd.generarNuevoPedido(((ClienteDTO)request.getSession().getAttribute("cliente")).getId(), 
					request.getParameter("pais"), request.getParameter("provincia"), request.getParameter("partido"), 
					request.getParameter("codigoPostal"), request.getParameter("calle"), request.getParameter("altura"),
					request.getParameter("piso"), Integer.parseInt(request.getParameter("numero")));
		} catch (NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"El numero debe ser numerico, pah favah!\"}");;
			response.setStatus(400);
			return;
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"Error de Comunicacion.\"}");;
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e) {
			response.getWriter().print("{\"errorMessage\": \"Error no controlado.\"}");;
			response.setStatus(400);
			return;
		}

		PedidoCteDTO pedidoAbierto=null;
		try {
			pedidoAbierto = bd.getPedidoAbiertoByCliente(((ClienteDTO)request.getSession().getAttribute("cliente")).getId());
		} catch (ObjetoInexistenteException e) {
			response.getWriter().print("{\"errorMessage\": \"Error no controlado,2.\"}");;
			response.setStatus(400);
			return;
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"Error de comunicacion.\"}");;
			response.setStatus(400);
			return;
		}	 
		request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
		
		response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletAgregarItemsPedido"+"\"}");
	}
}
