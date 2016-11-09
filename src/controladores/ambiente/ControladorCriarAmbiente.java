package controladores.ambiente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ambiente;
import excecoes.DatabaseAccessException;
import persistencia.AmbienteBanco;
import roteiros.ambiente.GuardarAmbienteTS;

/**
 * Servlet implementation class ControladorCriarAmbiente
 */
@WebServlet("/contrato/ambiente/criar")
public class ControladorCriarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contratoId;
		
		try {
			contratoId = Integer.parseInt(request.getParameter("contrato_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		request.setAttribute("contrato_id", contratoId);
		
		RequestDispatcher rd = request.getRequestDispatcher("../../FronteiraCriarAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numParedes = Integer.parseInt(request.getParameter("numParedes"));
		int numPortas = Integer.parseInt(request.getParameter("numPortas"));
		float metragem = Float.parseFloat(request.getParameter("metragem"));
		int contratoId = Integer.parseInt(request.getParameter("contrato_id"));
		
		try {
			GuardarAmbienteTS.execute(numParedes, numPortas, metragem, contratoId);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ler?contrato_id=" + contratoId);
	}

}
