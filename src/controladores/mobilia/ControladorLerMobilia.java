package controladores.mobilia;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Mobilia;
import excecoes.DatabaseAccessException;
import persistencia.MobiliaBanco;
import roteiros.mobilia.LerMobiliaTS;

/**
 * Servlet implementation class ControladorLerMobilia
 */
@WebServlet("/mobilia/ler")
public class ControladorLerMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Mobilia> mobilias;
		try {
			mobilias = LerMobiliaTS.execute();
			
			request.setAttribute("mobilias", mobilias);
		} catch (DatabaseAccessException e) {
			e.printStackTrace(response.getWriter());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("../FronteiraLerMobilia.jsp");
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
