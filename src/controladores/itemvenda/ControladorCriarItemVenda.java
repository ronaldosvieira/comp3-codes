package controladores.itemvenda;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ItemVenda;
import persistencia.ItemVendaBanco;

/**
 * Servlet implementation class ControladorCriarItemVenda
 */
@WebServlet("/itemvenda/criar")
public class ControladorCriarItemVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarItemVenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		
		ItemVenda itemVenda = new ItemVenda(quantidade);
		
		try (ItemVendaBanco bd = new ItemVendaBanco()) {
			int id = bd.insert(itemVenda);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
