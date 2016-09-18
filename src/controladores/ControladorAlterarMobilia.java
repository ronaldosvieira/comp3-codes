package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO obter mobilia id
		String descricao = "";
		float custo = 0.0f;
		int tempoEntrega = 0;
		
		request.setAttribute("descricao", descricao);
		request.setAttribute("custo", custo);
		request.setAttribute("tempoEntrega", tempoEntrega);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarMobilia.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
