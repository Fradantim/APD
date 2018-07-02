package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import delegate.BusinessDelegate;
import dto.FacturaDTO;
import exception.ObjetoInexistenteException;


@WebServlet("/ServletFacturasAbonadas")
public class ServletFacturasAbonadas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFacturasAbonadas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("facturasAbonadas")!=null)
			request.getSession().removeAttribute("facturasAbonadas");

		int id = Integer.parseInt(request.getParameter("idcli"));
		float monto = Float.parseFloat(request.getParameter("monto")) * -1;
		String esp  = request.getParameter("especie") ;
		try {
			Integer idfacturaAbonada = BusinessDelegate.GetInstancia().agregarPago(id, monto, esp);
			if (idfacturaAbonada.equals(0)){
				ArrayList<FacturaDTO> facturasAbonadas = new ArrayList<>();
				System.out.println("hice un pago general");
				request.getSession().setAttribute("facturasAbonadas", facturasAbonadas);
			}else{
				FacturaDTO facturaAbonada  = BusinessDelegate.GetInstancia().getById(idfacturaAbonada);
				System.out.println("factura  " + facturaAbonada.getId() + facturaAbonada.getFecha() + facturaAbonada.getBonificacion() + facturaAbonada.getEstado() + facturaAbonada.getImporte());
				ArrayList<FacturaDTO> facturasAbonadas = new ArrayList<>();
				facturasAbonadas.add(facturaAbonada);	
				request.getSession().setAttribute("facturasAbonadas", facturasAbonadas);
			}

		} catch (CommunicationException | ObjetoInexistenteException e) {
			e.printStackTrace();
		}
		
	}
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
