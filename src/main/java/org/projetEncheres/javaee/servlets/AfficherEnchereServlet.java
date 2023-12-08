package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class AfficherEnchereServlet
 */
@WebServlet("/AfficherEnchereServlet")
public class AfficherEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticleManager mgr = new ArticleManager();
		UtilisateurManager umgr = new UtilisateurManager();
		Utilisateur u2 = null;
		List<ArticleVendu> articles = null;

		try {
			if (request.getParameter("nomArticle") != null) {
				String tri = request.getParameter("nomArticle");
				articles = mgr.selectByNom(tri);
				for (ArticleVendu a : articles) {
					int id = a.getNo_utilisateur();
					u2 = umgr.selectByID(id);
					request.setAttribute("u2", u2);
				}
			} else {
				articles = mgr.selectAll();
				for (ArticleVendu a : articles) {
					int id = a.getNo_utilisateur();
					u2 = umgr.selectByID(id);
					request.setAttribute("u2", u2);
				}
			}
			request.setAttribute("articles", articles);

		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
