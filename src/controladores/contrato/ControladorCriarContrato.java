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
import roteiros.contrato.GuardarContratoTS;

/**
 * Servlet implementation class ControladorCriarContrato
 */
@WebServlet("/contrato/criar")
public class ControladorCriarContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarContrato.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Float comissao = Float.parseFloat(request.getParameter("comissao"));
		
		try {
			GuardarContratoTS.execute(comissao);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
