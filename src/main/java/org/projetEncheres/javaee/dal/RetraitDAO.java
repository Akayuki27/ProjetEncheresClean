package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Retrait;

public interface RetraitDAO extends DAO<Retrait> {
	
	public void insertRetrait(Retrait retrait, ArticleVendu a) throws DALException;

}
