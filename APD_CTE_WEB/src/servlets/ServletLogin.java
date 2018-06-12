package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletLogin() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO autenticar
		String user=request.getParameter("user");
		//System.out.println(user+" "+request.getParameter("pass"));
		//TODO Si el login no existe tengo que devolver codigo 2XX		
		
		//TODO recuperar bien el idUsuario ingresado
		request.getSession().setAttribute("idUsuario",user);
		
		//TODO Evaluar el perfil del usuario y asignar a la session los atributos que correspondan		
		request.getSession().setAttribute("perfil","Cliente");
		request.getSession().setAttribute("idCLiente","5353456");		
		
		//TODO cargar errores aca (segun sucedan)
		if("aaa".equals( user.trim().toLowerCase() )) {
			response.getWriter().print("{\"errorMessage\": \"Epa Epa!!!\"}");;
			response.setStatus(400);
		} else {
			//TODO evaluar un home mas dinamico para el cliente
			response.getWriter().print("{\"forwardTo\": \""+request.getContextPath()+"/jsp/bannerSuperior.jsp"+"\"}");;
		}
	}

}
