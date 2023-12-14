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
 * Servlet implementation class transitionMotDePasseServlet
 */
@WebServlet("/transitionMotDePasseServlet")
public class transitionMotDePasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/transitionMotDePasseOublie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		UtilisateurManager umgr = new UtilisateurManager();
		Utilisateur u = null;
		int idUser = 0;
		PrintWriter out = response.getWriter();
		try {
			u = umgr.selectByEmail(email);
			if (u != null) {
			idUser = u.getNoUtilisateur();
			session.setAttribute("idUser", idUser);
			response.sendRedirect("modificationMotDePasse");
			} else {
				out.println("<b><font color='red'>Aucun utilisateur n'a été trouvé avec cette adresse email</font></b>");
			}
		} catch (BLLException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
			
		}
		
	}


