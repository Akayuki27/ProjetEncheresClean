package org.projetEncheres.javaee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ArticleDAO;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.DAOFactory;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ArticleVendu a = new ArticleVendu("nom","description", LocalDate.of(2019, Month.AUGUST, 16), LocalDate.of(2019, Month.AUGUST, 17), 100, 100, 1, 1);
		ArticleDAO cat = DAOFactory.getArticleDAO();
		List<ArticleVendu> cats = new ArrayList<>();
		Categorie c = new Categorie(2,"sport");
		Utilisateur u = new Utilisateur(2,"Simon", "Ben", "Bleu", "eni@gmail.com", "02158789855", "rue des hordes", "666", "enfer", "motDepasse", 0, true);
		
		try {
			cat.delete(1);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(a);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
