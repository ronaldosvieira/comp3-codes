package controladores.contrato;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;
import roteiros.contrato.RemoverContratoTS;

/**
 * Servlet implementation class ControladorRemoverContrato
 */
@WebServlet("/contrato/remover")
public class ControladorRemoverContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraRemoverContrato.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			RemoverContratoTS.execute(id);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ler");
	}

}
