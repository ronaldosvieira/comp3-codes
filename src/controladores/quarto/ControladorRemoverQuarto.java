package controladores.quarto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Comodo;
import entidades.ComodoComposto;
import excecoes.BusinessLogicException;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import roteiros.quarto.RemoverQuartoTS;

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
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverQuarto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			RemoverQuartoTS.execute(id);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		} catch (BusinessLogicException e) {
			response.getWriter().append(e.getMessage());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
