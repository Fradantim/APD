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

import dto.FacturaDTO;

@WebServlet("/ServletFrontClienteFacturasInpagas")
public class ServletFrontClienteFacturasInpagas extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletFrontClienteFacturasInpagas() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasInpagas")!=null)
			request.getSession().removeAttribute("facturasInpagas");
		
		//TODO pedirle facturas inpagas al bd
		ArrayList<FacturaDTO> facturasInpagas = new ArrayList<>();
		for(int i=0; i< 5 ; i++) {
			facturasInpagas.add(new FacturaDTO(i, new Date(), 0, "inpaga", 13*i));
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		request.getSession().setAttribute("facturasInpagas", facturasInpagas);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
