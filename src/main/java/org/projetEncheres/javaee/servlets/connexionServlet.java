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
		if (u != null) {
			session = request.getSession();
			session.setAttribute("userCo", u);
			Cookie gato;
			gato = new Cookie("lastLogin", u.getEmail());
			gato.setMaxAge(31 * 24 * 60 * 60);
			response.addCookie(gato);
			response.sendRedirect("accueilServlet");
		} else {
			response.sendRedirect("connexionServlet");
		}
	}

}
