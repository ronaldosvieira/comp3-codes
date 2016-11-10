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
import excecoes.DatabaseAccessException;
import entidades.ItemVenda;
import persistencia.ItemVendaBanco;
import roteiros.itemvenda.LerItemVendaTS;
import roteiros.itemvenda.ObterItemVendaTS;

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
		
		try {
			itemVendas = LerItemVendaTS.execute(ambienteId);
			
			request.setAttribute("ambiente_id", ambienteId);
			request.setAttribute("itemVendas", itemVendas);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
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
