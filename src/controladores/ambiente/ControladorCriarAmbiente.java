package controladores.ambiente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorCriarAmbiente
 */
@WebServlet("/ambiente/criar")
public class ControladorCriarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numParedes = Integer.parseInt(request.getParameter("numParedes"));
		int numPortas = Integer.parseInt(request.getParameter("numPortas"));
		float metragem = Float.parseFloat(request.getParameter("metragem"));
		
		// TODO inserir ambiente
		
		response.sendRedirect("../FronteiraLerAmbiente.jsp");
	}

}
