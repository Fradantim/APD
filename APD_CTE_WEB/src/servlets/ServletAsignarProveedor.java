package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.ProveedorDTO;

@WebServlet("/ServletAsignarProveedor")
public class ServletAsignarProveedor extends HttpServlet {
	
	
	private static final long serialVersionUID = 2405811717259763848L;

	public ServletAsignarProveedor(){}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("idOrden: "+request.getParameter("id"));
		System.out.println("idProveedor: "+request.getParameter("proveedor"));		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
	}

}
