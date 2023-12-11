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
import javax.servlet.annotation.MultipartConfig;
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
import org.projetEncheres.javaee.dal.jdbc.ArticleDAOJdbcImpl;

/**
 * Servlet implementation class ajoutArticleServlet
 */
@WebServlet("/ajoutArticleServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1 MB
	    maxFileSize = 1024 * 1024 * 10,   // 10 MB
	    maxRequestSize = 1024 * 1024 * 50)  // 50 MB
public class ajoutArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur u;
		List<Categorie> cat;
		
		//recuperer categorie pour la liste de choix		
		CategorieManager catrg = new CategorieManager();
			try {
				cat = catrg.selectAll();
				request.setAttribute("categories", cat);
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}

	
		u = (Utilisateur) session.getAttribute("userCo");
		request.setAttribute("u", u);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutArticle.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Utilisateur u = (Utilisateur) session.getAttribute("userCo");
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    ArticleVendu a = null;
	    Categorie c = null;
	    CategorieManager crg = new CategorieManager();
	    ArticleManager mgr = new ArticleManager();
	    RetraitManager rmgr = new RetraitManager();
	    Retrait r = null;
	    int numeroArticle = -1; // Initialiser avec une valeur par defaut

	    String nom = request.getParameter("nom");
	    String description = request.getParameter("description");
	    System.out.println(description);
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"), dtf);
	    LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"), dtf);
	    int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
	    int prixVente = prixInitial;
	    
        // Récupérer le fichier depuis la requête
        Part filePart = request.getPart("fileInput");
        String fileName = getSubmittedFileName(filePart);
        System.out.println(fileName);
        // Spécifier l'emplacement de sauvegarde du fichier sur le serveur
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
           
        }catch(IOException e) {
        	   e.printStackTrace();
        }


	    try {
	        c = crg.selectByLibelle(request.getParameter("categories"));
	        a = new ArticleVendu(nom, description, dateDebut, dateFin, prixInitial, prixVente, u.getNoUtilisateur(), c.getNoCategorie(), fileName);

	        // inserer dans "article_vendu" et retourne la clé primaire
	        ArticleDAOJdbcImpl adji = new ArticleDAOJdbcImpl();
	        numeroArticle = adji.insertArticle(a, u, c);

	        //set le numéro d'article dans l'objet retrait
	        r = new Retrait(numeroArticle, request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"));
	        rmgr.insertRetrait(r);
	    } catch (DALException e) {
	        e.printStackTrace();
	    } catch (BLLException e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("accueilServlet");
	}
	

	//transforme le chemin du fichier en nom de fichier
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
