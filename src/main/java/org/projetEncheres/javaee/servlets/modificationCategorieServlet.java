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
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class modificationCategorieServlet
 */
@WebServlet("/modificationCategorieServlet")
public class modificationCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = null;
		int id = Integer.parseInt(request.getParameter("id"));
		
		session.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModificationArticle.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategorieManager cmgr = new CategorieManager();
		String libelle = request.getParameter("nomCategorie");
		int id =  Integer.parseInt(request.getParameter("id")) ;
		Categorie c = new Categorie(id, libelle);
		
		try {
			cmgr.update(c);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("GestionCategorieAdmin");
		rd.forward(request, response);
	}

}
