package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class modifProfilServlet
 */
@WebServlet("/modifProfilServlet")
public class modifProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifProfil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager mgr = new UtilisateurManager();
		Utilisateur u = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String erreurModifProfil = null;
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("userCo");

		if (request.getParameter("motDePasse") != null) {
			String motdePasse = request.getParameter("motDePasse");
			String confirmation = request.getParameter("confirmation");
			if (motdePasse.equals(confirmation)) {
				try {
					u = new Utilisateur(id, pseudo, nom, prenom, email, tel, rue, codePostal, ville, motdePasse,
							user.getCredit(), user.getAdministrateur());
					mgr.updateUtilisateur(u);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.sendError(403, "Veuillez taper un mot de passe identique pour la confirmation");
			}

			session.setAttribute("userCo", u);

			response.sendRedirect("afficherProfilServlet");

		} else if (request.getParameter("motDePasse") == null || request.getParameter("motDePasse").isEmpty()) {
			u = new Utilisateur(id, pseudo, nom, prenom, email, tel, rue, codePostal, ville, user.getMotDePasse(),
					user.getCredit(), user.getAdministrateur());
			try {
				mgr.updateUtilisateur(u);
				session.setAttribute("userCo", u);
				response.sendRedirect("afficherProfilServlet");

			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}
		} else {
			erreurModifProfil = "<b><font color='red'>La modification du profil à échouer, veuillez saisir des informations valides</font></b>";
			session.setAttribute("erreurModifProfil", erreurModifProfil);
			response.sendRedirect("modifProfilServlet");
		}

	}

}
