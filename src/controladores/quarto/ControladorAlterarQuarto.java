package controladores.quarto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Quarto;
import persistencia.ComodoBanco;
import persistencia.QuartoBanco;

/**
 * Servlet implementation class ControladorAlterarQuarto
 */
@WebServlet("/quarto/alterar")
public class ControladorAlterarQuarto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Quarto quarto = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try (ComodoBanco bd = new ComodoBanco()) {
			quarto = (Quarto) bd.get(id);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("quarto", quarto);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarQuarto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");

		try (ComodoBanco bd = new ComodoBanco()) {
			Quarto quarto = (Quarto) bd.get(id);
			
			quarto.alterarDescricao(descricao);
			
			bd.update(id, quarto);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
