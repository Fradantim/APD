package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@WebServlet("/ServletUbicarOrden")
public class ServletUbicarOrden extends HttpServlet {

	private static final long serialVersionUID = -4681816431973563360L;

	public ServletUbicarOrden() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");
		System.out.println("Adeeeentrooo " + ids);
		ArrayList<Integer> idsElegidos= new ArrayList<>();
		try {
			for(String str: ids.split(";")) {
				idsElegidos.add(Integer.parseInt(str));
			}
			/*
			System.out.print("Ids Ubicaciones recuperadas: ");
			for(Integer i: idsElegidos) {
				System.out.print(i+" ");
			}
			System.out.println();*/
			//TODO recuperar data necesaria y llamar a bd
			response.getWriter().print("{\"message\" : \"Orden ubicada correctamente!\" , \"forwardTo\" : \""+request.getContextPath()+"/ServletOrdIngrPendUbic\"}");
		} catch(ParseException e) {
			response.getWriter().print("{\"errorMessage\": \"Error: Se obtuvo un id de ubicacion no numerico.\"}");;
			response.setStatus(400);
		} catch(NumberFormatException e) {
			response.getWriter().print("{\"errorMessage\": \"Error: Se obtuvo un id de ubicacion no numerico.\"}");;
			response.setStatus(400);
		}
		
	}
}
