package servlets;

import java.io.IOException;
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
import dto.ClienteDTO;
import dto.PedidoCteDTO;
import exception.ObjetoInexistenteException;

@WebServlet("/ServletAgregarItemsPedido")
public class ServletAgregarItemsPedido extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletAgregarItemsPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessDelegate bd;
		try {
			bd= BusinessDelegate.GetInstancia();
		} catch (CommunicationException e) {
			request.setAttribute("errorMessage", "Ud no tiene un pedido abierto para agregarle items. "+e.getMessage());
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		
		if(request.getSession().getAttribute("pedidoAbierto")==null) {
			PedidoCteDTO pedidoAbierto;
			try {
				pedidoAbierto= bd.getPedidoAbiertoByCliente(((ClienteDTO)request.getSession().getAttribute("cliente")).getId() );
			} catch (ObjetoInexistenteException e) {
				request.setAttribute("errorMessage", "No se encontró el cliente logueado. "+e.getMessage());
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			} catch (CommunicationException e) {
				request.setAttribute("errorMessage", "Error de comunicacion. "+e.getMessage());
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}
			
			request.getSession().setAttribute("pedidoAbierto", pedidoAbierto);
			if(pedidoAbierto==null) {
				request.setAttribute("errorMessage", "Ud no tiene un pedido abierto para agregarle items. ");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			}
		}
		
		System.out.println("IN");
		List<ArticuloDTO> articulos;
		try {
			articulos = bd.getArticulos();
		} catch (CommunicationException e) {
			request.setAttribute("errorMessage", "No pudieron recuperarse articulos. "+e.getMessage());
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("articulos", articulos);
		request.getRequestDispatcher("/jsp/agregarItemAPedido.jsp").forward(request, response);
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
		//System.out.println("id Recibido: "+request.getParameter("id"));
		//System.out.println("cantidad: "+request.getParameter("cantidad"));
		//System.out.println("idCliente: "+request.getSession().getAttribute("idCliente"));
		//System.out.println("idPedido: "+((PedidoCteDTO)request.getSession().getAttribute("pedidoAbierto")).getId());
		try {
			String cantidad=request.getParameter("cantidad");
			int intcant=Integer.parseInt(cantidad);
			int idArt= Integer.parseInt(request.getParameter("id"));
			ArticuloDTO art=null;
			for(ArticuloDTO bdArt: bd.getArticulos()) {
				if(bdArt.getId()==idArt)
					art=bdArt;
			}
			
			bd.agregarArticuloAPedido(art.getCodDeBarras(),intcant , ((PedidoCteDTO)request.getSession().getAttribute("pedidoAbierto")).getId());
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
				
		/*try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			response.getWriter().print("{\"errorMessage\": \"Ooooops error no controlado\"}");;
			response.setStatus(400);
		}*/
	}
}
