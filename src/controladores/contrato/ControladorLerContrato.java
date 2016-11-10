package controladores.contrato;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contrato;
import excecoes.DatabaseAccessException;
import persistencia.ContratoBanco;
import roteiros.contrato.LerContratosTS;

/**
 * Servlet implementation class ControladorLerContrato
 */
@WebServlet("/contrato/ler")
public class ControladorLerContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contrato> contratos;
		try {
			contratos = LerContratosTS.execute();
			
			request.setAttribute("contratos", contratos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerContrato.jsp");
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
