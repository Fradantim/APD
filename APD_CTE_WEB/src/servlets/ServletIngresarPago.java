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

import dto.FacturaDTO;
import dto.PedidoCteDTO;

@WebServlet("/ServletIngresarPago")
public class ServletIngresarPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private BusinessDelegate bd;
    public ServletIngresarPago() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO pedir al BD la lista de pedidos pendientes
		//	List<PedidoCteDTO> pedidospen = bd.getPedidosPendAprobCred();
			List<PedidoCteDTO> pedidospen = new ArrayList<>();
			request.setAttribute("pedidospen", pedidospen);
			request.getRequestDispatcher("/jsp/ingresarPago.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
