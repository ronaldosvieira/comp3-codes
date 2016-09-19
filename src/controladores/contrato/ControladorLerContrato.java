package controladores.contrato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contrato;

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
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO logica do banco
		List<Contrato> contratos = new ArrayList<>();
		
		contratos.add(new Contrato(15.1f));
		contratos.add(new Contrato(8.7f));
		contratos.add(new Contrato(10.4f));
		
		Contrato contrato;
		
		try {
			contrato = contratos.get(id);
			request.setAttribute("contrato", contrato);
		} catch (IndexOutOfBoundsException e) {
			request.setAttribute("contrato", null);
		}
		
		request.setAttribute("id", id);
		
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
