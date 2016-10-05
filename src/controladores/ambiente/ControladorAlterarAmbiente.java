package controladores.ambiente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ambiente;
import persistencia.AmbienteBanco;

/**
 * Servlet implementation class ControladorAlterarAmbiente
 */
@WebServlet("/ambiente/alterar")
public class ControladorAlterarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Ambiente ambiente = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect("ler");
			return;
		}
		
		try (AmbienteBanco bd = new AmbienteBanco()) {
			ambiente = bd.get(id);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("ambiente", ambiente);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int numParedes = Integer.parseInt(request.getParameter("numParedes"));
		int numPortas = Integer.parseInt(request.getParameter("numPortas"));
		float metragem = Float.parseFloat(request.getParameter("metragem"));
		
		Ambiente ambiente = new Ambiente(id, numParedes, numPortas, metragem);
		
		try (AmbienteBanco bd = new AmbienteBanco()) {
			bd.update(id, ambiente);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
		
		response.sendRedirect("ler");
	}

}
