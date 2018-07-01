package servlets;

import java.io.IOException;
import java.util.List;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.FacturaDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletFacturasImpagas")
public class ServletFacturasImpagas extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletFacturasImpagas() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasImpagas")!=null)
			request.getSession().removeAttribute("facturasImpagas");
		String idcli = request.getParameter("idcli"); 
		String montop = request.getParameter("monto");
		int id = Integer.parseInt(request.getParameter("idcli"));
		String esp  = request.getParameter("especie") ;
		try {
			List<FacturaDTO> facturasImpagas = BusinessDelegate.GetInstancia().getFacturasInpagas(id);
			request.getSession().setAttribute("facturasImpagas", facturasImpagas);
			request.getSession().setAttribute("idcli", idcli);
			request.getSession().setAttribute("esp", esp);
			request.getSession().setAttribute("montop", montop);
		} catch (CommunicationException | ObjetoInexistenteException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
