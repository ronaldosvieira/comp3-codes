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

/**
 * Servlet implementation class ControladorLerItemVenda
 */
@WebServlet("/itemVenda/ler")
public class ControladorLerItemVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO logica do banco
		List<ItemVenda> itemVendas = new ArrayList<>();
		
		itemVendas.add(new ItemVenda(15));
		itemVendas.add(new ItemVenda(8));
		itemVendas.add(new ItemVenda(10));
		
		ItemVenda itemVenda;
		
		try {
			itemVenda = itemVendas.get(id);
			request.setAttribute("itemVenda", itemVenda);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("itemVenda", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerItemVenda.jsp");
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
