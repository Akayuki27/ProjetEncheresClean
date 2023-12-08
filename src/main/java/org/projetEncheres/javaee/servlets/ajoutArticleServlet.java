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
import org.projetEncheres.javaee.bll.RetraitManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Retrait;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.jdbc.ArticleDAOJdbcImpl;

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
	    Utilisateur u = (Utilisateur) session.getAttribute("userCo");
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    ArticleVendu a = null;
	    Categorie c = null;
	    CategorieManager crg = new CategorieManager();
	    ArticleManager mgr = new ArticleManager();
	    RetraitManager rmgr = new RetraitManager();
	    Retrait r = null;
	    int numeroArticle = -1; // Initialize with a default value

	    String nom = request.getParameter("nom");
	    String description = request.getParameter("description");
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"), dtf);
	    LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"), dtf);
	    int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
	    int prixVente = prixInitial;

	    try {
	        c = crg.selectByLibelle(request.getParameter("categories"));
	        a = new ArticleVendu(nom, description, dateDebut, dateFin, prixInitial, prixVente, u.getNoUtilisateur(), c.getNoCategorie());

	        // Insert into "article_vendu" and retrieve the generated key
	        ArticleDAOJdbcImpl adji = new ArticleDAOJdbcImpl();
	        numeroArticle = adji.insertArticle(a, u, c);

	        // Set the article number in the Retrait object
	        r = new Retrait(numeroArticle, request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"));
	        rmgr.insertRetrait(r);
	    } catch (DALException e) {
	        e.printStackTrace();
	    } catch (BLLException e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("accueilServlet");
	}
}
