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
import roteiros.comodocomposto.AlterarComodoCompostoTS;
import roteiros.comodocomposto.ObterComodoCompostoTS;

/**
 * Servlet implementation class ControladorAlterarComodoComposto
 */
@WebServlet("/comodocomposto/alterar")
public class ControladorAlterarComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		ComodoComposto comodoComposto = null;
		List<Comodo> comodos;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try {
			comodoComposto = ObterComodoCompostoTS.execute(id);
			comodos = LerComodoTS.execute();
			
			request.setAttribute("id", id);
			request.setAttribute("comodoComposto", comodoComposto);
			request.setAttribute("comodos", comodos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		String[] comodosStr = request.getParameterValues("comodos");
		int[] comodosInt = new int[comodosStr.length];
		
		for (int i = 0; i < comodosStr.length; i++) {
			comodosInt[i] = Integer.parseInt(comodosStr[i]);
		}
		
		try {
			AlterarComodoCompostoTS.execute(id, descricao, comodosInt);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ler");
	}

}
