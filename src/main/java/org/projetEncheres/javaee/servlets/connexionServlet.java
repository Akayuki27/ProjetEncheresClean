package org.projetEncheres.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.UtilisateurManager;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class accesEncheres
 */
@WebServlet("/connexionServlet")
public class connexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if("seSouvenirDeMoi".equals(cookie.getName()));
				String pseudo = cookie.getValue();
				request.setAttribute("pseudo", pseudo);
			}
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur u = null;
		UtilisateurManager mger = new UtilisateurManager();
		HttpSession session;
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("motDePasse");
		Utilisateur uSouvenir = null;

		try {
			if (identifiant.contains("@")) {
				u = mger.loginEmail(identifiant, password);
			} else {
				u = mger.login(identifiant, password);
			}
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		if (request.getParameter("seSouvenirDeMoi") != null) {
			Cookie seSouvenirDeMoiCookie;
			seSouvenirDeMoiCookie = new Cookie("seSouvenirDeMoi", u.getPseudo());
			// Cookies qui dure 1 mois
			seSouvenirDeMoiCookie.setMaxAge(31 * 24 * 60 * 60);
			response.addCookie(seSouvenirDeMoiCookie);
		}

		if (u != null) {
			session = request.getSession();
			session.setAttribute("userCo", u);
			Cookie gato;
			gato = new Cookie("lastLogin", u.getEmail());
			// Cookies qui dure 1 journée
			gato.setMaxAge(24 * 60 * 60);
			response.addCookie(gato);
			response.sendRedirect("accueilServlet");
		} else {
			response.sendRedirect("connexionServlet");
		}
	}

}
