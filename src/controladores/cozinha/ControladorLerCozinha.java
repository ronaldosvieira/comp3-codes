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
import persistencia.ComodoBanco;

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
		
		try (ComodoBanco bd = new ComodoBanco()) {
			cozinhas = bd.get("cozinha");
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("cozinhas", cozinhas);
		
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
