package controladores.itemvenda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ItemVenda;
import entidades.ItemVenda;
import persistencia.ItemVendaBanco;

/**
 * Servlet implementation class ControladorLerItemVenda
 */
@WebServlet("/contrato/ambiente/item/ler")
public class ControladorLerItemVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ItemVenda> itemVendas = null;
		int ambienteId;
		
		try {
			ambienteId = Integer.parseInt(request.getParameter("ambiente_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("../ler");
			return;
		}
		
		try (ItemVendaBanco bd = new ItemVendaBanco()) {
			itemVendas = bd.getWhereAmbienteId(ambienteId);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("ambiente_id", ambienteId);
		request.setAttribute("itemVendas", itemVendas);
		
		RequestDispatcher rd = request.getRequestDispatcher("../../../FronteiraLerItemVenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
