package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.dal.jdbc.*;

public abstract class DAOFactory {
	
	 public static ArticleDAOJdbcImpl getArticleDAO() {
	        // Retourner une instance de la classe ArticleDAOJdbcImpl
	        return new ArticleDAOJdbcImpl();
	    }

	    // Ajoutez des méthodes similaires pour d'autres DAO si nécessaire

}
