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
		/*
		ArticuloDTO art1  = new ArticuloDTO(1, "00001105", "papita", 300, "bolsa", "gr", 10);
		ArticuloDTO art2 = new ArticuloDTO(2, "00001106", "coca", 300, "bolsa", "gr", 20);
		ArticuloDTO art3 = new ArticuloDTO(3, "00001107", "chicle", 300, "bolsa", "gr", 15);
		ArticuloDTO art4 = new ArticuloDTO(4, "00001108", "salmon", 300, "bolsa", "gr", 117);
		ArticuloDTO art5 = new ArticuloDTO(5, "00001109", "asd", 300, "bolsa", "gr", 22);
		ArticuloDTO art6 = new ArticuloDTO(6, "00001110", "2389", 300, "bolsa", "gr", 543);
		Date fecha = new Date();
		ordenes.add(new OrdenDeCompraDTO(000001, 10, "pendiente", fecha, fecha, 01, art1));
		ordenes.add(new OrdenDeCompraDTO(000002, 10, "pendiente", fecha, fecha, 01, art2));
		ordenes.add(new OrdenDeCompraDTO(000003, 10, "pendiente", fecha, fecha, 01, art3));
		ordenes.add(new OrdenDeCompraDTO(000004, 10, "pendiente", fecha, fecha, 01, art4));
		ordenes.add(new OrdenDeCompraDTO(000005, 10, "pendiente", fecha, fecha, 01, art5));
		ordenes.add(new OrdenDeCompraDTO(000006, 10, "pendiente", fecha, fecha, 01, art6));
		*/
		try {
			List<OrdenDeCompraDTO> ordenes = BusinessDelegate.GetInstancia().getOrdenesPendElecProveedor();			
			request.setAttribute("ordenes", ordenes);
			request.getRequestDispatcher("/jsp/OrdPendAsigProv.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*List<ProveedorDTO> proveedores= BusinessDelegate.GetInstancia();	
		String ordenId=(String)request.getParameter("id");
		String articulo=(String)request.getParameter("articulo");
		Date fecha = new Date();
		proveedores.add(new ProveedorDTO(001, "Raul", fecha, fecha));
		proveedores.add(new ProveedorDTO(002, "Javi", fecha, fecha));
		proveedores.add(new ProveedorDTO(003, "Juan Martin", fecha, fecha));
		proveedores.add(new ProveedorDTO(004, "Laura", fecha, fecha));
		proveedores.add(new ProveedorDTO(005, "Micaela", fecha, fecha));
		request.getSession().setAttribute("proveedores", proveedores);
		request.getSession().setAttribute("orden", ordenId);
		request.getSession().setAttribute("articulo", articulo);
		
		response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/AsignarProveedor.jsp"+"\"}");;*/
		
		
	}
	
	

}
