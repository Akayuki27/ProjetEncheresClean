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
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class afficherVendeurServlet
 */
@WebServlet("/afficherVendeurServlet")
public class afficherVendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur u2 = null;
		UtilisateurManager mgr = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			u2 = mgr.selectByID(id);
			request.setAttribute("u2", u2);
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profilVendeur.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtilisateur = Integer.parseInt(request.getParameter("supprimerUtilisateur"));
		UtilisateurManager umgr = new UtilisateurManager();
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleManager amgr = new ArticleManager();
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");
		
		try {
			articles = amgr.selectByIdUtilisateur(idUtilisateur);
			for(ArticleVendu a : articles) {
				amgr.delete(a.getNoArticle());
			}
			umgr.deleteUtilisateur(idUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("accueilServlet").forward(request, response);
	}

}
