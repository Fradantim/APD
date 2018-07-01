package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;


@WebServlet("/ServletAjustarStock")
public class ServletAjustarStock extends HttpServlet{

	private static final long serialVersionUID = -7585561009387612412L;
	public ServletAjustarStock(){} 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/jsp/AjustarStock.jsp").forward(request, response);		
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
			if ((request.getParameter("usuarioEncargado")).isEmpty()){
				bd.ajusteInvAjuste(request.getParameter("codigoBarras"), Integer.parseInt((String)request.getParameter("cantidad")), Integer.parseInt((String)request.getParameter("ubicacion")));
			}else {				 
				bd.ajusteInvRotura(request.getParameter("codigoBarras"), Integer.parseInt((String)request.getParameter("ubicacion")), Integer.parseInt((String)request.getParameter("cantidad")), 
						Integer.parseInt((String)request.getParameter("usuarioEncargado")), Integer.parseInt((String)request.getParameter("usuarioAutorizador")));
			}
		} catch (NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"Error al recuperar la cantidad. \"}");;
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
		} catch (LaUbicacionNoTieneEsteArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LaUbicacionNoTieneSuficientesArticulosParaRemoverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SuperaLaCantidadUbicableEnLaUbicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
