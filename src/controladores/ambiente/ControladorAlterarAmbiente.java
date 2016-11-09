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
import roteiros.ambiente.ObterAmbienteTS;
import roteiros.ambiente.AlterarAmbienteTS;

/**
 * Servlet implementation class ControladorAlterarAmbiente
 */
@WebServlet("/contrato/ambiente/alterar")
public class ControladorAlterarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Ambiente ambiente = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try {
			ambiente = ObterAmbienteTS.execute(id);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("id", id);
		request.setAttribute("ambiente", ambiente);
		
		RequestDispatcher rd = request.getRequestDispatcher("../../FronteiraAlterarAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int numParedes = Integer.parseInt(request.getParameter("numParedes"));
		int numPortas = Integer.parseInt(request.getParameter("numPortas"));
		float metragem = Float.parseFloat(request.getParameter("metragem"));
		int contratoId = Integer.parseInt(request.getParameter("contrato_id"));
		
		try {
			AlterarAmbienteTS.execute(id, numParedes, numPortas, metragem, contratoId);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ler?contrato_id=" + contratoId);
	}

}
