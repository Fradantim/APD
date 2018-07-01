package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FacturaDTO;

@WebServlet("/ServletFacturasAbonadas")
public class ServletFacturasAbonadas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFacturasAbonadas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasAbonadas")!=null)
			request.getSession().removeAttribute("facturasAbonadas");
//TODO Agregar pago a traves del BD
//AdministradorClientes --> agregarPago(int idCliente, float pago, String especie) 
//se agrega return al agregar pago , devuelve facturas pagadas o guarda un null si ademas se paso
//recorrer el array devuelto y preguntar si nulo entonces indicar que se actualizo la cta cte y mostrar valor de cuenta corriente		
		String idcli = request.getParameter("idcli"); 
		String montop = request.getParameter("monto");
		int id = Integer.parseInt(request.getParameter("idcli"));
		float monto = Float.parseFloat(request.getParameter("monto"));
		String esp  = request.getParameter("especie") ;
		System.out.println("do get" + " voy a buscar facturas abonadas para el cliente: " + id + "monto: " + monto + "especie: " + esp  );
		System.out.println("do get" + " voy a buscar facturas abonadas para el cliente: " + idcli + "monto: " + montop + "especie: " + esp  );
		ArrayList<FacturaDTO> facturasAbonadas = new ArrayList<>();
		for(int i=0; i< 5 ; i++) {
			facturasAbonadas.add(new FacturaDTO(i, new Date(), 0, "abonada", 13*i));
		}
		request.getSession().setAttribute("facturasAbonadas", facturasAbonadas);
		
	}
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
