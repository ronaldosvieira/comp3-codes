package controladores.ambiente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Ambiente;
import persistencia.AmbienteBanco;

/**
 * Servlet implementation class ControladorRemoverAmbiente
 */
@WebServlet("/contrato/ambiente/remover")
public class ControladorRemoverAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try (AmbienteBanco bd = new AmbienteBanco()) {
			Ambiente ambiente = bd.get(id);

			request.setAttribute("id", id);
			request.setAttribute("contrato_id", ambiente.obterContratoId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../../FronteiraRemoverAmbiente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int contratoId = Integer.parseInt(request.getParameter("contrato_id"));

		try (AmbienteBanco bd = new AmbienteBanco()) {
			bd.remove(id);
		} catch (Exception e) {
			response.getWriter().append("Erro ao acessar o banco de dados: \n");
			e.printStackTrace(response.getWriter());
			return;
		}
	
		response.sendRedirect("ler?contrato_id=" + contratoId);
	}

}
