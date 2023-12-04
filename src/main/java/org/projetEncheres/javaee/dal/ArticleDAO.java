package org.projetEncheres.javaee.dal;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;

public interface ArticleDAO extends DAO<ArticleVendu>{
	
	List<ArticleVendu> selectByCategorie(String libelle) throws DALException;

	
}
