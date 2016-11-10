package controladores.mobilia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import roteiros.mobilia.AlterarMobiliaTS;
import roteiros.mobilia.ObterMobiliaTS;

/**
 * Servlet implementation class ControladorAtualizarMobilia
 */
@WebServlet("/mobilia/alterar")
public class ControladorAlterarMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		List<Comodo> comodos = new ArrayList<>();
		List<Comodo> comodosMobilia = new ArrayList<>();
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try {
			Mobilia mobilia = ObterMobiliaTS.execute(id);
			comodos = LerComodoTS.execute();
			comodosMobilia = comodos.stream().filter(c -> c.listaMobiliaDisponivel().contains(mobilia)).collect(Collectors.toList());
		
			request.setAttribute("id", id);
			request.setAttribute("mobilia", mobilia);
			request.setAttribute("comodos", comodos);
			request.setAttribute("comodosMobilia", comodosMobilia);
		} catch (DatabaseAccessException e) {
			response.getWriter().append(e.getMessage());
			e.printStackTrace(response.getWriter());
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarMobilia.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = request.getParameter("descricao");
		Float custo = Float.parseFloat(request.getParameter("custo"));
		int tempoEntrega = Integer.parseInt(request.getParameter("tempoEntrega"));
		List<String> comodos = Arrays.asList(request.getParameterValues("comodos"));
		
		try {
			AlterarMobiliaTS.execute(id, descricao, custo, tempoEntrega, comodos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
