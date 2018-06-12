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
		//System.out.println(request.getParameter("user")+" "+request.getParameter("pass"));
		//TODO Si el login no existe tengo que devolver codigo 2XX		
		
		//TODO recuperar bien el idUsuario ingresado
		request.getSession().setAttribute("idUsuario",request.getAttribute("user"));
		
		//TODO Evaluar el perfil del usuario y asignar a la session los atributos que correspondan		
		request.getSession().setAttribute("perfil","Cliente");
		request.getSession().setAttribute("idCLiente","5353456");		
		
		//TODO evaluar un home mas dinamico para el cliente
		request.getRequestDispatcher("/jsp/bannerSuperior.jsp").forward(request, response);
	}

}
