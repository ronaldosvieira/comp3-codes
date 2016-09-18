package controladores.comodocomposto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int id = Integer.parseInt(request.getParameter("id"));
		
		// TODO obter comodo comosto id
		String descricao = "";
		
		request.setAttribute("descricao", descricao);
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraAlterarComodoComposto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		
		// TODO alterar comodo composto
		
		response.sendRedirect("../FronteiraLerComodoComposto.jsp");
	}

}
