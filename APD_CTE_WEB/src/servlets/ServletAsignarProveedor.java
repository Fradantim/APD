package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoCteDTO;
import dto.ProveedorDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletAsignarProveedor")
public class ServletAsignarProveedor extends HttpServlet {
	
	
	private static final long serialVersionUID = 2405811717259763848L;

	public ServletAsignarProveedor(){}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"No se pudo recuperar la información del pedido. "+e.getMessage()+"\"}");;
			response.setStatus(400);
			return;
		}
		try {
			bd.asignarProveedor(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("proveedor")));	
			//response.getWriter().print("{\"fowardTo\": \""+"/ServletOrdPendAsigProv\"}");;
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/ServletOrdPendAsigProv"+"\"}");
		} catch (NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"Error al recuperar la cantidad. "+e.getMessage()+"\"}");;
			response.setStatus(400);
			return;
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"Error de comunicacion. "+e.getMessage()+"\"}");;
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: No se pudo encontrar los objetos referenciados. "+e.getMessage()+"\"}");;
			response.setStatus(400);
			return; 
		}
	}

}
