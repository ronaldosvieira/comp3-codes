package controladores.ambiente;

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
import excecoes.DatabaseAccessException;
import entidades.Ambiente;
import persistencia.AmbienteBanco;
import roteiros.ambiente.LerAmbienteTS;

/**
 * Servlet implementation class ControladorLerAmbiente
 */
@WebServlet("/contrato/ambiente/ler")
public class ControladorLerAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contratoId;
		
		try {
			contratoId = Integer.parseInt(request.getParameter("contrato_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("../ler");
			return;
		}
		
		List<Ambiente> ambientes;
		try {
			ambientes = LerAmbienteTS.execute(contratoId);
			
			request.setAttribute("contrato_id", contratoId);
			request.setAttribute("ambientes", ambientes);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../../FronteiraLerAmbiente.jsp");
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
