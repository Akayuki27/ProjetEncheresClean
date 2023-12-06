package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Enchere;
import org.projetEncheres.javaee.bo.Utilisateur;

public interface EnchereDAO extends DAO<Enchere> {
	
	public void insertEnchere(Enchere e, ArticleVendu a, Utilisateur u) throws DALException;
	public Enchere selectByIdArticle(int idArt) throws DALException;
	public Enchere selectByIdUtilisateur(int idUser)throws DALException;

}
