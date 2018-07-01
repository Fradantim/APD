package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.FacturaDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletFrontClienteFacturasInpagas")
public class ServletFrontClienteFacturasInpagas extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletFrontClienteFacturasInpagas() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasInpagas")!=null)
			request.getSession().removeAttribute("facturasInpagas");
		List<FacturaDTO> facturasInpagas= null;
		try {
			facturasInpagas = BusinessDelegate.GetInstancia().getFacturasInpagas(((ClienteDTO)request.getSession().getAttribute("cliente")).getId());
		} catch (CommunicationException e1) {
			request.getSession().setAttribute("errorMessage", "Error de comunicacion al recuperar facturas inpagas.");
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e1) {
			request.getSession().setAttribute("errorMessage", "Error de interno al recuperar facturas inpagas.");
			response.setStatus(400);
			return;
		}
		request.getSession().setAttribute("facturasInpagas", facturasInpagas);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
