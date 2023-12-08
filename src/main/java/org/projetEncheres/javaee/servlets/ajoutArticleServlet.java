package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class ajoutArticleServlet
 */
@WebServlet("/ajoutArticleServlet")
public class ajoutArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
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

	
		u = (Utilisateur) session.getAttribute("userCo");
		request.setAttribute("u", u);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
		u = (Utilisateur) session.getAttribute("userCo");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ArticleVendu a;
		Categorie c;
		CategorieManager crg = new CategorieManager();
		ArticleManager mgr = new ArticleManager();
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"), dtf);
		LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"), dtf);
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		int prixVente = prixInitial;
		
		try {
			c = crg.selectByLibelle(request.getParameter("categories"));
			a = new ArticleVendu(nom, description, dateDebut, dateFin, prixInitial, prixVente, u.getNoUtilisateur(), c.getNoCategorie());
			mgr.AjouterArticle(a, u, c);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("AfficherEnchereServlet"); //ou on crée une page pour afficher nos ventes en cours
	}

}
