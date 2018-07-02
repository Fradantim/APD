package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import dto.DomicilioDeFacturacionDTO;
import dto.PedidoCteDTO;
import dto.UsuarioDTO;
import exception.ExisteUnPedidoConArticulosDeEsosReservadosException;
import exception.ObjetoInexistenteException;



@WebServlet("/ServletAdminCred")
public class ServletAdminCred extends HttpServlet {
	private static final long serialVersionUID = 1L;

     public ServletAdminCred() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				List<PedidoCteDTO> pedidospen =  BusinessDelegate.GetInstancia().getPedidosPendAprobCred();
				request.setAttribute("pedidospen", pedidospen);
				request.getRequestDispatcher("/jsp/aprobarPedidosPendAprobCred.jsp").forward(request, response);

			} catch (CommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		int id = Integer.parseInt(request.getParameter("id"));
		String motivo =request.getParameter("motivo");
		String metodo =request.getParameter("metodo");
		if (metodo.equals("Aprobar")){
				try {
					BusinessDelegate.GetInstancia().aceptarPedidoCred(id, motivo);
				} catch (CommunicationException | ExisteUnPedidoConArticulosDeEsosReservadosException
						| ObjetoInexistenteException e) {
					e.printStackTrace();
				}
 		}else{
				try {
					BusinessDelegate.GetInstancia().rechazarPedidoCred(id, motivo);
				} catch (CommunicationException | ObjetoInexistenteException e) {
					e.printStackTrace();
				}
		}
 		;	
	}
}


