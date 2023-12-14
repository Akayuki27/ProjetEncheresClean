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
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class achatArticleServlet
 */
@WebServlet("/achatCreditServlet")
public class achatCreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/acheterCredit.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager umgr = new UtilisateurManager();
		HttpSession session = request.getSession();
		int credit = Integer.parseInt(request.getParameter("creditAchat"));
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");

		try {
			umgr.ajouterCredits(u, credit);
			u.setCredit(u.getCredit() + credit);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("accueilServlet");

	}

}
