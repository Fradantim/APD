package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;
import dto.UbicacionDTO;
import dto.UsuarioDTO;


@WebServlet("/ServletAjustarStock")
public class ServletAjustarStock extends HttpServlet{

	private static final long serialVersionUID = -7585561009387612412L;
	public ServletAjustarStock(){}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/jsp/AjustarStock.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("codigoBarras: "+request.getParameter("codigoBarras"));
		System.out.println("cantidad: "+request.getParameter("cantidad"));
		System.out.println("ubicacion: "+request.getParameter("ubicacion"));
		System.out.println("usuarioEncargado: "+request.getParameter("usuarioEncargado"));
		System.out.println("usuarioAutorizador: "+request.getParameter("usuarioAutorizador"));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
	}

}
