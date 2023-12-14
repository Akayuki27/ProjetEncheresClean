package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bll.EncheresManager;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
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
		HttpSession session = request.getSession();
		Utilisateur u;
		u = (Utilisateur) session.getAttribute("userCo");
		request.setAttribute("u", u);
		List<Categorie> cat;
		List<ArticleVendu> articles;
		ArticleManager amgr = new ArticleManager();
		EncheresManager emgr = new EncheresManager();
		//recuperer categorie pour la liste de choix		
		CategorieManager catrg = new CategorieManager();
		Utilisateur u2 = null;
		UtilisateurManager umgr = new UtilisateurManager();
			try {
				cat = catrg.selectAll();
				articles = amgr.selectAll();
				for (ArticleVendu a : articles) {
					int id = a.getNo_utilisateur();
					u2 = umgr.selectByID(id);
					a.setU2(u2);
					request.setAttribute("u2", u2);
					emgr.vainqueurEnchere(a);
				}
				request.setAttribute("categories", cat);
				request.setAttribute("articles", articles);
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
