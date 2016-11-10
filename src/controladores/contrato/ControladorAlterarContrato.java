package controladores.contrato;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;
import roteiros.contrato.AlterarContratoTS;
import roteiros.contrato.ObterContratoTS;

/**
 * Servlet implementation class ControladorAlterarContrato
 */
@WebServlet("/contrato/alterar")
public class ControladorAlterarContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Contrato contrato = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try {
			contrato = ObterContratoTS.execute(id);
			
			request.setAttribute("id", id);
			request.setAttribute("contrato", contrato);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarContrato.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		float comissao = Float.parseFloat(request.getParameter("comissao"));
		
		try {
			AlterarContratoTS.execute(id, comissao);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
