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
 * Servlet implementation class ControladorCriarComodoComposto
 */
@WebServlet("/comodocomposto/criar")
public class ControladorCriarComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comodo> comodos;
		
		try (ComodoBanco bd = new ComodoBanco()) {
			comodos = bd.get();
			
			request.setAttribute("comodos", comodos);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		String[] comodosStr = request.getParameterValues("comodos");
		List<Comodo> comodos = new ArrayList<>();
		
		try (ComodoBanco bd = new ComodoBanco()) {
			ComodoComposto comodoComposto = new ComodoComposto(descricao);
			
			for (String comodo : comodosStr) {
				comodos.add(bd.get(Integer.parseInt(comodo)));
			}
			
			comodoComposto.alterarComodos(comodos);
			
			bd.insert(comodoComposto);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
