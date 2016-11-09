package controladores.itemvenda;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ItemVenda;
import persistencia.ComodoBanco;
import persistencia.ItemVendaBanco;
import persistencia.MobiliaBanco;

/**
 * Servlet implementation class ControladorCriarItemVenda
 */
@WebServlet("/contrato/ambiente/item/criar")
public class ControladorCriarItemVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ambienteId;
		
		try (ComodoBanco bd = new ComodoBanco()) {
			ambienteId = Integer.parseInt(request.getParameter("ambiente_id"));
			
			request.setAttribute("comodos", bd.get());
			request.setAttribute("ambiente_id", ambienteId);
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
		} catch (SQLException | ClassNotFoundException e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../../../FronteiraCriarItemVenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		int mobiliaId = Integer.parseInt(request.getParameter("mobilia_id"));
		int ambienteId = Integer.parseInt(request.getParameter("ambiente_id"));
		
		try (ItemVendaBanco bd = new ItemVendaBanco();
				MobiliaBanco mobiliaBd = new MobiliaBanco()) {
			ItemVenda itemVenda = new ItemVenda(quantidade, mobiliaBd.get(mobiliaId), ambienteId);
			
			int id = bd.insert(itemVenda);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler?ambiente_id=" + ambienteId);
	}

}
