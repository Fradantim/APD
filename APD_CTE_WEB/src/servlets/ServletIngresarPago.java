package servlets;

import java.io.IOException;

import javax.naming.CommunicationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegate.BusinessDelegate;
import exception.LaFacturaYaTienePagosDeOtraEspecieException;
import exception.ObjetoInexistenteException;

 
@WebServlet("/ServletIngresarPago")
public class ServletIngresarPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public ServletIngresarPago() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/ingresarPago.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//TODO pedir al BD para pagar la factura correspondiente		
//AdministradorClientes --> pagarFactura(int idCliente, int nroFactura, float pago, String especie)
		int idcliente = Integer.parseInt(request.getParameter("idcli"));
		int nroFactura = Integer.parseInt(request.getParameter("idcli"));
		float pago = Float.parseFloat(request.getParameter("montop"));
		String especie =request.getParameter("esp");
		System.out.println("do post" + " id " + nroFactura + " cliente: " + idcliente + " monto: " + pago + " especie: " + especie);		
		try {
			BusinessDelegate.GetInstancia().pagarFactura(idcliente, nroFactura, pago, especie);
		} catch (CommunicationException | ObjetoInexistenteException | LaFacturaYaTienePagosDeOtraEspecieException e) {
			e.printStackTrace();
		}
	}
}
