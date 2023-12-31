package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class supprimerProfilServlet
 */
@WebServlet("/supprimerProfilServlet")
public class supprimerProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
		u = (Utilisateur) session.getAttribute("userCo");
		UtilisateurManager mgr = new UtilisateurManager();
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleManager amgr = new ArticleManager();
		try {
			articles = amgr.selectByIdUtilisateur(u.getNoUtilisateur());
			for(ArticleVendu a : articles) {
				amgr.delete(a.getNoArticle());
			}

			mgr.deleteUtilisateur(u.getNoUtilisateur());
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		session.invalidate();
		//request.getRequestDispatcher("accueilServlet").forward(request, response);
		response.sendRedirect("accueilServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
