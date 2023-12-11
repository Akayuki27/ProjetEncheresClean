package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import org.projetEncheres.javaee.bo.Enchere;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class EnchereArticleServlet
 */
@WebServlet("/EnchereArticleServlet")
public class EnchereArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArt = Integer.parseInt(request.getParameter("noArt"));
		ArticleManager amgr = new ArticleManager();
		Categorie c;
		ArticleVendu a;
		Utilisateur u2;
		try {
			a = amgr.selectByID(idArt);
			request.setAttribute("a", a);
			u2 = getVendeur(a);
			request.setAttribute("u2", u2);
			c = getCategorie(a);
			request.setAttribute("c", c);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/enchereArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");
		Enchere e = null;
		ArticleVendu a;
		int idArt = Integer.parseInt(request.getParameter("idArt"));
		ArticleManager amgr = new ArticleManager();
		EncheresManager emgr = new EncheresManager();
		
		String cookieName = String.valueOf(idArt);
		if (request.getParameter("enchere") != null) {
			try {
				a = amgr.selectByID(idArt);
				int enchereNew = Integer.parseInt(request.getParameter("enchere"));
				e = new Enchere(u.getNoUtilisateur(), a.getNoArticle(), LocalDate.now(),enchereNew);
				emgr.insertEnchere(e, a, u);
				a.setPrixVente(enchereNew);
				amgr.update(a);
				response.addCookie(new Cookie(cookieName, "1"));
				
			} catch (DALException e1) {
				e1.printStackTrace();
			} catch (BLLException e1) {
				e1.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("accueilServlet");
		rd.forward(request, response);
		
	}
	
	private Utilisateur getVendeur(ArticleVendu a) {
		Utilisateur u2 = null;
		UtilisateurManager umgr = new UtilisateurManager();
		int id = a.getNo_utilisateur();
		try {
			u2 = umgr.selectByID(id);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return u2;
	}
	
	private Categorie getCategorie(ArticleVendu a) {
		CategorieManager cmgr = new CategorieManager();
		Categorie c = null;
		try {
			c = cmgr.selectByID(a.getNo_categorie());
		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	

}
