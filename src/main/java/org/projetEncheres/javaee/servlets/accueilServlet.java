package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/accueilServlet")
public class accueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> cat;
		//recuperer categorie pour la liste de choix		
		CategorieManager catrg = new CategorieManager();
			try {
				cat = catrg.selectAll();
				request.setAttribute("categories", cat);
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
