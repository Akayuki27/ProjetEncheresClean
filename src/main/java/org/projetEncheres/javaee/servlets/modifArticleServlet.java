package org.projetEncheres.javaee.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.projetEncheres.javaee.bll.ArticleManager;
import org.projetEncheres.javaee.bll.BLLException;
import org.projetEncheres.javaee.bll.CategorieManager;
import org.projetEncheres.javaee.bll.RetraitManager;
import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Retrait;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;

/**
 * Servlet implementation class modifArticleServlet
 */
@WebServlet("/modifArticleServlet")
public class modifArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
		List<Categorie> cat;
		int idArt = Integer.parseInt(request.getParameter("noArt"));
		ArticleManager amgr = new ArticleManager();
		ArticleVendu a = null;

		// recuperer categorie pour la liste de choix
		CategorieManager catrg = new CategorieManager();
		try {
			cat = catrg.selectAll();
			request.setAttribute("categories", cat);
			a = amgr.selectByID(idArt);
			request.setAttribute("a", a);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		u = (Utilisateur) session.getAttribute("userCo");
		request.setAttribute("u", u);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifArticle.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Categorie c = null;
		ArticleManager amgr = new ArticleManager();
		RetraitManager rmgr = new RetraitManager();
		Retrait r = null;
		int numeroArticle = Integer.parseInt(request.getParameter("idArt"));
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"), dtf);
		LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"), dtf);
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		int prixVente = prixInitial;
		CategorieManager crg = new CategorieManager();
		ArticleVendu article = null;
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userCo");

		// Récupérer le fichier depuis la requête
		Part filePart = request.getPart("fileInput");
		String fileName = getSubmittedFileName(filePart);
		String uploadDir = request.getServletContext().getRealPath("/");
		String uploadFolder = "uploads";
		String savePath = uploadDir + File.separator + uploadFolder;
		File uploadDirFile = new File(savePath);
		System.out.println(uploadDir);
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdir();
		}

		// Sauvegarder le fichier dans le répertoire spécifié
		Path filePath = Paths.get(savePath, fileName);

		try (InputStream fileContent = filePart.getInputStream()) {
			Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			c = crg.selectByLibelle(request.getParameter("categories"));
			article = new ArticleVendu(numeroArticle, nom, description, dateDebut, dateFin, prixInitial, prixVente,
					false, u.getNoUtilisateur(), c.getNoCategorie(), fileName, 0);
			amgr.update(article);
			r = new Retrait(numeroArticle, request.getParameter("rue"), request.getParameter("codePostal"),
					request.getParameter("ville"));
			rmgr.updateRetrait(r);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("accueilServlet");
	}

	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("content-disposition");
		for (String token : header.split(";")) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
