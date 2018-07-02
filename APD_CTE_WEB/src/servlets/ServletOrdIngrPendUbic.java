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
import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.UbicacionDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletOrdIngrPendUbic")
public class ServletOrdIngrPendUbic extends HttpServlet {

	private static final long serialVersionUID = 8455662718558768718L;

	public ServletOrdIngrPendUbic() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO pedir las ordenes al bussinessDelegate
		BusinessDelegate bd=null;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"No se pudo conextar al Servidor.\"}");;
			response.setStatus(400);
			return;
		}
		List<OrdenDeCompraDTO> ordenes=null;
		try {
			ordenes = bd.getOrdCompraRecibidas();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"Error de comunicacion.\"}");;
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e) {
			//
			;
		}
				
		request.getSession().setAttribute("ordenes", ordenes);
		request.getRequestDispatcher("/jsp/OrdIngrPendUbic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("id");
		ArrayList<OrdenDeCompraDTO> ordenes = (ArrayList<OrdenDeCompraDTO>)request.getSession().getAttribute("ordenes");
		OrdenDeCompraDTO ordenElegida =null;
		for(OrdenDeCompraDTO orden: ordenes) {
			if(Integer.parseInt(id)==orden.getId()) {
				ordenElegida=orden;
			}
		}
		
		if(ordenElegida==null) {
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
			return;
		}
		
		request.getSession().removeAttribute("ordenes");
		
		request.getSession().setAttribute("ordenElegida", ordenElegida);
		
		BusinessDelegate bd=null;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"No se pudo conextar al Servidor.\"}");;
			response.setStatus(400);
			return;
		}
		List<UbicacionDTO> ubicacionesVacias=null;
		try {
			ubicacionesVacias = bd.getUbicacionesVacias();
		} catch (CommunicationException e1) {
			response.getWriter().print("{\"errorMessage\": \"No se pudo conextar al Servidor.\"}");;
			response.setStatus(400);
			return;
		} catch (ObjetoInexistenteException e1) {
			//
			;
		}
				
		request.getSession().setAttribute("ubicacionesVacias", ubicacionesVacias);
		//${o.cantidad/o.articulo.cantidadUbicable+(1-(o.cantidad/o.articulo.cantidadUbicable%1))%1}
		Double cantidadUbicacionesNecesarias=Math.ceil(new Double(ordenElegida.getCantidad())/ new Double(ordenElegida.getArticulo().getCantidadUbicable()));
		request.getSession().setAttribute("cantidadUbicacionesNecesarias", cantidadUbicacionesNecesarias);
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		
		response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/UbicarOrden.jsp"+"\"}");
	}
}
