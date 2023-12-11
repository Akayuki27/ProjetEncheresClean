package org.projetEncheres.javaee.dal;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;

public interface ArticleDAO extends DAO<ArticleVendu>{
	
	List<ArticleVendu> selectByCategorie(int id_categorie) throws DALException;
	List<ArticleVendu> selectByNom(String libelle) throws DALException;
	public int insertArticle(ArticleVendu a, Utilisateur u, Categorie c) throws DALException;
	List<ArticleVendu> selectByNometCategorie(String nom, int id) throws DALException;
	List<ArticleVendu> selectByCriteria (String c1, String c2, String c3, String c4,String c5) throws DALException;

	
}
