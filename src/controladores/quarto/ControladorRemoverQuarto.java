package controladores.quarto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Quarto;

/**
 * Servlet implementation class ControladorRemoverQuarto
 */
@WebServlet("/quarto/remover")
public class ControladorRemoverQuarto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO logica do banco
		List<Quarto> quartos = new ArrayList<>();
		
		quartos.add(new Quarto("Quarto Moderno"));
		quartos.add(new Quarto("Quarto Rústico"));
		quartos.add(new Quarto("Quarto Azul"));
		
		Quarto quarto;
		
		try {
			quarto = quartos.get(id);
			request.setAttribute("quarto", quarto);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("quarto", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverQuarto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
