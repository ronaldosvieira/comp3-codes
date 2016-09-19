package controladores.mobilia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Mobilia;

/**
 * Servlet implementation class ControladorLerMobilia
 */
@WebServlet("/mobilia/ler")
public class ControladorLerMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO logica do banco
		List<Mobilia> mobilias = new ArrayList<>();
		
		mobilias.add(new Mobilia("Cadeira Maneira de Madeira", 100.0f, 10));
		mobilias.add(new Mobilia("Cadeira Roxa Louca", 120.0f, 10));
		mobilias.add(new Mobilia("Cadeira do Wesley Safadão", 500.0f, 20));
		
		Mobilia mobilia;
		
		try {
			mobilia = mobilias.get(id);
			request.setAttribute("mobilia", mobilia);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("mobilia", null);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerMobilia.jsp");
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
