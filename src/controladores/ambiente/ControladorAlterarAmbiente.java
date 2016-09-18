package controladores.ambiente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorAlterarAmbiente
 */
@WebServlet("/ambiente/alterar")
public class ControladorAlterarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO obter ambiente id
		int numParedes = 0;
		int numPortas = 0;
		float metragem = 0.0f;
		
		request.setAttribute("numParedes", numParedes);
		request.setAttribute("numPortas", numPortas);
		request.setAttribute("metragem", metragem);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numParedes = Integer.parseInt(request.getParameter("numParedes"));
		int numPortas = Integer.parseInt(request.getParameter("numPortas"));
		float metragem = Float.parseFloat(request.getParameter("metragem"));
		
		// TODO alterar ambiente
		
		response.sendRedirect("../FronteiraLerAmbiente.jsp");
	}

}
