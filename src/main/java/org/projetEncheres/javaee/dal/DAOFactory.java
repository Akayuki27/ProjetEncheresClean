package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.dal.jdbc.*;

public abstract class DAOFactory {
	
	 public static ArticleDAOJdbcImpl getArticleDAO() {
	        // Retourner une instance de la classe ArticleDAOJdbcImpl
	        return new ArticleDAOJdbcImpl();
	    }
	 
	 public static UtilisateurDAOJdbcImpl getUtilisateurDAO() {
		 return new UtilisateurDAOJdbcImpl();
	 }
	 
	 public static CategorieDAOJdbcImpl getCategorieDAO() {
		 return new CategorieDAOJdbcImpl();
	 }
	 
	 public static RetraitDAOJdbcImpl getRetraitDAO() {
		 return new RetraitDAOJdbcImpl();
	 }
	 
	 public static EnchereDAOJdbcImpl getEnchereDAO() {
		 return new EnchereDAOJdbcImpl();
	 }

	    // Ajoutez des méthodes similaires pour d'autres DAO si nécessaire

}
