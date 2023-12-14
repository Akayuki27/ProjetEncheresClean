package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class GestionCategorieAdmin
 */
@WebServlet("/GestionCategorieAdmin")
public class GestionCategorieAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategorieManager cmgr = new CategorieManager();

        try {
            List<Categorie> categories = null;
			try {
				categories = cmgr.selectAll();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.setAttribute("categories", categories);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutCategorieAdmin.jsp");
            rd.forward(request, response);
        } catch (BLLException e) {
            // Gérez l'exception, par exemple, redirigez vers une page d'erreur
            e.printStackTrace();
            response.sendRedirect("erreur.jsp");
        }
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		CategorieManager cmgr = new CategorieManager();
		String libelle = request.getParameter("ajoutCategorie");
		Categorie c = new Categorie(libelle);
		request.getParameter("ajoutCategorie");
		try {
			cmgr.AjouterCategorie(c);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
