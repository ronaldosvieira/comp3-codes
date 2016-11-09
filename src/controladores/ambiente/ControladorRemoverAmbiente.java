package controladores.ambiente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import roteiros.ambiente.RemoverAmbienteTS;

/**
 * Servlet implementation class ControladorRemoverAmbiente
 */
@WebServlet("/contrato/ambiente/remover")
public class ControladorRemoverAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Ambiente ambiente = ObterAmbienteTS.execute(id);

			request.setAttribute("id", id);
			request.setAttribute("contrato_id", ambiente.obterContratoId());
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../../FronteiraRemoverAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int contratoId = Integer.parseInt(request.getParameter("contrato_id"));

		try {
			RemoverAmbienteTS.execute(id);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
	
		response.sendRedirect("ler?contrato_id=" + contratoId);
	}

}
