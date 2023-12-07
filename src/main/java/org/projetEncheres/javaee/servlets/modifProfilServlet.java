package org.projetEncheres.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.Utilisateur;

/**
 * Servlet implementation class modifProfilServlet
 */
@WebServlet("/modifProfilServlet")
public class modifProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
		u = (Utilisateur) session.getAttribute("userCo");
		request.setAttribute("u", u);
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

		if (request.getParameter("motDePasse") != null) {
			String motdePasse = request.getParameter("motDePasse");
			String confirmation = request.getParameter("confirmation");
			if (motdePasse.equals(confirmation)) {
				try {
					u = new Utilisateur(id, pseudo, nom, prenom, email, tel, rue, codePostal, ville, motdePasse, 0,
							false);
					mgr.updateUtilisateur(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					motdePasse = mgr.selectByID(id).getMotDePasse();
					u = new Utilisateur(id, pseudo, nom, prenom, email, tel, rue, codePostal, ville, motdePasse, 0,
							false);
					mgr.updateUtilisateur(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("userCo", u);

		response.sendRedirect("afficherProfilServlet");

	}

}
