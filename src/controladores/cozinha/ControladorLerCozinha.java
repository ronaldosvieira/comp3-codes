package controladores.cozinha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cozinha;

/**
 * Servlet implementation class ControladorLerCozinha
 */
@WebServlet("/cozinha/ler")
public class ControladorLerCozinha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO lógica do banco
		List<Cozinha> cozinhas = new ArrayList<>();
		
		cozinhas.add(new Cozinha("Cozinha Moderna"));
		cozinhas.add(new Cozinha("Cozinha Rústica"));
		cozinhas.add(new Cozinha("Cozinha Azul"));
		
		Cozinha cozinha;
		
		try {
			cozinha = cozinhas.get(id);
			request.setAttribute("cozinha", cozinha);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("cozinha", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerCozinha.jsp");
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
