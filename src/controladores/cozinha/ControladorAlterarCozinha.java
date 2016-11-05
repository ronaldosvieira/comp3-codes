package controladores.cozinha;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cozinha;
import persistencia.ComodoBanco;

/**
 * Servlet implementation class ControladorAlterarCozinha
 */
@WebServlet("/cozinha/alterar")
public class ControladorAlterarCozinha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Cozinha cozinha = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try (ComodoBanco bd = new ComodoBanco()) {
			cozinha = (Cozinha) bd.get(id);
		} catch (SQLException | ClassNotFoundException e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		} catch (IndexOutOfBoundsException e) {
			response.getWriter().append("Cozinha com id " + id + " não existe!");
		} catch (ClassCastException e) {
			response.getWriter().append("Cozinha inválida!");
		}
		
		request.setAttribute("id", id);
		request.setAttribute("cozinha", cozinha);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarCozinha.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		
		try (ComodoBanco bd = new ComodoBanco()) {
			Cozinha cozinha = (Cozinha) bd.get(id);
			
			cozinha.alterarDescricao(descricao);
			
			bd.update(id, cozinha);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
