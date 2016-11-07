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
import persistencia.ComodoBanco;
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
		List<Comodo> comodos = new ArrayList<>();
		
		try (ComodoBanco cb = new ComodoBanco()) {
			comodos = cb.get();
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		request.setAttribute("comodos", comodos);
		
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
		
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			Mobilia mobilia = new Mobilia(descricao, custo, tempoEntrega);
			
			int id = bd.insert(mobilia);
			
			mobilia = new Mobilia(id, 
					mobilia.obterDescricao(), 
					mobilia.obterCusto(), 
					mobilia.obterTempoEntrega());
			
			for (String comodoStr : comodosStr) {
				Comodo comodo = comodoBd.get(Integer.parseInt(comodoStr));
				
				comodo.associarMobilia(mobilia);
				
				comodoBd.update(comodo.obterId(), comodo);
			}
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
		}
		
		response.sendRedirect("ler");
	}

}
