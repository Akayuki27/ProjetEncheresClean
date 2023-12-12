package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.List;

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
		HttpSession session = request.getSession();
	    Utilisateur u = (Utilisateur) session.getAttribute("userCo");

		ArticleManager mgr = new ArticleManager();
		UtilisateurManager umgr = new UtilisateurManager();
		Utilisateur u2 = null;
		ArticleVendu article = null;
		List<ArticleVendu> articles = null;
		String nomArticle = request.getParameter("nomArticle");
        int categorie = Integer.parseInt(request.getParameter("categories"));
        List<Categorie> cat;
        Cookie[] cookies = request.getCookies();
		//recuperer categorie pour la liste de choix		
		CategorieManager catrg = new CategorieManager();
		String where = "WHERE 1+1 AND ";
		String[] filter = request.getParameterValues("filter[]");
		if (request.getParameter("ChoixAchat") != null) {
			for( String s : filter) {
				switch(s) {
				case "EnchereOuvertes" : where += "date_fin_encheres >= GETDATE()";
				break;
				case "EnchereEnCours":
					where += "GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres AND no_article IN(";
					int compt = 0;
					for(Cookie c : cookies) {
						if (c.getValue() == "1") {
							int id = Integer.parseInt(c.getName());
							if (compt >= 1) {
								where += ",";
							}
							where += id;
							compt++;
						}
					}
					where += "GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres AND no_article=?";
				break;
				case "EnchereRemportes": where += "no_utilisateur=? and no_article=?";
				break;
				}
			}
		} else if (request.getParameter("ChoixVente") != null) {
			where += "no_ulisateur=" + u.getNoUtilisateur();
			for( String s : filter) {
				switch(s) {
				case "VenteEnCours" : where += " AND GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres";
				break;
				case "VenteNonDebutes": where += " AND date_debut_encheres > GETDATE()";
				break;
				case "VentesTermines": where += " AND etat_vente=1";
				break;
				}
			}
		}
		
			try {
				cat = catrg.selectAll();
				request.setAttribute("categories", cat);
				//encheres auxquelles l'uitlisateur a participé
				/*for(Cookie c : cookies) {
				article = mgr.selectByID(Integer.parseInt(c.getName()));
					articles.add(article);
				}*/
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}

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
		
		doGet(request, response);
	}

}
