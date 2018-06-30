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

@WebServlet("/ServletFacturasImpagas")
public class ServletFacturasImpagas extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletFacturasImpagas() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasImpagas")!=null)
			request.getSession().removeAttribute("facturasImpagas");
		
//TODO pedirle facturas inpagas al bd
//		Administrador clientes --> getFacturasInpagas(int clienteId)
		String idcli = request.getParameter("idcli"); 
		String montop = request.getParameter("monto");
		int id = Integer.parseInt(request.getParameter("idcli"));
		float monto = Float.parseFloat(request.getParameter("monto"));
		String esp  = request.getParameter("especie") ;
		System.out.println("do get" + " voy a buscar facturas impagas para el cliente: " + id + "monto: " + monto + "especie: " + esp  );
		System.out.println("do get" + " voy a buscar facturas impagas para el cliente: " + idcli + "monto: " + montop + "especie: " + esp  );
		ArrayList<FacturaDTO> facturasImpagas = new ArrayList<>();
		for(int i=0; i< 5 ; i++) {
			facturasImpagas.add(new FacturaDTO(i, new Date(), 0, "impaga", 13*i));
		}

		request.getSession().setAttribute("facturasImpagas", facturasImpagas);
		request.getSession().setAttribute("idcli", idcli);
		request.getSession().setAttribute("esp", esp);
		request.getSession().setAttribute("montop", montop);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
