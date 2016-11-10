package controladores.cozinha;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import roteiros.cozinha.LerCozinhaTS;

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
		List<Comodo> cozinhas = null;
		
		try {
			cozinhas = LerCozinhaTS.execute();
			
			request.setAttribute("cozinhas", cozinhas);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
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
