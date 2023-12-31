package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class modificationMotDePasse
 */
@WebServlet("/modificationMotDePasse")
public class modificationMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		HttpSession session = request.getSession();
		Utilisateur u = null;
		PrintWriter out = response.getWriter();
		UtilisateurManager umgr = new UtilisateurManager();
		int idUser = (int) session.getAttribute("idUser");

		try {
			if (motDePasse.equals(confirmation) && motDePasse != null) {
				u = umgr.selectByID(idUser);
				u.setMotDePasse(motDePasse);
				umgr.updateUtilisateur(u);
				response.sendRedirect("connexionServlet");
			} else {
				out.println("La confirmation du mot de passe n'est pas identique au mot de passe, veuillez réessayer.");
			}
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

}
