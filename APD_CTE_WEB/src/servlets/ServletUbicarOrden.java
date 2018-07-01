package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import delegate.BusinessDelegate;
import dto.OrdenDeCompraDTO;
import exception.LaUbicacionNoTieneEsteArticuloException;
import exception.LaUbicacionNoTieneSuficientesArticulosParaRemoverException;
import exception.ObjetoInexistenteException;
import exception.SuperaLaCantidadUbicableEnLaUbicacionException;;

@WebServlet("/ServletUbicarOrden")
public class ServletUbicarOrden extends HttpServlet {

	private static final long serialVersionUID = -4681816431973563360L;

	public ServletUbicarOrden() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		String strid=request.getParameter("id");
		System.out.println(strid);
		int id=Integer.parseInt(strid);
		System.out.println(strid+" "+id);
		
		BusinessDelegate bd=null;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"No se pudo conectar al Servidor.\"}");
			response.setStatus(400);
			return;
		}
		
		ArrayList<Integer> idsElegidos= new ArrayList<>();
		try {
			for(String str: ids.split(";")) {
				idsElegidos.add(Integer.parseInt(str));
			}
			OrdenDeCompraDTO orden = (OrdenDeCompraDTO) request.getSession().getAttribute("ordenElegida");
			request.getSession().removeAttribute("ordenElegida");
			bd.ajusteInvCompra(orden.getId(), idsElegidos);
			response.getWriter().print("{\"message\" : \"Orden ubicada correctamente!\" , \"forwardTo\" : \""+request.getContextPath()+"/ServletOrdIngrPendUbic\"}");
		} catch(ParseException e) {
			response.getWriter().print("{\"errorMessage\": \"Error: Se obtuvo un id de ubicacion no numerico.\"}");;
			response.setStatus(400);
		} catch(NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"Error: Se obtuvo un id de ubicacion no numerico.\"}");;
			response.setStatus(400);
		} catch (CommunicationException e) {
			response.getWriter().print("{\"errorMessage\": \"Error de comunicacion.\"}");;
			response.setStatus(400);
		} catch (ObjetoInexistenteException e) {
			//
			;
		} catch (LaUbicacionNoTieneEsteArticuloException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: una de las ubicacioes ya tiene otro articulo.\"}");;
			response.setStatus(400);
		} catch (LaUbicacionNoTieneSuficientesArticulosParaRemoverException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: WTF?.\"}");;
			response.setStatus(400);
		} catch (SuperaLaCantidadUbicableEnLaUbicacionException e) {
			response.getWriter().print("{\"errorMessage\": \"ERROR: ???.\"}");;
			response.setStatus(400);
		}
		
	}
}
