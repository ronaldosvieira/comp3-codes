package controladores.mobilia;

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
import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import persistencia.MobiliaBanco;
import roteiros.comodo.LerComodoTS;
import roteiros.itemvenda.GuardarItemVendaTS;
import roteiros.mobilia.GuardarMobiliaTS;

/**
 * Servlet implementation class ControladorCriarMobilia
 */
@WebServlet("/mobilia/criar")
public class ControladorCriarMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comodo> comodos = new ArrayList<>();
		
		try {
			comodos = LerComodoTS.execute();

			request.setAttribute("comodos", comodos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraCriarMobilia.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		Float custo = Float.parseFloat(request.getParameter("custo"));
		int tempoEntrega = Integer.parseInt(request.getParameter("tempoEntrega"));
		String[] comodosStr = request.getParameterValues("comodos");
		int[] comodosInt = new int[comodosStr.length];
		
		for (int i = 0; i < comodosStr.length; i++) {
			comodosInt[i] = Integer.parseInt(comodosStr[i]);
		}
		
		try {
			GuardarMobiliaTS.execute(descricao, custo, tempoEntrega, comodosInt);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
