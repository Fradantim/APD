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



@WebServlet("/ServletAdminCred")
public class ServletAdminCred extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private BusinessDelegate bd;

     public ServletAdminCred() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO pedir al BD la lista de pedidos pendientes
		//	List<PedidoCteDTO> pedidospen = bd.getPedidosPendAprobCred();
 			List<PedidoCteDTO> pedidospen = new ArrayList<>();
			UsuarioDTO usuario = new UsuarioDTO(1,"yesica","cannatella","nivelRol","contrasena");
			DomicilioDeFacturacionDTO domicilio = new DomicilioDeFacturacionDTO(0,"Argentina", "Buenos Aires", "Lanus", "1824", "Arias", "255", "3", 3);		
			ClienteDTO Cte = new ClienteDTO(1, "Accenture", 200, "34963780",200,42419999,"condicionFin",domicilio,usuario);																			
			pedidospen.add(new PedidoCteDTO(1,new Date(),100,"Pendiente aprobacion crediticia", Cte));
			pedidospen.add(new PedidoCteDTO(2,new Date(),50,"Pendiente aprobacion crediticia", Cte));
			pedidospen.add(new PedidoCteDTO(3,new Date(),130,"Pendiente aprobacion crediticia", Cte));
			request.setAttribute("pedidospen", pedidospen);
			request.getRequestDispatcher("/jsp/aprobarPedidosPendAprobCred.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("do post");		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
	}

}


