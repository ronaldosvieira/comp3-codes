package controladores.comodocomposto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Comodo;
import entidades.ComodoComposto;
import persistencia.ComodoBanco;

/**
 * Servlet implementation class ControladorRemoverComodoComposto
 */
@WebServlet("/comodocomposto/remover")
public class ControladorRemoverComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try (ComodoBanco bd = new ComodoBanco()) {
			Comodo comodo = bd.get(id);
			
			if (!comodo.listaMobiliaDisponivel().isEmpty()) {
				response.getWriter().append("O cômodo '" + comodo.obterDescricao() + 
						"' não pode ser removido, pois, existem mobílias associadas a ele.");
				return;
			}
			
			for (Comodo comodoComposto : bd.get("comodo_composto")) {
				if (((ComodoComposto) comodoComposto).obterComodos().contains(comodo)) {
					response.getWriter().append("O cômodo '" + comodo.obterDescricao() + 
							"' não pode ser removido, pois, existem cômodos compostos associados a ele.");
					return;
				}
			}
			
			bd.remove(id);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
