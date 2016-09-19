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

import entidades.ComodoComposto;

/**
 * Servlet implementation class ControladorRemoverComodoComposto
 */
@WebServlet("/comodoComposto/remover")
public class ControladorRemoverComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO exemplos
		List<ComodoComposto> comodosCompostos = new ArrayList<>();
		
		comodosCompostos.add(new ComodoComposto("Suite"));
		comodosCompostos.add(new ComodoComposto("Sala de estudos"));
		comodosCompostos.add(new ComodoComposto("Cozinha americana"));
		
		ComodoComposto comodoComposto;
		
		try {
			comodoComposto = comodosCompostos.get(id);
			request.setAttribute("comodoComposto", comodoComposto);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("comodoComposto", null);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverComodoComposto.jsp");
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
