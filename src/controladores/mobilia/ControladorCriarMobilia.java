package controladores.mobilia;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Mobilia;
import persistencia.MobiliaBanco;

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
		
		Mobilia mobilia = new Mobilia(descricao, custo, tempoEntrega);
		
		try (MobiliaBanco bd = new MobiliaBanco()) {
			bd.insert(mobilia);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
