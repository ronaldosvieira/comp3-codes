package controladores.itemvenda;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ItemVenda;
import excecoes.DatabaseAccessException;
import persistencia.ItemVendaBanco;
import roteiros.itemvenda.AlterarItemVendaTS;
import roteiros.itemvenda.ObterItemVendaTS;

/**
 * Servlet implementation class ControladorRemoverItemVenda
 */
@WebServlet("/contrato/ambiente/item/alterar")
public class ControladorAlterarItemVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			ItemVenda item = ObterItemVendaTS.execute(id);
			
			request.setAttribute("itemVenda", item);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../../../FronteiraAlterarItemVenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		
		try {
			AlterarItemVendaTS.execute(id, quantidade);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
