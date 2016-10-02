package controladores.comodocomposto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ComodoComposto;
import persistencia.ComodoCompostoBanco;

/**
 * Servlet implementation class ControladorAlterarComodoComposto
 */
@WebServlet("/comodocomposto/alterar")
public class ControladorAlterarComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		ComodoComposto comodoComposto = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try (ComodoCompostoBanco bd = new ComodoCompostoBanco()) {
			comodoComposto = bd.get(id);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("comodoComposto", comodoComposto);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		
		ComodoComposto comodoComposto = new ComodoComposto(id, descricao);
		
		try (ComodoCompostoBanco bd = new ComodoCompostoBanco()) {
			bd.update(id, comodoComposto);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
