package controladores.comodocomposto;

import java.io.IOException;
import java.util.ArrayList;
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
		List<Comodo> comodos;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try (ComodoBanco bd = new ComodoBanco()) {
			comodoComposto = (ComodoComposto) bd.get(id);
			comodos = bd.get();
			
			;
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("comodoComposto", comodoComposto);
		request.setAttribute("comodos", comodos);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		String[] comodosStr = request.getParameterValues("comodos");
		List<Comodo> comodos = new ArrayList<>();
		
		try (ComodoBanco bd = new ComodoBanco()) {
			ComodoComposto comodoComposto = (ComodoComposto) bd.get(id);
			
			for (String comodo : comodosStr) {
				comodos.add(bd.get(Integer.parseInt(comodo)));
			}
			
			comodoComposto.alterarDescricao(descricao);
			comodoComposto.alterarComodos(comodos);
			
			bd.update(id, comodoComposto);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
