package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.DomicilioDeFacturacionDTO;
import dto.PedidoCteDTO;
import dto.UsuarioDTO;

@WebServlet("/ServletProcesarPedidosPendStockDesp")
public class ServletProcesarPedidosPendStockDesp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -339935811852484508L;

	private BusinessDelegate bd;
	
	public ServletProcesarPedidosPendStockDesp() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//No hace nada
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id Recibido: "+request.getParameter("id"));
		try {
			//bd = new BusinessDelegate();
			//bd.aceptarPedidoDesp(Integer.parseInt(request.getParameter("id")));		
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletPedidosPendStockDesp"+"\"}");
			response.setStatus(200);	
		} catch (Exception e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		
	}

}
