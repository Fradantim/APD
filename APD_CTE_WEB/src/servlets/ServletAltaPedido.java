package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletAltaPedido")
public class ServletAltaPedido extends HttpServlet {
	
	private static final long serialVersionUID = -7032019068067774735L;

    public ServletAltaPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*GestorDePersonas gestorDePersonas = new GestorDePersonas();
		String textoBuscar = request.getParameter("cpoCriterioConsulta");
		ArrayList<Persona> personas = gestorDePersonas.recupararPersonas(textoBuscar);
		request.setAttribute("personas", personas);
		request.getRequestDispatcher("/Ejercicios_JSP/TablaPersonas.jsp").forward(request, response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Atributos recuperados del Back End");
		System.out.println("Pais "+ request.getParameter("pais"));
		System.out.println("Provincia " +request.getParameter("provincia"));
		System.out.println("Partido " +request.getParameter("partido"));
		System.out.println("Codigo Postal " +request.getParameter("codigoPostal"));
		System.out.println("Calle " +request.getParameter("calle"));
		System.out.println("Altura " +request.getParameter("altura"));
		System.out.println("Piso " +request.getParameter("piso"));
		System.out.println("Numero " +request.getParameter("numero"));
		
		//TODO dar alta de pedido, recuperar el idPedido 
		//TODO Fowardear a agregarItemsAPedido con el idpedido
		
		/*GestorDePersonas gestorDePersonas = new GestorDePersonas();
		
		ArrayList <Persona> personas = gestorDePersonas.recupararPersonas(request.getParameter("cpoCriterioConsulta"));
		*/
		/*for(Persona p: personas){
			System.out.println(p.getApellido());
		}*/
		/*
		request.setAttribute("personas", personas);
		
		request.getRequestDispatcher("/Ejercicios_JSP/VistaPersona.jsp").forward(request, response);
		*/		
	}

}
