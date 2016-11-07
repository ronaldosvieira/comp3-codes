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
import persistencia.ComodoBanco;
import persistencia.MobiliaBanco;

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
		
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			Mobilia mobilia = bd.get(id);
			comodos = comodoBd.get();
			comodosMobilia = comodos.stream().filter(c -> c.listaMobiliaDisponivel().contains(mobilia)).collect(Collectors.toList());
		
			request.setAttribute("id", id);
			request.setAttribute("mobilia", mobilia);
			request.setAttribute("comodos", comodos);
			request.setAttribute("comodosMobilia", comodosMobilia);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
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
		List<String> newComodos = Arrays.asList(request.getParameterValues("comodos"));
		List<Comodo> oldComodos = new ArrayList<>();
		
		Mobilia mobilia = new Mobilia(id, descricao, custo, tempoEntrega);
		
		try (MobiliaBanco bd = new MobiliaBanco();
				ComodoBanco comodoBd = new ComodoBanco()) {
			bd.update(id, mobilia);
			
			oldComodos = comodoBd.get().stream()
					.filter(c -> c.listaMobiliaDisponivel().contains(mobilia))
					.collect(Collectors.toList());
			
			for (String comodoStr : newComodos) {
				Comodo comodo = comodoBd.get(Integer.parseInt(comodoStr));
				
				comodo.associarMobilia(mobilia);
				
				comodoBd.update(comodo.obterId(), comodo);
			}
			
			for (Comodo comodo : oldComodos) {
				if (!newComodos.contains(String.valueOf(comodo.obterId()))) {
					comodo.desassociarMobilia(mobilia);
					
					comodoBd.update(comodo.obterId(), comodo);
				}
			}
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
