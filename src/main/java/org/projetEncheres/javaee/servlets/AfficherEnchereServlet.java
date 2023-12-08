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
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
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
		String nomArticle = request.getParameter("nomArticle");
        int categorie = Integer.parseInt(request.getParameter("categories"));

		try {
			if (nomArticle != null && categorie != 0) {
	            // Filtre par nom et catégorie
	            articles = mgr.selectByNometCategorie(nomArticle, categorie);
	        } else if (nomArticle != null) {
	            // Filtre par nom
	            articles = mgr.selectByNom(nomArticle);
	        } else if (categorie != 0) {
	            // Filtre par catégorie
	            articles = mgr.selectByCategorie(categorie);
	       // } else {
	            // Aucun filtre, affiche tous les articles par ordre ID descendant
	          //  articles = mgr.selectAllOrderedByIdDesc();
	        //}
			
			} else {
				articles = mgr.selectAll();}
				for (ArticleVendu a : articles) {
					int id = a.getNo_utilisateur();
					u2 = umgr.selectByID(id);
					request.setAttribute("u2", u2);
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
		doGet(request, response);
	}

}
