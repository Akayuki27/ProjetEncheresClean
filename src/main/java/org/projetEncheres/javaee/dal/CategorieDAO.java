package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie> {
	public Categorie selectByLibelle(String libelle) throws DALException;

}
