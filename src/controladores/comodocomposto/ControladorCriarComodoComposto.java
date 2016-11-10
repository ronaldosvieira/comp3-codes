package controladores.comodocomposto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Comodo;
import entidades.ComodoComposto;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import roteiros.comodocomposto.GuardarComodoCompostoTS;

/**
 * Servlet implementation class ControladorCriarComodoComposto
 */
@WebServlet("/comodocomposto/criar")
public class ControladorCriarComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comodo> comodos;
		
		try {
			comodos = ObterComodoTS.execute();
			
			request.setAttribute("comodos", comodos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		String[] comodosStr = request.getParameterValues("comodos");
		int[] comodosInt = new int[comodosStr.length];
		
		for (int i = 0; i < comodosStr.length; i++) {
			comodosInt[i] = Integer.parseInt(comodosStr[i]);
		}
		
		try {
			GuardarComodoCompostoTS.execute(descricao, comodosInt);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
