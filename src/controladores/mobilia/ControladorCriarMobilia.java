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
		String[] comodosStr = request.getParameterValues("comodos");
		List<Comodo> comodos = new ArrayList<>();
		
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			for (String comodo : comodosStr) {
				comodos.add(bd.get(Integer.parseInt(comodo)));
			}
			
			Mobilia mobilia = new Mobilia(descricao, custo, tempoEntrega);
			
			bd.insert(mobilia);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
