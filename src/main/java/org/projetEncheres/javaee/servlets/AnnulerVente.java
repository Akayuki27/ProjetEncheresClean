package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class AnnulerVente
 */
@WebServlet("/AnnulerVente")
public class AnnulerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArt = Integer.parseInt(request.getParameter("noArticle"));
		ArticleManager amgr = new ArticleManager();
		String erreurSuppression = null;
		HttpSession session = request.getSession();
		
		if(idArt != 0) {
		try {
			amgr.delete(idArt);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			erreurSuppression = "<b><font color='red'>La suppression de l'article à échouer</font></b>";
			session.setAttribute("erreurSuppression", erreurSuppression);
		}
		
		request.getRequestDispatcher("accueilServlet").forward(request, response);
	}

}
