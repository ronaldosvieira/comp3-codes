package controladores.ambiente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ambiente;

/**
 * Servlet implementation class ControladorRemoverAmbiente
 */
@WebServlet("/ambiente/remover")
public class ControladorRemoverAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO exemplos
		List<Ambiente> ambientes = new ArrayList<>();
		
		ambientes.add(new Ambiente(4, 1, 15.1f));
		ambientes.add(new Ambiente(2, 2, 8.7f));
		ambientes.add(new Ambiente(6, 2, 10.4f));
		
		Ambiente ambiente;
		
		try {
			ambiente = ambientes.get(id);
			request.setAttribute("ambiente", ambiente);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("ambiente", null);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		
		boolean remover = Boolean.parseBoolean(request.getParameter("remover"));
		
		if (!remover) {
			RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerAmbiente.jsp");
			rd.forward(request, response);
		}
		
		// TODO exemplos
		List<Ambiente> ambientes = new ArrayList<>();
		
		ambientes.add(new Ambiente(4, 1, 15.1f));
		ambientes.add(new Ambiente(2, 2, 8.7f));
		ambientes.add(new Ambiente(6, 2, 10.4f));
		
		// TODO logica de remocao
		try {
			ambientes.remove(id);
		} catch (IndexOutOfBoundsException e) {}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerAmbiente.jsp");
		rd.forward(request, response);
	}

}
