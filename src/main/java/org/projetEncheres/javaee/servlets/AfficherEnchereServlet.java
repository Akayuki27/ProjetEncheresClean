package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.projetEncheres.javaee.bll.EncheresManager;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class AfficherEnchereServlet
 */
@WebServlet("/AfficherEnchereServlet")
public class AfficherEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// initialisation variables nécéssaires
		// utilisateur conencté
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");

		// différents managers pour manipuler les données + données
		ArticleManager mgr = new ArticleManager();
		UtilisateurManager umgr = new UtilisateurManager();
		Utilisateur u2 = null;
		ArticleVendu article = null;
		List<ArticleVendu> articles = new ArrayList<>();
		String nomArticle = request.getParameter("nomArticle");
		System.out.println(nomArticle);
		int categorie = Integer.parseInt(request.getParameter("categories"));
		List<Categorie> cat;
		Cookie[] cookies = request.getCookies();
		// recuperer categorie pour la liste de choix
		CategorieManager catrg = new CategorieManager();
		EncheresManager emgr = new EncheresManager();
		String where = "WHERE prix_initial != 0 ";
		String[] filter = request.getParameterValues("filter[]");

		try {
			cat = catrg.selectAll();
			request.setAttribute("categories", cat);
			// encheres auxquelles l'uitlisateur a participé
			
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Initialisation filtre sql

		// construction filtre sql
		if (nomArticle != null && !nomArticle.isEmpty() && categorie != 0) {
			// Filtre par nom et catégorie
			where += "AND nom_article LIKE '%" + nomArticle + "%' AND no_categorie=" + categorie;
		} else if (nomArticle != null && !nomArticle.isEmpty()) {
			// Filtre par nom
			where += "AND nom_article LIKE '%" + nomArticle + "%'";

		} else if (categorie != 0) {
			// Filtre par catégorie
			where += "AND no_categorie=" + categorie;
		}

		// switch sur radiobuttons
		if (request.getParameter("ChoixAchatVentes") != null) {
			if (filter != null) {
				
			for (String s : filter) {
				switch (s) {
				case "EnchereOuvertes":
					where += "AND date_fin_encheres >= CAST(GETDATE() AS Date)";
					break;
				case "EnchereEnCours":
					where += "AND CAST(GETDATE() AS Date) BETWEEN date_debut_encheres AND date_fin_encheres AND no_article IN(";
					int compt = 0;
					for (Cookie c : cookies) {
						if (c.getValue() == "1") {
							int id = Integer.parseInt(c.getName());
							if (compt >= 1) {
								where += ",";
							}
							where += id;
							compt++;
						}
					}
					where += ")";
					break;
				case "EnchereRemportes":
					where += "winner=" + u.getNoUtilisateur();
					break;
				case "VenteEnCours":
					where += "AND no_utilisateur=" + u.getNoUtilisateur()
							+ " AND CAST(GETDATE() AS Date) BETWEEN date_debut_encheres AND date_fin_encheres";
					break;
				case "VenteNonDebutes":
					where += "AND no_utilisateur=" + u.getNoUtilisateur() + " AND date_debut_encheres > CAST(GETDATE() AS Date)";
					break;
				case "VentesTermines":
					where += "AND no_utilisateur=" + u.getNoUtilisateur() + " AND etat_vente=1";
					break;
				default:
					where += "AND date_debut_encheres >= CAST(GETDATE() AS Date)";
				}
			}
		} else {
			where += "AND date_fin_encheres >= CAST(GETDATE() AS Date)";
		}
		} 
		System.out.println(where);
		//Recuperation des articles selon filtre
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement("SELECT * FROM ARTICLES_VENDUS " + where);) {
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				article = mapping(rs);
				articles.add(article);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		request.setAttribute("articles", articles);
		
		//recuperation pseudo vendeur
		
		 for (ArticleVendu a : articles) { 
			 int id = a.getNo_utilisateur(); 
			 try {
				u2 = umgr.selectByID(id);
				a.setU2(u2);
				request.setAttribute("u2", u2);
				
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}
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

	private ArticleVendu mapping(ResultSet rs) throws SQLException {
		ArticleVendu a = null;
		int noArt = rs.getInt("no_article");
		String nom = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDate dateDebut = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFin = rs.getDate("date_fin_encheres").toLocalDate();

		int prixInitial = rs.getInt("prix_initial");
		int prixVente = rs.getInt("prix_vente");
		boolean etatVente = rs.getBoolean("etat_vente");
		int no_utilisateur = rs.getInt("no_utilisateur");
		int no_categorie = rs.getInt("no_categorie");

		String image = rs.getString("image");
		int winner = rs.getInt("winner");

		a = new ArticleVendu(noArt, nom, description, dateDebut, dateFin, prixInitial, prixVente, etatVente,
				no_utilisateur, no_categorie, image, winner);

		return a;
	}

}
