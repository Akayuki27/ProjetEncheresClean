package org.projetEncheres.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.Utilisateur;

/**
 * Servlet implementation class inscriptionServlet
 */
@WebServlet("/inscriptionServlet")
public class inscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u = null;
		UtilisateurManager mgr = new UtilisateurManager();
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motdePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		if (motdePasse.equals(confirmation)) {
			try {
				u = new Utilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, motdePasse, 0, false);
				mgr.ajouterUtilisateur(u);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("connexionServlet");
	}

}
