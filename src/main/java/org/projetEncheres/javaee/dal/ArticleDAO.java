package org.projetEncheres.javaee.dal;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;

public interface ArticleDAO extends DAO<ArticleVendu>{
	
	List<ArticleVendu> selectByCategorie(int id_categorie) throws DALException;
	List<ArticleVendu> selectByNom(String libelle) throws DALException;
	public int insertArticle(ArticleVendu a, Utilisateur u, Categorie c) throws DALException;

	
}
