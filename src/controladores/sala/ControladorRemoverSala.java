package controladores.sala;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Sala;

/**
 * Servlet implementation class ControladorRemoverSala
 */
@WebServlet("/sala/remover")
public class ControladorRemoverSala extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO logica do banco
		List<Sala> salas = new ArrayList<>();
		
		salas.add(new Sala("Sala de Estar"));
		salas.add(new Sala("Sala de Jogos"));
		salas.add(new Sala("Sala Sadomazoquista"));
		
		Sala sala;
		
		try {
			sala = salas.get(id);
			request.setAttribute("sala", sala);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("sala", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverSala.jsp");
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
