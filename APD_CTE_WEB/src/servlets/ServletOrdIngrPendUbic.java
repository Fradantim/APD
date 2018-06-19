package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;
import dto.OrdenDeCompraDTO;
import dto.UbicacionDTO;

@WebServlet("/ServletOrdIngrPendUbic")
public class ServletOrdIngrPendUbic extends HttpServlet {

	private static final long serialVersionUID = 8455662718558768718L;

	public ServletOrdIngrPendUbic() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO pedir las ordenes al bussinessDelegate
		ArrayList<OrdenDeCompraDTO> ordenes = new ArrayList<>();
		
		ordenes.add(new OrdenDeCompraDTO(1, 122, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(1, "00001105", "papita", 300, "bolsa", "gr", 10,11,12)));
		ordenes.add(new OrdenDeCompraDTO(2, 112, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(2, "00001106", "coca", 300, "bolsa", "gr", 20,21,22)));
		ordenes.add(new OrdenDeCompraDTO(3, 312, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(3, "00001107", "chicle", 300, "bolsa", "gr", 15,16,17)));
		ordenes.add(new OrdenDeCompraDTO(4, 112, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(4, "00001108", "salmon", 300, "bolsa", "gr", 117,118,119)));
		ordenes.add(new OrdenDeCompraDTO(5, 132, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(5, "00001109", "asd", 300, "bolsa", "gr", 22,23,24)));
		ordenes.add(new OrdenDeCompraDTO(6, 162, OrdenDeCompraDTO.ESTADO_RECIBIDO, new Date(), new Date(), 1,
				new ArticuloDTO(6, "00001110", "2389", 300, "bolsa", "gr", 543,544,545)));
				
		request.getSession().setAttribute("ordenes", ordenes);
		request.getRequestDispatcher("/jsp/OrdIngrPendUbic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("id");
		ArrayList<OrdenDeCompraDTO> ordenes = (ArrayList<OrdenDeCompraDTO>)request.getSession().getAttribute("ordenes");
		OrdenDeCompraDTO ordenElegida =null;
		for(OrdenDeCompraDTO orden: ordenes) {
			if(Integer.parseInt(id)==orden.getId()) {
				ordenElegida=orden;
			}
		}
		
		if(ordenElegida==null) {
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
			return;
		}
		
		request.getSession().removeAttribute("ordenes");
		
		request.getSession().setAttribute("ordenElegida", ordenElegida);
		
		//TODO pedir ubicaciones vacias al bd
		ArrayList<UbicacionDTO> ubicacionesVacias = new ArrayList<>();
		
		int i=1;
		String calles[]= new String[] {"A","B","C","D","E"};
		int bloques=8, estantes=5, posiciones=4;
		
		int idUbicacion=0;
		for(int iCalle=0; iCalle<calles.length; iCalle++) {
			for(int iBloque=0; iBloque<bloques; iBloque++) {
				for(int iEstante=0; iEstante<estantes; iEstante++) {
					for(int iPosticion=0; iPosticion<posiciones; iPosticion++) {
						ubicacionesVacias.add(new UbicacionDTO(++idUbicacion, calles[iCalle], iBloque+1, iEstante+1, iPosticion+1, 0));
					}
				}
			}
		}		
		
		request.getSession().setAttribute("ubicacionesVacias", ubicacionesVacias);
		//${o.cantidad/o.articulo.cantidadUbicable+(1-(o.cantidad/o.articulo.cantidadUbicable%1))%1}
		Double cantidadUbicacionesNecesarias=Math.ceil(new Double(ordenElegida.getCantidad())/ new Double(ordenElegida.getArticulo().getCantidadUbicable()));
		request.getSession().setAttribute("cantidadUbicacionesNecesarias", cantidadUbicacionesNecesarias);
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			//Asi seteo un codigo de error
			request.getSession().setAttribute("errorMessage", "Ooooops error no controlado.");
			response.setStatus(400);
		}
		
		//TODO cargar errores aca (segun sucedan)
		if("1".equals( id.trim().toLowerCase() )) {
			response.getWriter().print("{\"errorMessage\": \"Epa Epa!!!\"}");;
			response.setStatus(400);
		} else {
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/UbicarOrden.jsp"+"\"}");;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("INnnnnn" + request.getAttribute("ubicacionesIds"));
		for(String str: request.getParameterMap().keySet()) {
			System.out.println(str+" "+request.getParameterMap().get(str));
		}
		System.out.println(request.getAttribute("id"));
		response.setStatus(200);
	}

}
