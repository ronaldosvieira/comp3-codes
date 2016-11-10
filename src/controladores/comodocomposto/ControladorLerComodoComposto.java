package controladores.comodocomposto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Comodo;
import excecoes.DatabaseAccessException;
import persistencia.ComodoBanco;
import roteiros.comodocomposto.LerComodoCompostoTS;
import roteiros.comodocomposto.ObterComodoCompostoTS;

/**
 * Servlet implementation class ControladorLerComodoComposto
 */
@WebServlet("/comodocomposto/ler")
public class ControladorLerComodoComposto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comodo> comodoCompostos = null;
		
		try {
			comodoCompostos = LerComodoCompostoTS.execute();
			
			request.setAttribute("comodoCompostos", comodoCompostos);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerComodoComposto.jsp");
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
