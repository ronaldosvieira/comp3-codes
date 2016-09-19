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

import entidades.Ambiente;
import entidades.ComodoComposto;

/**
 * Servlet implementation class ControladorLerComodoComposto
 */
@WebServlet("/comodocomposto/ler")
public class ControladorLerComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO lógica do banco
		List<ComodoComposto> comodoCompostos = new ArrayList<>();
		
		comodoCompostos.add(new ComodoComposto("Suíte"));
		comodoCompostos.add(new ComodoComposto("Sala de estudos"));
		comodoCompostos.add(new ComodoComposto("Cozinha americana"));
		
		ComodoComposto comodoComposto;
		
		try {
			comodoComposto = comodoCompostos.get(id);
			request.setAttribute("comodoComposto", comodoComposto);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("comodoComposto", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerComodoComposto.jsp");
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
