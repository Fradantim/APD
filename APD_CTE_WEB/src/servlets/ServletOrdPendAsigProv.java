package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.ProveedorDTO;

@WebServlet("/ServletOrdPendAsigProv")
public class ServletOrdPendAsigProv extends HttpServlet {

	private static final long serialVersionUID = -4637687064233503085L;
	
	public ServletOrdPendAsigProv(){}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<OrdenDeCompraDTO> ordenes = BusinessDelegate.GetInstancia().getOrdenesPendElecProveedor();			
			request.setAttribute("ordenes", ordenes);
			request.getRequestDispatcher("/jsp/OrdPendAsigProv.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace(); 
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ordenId=(String)request.getParameter("id");
			String articulo=(String)request.getParameter("articulo");
			String idArticulo=(String)request.getParameter("idArticulo");
			int articuloId= Integer.parseInt(idArticulo);
			List<ProveedorDTO> proveedores= BusinessDelegate.GetInstancia().obtenerProveedores(articuloId);	
			
			
			request.getSession().setAttribute("proveedores", proveedores);
			request.getSession().setAttribute("orden", ordenId);
			request.getSession().setAttribute("articulo", articulo);
			
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/AsignarProveedor.jsp"+"\"}");;
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
