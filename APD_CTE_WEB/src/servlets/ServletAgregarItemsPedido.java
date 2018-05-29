package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ArticuloDTO;

@WebServlet("/ServletAgregarItemsPedido")
public class ServletAgregarItemsPedido extends HttpServlet {
	
	private static final long serialVersionUID = -374258851536329702L;

	public ServletAgregarItemsPedido() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id Recibido: "+request.getParameter("id"));
		System.out.println("cantidad recibida: "+request.getParameter("cant"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO pedir los articulos al bussinessDelegate

		ArrayList<ArticuloDTO> articulos = new ArrayList<>();
		articulos.add(new ArticuloDTO(1, "00001105", "papita", 300, "bolsa", "gr", 10));
		articulos.add(new ArticuloDTO(2, "00001106", "coca", 300, "bolsa", "gr", 20));
		articulos.add(new ArticuloDTO(3, "00001107", "chicle", 300, "bolsa", "gr", 15));
		articulos.add(new ArticuloDTO(4, "00001108", "salmon", 300, "bolsa", "gr", 117));
		articulos.add(new ArticuloDTO(5, "00001109", "asd", 300, "bolsa", "gr", 22));
		articulos.add(new ArticuloDTO(6, "00001110", "2389", 300, "bolsa", "gr", 543));
		
		request.setAttribute("articulos", articulos);
		request.setAttribute("idPedido", new Random().nextInt(1000000-1)+1);
		request.getRequestDispatcher("/jsp/agregarItemAPedido.jsp").forward(request, response);
		
		
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
